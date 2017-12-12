package org.beans;

import java.util.List;

public class Site {
	
	private int id;
	private String nomSite;
    private String pays;
    private String region;
    private int codePostal;
    private int nbSecteurs;
    private List<Secteur> secteurs;
    
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
		public void setSecteurs(List<Secteur> secteurs) {
			this.secteurs = secteurs;
		}
}

