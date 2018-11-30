import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OperationServlet extends HttpServlet {
	OperationDAO pdao;

	public void init() throws ServletException {
		pdao = new OperationDAO();
	}
	//Return a list of all Operations
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting up the content type of webpage
		response.setContentType("text/html");
		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "List all Operations" + "</h1>");
		OperationDAO pdao = new OperationDAO();
		String id = request.getParameter("id");
		if(id.equals("-1")) {
			List<Operation> lst = new ArrayList<Operation>();
			try {
				//Get all initial data
				lst = pdao.getAllOperations();
				if(lst != null) {
					out.println("<p> <strong> ID | Name        | Illness 	 | Specialty     | Duration </strong></p>");
					for(int i = 0; i < lst.size(); i++) {
						Operation tempP = lst.get(i);
						out.println("<p>" + tempP.getId() + " | " +  tempP.getName() + " | " + tempP.getIllness() + " | " + tempP.getSpecialty() +" | " + tempP.getDuration() + "</p>");
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
					pdao.deleteOperation(Integer.parseInt(id));
					out.println("<p> Operation Deleted</p>");
				}catch (Exception e) {
					
				}
			}
		}
	}
	
	//Add a new Operation
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // read form fields
		String id = request.getParameter("id");
        String name = request.getParameter("Name");
        String illness = request.getParameter("Illness");
        String specialty = request.getParameter("Specialty");
        String duration = request.getParameter("Duration");
        
        OperationDAO pdao = new OperationDAO();
       
        
        if(id.equals("-1")) {
        	try {
    			Operation p = new Operation(3, name, illness, specialty, Integer.parseInt(duration));
    			pdao.addOperation(p);
    			
    			// get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> Operation Added. </br></h2>";
    	        htmlRespone += "<button onclick=\"goBack()\">Finish</button>\n" + 
    	        		"\n" + 
    	        		"<script>\n" + 
    	        		"function goBack() {\n" + 
    	        		"    window.history.back();\n" + 
    	        		"}\n" + 
    	        		"</script>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
        	try {
    			Operation p = new Operation(Integer.parseInt(id), name, illness, specialty, Integer.parseInt(duration));
    			pdao.updateOperation(p);
    			 // get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> Operation Updated. </br></h2>";
    	        htmlRespone += "<button onclick=\"goBack()\">Finish</button>\n" + 
    	        		"\n" + 
    	        		"<script>\n" + 
    	        		"function goBack() {\n" + 
    	        		"    window.history.back();\n" + 
    	        		"}\n" + 
    	        		"</script>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
       
        //TODO: Add a back button so the user can return to the previous page        
    }
}
