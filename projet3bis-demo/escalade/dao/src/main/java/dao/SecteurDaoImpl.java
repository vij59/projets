package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.beans.Secteur;
import org.beans.Site;

public class SecteurDaoImpl  implements SecteurDao {
    private DaoFactory daoFactory;
	private SecteurDao secteurDao;

	SecteurDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterSecteur (Secteur secteur, Site site) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO secteur(nom, cotation, id_site) VALUES(?, ?, ?);");
            preparedStatement.setString(1, secteur.getNom());
            preparedStatement.setString(2, secteur.getCotation());
            preparedStatement.setInt(3, site.getId());
            
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

    public List<Secteur> lister() throws DaoException {
        List<Secteur> secteurs = new ArrayList<Secteur>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id_secteur , nom, cotation, id_site FROM secteur;");

            while (resultat.next()) {
            	int idSecteur = resultat.getInt("id_secteur");
                String nomSecteur = resultat.getString("nom");
                String cotation = resultat.getString("cotation");
                int idSite = resultat.getInt("id_site");

                Secteur secteur = new Secteur();
                secteur.setId(idSecteur);
                secteur.setNom(nomSecteur);
                secteur.setCotation(cotation);
                secteur.setIdSite(idSite);

                secteurs.add(secteur);
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
        return secteurs;
    }


    
}