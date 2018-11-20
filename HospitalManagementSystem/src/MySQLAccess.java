//Adapted from http://www.vogella.com/tutorials/MySQLJava/article.html
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.Date;
import java.util.Properties;
import java.util.ResourceBundle;

public class MySQLAccess {
	
	private Connection connect = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;
	
//	final private String host = getProp("dbUrl");
//	final private String user = getProp("dbUsername");
//	final private String passwd = getProp("dbPassword");
	
	final private String host = "jdbc:mysql://localhost:3306/YOURDBNAME";
	final private String user = "username";
	final private String passwd = "password";
	
	InputStream input;

	private String getProp(String propertyType) {
		String propValue = "";
		try {
			Properties prop = new Properties();
			String propertyFileName = "Java Resources/resources/config.properties";
			
			input = getClass().getClassLoader().getResourceAsStream(propertyFileName);
			
			if(input != null) {
				prop.load(input);			
			}else {
				System.out.println("File didn't load");
			}
		    
		    propValue = prop.getProperty(propertyType);
			System.out.println("1PropType: " + propertyType + " ; Value: " + propValue);

		    // get the property value and print it out
//		    System.out.println(prop.getProperty("database"));
//		    System.out.println(prop.getProperty("dbuser"));
//		    System.out.println(prop.getProperty("dbpassword"));

		} catch (Exception e) {
			System.out.println("Exception: " + e);
		    //ex.printStackTrace();
		} finally {
//		    if (input != null) {
//		        try {
//		            input.close();
//		        } catch (IOException e) {
//		            e.printStackTrace();
//		        }
//		    }
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("2PropType: " + propertyType + " ; Value: " + propValue);
		return propValue;
	}
	
	
	public void addPatient(Patient p) {
		
	}
	public void addEmployee(Employee e) {
		
	}
	public void addOperation(Operation op) {
		
	}
	public void addOperatingRoom(OperatingRoom or) {
		
	}
	public void addAppointment(Appointment a) {
		
	}
	
	public void getPatient(Patient p) {
		
	}
	public void getEmployee(Employee e) {
		
	}
	public void getOperation(Operation op) {
		
	}
	public void getOperatingRoom(OperatingRoom or) {
		
	}
	public void getAppointment(Appointment a) {
		
	}
	
	public void updatePatient(Patient p) {
		
	}
	public void updateEmployee(Employee e) {
		
	}
	public void updateOperation(Operation op) {
		
	}
	public void updateOperatingRoom(OperatingRoom or) {
		
	}
	public void updateAppointment(Appointment a) {
		
	}
	
	public void deletePatient(Patient p) {
		
	}
	public void deleteEmployee(Employee e) {
		
	}
	public void deleteOperation(Operation op) {
		
	}
	public void deleteOperatingRoom(OperatingRoom or) {
		
	}
	public void deleteAppointment(Appointment a) {
		
	}
	
	
	public void readDataBase() throws Exception {
		try {
		    // This will load the MySQL driver, each DB has its own driver
		    Class.forName("com.mysql.jdbc.Driver");
		   
		    // Setup the connection with the DB
		    connect = DriverManager.getConnection(host + "/feedback?"+ "user=" + user + "&password=" + passwd );
		
		    // Statements allow to issue SQL queries to the database
		    statement = connect.createStatement();
		    // Result set get the result of the SQL query
		    resultSet = statement.executeQuery("select * from employees");
		    writeResultSet(resultSet);
		
		    // PreparedStatements can use variables and are more efficient
		    preparedStatement = connect.prepareStatement("insert into  patients values (default, ?, ?, ?, ? , ?, ?)");

		    // Parameters start with 1
		    //Add values here: 
		    preparedStatement.setInt(1, 3);
		    preparedStatement.setString(1, "Test");
		    preparedStatement.setString(2, "TestEmail");
		    preparedStatement.setString(3, "TestWebpage");
		    preparedStatement.setDate(2, java.sql.Date.valueOf("2013-09-04"));
		    preparedStatement.setString(5, "TestSummary");
		    preparedStatement.setString(6, "TestComment");
		    preparedStatement.executeUpdate();
		
		    preparedStatement = connect.prepareStatement("SELECT myuser, webpage, datum, summary, COMMENTS from feedback.comments");
		    resultSet = preparedStatement.executeQuery();
		    writeResultSet(resultSet);
		
		    // Remove again the insert comment
		    preparedStatement = connect.prepareStatement("delete from feedback.comments where myuser= ? ; ");
		    preparedStatement.setString(1, "Test");
		    preparedStatement.executeUpdate();
		   
		    resultSet = statement.executeQuery("select * from feedback.comments");
		    writeMetaData(resultSet);
		} catch (Exception e) {
			throw e;
		} finally {
			close();
		}
	}
	
	private void writeMetaData(ResultSet resultSet) throws SQLException {
		//   Now get some metadata from the database
		// Result set get the result of the SQL query
		System.out.println("The columns in the table are: ");
		System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
		for  (int i = 1; i<= resultSet.getMetaData().getColumnCount(); i++){
			System.out.println("Column " +i  + " "+ resultSet.getMetaData().getColumnName(i));
		}
	}
	
	//get data from table 
	private void writeResultSet(ResultSet resultSet) throws SQLException {
		// ResultSet is initially before the first data set
		while (resultSet.next()) {
			// It is possible to get the columns via name
			// also possible to get the columns via the column number
			// which starts at 1
			String user = resultSet.getString("myuser");
			String website = resultSet.getString("webpage");
			String summary = resultSet.getString("summary");
			Date date = resultSet.getDate("datum");
			String comment = resultSet.getString("comments");
			System.out.println("User: " + user);
			System.out.println("Website: " + website);
			System.out.println("Summary: " + summary);
			System.out.println("Date: " + date);
			System.out.println("Comment: " + comment);
		}
	}
	
	// You need to close the resultSet
	private void close() {
		try {
		   if (resultSet != null) {
		     resultSet.close();
		   }
		
		   if (statement != null) {
		     statement.close();
		   }
		
		   if (connect != null) {
		     connect.close();
		   }
		} catch (Exception e) {
			
		}
	}
}