<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="beans.*" %>
<%@ page import="java.util.*" %>

<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		String ord="sottomissione desc";
		session.setAttribute("previousPage", "/Ordine?toDo=gestore&order=" + ord);
		response.sendRedirect("./Login.jsp");
	}
	else{
		UtenteBean userForRoleControl=(UtenteBean) session.getAttribute("userLogged");
		if(!userForRoleControl.getRuolo().containsKey(RuoloBean.ORDINI)){
			response.sendRedirect("./OnlyAdminPage.html");
		}
		else{
			session.setAttribute("ruolo", RuoloBean.ORDINI);

			boolean areAttivi=false;
			boolean areChiusi=false;
			LinkedHashSet<ReclamoBean> reclami=(LinkedHashSet<ReclamoBean>) session.getAttribute("Reclami");
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>eSport - Gestione reclami</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/Carrello.css">
		<link rel="stylesheet" type="text/css" href="css/OrdineGestore.css">
		<link rel="stylesheet" type="text/css" href="css/ButtonWhite.css">
		<link rel="stylesheet" type="text/css" href="css/Button.css">
		<link rel="stylesheet" type="text/css" href="css/Nav.css">
		<link rel="stylesheet" type="text/css" href="css/Footer.css">
	</head>
	
	<body class="py-0">
		<!-- Navigation -->
		<header>
			<%@ include file="NavbarOrdini.jsp" %>
		</header>
		
		<!-- Page Content -->
  		<div class="container">

    		<div class="row py-4">

	      	 	  <div class="col-lg-3">
					<h1 class="my-4">Ordina</h1>
	        			
	        			<%! String recente="id desc"; %>
	        			
	        			<div class="list-group">
	         	 			<a href="Reclamo?order=usr" class="list-group-item bb">Utente</a>
	          				<a href="Reclamo?order= <%= recente %>"  class="list-group-item bb">Data [più recente]</a>
	          				<a href="Reclamo?order=id"  class="list-group-item bb">Data [meno recente]</a>
	        			</div>
	        			
	        			<h1 class="my-4">Filtra</h1>
	        			<form class="form-inline" action="InCostruzione.html">
		                    <div class="input-group">
		                        <input id="u-fil" type="text" name="user" class="form-control" placeholder="Username">
		                        <div class="input-group-btn">
		                            <button id="btt" class="btn btn-secondary" type="submit"> 
		                                <i class="fas fa-search"></i>
		                            </button>
		                        </div>
		                    </div>
		                </form>
		             	
		             	<form class="form-inline" action="InCostruzione.html">
		                    <div class="input-group">
		                        <input id="d-fil" type="text" name="date" class="form-control" placeholder="Da yyyy-MM-dd">
		                    </div>
		                    
		                    <div class="input-group">
		                        <input id="d2-fil" type="text" name="dateDue" class="form-control" placeholder="A yyyy-MM-dd">
		                    </div>
		                    
		                    <div id="div-d-filt" class="input-group-btn">
		                          <button id="btt" class="btn btn-secondary" type="submit"> 
		                               Filtra per data <i class="fas fa-search"></i>
		                          </button>
		                    </div>
		                </form>
	      			</div> 
	      			<!-- /.col-lg-3 -->
	      			
	      			<div class="col-lg-9">
						<h1 class="my-4">Reclami in attesa</h1>
	      				
	      				<%
	      					for(ReclamoBean r: reclami){
	      						if(r.getStato().equals(ReclamoBean.IN_ATTESA)){
	      							areAttivi=true;
	      				%>
	      				<div class="row">
                                <div class="col-12 col-md-12 col-lg-12">
                                    <div class="card bg-light card-body mb-3 card bg-faded p-1 mb-3">
                                        <div class="row">
                                            <div class="col-md-6 col-lg-8 card-body">
                                                <h4>
                                                  <a class="title-prod" href="#">
                                                     Reclamo <%= r.getId() %>
                                                  </a>
                                                </h4>
                                                <h6>Segnalato da <%= r.getUsr() %></h6>
                                                <h6>Per l'ordine numero <%= r.getOrdine() %></h6>
                                                <p><%= r.getCommento() %></p>
                                                                            
                                                <button id="carrello-button" class="btn btn-secondary bg-dark text-white">
				                              		<a class="text-light a-btt" href="#">
				                              			Approva
				                              		</a>
				                          		</button>
				                          		
				                          		<button id="carrello-button" class="btn btn-secondary bg-dark text-white">
				                              		<a class="text-light a-btt" href="#">
				                              			Rifiuta
				                              		</a>
				                          		</button>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <%
                        		}
	      					}
	      				
	      					if(!areAttivi){ 
	      				%>
								<h4 class="my-4">Nessun reclamo in attesa</h4>
	      				<% } %>
	      				
	      				<h1 class="my-4">Reclami chiusi</h1>
	      				
	      				<%
	      					for(ReclamoBean r: reclami){
	      						if(r.getStato().equals(ReclamoBean.APPROVATO) && r.getStato().equals(ReclamoBean.RIFIUTATO)
	      								&& reclami.size()!=0){
	      							areChiusi=true;
	      				%>
	      				<div class="row">
                                <div class="col-12 col-md-12 col-lg-12">
                                    <div class="card bg-light card-body mb-3 card bg-faded p-1 mb-3">
                                        <div class="row">
                                            <div class="col-md-6 col-lg-8 card-body">
                                                <h4>
                                                  <a class="title-prod" href="#">
                                                     Reclamo <%= r.getId() %>
                                                  </a>
                                                </h4>
                                                <h5>Reclamo <%= r.getStato() %> </h5>
                                                <h6>Sottomesso da <%= r.getUsr() %></h6>
                                                <h6>Per l'ordine numero <%= r.getOrdine() %></h6>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                        </div>
                        <%
                        		}
	      					}
	      				
	      					if(!areChiusi){ 
	      				%>
								<h4 class="my-4">Nessun reclamo chiuso</h4>
	      				<% } %>
	      			</div>
	        		<!-- /.col-lg-9 -->
	        		
        	</div>
        	<!-- /.row -->
        	
        </div>
        <!-- Container -->
        
		<!-- Footer -->
 		<footer class="bg-dark">
    		<%@ include file="Footer.jsp" %>
  		</footer>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
<%
	}
	//Chiusura secondo else
}
//Chiusura primo else
%>