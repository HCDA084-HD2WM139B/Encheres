package org.eni.encheres.dal.categories;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eni.encheres.bo.Categorie;
import org.eni.encheres.dal.exceptions.ConnectionException;
import org.eni.encheres.dal.exceptions.DALException;
import org.eni.encheres.dal.exceptions.RequeteSQLException;
import org.eni.encheres.dal.jdbc.ConnectionProvider;

public class CategorieDAOimpl implements CategorieDao {

	private static final String SQL_SELECT_BY_ID = "SELECT no_categorie,libelle FROM categories WHERE id=?";
	private static final String COL_LIBELLE = "libelle";
	private static final String COL_NO_CATEGORIE = "no_categorie";
	
	private static final String SQL_SELECT_CATEGORIES = "SELECT no_categorie,libelle FROM categories";

	@Override
	public Map<Integer,Categorie> findAll() throws DALException {

		Map<Integer,Categorie> listeCategories = new HashMap<>();

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Traitement de la requete SQL
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_CATEGORIES);

				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				while (rs.next()) {
					int numCategorie = rs.getInt(COL_NO_CATEGORIE);
					String lib = rs.getString(COL_LIBELLE);

					Categorie categorie = new Categorie(lib);
					categorie.setNoCategorie(numCategorie);

					listeCategories.put(numCategorie,categorie);
				}
				
				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors de la selection en base", sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

		return listeCategories;
	}

	@Override
	public Categorie find(int categorieId) throws DALException {
		Categorie categorie = null;

		// Connection en base
		try (Connection cnx = ConnectionProvider.getConnection()) {

			// Traitement de la requete SQL
			try {
				PreparedStatement pstmt = cnx.prepareStatement(SQL_SELECT_BY_ID);
				pstmt.setInt(1, categorieId);
				
				// Execution de la requete
				ResultSet rs = pstmt.executeQuery();

				if (rs.next()) {
					int noCategorie = rs.getInt(COL_NO_CATEGORIE);
					String libelle = rs.getString(COL_LIBELLE);

					categorie = new Categorie(libelle);
					categorie.setNoCategorie(noCategorie);
				}
				
				rs.close();
				pstmt.close();

			} catch (SQLException sqle) {
				throw new RequeteSQLException("Erreur lors de la selection en base", sqle);
			}
		} catch (SQLException sqle) {
			sqle.printStackTrace();
			throw new ConnectionException("Problème de connection", sqle);
		}

		return categorie;
	}

}
