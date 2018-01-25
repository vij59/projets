package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.beans.Voie;
import org.beans.Secteur;
import org.beans.Site;

public class VoieDaoImpl implements VoieDao {
	private DaoFactory daoFactory;
	private VoieDao voieDao;

	VoieDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterVoie(Voie voie, Secteur secteur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO voie(nom, cotation, id_secteur) VALUES(?, ?, ?);");
			preparedStatement.setString(1, voie.getNom().toUpperCase());
			preparedStatement.setString(2, voie.getCotation().toUpperCase());
			preparedStatement.setInt(3, secteur.getId());

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

	public List<Voie> lister() throws DaoException {
		List<Voie> voies = new ArrayList<Voie>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id_voie , nom, cotation, id_secteur FROM voie;");

			while (resultat.next()) {
				int idVoie = resultat.getInt("id_voie");
				String nomVoie = resultat.getString("nom");
				String cotation = resultat.getString("cotation");
				int idSecteur = resultat.getInt("id_secteur");

				Voie voie = new Voie();
				voie.setId(idVoie);
				voie.setNom(nomVoie);
				voie.setCotation(cotation);
				voie.setIdSecteur(idSecteur);

				voies.add(voie);
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
		return voies;
	}

	public int recupererIdVoie(Voie voie, Secteur secteur) throws DaoException {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int idVoie = 0;
		int idSecteur = secteur.getId();

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id_voie,id_secteur FROM voie;");

			while (resultat.next()) {

				if (idSecteur == resultat.getInt("id_secteur")) {
					idVoie = resultat.getInt("id_voie");
					idSecteur = resultat.getInt("id_secteur");

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
		return idVoie;
	}

}