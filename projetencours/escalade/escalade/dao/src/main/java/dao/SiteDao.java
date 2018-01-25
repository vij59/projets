package dao;

import java.util.List;

import org.beans.Site;

public interface SiteDao {
	void ajouterSite(Site site) throws DaoException;

	List<Site> lister() throws DaoException;

	int recupererIdSite(Site site) throws DaoException;

	List<Site> listerDistinct() throws DaoException;
}
