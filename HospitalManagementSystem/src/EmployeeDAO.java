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
	//Had a slash before -- try now with colon.           vv
	//or maybe need to do IP:3306/team17 because file paths are a thing
	//Also the database name is team17 not employee...
	static final String host = "jdbc:mysql://team17@129.22.23.135:3306/Employee";
	static final String dbUsername = "team17";
	static final String dbPassword = "team17";
	
	InputStream input;
	
	
	public void addEmployee(Employee e) {
		try {
			//SQL statement: 
		    preparedStatement = connection.prepareStatement("insert into  Employee values (default, ?, ?, ?, ?, ?)");
		    System.out.println("Adding " + e.getName() + " to employee table");
		    
		    //Add values here: 
		    preparedStatement.setString(1, e.getName());
		    preparedStatement.setInt(2, e.getAge());
		    preparedStatement.setLong(3, e.getPhoneNumber());
		    preparedStatement.setString(4, e.getOffice());
		    preparedStatement.setString(5, e.getSpeciality());
		    preparedStatement.executeUpdate();
		    System.out.println("Employee Added!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("We've got an error");
			e1.printStackTrace();
		}
	}
	
	
	public Employee getEmployee(Employee e) throws Exception {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE id =" + e.getId());
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			Employee emp = new Employee(resultSet.getInt(0), 
										resultSet.getString(1), 
										resultSet.getInt(2), 
										resultSet.getLong(3), 
										resultSet.getString(4), 
										resultSet.getString(5)); 

			while(resultSet.next()) {
				System.out.println("Name: " + resultSet.getString(1));
			}
			return emp;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     System.out.println("Closing the connection.");
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
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE");
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			
			List<Employee> allEmps = new ArrayList<Employee>();
			while(resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt(0), 
						resultSet.getString(1), 
						resultSet.getInt(2), 
						resultSet.getLong(3), 
						resultSet.getString(4), 
						resultSet.getString(5)); 
				allEmps.add(emp);
			}
			
			System.out.println("Found " + allEmps.size() + " employees!");
			
			return allEmps;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     System.out.println("Closing the connection.");
		     preparedStatement.close();
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
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE NOT speciality = 'Nurse' ");
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			
			List<Employee> allEmps = new ArrayList<Employee>();
			while(resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt(0), 
						resultSet.getString(1), 
						resultSet.getInt(2), 
						resultSet.getLong(3), 
						resultSet.getString(4), 
						resultSet.getString(5)); 
				allEmps.add(emp);
			}
			
			System.out.println("Found " + allEmps.size() + " doctors!");
			
			return allEmps;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     System.out.println("Closing the connection.");
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
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE speciality = 'Nurse' ");
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			
			List<Employee> allEmps = new ArrayList<Employee>();
			while(resultSet.next()) {
				Employee emp = new Employee(resultSet.getInt(0), 
						resultSet.getString(1), 
						resultSet.getInt(2), 
						resultSet.getLong(3), 
						resultSet.getString(4), 
						resultSet.getString(5)); 
				allEmps.add(emp);
			}
			
			System.out.println("Found " + allEmps.size() + " Nurses!");
			
			return allEmps;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     System.out.println("Closing the connection.");
		     preparedStatement.close();
		     if (connection != null){
		    	 try{
		    		 connection.close();
		    	 }catch (SQLException ignore){}
		     }
		 }
		return null;
	}

	public void updateEmployee(Employee e) {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String updatequery="UPDATE Employee SET "
		    								+ "name=" + e.getName()
		    								+ "age=" + e.getAge()
		    								+ "phoneNumber=" + e.getPhoneNumber()
		    								+ "office=" + e.getOffice()
		    								+ "speciality=" + e.getSpeciality()
		    								+ "Where id=" + e.getId();
		    //Run Query
		    statement.executeUpdate(updatequery);
		    System.out.println("Table Updated Successfully");
	    } catch (SQLException e3) {
		    System.err.println("Cannot connect to database ! ");
		    e3.printStackTrace();
	    } finally {
		    System.out.println("Closing the connection.");
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
	public void deleteEmployee(Employee e) {
		try {
			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Employee WHERE id=" + e.getId();
		    //Run Query
		    statement.executeUpdate(deletequery);
		    System.out.println("Row Deleted Successfully");
	    } catch (SQLException e4) {
		    System.err.println("Cannot connect to database ! ");
		    e4.printStackTrace();
	    } finally {
		    System.out.println("Closing the connection.");
		    if (connection != null) try { connection.close(); } catch (SQLException ignore) {}
	    }
	}
	
}