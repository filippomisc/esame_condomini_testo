package condomini;

public class SpesaStraordinaria extends Spesa{
	
	private double percentuale;
	
	
	
	public SpesaStraordinaria(String descrizione, double importo, String data, boolean allaRegistrazione, double percentuale) {
		super(descrizione, importo, data, allaRegistrazione);
		this.percentuale = percentuale;
	}



	public double getPercentualeAmministratore() {
		return percentuale;
	}
}
