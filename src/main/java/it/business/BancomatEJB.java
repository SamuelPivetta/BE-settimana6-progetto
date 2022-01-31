package it.business;

import it.data.ContoCorrente;
import it.data.ContoCorrenteDAO;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;

@Stateless
@LocalBean
public class BancomatEJB implements BancomatEJBLocal {
	
	//qui DEPENDENCE-INJECTION, 
	private ContoCorrenteDAO ccDAO;

	public BancomatEJB() {
	}

	public static final int TUTTO_OK = 1;
	public static final int QTA_MINORE_0 = -1;
	public static final int  SALDO_INSUFF = -2;
	

//	Tale metodo tornerà false se i dati sono errati 
//	(ad esempio se la quantità è negativa, se il numero del conto non esiste 
//	o se la quantità da prelevare supera il saldo). 
//	Per semplicità puoi effettuare solo i controlli principali.
	//codici di errore che utilizzeremo:
	//1: controlli passati!
	//-1: controllo non passato, quantita minore di 0
	//-2: quantita da prelevare supera il saldo
	
	public int controllaOperazione(String operazione, int idconto, float quantita) {
		if(quantita < 0) {
			return QTA_MINORE_0;
		}
		ContoCorrente contoControllo = ccDAO.getContoCorrente(idconto);
		
		if(contoControllo.getSaldo() < quantita) {
			return SALDO_INSUFF;
		}
		return TUTTO_OK;
	}
			
	public boolean preleva(int idconto, float quantita) {
		return ccDAO.prelevaCash(idconto, quantita);
	}
	
	public boolean deposita(int idconto, float quantita) {
		return ccDAO.depositaCash(idconto, quantita);
	}
	
	//dobbiamo restituire un float, non tutto il conto
	//quindi:
	public float saldo(int idconto) {
		return ccDAO.getContoCorrente(idconto).getSaldo();
	}
	
	//prende il conto, che diventa null se non esiste!
	//non è null invece, se esiste 
	public boolean esisteCc(int idconto) {
		return ccDAO.getContoCorrente(idconto) != null;
	}
	
	
	public ContoCorrenteDAO getCcDAO() {
		return ccDAO;
	}

	public void setCcDAO(ContoCorrenteDAO ccDAO) {
		this.ccDAO = ccDAO;
	}
}