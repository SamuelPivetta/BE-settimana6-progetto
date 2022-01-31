package it.presentation;

import jakarta.ejb.EJB;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import it.business.BancomatEJB;
import it.data.ContoCorrenteDAO;

@WebServlet("/conto")
public class MiaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public MiaServlet() {
        super();
    }
    @EJB
    BancomatEJB bankService;
    


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ContoCorrenteDAO ccDAO = new ContoCorrenteDAO();
		
		//prima creo setter/getter del'EJB
		//poi uso il setter per passare all'EJB il DAO
		//perche? 
		//non potendo farlo nella dichiarazione dell'EJB,
		//abbiamo bisogno di un setter per "Iniettarlo" in esso
		//cosi da poterlo poi utilizzare
		bankService.setCcDAO(ccDAO);
		
		//qui prendiamo i parametri che arrivano dall'index
		//e le teniamo  nella variabile corrispondente
		int idconto = Integer.parseInt(request.getParameter("id"));
		float quantita = Float.parseFloat(request.getParameter("importo"));
		
		
		String tipo = request.getParameter("tipomovimento");
		
		if(!bankService.esisteCc(idconto)) {
			System.out.println("Il conto è insesitente, operazione annullata");
			return;
		}		
		
		//controllo se l'operazione è consentita, se TUTTO_OK non è soddisfatto
		//il metodo si blocca
		if(bankService.controllaOperazione(tipo, idconto, quantita) != bankService.TUTTO_OK) {
			System.out.println("Operazione non consentita");
			return;
		}
		
		
		
		if(tipo.equalsIgnoreCase("preleva")) {			
						
			boolean risultato = bankService.preleva(idconto, quantita);
			
			System.out.println("Prelievo completato!");
			if(risultato) {
				System.out.println("Prelievo completato");
				}else {
					System.out.println("Prelievo non riuscito");
				}
			
		}else if(tipo.equalsIgnoreCase("deposita")) {
			boolean risultato = bankService.deposita(idconto, quantita);			
			System.out.println("Deposito completato!");
			if(risultato) {
				System.out.println("Deposito completato");
			}else {
				System.out.println("Deposito NON riuscito");
			}
			
		}else if(tipo.equalsIgnoreCase("saldo")) {
			
			float saldo = bankService.saldo(idconto);
			
			System.out.println("Il saldo è " + saldo);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}