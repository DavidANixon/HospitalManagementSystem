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

public class PatientServlet extends HttpServlet {
	PatientDAO pdao;

	public void init() throws ServletException {
		pdao = new PatientDAO();
	}
	//Return a list of all Patients
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting up the content type of webpage
		response.setContentType("text/html");
		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "List all Patients" + "</h1>");
		PatientDAO pdao = new PatientDAO();
		String id = request.getParameter("id");
		if(id.equals("-1")) {
			List<Patient> lst = new ArrayList<Patient>();
			try {
				//Get all initial data
				lst = pdao.getAllPatients();
				if(lst != null) {
					out.println("<p> <strong> ID | Name        | DOB 	 | Address     | Phone    | Illness </strong></p>");
					for(int i = 0; i < lst.size(); i++) {
						Patient tempP = lst.get(i);
						out.println("<p>" + tempP.getId() + " | " +  tempP.getName() + " | " + tempP.getDOB() + " | " + tempP.getAddress() +" | " + tempP.getPhoneNumber() + " | " + tempP.getIllness() + "</p>");
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
					pdao.deletePatient(Integer.parseInt(id));
					out.println("<p> Patient Deleted</p>");
				}catch (Exception e) {
					
				}
			}
		}
	}
	
	//Add a new Patient
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // read form fields
		String id = request.getParameter("id");
        String name = request.getParameter("Name");
        String dateStr = request.getParameter("DOB");
        String address = request.getParameter("Address");
        String phone = request.getParameter("Phone");
        String illness = request.getParameter("Illness");
        
        PatientDAO pdao = new PatientDAO();
        
        DateFormat format = new SimpleDateFormat("yyyy-mm-dd");
        Date date = null;
		try {
			date = format.parse(dateStr);
		} catch (ParseException e1) {
			System.out.println("rip");
			e1.printStackTrace();
		}
        
        if(id.equals("-1")) {
        	try {
    			Patient p = new Patient(3, name, date, address, phone, illness);
    			pdao.addPatient(p);
    			
    			// get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> Patient Added. </br></h2>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
        	try {
    			Patient p = new Patient(Integer.parseInt(id), name, date, address, phone, illness);
    			pdao.updatePatient(p);
    			 // get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> Patient Updated. </br></h2>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
       
        //TODO: Add a back button so the user can return to the previous page        
    }
	
}
