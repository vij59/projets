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
import org.beans.Commentaire;
import org.beans.Longueur;
import org.beans.Secteur;
import org.beans.Utilisateur;
import org.beans.Voie;
import org.beans.Site;

import dao.CommentaireDao;
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
	private CommentaireDao commentaireDao;
	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.siteDao = daoFactory.getSiteDao();
		this.secteurDao = daoFactory.getSecteurDao();
		this.voieDao = daoFactory.getVoieDao();
		this.longueurDao = daoFactory.getLongueurDao();
		this.commentaireDao = daoFactory.getCommentaireSiteDao();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DetailsSite() {
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
			session.removeAttribute("top");
			session.removeAttribute("topoNom");
			session.removeAttribute("nomSite");

			session.removeAttribute("idSite");
		} catch (Exception e) {

		}

		try {
			request.setAttribute("sites", siteDao.lister());
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
		List<Site> listeSites = null;
		List<Secteur> listeSecteurs = new ArrayList<Secteur>();
		List<Voie> listeVoies = new ArrayList<Voie>();
		List<Longueur> listeLongueurs = new ArrayList<Longueur>();
		List<Secteur> listeSecteurFinale = new ArrayList<Secteur>();
		List<Voie> listeVoiesFinale = new ArrayList<Voie>();
		List<Longueur> listeLongueursFinale = new ArrayList<Longueur>();

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
			if (site.getId() == idSite) {
				//System.out.println(site);
				siteDetail = site;
				//System.out.println(siteDetail);
			}

		}

		for (Secteur secteur : listeSecteurs) {
			if (secteur.getIdSite() == idSite) {
				listeSecteurFinale.add(secteur);

				for (Voie voie : listeVoies) {
					if (voie.getIdSecteur() == secteur.getId()) {
						listeVoiesFinale.add(voie);

						for (Longueur longueur : listeLongueurs) {
							if (longueur.getIdVoie() == voie.getId()) {
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

			int x = 0;
			for (Commentaire com : commentaireDao.lister()) {
				if (com.getIdSite() == idSite2 && com.getIdUtilisateur() == utilisateur.getId()) {
					if (comment.equals(com.getCommentaire())) {
						x++;
					}

				}
			}

			if (comment.equals("")) {
				//System.out.println("pas de commentaire");
			} else if (x > 0) {
				//System.out.println("déjà écrit");
			}

			else {
				//System.out.println(commentaire.getCommentaire());
				commentaireDao.ajouterCommentaireSite(commentaire);
				//System.out.println("ajouté");
			}
		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}

		try {
			request.setAttribute("sites", siteDao.lister());
			request.setAttribute("utilisateurs", utilisateurDao.lister());
			request.setAttribute("commentaires", commentaireDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		String servletPath = request.getServletPath();
		//System.out.println(" servlet path : " + servletPath);
		session.setAttribute("redirection", servletPath);

		this.getServletContext().getRequestDispatcher("/WEB-INF/commentaireValidation.jsp").forward(request, response);
	}
}