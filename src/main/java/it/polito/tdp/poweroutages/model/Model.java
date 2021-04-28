package it.polito.tdp.poweroutages.model;

import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.poweroutages.DAO.PowerOutageDAO;

public class Model {
	
	PowerOutageDAO podao;
	
	List<PowerOutage> partenza;
	List<PowerOutage> risultato;
	int totCustomersOttimo;
	int totOreGuasto;
	
	public int getTotCustomersOttimo() {
		return totCustomersOttimo;
	}

	public int getTotOreGuasto() {
		return totOreGuasto;
	}

	public Model() {
		podao = new PowerOutageDAO();
	}
	
	public List<Nerc> getNercList() {
		return podao.getNercList();
	}
	
	//metodo per far partire ricerca e che restituisce soluzione finale 

	public List<PowerOutage> trovaPowerOutage(int nercId, int anni,int ore){
		// riempio lista iniziale con PO corrispondendi alla zona chiesta
		partenza= podao.getPowerOutageList(nercId);
		
		//creo lista temporanea dove slavo le sequenze
		List<PowerOutage> parziale = new ArrayList<PowerOutage>();
		
		//inizializzo: 1)lista dove mettero rsultato  2) totale persone colpite che cerco di massimizzare 
		risultato = new ArrayList<PowerOutage>();
		totCustomersOttimo = 0;
		
		cerca(parziale, 0, anni, ore);
		
		return risultato;
	}

	private void cerca(List<PowerOutage> parziale, int livello, int anni, int ore) {
		// casi terminali
		
		//controllo che le ore (durata) di tutte le PO che aggiungo man mano non siano di più del max inserito dall'utente
		int totOre=sommaOrePO(parziale);
		if(totOre>ore) {
			return;
		}
		int totCustomers = this.sommaCustomers(parziale);
		if(totCustomers>totCustomersOttimo){
			//salvo la sequenza temporanea in quella finale 
			risultato=new ArrayList<>(parziale);
			totCustomersOttimo=totCustomers;
			totOreGuasto=totOre;
		}
		//non ho più outages da aggiungere
		if(livello == partenza.size()) 
			return;
		//devo controllare che la sequenza parziale sia valida   !!!!!!
		//if()
			
		//devo controllare che la parziale sia migliore di quella prima	
			
		parziale.add(partenza.get(livello)); //provo con il PowerOutage corrente
		if (differenzaAnniCorretta(parziale,anni)) {
			cerca(parziale, livello+1, anni, ore);
		}
		
		//backtraking 
		parziale.remove(partenza.get(livello));
		cerca(parziale,livello+1,anni,ore);
	
	}

	private int sommaCustomers(List<PowerOutage> parziale) {
		// TODO Auto-generated method stub
		int sommaClienti=0;
		for (PowerOutage po: parziale) {
			sommaClienti+= po.customerAffected;
		}
		return sommaClienti;
	}

	private boolean differenzaAnniCorretta(List<PowerOutage> parziale, int anni) {
		// TODO Auto-generated method stub
		if(parziale.size()==1)
			return true;
		if((parziale.get(parziale.size()-1).getDateEventBegan().getYear())-(parziale.get(0).getDateEventBegan().getYear())<= anni) {
			return true;
		}
		return false;
	}

	private int sommaOrePO(List<PowerOutage> parziale) {
		// TODO Auto-generated method stub
		int contOreTot=0;
		for (PowerOutage po: parziale) {
			contOreTot+=po.oreIncendio;
		}
		return contOreTot;
	}

}
