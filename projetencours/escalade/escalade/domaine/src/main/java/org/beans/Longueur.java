package org.beans;

import java.util.ArrayList;
import java.util.List;

public class Longueur {

	private int id;
	private String nom;
	private String cotation;
	private int idVoie;

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

	public int getIdVoie() {
		return idVoie;
	}

	public void setIdVoie(int idVoie) {
		this.idVoie = idVoie;
	}

	@Override
	public String toString() {
		return "Longueur [id=" + id + ", nom=" + nom + ", cotation=" + cotation + "]";
	}

}
