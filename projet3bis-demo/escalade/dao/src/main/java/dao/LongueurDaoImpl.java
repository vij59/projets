package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.beans.Longueur;
import org.beans.Secteur;
import org.beans.Voie;

public class LongueurDaoImpl implements LongueurDao {
	private DaoFactory daoFactory;
	private LongueurDao longueurDao;

	LongueurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterLongueur(Longueur longueur, Voie voie) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO longueur(nom, cotation, id_voie) VALUES(?, ?, ?);");
			preparedStatement.setString(1, longueur.getNom().toUpperCase());
			preparedStatement.setString(2, longueur.getCotation().toUpperCase());
			preparedStatement.setInt(3, voie.getId());

			preparedStatement.executeUpdate();
			connexion.commit();
		} catch (SQLException e) {
			try {
				if (connexion != null) {
					connexion.rollback();
				}
			} catch (SQLException e2) {
			}
			throw new DaoException("Impossible de communiquer avec la base de donn�es");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de donn�es");
			}
		}

	}

	public List<Longueur> lister() throws DaoException {
		List<Longueur> longueurs = new ArrayList<Longueur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id_longueur , nom, cotation, id_voie FROM longueur;");

			while (resultat.next()) {
				int idLongueur = resultat.getInt("id_longueur");
				String nomLongueur = resultat.getString("nom");
				String cotation = resultat.getString("cotation");
				int idVoie = resultat.getInt("id_voie");

				Longueur longueur = new Longueur();
				longueur.setId(idLongueur);
				longueur.setNom(nomLongueur);
				longueur.setCotation(cotation);
				longueur.setIdVoie(idVoie);

				longueurs.add(longueur);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de donn�es");
		} catch (Exception e) {
			throw new DaoException("Les donn�es de la base sont invalides");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de donn�es");
			}
		}
		return longueurs;
	}

	public int recupererIdLongueur(Longueur longueur, Voie voie) throws DaoException {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int idLongueur = 0;
		int idVoie = voie.getId();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id_longueur,id_voie FROM longueur;");

			while (resultat.next()) {

				if (idVoie == resultat.getInt("id_voie")) {
					idLongueur = resultat.getInt("id_longueur");
					idVoie = resultat.getInt("id_voie");

				}

			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de donn�es");
		} catch (Exception e) {
			throw new DaoException("Les donn�es de la base sont invalides");
		} finally {
			try {
				if (connexion != null) {
					connexion.close();
				}
			} catch (SQLException e) {
				throw new DaoException("Impossible de communiquer avec la base de donn�es");
			}
		}
		return idLongueur;
	}

}