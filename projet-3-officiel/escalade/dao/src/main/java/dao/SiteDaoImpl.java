package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.beans.BeanException;
import org.beans.Site;

public class SiteDaoImpl implements SiteDao {
	private DaoFactory daoFactory;
	private SiteDao siteDao;

	SiteDaoImpl(DaoFactory daoFactory) {
		this.daoFactory = daoFactory;
	}

	public void ajouterSite(Site site) throws DaoException {
		Connection connexion = null;
		PreparedStatement preparedStatement = null;

		try {
			connexion = daoFactory.getConnection();
			preparedStatement = connexion.prepareStatement("INSERT INTO site(nom, pays, region) VALUES(?, ?, ?);");
			preparedStatement.setString(1, site.getNomSite().toUpperCase());
			preparedStatement.setString(2, site.getPays().toUpperCase());
			preparedStatement.setString(3, site.getRegion().toUpperCase());

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

	public List<Site> lister() throws DaoException {
		List<Site> sites = new ArrayList<Site>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id_site,nom, pays, region FROM site ORDER BY pays;");

			while (resultat.next()) {
				int idSite = resultat.getInt("id_site");
				String nomSite = resultat.getString("nom");
				String pays = resultat.getString("pays");

				String region = resultat.getString("region");

				Site site = new Site();
				site.setId(idSite);
				site.setNomSite(nomSite);
				site.setPays(pays);
				site.setRegion(region);

				sites.add(site);
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
		return sites;
	}

	public List<Site> listerDistinct() throws DaoException {
		List<Site> sites = new ArrayList<Site>();
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT distinct   region, pays, nom FROM site ;");

			while (resultat.next()) {

				String nomSite = resultat.getString("nom");
				String pays = resultat.getString("pays");

				String region = resultat.getString("region");

				Site site = new Site();

				site.setNomSite(nomSite);
				site.setPays(pays);
				site.setRegion(region);

				sites.add(site);
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
		return sites;
	}

	public int recupererIdSite(Site site) throws DaoException {
		Connection connexion = null;
		Statement statement = null;
		ResultSet resultat = null;
		int idSite = 0;

		try {
			connexion = daoFactory.getConnection();
			statement = connexion.createStatement();
			resultat = statement.executeQuery("SELECT id_site,nom, pays, region FROM site;");

			while (resultat.next()) {
				idSite = resultat.getInt("id_site");
				String nomSite = resultat.getString("nom");
				String pays = resultat.getString("pays");

				String region = resultat.getString("region");

				if ((site.getNomSite().equals(nomSite)) && (site.getRegion().equals(region))
						&& (site.getPays().equals(pays))) {
					Site site2 = new Site();
					site2.setId(idSite);
					site2.setNomSite(nomSite);
					site2.setPays(pays);
					site2.setRegion(region);

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
		return idSite;
	}

}