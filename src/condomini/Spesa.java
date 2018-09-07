package condomini;

public class Spesa implements Comparable<Spesa>{
	
	private String descrizione;
	private double importo;
	private String data;
	private boolean allaRegistrazione;
	
	

	public Spesa(String descrizione, double importo, String data, boolean allaRegistrazione) {
		this.descrizione = descrizione;
		this.importo = importo;
		this.data = data;
		this.allaRegistrazione = allaRegistrazione;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public double getImporto() {
		return importo;
	}

	public String getData() {
		return data;
	}

	public boolean isAllaRegistrazione() {
		return allaRegistrazione;
	}

	@Override
	public String toString() {
		return "Spesa [descrizione=" + descrizione + ", importo=" + importo + ", data=" + data + ", allaRegistrazione="
				+ allaRegistrazione + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (allaRegistrazione ? 1231 : 1237);
		result = prime * result + ((data == null) ? 0 : data.hashCode());
		result = prime * result + ((descrizione == null) ? 0 : descrizione.hashCode());
		long temp;
		temp = Double.doubleToLongBits(importo);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		Spesa other = (Spesa) obj;
		if (allaRegistrazione != other.allaRegistrazione)
			return false;
		if (data == null) {
			if (other.data != null)
				return false;
		} else if (!data.equals(other.data))
			return false;
		if (descrizione == null) {
			if (other.descrizione != null)
				return false;
		} else if (!descrizione.equals(other.descrizione))
			return false;
		if (Double.doubleToLongBits(importo) != Double.doubleToLongBits(other.importo))
			return false;
		return true;
	}

	@Override
	public int compareTo(Spesa s) {
		
		int d1 = Integer.parseInt(this.data);
		int d2 = Integer.parseInt(s.getData());
		
		if(d1>d2)
			return 1;
		else if(d1<d2)
			return -1;
		else
			return 0;
	}
	
	

}
