package controller.gestioneReclamo;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedHashSet;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReclamoBean;
import model.ReclamoModel;

@WebServlet("/Reclamo")
public class Reclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Logger log=Logger.getLogger("OrdineDebugger");

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		String redirectedPage="";
		String order="";
		if(order==null || order.equals(""))
			order="id";
		
		synchronized(session) {
			log.info("Ordine -> verifico che l'utente si sia autenticato");
			Boolean userAuth=(Boolean) session.getAttribute("userAuth");
			if((userAuth==null) || (!userAuth.booleanValue())) {
				log.info("Ordine -> stabilisco a quale pagina tornare");
				
				redirectedPage="/Login.jsp";
				response.sendRedirect(request.getContextPath() + redirectedPage);
			}
			else {
				ReclamoModel reclamoModel=new ReclamoModel();
				try {
					LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) reclamoModel.doRetrieveAll(order);
					session.setAttribute("Reclami", reclami);
				} 
				catch (SQLException e) {
					log.info("");
					e.printStackTrace();
				}
			}
			
			RequestDispatcher view=request.getRequestDispatcher("Reclamo.jsp");
			view.forward(request, response);		
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
