package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Species;
import model.SpeciesDAO;

/**
 * Servlet implementation class SpeciesServlet
 */
@WebServlet("/SpeciesServlet")
public class SpeciesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SpeciesDAO specieDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpeciesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    @Override
	public void init() {
		specieDAO = new SpeciesDAO();
	}
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String action = request.getParameter("action");
		if (action == null) {
			action = "No action";
		}
		try {
			switch (action) {
			case "new":
				showNewSpecies(request, response);
				break;
			case "insert":
				insertNewSpecies(request, response);
				break;
			case "delete":
				deleteExistingSpecies(request, response);
				break;
			case "edit":
				showEditSpecies(request, response);
				break;
			case "update":
				updateExistingSpecies(request, response);
				break;
			default:
				listSpecies(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listSpecies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=default
		List<Species> allSpecies = specieDAO.selectAllSpecies();
		request.setAttribute("listSpecies", allSpecies);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Species&CategoriesList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewSpecies(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//action="new"
		RequestDispatcher dispatcher = request.getRequestDispatcher("Species&CategoriesList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertNewSpecies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {//action="insert"
		String title = request.getParameter("Title");
		int categoryID = Integer.parseInt(request.getParameter("CategoryID"));
		String dateCreated = request.getParameter("CreatedDate");
		String content = request.getParameter("Content");
		Boolean isHidden = Boolean.parseBoolean(request.getParameter("is_hidden"));
		Species specie = new Species(title, categoryID, dateCreated, content, isHidden);
		specieDAO.insertSpecies(specie);
		response.sendRedirect(request.getContextPath() + "/SpeciesServlet?action=list");
	}
	
	private void showEditSpecies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {//action="edit"
		int id = Integer.parseInt(request.getParameter("id"));
		Species existingSpecies = specieDAO.selectSpecies(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("SpeciesForm.jsp");
		request.setAttribute("species", existingSpecies);
		dispatcher.forward(request, response);
	}
	
	private void updateExistingSpecies(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {//action="update"
		int id = Integer.parseInt(request.getParameter("id"));
		String title = request.getParameter("Title");
		int categoryID = Integer.parseInt(request.getParameter("CategoryID"));
		String dateCreated = request.getParameter("CreatedDate");
		String content = request.getParameter("Content");
		Boolean isHidden = Boolean.parseBoolean(request.getParameter("is_hidden"));
		Species specie = new Species(id, title, categoryID, dateCreated, content, isHidden);
		specieDAO.updateSpecies(specie);
		response.sendRedirect(request.getContextPath() + "/SpeciesServlet?action=list");
	}
	
	private void deleteExistingSpecies(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		specieDAO.deleteSpecies(id);
	    response.sendRedirect(request.getContextPath() +"/SpeciesServlet?action=list");
	}
}
