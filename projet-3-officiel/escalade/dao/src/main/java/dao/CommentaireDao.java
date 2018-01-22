package dao;

import java.util.List;

import org.beans.Commentaire;

public interface CommentaireDao {
	void ajouterCommentaire(Commentaire commentaire) throws DaoException;

	void ajouterCommentaireSite(Commentaire commentaire) throws DaoException;

	List<Commentaire> lister() throws DaoException;

	List<Commentaire> listerById(int idSite2) throws DaoException;

	void supprimerCommentaireSite(int idCommentaire) throws DaoException;

	void supprimerCommentaireTopo(int idCommentaire) throws DaoException;
}
