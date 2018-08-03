<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
	import="java.sql.ResultSet" 
 	import="javax.servlet.http.HttpSession"
 	import="java.sql.ResultSetMetaData"
 	import="java.util.StringJoiner"
 	import="java.util.ArrayList"
	import="java.util.List"
	import="com.everis.wizardadmin.model.Query"
%>
<!DOCTYPE html>
<html lang="pt-br">
  <head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Wizard Admin</title>
    <link rel="icon" href="global/imagens/logovivo.ico">

    <!-- Bootstrap -->
    <link href="global/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="global/css/index.css" rel="stylesheet">

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
      <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
  </head>
 <%
 	Query returnQuery = (Query) session.getAttribute("returnQuery");
 %>
  <body>
    <nav class="navbar navbar-fixed-top navbar-inverse navbar-transparente" <%if(returnQuery != null){ if(returnQuery.isRetorno() == true || returnQuery.isQueryRetorno() == true){%>style="border-bottom: 10px solid #6dec96;"<%}else{%>style="border-bottom: 10px solid #f76262;" <% } } %>>
      <div class="container" >
        
        <!-- header -->
        <div class="navbar-header">
          <!-- botao toggle -->
          <button type="button" class="navbar-toggle collapsed"
                  data-toggle="collapse" data-target="#barra-navegacao">
            <span class="sr-only">alternar navegação</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>

          <a href="index.jsp" class="navbar-brand, a-img">
            <span class="img-logo"></span>
          </a>
        </div>

        <!-- navbar -->
        <div class="collapse navbar-collapse" id="barra-navegacao">
          <ul class="nav navbar-nav navbar-right">
            <li><a href="">Menu1</a></li>
            <li><a href="">Menu2</a></li>
            <li><a href="">Menu3</a></li>
            <li class="divisor" role="separator"></li>
            <li><a href="">Menu4</a></li>
            <li><a href="">Entrar</a></li>
          </ul>
        </div>
      </div><!-- /container -->
    </nav><!-- /nav -->
    
    <div class="container" style="height: 100%;">
        <div class="row form-row">
            <div class="col-md-12">
				<form class="form-query" action="QueryController" method="POST">
					  <div class="form-group">
					    <label for="textAreaQuery" style="color: #660099;font-size: 25px;">Wizard Admin</label>
					    <textarea class="form-control" id="textAreaQuery" name="textAreaQuery" rows="7" required="required"><%if(returnQuery != null){out.print(returnQuery.getSql());} %></textarea>
					  </div>
					  <div class="form-group btn-query">
					  		<button type="submit" class="btn btn-primary" style="background-color: #660099;font-weight: bold;">Executar</button> 
					  </div>
					  				
					  <input type="hidden" name="paginaInicio" value="1" />
 					  <input type="hidden" name="paginaFim" value="100" />
				</form>
	    	</div>
		</div>
<%
		if(returnQuery != null){ 
		
			if(returnQuery.isRetorno() == true){
			
				List<List> objectList = (ArrayList) returnQuery.getObjectList();
				int numeroDeColunas = returnQuery.getNumeroDeColunas();
				List<String> listColumHeaderName = (ArrayList) returnQuery.getListColumHeaderName();
 %>		
 		<div class="row row-page">
 			<div class="col-md-12" >
 				<h5 class="h5-page">Registros <%out.print(returnQuery.getPagInicio()); %> a <%out.print(returnQuery.getPagFim()); %> </h5>
			</div>
		</div>
		<div class="row form-row-table">
			
            <div class="col-md-12 col-table">
				<table class="table" >
				  <thead>
				    <tr>
				      <%for(int i = 0; i < numeroDeColunas - 1; i++){%>
				      	<th scope="col" style="color:white;background-color:#660099; "><%out.print(listColumHeaderName.get(i)); %></th>
				      <% } %>
				    </tr>
				  </thead>
				  <tbody>
				  	<%for(int i = 0; i < objectList.size(); i++){ %>
				  		<tr>
				  			<%for(int j = 0; j < objectList.get(i).size() - 1;j++){ %>
				  				<th scope="row"><%out.print(objectList.get(i).get(j));%></th>
			   				<% } %>
				  		</tr>
				  	<% } %>
				  </tbody>
				</table>
			</div>
		</div>
		<div class="row row-proximo" style="mar">
 			<div class="col-md-12" >
 				<form action="QueryController" method="POST">
 					<input type="hidden" name="textAreaQuery" value="<%out.print(returnQuery.getSql());%>" />
 					<input type="hidden" name="paginaInicio" value="<%out.print(returnQuery.getPagInicio());%>" />
 					<input type="hidden" name="paginaFim" value="<%out.print(returnQuery.getPagFim());%>" />
 					
 					 <button type="input" name="pagina" value="anterior" class="btn" style="background-color: #660099;color: white;"> << Anterior </button>
 					<button type="input" name="pagina" value="proximo" class="btn" style="background-color: #660099;color: white;"> Próximo >> </button>
 				</form>
			</div>
		</div>
<% 
			}else{
				%>
				<div class="row" >
					<div class="col-md-12 col-alert-error">
						<%if(returnQuery.isQueryRetorno()){ %>
						<div class="alert alert-success" role="alert">
					          <button type="button" class="close" data-dismiss="alert" aria-label="Fechar">
					              <span aria-hidden="true">&times;</span>
					          </button>
					          <strong>Alerta:</strong> <%out.print(returnQuery.getMensagem());%> 
		      			</div>
						<%} else{%>
						<div class="alert alert-danger" role="alert" >
					          <button type="button" class="close" data-dismiss="alert" aria-label="Fechar">
					              <span aria-hidden="true">&times;</span>
					          </button>
					          <strong>Alerta:</strong> <%out.print(returnQuery.getMensagem());%> 
		      			</div>
		      			<%} %>
      				</div>
      			</div>
				<%
			}
		}
%>
	</div>
	
<%session.removeAttribute("returnQuery");%>

    <!-- Rodape -->


    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>

    <!-- Include all compiled plugins (below), or include individual files as needed -->
    <script src="global/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>