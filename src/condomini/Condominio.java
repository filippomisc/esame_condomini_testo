package condomini;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Condominio {
	
	private String codice;
	private String indirizzo;
	private int numeroCivico; 
	private double saldo;
	
	private List<Proprietario> proprietari;
	
	
	public Condominio(String codice, String indirizzo, int numeroCivico, double saldo) {
		this.codice = codice;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.saldo = saldo;
		
		proprietari = new ArrayList<>();
	}	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codice == null) ? 0 : codice.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Condominio other = (Condominio) obj;
		if (codice == null) {
			if (other.codice != null)
				return false;
		} else if (!codice.equals(other.codice))
			return false;
		return true;
	}

	public String getCodice() {
		return codice;
	}

	public String getIndirizzo() {
		return indirizzo;
	}

	public int getNumeroCivico(){
		return numeroCivico;
	}
	
	public double getSaldo() {
		return saldo;
	}
	
	public Collection<Proprietario> elencoProprietari(){
		return this.proprietari;
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

	@Override
	public String toString() {
		return "Condominio [codice=" + codice + ", indirizzo=" + indirizzo + ", numeroCivico=" + numeroCivico
				+ ", saldo=" + saldo + "]";
	}
}
