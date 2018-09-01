package condomini;

public class Proprietario{
	
	String cognome;
	String nome;
	int interno;
	double millesimi; 
	double debito;

	public String getProprietario() {
		return this.cognome + " " + this.nome;
	}
	
	public double getMillesimi() {
		return this.millesimi;
	}

	public int getInterno(){
		return this.interno;
	}

	public double getDebito(){
		return this.debito;
	}
 
}
