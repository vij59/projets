package org.beans;

import java.util.ArrayList;
import java.util.List;

public class Voie {

	private int id;
	private String nom;
	private String cotation;
	private int idSecteur;
	private List<Longueur> longueurs = new ArrayList<Longueur>();

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

	public int getIdSecteur() {
		return idSecteur;
	}

	public void setIdSecteur(int idSecteur) {
		this.idSecteur = idSecteur;
	}

	public List<Longueur> getLongueurs() {
		return longueurs;
	}

	public void addLongueur(Longueur longueur) {
		this.longueurs.add(longueur);
	}

	@Override
	public String toString() {
		return "Voie [id=" + id + ", nom=" + nom + ", cotation=" + cotation + ", idSecteur=" + idSecteur
				+ ", longueurs=" + longueurs + "]";
	}

}
