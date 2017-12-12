package org.beans;

import java.util.List;

public class Secteur {
	
	private int id;
	private String nom;
	private String cotation;
	private int idSite;
	private int nbVoies;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getCotation() {
		return cotation;
	}
	public void setCotation(String cotation) {
		this.cotation = cotation;
	}
	
	public int getIdSite() {
		return idSite;
	}
	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}
	
	
	public int getNbVoies() {
		return nbVoies;
	}
	public void setNbVoies(int nbVoies) {
		this.nbVoies = nbVoies;
	}
	@Override
	public String toString() {
		return "Secteur [id=" + id + ", nom=" + nom + ", cotation=" + cotation
				+ "]";
	}

}
