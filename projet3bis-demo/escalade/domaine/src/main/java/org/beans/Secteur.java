package org.beans;

import java.util.ArrayList;
import java.util.List;

public class Secteur {

	private int id;
	private String nom;
	private int idSite;
	private int nbVoies;
	private List<Voie> voies = new ArrayList<Voie>();

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

	public List<Voie> getVoies() {
		return voies;
	}

	public void addVoie(Voie voie) {
		this.voies.add(voie);
	}

	@Override
	public String toString() {
		return "Secteur [id=" + id + ", nom=" + nom + "]";
	}

}
