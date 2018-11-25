import java.sql.DriverManager;
import java.util.List;

public class Main {
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
	public static void main(String[] args) throws Exception {
		//MySQLAccess dao = new MySQLAccess();
//		
		
		EmployeeDAO edao = new EmployeeDAO();
		List<Employee> lst = edao.getAllEmployees();
		if(lst != null) {
			for(int i = 0; i < lst.size(); i++) {
				System.out.println(lst.get(i).getName());
			}
		}
    }
}
