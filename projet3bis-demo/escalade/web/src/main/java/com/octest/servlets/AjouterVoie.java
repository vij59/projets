package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Voie;
import org.beans.Secteur;

import dao.DaoException;
import dao.DaoFactory;
import dao.VoieDao;

/**
 * Servlet implementation class Voie
 */
public class AjouterVoie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	List<Voie> liste2Voies = new ArrayList<Voie>();

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AjouterVoie() {
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

		this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterVoie.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		request.setAttribute("secteursFinis", 0);

		Voie voie = new Voie();
		voie.setNom(request.getParameter("nom_voie"));
		voie.setCotation(request.getParameter("cotation"));
		int j = (Integer) session.getAttribute("numSecteur");

		//System.out.println(j);

		List listeSecteurs = (List) session.getAttribute("secteurs");
		Secteur secteur = new Secteur();
		secteur = (Secteur) listeSecteurs.get(j);
		try {
			char premiereLettreNom = request.getParameter("nom_voie").charAt(0);
			char premiereLettreCotation = request.getParameter("cotation").charAt(0);
			if (request.getParameter("nom_voie").isEmpty() || request.getParameter("cotation").isEmpty()
					|| premiereLettreNom == ' ' || premiereLettreCotation == ' ') {
				if (premiereLettreNom == ' ') {
					request.setAttribute("errorNom", "Voie doit avoir un nom");
				}
				if (premiereLettreCotation == ' ') {
					request.setAttribute("errorCotation", "Cotation obligatoire");
				}

			} else {
				secteur.addVoie(voie);
				liste2Voies.add(voie);
				request.setAttribute("affichage", 1);
			}
		} catch (Exception e) {
			//System.out.println("catch");
		}

		List<Voie> listeVoies = new ArrayList<Voie>();
		int i = 0;
		for (Voie v : secteur.getVoies()) {
			v.setId(i);
			v.setIdSecteur(j);
			listeVoies.add(v);
			i++;
		}

		try {
			if ((Integer.parseInt(request.getParameter("fini")) == 1) && (j < listeSecteurs.size())) {

				j++;

				List<Secteur> listeSec = new ArrayList<Secteur>();

				if (j < listeSecteurs.size()) {
					Secteur sec = (Secteur) listeSecteurs.get(j);
					listeSec.add(sec);
					request.setAttribute("sect", listeSec);
					session.setAttribute("sect", listeSec);
					request.setAttribute("affichage", 0);

				}

				listeVoies.clear();
			}
		} catch (Exception e) {
			//System.out.println("erreur");
		}

		if (j == listeSecteurs.size() - 1) {
			request.setAttribute("secteursFinis", 1);
		}

		if (j >= listeSecteurs.size()) {

			j = 0;
			session.setAttribute("numVoie", j);
			session.setAttribute("numSecteur", j);
			voie = liste2Voies.get(0);
			listeVoies.add(voie);

			Secteur sect = (Secteur) listeSecteurs.get(j);
			List<Secteur> listeSect = new ArrayList<Secteur>();
			listeSect.add(sect);

			request.setAttribute("sec", listeSect);
			session.setAttribute("sec", listeSect);

			//System.out.println(listeVoies);

			request.setAttribute("listeVoies", liste2Voies);
			session.setAttribute("listeVoies", liste2Voies);
			request.setAttribute("voies", listeVoies);
			session.setAttribute("voies", listeVoies);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLongueur.jsp").forward(request, response);
		} else {

			session.setAttribute("numSecteur", j);
			request.setAttribute("numSecteur", j);
			request.setAttribute("voies", listeVoies);
			session.setAttribute("voies", listeVoies);

			this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterVoie.jsp").forward(request, response);

		}
	}
}