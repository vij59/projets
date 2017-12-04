package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.beans.BeanException;
import org.beans.Topo;
import org.beans.Topo;
//import com.octest.beans.Topo;

public class TopoDaoImpl implements TopoDao {
    private DaoFactory daoFactory;
	private TopoDao topoDao;

    TopoDaoImpl(DaoFactory daoFactory) {
        this.daoFactory = daoFactory;
    }

    public void ajouterTopo (Topo topo) throws DaoException {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("INSERT INTO topo(id_site, nom_topo, fichier) VALUES(?, ?, ?);");
            preparedStatement.setInt(1, topo.getIdSite());
            preparedStatement.setString(2, topo.getNomTopo());
            preparedStatement.setString(3, topo.getFichier());
           
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

    public List<Topo> lister() throws DaoException {
        List<Topo> topos = new ArrayList<Topo>();
        Connection connexion = null;
        Statement statement = null;
        ResultSet resultat = null;

        try {
            connexion = daoFactory.getConnection();
            statement = connexion.createStatement();
            resultat = statement.executeQuery("SELECT id_site, nom_topo, fichier FROM topo;");

            while (resultat.next()) {
                int idSite = resultat.getInt("id_site");
                String nomTopo = resultat.getString("nom_topo");
                
                String fichier = resultat.getString("fichier");
                
                Topo topo = new Topo();
                topo.setIdSite(idSite);
                topo.setNomTopo(nomTopo);
                topo.setFichier(fichier);
                

                topos.add(topo);
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
        return topos;
    }

	


}