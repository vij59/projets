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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub


		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSecteur.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Secteur secteur = new Secteur();
		HttpSession session = request.getSession();
		Secteur secteur = new Secteur();
		secteur.setNom(request.getParameter("nom_secteur"));

		Site site = (Site) session.getAttribute("site");

		try {
			if(request.getParameter("nom_secteur").isEmpty()){ System.out.println("longueur vide");}
			else {
				site.addSecteur(secteur);
			}
		}
		catch (Exception e) {
			System.out.println("catch erreur longueur");
		}

		int i =0;
		List<Secteur> listeSecteurs = new ArrayList<Secteur>();
		for( Secteur s : site.getSecteurs()) {
			s.setId(i);
			listeSecteurs.add(s);
			i++;
		}

		request.setAttribute("secteurs", listeSecteurs);
		session.setAttribute( "secteurs", listeSecteurs );
		int j=0;
		session.setAttribute( "numSecteur", j );
		Secteur sec = (Secteur) listeSecteurs.get(0);
		List <Secteur> listeSec = new ArrayList<Secteur>();
		listeSec.add(sec);
		request.setAttribute("sect", listeSec);
		session.setAttribute("sect", listeSec);



		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSecteur.jsp").forward(request, response);
	}
}