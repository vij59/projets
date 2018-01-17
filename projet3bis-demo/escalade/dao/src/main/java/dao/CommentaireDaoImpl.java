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

public class CommentaireDaoImpl implements CommentaireDao {
	private DaoFactory daoFactory;

	CommentaireDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterCommentaire(Commentaire commentaire) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO commentaire(id_topo, id_utilisateur, commentaire, date) VALUES(?, ?, ?, ?);");
			preparedStatement.setInt(1, commentaire.getIdTopo());
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
					.executeQuery("SELECT id , id_topo, id_utilisateur, commentaire , date FROM commentaire;");

			while (resultat.next()) {

				int id = resultat.getInt("id");
				int idTopo = resultat.getInt("id_topo");
				int idUtilisateur = resultat.getInt("id_utilisateur");
				String comment = resultat.getString("commentaire");
				Date date = resultat.getDate("date");
				String timestamp = resultat.getString("date");

				Commentaire commentaire = new Commentaire();

				commentaire.setId(id);
				commentaire.setIdTopo(idTopo);
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

	public void ajouterCommentaireSite(Commentaire commentaire) throws DaoException {
		// TODO Auto-generated method stub

	}

	public List<Commentaire> listerById(int idSite2) throws DaoException {
		// TODO Auto-generated method stub
		return null;
	}

	public void supprimerCommentaireSite(int idCommentaire) throws DaoException {
		// TODO Auto-generated method stub

	}

	public void supprimerCommentaireTopo(int idCommentaire) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("DELETE FROM commentaire WHERE id=" + idCommentaire + ";");

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

}
