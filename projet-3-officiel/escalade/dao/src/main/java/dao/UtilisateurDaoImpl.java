package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.beans.BeanException;
import org.beans.Utilisateur;
//import com.octest.beans.Utilisateur;

public class UtilisateurDaoImpl implements UtilisateurDao {
	private DaoFactory daoFactory;
	private UtilisateurDao utilisateurDao;

	UtilisateurDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterUtilisateur(Utilisateur utilisateur) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion
					.prepareStatement("INSERT INTO utilisateur(nom, prenom, mail, mdp) VALUES(?, ?, ?, ?);");
			preparedStatement.setString(1, utilisateur.getNom());
			preparedStatement.setString(2, utilisateur.getPrenom());
			preparedStatement.setString(3, utilisateur.getMail());
			String mdp = encrypt(utilisateur.getMdp());
			//System.out.println(mdp);
			preparedStatement.setString(4, mdp);

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

	public List<Utilisateur> lister() throws DaoException {
		List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id, nom, prenom, mail, mdp FROM utilisateur;");

			while (resultat.next()) {
				int id = resultat.getInt("id");
				String nom = resultat.getString("nom");
				String prenom = resultat.getString("prenom");

				String mail = resultat.getString("mail");
				String mdp = resultat.getString("mdp");
				mdp = decrypt(mdp);

				Utilisateur utilisateur = new Utilisateur();
				utilisateur.setId(id);
				utilisateur.setNom(nom);
				utilisateur.setPrenom(prenom);
				utilisateur.setMail(mail);
				utilisateur.setMdp(mdp);

				utilisateurs.add(utilisateur);
			}
		} catch (SQLException e) {
			throw new DaoException("Impossible de communiquer avec la base de donn�es");
		} catch (BeanException e) {
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
		return utilisateurs;
	}

	public boolean validerMdp(String mail, String mdp) throws DaoException {
		// true si connexion ok, false sinon
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		boolean res = false;
		int x = 0;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT  mdp FROM utilisateur where mail='" + mail + "';");

			while (resultat.next()) {

				String password = resultat.getString("mdp");
				if (mdp.equals(password)) {
					// System.out.println("ça concorde");
					// on accroit le x si le mdp du mail correspond
					x++;
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

		if (x > 0) {
			res = true;
		}

		return res;
	}

	public String encrypt(String password) {
		String crypte = "";
		for (int i = 0; i < password.length(); i++) {
			int c = password.charAt(i) ^ 48;
			crypte = crypte + (char) c;
		}
		return crypte;
	}

	public String decrypt(String password) {
		String aCrypter = "";
		for (int i = 0; i < password.length(); i++) {
			int c = password.charAt(i) ^ 48;
			aCrypter = aCrypter + (char) c;
		}
		return aCrypter;
	}

}