package dao;

import java.util.List;

import org.beans.Topo;

public interface TopoDao {
	  void ajouterTopo( Topo topo ) throws DaoException;
	    List<Topo> lister() throws DaoException;
}
