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
	
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
//		String action = request.getServletPath();
//		System.out.println("*******");
//		System.out.println("New action: " + action);
//        try {
//            switch (action) {
//            case "/EmployeeServlet/new":
//            	System.out.println("new");
//                showNewForm(request, response);
//                break;
////            case "/insert":
////                insertBook(request, response);
////                break;
////            case "/delete":
////                deleteBook(request, response);
////                break;
//            case "/EmployeeServlet/edit":
//            	System.out.println("edit");
//                showEditForm(request, response);
//                break;
////            case "/update":
////                updateBook(request, response);
////                break;
//            default:
//            	System.out.println("listStuff");
//                listEmployees(request, response);
//                break;
//            }
//        } catch (SQLException ex) {
//        	System.out.println("rip");
//            throw new ServletException(ex);
//        }
		
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
					out.println("<p>" + tempE.getName() + ":	" + tempE.getAge() + ":	" + tempE.getPhone() +":	" + tempE.getOffice() + ":	" + tempE.getSpecialty() + "</p>");
				}
				out.println("<p> <strong> Each item in the table also has an id but user doesn't need to know what it is </strong> </p>");
			}else {
				out.println("<p>" + "There is no data in the table" + "<p>");
			}
			
		} catch (Exception e) {
			
		}
	}
	
	 private void listEmployees(HttpServletRequest request, HttpServletResponse response)
	            throws SQLException, IOException, ServletException {
	        List<Employee> empList = null;
			try {
				empList = edao.getAllEmployees();
				System.out.println("empList has size: " + empList.size());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	        request.setAttribute("empList", empList);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("Employee.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	    	System.out.println("hi there");
	        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
	        dispatcher.forward(request, response);
	    }
	 
	    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        int id = Integer.parseInt(request.getParameter("id"));
	        Employee selectedEmployee = null;
			try {
				selectedEmployee = edao.getEmployee(id);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        RequestDispatcher dispatcher = request.getRequestDispatcher("EmployeeForm.jsp");
	        request.setAttribute("emp", selectedEmployee);
	        dispatcher.forward(request, response);
	 
	    }
//	 
//	    private void insertBook(HttpServletRequest request, HttpServletResponse response)
//	            throws SQLException, IOException {
//	        String title = request.getParameter("title");
//	        String author = request.getParameter("author");
//	        float price = Float.parseFloat(request.getParameter("price"));
//	 
//	        Book newBook = new Book(title, author, price);
//	        bookDAO.insertBook(newBook);
//	        response.sendRedirect("list");
//	    }
//	 
//	    private void updateBook(HttpServletRequest request, HttpServletResponse response)
//	            throws SQLException, IOException {
//	        int id = Integer.parseInt(request.getParameter("id"));
//	        String title = request.getParameter("title");
//	        String author = request.getParameter("author");
//	        float price = Float.parseFloat(request.getParameter("price"));
//	 
//	        Book book = new Book(id, title, author, price);
//	        bookDAO.updateBook(book);
//	        response.sendRedirect("list");
//	    }
//	 
//	    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
//	            throws SQLException, IOException {
//	        int id = Integer.parseInt(request.getParameter("id"));
//	 
//	        Book book = new Book(id);
//	        bookDAO.deleteBook(book);
//	        response.sendRedirect("list");
//	 
//	    }
	
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
			Employee e = new Employee(3, name, Integer.parseInt(age), phone, office, specialty);
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
	
	
}