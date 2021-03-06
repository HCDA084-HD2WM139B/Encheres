<%@ page
	language="java"
	contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib
	prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib
	prefix="fmt"
	uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page import="java.time.format.DateTimeFormatter"%>
<!DOCTYPE html>
<html>
<head>
<meta
	name="viewport"
	content="width=device-width, initial-scale=1">
<link
	rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
<link
	href="css/styleAccueil.css"
	rel="stylesheet" />

<meta
	http-equiv="Content-Type"
	content="text/html; charset=UTF-8">
<title>Accueil</title>

</head>
<body>
	<div
		class="container"
		style="margin-top: 30px;">
		<!-- Section en tête du contenu -->
		<header>
			<!-- Section LOGO -->
			<%@include file="../jsp/EnTeteEni.jspf"%>
		</header>
		<!-- Section MENU -->
		<main>

		<nav>
			<ul>
				<c:choose>
					<c:when test="${!empty sessionScope.utilisateur}">

						<li><c:choose>
								<c:when test="${sessionScope.utilisateur.actif eq 'true' }">
									<a href="<c:url value="/ServletVente"/>">Vendre un article</a>
								</c:when>
								<c:otherwise>
									Vendre un article
								</c:otherwise>
							</c:choose></li>
						<li><a href="<c:url value="/ServletCompte"/>">Mon profil</a></li>
						<li><a href="<c:url value="/ServletConnexion"/>">Déconnexion</a></li>

					</c:when>
					<c:otherwise>
						<div>
							<li id="connexion"><a
								href="<c:url value="/ServletConnexion"/>">S'inscrire - Se connecter</a></li>
						</div>
					</c:otherwise>
				</c:choose>
			</ul>
		</nav>


		<div>
			<h1>Liste des enchères</h1>
		</div>

		<div>
			<h3>Filtres :</h3>
		</div>
		<form
			method="post"
			action="<c:url value="/ServletAccueil?recherche=true"/>">
			<div>
				<input
					name="rechercheTexte"
					type="search"
					placeholder="Le nom de l'article contient"
					class="col-md-6 col-lg-4"
					value="<c:if test="${!empty param.rechercheTexte}">${param.rechercheTexte}</c:if>" />
			</div>
			<br>
			<div>
				<label for="categorieFiltre">Categorie : </label> <select
					name="categorieFiltre"
					size="1"
					id="categorieFiltre">
					<c:forEach
						var="categorie"
						items="${mapCategories}">
						<option value="${categorie.key}" <c:if test="${!empty param.categorieFiltre && param.categorieFiltre == categorie.key }">selected</c:if> >${categorie.value.libelle}</option>
					</c:forEach>
				</select>
			</div>
			<br>


			<!-- Attribut en plus lors de la connection -->
			<c:if test="${!empty sessionScope.utilisateur}">
				<div id="Filtres">

					<div class="container">
						<div class="row">

							<div
								id="radio1"
								class="col-md-6 col-lg-4">

								<div>
									<input
										type="radio"
										name="typeFiltre"
										value="achat"
										onchange="selectType(false)"
										<c:if test="${param.typeFiltre == 'achat'}">checked="checked"</c:if>
										<c:if test="${ empty param.recherche}">checked="checked"</c:if>>
									<label>Achats</label>
								</div>
								<div id="venteid">
									<div>
										<input
											class="achat"
											id="chktest"
											type="checkbox"
											name="chkEncheresOuvertes"
											<c:if test="${param.chkEncheresOuvertes == 'on'}">checked="checked"</c:if>>
										<label for="chkEncheresOuvertes">enchères ouvertes</label>
									</div>
									<div>
										<input
											class="achat"
											type="checkbox"
											name="chkMesEnchereEnCours"
											<c:if test="${param.chkMesEnchereEnCours== 'on'}">checked="checked"</c:if>>
										<label>mes enchères en cours</label>
									</div>
									<div>
										<input
											class="achat"
											type="checkbox"
											name="chkMesEncheresRemportes"
											<c:if test="${param.chkMesEncheresRemportes == 'on'}">checked="checked"</c:if>>
										<label>mes enchères remportées</label>
									</div>
								</div>
							</div>

							<div
								id="radio2"
								class="col-md-6 col-lg-4">
								<div>
									<input
										type="radio"
										name="typeFiltre"
										value="vente"
										onchange="selectType(false)"
										<c:if test="${param.typeFiltre == 'vente'}">checked="checked"</c:if>>
									<label>Ventes</label>
								</div>
								<div>
									<div>
										<input
											class="vente"
											type="checkbox"
											name="chkVentesEnCours"
											<c:if test="${param.chkVentesEnCours == 'on'}">checked="checked"</c:if>>
										<label>mes ventes en cours</label>
									</div>
									<div>
										<input
											class="vente"
											type="checkbox"
											name="chkVentesNonDebutes"
											<c:if test="${param.chkVentesNonDebutes == 'on'}">checked="checked"</c:if>>
										<label>ventes non débutées</label>
									</div>
									<div>
										<input
											class="vente"
											type="checkbox"
											name="chkVentesTerminees"
											<c:if test="${param.chkVentesTerminees == 'on'}">checked="checked"</c:if>>
										<label>ventes terminées</label>
									</div>
								</div>
							</div>
			</c:if>
			<div
				id="submit"
				class="col-md-6 col-lg-4">
				<div>
					<button
						class="btn btn-primary btn-lg"
						type="submit"
						value="Rechercher" />
					Rechercher
					</button>

				</div>

			</div>
			
	</div>
	</div>
	</div>
	</div>

	</form>




	<!-- Liste des enchères  -->

	<section id="encheres">
		<div class="container">
		<hr>
			<div class="row">

				<c:forEach
					var="article"
					items="${mapArticlesAffiches}">

					<!--  <option value="${article.key}">${categorie.value.libelle}</option> -->
					<div
						id="block1"
						class="col-lg-6">
						<div class="articles">
							<div class="container">
								<div class="row">
									<div class="col-6">

										<img
											class="picture-left"
											alt="photo objet"
											src="images/ecommerce-navigation.png">
									</div>
									<div class="col-6">
										<div class="paragraph-right">
											<div>
												<c:choose>
													<c:when
														test="${(!empty sessionScope.utilisateur) and (sessionScope.utilisateur.actif eq 'true')}">
														<a
															href="<c:url value="/ServletEnchere?noArticle=${article.key}"/>"
															id="libelleArticle">${article.value.nomArticle}</a>
													</c:when>
													<c:otherwise>
														<span>${article.value.nomArticle}</span>
													</c:otherwise>
												</c:choose>
											</div>
											<div>
												Prix : <span class="nbPoints"> <c:choose>
														<c:when
															test="${article.value.enchereMax.montantEnchere > 0}">
									${article.value.enchereMax.montantEnchere}
								</c:when>
														<c:otherwise>
									${article.value.prixInitial}
								</c:otherwise>
													</c:choose>
												</span> points
											</div>
											<div>

												Fin de l'enchère : <span id="dateFinEnchere">${article.value.dateFinEncheres}</span>
											</div>
											<div>
												Vendeur : <span>${article.value.vendeur.pseudo}</span>
											</div>
										</div>

									</div>
								</div>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>

		</div>
	</section>

	<script src="<c:url value="/js/accueil.js"/>"></script>
	<!--<script src="<c:url value="/WEB-INF/Accueil/accueil.js"/>"></script> A revoir-->

	<!-- selection par défaut -->
	<c:if test="${empty param.recherche}">
		<script type="text/javascript">
			selectType(true, 'vente');
		</script>
	</c:if>
	<c:if test="${param.recherche}">
		<c:if test="${param.typeFiltre == 'achat'}">
			<script type="text/javascript">
				selectType(true, 'vente');
			</script>
		</c:if>
		<c:if test="${param.typeFiltre != 'achat'}">
			<script type="text/javascript">
				selectType(true, 'achat');
			</script>
		</c:if>
	</c:if>
	</div>
	</main>


	<footer>
		<div class="container">
			<hr>
			<!-- pied de page -->
			<div id="footer-author">
				<!-- auteur -->
				<a
					href="mailto:contact@eni-ecole.fr"
					title="Si vous désirez nous écrire">ENI ECOLE</a>
			</div>
			<div id="footer-copyright">
				<!-- copyright -->
				<a
					href="http://www.eni-ecole.fr"
					title="Eni ECOLE "
					target="_blank">Copyright&copy; ENI ECOLE Informatique </a>
			</div>
		</div>
	</footer>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>