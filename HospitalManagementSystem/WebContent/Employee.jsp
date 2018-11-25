<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee Info</title>
</head>
<body>
    <center>
        <h1>Employees</h1>
        <h2>
            <a href="/HospitalManagementSystem/EmployeeServlet/new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/HospitalManagementSystem/EmployeeServlet/list">List All Employees</a>
             
        </h2>
    </center>
    <div align="center">
        <table border="1" cellpadding="5">
            <caption><h2>List of Employees</h2></caption>
            <tr>
                <th>Name</th>
                <th>Age</th>
                <th>Phone</th>
                <th>Office</th>
                <th>Specialty</th>
            </tr>
            <c:forEach var="Employee" items="${empList}">
                <tr>
                    <td><c:out value="${Employee.name}" /></td>
                    <td><c:out value="${Employee.age}" /></td>
                    <td><c:out value="${Employee.phone}" /></td>
                    <td><c:out value="${Employee.office}" /></td>
                    <td><c:out value="${Employee.specialty}" /></td>
                    <td>
                    <a href="/HospitalManagementSystem/EmployeeServlet/edit?id=<c:out value='${emp.id}'/> ">Edit</a>
                    &nbsp;&nbsp;&nbsp;&nbsp;
                    <a href="/HospitalManagementSystem/EmployeeServlet/delete?id=<c:out value='${emp.id}'/> ">Delete</a>                     
                	</td>
            	</tr>
            </c:forEach>
        </table>
    </div>   
</body>
</html>