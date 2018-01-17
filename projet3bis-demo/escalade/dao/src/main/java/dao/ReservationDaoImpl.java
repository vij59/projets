package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.beans.Reservation;

public class ReservationDaoImpl implements ReservationDao {
	private DaoFactory daoFactory;

	ReservationDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterReservation(Reservation reservation) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement(
					"INSERT INTO reservation(id_topo, id_utilisateur, date_emprunt, date_retour) VALUES(?, ?, ?,?);");
			preparedStatement.setInt(1, reservation.getIdTopo());
			preparedStatement.setInt(2, reservation.getIdUtilisateur());

			LocalDate localDate = LocalDate.now();
			preparedStatement.setObject(3, localDate);
			preparedStatement.setObject(4, localDate.plusDays(30));

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

	public List<Reservation> lister() throws DaoException {
		List<Reservation> reservations = new ArrayList<Reservation>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery(
					"SELECT id_reservation , id_topo, id_utilisateur, date_emprunt, date_retour FROM reservation ORDER BY date_retour DESC;");

			while (resultat.next()) {
				int idReservation = resultat.getInt("id_reservation");
				int idTopo = resultat.getInt("id_topo");
				int idUtilisateur = resultat.getInt("id_utilisateur");
				Date dateEmprunt = resultat.getDate("date_emprunt");
				Date dateRetour = resultat.getDate("date_retour");

				Reservation reservation = new Reservation();
				reservation.setIdReservation(idReservation);
				reservation.setIdTopo(idTopo);
				reservation.setIdUtilisateur(idUtilisateur);
				reservation.setDateEmprunt(dateEmprunt);
				reservation.setDateRetour(dateRetour);

				reservations.add(reservation);
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
		return reservations;
	}

	public void terminerReservation(int idTopo) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("Update reservation set date_retour = ? where id_topo = ?;");

			LocalDate localDate = LocalDate.now();
			preparedStatement.setObject(1, localDate);
			preparedStatement.setInt(2, idTopo);

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