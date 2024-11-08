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

import model.CategoriesDAO;
import model.Categories;

/**
 * Servlet implementation class CategoriesServlet
 */
@WebServlet("/CategoriesServlet")
public class CategoriesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private CategoriesDAO categoryDAO;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CategoriesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public void init() {
		categoryDAO = new CategoriesDAO();
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
						showNewCategories(request, response);
						break;
					case "insert":
						insertNewCategories(request, response);
						break;
					case "delete":
						deleteExistingCategories(request, response);
						break;
					case "edit":
						showEditCategories(request, response);
						break;
					case "update":
						updateExistingCategories(request, response);
						break;
					default:
						listCategories(request, response);
						break;
					}
				} catch (Exception ex) {
					throw new ServletException(ex);
				}
			}
	private void listCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {//action=default
		List<Categories> allCategories = categoryDAO.selectAllCategories();
		request.setAttribute("listCategories", allCategories);
		RequestDispatcher dispatcher = request.getRequestDispatcher("Species&CategoriesList.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewCategories(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {//action="new"
		RequestDispatcher dispatcher = request.getRequestDispatcher("CategoriesForm.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertNewCategories(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {//action="insert"
		String name = request.getParameter("name");
		Categories category = new Categories(name);
		categoryDAO.insertCategories(category);
		response.sendRedirect(request.getContextPath() + "/CategoriesServlet?action=list");
	}
	
	private void showEditCategories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Categories existingCategories = categoryDAO.selectCategories(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CategoriesForm.jsp");
        request.setAttribute("categories", existingCategories);
        dispatcher.forward(request, response);
	}
	
	private void updateExistingCategories(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        Categories category = new Categories(id, name);
        categoryDAO.updateCategories(category);
        response.sendRedirect(request.getContextPath() + "/CategoriesServlet?action=list");
    }
	
	private void deleteExistingCategories(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		categoryDAO.deleteCategories(id);
	    response.sendRedirect(request.getContextPath() +"/CategoriesServlet?action=list");
	}

}
