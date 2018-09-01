import java.io.IOException;
import java.util.*;

import condomini.*;

public class Esempio {

	public static void main(String[] args) throws IOException, MillesimiSuperatiException{
		
		GestioneCondomini gc = new GestioneCondomini();
		
		System.out.println("******************************************");
		System.out.println("*             R1. CONDOMINI              *");
		System.out.println("******************************************");
		
		System.out.println("\nAggiunto nuovo condominio");
		Condominio c1 = gc.aggiungiCondominio("Via Pastrengo", 30, 28000.0);
          
		System.out.println("\nDettagli condominio:");
		System.out.println("Codice: "+c1.getCodice());
		System.out.println("Indirizzo: "+c1.getIndirizzo()+ " "+c1.getNumeroCivico());
		System.out.println("Saldo: "+c1.getSaldo());

		System.out.print("\nAggiunti altri condomini: ");
		Condominio c2 = gc.aggiungiCondominio("Via Massena", 10, -340.0);
		Condominio c3 = gc.aggiungiCondominio("Corso Castelfidardo", 54, 0.0);
		Condominio c4 = gc.aggiungiCondominio("Piazza Vittorio", 32, 569.0);
		System.out.println(c2.getCodice() + ", "+c3.getCodice()+ ", "+c4.getCodice());
		
		System.out.print("\nRicerca del condominio con codice PASTRENGO30: ");
		Condominio c5 = gc.cercaCondominio("PASTRENGO30");
		System.out.println("Indirizzo: "+c5.getIndirizzo()+ " "+c5.getNumeroCivico());

		System.out.print("\nRicerca del condominio contenente nell'indirizzo la stringa 'telf': ");
		Condominio c6 = gc.cercaCondominioPerIndirizzo("telf");
		System.out.println("Indirizzo: "+c6.getIndirizzo()+ " "+c6.getNumeroCivico());

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*             R2. PROPRIETARI            *");
		System.out.println("******************************************");
		
		System.out.println("Aggiunto proprietario");
		Proprietario p = gc.aggiungiProprietario("PASTRENGO30", "Rossi", "Mario", 1, 105.0, 0.0);
		
		System.out.println("\nDettagli proprietario:");
		System.out.println("Proprietario: "+p.getProprietario());
		System.out.println("Interno: "+p.getInterno());
		System.out.println("Millesimi: "+p.getMillesimi());
		
		System.out.println("\nAggiunti altri proprietari");
		Proprietario vm = gc.aggiungiProprietario("PASTRENGO30", "Verdi", "Martina", 2, 262.0, 120.0);
		Proprietario vf = gc.aggiungiProprietario("PASTRENGO30", "Verdi", "Filippo", 3, 120.0, 800.0);
		Proprietario ga = gc.aggiungiProprietario("PASTRENGO30", "Gialli", "Arturo", 4, 246.0, 0.0);
		Proprietario bg = gc.aggiungiProprietario("PASTRENGO30", "Bruni", "Giuliana", 5, 178.0, 0.0);
		Proprietario nm = gc.aggiungiProprietario("PASTRENGO30", "Neri", "Marco", 6, 89.0, 0.0);
		
		gc.aggiungiProprietario("MASSENA10", "Giordano", "Diego", 1, 380.0, 3000.0);
		gc.aggiungiProprietario("MASSENA10", "Ferrero", "Gianni", 2, 220.0, 80.0);
		gc.aggiungiProprietario("MASSENA10", "Meneghetti", "Pina", 3, 400.0, 0.0);
		gc.aggiungiProprietario("CASTELFIDARDO54", "Rana", "Giacomo", 1, 670.2, 0.0);
		gc.aggiungiProprietario("CASTELFIDARDO54", "Volpi", "Luciana", 2, 329.8, 0.0);
		gc.aggiungiProprietario("VITTORIO32", "Bertolli", "Lucia", 1, 220.0, 0.0);
		gc.aggiungiProprietario("VITTORIO32", "Fumagalli", "Vittorio", 2, 180.0, 0.0);
		gc.aggiungiProprietario("VITTORIO32", "Martini", "Marco", 3, 350.0, 0.0);
		gc.aggiungiProprietario("VITTORIO32", "Superga", "Giovanni", 4, 250.0, 120.0);
		
		/*
		System.out.println("\nAggiunto proprietario che fa superare il numero di millesimi");
		gc.aggiungiProprietario("PASTRENGO30", "Ricco", "Gastone", 7, 650.0, 0.0);*/

		System.out.println("\nElenco proprietari del condominio con codice "+c1.getCodice()+" "
				+ "(in ordine alfabetico):");
		LinkedList<Proprietario> listaProprietari = new LinkedList<Proprietario>(c1.elencoProprietari());
		for(Proprietario ptemp : listaProprietari){
			System.out.println("Cognome e nome: "+ptemp.getProprietario()+", interno: "+ptemp.getInterno()+" millesimi: "+ptemp.getMillesimi()+ ", debito: "+ptemp.getDebito());
		}
		
		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*               R3. SPESE                *");
		System.out.println("******************************************");

		System.out.println("Aggiungi spesa ordinaria");
		Spesa s1 = gc.aggiungiSpesa("PASTRENGO30", "Acqua", 2000.0, "20161115", false, 0.0);
		
		System.out.println("Dettagli spesa ordinaria:");
		System.out.println("Descrizione: "+s1.getDescrizione());
		System.out.println("Importo: "+s1.getImporto());
		System.out.println("Data: "+s1.getData());

		System.out.println("\nAggiungi spesa straordinaria");
		Spesa s2 = gc.aggiungiSpesa("PASTRENGO30", "Rifacimento facciata", 14000.0, "20160130", true, 4.5);

		System.out.println("Dettagli spesa straordinaria:");
		System.out.println("Descrizione: "+s2.getDescrizione());
		System.out.println("Importo: "+s2.getImporto());
		System.out.println("Data: "+s2.getData());

		System.out.println("\nAggiungi altre spese");
		gc.aggiungiSpesa("PASTRENGO30", "Pulizia scale semestre 1", 3500.0, "20160630",  true, 0.0);
		gc.aggiungiSpesa("PASTRENGO30", "Pulizia scale semestre 2", 3800.0, "20161231",  false, 0.0);
		gc.aggiungiSpesa("PASTRENGO30", "Luce scale", 1300.0, "20161223", false, 0.0);
		
		gc.aggiungiSpesa("MASSENA10", "Luce scale", 1000.0, "20161231", false, 0.0);
		gc.aggiungiSpesa("MASSENA10", "Acqua", 1400.0, "20161010", false, 0.0);
		gc.aggiungiSpesa("MASSENA10", "Riscaldamento prima rata", 1200.0, "20161220", false, 0.0);
		gc.aggiungiSpesa("MASSENA10", "Riscaldamento seconda rata", 3500.0, "20160415", true, 0.0);
		gc.aggiungiSpesa("MASSENA10", "Rifacimento scale", 5000.0, "20160115", true, 0.0);

		gc.aggiungiSpesa("CASTELFIDARDO54", "Luce scale", 500.0, "20161231", false, 0.0);
		gc.aggiungiSpesa("CASTELFIDARDO54", "Acqua", 900.0, "20160204", true, 0.0);
		
		gc.aggiungiSpesa("VITTORIO32", "Riscaldamento", 4000.0, "20160424", true, 0.0);
		gc.aggiungiSpesa("VITTORIO32", "Acqua", 2000.0, "20161213", false, 0.0);
		gc.aggiungiSpesa("VITTORIO32", "Sostituzione grondaie", 3000.0, "20160713", true, 3.5);
		gc.aggiungiSpesa("VITTORIO32", "Rifacimento pavimentazione cantina", 5000.0, "20161003", false, 4.0);
		
		System.out.println("\nElenco spese del condominio con codice "+c1.getCodice()+" "
				+ "(per data crescente):");
		LinkedList<Spesa> listaSpese = new LinkedList<Spesa>(c1.elencoSpeseCondominioPerDataCrescente());
		for(Spesa stemp : listaSpese){
			System.out.println("Data: "+stemp.getData()+", descrizione: "+stemp.getDescrizione()+", importo "+stemp.getImporto()+"");
		}

		System.out.println("\nElenco spese del condominio con codice "+c1.getCodice()+" "
				+ "(per importo decrescente):");
		listaSpese = new LinkedList<Spesa>(c1.elencoSpeseCondominioPerImportoDecrescente());
		for(Spesa stemp : listaSpese){
			System.out.println("Data: "+stemp.getData()+", descrizione: "+stemp.getDescrizione()+", importo "+stemp.getImporto()+"");
		}
		
		System.out.println("\nElenco spese del condominio con codice "+c1.getCodice()+" "
				+ "che devono ancora essere pagate (per data crescente):");
		listaSpese = new LinkedList<Spesa>(c1.elencoSpeseCondominioAncoraDaPagare());
		for(Spesa stemp : listaSpese){
			System.out.println("Data: "+stemp.getData()+", descrizione: "+stemp.getDescrizione()+", importo "+stemp.getImporto()+"");
		}

		System.out.println("\nPaga spese di Novembre 2016 del condominio con codice "+c1.getCodice());
		System.out.print("Saldo condominio prima del pagamento: "+c1.getSaldo()+" ");
		gc.pagaSpese(c1.getCodice(), "20161101", "20161130");
		System.out.println("Saldo condominio dopo il pagamento: "+c1.getSaldo());

		System.out.println("\nPaga tutte le rimanenti spese del condominio con codice "+c1.getCodice());
		gc.pagaSpese(c1.getCodice());
		System.out.println("Saldo condominio dopo il pagamento: "+c1.getSaldo());

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*        R4. PAGAMENTI PROPRIETARI       *");
		System.out.println("******************************************");
		
		System.out.println("\nCalcola importo dovuto dai proprietari degli appartamenti "
				+ "del condominio con codice "+c1.getCodice()+ " e ne aggiorna il debito");
		gc.calcolaDovutoProprietari(c1.getCodice());
		for(Proprietario ptemp : listaProprietari){
			System.out.println("Debito aggiornato di: "+ptemp.getProprietario()+" = "+ptemp.getDebito());
		}
		
		System.out.print("\nVersamento della rata dovuta da parte del proprietario Rossi Mario. ");
		gc.saldaDebito("PASTRENGO30", "Rossi", "Mario", p.getDebito());
		System.out.println("Il nuovo saldo del condominio e': "+c1.getSaldo());
		
		System.out.print("\nVersamento delle rate dovute da parte di altri proprietari. ");
		gc.saldaDebito("PASTRENGO30", "Bruni", "Giuliana", bg.getDebito());
		gc.saldaDebito("PASTRENGO30", "Verdi", "Filippo", vf.getDebito());
		gc.saldaDebito("PASTRENGO30", "Neri", "Marco", nm.getDebito());

		System.out.println("\nElenco proprietari morosi nel condominio con codice " +c1.getCodice()+"");
		List<Proprietario> proprietariMorosi = new LinkedList<Proprietario>(gc.elencoProprietariMorosi(c1.getCodice()));
		for(Proprietario ptemp : proprietariMorosi){
			System.out.println("Proprietario: "+ptemp.getProprietario()+", rata da pagare: "+ptemp.getDebito());
		}

		System.out.println("\n");
		System.out.println("******************************************");
		System.out.println("*         R5. CARICAMENTO DA FILE        *");
		System.out.println("******************************************");

		System.out.println("\nCaricamento di altre informazioni da file e aggiunta alla struttura dati.");
		gc.leggiFile("input.txt");
		
		System.out.println("\nAggiunte le spese per il condominio con codice CASTELFIDARDO54. Nuovo elenco spese:");
		for (Spesa stemp : gc.cercaCondominio("CASTELFIDARDO54").elencoSpeseCondominioPerDataCrescente()){
			System.out.println("Data: "+stemp.getData()+", descrizione: "+stemp.getDescrizione()+", importo "+stemp.getImporto()+"");
		}

	}

}

