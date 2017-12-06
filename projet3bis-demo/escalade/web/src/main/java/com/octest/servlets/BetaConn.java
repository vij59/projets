package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.BeanException;
import org.beans.Pseudo;
import org.beans.Utilisateur;

import dao.DaoException;
import dao.DaoFactory;
import dao.UtilisateurDao;

/**
 * Servlet implementation class CreerUtilisateur
 */
public class BetaConn extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UtilisateurDao utilisateurDao;
	public static final String ATT_SESSION_USER = "sessionUtilisateur";
	public static final String ATT_USER         = "utilisateur";

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.utilisateurDao = daoFactory.getUtilisateurDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BetaConn() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        try {
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/betaConn.jsp").forward(request, response);
    }

    public void doPost( HttpServletRequest request, HttpServletResponse response ) throws ServletException, IOException {
        Utilisateur utilisateur = new Utilisateur();
       
       
			try {
				if ((utilisateurDao.validerMail(request.getParameter("mail")) == false))
				{
					System.out.println("mail OK");
					if ((utilisateurDao.validerMdp(request.getParameter("mail"), request.getParameter("mdp"))== true)) 
						{
     
						utilisateur.setMail(request.getParameter("mail"));
						utilisateur.setMdp(request.getParameter("mdp"));
						
						System.out.println("mot de passe concorde avec mail");
						 HttpSession session = request.getSession();
						  session.setAttribute( ATT_SESSION_USER, utilisateur );
						  request.setAttribute( ATT_USER, utilisateur );
						
						}
					else 
					{
						System.out.println("erreur de mot de passe");
						request.setAttribute("errorMdp", "erreur de mot de passe");
					}
				}
				else 
				{
					System.out.println("mail pas bon");
					request.setAttribute("errorMail", "Mail pas bon");
				}
			} catch (DaoException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//fin du if validerMail
			
			
       
        
      
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/betaConn.jsp").forward(request, response);
    }
}
