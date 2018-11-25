<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Employee Form</title>
</head>
<body>
    <center>
        <h1>Modify Employees</h1>
        <h2>
            <a href="/EmployeeServlet/new">Add New Employee</a>
            &nbsp;&nbsp;&nbsp;
            <a href="/EmployeeServlet/list">List All Employees</a>
             
        </h2>
    </center>
    <div align="center">
        <c:if test="${emp != null}">
            <form action="update" method="post">
        </c:if>
        <c:if test="${emp == null}">
            <form action="insert" method="post">
        </c:if>
        <table border="1" cellpadding="5">
            <caption>
                <h2>
                    <c:if test="${emp != null}">
                        Edit Book
                    </c:if>
                    <c:if test="${emp == null}">
                        Add New Book
                    </c:if>
                </h2>
            </caption>
                <c:if test="${emp != null}">
                    <input type="hidden" name="id" value="<c:out value='${emp.id}' />" />
                </c:if>           
            <tr>
                <th>Name: </th>
                <td>
                    <input type="text" name="name" size="45"
                            value="<c:out value='${emp.name}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Age: </th>
                <td>
                    <input type="number" name="age" min="0" max="200" step="1" 
                    		value="<c:out value='${emp.name}' />"
                    	/>   	
                </td>
            </tr>
            <tr>
                <th>Phone Number: </th>
                <td>
                    <input type="text" name="title" size="45"
                            value="<c:out value='${emp.phone}' />"
                        />
                </td>
            </tr>
            <tr>
                <th>Office: </th>
                <td>
                    <input type="text" name="author" size="45"
                            value="<c:out value='${emp.office}' />"
                    />
                </td>
            </tr>
            <tr>
                <th>Specialty: </th>
                <td>
                    <input type="text" name="price" size="5"
                            value="<c:out value='${emp.specialty}' />"
                    />
                </td>
            </tr>
            <tr>
                <td colspan="2" align="center">
                    <input type="submit" value="Save" />
                </td>
            </tr>
        </table>
        </form>
    </div>   
</body>
</html>