package dao;

import java.util.List;

import org.beans.Secteur;
import org.beans.Site;

public interface SecteurDao {
	void ajouterSecteur(Secteur secteur, Site site) throws DaoException;

	List<Secteur> lister() throws DaoException;

	int recupererIdSecteur(Secteur secteur, Site site) throws DaoException;
}
