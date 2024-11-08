package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Species;


public class SpeciesDAO {
	private String DBURL = "jdbc:mysql://localhost:3306/assessment2?serverTimezone=Australia/Melbourne";
    private String DBUsername = "root";
    private String DBPassword = "bit235";

    private String INSERTSPECIES = "INSERT INTO species (title, category_id, created_date, content, is_hidden) VALUES (?, ?, ?, ?, ?);";
    private String SELECTSPECIESID = "select title, category_id, created_date, content, is_hidden from species where id =?";
    private String SELECTALLSPECIES = "select * from SPECIES";
    private String DELETESPECIES = "delete from species where id = ?;";
    private String UPDATESPECIES = "update species set title = ?, category_id = ?, created_date=?, content = ?, is_hidden = ? where id = ?;";

    public SpeciesDAO() {}
    
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
    
    public void insertSpecies(Species specie) throws SQLException {
        System.out.println(INSERTSPECIES);
        Connection connection = null; 
    	PreparedStatement preparedStatement = null;
        // try-with-resource statement will auto close the connection.
        try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(INSERTSPECIES);
            preparedStatement.setString(1, specie.getTitle());
            preparedStatement.setInt(2, specie.getCategoryID());
            preparedStatement.setString(3, specie.getCreated_date());
            preparedStatement.setString(4, specie.getContent());
            preparedStatement.setBoolean(5, specie.isHidden());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            	printSQLException(e);
        } finally {
        	finallySQLException(connection,preparedStatement,null);
        }
    }
	
    public Species selectSpecies(int id) {
    	Species specie = null;
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // Step 1: Establishing a Connection
        try {
        	connection = getConnection();
          // Step 2:Create a statement using connection object
            preparedStatement = connection.prepareStatement(SELECTSPECIESID);
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            rs = preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String title = rs.getString("title");
                int category_id = rs.getInt("category_id");
                String created_date = rs.getString("created_date");
                String content = rs.getString("content");
                Boolean is_hidden = rs.getBoolean("is_hidden");
                specie = new Species(id, title, category_id, created_date, content, is_hidden);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return specie;
    }

    public List < Species > selectAllSpecies() {
    	Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	ResultSet rs=null;
        // using try-with-resources to avoid closing resources (boiler plate code)
        List < Species > specie = new ArrayList < > ();
        // Step 1: Establishing a Connection
        try { 
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(SELECTALLSPECIES);
            // Step 2:Create a statement using connection object
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            preparedStatement.executeQuery();
            // Step 4: Process the ResultSet object.
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
            	int id = rs.getInt("id");
                String title = rs.getString("title");
                int category_id = rs.getInt("category_id");
                String created_date = rs.getString("created_date");
                String content = rs.getString("content");
                Boolean is_hidden = rs.getBoolean("is_hidden");
                Species speciee = new Species(id, title, category_id, created_date, content, is_hidden);
                specie.add(speciee);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        finally {
        	finallySQLException(connection,preparedStatement,rs);
        }
        return specie;
    }
    
    public boolean deleteSpecies(int id) throws SQLException {
        boolean speciesDeleted = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	try {
        	 connection = getConnection(); 
        	 preparedStatement = connection.prepareStatement(DELETESPECIES);
        	 preparedStatement.setInt(1, id);
             speciesDeleted = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return speciesDeleted;
    }   
    
    public boolean updateSpecies (Species specie) throws SQLException {
        boolean speciesUpdated = false;
        Connection connection = null; 
      	PreparedStatement preparedStatement = null;
      	try {
        	connection = getConnection(); 
        	preparedStatement = connection.prepareStatement(UPDATESPECIES);
        	preparedStatement.setString(1, specie.getTitle());
        	preparedStatement.setInt(2, specie.getCategoryID());
          	preparedStatement.setString(3, specie.getCreated_date());
        	preparedStatement.setString(4, specie.getContent());
        	preparedStatement.setBoolean(5, specie.isHidden());
        	preparedStatement.setInt(6, specie.getSpeciesID());

        	speciesUpdated = preparedStatement.executeUpdate() > 0 ? true:false;
        }
        catch (SQLException e) {
        	printSQLException (e);
        }     
      	finally {
        	finallySQLException(connection,preparedStatement,null);
        }
        return speciesUpdated;
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
