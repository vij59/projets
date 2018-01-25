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

import dao.DaoException;
import dao.DaoFactory;
import dao.ReservationDao;
import dao.SiteDao;
import dao.TopoDao;

/**
 * Servlet implementation class Topos
 */
public class ReserverTopo extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private TopoDao topoDao;
	private SiteDao siteDao;
	private ReservationDao reservationDao;

	public void init() throws ServletException {
		DaoFactory daoFactory = DaoFactory.getInstance();
		this.topoDao = daoFactory.getTopoDao();
		this.siteDao = daoFactory.getSiteDao();
		this.reservationDao = daoFactory.getReservationDao();
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ReserverTopo() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/reserverTopo.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();

		Topo topo = new Topo();
		Reservation reservation = new Reservation();

		int idSite = Integer.parseInt(request.getParameter("id_site"));
		topo.setIdTopo(Integer.parseInt(request.getParameter("id_topo")));
		topo.setNomTopo(request.getParameter("nom_topo"));
		topo.setFichier(request.getParameter("fichier"));
		topo.setDisponible(Boolean.parseBoolean(request.getParameter("dispo")));

		topo.setIdSite(idSite);

		try {
			if ((Integer.parseInt(request.getParameter("fini")) == 1)
					&& (Boolean.parseBoolean(request.getParameter("dispo")) == true)) {

				reservation.setIdTopo(topo.getIdTopo());
				//System.out.println(topo.getIdTopo());
				Utilisateur utilisateur = (Utilisateur) session.getAttribute("sessionUtilisateur");
				//System.out.println(utilisateur.getId());
				reservation.setIdUtilisateur(utilisateur.getId());

				try {
					reservationDao.ajouterReservation(reservation);
					topo.setDisponible(false);

					List<Topo> listeTopos = topoDao.lister();

					for (Topo topo2 : listeTopos) {
						if (topo2.getIdTopo() == (topo.getIdTopo())) {
							((TopoDao) topoDao).reserverTopo(topo.getIdTopo());
						}
					}

				} catch (DaoException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		} catch (Exception e) {

		}

		request.setAttribute("topo", topo);
		session.setAttribute("topo", topo);

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
		} catch (DaoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.getServletContext().getRequestDispatcher("/WEB-INF/reserverTopo.jsp").forward(request, response);
	}
}