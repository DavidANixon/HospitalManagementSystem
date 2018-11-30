import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperationDAO {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String host = "jdbc:mysql://localhost:3306/Hospital";
	static final String dbUsername = "root";
	static final String dbPassword = "";
	
	InputStream input;
	
	public OperationDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOperation(Operation o) throws ClassNotFoundException {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		      // the mysql insert statement
		      String query = "insert into Operation(Name, Illness, Specialty, Duration)"
		        + " values (?, ?, ?, ?)";
		      

		//| Operation_ID | Name    | Illness      | Specialty       | Duration
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, o.getName());
		      preparedStmt.setString(2, o.getIllness());
		      preparedStmt.setString(3, o.getSpecialty());
		      preparedStmt.setInt(4, o.getDuration());

		      // execute the preparedstatement
		      preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Operation getOperation(int id ) throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Operation WHERE Operation_ID =" + id);
			resultSet = preparedStatement.executeQuery();
			Operation emp = null;
			while(resultSet.next()) {
				emp = new Operation(id, 
									resultSet.getString(2), 
									resultSet.getString(3), 
									resultSet.getString(4), 
									resultSet.getInt(5)); 
			}
			return emp;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     preparedStatement.close();
		     if (connection != null){
		    	 try{
		    		 connection.close();
		    	 }catch (SQLException ignore){}
		     }
		 }
		return null;
	}
	
	public List<Operation> getAllOperations() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Operation");
			resultSet = preparedStatement.executeQuery();
			
			List<Operation> allEmps = new ArrayList<Operation>();
			while(resultSet.next()) {
				Operation emp = new Operation(resultSet.getInt(1), 
						resultSet.getString(2), 
						resultSet.getString(3), 
						resultSet.getString(4), 
						resultSet.getInt(5));  
				allEmps.add(emp);
			}			
			return allEmps;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     if(preparedStatement != null) {
		    	 preparedStatement.close();
		     }
		     if (connection != null){
		    	 try{
		    		 connection.close();
		    	 }catch (SQLException ignore){}
		     }
		 }
		return null;
	}

	public void updateOperation(Operation p) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    
		    //SQL Query
		    String updatequery="UPDATE Operation SET "
		    								+ "Name="+ "\"" + p.getName() + "\""
		    								+ ", Illness=" + "\"" + p.getIllness() + "\""
		    								+ ", Specialty=" + "\"" + p.getSpecialty() + "\""
		    								+ ", Duration=" + p.getDuration()
		    								+ " Where Operation_ID=" + p.getId();
		    //Run Query
		    statement.executeUpdate(updatequery);
	    } catch (SQLException e3) {
		    System.err.println("Cannot connect to database ! ");
		    e3.printStackTrace();
	    } finally {
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
	public void deleteOperation(int id) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Operation WHERE Operation_ID=" + id;
		    //Run Query
		    statement.executeUpdate(deletequery);
	    } catch (SQLException e4) {
		    System.err.println("Cannot connect to database ! ");
		    e4.printStackTrace();
	    } finally {
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
}
