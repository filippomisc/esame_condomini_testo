package condomini;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

import javax.swing.text.StyleConstants.CharacterConstants;

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
//		
//		//verifica che i millesimi dei condomini non superano quelli massimi
//		double totMill = 0.0;
//		for( Proprietario p : condominio.elencoProprietari()) {
//			totMill += p.getMillesimi();
//		}
//		
//		if(totMill+proprietario.getMillesimi()>1000.0)
//			throw new MillesimiSuperatiException();
//		
		
		
//VERIFICO CHE IL PROPRIETARIO ESISTE GIA NEL CONDOMINIO. SE ESISTE AGGIORNO LE INFO (CANCELLANDO QUELLO VECCHIO E AGGIUNGENDO QUELLO NUOVO
		//TODO SE CI SONO PROBLEMI IN QUESTO METODO VERIFICARE CHE NON SIANO DIPESI DA QUESTO
//		if(condomini.containsKey(codiceCondominio)) {
			for(Proprietario p : condominio.elencoProprietari()) {
				if(p.getInterno()==proprietario.getInterno() && p.equals(proprietario)) {
					isPresente = true;
					proprietarioPres = p;
//					condominio.elencoProprietari().remove(p);//TODO potrebbe dipendere da qui
				}

			}
			
			//verifica che i millesimi dei condomini non superano quelli massimi
			double totMill = 0.0;
			for( Proprietario p : condominio.elencoProprietari()) {
				if(isPresente==true)//se il proprietario è gia presente nell'appartamento bisogna sottrarre i millesimi (di quello gia presente) alla somma, altrimenti si aggiungono sempre millesimi e scatena l'eccezione
					totMill-=proprietarioPres.getMillesimi();
					
				totMill += p.getMillesimi();
			}
			

			if(totMill+proprietario.getMillesimi()>1000.0)
				throw new MillesimiSuperatiException();
			
			if(isPresente==true) {
				condominio.elencoProprietari().remove(proprietarioPres);
			condominio.elencoProprietari().add(proprietario);}
			else 			
				condominio.elencoProprietari().add(proprietario);


//			condominioGiaPresente.elencoProprietari()
//		}else {
			
			
//		}
		return proprietario;
	}
	
	public Spesa aggiungiSpesa(String codiceCondominio, String descrizione, double importo, String data, boolean pagata,
			double percentualeAmministratore){
		
		Spesa spesa = null;
		
		//se la percentuale e 0.0 allora è una spesa ordinaria
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
		
		//paghiamo le spese che si trovano tra due date
		for(Spesa sDaPagare : spesas) {
				condominio.pagaSpesa(sDaPagare);	
		}
	} 
	
	public void calcolaDovutoProprietari(String codiceCondominio){
	}
	
	public void saldaDebito(String codiceCondominio, String cognomeProprietario, String nomeProprietario, double versato) {
	}

	public Collection<Proprietario> elencoProprietariMorosi(String codiceCondominio) {
		return null;
	}
	
	public void leggiFile(String nomeFile) throws IOException {
	}	
}
