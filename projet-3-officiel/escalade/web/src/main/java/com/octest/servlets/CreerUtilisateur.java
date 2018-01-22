package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/creerUtilisateur.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = new Utilisateur();
		boolean mailExiste = false;
		boolean créé = false;
		char premiereLettreNom = request.getParameter("nom").charAt(0);
		char premiereLettrePrenom = request.getParameter("prenom").charAt(0);
		char premiereLettreMail = request.getParameter("mail").charAt(0);
		char premiereLettreMdp = request.getParameter("mdp").charAt(0);

		if (premiereLettreNom == ' ' || premiereLettrePrenom == ' ' || premiereLettreMail == ' '
				|| premiereLettreMdp == ' ') {
			if (premiereLettreNom == ' ') {
				request.setAttribute("errorNom", "Nom obligatoire");
			}
			if (premiereLettrePrenom == ' ') {
				request.setAttribute("errorPrenom", "Prénom obligatoire");
			}
			if (premiereLettreMail == ' ') {
				request.setAttribute("errorMail", "Mail obligatoire");
			}
			if (premiereLettreMdp == ' ') {
				request.setAttribute("errorMdp", "Mot de passe obligatoire");
			}
		} else {

			try { // verification du mail

				for (Utilisateur user : utilisateurDao.lister()) {
					if (request.getParameter("mail").equals(user.getMail())) {
						mailExiste = true;
						request.setAttribute("errorMail", "Mail déjà enregistré dans la base");
					}
				}

				if (mailExiste == false) {

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
						créé = true;
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// fin du if validerMail
				} else {
					//System.out.println("deja ce mail");
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
		}
		
if (créé == true) {
	session.setAttribute("nouvelUtilisateur", utilisateur.getMail());
	this.getServletContext().getRequestDispatcher("/WEB-INF/utilisateurAjoute.jsp").forward(request, response);
}
else {
		this.getServletContext().getRequestDispatcher("/WEB-INF/creerUtilisateur.jsp").forward(request, response);
}
	}

}
