package controller.gestioneAccount;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.RuoloBean;

@WebServlet("/Ruolo")
public class Ruolo extends HttpServlet {
	private static final long serialVersionUID = 1L;
    Logger log=Logger.getLogger("RuoloDebugger");  
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String permesso="";
		String redirectedPage="";
		
		HttpSession session=request.getSession();
		synchronized(session) {	
			log.info("Ruolo -> sono nella servlet Ruolo");
			
			permesso=request.getParameter("permesso");
			log.info("Ruolo -> permesso: " + permesso);
			
			session.setAttribute("ruolo", permesso);

			log.info("Ruolo -> se gestore degli ordini vado alla pagina di gestione degli ordini attivi");
			if(permesso.equals(RuoloBean.ORDINI))
				redirectedPage="/OrdiniAttivi";
			else if(permesso.equals(RuoloBean.CATALOGO)) {
				log.info("Ruolo -> vado alla pagina di gestione del catalogo");
				redirectedPage="/GestioneCatalogo?tipo=Divisa&order=nome";
			}
			else {
				log.info("Ruolo -> vado alla Home Page");
				redirectedPage="/Index.jsp";
			}
		}
		
		response.sendRedirect(request.getContextPath() + redirectedPage);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
