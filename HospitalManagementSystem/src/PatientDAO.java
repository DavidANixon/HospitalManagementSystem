import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String host = "jdbc:mysql://localhost:3306/Hospital";
	static final String dbUsername = "root";
	static final String dbPassword = "";
	
	InputStream input;
	
	public PatientDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addPatient(Patient p) throws ClassNotFoundException {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		      // the mysql insert statement
		      String query = "insert into Patient(Name, DOB, Address, Phone, Illness)"
		        + " values (?, ?, ?, ?, ?)";
		      
		      java.util.Date utilStartDate = p.getDOB();
		      java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		      
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, p.getName());
		      preparedStmt.setDate(2, sqlStartDate);
		      preparedStmt.setString(3, p.getAddress());
		      preparedStmt.setString(4, p.getPhoneNumber());
		      preparedStmt.setString(5, p.getIllness());

		      // execute the preparedstatement
		      preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public Patient getPatient(int id) throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Patient WHERE Patient_ID =" + id);
			resultSet = preparedStatement.executeQuery();
			Patient emp = null;
			while(resultSet.next()) {
				emp = new Patient(id, 
									resultSet.getString(2), 
									resultSet.getDate(3), 
									resultSet.getString(4), 
									resultSet.getString(5), 
									resultSet.getString(6)); 
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
	
	public List<Patient> getAllPatients() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Patient");
			resultSet = preparedStatement.executeQuery();
			
			List<Patient> allEmps = new ArrayList<Patient>();
			while(resultSet.next()) {
				Patient emp = new Patient(resultSet.getInt(1), 
						resultSet.getString(2), 
						resultSet.getDate(3), 
						resultSet.getString(4), 
						resultSet.getString(5), 
						resultSet.getString(6)); 
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

	public void updatePatient(Patient p) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    
		    java.util.Date utilStartDate = p.getDOB();
		      java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		    //SQL Query
		    String updatequery="UPDATE Patient SET "
		    								+ "Name="+ "\"" + p.getName() + "\""
		    								+ ", DOB=" + "\"" + sqlStartDate + "\""
		    								+ ", Address=" + "\"" + p.getAddress() + "\""
		    								+ ", Phone=" + "\"" + p.getPhoneNumber() + "\""
		    								+ ", Illness="+ "\"" + p.getIllness()+ "\""
		    								+ " Where Patient_ID=" + p.getId();
		    //Run Query
		    statement.executeUpdate(updatequery);
	    } catch (SQLException e3) {
		    System.err.println("Cannot connect to database ! ");
		    e3.printStackTrace();
	    } finally {
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
	public void deletePatient(int id) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Patient WHERE Patient_ID=" + id;
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
