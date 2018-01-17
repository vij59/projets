package org.beans;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Site {

	private int id;
	private String nomSite;
	private String pays;
	private String region;
	private int codePostal;
	private int nbSecteurs;

	private List<Secteur> secteurs = new ArrayList<Secteur>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomSite() {
		return nomSite;
	}

	public void setNomSite(String nomSite) {
		this.nomSite = nomSite;
	}

	public String getPays() {
		return pays;
	}

	public void setPays(String pays) {
		this.pays = pays;
	}

	public String getRegion() {
		return region;
	}

	public void setRegion(String region) {
		this.region = region;
	}

	public int getCodePostal() {
		return codePostal;
	}

	public void setCodePostal(int codePostal) {
		this.codePostal = codePostal;
	}

	public int getNbSecteurs() {
		return nbSecteurs;
	}

	public void setNbSecteurs(int nbSecteurs) {
		this.nbSecteurs = nbSecteurs;
	}

	public List<Secteur> getSecteurs() {
		return secteurs;
	}

	public void addSecteur(Secteur secteur) {
		this.secteurs.add(secteur);

	}

	public void removeSecteurs() {
		this.secteurs.clear();

	}

	public void removeSecteur(Secteur secteur) {
		this.secteurs.remove(secteur);

	}

	@Override
	public String toString() {
		return "Site [id=" + id + ", nomSite=" + nomSite + ", pays=" + pays + ", region=" + region + ", codePostal="
				+ codePostal + ", nbSecteurs=" + nbSecteurs + ", secteurs=" + secteurs + "]";
	}
}
