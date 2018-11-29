import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OperatingRoomServlet extends HttpServlet {
	OperatingRoomDAO pdao;

	public void init() throws ServletException {
		pdao = new OperatingRoomDAO();
	}
	//Return a list of all OperatingRooms
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// Setting up the content type of webpage
		response.setContentType("text/html");
		// Writing message to the web page
		PrintWriter out = response.getWriter();
		out.println("<h1>" + "List all OperatingRooms" + "</h1>");
		OperatingRoomDAO pdao = new OperatingRoomDAO();
		String id = request.getParameter("id");
		if(id.equals("-1")) {
			List<OperatingRoom> lst = new ArrayList<OperatingRoom>();
			try {
				//Get all initial data
				lst = pdao.getAllOperatingRooms();
				if(lst != null) {
					out.println("<p> <strong> ID | Building        | Room Number </strong></p>");
					for(int i = 0; i < lst.size(); i++) {
						OperatingRoom tempP = lst.get(i);
						out.println("<p>" + tempP.getId() + " | " +  tempP.getBuilding() + " | " + tempP.getRoomNumber() + "</p>");
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
					pdao.deleteOperatingRoom(Integer.parseInt(id));
					out.println("<p> OperatingRoom Deleted</p>");
				}catch (Exception e) {
					
				}
			}
		}
	}
	
	//Add a new OperatingRoom
	protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        // read form fields
		String id = request.getParameter("id");
        String building = request.getParameter("Building");
        String roomNumber = request.getParameter("RoomNumber");

        OperatingRoomDAO pdao = new OperatingRoomDAO();
       
        
        if(id.equals("-1")) {
        	try {
    			OperatingRoom p = new OperatingRoom(3, building, Integer.parseInt(roomNumber));
    			pdao.addOperatingRoom(p);
    			
    			// get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> OperatingRoom Added. </br></h2>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }else {
        	try {
    			OperatingRoom p = new OperatingRoom(Integer.parseInt(id), building, Integer.parseInt(roomNumber));
    			pdao.updateOperatingRoom(p);
    			 // get response writer
    	        PrintWriter writer = response.getWriter();
    	        // build HTML code
    	        String htmlRespone = "<html>";
    	        htmlRespone += "<h2> OperatingRoom Updated. </br></h2>";
    	        htmlRespone += "</html>";
    	        writer.println(htmlRespone);
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
        }
        
       
        //TODO: Add a back button so the user can return to the previous page        
    }
}
