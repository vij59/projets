package dao;


import java.util.List;
import org.beans.Utilisateur;
//import com.octest.beans.Utilisateur;

public interface UtilisateurDao {
    void ajouter( Utilisateur utilisateur ) throws DaoException;
    List<Utilisateur> lister() throws DaoException;
}