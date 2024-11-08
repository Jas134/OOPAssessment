package model;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Categories;
import model.Species;

	public class CategoriesDAO {
		private String DBURL = "jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne";
	    private String DBUsername = "root";
	    private String DBPassword = "bit235";

	    private String INSERTCATEGORIES = "INSERT INTO categories (id, name) VALUES (?, ?);";
	    private String SELECTCATEGORIES = "select id, name FROM categories where id= ?";
	    private String SELECTALLCATEGORIES = "SELECT * FROM categories";
	    private String DELETECATEGORIES = "DELETE FROM categories WHERE id= ?;";
	    private String UPDATESPECIES = "UPDATE species SET category_id = 1 WHERE category_id = ?";
	    private String UPDATECATEGORIES = "UPDATE categories SET name= ? WHERE id = ?;";

	    public CategoriesDAO() {}
	    
	    protected Connection getConnection() {
	        Connection connection = null;
	        try {
	            Class.forName("com.mysql.jdbc.Driver");
	            connection = DriverManager.getConnection(DBURL, DBUsername, DBPassword);
	        } catch (SQLException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (ClassNotFoundException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        return connection;
	    }
	    
	    public void insertCategories(Categories category) throws SQLException {
	        System.out.println(INSERTCATEGORIES);
	        Connection connection = null; 
	    	PreparedStatement preparedStatement = null;
	        // try-with-resource statement will auto close the connection.
	        try {
	        	connection = getConnection(); 
	        	preparedStatement = connection.prepareStatement(INSERTCATEGORIES);
	            preparedStatement.setInt(1, category.getId());
	            preparedStatement.setString(2, category.getName());
	            System.out.println(preparedStatement);
	            preparedStatement.executeUpdate();
	        } catch (SQLException e) {
	            	printSQLException(e);
	        } finally {
	        	finallySQLException(connection,preparedStatement,null);
	        }
	    }
		
	    public Categories selectCategories(int id) {
	    	Categories category = null;
	    	Connection connection = null; 
	      	PreparedStatement preparedStatement = null;
	      	ResultSet rs=null;
	        // Step 1: Establishing a Connection
	        try {
	        	connection = getConnection();
	          // Step 2:Create a statement using connection object
	            preparedStatement = connection.prepareStatement(SELECTCATEGORIES);
	            preparedStatement.setInt(1, id);
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            rs = preparedStatement.executeQuery();
	            // Step 4: Process the ResultSet object.
	            while (rs.next()) {
	                String name = rs.getString("name");
	                category = new Categories(id, name);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        finally {
	        	finallySQLException(connection,preparedStatement,rs);
	        }
	        return category;
	    }

	    public List < Categories > selectAllCategories() {
	    	Connection connection = null; 
	      	PreparedStatement preparedStatement = null;
	      	ResultSet rs=null;
	        // using try-with-resources to avoid closing resources (boiler plate code)
	        List < Categories > category = new ArrayList < > ();
	        // Step 1: Establishing a Connection
	        try { 
	        	connection = getConnection(); 
	        	preparedStatement = connection.prepareStatement(SELECTALLCATEGORIES);
	            // Step 2:Create a statement using connection object
	            System.out.println(preparedStatement);
	            // Step 3: Execute the query or update query
	            preparedStatement.executeQuery();
	            // Step 4: Process the ResultSet object.
	            rs = preparedStatement.executeQuery();
	            while (rs.next()) {
	            	int id = rs.getInt("id");
	                String name = rs.getString("name");
	                Categories categoree = new Categories(id, name);
	                category.add(categoree);
	            }
	        } catch (SQLException e) {
	            printSQLException(e);
	        }
	        finally {
	        	finallySQLException(connection,preparedStatement,rs);
	        }
	        return category;
	    }
	    
	    public boolean deleteCategories(int id) throws SQLException {
	        boolean CategoriesDeleted = false;
	        Connection connection = null; 
	        PreparedStatement species = null;
	      	PreparedStatement preparedStatement = null;
	      	try {
	        	 connection = getConnection(); 
	        	 species = connection.prepareStatement(UPDATESPECIES);
	        	 species.setInt(1, id);
	        	 species.executeUpdate();
	        	 preparedStatement = connection.prepareStatement(DELETECATEGORIES);
	        	 preparedStatement.setInt(1, id);
	             CategoriesDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
	        }
	        finally {
	        	finallySQLException(connection,preparedStatement,null);
	        }
	        return CategoriesDeleted;
	    }   
	    
	    public boolean updateCategories (Categories category) throws SQLException {
	        boolean CategoriesUpdated = false;
	        Connection connection = null; 
	      	PreparedStatement preparedStatement = null;
	      	try {
	        	connection = getConnection(); 
	        	preparedStatement = connection.prepareStatement(UPDATECATEGORIES);
	        	preparedStatement.setString(1, category.getName());
	        	preparedStatement.setInt(2, category.getId());

	        	CategoriesUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
	        }
	        catch (SQLException e) {
	        	printSQLException (e);
	        }     
	      	finally {
	        	finallySQLException(connection,preparedStatement,null);
	        }
	        return CategoriesUpdated;
	    }
	    
		private void printSQLException(SQLException ex) {
	        for (Throwable e: ex) {
	            if (e instanceof SQLException) {
	                e.printStackTrace(System.err);
	                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
	                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
	                System.err.println("Message: " + e.getMessage());
	                Throwable t = ex.getCause();
	                while (t != null) {
	                    System.out.println("Cause: " + t);
	                    t = t.getCause();
	                }
	            }
	        }
	    }
		
		private void finallySQLException(Connection c, PreparedStatement p, ResultSet r){
	   	 if (r != null)	{
	            try {
	               r.close();
	            } catch (Exception e) {}
	               r = null;
	            }
		
	         if (p != null) {
	            try {
	               p.close();
	            } catch (Exception e) {}
	               p = null;
	            }
	         if (c != null) {
	            try {
	               c.close();
	            } catch (Exception e) {
	          	  c = null;
	            }
	         }
	   }

	}