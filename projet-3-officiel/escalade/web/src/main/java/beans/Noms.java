package beans;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

import org.beans.BeanException;
import org.beans.Utilisateur;

public class Noms {
    private Connection connexion;
    
    public List<Utilisateur> recupererUtilisateurs() throws BeanException {
        List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();
        Statement statement = null;
        ResultSet resultat = null;

        loadDatabase();
        
        try {
            statement = connexion.createStatement();

            // Ex�cution de la requ�te
            resultat = statement.executeQuery("SELECT nom, prenom FROM noms;");

            // R�cup�ration des donn�es
            while (resultat.next()) {
                String nom = resultat.getString("nom");
                String prenom = resultat.getString("prenom");
                
                Utilisateur utilisateur = new Utilisateur();
                utilisateur.setNom(nom);
                utilisateur.setPrenom(prenom);
                
                utilisateurs.add(utilisateur);
            }
        } catch (SQLException e) {
        } finally {
            // Fermeture de la connexion
            try {
                if (resultat != null)
                    resultat.close();
                if (statement != null)
                    statement.close();
                if (connexion != null)
                    connexion.close();
            } catch (SQLException ignore) {
            }
        }
        
        return utilisateurs;
    }
    
    private void loadDatabase() {
        // Chargement du driver
        try {
        	 Class.forName("org.postgresql.Driver");
             System.out.println("Driver O.K.");
        } catch (ClassNotFoundException e) {
        }

        try {
        	
            connexion = DriverManager.getConnection("jdbc:postgresql://localhost:5432/java_ee", "postgres", "");
            System.out.println("bonjour");
            
           // jdbc:postgresql://host:port/database
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("marche pas");
        }
    }
    
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        loadDatabase();
        
        try {
            PreparedStatement preparedStatement = connexion.prepareStatement("INSERT INTO noms(nom, prenom) VALUES(?, ?);");
            preparedStatement.setString(1, utilisateur.getNom());
            preparedStatement.setString(2, utilisateur.getPrenom());
            
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}