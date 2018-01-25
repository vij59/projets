package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Commentaire;
import org.beans.Utilisateur;

import dao.CommentaireDao;
import dao.DaoException;
import dao.DaoFactory;

/**
 * Servlet implementation class AjoutCommentaireSite
 */
public class AjoutCommentaireSite extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CommentaireDao commentaireDao;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();

		this.commentaireDao = daoFactory.getCommentaireSiteDao();

	}

	public AjoutCommentaireSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {

			Utilisateur utilisateur = new Utilisateur();
			utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
			//System.out.println("id user");
			//System.out.println(utilisateur.getId());

			int idSite2 = Integer.parseInt(request.getParameter("id"));

			//System.out.println("id site");
			//System.out.println(idSite2);

			Commentaire commentaire = new Commentaire();
			String comment = request.getParameter("comment");
			commentaire.setCommentaire(comment);
			commentaire.setIdUtilisateur(utilisateur.getId());
			commentaire.setIdSite(idSite2);

			if (comment.equals("")) {
				//System.out.println("pas de commentaire");
			}

			else {
				//System.out.println(commentaire.getCommentaire());
				commentaireDao.ajouterCommentaireSite(commentaire);
				//System.out.println("ajouté");
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsSite.jsp").forward(request, response);
	}
}