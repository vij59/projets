package com.octest.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.beans.Topo;


import dao.DaoException;
import dao.DaoFactory;
import dao.TopoDao;


/**
 * Servlet implementation class Topos
 */
public class Topos extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopoDao topoDao;

    public void init() throws ServletException {
        DaoFactory daoFactory = DaoFactory.getInstance();
        this.topoDao = daoFactory.getTopoDao();
    }
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Topos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		  
        try {
			request.setAttribute("topos", topoDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        this.getServletContext().getRequestDispatcher("/WEB-INF/topos.jsp").forward(request, response);
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Topo topo = new Topo();
        
	
		 topo.setNomTopo(request.getParameter("nom_topo"));
		
        topo.setFichier(request.getParameter("fichier"));
        
        try {
        	topoDao.ajouterTopo(topo);
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        	
        try {
			request.setAttribute("topos", topoDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	
        
        this.getServletContext().getRequestDispatcher("/WEB-INF/topos.jsp").forward(request, response);
    }
}