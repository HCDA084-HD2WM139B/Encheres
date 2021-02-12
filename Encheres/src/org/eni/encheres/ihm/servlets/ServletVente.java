package org.eni.encheres.ihm.servlets;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eni.encheres.bll.ArticleManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.ArticleVendu;
import org.eni.encheres.bo.Categorie;
import org.eni.encheres.bo.Utilisateur;
import org.eni.encheres.dal.exceptions.DALException;

/**
 * Servlet implementation class ServletVente
 */
@WebServlet("/ServletVente")
public class ServletVente extends HttpServlet {
	private static final String PARAM_VILLE = "ville";
	private static final String PARAM_CODEPOSTAL = "codepostal";
	private static final String PARAM_RUE = "rue";
	private static final String PARAM_DATEFIN = "datefin";
	private static final String PARAM_DATEDEBUT = "datedebut";
	private static final String PARAM_PRIXDEPART = "prixdepart";
	private static final String PARAM_CATEGORIE = "categorie";
	private static final String PARAM_DESCRIPTION = "description";
	private static final String PARAM_ARTICLE = "article";
	private static final long serialVersionUID = 1L;
	private static final String FORMAT_DATE = "yyyy-MM-dd";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Utilisateur vendeur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		// Si l'utilisateur est autorisé (compte actif ou non)
		if (vendeur.isActif()) {
			// Entrez l'adresse du vendeur par défaut
			// Récupérer l'adresse
			String rue = vendeur.getRue();
			String codePostal = vendeur.getCodePostal();
			String ville = vendeur.getVille();
			// Mettre ces variables en attribut dans request
			request.setAttribute("rue", rue);
			request.setAttribute("cp", codePostal);
			request.setAttribute("ville", ville);

			getServletContext().getRequestDispatcher("/vente").forward(request, response);
		}
		// TODO cas si vendeur inactif
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		Utilisateur vendeur = (Utilisateur) request.getSession().getAttribute("utilisateur");
		// récupérer les paramètres
		String article = request.getParameter(PARAM_ARTICLE);
		String description = request.getParameter(PARAM_DESCRIPTION);
		Categorie categorieArticle = null;

		List<Categorie> listeCategorie = (List<Categorie>) getServletContext().getAttribute("Categories");
		for (Categorie categorieEnCours : listeCategorie) {
			if (request.getParameter(PARAM_CATEGORIE).equals(categorieEnCours.getLibelle())) {
				categorieArticle = categorieEnCours;
				break;
			}
		}

		Integer prixdepart = Integer.parseInt(request.getParameter(PARAM_PRIXDEPART));
		LocalDateTime dateDebut = LocalDateTime.parse(request.getParameter(PARAM_DATEDEBUT),
				DateTimeFormatter.ofPattern(FORMAT_DATE));
		LocalDateTime dateFin = LocalDateTime.parse(request.getParameter(PARAM_DATEFIN),
				DateTimeFormatter.ofPattern(FORMAT_DATE));

		// Besoin si différent de l'adresse du vendeur :
		String rue = request.getParameter(PARAM_RUE);
		String codePostal = request.getParameter(PARAM_CODEPOSTAL);
		String ville = request.getParameter(PARAM_VILLE);

		ArticleManager manager = ArticleManager.getInstance();
		try {
			manager.ajouterArticle(article, description, dateDebut, dateFin, vendeur, categorieArticle);
		} catch (BLLException e) {
			e.printStackTrace();
		}

		// si tout s'est bien passé :
		Boolean venteCree = true;
		request.setAttribute("booleen", venteCree);
		getServletContext().getRequestDispatcher("/ServletAccueil").forward(request, response);
	}

}
