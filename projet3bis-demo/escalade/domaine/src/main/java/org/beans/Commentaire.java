package org.beans;

import java.security.Timestamp;
import java.sql.Date;

public class Commentaire {

	private int id;
	private int idTopo;
	private int idSite;

	private int idUtilisateur;
	private String commentaire;
	private Date date;
	private String dateRetour;

	public String getDateRetour() {
		return dateRetour;
	}

	public void setDateRetour(String dateRetour) {
		this.dateRetour = dateRetour;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public int getIdSite() {
		return idSite;
	}

	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}

}
