import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class EmployeeServlet extends HttpServlet {
	EmployeeDAO edao;

	public void init() throws ServletException {
		edao = new EmployeeDAO();
	}
	
	//Return a list of all employees
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting up the content type of webpage
		response.setContentType("text/html");
		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "List all Employees" + "</h1>");
		EmployeeDAO edao = new EmployeeDAO();
		String id = request.getParameter("id");
		if(id.equals("-1")) {
			List<Employee> lst = new ArrayList<Employee>();
			try {
				//Get all initial data
				lst = edao.getAllEmployees();
				if(lst != null) {
					out.println("<p> <strong> ID | Name        | Age | Phone     | Office    | Specialty </strong></p>");
					for(int i = 0; i < lst.size(); i++) {
						Employee tempE = lst.get(i);
						out.println("<p>" + tempE.getId() + " | " +  tempE.getName() + " | " + tempE.getAge() + " | " + tempE.getPhone() +" | " + tempE.getOffice() + " | " + tempE.getSpecialty() + "</p>");
					}
				}else {
					out.println("<p>" + "There is no data in the table or a table was not found." + "<p>");
				}
			} catch (Exception e) {
				
			}
		}else {
			if(id.equals("")) {
				out.println("<p> Input not valid</p>");
			}else {
				try {
					edao.deleteEmployee(Integer.parseInt(id));
					out.println("<p> Employee Deleted</p>");
				}catch (Exception e) {
					
				}
			}
		}
	}
	
	//Add a new employee
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // read form fields
		String id = request.getParameter("id");
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        String phone = request.getParameter("phone");
        String office = request.getParameter("office");
        String specialty = request.getParameter("specialty");
        
        EmployeeDAO edao = new EmployeeDAO();
        if(id.equals("-1")) {
        	try {
            	
    			Employee e = new Employee(3, name, Integer.parseInt(age), phone, office, specialty);
    			edao.addEmployee(e);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
        	try {
    			Employee e = new Employee(Integer.parseInt(id), name, Integer.parseInt(age), phone, office, specialty);
    			edao.updateEmployee(e);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
        // get response writer
        PrintWriter writer = response.getWriter();
        // build HTML code
        String htmlRespone = "<html>";
        htmlRespone += "<h2> Employee Added. </br></h2>";
        htmlRespone += "</html>";
        //TODO: Add a back button so the user can return to the previous page
         
        // return response
        writer.println(htmlRespone);
    }
	
	public void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
	}
	
	public void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
	}
	
}