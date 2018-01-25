package com.octest.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.beans.BeanException;
import org.beans.Reservation;
import org.beans.Topo;
import org.beans.Utilisateur;

import dao.CommentaireDao;
import dao.DaoException;
import dao.DaoFactory;
import dao.ReservationDao;
import dao.SiteDao;
import dao.TopoDao;
import dao.UtilisateurDao;

/**
 * Servlet implementation class Topos
 */
public class MesReservations extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopoDao topoDao;
	private SiteDao siteDao;
	private ReservationDao reservationDao;
	private CommentaireDao commentaireDao;
	private UtilisateurDao utilisateurDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.topoDao = daoFactory.getTopoDao();
		this.siteDao = daoFactory.getSiteDao();
		this.reservationDao = daoFactory.getReservationDao();
		this.commentaireDao = daoFactory.getCommentaireDao();
		this.utilisateurDao = daoFactory.getUtilisateurDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MesReservations() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String servletPath = request.getServletPath();
		//System.out.println(" servlet path : " + servletPath);
		session.setAttribute("redirection", servletPath);

		try {
			request.setAttribute("sites", siteDao.lister());
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

		try {
			request.setAttribute("reservations", reservationDao.lister());
			request.setAttribute("utilisateurs", utilisateurDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/mesReservations.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		try {
			int idTopo = Integer.parseInt(request.getParameter("idTopo"));
			reservationDao.terminerReservation(idTopo);
			topoDao.rendreTopo(idTopo);
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			request.setAttribute("topos", topoDao.lister());
			request.setAttribute("reservations", reservationDao.lister());
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			if (request.getParameter("formVal").equals("1")) {
				int idTopoBis = Integer.parseInt(request.getParameter("idTopoBis"));
				request.setAttribute("top", idTopoBis);
				session.setAttribute("top", idTopoBis);
				try {
					session.setAttribute("commentaires", commentaireDao.lister());
					request.setAttribute("commentaires", commentaireDao.lister());
					request.setAttribute("utilisateurs", utilisateurDao.lister());
				} catch (DaoException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.getServletContext().getRequestDispatcher("/WEB-INF/commentaireTopo.jsp").forward(request,
						response);
			}

			else {
				this.getServletContext().getRequestDispatcher("/WEB-INF/mesReservations.jsp").forward(request,
						response);
			}
		} finally {
			this.getServletContext().getRequestDispatcher("/WEB-INF/mesReservations.jsp").forward(request, response);
		}
	}
}