package controller.gestioneReclamo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.RecensioneBean;
import beans.ReclamoBean;
import beans.UtenteBean;
import model.RecensioneModel;
import model.ReclamoModel;


@WebServlet("/SottomissioneReclamo")
public class SottomissioneReclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("SottomissioneReclamoDebugger");
	String WRITE="write";
	String SAVE="save";
	
	String redirectedPage="";
	String numeroOrdine="";
   
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();

		log.info("SottomissioneReclamo -> controllo l'azione da eseguire");
		String needTo=request.getParameter("needTo");
		log.info("SottomissioneReclamo -> azione: " + needTo);
		if(needTo==null || needTo.equals(""))
			needTo=WRITE;
		
		numeroOrdine=request.getParameter("numeroOrdine");

		log.info("SottomissioneReclamo -> controllo che l'utente sia autenticato");
		Boolean userAuth=(Boolean) session.getAttribute("userAuth");
		if((userAuth==null) || (!userAuth.booleanValue())) {
			session.setAttribute("previousPage", "");

			redirectedPage="/Login.jsp";
			response.sendRedirect(request.getContextPath() + redirectedPage);
		}
		else {
			log.info("SottomissioneReclamo -> se autenticato e ha fatto l'ordine procedo");

			if(needTo.equals(WRITE)) {
				session.setAttribute("numeroOrdine", numeroOrdine);
				
				redirectedPage="/LasciaReclamo.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else if(needTo.equals(SAVE)) {
				
				log.info("SottomissioneReclamo -> ottengo l'utente che sta lasciando il reclamo");
				UtenteBean user=(UtenteBean) session.getAttribute("userLogged");
				ReclamoModel reclamoModel=new ReclamoModel();
					
				log.info("SottomissioneReclamo -> ottengo l'ordine per cui si sta lasciando il reclamo");
				numeroOrdine=(String) session.getAttribute("numeroOrdine");
					
				log.info("SottomissioneReclamo -> ottengo il commento e gestisco i caratteri speciali");
				String commento=request.getParameter("commento");
				commento=reclamoModel.correzione(commento);
				
				log.info("SottomissioneReclamo -> effettuo controllo sul commento");
				
				if(commento==null || commento.length()<RecensioneBean.LUNGHEZZA_MINIMA) {
					if(commento.length()<RecensioneBean.LUNGHEZZA_MINIMA)
						session.setAttribute("erroreCommento", "errore");
					
					session.setAttribute("commento", commento);
					response.sendRedirect(request.getContextPath() + "/SottomissioneReclamo?needTo=write&numeroOrdine=" + numeroOrdine);
				}
				else {
					

					log.info("SottomissioneReclamo -> creo il reclamo");
					ReclamoBean reclamo = new ReclamoBean();
					reclamo.setStato(ReclamoBean.IN_ATTESA);
					reclamo.setCommento(commento);
					reclamo.setUsr(user.getUsername());
					reclamo.setOrdine(numeroOrdine);
						
					log.info("SottomissioneReclamo -> reclamo da salvare: " + reclamo.getId() 
					    + ", " + reclamo.getOrdine() 
						+ ", " + reclamo.getUsr()
						+ "\n" + reclamo.getCommento());
						
					log.info("SottomissioneReclamo -> salvo il reclamo");
					try {
						reclamoModel.doSave(reclamo);
						
						session.removeAttribute("numeroOrdine");
							
						log.info("SottomissioneReclamo -> vado alla pagina di successo per il reclamo");
						redirectedPage="/SuccessoReclamo.jsp";
						response.sendRedirect(request.getContextPath() + redirectedPage);
					} 
					catch (SQLException e) {
						log.info("LasciaRecensione -> errore salvataggio recensione");
						e.printStackTrace();
					}
				}
			}
		}
		//Fine controllo autenticazione
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		
	}

}