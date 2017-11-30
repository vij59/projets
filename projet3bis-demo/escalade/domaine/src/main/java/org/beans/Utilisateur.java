package org.beans;

public class Utilisateur {
    private String nom;
    private String prenom;
    private String mail;
    private String mdp;
    
    public String getNom() {
        return nom;
    }
    public void setNom(String nom) throws BeanException {
    	
        if (nom.length() > 10) {
            throw new BeanException("Le nom est trop grand ! (10 caract�res maximum)");
        }
        else  {
            this.nom = nom; 
        }
    }
    public String getPrenom() {
        return prenom;
    }
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
	public String getMdp() {
		return mdp;
	}
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	public String getMail() {
		return mail;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
}