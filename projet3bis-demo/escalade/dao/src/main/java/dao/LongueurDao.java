package dao;

import java.util.List;

import org.beans.Longueur;
import org.beans.Voie;

public interface LongueurDao {
	void ajouterLongueur(Longueur longueur, Voie voie) throws DaoException;

	List<Longueur> lister() throws DaoException;

	int recupererIdLongueur(Longueur longueur, Voie voie) throws DaoException;
}
