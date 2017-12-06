package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beans.BeanException;
import org.beans.Pseudo;
import org.beans.Utilisateur;

import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;

/**
 * Servlet implementation class CreerUtilisateur
 */
public class CreerUtilisateur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
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
        
        try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerUtilisateur.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();
       
        try { // verification du mail
			if (utilisateurDao.validerMail(request.getParameter("mail")) == false) 
			{
			
			try {
				utilisateur.setNom(request.getParameter("nom"));
			} catch (BeanException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			utilisateur.setPrenom(request.getParameter("prenom"));
      
			utilisateur.setMail(request.getParameter("mail"));
			utilisateur.setMdp(request.getParameter("mdp"));
			
			try {
				utilisateurDao.ajouterUtilisateur(utilisateur);
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//fin du if validerMail
			}
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
       
        
        try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/creerUtilisateur.jsp").forward(request, response);
    }
}
