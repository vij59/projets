package com.octest.servlets;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Longueur;
import org.beans.Secteur;
import org.beans.Site;
import org.beans.Voie;

import dao.DaoException;
import dao.DaoFactory;
import dao.LongueurDao;
import dao.SecteurDao;
import dao.SiteDao;
import dao.UtilisateurDao;
import dao.VoieDao;

/**
 * Servlet implementation class Longueur
 */
public class RecapSite extends HttpServlet {
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
	public RecapSite() {
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

		this.getServletContext().getRequestDispatcher("/WEB-INF/recapSite.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Site site = (Site) session.getAttribute("site");

		////System.out.println(site);
		try {
			siteDao.ajouterSite(site);

		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			int idSite = siteDao.recupererIdSite(site);
			site.setId(idSite);
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		List<Secteur> listeSecteurs = site.getSecteurs();
		//System.out.println("\n==> Liste des secteurs..");

		// Iterator<Secteur> iterSecteur = listeSecteurs.iterator();
		for (Secteur secteur : listeSecteurs) {
			// Secteur secteur = iterSecteur.next(); //
			// //System.out.println(secteur.getNom());
			try {
				secteurDao.ajouterSecteur(secteur, site); // TODO : idsite
															// plutot que site

			} catch (DaoException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			int idSecteur;
			try {
				idSecteur = secteurDao.recupererIdSecteur(secteur, site);
				//System.out.println(idSecteur);
				secteur.setId(idSecteur);
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			List<Voie> listeVoies = secteur.getVoies();
			//System.out.println("\n==> Liste des voies du secteur : " + secteur.getNom());
			Iterator<Voie> iterVoie = listeVoies.iterator();
			while (iterVoie.hasNext()) {
				Voie voie = iterVoie.next();
				// //System.out.println(voie.getNom());
				try {
					voieDao.ajouterVoie(voie, secteur);
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				int idVoie;
				try {
					idVoie = voieDao.recupererIdVoie(voie, secteur);
					voie.setId(idVoie);
				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				List<Longueur> listeLongueurs = voie.getLongueurs();
				//System.out.println("\n==> Liste des longueurs de la voie : " + voie.getNom());
				Iterator<Longueur> iterLongueur = listeLongueurs.iterator();
				while (iterLongueur.hasNext()) {
					Longueur longueur = iterLongueur.next();
					//System.out.println(longueur.getNom());
					try {
						longueurDao.ajouterLongueur(longueur, voie);
					} catch (DaoException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}

		/*
		 * ajouterLongueur (Longueur longueur, Voie voie); ajouterVoie (Voie
		 * voie, Secteur secteur); ajouterSite (Site site); ajouterSecteur
		 * (Secteur secteur, Site site); preparedStatement = connexion.
		 * prepareStatement("INSERT INTO site(nom_site, pays, region, code_postal) VALUES(?, ?, ?, ?);"
		 * ); preparedStatement.setString(1, site.getNomSite());
		 * preparedStatement.setString(2, site.getPays());
		 * preparedStatement.setString(3, site.getRegion());
		 * preparedStatement.setInt(4, site.getCodePostal());
		 */

		request.setAttribute("siteFini", true);
		this.getServletContext().getRequestDispatcher("/WEB-INF/confirmationSite.jsp").forward(request, response);

	}
}