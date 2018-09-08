package condomini;

public class Proprietario implements Comparable<Proprietario>{
	
	private String cognome;
	private String nome;
	private int interno;
	private double millesimi; 
	private double debito;

	
	public Proprietario(String cognome, String nome, int interno, double millesimi, double debito) {
		super();
		this.cognome = cognome;
		this.nome = nome;
		this.interno = interno;
		this.millesimi = millesimi;
		this.debito = debito;
	}

	public String getProprietario() {
		return this.cognome + " " + this.nome;
	}
	
	public double getMillesimi() {
		return this.millesimi;
	}

	public int getInterno(){
		return this.interno;
	}

	public void setDebito(double debito) {
		this.debito = debito;
	}

	public double getDebito(){
		return this.debito;
	}

	@Override
	public String toString() {
		return "Proprietario [cognome=" + cognome + ", nome=" + nome + ", interno=" + interno + ", millesimi="
				+ millesimi + ", debito=" + debito + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cognome == null) ? 0 : cognome.hashCode());
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Proprietario other = (Proprietario) obj;
		if (cognome == null) {
			if (other.cognome != null)
				return false;
		} else if (!cognome.equals(other.cognome))
			return false;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public int compareTo(Proprietario arg0) {

		return this.getProprietario().compareTo(arg0.getProprietario());
	}
 
	
}
