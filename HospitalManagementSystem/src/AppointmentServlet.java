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

public class AppointmentServlet extends HttpServlet {
	AppointmentDAO pdao;

	public void init() throws ServletException {
		pdao = new AppointmentDAO();
	}
	//Return a list of all Appointments
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting up the content type of webpage
		response.setContentType("text/html");
		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "List all Appointments" + "</h1>");
		AppointmentDAO pdao = new AppointmentDAO();
		String id = request.getParameter("id");
		if(id.equals("-1")) {
			List<Appointment> lst = new ArrayList<Appointment>();
			try {
				//Get all initial data
				lst = pdao.getAllAppointments();
				if(lst != null) {
					//| Appointment_ID | Date       | Time | OR_ID | Patient_ID | Operation_ID | Doctor_ID | Nurse_ID |
					out.println("<p> <strong> ID | Date | Time | OR_ID | Patient_ID | Operation_ID | Doctor_ID | Nurse_ID </strong></p>");
					for(int i = 0; i < lst.size(); i++) {
						Appointment tempP = lst.get(i);
						out.println("<p>" + tempP.getId() + " | " +  tempP.getDate() + " | " + tempP.getTime() + " | " + tempP.getOr_id() + " | " + tempP.getPatient_id() + " | " + tempP.getOperation_id() + " | " + tempP.getDoctor_id() + " | " + tempP.getNurse_id() +  "</p>");
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
					pdao.deleteAppointment(Integer.parseInt(id));
					out.println("<p> Appointment Deleted</p>");
				}catch (Exception e) {
					
				}
			}
		}
	}
	
	//Add a new Appointment
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // read form fields
	
		String id = request.getParameter("id");
		String dateStr = request.getParameter("Date");
        String time = request.getParameter("Time");
        String orId = request.getParameter("OR_id");
        String pId = request.getParameter("P_id");
        String oId = request.getParameter("O_id");
        String dId = request.getParameter("D_id");
        String nId = request.getParameter("N_id");

        AppointmentDAO pdao = new AppointmentDAO();
        System.out.println("We have a date " + dateStr);
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
    			Appointment p = new Appointment(3, date, Integer.parseInt(time), Integer.parseInt(orId), Integer.parseInt(pId), Integer.parseInt(oId), Integer.parseInt(dId),Integer.parseInt(nId));
    			pdao.addAppointment(p);
    			
    			// get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> Appointment Added. </br></h2>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
        	try {
    			Appointment p = new Appointment(Integer.parseInt(id), date, Integer.parseInt(time), Integer.parseInt(orId), Integer.parseInt(pId), Integer.parseInt(oId), Integer.parseInt(dId),Integer.parseInt(nId));
    			pdao.updateAppointment(p);
    			 // get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> Appointment Updated. </br></h2>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
       
        //TODO: Add a back button so the user can return to the previous page        
    }
}
