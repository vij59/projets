package com.octest.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beans.BeanException;
import org.beans.Utilisateur;

import beans.Noms;
import org.beans.Pseudo;

/**
 * Servlet implementation class Connexion
 */
public class Connexion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Connexion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Pseudo tableNoms = new Pseudo();
        try {
			request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());
		} catch (BeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();
        try {
			utilisateur.setNom(request.getParameter("nom"));
		} catch (BeanException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        utilisateur.setPrenom(request.getParameter("prenom"));
        utilisateur.setMail(request.getParameter("mail"));
        
        Pseudo tableNoms = new Pseudo();
        tableNoms.ajouterUtilisateur(utilisateur);
       
        
        try {
			request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());
		} catch (BeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/connexion.jsp").forward(request, response);
    }
    
    

}