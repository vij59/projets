package com.octest.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.BeanException;
import org.beans.Commentaire;
import org.beans.Reservation;
import org.beans.Topo;
import org.beans.Utilisateur;

import dao.DaoException;
import dao.DaoFactory;
import dao.ReservationDao;
import dao.SiteDao;
import dao.TopoDao;
import dao.UtilisateurDao;
import dao.CommentaireDao;

/**
 * Servlet implementation class Topos
 */
public class CommentaireSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SiteDao siteDao;
	private CommentaireDao commentaireDao;
	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.siteDao = daoFactory.getSiteDao();
		this.commentaireDao = daoFactory.getCommentaireSiteDao();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CommentaireSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		try {
			request.setAttribute("sites", siteDao.lister());
			session.setAttribute("commentaires", commentaireDao.lister());
			request.setAttribute("commentaires", commentaireDao.lister());
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsSite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Utilisateur utilisateur = new Utilisateur();
		utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
		//System.out.println("id user");
		//System.out.println(utilisateur.getId());

		try {
			int idSite = Integer.parseInt(request.getParameter("idSite"));

			//System.out.println("id site");
			//System.out.println(idSite);

			Commentaire commentaire = new Commentaire();
			String comment = request.getParameter("comment");
			commentaire.setCommentaire(comment);
			commentaire.setIdUtilisateur(utilisateur.getId());
			commentaire.setIdSite(idSite);

			if (comment.equals("")) {
				//System.out.println("pas de commentaire");
			} else {
				//System.out.println(commentaire.getCommentaire());
				commentaireDao.ajouterCommentaireSite(commentaire);
				//System.out.println("ajouté");
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}

		session.removeAttribute("commentaire");

		try {
			request.setAttribute("sites", siteDao.lister());
			request.setAttribute("utilisateurs", utilisateurDao.lister());
			request.setAttribute("commentaires", commentaireDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/commentaireValidation.jsp").forward(request, response);
	}
}