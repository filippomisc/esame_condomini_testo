package condomini;

import java.io.IOException;
import java.util.Collection;

public class GestioneCondomini {

	public Condominio aggiungiCondominio(String indirizzo, int numeroCivico, double saldo){
		return null;
	}
	
	public Condominio cercaCondominio(String codiceCondominio){
		return null;
	}

	public Condominio cercaCondominioPerIndirizzo(String codiceCondominio){
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
