package controller.gestioneReclamo;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.ReclamoBean;
import model.ReclamoModel;

@WebServlet("/AggiornaReclamo")
public class AggiornaReclamo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session=request.getSession();
		
		synchronized(session) {
			String stato=request.getParameter("stato");
			if (stato.equalsIgnoreCase(ReclamoBean.APPROVATO))
				stato=ReclamoBean.APPROVATO;
			else if(stato.equalsIgnoreCase(ReclamoBean.RIFIUTATO))
				stato=ReclamoBean.RIFIUTATO;
			else
				stato=ReclamoBean.IN_ATTESA;
				
			String id=request.getParameter("reclamoId");
			
			ReclamoModel reclamoModel=new ReclamoModel();
			
			ReclamoBean reclamoBean=new ReclamoBean();
			reclamoBean.setId(Integer.parseInt(id));
			reclamoBean.setStato(stato);
			
			try {
				reclamoModel.doUpdate(reclamoBean);
				
				response.sendRedirect(request.getContextPath() + "/Reclamo");
			} 
			catch(SQLException e) {
				e.printStackTrace();
			}
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
