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
	int i=0;


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
		
	/*
		Secteur[] secteur = new Secteur[Integer.parseInt(request.getParameter("nb_voies"))];
	for(int i=0; i<n ; i++) {
		
			secteur[i] = new Secteur();
			int id = i;
			String nom = sc.next();
			String cotation = sc.next();
			secteur[i].setId(id);
			secteur[i].setNom(nom);
			secteur[i].setCotation(cotation);
		}
		*/
		
		Secteur[] secteur = (Secteur[]) session.getAttribute("tabSecteurs");
		int i = (Integer) session.getAttribute("numSecteur");
		try {
		secteur[i] = new Secteur();
		secteur[i].setNom(request.getParameter("nom_secteur"));
		secteur[i].setCotation(request.getParameter("cotation"));
		secteur[i].setNbVoies(Integer.parseInt(request.getParameter("nb_voies")));
		secteur[i].setId(i);
		
		i++;
		}
		catch (Exception e) { System.out.println("erreur de tableau");}
		session.setAttribute("numSecteur", i);
		
        
        /*
		secteur.setNom(request.getParameter("nom_secteur"));
        secteur.setCotation(request.getParameter("cotation"));
        secteur.setNbVoies(Integer.parseInt(request.getParameter("nb_voies")));
        */
        
       List<Secteur> listeSecteurs = new ArrayList<Secteur>();
       for(int i1 =0; i1<secteur.length; i1++){
       listeSecteurs.add(secteur[i1]);
       }
       request.setAttribute("secteurs", listeSecteurs);
       session.setAttribute( "secteurs", listeSecteurs );

        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterSecteur.jsp").forward(request, response);
    }
}