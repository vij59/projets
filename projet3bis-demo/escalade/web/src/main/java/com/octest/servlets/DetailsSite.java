package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class DetailsSite extends HttpServlet {
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
	public DetailsSite() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		try {
			request.setAttribute("sites", siteDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsSite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		List<Site> listeSites = null;
		List<Secteur> listeSecteurs = new ArrayList<Secteur>();
		List<Voie> listeVoies = new ArrayList<Voie>();
		List <Longueur> listeLongueurs = new ArrayList<Longueur>();
		List<Secteur> listeSecteurFinale = new ArrayList<Secteur>();
		List<Voie> listeVoiesFinale = new ArrayList<Voie>();
		List <Longueur> listeLongueursFinale = new ArrayList<Longueur>();
		
		Site siteDetail = new Site();
		
		try {
			listeSites = siteDao.lister();
			listeSecteurs = secteurDao.lister();
			listeVoies = voieDao.lister();
			listeLongueurs = longueurDao.lister();
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}	
		
		int idSite = Integer.parseInt(request.getParameter("id"));
		
		
		for (Site site : listeSites) {
			if(site.getId() == idSite )
			{
				System.out.println(site);
				siteDetail = site;
				System.out.println(siteDetail);
			}
			
		}
		

		
		for(Secteur secteur : listeSecteurs)
		{ 
		if(secteur.getIdSite()==idSite) {
			listeSecteurFinale.add(secteur);
				
			for(Voie voie : listeVoies)
				{ 
				if(voie.getIdSecteur()==secteur.getId()) {
					listeVoiesFinale.add(voie);
					
					
					for(Longueur longueur : listeLongueurs)
					{ 
					if(longueur.getIdVoie()==voie.getId()) {
						listeLongueursFinale.add(longueur);
						voie.addLongueur(longueur);
					}
					}
					secteur.addVoie(voie);
				}
				}
			siteDetail.addSecteur(secteur);
		}
		}
		
		request.setAttribute("siteDetail", siteDetail);
		session.setAttribute("siteDetail", siteDetail);
		
		
		

		try {
			request.setAttribute("sites", siteDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/detailsSite.jsp").forward(request, response);
	}
}