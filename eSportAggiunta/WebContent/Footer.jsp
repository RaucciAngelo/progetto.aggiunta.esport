<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

	<section id="footer">
		<div class="container">
			<div class="row text-center text-xs-center text-sm-left text-md-left">
				<div class="col-xs-12 col-sm-4 col-md-4">
					<h5>Suggerimenti</h5>
					<ul class="list-unstyled quick-links">
						<li><a href="Index.jsp"><i class="fa fa-angle-double-right"></i>Home</a></li>
						<li><a href="InCostruzione.html"><i class="fa fa-angle-double-right"></i>Supporto</a></li>
						<li><a href="Carrello"><i class="fa fa-angle-double-right"></i>Il mio carrello</a></li>
					
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<h5>Catalogo</h5>
					<ul class="list-unstyled quick-links">
						<li><a href="Catalogo?tipo=Divisa&order=nome"><i class="fa fa-angle-double-right"></i>Divise</a></li>
						<li><a href="Catalogo?tipo=Pantaloncini&order=nome"><i class="fa fa-angle-double-right"></i>Pantaloncini</a></li>
						<li><a href="Catalogo?tipo=Scarpe&order=nome"><i class="fa fa-angle-double-right"></i>Scarpe da gioco</a></li>
				
					</ul>
				</div>
				<div class="col-xs-12 col-sm-4 col-md-4">
					<h5>Area utente</h5>
					<ul class="list-unstyled quick-links">
						<li><a href="Profilo.jsp"><i class="fa fa-angle-double-right"></i>Profilo</a></li>
						<%! String sttmnDesc="sottomissione desc"; %>
						<li><a href="Ordine?toDo=utente&order=<%= sttmnDesc %>"><i class="fa fa-angle-double-right"></i>Ordini effettuati</a></li>
						<li><a href="Login.jsp"><i class="fa fa-angle-double-right"></i>Accedi</a></li>
						
					</ul>
				</div>
			</div>
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-5">
					<ul class="list-unstyled list-inline social text-center">
						<li class="list-inline-item"><a href="javascript:void();"><i class="fa fa-facebook"></i></a></li>
						<li class="list-inline-item"><a href="javascript:void();"><i class="fa fa-twitter"></i></a></li>
						<li class="list-inline-item"><a href="javascript:void();"><i class="fa fa-instagram"></i></a></li>
						<li class="list-inline-item"><a href="javascript:void();"><i class="fa fa-google-plus"></i></a></li>
						<li class="list-inline-item"><a href="javascript:void();" target="_blank"><i class="fa fa-envelope"></i></a></li>
					</ul>
				</div>
				</hr>
			</div>	
			<div class="row">
				<div class="col-xs-12 col-sm-12 col-md-12 mt-2 mt-sm-2 text-center text-white">
					<p><u><a href="Index.jsp">eSport Inc.</a></u> � un marchio registrato presso l'Universit� degli Studi di Salerno</p>
					<p class="h6">&copy All right Reversed.<a class="text-green ml-2" href="Index.jsp" target="_blank">eSport Inc.</a></p>
				</div>
				</hr>
			</div>	
		</div>
	</section>
	<!-- ./Footer -->
