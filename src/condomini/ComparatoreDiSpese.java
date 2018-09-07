package condomini;

import java.util.Comparator;

public class ComparatoreDiSpese implements Comparator<Spesa>{
	


	@Override
	public int compare(Spesa o1, Spesa o2) {
		int d1 = (int) o1.getImporto();
		int d2 = (int) o2.getImporto();
		
		if(d1<d2)
			return 1;
		else if(d1>d2)
			return -1;
		else
			return 0;
	}

}
