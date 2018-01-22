package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.BeanException;
import org.beans.Longueur;
import org.beans.Secteur;
import org.beans.Utilisateur;
import org.beans.Voie;
import org.beans.Site;

import dao.DaoException;
import dao.DaoFactory;
import dao.LongueurDao;
import dao.SecteurDao;
import dao.SiteDao;
import dao.UtilisateurDao;
import dao.VoieDao;

/**
 * Servlet implementation class Site
 */
public class Regions extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private SiteDao siteDao;
	private SecteurDao secteurDao;
	private VoieDao voieDao;
	private LongueurDao longueurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.siteDao = daoFactory.getSiteDao();
		this.secteurDao = daoFactory.getSecteurDao();
		this.voieDao = daoFactory.getVoieDao();
		this.longueurDao = daoFactory.getLongueurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Regions() {
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
		String pays = request.getParameter("pays");

		Set<String> listePays = new HashSet<String>();
		Set<String> listeRegions = new HashSet<String>();
		try {
			for (Site site : siteDao.lister()) {
				if (site.getPays() == pays) {
					listeRegions.add(site.getRegion());
				}
				String country = site.getPays();
				listePays.add(country);

			}
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			request.setAttribute("pays", listePays);
			request.setAttribute("regions", listeRegions);
			request.setAttribute("sites", siteDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 * 
	 * 
	 */

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pays = request.getParameter("pays");

		Set<String> listePays = new HashSet<String>();
		Set<String> listeRegions = new HashSet<String>();
		try {
			for (Site site : siteDao.lister()) {
				if (site.getPays() == pays) {
					listeRegions.add(site.getRegion());
				}
				String country = site.getPays();
				listePays.add(country);

			}
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			request.setAttribute("pays", listePays);
			request.setAttribute("regions", listeRegions);
			request.setAttribute("sites", siteDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
	}
}
