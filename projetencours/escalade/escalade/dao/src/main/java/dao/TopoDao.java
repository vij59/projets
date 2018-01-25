package dao;

import java.io.FileNotFoundException;
import java.util.List;

import org.beans.Topo;

public interface TopoDao {
	void ajouterTopo(Topo topo, int idSite) throws DaoException, FileNotFoundException;

	List<Topo> lister() throws DaoException;

	void reserverTopo(int idTopo) throws DaoException;

	void rendreTopo(int idTopo) throws DaoException;

	String getNomSite(int idSite) throws DaoException;
}
