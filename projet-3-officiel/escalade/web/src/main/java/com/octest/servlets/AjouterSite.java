package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Secteur;
import org.beans.Site;
import dao.DaoException;
import dao.DaoFactory;
import dao.SiteDao;

/**
 * Servlet implementation class Site
 */
public class AjouterSite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterSite() {
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
		String servletPath = request.getServletPath();
		//System.out.println(" servlet path : " + servletPath);
		session.setAttribute("redirection", servletPath);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Site site = new Site();
		HttpSession session = request.getSession();
		char premiereLettreNom = request.getParameter("nom_site").charAt(0);
		char premiereLettrePays = request.getParameter("pays").charAt(0);
		char premiereLettreRegion = request.getParameter("region").charAt(0);

		if (premiereLettreNom == ' ' || premiereLettrePays == ' ' || premiereLettreRegion == ' ') {
			if (premiereLettreNom == ' ') {
				request.setAttribute("errorNom", "Site doit avoir un nom");
			}
			if (premiereLettrePays == ' ') {
				request.setAttribute("errorPays", "Pays obligatoire");
			}
			if (premiereLettreRegion == ' ') {
				request.setAttribute("errorRegion", "Region obligatoire, entrer X si inconnue");
			}
		} else {

			site.setNomSite(request.getParameter("nom_site"));
			site.setPays(request.getParameter("pays"));
			site.setRegion(request.getParameter("region"));
			site.setId(1);

			session.setAttribute("site", site);
			request.setAttribute("site", site);
		}
		/*
		 * List<Site> listeSites = new ArrayList<Site>(); listeSites.add(site);
		 * request.setAttribute("sites", listeSites); session.setAttribute(
		 * "sites", listeSites );
		 */
		try {
			int i = Integer.parseInt(request.getParameter("reponse"));
			if (i == 1) {
				session.setAttribute("site", null);
				request.setAttribute("site", null);
			}
		} catch (Exception e) {
			//System.out.println(e);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSite.jsp").forward(request, response);
	}
}