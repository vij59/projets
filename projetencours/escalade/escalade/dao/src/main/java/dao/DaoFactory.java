package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
	private String url;
	private String username;
	private String password;

	DaoFactory(String url, String username, String password) {
		this.url = url;
		this.username = username;
		this.password = password;
	}

	public static DaoFactory getInstance() {
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {

		}

		DaoFactory instance = new DaoFactory("jdbc:postgresql://localhost:5432/java_ee", "postgres", "");
		return instance;
	}

	public Connection getConnection() throws SQLException {
		Connection connexion = DriverManager.getConnection(url, username, password);
		connexion.setAutoCommit(false);
		return connexion;
	}

	
	public UtilisateurDao getUtilisateurDao() {
		return new UtilisateurDaoImpl(this);
	}

	public SiteDao getSiteDao() {
		return new SiteDaoImpl(this);
	}

	public TopoDao getTopoDao() {
		return new TopoDaoImpl(this);
	}

	public SecteurDaoImpl getSecteurDao() {
		return new SecteurDaoImpl(this);
	}

	public VoieDaoImpl getVoieDao() {
		return new VoieDaoImpl(this);
	}

	public LongueurDaoImpl getLongueurDao() {
		return new LongueurDaoImpl(this);
	}

	public ReservationDaoImpl getReservationDao() {
		return new ReservationDaoImpl(this);
	}

	public CommentaireDaoImpl getCommentaireDao() {
		return new CommentaireDaoImpl(this);
	}

	public CommentaireSiteDaoImpl getCommentaireSiteDao() {
		return new CommentaireSiteDaoImpl(this);
	}
}