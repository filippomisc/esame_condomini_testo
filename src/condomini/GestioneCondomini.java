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

	public Proprietario aggiungiProprietario(String codiceCondominio, String cognome, String nome, int interno, double millesimi, double debito) 
			throws MillesimiSuperatiException{
		return null;
	}
	
	public Spesa aggiungiSpesa(String codiceCondominio, String descrizione, double importo, String data, boolean pagata,
			double percentualeAmministratore){
		return null;
	}
	
	public void pagaSpese(String codiceCondominio, String da, String a){
	} 
	
	public void pagaSpese(String codiceCondominio){
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
