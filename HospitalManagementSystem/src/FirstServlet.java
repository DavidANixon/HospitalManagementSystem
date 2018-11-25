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
		out.println("<h1>" + mymsg + "</h1>");
		System.out.println("hi there server!");
		
		EmployeeDAO edao = new EmployeeDAO();
		List<Employee> lst = new ArrayList<Employee>();
		List<Employee> doctors = new ArrayList<Employee>();
		List<Employee> nurses = new ArrayList<Employee>();
		try {
			lst = edao.getAllEmployees();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(lst != null) {
			for(int i = 0; i < lst.size(); i++) {
				//System.out.println(lst.get(i).getName());
				out.println("<h5>" + lst.get(i).getName() + ": " + lst.get(i).getAge() + "</h5>");
			}
		}
		try {
			Employee e = new Employee(2, "new person", 99, 2041231234, "New office", "Doctor");
			edao.addEmployee(e);
			doctors = edao.getAllDoctors();
			nurses = edao.getAllNurses();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		out.println("<h1>" + "Doctors" + "</h1>");
		for(int i = 0; i < doctors.size(); i++) {
			out.println("<h5>" + doctors.get(i).getName() + ": " + doctors.get(i).getAge() + "</h5>");
		}
		
		out.println("<h1>" + "Nurses" + "</h1>");
		for(int i = 0; i < nurses.size(); i++) {
			out.println("<h5>" + nurses.get(i).getName() + ": " + nurses.get(i).getAge() + "</h5>");
		}
		out.println("<h1>" + "Update employee michael" + "</h1>");
		
		try {
			Employee e = edao.getEmployee(1);
			e.setName("Michael Smith");
			edao.updateEmployee(e);
			Employee e1 = edao.getEmployee(1);
			out.println("<h5>" + e1.getName() + "</h5>");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

   public void destroy() {
      /* leaving empty for now this can be
       * used when we want to do something at the end
       * of Servlet life cycle
       */
   }
}
