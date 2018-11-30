import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OperatingRoomDAO {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String host = "jdbc:mysql://localhost:3306/Hospital";
	static final String dbUsername = "root";
	static final String dbPassword = "";
	
	InputStream input;
	
	public OperatingRoomDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addOperatingRoom(OperatingRoom p) throws ClassNotFoundException {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		      // the mysql insert statement
		      String query = "insert into Operating_Room(Building, Room_Number)"
		        + " values (?, ?)";
		     
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, p.getBuilding());
		      preparedStmt.setInt(2, p.getRoomNumber());
		   

		      // execute the preparedstatement
		      preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public OperatingRoom getOperatingRoom(int id ) throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Operating_Room WHERE OR_ID =" + id);
			resultSet = preparedStatement.executeQuery();
			OperatingRoom emp = null;
			while(resultSet.next()) {
				emp = new OperatingRoom(id, 
									resultSet.getString(2), 
									resultSet.getInt(3)); 
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
	
	public List<OperatingRoom> getAllOperatingRooms() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Operating_Room");
			resultSet = preparedStatement.executeQuery();
			
			List<OperatingRoom> allEmps = new ArrayList<OperatingRoom>();
			while(resultSet.next()) {
				OperatingRoom emp = new OperatingRoom(resultSet.getInt(1), 
						resultSet.getString(2), 
						resultSet.getInt(3)); 
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

	public void updateOperatingRoom(OperatingRoom p) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    
		    //SQL Query
		    String updatequery="UPDATE Operating_Room SET "
		    								+ "Building="+ "\"" + p.getBuilding() + "\""
		    								+ ", Room_Number=" + "\"" + p.getRoomNumber() + "\""
		    								+ " Where OR_ID=" + p.getId();
		    //Run Query
		    statement.executeUpdate(updatequery);
	    } catch (SQLException e3) {
		    System.err.println("Cannot connect to database ! ");
		    e3.printStackTrace();
	    } finally {
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
	public void deleteOperatingRoom(int id) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Operating_Room WHERE OR_ID=" + id;
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
