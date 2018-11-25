# DatabasesProject
Term Project for Intro to Database Systems
Created by Anna, David, Michael, and Zetao

 
### Backend File Structure: 
- Each data type (patient, employee, etc) had a class and a DAO file in the `/src/` directory. 
  - The class had each of its attributes, getters/setters, a constructor, etc
  - The DAO file had all the methods that are for using that table 
- Each Page will have a seperate file in `/src/` (file name format has not been decided yet)
  - All the logic will (probably) go into this page 
  - Formatting might also go here???
- The static landing page is in: `WebContent/WEB-INF/index.html`
- To run, you need to install Tomcat Server v8.5. The server is used to run the servlets and access the database. 
