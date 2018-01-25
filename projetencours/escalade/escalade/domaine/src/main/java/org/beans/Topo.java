package org.beans;

import java.io.File;

public class Topo {
	private int idTopo;
	private int idSite;
	private String nomTopo;
	private String fichier;
	private boolean disponible;

	public String getFichier() {
		return fichier;
	}

	public void setFichier(String fichier) {
		this.fichier = fichier;
	}

	public int getIdTopo() {
		return idTopo;
	}

	public void setIdTopo(int idTopo) {
		this.idTopo = idTopo;
	}

	public int getIdSite() {
		return idSite;
	}

	public void setIdSite(int idSite) {
		this.idSite = idSite;
	}

	public String getNomTopo() {
		return nomTopo;
	}

	public void setNomTopo(String nomTopo) {
		this.nomTopo = nomTopo;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

}
