/**
 * 
 */
package org.eni.encheres.bo;

import java.time.LocalDate;

/**
 * @author ElCaTar
 *
 */
public class ArticleVendu {

	private Integer noArticle;
	private String nomArticle;
	private String description;
	private LocalDate dateDebutEncheres;
	private LocalDate dateFinEncheres;
	private Integer prixInitial;
	private Integer prixVente;
	private Utilisateur vendeur;
	private Utilisateur acheteur;
	private Categorie categorie;
	
	
	/**
	 *Construteur
	 */
	public ArticleVendu() {
	}
	
	
	/**
	 *Construteur
	 * @param nomArticle
	 * @param description
	 * @param dateDebutEncheres
	 * @param dateFinEncheres
	 * @param vendeur
	 * @param categorie
	 */
	public ArticleVendu(String nomArticle, String description, LocalDate dateDebutEncheres, LocalDate dateFinEncheres,
			Utilisateur vendeur, Categorie categorie) {
		this();
		setNomArticle(nomArticle);
		setDescription(description);
		setDateDebutEncheres(dateDebutEncheres);
		setDateFinEncheres(dateFinEncheres);
		setVendeur(vendeur);
		setCategorie(categorie);
	}


	/**
	 * Méthode permettant de récupérer noArticle
	 * @return le noArticle
	 */
	public Integer getNoArticle() {
		return noArticle;
	}
	/**
	 * Méthode permettant de modifier noArticle
	 * @param noArticle le new noArticle
	 */
	public void setNoArticle(Integer noArticle) {
		this.noArticle = noArticle;
	}
	/**
	 * Méthode permettant de récupérer nomArticle
	 * @return le nomArticle
	 */
	public String getNomArticle() {
		return nomArticle;
	}
	/**
	 * Méthode permettant de modifier nomArticle
	 * @param nomArticle le new nomArticle
	 */
	public void setNomArticle(String nomArticle) {
		this.nomArticle = nomArticle;
	}
	/**
	 * Méthode permettant de récupérer description
	 * @return le description
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * Méthode permettant de modifier description
	 * @param description le new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Méthode permettant de récupérer dateDebutEncheres
	 * @return le dateDebutEncheres
	 */
	public LocalDate getDateDebutEncheres() {
		return dateDebutEncheres;
	}
	/**
	 * Méthode permettant de modifier dateDebutEncheres
	 * @param dateDebutEncheres le new dateDebutEncheres
	 */
	public void setDateDebutEncheres(LocalDate dateDebutEncheres) {
		this.dateDebutEncheres = dateDebutEncheres;
	}
	/**
	 * Méthode permettant de récupérer dateFinEncheres
	 * @return le dateFinEncheres
	 */
	public LocalDate getDateFinEncheres() {
		return dateFinEncheres;
	}
	/**
	 * Méthode permettant de modifier dateFinEncheres
	 * @param dateFinEncheres le new dateFinEncheres
	 */
	public void setDateFinEncheres(LocalDate dateFinEncheres) {
		this.dateFinEncheres = dateFinEncheres;
	}
	/**
	 * Méthode permettant de récupérer prixInitial
	 * @return le prixInitial
	 */
	public Integer getPrixInitial() {
		return prixInitial;
	}
	/**
	 * Méthode permettant de modifier prixInitial
	 * @param prixInitial le new prixInitial
	 */
	public void setPrixInitial(Integer prixInitial) {
		this.prixInitial = prixInitial;
	}
	/**
	 * Méthode permettant de récupérer prixVnete
	 * @return le prixVnete
	 */
	public Integer getPrixVnete() {
		return prixVente;
	}
	/**
	 * Méthode permettant de modifier prixVnete
	 * @param prixVnete le new prixVnete
	 */
	public void setPrixVnete(Integer prixVnete) {
		this.prixVente = prixVnete;
	}
	/**
	 * Méthode permettant de récupérer vendeur
	 * @return le vendeur
	 */
	public Utilisateur getVendeur() {
		return vendeur;
	}
	/**
	 * Méthode permettant de modifier vendeur
	 * @param vendeur le new vendeur
	 */
	public void setVendeur(Utilisateur vendeur) {
		this.vendeur = vendeur;
	}
	/**
	 * Méthode permettant de récupérer acheteur
	 * @return le acheteur
	 */
	public Utilisateur getAcheteur() {
		return acheteur;
	}
	/**
	 * Méthode permettant de modifier acheteur
	 * @param acheteur le new acheteur
	 */
	public void setAcheteur(Utilisateur acheteur) {
		this.acheteur = acheteur;
	}
	/**
	 * Méthode permettant de récupérer categorie
	 * @return le categorie
	 */
	public Categorie getCategorie() {
		return categorie;
	}
	/**
	 * Méthode permettant de modifier categorie
	 * @param categorie le new categorie
	 */
	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}
	
	
	
}
