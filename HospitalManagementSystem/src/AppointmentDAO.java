import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String host = "jdbc:mysql://localhost:3306/Hospital";
	static final String dbUsername = "root";
	static final String dbPassword = "";
	
	InputStream input;
	
	public AppointmentDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	//| Appointment_ID | Date       | Time | OR_ID | Patient_ID | Operation_ID | Doctor_ID | Nurse_ID |

	
	public void addAppointment(Appointment p) throws ClassNotFoundException {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		      // the mysql insert statement
		      String query = "insert into Appointment(Date, Time, OR_ID, Patient_ID, Operation_ID, Doctor_ID, Nurse_ID)"
		        + " values (?, ?, ?, ?, ?, ?, ?)";
		      
		      java.util.Date utilStartDate = p.getDate();
		      java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		      
		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		  
		      preparedStmt.setDate(1, sqlStartDate);
		      preparedStmt.setInt(2, p.getTime());
		      preparedStmt.setInt(3, p.getOr_id());
		      preparedStmt.setInt(4, p.getPatient_id());
		      preparedStmt.setInt(5, p.getOperation_id());
		      preparedStmt.setInt(6, p.getDoctor_id());
		      preparedStmt.setInt(7, p.getNurse_id());
		      System.out.println("********");
		      System.out.println(preparedStmt);

		      // execute the preparedstatement
		      preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	public int checkForORTimeConflicts(String date, int time, int duration, int orId) {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		   String statementString = "";
		   statementString += "SELECT Appointment_ID FROM Appointment WHERE ";
		   statementString += "OR_ID = " + orId + " AND Date = '" + date + "' AND ";
		   statementString += "(Time BETWEEN " + (String.valueOf(time-duration));
		   statementString += " AND " + (String.valueOf(time+duration)) + ")";
		   System.out.println(statementString);
		   preparedStatement = connection.prepareStatement(statementString);
		   
		   resultSet = preparedStatement.executeQuery();
			
		   if (!resultSet.next())
			   return -1;
		   else 
			   return resultSet.getInt(1);
			   
			
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return -100;
		}
	}
	
	public int checkForDoctorTimeConflicts(String date, int time, int duration, int dId) {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		   String statementString = "";
		   statementString += "SELECT Appointment_ID FROM Appointment WHERE ";
		   statementString += "Doctor_ID = " + dId + " AND Date = '" + date + "' AND ";
		   statementString += "(Time BETWEEN " + (String.valueOf(time-duration));
		   statementString += " AND " + (String.valueOf(time+duration)) + ")";
		   System.out.println(statementString);
		   preparedStatement = connection.prepareStatement(statementString);
		   
		   resultSet = preparedStatement.executeQuery();
			
		   if (!resultSet.next())
			   return -1;
		   else 
			   return resultSet.getInt(1);
			   
			
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return -100;
		}
	}
	
	public int checkForNurseTimeConflicts(String date, int time, int duration, int nurseId) {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
		   String statementString = "";
		   statementString += "SELECT Appointment_ID FROM Appointment WHERE ";
		   statementString += "Nurse_ID = " + nurseId + " AND Date = '" + date + "' AND ";
		   statementString += "(Time BETWEEN " + (String.valueOf(time-duration));
		   statementString += " AND " + (String.valueOf(time+duration)) + ")";
		   System.out.println(statementString);
		   preparedStatement = connection.prepareStatement(statementString);
		   
		   resultSet = preparedStatement.executeQuery();
			
		   if (!resultSet.next())
			   return -1;
		   else 
			   return resultSet.getInt(1);
			   
			
		} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				return -100;
		}
	}
	
	public Appointment getAppointment(int id ) throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Appointment WHERE Appointment_ID =" + id);
			resultSet = preparedStatement.executeQuery();
			Appointment emp = null;
			while(resultSet.next()) {
				emp = new Appointment(id, 
									resultSet.getDate(1), 
									resultSet.getInt(2),
									resultSet.getInt(3),
									resultSet.getInt(4),
									resultSet.getInt(5),
									resultSet.getInt(6),
									resultSet.getInt(7)); 
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
	
	public List<Appointment> getAllAppointments() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM Appointment");
			resultSet = preparedStatement.executeQuery();
			
			List<Appointment> allEmps = new ArrayList<Appointment>();
			while(resultSet.next()) {
				Appointment emp = new Appointment(resultSet.getInt(1), 
						resultSet.getDate(2), 
						resultSet.getInt(3),
						resultSet.getInt(4),
						resultSet.getInt(5),
						resultSet.getInt(6),
						resultSet.getInt(7),
						resultSet.getInt(8));  
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

	public void updateAppointment(Appointment p) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    
		    java.util.Date utilStartDate = p.getDate();
		      java.sql.Date sqlStartDate = new java.sql.Date(utilStartDate.getTime());
		    //SQL Query
		    String updatequery="UPDATE Appointment SET "
		    								+ "Date="+ "\"" + sqlStartDate + "\""
		    								+ ", Time=" + p.getTime()
		    								+ ", OR_ID=" + p.getOr_id()
		    								+ ", Patient_ID=" + p.getPatient_id()
		    								+ ", Operation_ID="+ p.getOperation_id()
		    								+ ", Doctor_ID="+ p.getDoctor_id()
		    								+ ", Nurse_ID="+ p.getNurse_id()
		    								+ " Where Appointment_ID=" + p.getId();
		    //Run Query
		    statement.executeUpdate(updatequery);
	    } catch (SQLException e3) {
		    System.err.println("Cannot connect to database ! ");
		    e3.printStackTrace();
	    } finally {
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
	public void deleteAppointment(int id) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Appointment WHERE Appointment_ID=" + id;
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
