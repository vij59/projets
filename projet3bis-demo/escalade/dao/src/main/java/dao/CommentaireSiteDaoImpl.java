package dao;

import java.security.Timestamp;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.beans.Commentaire;
import org.beans.Longueur;
import org.beans.Reservation;
import org.beans.Voie;

public class CommentaireSiteDaoImpl implements CommentaireDao {
	private DaoFactory daoFactory;

	CommentaireSiteDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterCommentaireSite(Commentaire commentaire) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO commentaire_site(id_site, id_utilisateur, commentaire, date) VALUES(?, ?, ?, ?);");
			preparedStatement.setInt(1, commentaire.getIdSite());
			preparedStatement.setInt(2, commentaire.getIdUtilisateur());
			preparedStatement.setString(3, commentaire.getCommentaire());
			LocalDateTime localDate = LocalDateTime.now();

			preparedStatement.setObject(4, localDate);

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

	public List<Commentaire> lister() throws DaoException {
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement
					.executeQuery("SELECT id, id_site, id_utilisateur, commentaire , date FROM commentaire_site;");

			while (resultat.next()) {
				int idCommentaire = resultat.getInt("id");
				int idSite = resultat.getInt("id_site");
				int idUtilisateur = resultat.getInt("id_utilisateur");
				String comment = resultat.getString("commentaire");
				Date date = resultat.getDate("date");
				String timestamp = resultat.getString("date");

				Commentaire commentaire = new Commentaire();

				commentaire.setId(idCommentaire);
				commentaire.setIdSite(idSite);
				commentaire.setIdUtilisateur(idUtilisateur);
				commentaire.setCommentaire(comment);
				commentaire.setDateRetour(timestamp);
				commentaire.setDate(date);

				commentaires.add(commentaire);
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
		return commentaires;
	}

	public List<Commentaire> listerById(int idSite2) throws DaoException {
		List<Commentaire> commentaires = new ArrayList<Commentaire>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(
					"SELECT id_site, id_utilisateur, commentaire , date FROM commentaire_site WHERE id_site = "
							+ idSite2 + ";");

			while (resultat.next()) {

				int idSite = resultat.getInt("id_site");
				int idUtilisateur = resultat.getInt("id_utilisateur");
				String comment = resultat.getString("commentaire");
				Date date = resultat.getDate("date");
				String timestamp = resultat.getString("date");

				Commentaire commentaire = new Commentaire();

				commentaire.setIdSite(idSite);
				commentaire.setIdUtilisateur(idUtilisateur);
				commentaire.setCommentaire(comment);
				commentaire.setDateRetour(timestamp);
				commentaire.setDate(date);

				commentaires.add(commentaire);
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
		return commentaires;
	}

	public void ajouterCommentaire(Commentaire commentaire) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void supprimerCommentaireSite(int idCommentaire) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("DELETE FROM commentaire_site WHERE id=" + idCommentaire + ";");

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

	public void supprimerCommentaireTopo(int idCommentaire) throws DaoException {
		// TODO Auto-generated method stub

	}

}
