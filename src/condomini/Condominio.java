package condomini;

import java.util.Collection;

public class Condominio {

	public String getCodice() {
		return null;
	}

	public String getIndirizzo() {
		return null;
	}

	public int getNumeroCivico(){
		return 0;
	}
	
	public double getSaldo() {
		return 0.0;
	}
	
	public Collection<Proprietario> elencoProprietari(){
		return null;
	}
	
	public Collection<Spesa> elencoSpeseCondominioPerDataCrescente(){
		return null;
	}
	
	public Collection<Spesa> elencoSpeseCondominioAncoraDaPagare(){
		return null;
	}

	public Collection<Spesa> elencoSpeseCondominioPerImportoDecrescente(){
		return null;
	}
}
