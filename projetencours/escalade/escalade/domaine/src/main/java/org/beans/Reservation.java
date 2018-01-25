package org.beans;

import java.sql.Date;

public class Reservation {

	private int idReservation;
	private int idTopo;
	private int idUtilisateur;
	private Date dateEmprunt;
	private Date dateRetour;

	public int getIdReservation() {
		return idReservation;
	}

	public void setIdReservation(int idReservation) {
		this.idReservation = idReservation;
	}

	public int getIdTopo() {
		return idTopo;
	}

	public void setIdTopo(int idTopo) {
		this.idTopo = idTopo;
	}

	public int getIdUtilisateur() {
		return idUtilisateur;
	}

	public void setIdUtilisateur(int idUtilisateur) {
		this.idUtilisateur = idUtilisateur;
	}

	public Date getDateEmprunt() {
		return dateEmprunt;
	}

	public void setDateEmprunt(Date dateEmprunt) {
		this.dateEmprunt = dateEmprunt;
	}

	public Date getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(Date dateRetour) {
		this.dateRetour = dateRetour;
	}

}
