package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beans.BeanException;
import org.beans.Pseudo;
import org.beans.Utilisateur;

/**
 * Servlet implementation class CreerUtilisateur
 */
public class CreerUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CreerUtilisateur() {
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
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerUtilisateur.jsp").forward(request, response);
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
        utilisateur.setMdp(request.getParameter("mdp"));
        
        Pseudo tableNoms = new Pseudo();
        tableNoms.ajouterUtilisateur(utilisateur);
        
        try {
			request.setAttribute("utilisateurs", tableNoms.recupererUtilisateurs());
		} catch (BeanException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerUtilisateur.jsp").forward(request, response);
    }
}
