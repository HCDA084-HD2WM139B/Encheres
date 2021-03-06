package org.eni.encheres.ihm.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.eni.encheres.bll.UtilisateurManager;
import org.eni.encheres.bll.exceptions.BLLException;
import org.eni.encheres.bo.Utilisateur;

/**
 * Servlet implementation class ServletConnexion
 */
@WebServlet("/ServletConnexion")
public class ServletConnexion extends HttpServlet {
	private static final String JSP_ACCUEIL = "/accueil";
	private static final String SERVLET_ACCUEIL = "/ServletAccueil";
	private static final String ATTR_UTILISATEUR = "utilisateur";
	private static final String JSP_CONNEXION = "/connexion";
	private static final String ERREUR_IDENTIFIANT = "erreurIdentifiant";
	private static final String PARAM_MOTDEPASSE = "motdepasse";
	private static final String PARAM_IDENTIFIANT = "identifiant";
	private static final String APP_ENCODAGE = "UTF-8";
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Si "Se souvenir de moi" avait été coché, il faut renseigner les 2
		// champs:
		// if (request.getParameter("memoriser") cohé avant) {
		// // Récupérer l'identifiant de l'utilisateur...
		// // ... et l'insérer dans le champs
		// // Récupérer le mot de passe de l'utilisateur
		// // ... et l'insérer dans le champs
		// }
		request.setCharacterEncoding(APP_ENCODAGE);
		HttpSession session = request.getSession();
		if (session.getAttribute(ATTR_UTILISATEUR) == null) {
			getServletContext().getRequestDispatcher(JSP_CONNEXION).forward(request, response);
		} else {
			session.setAttribute(ATTR_UTILISATEUR, null);
			getServletContext().getRequestDispatcher(JSP_ACCUEIL).forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding(APP_ENCODAGE);
	
			String identifiants = request.getParameter(PARAM_IDENTIFIANT);
			String motDePasse = request.getParameter(PARAM_MOTDEPASSE);
	
			// Vérifier identifiant et mots de passe
			UtilisateurManager manager = UtilisateurManager.getInstance();
			Utilisateur utilisateur = null;
			try {
				utilisateur = manager.rechercherUtilisateur(identifiants, motDePasse);
			} catch (BLLException blle) {
				blle.printStackTrace();
				// Si les identifiant/mot de passe pas existants :
				request.setAttribute(ERREUR_IDENTIFIANT, true);
				getServletContext().getRequestDispatcher(JSP_CONNEXION).forward(request, response);
			}
			
			HttpSession session = request.getSession();
			session.setAttribute(ATTR_UTILISATEUR, utilisateur);
	
			// Si les identifiant/mot de passe ok et si attribut 'supprime"=false :
			getServletContext().getRequestDispatcher(SERVLET_ACCUEIL).forward(request, response);
			
	}

}
