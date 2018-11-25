import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class FirstServlet extends HttpServlet {
	private String mymsg;

	public void init() throws ServletException {
		mymsg = "Hello World! This is a test.";
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting up the content type of webpage
		response.setContentType("text/html");
		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "Employee Sample Page" + "</h1>");

		EmployeeDAO edao = new EmployeeDAO();
		List<Employee> lst = new ArrayList<Employee>();
		List<Employee> doctors = new ArrayList<Employee>();
		List<Employee> nurses = new ArrayList<Employee>();
		try {
			//Get all initial data
			out.println("<h3>" + "First, lets list all the data in the table:" + "</h3>");
			lst = edao.getAllEmployees();
			if(lst != null) {
				out.println("<p> <strong> Name 	: Age 	: Phone 	: Office 	: Specialty </strong></p>");
				for(int i = 0; i < lst.size(); i++) {
					Employee tempE = lst.get(i);
					out.println("<p>" + tempE.getName() + ":	" + tempE.getAge() + ":	" + tempE.getPhoneNumber() +":	" + tempE.getOffice() + ":	" + tempE.getSpecialty() + "</p>");
				}
				out.println("<p> <strong> Each item in the table also has an id but user doesn't need to know what it is </strong> </p>");
			}else {
				out.println("<p>" + "There is no data in the table" + "<p>");
			}
			out.println("<h3>" + "Now, lets add a new employee:" + "</h3>");
			
		} catch (Exception e) {
			
		}
		
		
		
//		
//		try {
//			System.out.println("Deleting an employee");
//			edao.deleteEmployee(4);
//			//doctors = edao.getAllDoctors();
//			//nurses = edao.getAllNurses();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			System.out.println("Added a new employee");
//			Employee e = new Employee(3, "new Person2", 99, 2041231234, "New Office Road", "Doctor activities");
//			edao.addEmployee(e);
//			//doctors = edao.getAllDoctors();
//			//nurses = edao.getAllNurses();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		try {
//			lst = edao.getAllEmployees();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(lst != null) {
//			for(int i = 0; i < lst.size(); i++) {
//				//System.out.println(lst.get(i).getName());
//				out.println("<h5>" + lst.get(i).getName() + ": " + lst.get(i).getAge() + "</h5>");
//			}
//		}
//		
//		out.println("<h1>" + "Doctors" + "</h1>");
//		for(int i = 0; i < doctors.size(); i++) {
//			out.println("<h5>" + doctors.get(i).getName() + ": " + doctors.get(i).getAge() + "</h5>");
//		}
//		
//		out.println("<h1>" + "Nurses" + "</h1>");
//		for(int i = 0; i < nurses.size(); i++) {
//			out.println("<h5>" + nurses.get(i).getName() + ": " + nurses.get(i).getAge() + "</h5>");
//		}
//		out.println("<h1>" + "Update employee michael" + "</h1>");
//		
//		try {
//			Employee e = edao.getEmployee(1);
//			e.setName("Michael Smith");
//			edao.updateEmployee(e);
//			Employee e1 = edao.getEmployee(1);
//			out.println("<h5>" + e1.getName() + "</h5>");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		out.println("<h1>" + "All employees " + "</h1>");
//		try {
//			lst = edao.getAllEmployees();
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		if(lst != null) {
//			for(int i = 0; i < lst.size(); i++) {
//				//System.out.println(lst.get(i).getName());
//				out.println("<h5>" + lst.get(i).getName() + ": " + lst.get(i).getAge() + "</h5>");
//			}
//		}
		
		
	}

   public void destroy() {
      /* leaving empty for now this can be
       * used when we want to do something at the end
       * of Servlet life cycle
       */
   }
}
