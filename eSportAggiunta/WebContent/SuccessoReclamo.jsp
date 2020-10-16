<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="beans.UtenteBean" %>

<% 
	Boolean userIn=(Boolean) session.getAttribute("userAuth"); 
	if((userIn==null) || (!userIn.booleanValue())){
		response.sendRedirect("./Login.jsp");
	}
	else{
		String UTENTE="Utente";
		session.setAttribute("ruolo", UTENTE);
%>

<!DOCTYPE html>

<html>
	<head>
		<meta charset="utf-8">
		<title>eSport - Reclamo gestito con successo</title>
		
		<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
		<link rel="stylesheet" type="text/css" href="bootstrap/css/bootstrap.min.css">
		<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="css/Carrello.css">
		<link rel="stylesheet" type="text/css" href="css/Riepilogo.css">
		<link rel="stylesheet" type="text/css" href="css/Button.css">
		<link rel="stylesheet" type="text/css" href="css/Nav.css">
		<link rel="stylesheet" type="text/css" href="css/Footer.css">
	</head>
	
	<body class="py-0">
		<!-- Navigation -->
		<header>
			<%@ include file="NavbarReclamo.jsp" %>
		</header>
		
<!-- Page Content -->
  <div class="container">
        <div class="row py-3">

                <div class="col-lg-12">
                        <div class="riepilogo">
                        	<% UtenteBean userForName=(UtenteBean) session.getAttribute("userLogged"); %>
                            <h2>Reclamo gestito con successo!</h2>
                            <h4>Per maggiori dettagli:</h4>
                            <div class="py-2">
                                <button class="btn btn-secondary bg-dark text-white">
                                        <a id="d-o" class="text-light" href="Reclamo">
                                            Visualizza reclami
                                        </a>
                                </button>
                            </div>
                        </div>
                </div>
            </div>
            <!-- /.row -->
        
        </div>
        <!-- /.container -->
		
		<!-- Footer -->
 		<footer class="bg-dark">
    		<%@ include file="Footer.jsp" %>
  		</footer>
		
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  		<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
  		<script type="text/javascript" src="bootstrap/js/bootstrap.min.js"></script>
	</body>
</html>
<%} %>