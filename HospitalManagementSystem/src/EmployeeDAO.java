import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.util.*;
import java.sql.*;

public class EmployeeDAO {
	private Connection connection = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	static final String host = "jdbc:mysql://localhost:3306/Hospital";
	static final String dbUsername = "root";
	static final String dbPassword = "";
	
	InputStream input;
	
	public EmployeeDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addEmployee(Employee e) throws ClassNotFoundException {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);


		      // the mysql insert statement
		      String query = "insert into Employee(Name, Age, Phone, Office, Specialty)"
		        + " values (?, ?, ?, ?, ?)";

		      // create the mysql insert preparedstatement
		      PreparedStatement preparedStmt = connection.prepareStatement(query);
		      preparedStmt.setString(1, e.getName());
		      preparedStmt.setInt(2, e.getAge());
		      preparedStmt.setString(3, e.getPhone());
		      preparedStmt.setString(4, e.getOffice());
		      preparedStmt.setString(5, e.getSpecialty());

		      // execute the preparedstatement
		      preparedStmt.execute();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	}
	
	
	public Employee getEmployee(int id ) throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE Employee_ID =" + id);
			resultSet = preparedStatement.executeQuery();
			Employee emp = null;
			while(resultSet.next()) {
				emp = new Employee(id, 
										resultSet.getString(2), 
										resultSet.getInt(3), 
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
	
	public List<Employee> getAllEmployees() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE");
			resultSet = preparedStatement.executeQuery();
			
			List<Employee> allEmps = new ArrayList<Employee>();
			while(resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt(1), 
						resultSet.getString(2), 
						resultSet.getInt(3), 
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
	
	public List<Employee> getAllDoctors() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE NOT Speciality = 'Nurse' ");
			resultSet = preparedStatement.executeQuery();
			
			List<Employee> allEmps = new ArrayList<Employee>();
			while(resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt(1), 
						resultSet.getString(2), 
						resultSet.getInt(3), 
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
		     preparedStatement.close();
		     if (connection != null){
		    	 try{
		    		 connection.close();
		    	 }catch (SQLException ignore){}
		     }
		 }
		return null;
	}
	
	public List<Employee> getAllNurses() throws Exception {
		try {
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE Speciality = 'Nurse' ");
			resultSet = preparedStatement.executeQuery();
			
			List<Employee> allEmps = new ArrayList<Employee>();
			while(resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt(1), 
						resultSet.getString(2), 
						resultSet.getInt(3), 
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
		     preparedStatement.close();
		     if (connection != null){
		    	 try{
		    		 connection.close();
		    	 }catch (SQLException ignore){}
		     }
		 }
		return null;
	}

	public void updateEmployee(Employee e) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String updatequery="UPDATE Employee SET "
		    								+ "Name="+ "\"" + e.getName()+ "\""
		    								+ ", Age=" + e.getAge()
		    								+ ", Phone=" + "\"" + e.getPhone() + "\""
		    								+ ", Office=" + "\"" + e.getOffice() + "\""
		    								+ ", Specialty="+ "\"" + e.getSpecialty()+ "\""
		    								+ " Where Employee_ID=" + e.getId();
		    //Run Query
		    statement.executeUpdate(updatequery);
	    } catch (SQLException e3) {
		    System.err.println("Cannot connect to database ! ");
		    e3.printStackTrace();
	    } finally {
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
	public void deleteEmployee(int id) throws ClassNotFoundException {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Employee WHERE Employee_ID=" + id;
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