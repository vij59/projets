package dao;

import java.util.List;

import org.beans.Reservation;



public interface ReservationDao {

	  void ajouterReservation( Reservation reservation ) throws DaoException;
	    List<Reservation> lister() throws DaoException;
	  
}


