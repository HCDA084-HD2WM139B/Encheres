<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
   
<link href="css/styleConnexion.css" rel="stylesheet"/>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Connexion</title>

<c:set var="ColorIdentifiant" value="black" scope="page" />
<c:if test="${requestScope.erreurIdentifiant == true }">
	<c:set var="ColorIdentifiant" value="red" scope="page" />
	<script type="text/javascript">
		alert('Identifiant non existant');
	</script>
</c:if>

</head>
<body>

	<%@include file="EnTeteEni.jspf"%>
	
	<section>
		<form method="post" action="<c:url value="/ServletConnexion"/>">
			<div id="identifiants" class="pave">
				<div class="intitule">
					<label for="identifiant" class="element">Identifiant : </label>
					<label for="motdepasse" class="element">Mot de passe : </label>
				</div>
				<div class="champ">
					<input type="text" name="identifiant" class="element" id="identifiant" required value="${param.identifiant }">
					<input type="password" name="motdepasse" class="element" id="motdepasse" required value="${param.motdepasse }">
				</div>
			</div>
			
			<div id="connexion" class="pave">
				<button type="submit" id="btn_connexion">Connexion</button>
				<div id="choix">
					<span><input id="case" type="checkbox" name="memoriser" value="Se souvenir de moi"><label for="memoriser">Se souvenir de moi</label></span>
					<a href="">Mot de passe oublié</a>
				</div>
			</div>
		</form>
		
		<a href="<c:url value="/ServletCompte?creation=true&modification=true"/>" id="lien_creer_compte" class="pave"><button name="creer" id="creer_compte">Creer un compte</button></a>
	</section>
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</div>
</body>
</html>