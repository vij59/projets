package com.octest.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.Longueur;
import org.beans.Secteur;
import org.beans.Voie;

import dao.DaoException;
import dao.DaoFactory;
import dao.LongueurDao;


/**
 * Servlet implementation class Longueur
 */
public class AjouterLongueur extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public AjouterLongueur() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		
        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLongueur.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		
		Longueur longueur = new Longueur();
		longueur.setNom(request.getParameter("nom_longueur"));
		longueur.setCotation(request.getParameter("cotation"));
		int numVoie= (Integer) session.getAttribute("numVoie");
		int numSecteur= (Integer) session.getAttribute("numSecteur");
		
		List listeSecteurs  =  (List) session.getAttribute("secteurs");
		  System.out.println("listeSecteurs : "+listeSecteurs);
		Secteur sect = (Secteur) listeSecteurs.get(numSecteur);
		  System.out.println("sect : "+sect);
		List <Secteur>listeSect = new ArrayList<Secteur>();
		listeSect.add(sect);
		
	     System.out.println("numSecteur Longueur" +numSecteur);
		System.out.println("numVoie Longueur" +numVoie);
		
		// List listeVoies  =  (List) session.getAttribute("voies");
		List listeVoies = sect.getVoies();
		Voie voie = new Voie();
		voie = (Voie) listeVoies.get(numVoie);
		voie.addLongueur(longueur);
		
		List<Longueur> listeLongueurs = new ArrayList<Longueur>();
		int i =0;
		 for( Longueur l : voie.getLongueurs()) {
			 	l.setId(i);
			 	l.setIdVoie(numVoie);
				listeLongueurs.add(l);
				i++;
	      }
		
			
   
    //  j++;
		try {
		if(Integer.parseInt(request.getParameter("fini"))==1) {
			numVoie++;
			if(numVoie>=listeVoies.size()-1) {
				if(numSecteur<=listeSecteurs.size()-1){
				numSecteur++;
				session.setAttribute( "numSecteur", numSecteur );
				request.setAttribute( "numSecteur", numSecteur );
				}
				else {
					
					 this.getServletContext().getRequestDispatcher("/WEB-INF/bonjour.jsp").forward(request, response);
				}
				
			}
					listeLongueurs.clear();
		}
		}
		catch (Exception e){
			System.out.println("erreurLongueur");
		}
		
		if(numVoie>=listeVoies.size()){ 
			 this.getServletContext().getRequestDispatcher("/WEB-INF/sites.jsp").forward(request, response);
		}
		else {
		session.setAttribute( "numVoie", numVoie );
		request.setAttribute( "numVoie", numVoie );
		request.setAttribute("longueurs", listeLongueurs);
		session.setAttribute( "longueurs", listeLongueurs );
		request.setAttribute("sec", listeSect);
	     session.setAttribute("sec", listeSect);

        this.getServletContext().getRequestDispatcher("/WEB-INF/ajouterLongueur.jsp").forward(request, response);
		}
    }
}