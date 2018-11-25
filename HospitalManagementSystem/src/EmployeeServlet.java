import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
	private String mymsg;

	public void init() throws ServletException {
		mymsg = "Hello World! This is a test.";
	}
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
         
        // read form fields
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String office = request.getParameter("office");
        String specialty = request.getParameter("specialty");
         
        System.out.println("name: " + name);
        System.out.println("age: " + age);
 
        // do some processing here...
        
        try {
        	EmployeeDAO edao = new EmployeeDAO();
			Employee e = new Employee(3, name, Integer.parseInt(age), Long.parseLong(phone), office, specialty);
			edao.addEmployee(e);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
        // get response writer
        PrintWriter writer = response.getWriter();
         
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2> Employee Added. </br></h2>";  
        htmlRespone += "</html>";
         
        // return response
        writer.println(htmlRespone);
         
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
		//List<Employee> doctors = new ArrayList<Employee>();
		//List<Employee> nurses = new ArrayList<Employee>();
		try {
			//Get all initial data
			//out.println("<h3>" + "First, lets list all the data in the table:" + "</h3>");
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
			
		} catch (Exception e) {
			
		}
	}
}