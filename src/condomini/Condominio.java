package condomini;

import java.nio.channels.NonWritableChannelException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javafx.beans.property.SimpleSetProperty;

public class Condominio {
	
	private String codice;
	private String indirizzo;
	private int numeroCivico;
	private double saldo;
	
	private List<Proprietario> proprietari;
	private List<Spesa> spese;
	private List<Spesa> speseDaPagare;
	private List<Proprietario> morosi;
	
	
	
	public Condominio(String codice, String indirizzo, int numeroCivico, double saldo) {
		this.codice = codice;
		this.indirizzo = indirizzo;
		this.numeroCivico = numeroCivico;
		this.saldo = saldo;
		
		proprietari = new ArrayList<>();
		spese = new ArrayList<>();
		speseDaPagare = new ArrayList<>();
		morosi = new ArrayList<>();
		
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
	
	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Collection<Proprietario> elencoProprietari(){
		Collections.sort(this.proprietari);
		return this.proprietari;
	}
	
	public Collection<Proprietario> elencoMorosi(){
		Collections.sort(this.morosi);
		return this.morosi;
	}
	
	public Collection<Spesa> elencoSpeseCondominioPerDataCrescente(){
		Collections.sort(spese);
		return this.spese;
	}
	
	public Collection<Spesa> elencoSpeseCondominioAncoraDaPagare(){
		Collections.sort(speseDaPagare);

		return this.speseDaPagare;
	}

	public Collection<Spesa> elencoSpeseCondominioPerImportoDecrescente(){
		
		Collections.sort(spese, new ComparatoreDiSpese());
		
		return this.spese;
	}

	@Override
	public String toString() {
		return "Condominio [codice=" + codice + ", indirizzo=" + indirizzo + ", numeroCivico=" + numeroCivico
				+ ", saldo=" + saldo + "]";
	}

	public void pagaSpesa(Spesa spesa) {

		if(spesa instanceof SpesaStraordinaria)
			this.saldo = this.saldo - (((SpesaStraordinaria)spesa).getImporto() + ((SpesaStraordinaria)spesa).getPercentualeAmministratore()*(((SpesaStraordinaria)spesa).getImporto())/100);
		else
			this.saldo-=spesa.getImporto();
			
	}

	public void calcolaSpeseDiProprietario() {
		for(Spesa spesa : elencoSpeseCondominioPerDataCrescente()) {
		for(Proprietario proprietario : this.elencoProprietari()) {
			
			if(spesa instanceof SpesaStraordinaria)
				proprietario.setDebito(
					proprietario.getDebito() + 
					((SpesaStraordinaria)spesa).getImporto()*(proprietario.getMillesimi()/1000)*(1+(((SpesaStraordinaria)spesa).getPercentualeAmministratore()/100)));
			else 
				proprietario.setDebito(
						proprietario.getDebito() + spesa.getImporto()*(proprietario.getMillesimi()/1000));
			}
		}
	}

	public void saldaDebitoProprietario(String cognomeProprietario, String nomeProprietario, double versato) {

		Proprietario proprietario = cercaProprietario(nomeProprietario, cognomeProprietario);
		
		proprietario.setDebito(proprietario.getDebito()-versato);
		setSaldo(this.saldo +versato);
		
	}
	
	public Proprietario cercaProprietario(String nome, String cognome) {
		Proprietario result = null;
		for(Proprietario p : this.proprietari) {
			if(p.getProprietario().compareTo(cognome + " "+ nome)==0)
				result = p;
		}
		return result;
	}
}
