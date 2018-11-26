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
//	static final String host = "jdbc:mysql://129.22.23.135:3306/team17";
//	static final String dbUsername = "team17";
//	static final String dbPassword = "team17";
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
		      String query = "insert into Employee(name, age, phone, office, specialty)"
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
			
//			//insert into Employee (id, name, age, phone, office, specialty) values (default, “NurseName1”, 28, 6436781290, “Three Office Drive”, “Nurse”); 
//			//insert into Employee (id, name, age, phone, office, specialty) values (default, "new person", 99, 2041231234, "New office", "Doctor");
//			//SQL statement: 
			String abc = "insert into  Employee (id, name, age, phone, office, specialty) values (default, \"" 
					+ e.getName() + "\", " 
					+ e.getAge() + ", " 
					+ e.getPhone() + ", \""
					+ e.getOffice() + "\", \""
					+ e.getSpecialty() + "\");";
			System.out.println(abc);
//		    preparedStatement = connection.prepareStatement("insert into Employee (id, name, age, phone, office, specialty) values (default, \"" 
//		    																														+ e.getName() + "\", " 
//		    																														+ e.getAge() + ", " 
//		    																														+ e.getPhoneNumber() + ", \""
//		    																														+ e.getOffice() + "\", \""
//		    																														+ e.getSpecialty() + "\");");
//		    
		    System.out.println("Adding " + e.getName() + " to employee table");
		    
		    //Add values here: 
//		    preparedStatement.setString(1, e.getName());
//		    preparedStatement.setInt(2, e.getAge());
//		    preparedStatement.setLong(3, e.getPhoneNumber());
//		    preparedStatement.setString(4, e.getOffice());
//		    preparedStatement.setString(5, e.getSpecialty());
//		    preparedStatement.executeUpdate();
		    System.out.println("Employee Added!");
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			System.out.println("We've got an error");
			e1.printStackTrace();
		}
	}
	
	
	public Employee getEmployee(int id ) throws Exception {
		try {
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE id =" + id);
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			Employee emp = null;
			while(resultSet.next()) {
				emp = new Employee(id, 
										resultSet.getString(2), 
										resultSet.getInt(3), 
										resultSet.getString(4), 
										resultSet.getString(5), 
										resultSet.getString(6)); 
			}

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
			System.out.println("hi");
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE");
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			
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
			
			System.out.println("Found " + allEmps.size() + " employees!");
			
			return allEmps;
		}
		catch (SQLException e2) {
			 System.err.println("Cannot connect to database! See error below:  ");
		     e2.printStackTrace();
		}
		finally {
		     System.out.println("Closing the connection.");
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
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE NOT speciality = 'Nurse' ");
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			
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
			//Class.forName("com.mysql.jdbc.Driver");
			System.out.println("Connecting to Database (hopefully)");
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);
			System.out.println("Connected to Database");
			preparedStatement = connection.prepareStatement("SELECT * FROM EMPLOYEE WHERE speciality = 'Nurse' ");
			resultSet = preparedStatement.executeQuery();
			System.out.println("Got some data");
			
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

	public void updateEmployee(Employee e) throws ClassNotFoundException {
		try {
			//Class.forName("com.mysql.jdbc.Driver");

			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String updatequery="UPDATE Employee SET "
		    								+ "name="+ "\"" + e.getName()+ "\""
		    								+ ", age=" + e.getAge()
		    								+ ", phone=" + e.getPhone()
		    								+ ", office=" + "\"" + e.getOffice() + "\""
		    								+ ", speciality="+ "\"" + e.getSpecialty()+ "\""
		    								+ " Where id=" + e.getId();
		    System.out.println(updatequery);
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
	
	public void deleteEmployee(int id) throws ClassNotFoundException {
		try {
			//Class.forName("com.mysql.jdbc.Driver");

			//Connect to server and database
			connection = DriverManager.getConnection(host, dbUsername, dbPassword);     
		    //Initialize Statement
		    statement=connection.createStatement();
		    //SQL Query
		    String deletequery="DELETE FROM Employee WHERE id=" + id;
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