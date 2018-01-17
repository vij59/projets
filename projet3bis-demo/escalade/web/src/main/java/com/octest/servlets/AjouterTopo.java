package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Topo;

import dao.DaoException;
import dao.DaoFactory;
import dao.SiteDao;
import dao.TopoDao;

/**
 * Servlet implementation class Topos
 */
public class AjouterTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopoDao topoDao;
	private SiteDao siteDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.topoDao = daoFactory.getTopoDao();
		this.siteDao = daoFactory.getSiteDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterTopo() {
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
		String servletPath = request.getServletPath();
		//System.out.println(" servlet path : " + servletPath);
		session.setAttribute("redirection", servletPath);
		try {
			request.setAttribute("sites", siteDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			request.setAttribute("topos", topoDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterTopo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Topo topo = new Topo();
		HttpSession session = request.getSession();
		int idSite = Integer.parseInt(request.getParameter("idSite"));

		char premiereLettreNom = request.getParameter("nom_topo").charAt(0);
		if (premiereLettreNom == ' ') {
			request.setAttribute("errorNom", "Site doit avoir un nom");
			try {
				request.setAttribute("sites", siteDao.lister());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			topo.setNomTopo(request.getParameter("nom_topo"));
			topo.setFichier(request.getParameter("fichier"));
			topo.setIdSite(idSite);

			try {
				topoDao.ajouterTopo(topo, idSite);
				request.setAttribute("topos", topoDao.lister());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			try {
				request.setAttribute("topos", topoDao.lister());
			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		String servletPath = request.getServletPath();
		//System.out.println(" servlet path : " + servletPath);
		session.setAttribute("redirection", servletPath);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterTopo.jsp").forward(request, response);
	}
}