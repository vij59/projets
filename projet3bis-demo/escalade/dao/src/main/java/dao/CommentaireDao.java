package dao;

import java.util.List;

import org.beans.Commentaire;

public interface CommentaireDao {
	   void ajouterCommentaire (Commentaire commentaire) throws DaoException;
	    List<Commentaire> lister() throws DaoException;
	  
}
