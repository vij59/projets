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
public class SupprimerSecteur extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SupprimerSecteur() {
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

		Site site = (Site) session.getAttribute("site");

		Secteur secteur = new Secteur();

		int i = 0;
		List<Secteur> listeSecteurs = new ArrayList<Secteur>();
		for (Secteur s : site.getSecteurs()) {
			s.setId(i);
			listeSecteurs.add(s);
			i++;
		}
		for (Secteur sect : listeSecteurs) {
			if (sect.getId() == Integer.parseInt(request.getParameter("secteurToDelete"))) {
				listeSecteurs.remove(sect);
				site.removeSecteur(sect);
			}
		}

		request.setAttribute("secteurs", listeSecteurs);
		session.setAttribute("secteurs", listeSecteurs);
		request.setAttribute("site", site);
		session.setAttribute("site", site);

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSecteur.jsp").forward(request, response);
	}
}