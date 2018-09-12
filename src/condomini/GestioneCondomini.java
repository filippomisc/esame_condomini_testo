package condomini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.text.StyleConstants.CharacterConstants;

import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;

public class GestioneCondomini {
	
	Map<String, Condominio> condomini;

	public GestioneCondomini() {
		this.condomini=new HashMap<>();
	}

	public Condominio aggiungiCondominio(String indirizzo, int numeroCivico, double saldo){
		
		Condominio result = null;
		
		String numeroCivicoStr = Integer.toString(numeroCivico);
		
		String a = "";
		String iString =indirizzo.toUpperCase();
		
		if(iString.contains("VIA")) {
			a = iString.substring(3);
//			indirizzo.replace("VIA", "").replace(" ", "");
			
		}else if(iString.contains("PIAZZA")) {
			a = iString.substring(6);
//			indirizzo.replace("PIAZZA", "").replace(" ", "");
			
		}else if(iString.contains("CORSO")) {
			a = iString.substring(5);
//			indirizzo.replace("CORSO", "").replace(" ", "");
		}
		        
		String semiID = a.replace(" ", "");
		
		String codice = semiID + numeroCivicoStr;
		
		
		
			
//		if(this.condomini.containsKey(codice))
		if(cercaCondominio(codice)!=null)
			result = this.condomini.get(codice);
		else {
			result = new Condominio(codice, indirizzo, numeroCivico, saldo);
			
		
		condomini.put(codice, result);
		}
		
		return result;
	}
	
	public Condominio cercaCondominio(String codiceCondominio){
		if(this.condomini.containsKey(codiceCondominio))
			return this.condomini.get(codiceCondominio);
		else
			return null;
	}

	public Condominio cercaCondominioPerIndirizzo(String codiceCondominio){
		for(Condominio condominio : this.condomini.values()) {
			if(condominio.getIndirizzo().contains(codiceCondominio))
				return condominio;
		}
			return null;
	}

	public Proprietario aggiungiProprietario(String codiceCondominio, String cognome, String nome, int interno, double millesimi, double debito) throws MillesimiSuperatiException{
		
		Proprietario proprietario = new Proprietario(cognome, nome, interno, millesimi, debito);
		boolean isPresente = false;
		Proprietario proprietarioPres = null;
		
		Condominio condominio = cercaCondominio(codiceCondominio);

		
		
//VERIFICO CHE IL PROPRIETARIO ESISTE GIA NEL CONDOMINIO. SE ESISTE AGGIORNO LE INFO (CANCELLANDO QUELLO VECCHIO E AGGIUNGENDO QUELLO NUOVO
			for(Proprietario p : condominio.elencoProprietari()) {
				if(p.getInterno()==proprietario.getInterno() && p.equals(proprietario)) {
					isPresente = true;
					proprietarioPres = p;
				}

			}
			
			//calolo i millesimi di tutti i proprietari di un condominio 
			double totMill = 0.0;
			for( Proprietario p : condominio.elencoProprietari()) {
				if(isPresente==true)//se il proprietario � gia presente nell'appartamento bisogna sottrarre i millesimi (di quello gia presente) alla somma, altrimenti si aggiungono sempre millesimi e scatena l'eccezione
					totMill-=proprietarioPres.getMillesimi();
					
				totMill += p.getMillesimi();
			}
			
			//verifica che i millesimi dei condomini non superano quelli massimi
			if(totMill+proprietario.getMillesimi()>1000.0)
				throw new MillesimiSuperatiException();
			
			if(isPresente==true) 
				condominio.elencoProprietari().remove(proprietarioPres);
						
			condominio.elencoProprietari().add(proprietario);

			return proprietario;
	}
	
	public Spesa aggiungiSpesa(String codiceCondominio, String descrizione, double importo, String data, boolean pagata,
			double percentualeAmministratore){
		
		Spesa spesa = null;
		
		//se la percentuale e 0.0 allora � una spesa ordinaria
		if(percentualeAmministratore == 0.0)
			spesa = new Spesa(descrizione, importo, data, pagata);
		//altrimenti...
		else spesa = new SpesaStraordinaria(descrizione, importo, data, pagata, percentualeAmministratore);
		
		Condominio condominio = cercaCondominio(codiceCondominio);
		

		//se la spesa si paga alla registrazione:
		//-pagala
		//-aggiungila all'elenco spese
		if(spesa.isAllaRegistrazione()==true) {
			condominio.pagaSpesa(spesa);//il casting forse non la fa funzionare
			condominio.elencoSpeseCondominioPerDataCrescente().add(spesa);

			//altrimenti:
			//-non pagarla per ora
			//-aggiungila all'elenco "spese"
			//-aggiungila all'elenco "spese da pagare"
		}else {
			condominio.elencoSpeseCondominioPerDataCrescente().add(spesa);
			condominio.elencoSpeseCondominioAncoraDaPagare().add(spesa);
		}
			
		return spesa;
	}
	
	//serve per pagare le spese di un periodo
	public void pagaSpese(String codiceCondominio, String da, String a){
		
		//convertiamo le date da String a int
		int inizio = Integer.parseInt(da);
		int fine = Integer.parseInt(a);
		
		Condominio condominio = cercaCondominio(codiceCondominio);
		
		Collection<Spesa> spesas= condominio.elencoSpeseCondominioAncoraDaPagare();
		
		//paghiamo le spese che si trovano tra due date
		for(Spesa sDaPagare : spesas) {
			if(Integer.parseInt(sDaPagare.getData()) >= inizio && Integer.parseInt(sDaPagare.getData()) <= fine) {
				condominio.pagaSpesa(sDaPagare);
			}
			
		}
	} 
	
	public void pagaSpese(String codiceCondominio){
		
		Condominio condominio = cercaCondominio(codiceCondominio);
		
		Collection<Spesa> spesas= condominio.elencoSpeseCondominioAncoraDaPagare();
		
		//paghiamo tutte le spese della lista condominio.elencoSpeseCondominioAncoraDaPagare()
		for(Spesa sDaPagare : spesas) {
				condominio.pagaSpesa(sDaPagare);	
		}
	} 
	
	public void calcolaDovutoProprietari(String codiceCondominio){
		cercaCondominio(codiceCondominio).calcolaSpeseDiProprietario();
	}
	
	public void saldaDebito(String codiceCondominio, String cognomeProprietario, String nomeProprietario, double versato) {
		cercaCondominio(codiceCondominio).saldaDebitoProprietario(cognomeProprietario, nomeProprietario, versato);

		
	}

	public Collection<Proprietario> elencoProprietariMorosi(String codiceCondominio) {
		
		Collection<Proprietario> proprietarios = cercaCondominio(codiceCondominio).elencoProprietari();
		
		Collection<Proprietario> morosi = cercaCondominio(codiceCondominio).elencoMorosi();
		
		for(Proprietario proprietario : proprietarios) {
			if(proprietario.getDebito()!=0.0)
				morosi.add(proprietario);	
		}
		
		
		return morosi;
	}
	
	public void leggiFile(String nomeFile) throws IOException {
		
		BufferedReader in = new BufferedReader(new FileReader(nomeFile));
    	
    
    	
    	
		String linea = "";

		
		while ((linea = in.readLine()) != null) {
			
			try {
				
			    StringTokenizer st = new StringTokenizer(linea, ";");
			    String codCondominio = st.nextToken().trim();
			    

			    
			    	 String descrizione = st.nextToken().trim();    
			    	 double importo = Double.parseDouble(st.nextToken().trim()); //conversione da String a double
			    	 String data = st.nextToken().trim();
			    	 boolean pagata = Boolean.valueOf(st.nextToken().trim());    //conversione da String a boolean
					 double percentualeAmministratore = Double.parseDouble(st.nextToken().trim());
					 //a scopo informativo------ da String a int
//					 int numero = Integer.parseInt(st.nextToken().trim()); 
					 
					 					 
					 aggiungiSpesa(codCondominio, descrizione, importo, data, pagata, percentualeAmministratore);
					 
					 
					 
					 

			    
			} 
			catch (Exception e) {
			    //System.err.println("Errore");
				e.printStackTrace(); 
			}
		}
		
    				in.close();

    }


}
