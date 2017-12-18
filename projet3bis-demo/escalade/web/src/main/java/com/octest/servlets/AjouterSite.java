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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Site site = new Site();
		HttpSession session = request.getSession();

		site.setNomSite(request.getParameter("nom_site"));
		site.setPays(request.getParameter("pays"));
		site.setRegion(request.getParameter("region"));

		session.setAttribute("site", site);
		request.setAttribute("site", site);

		/*
       List<Site> listeSites = new ArrayList<Site>();
        listeSites.add(site);
        request.setAttribute("sites", listeSites);
        session.setAttribute( "sites", listeSites );
		 */

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSite.jsp").forward(request, response);
	}
}