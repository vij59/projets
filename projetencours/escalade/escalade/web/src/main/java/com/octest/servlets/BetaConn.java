package com.octest.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.BeanException;
import org.beans.Utilisateur;

import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;

/**
 * Servlet implementation class CreerUtilisateur
 */
public class BetaConn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_USER = "utilisateur";

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BetaConn() {
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
		this.getServletContext().getRequestDispatcher("/WEB-INF/betaConn.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Utilisateur utilisateur = new Utilisateur();
		HttpSession session = request.getSession();
		boolean mailExiste = false;
		boolean mdpExiste = false;
		boolean accèsAccordé = false;

		char premiereLettreMail = request.getParameter("mail").charAt(0);
		char premiereLettreMdp = request.getParameter("mdp").charAt(0);

		if (premiereLettreMail == ' ' || premiereLettreMdp == ' ') {

			if (premiereLettreMail == ' ') {
				request.setAttribute("errorMail", "Mail obligatoire");
			}
			if (premiereLettreMdp == ' ') {
				request.setAttribute("errorMdp", "Mot de passe obligatoire");
			}
		}

		try {
			for (Utilisateur user : utilisateurDao.lister()) {
				if (request.getParameter("mail").equals(user.getMail())) {
					mailExiste = true;
				}
			}
			if (mailExiste == true)

			{
				//System.out.println("mail OK");
				for (Utilisateur user : utilisateurDao.lister()) {
					if ((mailExiste == true) && request.getParameter("mdp").equals(user.getMdp())) {
						mdpExiste = true;
						//System.out.println(user.getMdp());

					}
				}

				if (mdpExiste == true) {

					utilisateur.setMail(request.getParameter("mail"));
					utilisateur.setMdp(request.getParameter("mdp"));

					List<Utilisateur> listeUtilisateurs = utilisateurDao.lister();

					for (Utilisateur user : listeUtilisateurs) {
						if (user.getMail().equals(request.getParameter("mail"))) {
							try {
								utilisateur.setNom(user.getNom());
								utilisateur.setId(user.getId());
							} catch (BeanException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

					// utilisateur.setMdp(request.getParameter("mdp"));
					utilisateur.setRole(1);

					//System.out.println("mot de passe concorde avec mail");
					session = request.getSession();
					session.setAttribute(ATT_SESSION_USER, utilisateur);
					request.setAttribute(ATT_USER, utilisateur);
					accèsAccordé = true;
				} else {
					//System.out.println("erreur de mot de passe");
					request.setAttribute("errorMdp", "Mot de passe invalide");
				}
			} else {
				//System.out.println("mail pas dans la BDD");
				request.setAttribute("errorMail", "Mail invalide");
			}
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		// fin du if validerMail

		String servletPath = (String) session.getAttribute("redirection");
		//System.out.println(servletPath);

		if (accèsAccordé == true) {
			if (servletPath == null) {
				this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
			} else {
				response.sendRedirect("/web" + servletPath);
			}
		} else {
			this.getServletContext().getRequestDispatcher("/WEB-INF/betaConn.jsp").forward(request, response);
		}
	}

}
