package com.octest.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Topo;

import dao.CommentaireDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.TopoDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class Site
 */
public class DetailsTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private TopoDao topoDao;
	private CommentaireDao commentaireDao;
	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.topoDao = daoFactory.getTopoDao();
		this.commentaireDao = daoFactory.getCommentaireDao();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailsTopo() {
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
		HttpSession session = request.getSession();

		try {

			request.setAttribute("topos", topoDao.lister());
			request.setAttribute("commentaires", commentaireDao.lister());
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsTopo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		int idTopo = 0;
		String topoNom = "";
		String nomSite = (String) session.getAttribute("nomDuSite");
		int idSite = 0;
		try {
			idTopo = Integer.parseInt(request.getParameter("id_topo"));

		} catch (Exception e) {
			//System.out.println(e);
		}

		try {
			for (Topo topo : topoDao.lister()) {
				if (topo.getIdTopo() == idTopo) {
					topoNom = topo.getNomTopo();
					idSite = topo.getIdSite();
					nomSite = topoDao.getNomSite(idSite);
				}
			}
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {

			request.setAttribute("top", idTopo);
			request.setAttribute("topoNom", topoNom);
			request.setAttribute("nomSite", nomSite);
			session.setAttribute("top", idTopo);
			session.setAttribute("topoNom", topoNom);
			session.setAttribute("nomSite", nomSite);
			request.setAttribute("idSite", idSite);
			session.setAttribute("idSite", idSite);
			request.setAttribute("utilisateurs", utilisateurDao.lister());
			request.setAttribute("commentaires", commentaireDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsTopo.jsp").forward(request, response);
	}
}