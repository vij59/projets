package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Secteur;
import org.beans.Site;

import dao.DaoException;
import dao.DaoFactory;
import dao.SecteurDao;

/**
 * Servlet implementation class Secteur
 */
public class AjouterSecteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterSecteur() {
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

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSecteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Secteur secteur = new Secteur();
		HttpSession session = request.getSession();
		Secteur secteur = new Secteur();
		secteur.setNom(request.getParameter("nom_secteur"));

		Site site = (Site) session.getAttribute("site");

		try {
			char premiereLettreNom = request.getParameter("nom_secteur").charAt(0);
			if (request.getParameter("nom_secteur").isEmpty() || premiereLettreNom == ' ') {
				//System.out.println("longueur vide");
				request.setAttribute("errorNom", "Secteur doit avoir un nom");
			} else {
				site.addSecteur(secteur);
				request.setAttribute("affichage", 1);
			}
		} catch (Exception e) {
			//System.out.println("catch erreur longueur");
		}

		int i = 0;
		List<Secteur> listeSecteurs = new ArrayList<Secteur>();
		for (Secteur s : site.getSecteurs()) {
			s.setId(i);
			listeSecteurs.add(s);
			i++;
		}

		try {
			request.setAttribute("secteurs", listeSecteurs);
			session.setAttribute("secteurs", listeSecteurs);
			int j = 0;
			session.setAttribute("numSecteur", j);
			Secteur sec = (Secteur) listeSecteurs.get(0);
			List<Secteur> listeSec = new ArrayList<Secteur>();
			listeSec.add(sec);
			request.setAttribute("sect", listeSec);
			session.setAttribute("sect", listeSec);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
		}
		try {
			int k = Integer.parseInt(request.getParameter("reponse"));
			if (k == 1) {
				site.removeSecteurs();
				session.setAttribute("secteurs", null);
				request.setAttribute("secteurs", null);
				session.setAttribute("sect", null);
				request.setAttribute("sect", null);
				request.setAttribute("affichage", 0);
			}
		} catch (Exception e) {
			//System.out.println(e);
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSecteur.jsp").forward(request, response);
	}
}