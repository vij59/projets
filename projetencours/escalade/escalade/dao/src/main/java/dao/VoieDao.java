package dao;

import java.util.List;

import org.beans.Voie;
import org.beans.Secteur;

public interface VoieDao {
	void ajouterVoie(Voie voie, Secteur secteur) throws DaoException;

	List<Voie> lister() throws DaoException;

	int recupererIdVoie(Voie voie, Secteur secteur) throws DaoException;
}
