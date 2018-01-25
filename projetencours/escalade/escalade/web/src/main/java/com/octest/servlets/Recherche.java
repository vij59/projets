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
public class Recherche extends HttpServlet {
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
	public Recherche() {
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
		Set<String> listePays = new HashSet<String>();
		Set<String> listeRegions = new HashSet<String>();
		List<String> listeDesRegions = new ArrayList<String>();
		Set<Site> listeDesSites = new HashSet<Site>();
		
		try {
			for (Site site : siteDao.lister()) {
				String country = site.getPays();
				listePays.add(country);
				String area = site.getRegion();
				listeRegions.add(area);

			}

			request.setAttribute("pays", listePays);
			request.setAttribute("regions", listeRegions);

			request.setAttribute("site", siteDao.listerDistinct());
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
		List<Site> listeSites = null;
		Set<Site> listeSitesTrouvés = new HashSet<Site>();
		List<Secteur> listeSecteurs = new ArrayList<Secteur>();
		List<Voie> listeVoies = new ArrayList<Voie>();
		List<Longueur> listeLongueurs = new ArrayList<Longueur>();
		request.setAttribute("affiche", 0);
		boolean trouve = false;

		try {
			listeSites = siteDao.lister();
			listeSecteurs = secteurDao.lister();
			listeVoies = voieDao.lister();
			listeLongueurs = longueurDao.lister();
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String pays = request.getParameter("pays").toUpperCase();
		String region = "";
		try {
			region = request.getParameter("region").toUpperCase();
			if (region.equals("TOUTES REGIONS")) {
				region = "";
			}
		} catch (Exception e) {
			//System.out.println("pas de region");
		}
		String cotationVoie = request.getParameter("cotationVoie").toUpperCase();
		String cotationLongueur = request.getParameter("cotationLongueur").toUpperCase();

		for (Site site : listeSites) {

			if (pays.equals("")) {

				if (cotationLongueur.equals("") && !cotationVoie.equals("")) {

					for (Voie v : listeVoies) {
						if (v.getCotation().equals(cotationVoie)) {
							int sect = v.getIdSecteur();
							for (Secteur s : listeSecteurs) {
								if (s.getId() == sect) {
									int siteId = s.getIdSite();
									if (site.getId() == siteId) {
										listeSitesTrouvés.add(site);
										trouve = true;
									}
								}
							}

						}
					}

				}

				else if (cotationVoie.equals("") && !cotationLongueur.equals("")) {

					for (Longueur l : listeLongueurs) {
						if (l.getCotation().equals(cotationLongueur)) {
							int voi = l.getIdVoie();

							for (Voie v : listeVoies) {
								if (v.getId() == voi) {
									int secteurId = v.getIdSecteur();

									for (Secteur s : listeSecteurs) {
										if (s.getId() == secteurId) {
											int siteId = s.getIdSite();
											if (site.getId() == siteId) {
												listeSitesTrouvés.add(site);
												trouve = true;
											}
										}
									}
								}
							}

						}
					}

				}

				else if (!cotationVoie.equals("") && !cotationLongueur.equals("")) {
					for (Longueur l : listeLongueurs) {
						if (l.getCotation().equals(cotationLongueur)) {
							int voi = l.getIdVoie();

							for (Voie v : listeVoies) {
								if (v.getId() == voi) {
									if (v.getCotation().equals(cotationVoie)) {
										int secteurId = v.getIdSecteur();

										for (Secteur s : listeSecteurs) {
											if (s.getId() == secteurId) {
												int siteId = s.getIdSite();
												if (site.getId() == siteId) {
													listeSitesTrouvés.add(site);
													trouve = true;
												}
											}
										}
									}
								}
							}

						}
					}
				} else {
					listeSitesTrouvés.add(site);
					trouve = true;
				}
			}

			else if (region.equals("") && !pays.equals("")) {

				if (cotationLongueur.equals("") && !cotationVoie.equals("")) {

					for (Voie v : listeVoies) {
						if (v.getCotation().equals(cotationVoie)) {
							int sect = v.getIdSecteur();
							for (Secteur s : listeSecteurs) {
								if (s.getId() == sect) {
									int siteId = s.getIdSite();
									if (site.getId() == siteId) {
										if (site.getPays().equals(pays)) {
											listeSitesTrouvés.add(site);
											trouve = true;
										}
									}
								}
							}

						}
					}

				}

				else if (cotationVoie.equals("") && !cotationLongueur.equals("")) {

					for (Longueur l : listeLongueurs) {
						if (l.getCotation().equals(cotationLongueur)) {
							int voi = l.getIdVoie();

							for (Voie v : listeVoies) {
								if (v.getId() == voi) {
									int secteurId = v.getIdSecteur();

									for (Secteur s : listeSecteurs) {
										if (s.getId() == secteurId) {
											int siteId = s.getIdSite();
											if (site.getId() == siteId) {
												if (site.getPays().equals(pays)) {
													listeSitesTrouvés.add(site);
													trouve = true;
												}

											}
										}
									}
								}
							}

						}
					}

				}

				else if (!cotationVoie.equals("") && !cotationLongueur.equals("")) {
					for (Longueur l : listeLongueurs) {
						if (l.getCotation().equals(cotationLongueur)) {
							int voi = l.getIdVoie();

							for (Voie v : listeVoies) {
								if (v.getId() == voi) {
									if (v.getCotation().equals(cotationVoie)) {
										int secteurId = v.getIdSecteur();

										for (Secteur s : listeSecteurs) {
											if (s.getId() == secteurId) {
												int siteId = s.getIdSite();
												if (site.getId() == siteId) {
													if (site.getPays().equals(pays)) {
														listeSitesTrouvés.add(site);
														trouve = true;
													}
												}
											}
										}
									}
								}
							}

						}
					}
				} else {
					if (site.getPays().equals(pays)) {
						listeSitesTrouvés.add(site);
						trouve = true;
					}

				}
			}

			else if (!pays.equals("") && !region.equals("")) {

				if (cotationLongueur.equals("") && !cotationVoie.equals("")) {

					for (Voie v : listeVoies) {
						if (v.getCotation().equals(cotationVoie)) {
							int sect = v.getIdSecteur();
							for (Secteur s : listeSecteurs) {
								if (s.getId() == sect) {
									int siteId = s.getIdSite();
									if (site.getId() == siteId) {
										if (site.getRegion().equals(region) && (site.getPays().equals(pays))) {
											listeSitesTrouvés.add(site);
											trouve = true;
										}
									}
								}
							}

						}
					}

				}

				else if (cotationVoie.equals("") && !cotationLongueur.equals("")) {

					for (Longueur l : listeLongueurs) {
						if (l.getCotation().equals(cotationLongueur)) {
							int voi = l.getIdVoie();

							for (Voie v : listeVoies) {
								if (v.getId() == voi) {
									int secteurId = v.getIdSecteur();

									for (Secteur s : listeSecteurs) {
										if (s.getId() == secteurId) {
											int siteId = s.getIdSite();
											if (site.getId() == siteId) {
												if (site.getRegion().equals(region) && (site.getPays().equals(pays))) {
													listeSitesTrouvés.add(site);
													trouve = true;
												}

											}
										}
									}
								}
							}

						}
					}

				}

				else if (!cotationVoie.equals("") && !cotationLongueur.equals("")) {
					for (Longueur l : listeLongueurs) {
						if (l.getCotation().equals(cotationLongueur)) {
							int voi = l.getIdVoie();

							for (Voie v : listeVoies) {
								if (v.getId() == voi) {
									if (v.getCotation().equals(cotationVoie)) {
										int secteurId = v.getIdSecteur();

										for (Secteur s : listeSecteurs) {
											if (s.getId() == secteurId) {
												int siteId = s.getIdSite();
												if (site.getId() == siteId) {
													if (site.getRegion().equals(region)
															&& (site.getPays().equals(pays))) {
														listeSitesTrouvés.add(site);
														trouve = true;
													}
												}
											}
										}
									}
								}
							}

						}
					}
				} else {
					if (site.getPays().equals(pays)) {
						if (site.getRegion().equals(region)) {
							listeSitesTrouvés.add(site);
							trouve = true;
						}
					}
				}
			}

			/*
			 * else if (pays.equals("")) { if (site.getRegion().equals(region))
			 * listeSitesTrouvés.add(site); }
			 * 
			 * else if (region.equals("")) { if (site.getPays().equals(pays))
			 * listeSitesTrouvés.add(site); } else if
			 * (site.getPays().equals(pays) && site.getRegion().equals(region))
			 * { System.out.println(site); listeSitesTrouvés.add(site);
			 * 
			 * }
			 */

		}

		request.setAttribute("siteRecherché", listeSitesTrouvés);
		
		if (trouve == true) {
			request.setAttribute("affiche", 1);
		}

		Set<String> listePays = new HashSet<String>();
		Set<String> listeRegions = new HashSet<String>();
		try {
			for (Site site : siteDao.lister()) {
				String country = site.getPays();
				listePays.add(country);
				String area = site.getRegion();
				listeRegions.add(area);
			}
		} catch (DaoException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		try {
			request.setAttribute("pays", listePays);
			request.setAttribute("regions", listeRegions);
			request.setAttribute("sites", siteDao.lister());
			request.setAttribute("site", siteDao.listerDistinct());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/recherche.jsp").forward(request, response);
	}
}