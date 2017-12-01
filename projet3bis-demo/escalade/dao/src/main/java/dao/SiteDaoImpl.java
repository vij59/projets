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

public class SiteDaoImpl  implements SiteDao {
    private DaoFactory daoFactory;
	private SiteDao siteDao;

	SiteDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterSite (Site site) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO site(nom_site, pays, region, code_postal) VALUES(?, ?, ?, ?);");
            preparedStatement.setString(1, site.getNomSite());
            preparedStatement.setString(2, site.getPays());
            preparedStatement.setString(3, site.getRegion());
            preparedStatement.setInt(4, site.getCodePostal());

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
        }
        finally {
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
            resultat = statement.executeQuery("SELECT nom_site, pays, region, code_postal FROM site;");

            while (resultat.next()) {
                String nomSite = resultat.getString("nom_site");
                String pays = resultat.getString("pays");
                
                String region = resultat.getString("region");
                int codePostal = resultat.getInt("code_postal");

                Site site = new Site();
                site.setNomSite(nomSite);
                site.setPays(pays);
                site.setRegion(region);
                site.setCodePostal(codePostal);

                sites.add(site);
            }
        } catch (SQLException e) {
            throw new DaoException("Impossible de communiquer avec la base de donn�es");
        } catch (Exception e) {
            throw new DaoException("Les donn�es de la base sont invalides");
        }
        finally {
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

}