<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>

<html>

<head>
    <title>List Customers</title>
    
    <!--  reference out style sheet -->
    <link type="text/css"
          rel="stylesheet"
          href="${pageContext.request.contextPath}/resources/css/style.css" /> 

</head>

<body>

    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2> 
        </div>
    </div>
    
    <div id="container"> 
        <div id="content">
  
            <!--  put a button: Add Customer -->
            
            <input type="button" value="Add Customer"
            		onclick="window.location.href='showFormForAdd'; return false;"   
            		class="add-button"
            />
            
            <!--  add a search button -->
            
            <form:form action="search" method="GET">
                Search Customer:<input type="text" name="theSearchName" />
            	
            	<input type="submit" value="Search" class="add-button" /> 
            </form:form>
        
        	<!-- add our html table here -->
  
        	<table>
        	    <tr>
        	       <th>First Name</th>
        	       <th>Last Name</th>
        	       <th>Email</th>   
        	       <th>Action</th>
        	    </tr>
        	    
        	    <!-- loop over and print our customers -->
        	    <c:forEach var="tempCustomer" items="${customers}">
        	    
        	        <!--  Construct a update link with customer id -->
        	        <c:url var="updatelink" value="/customer/showFormForUpdate">
        	        	<c:param name="customerId" value="${tempCustomer.id}" />
        	        </c:url>
        	        
        	        <!-- Construct a delete link -->
        	        <c:url var="deletelink" value="/customer/delete">
        	        	<c:param name="customerId" value="${tempCustomer.id}" />
        	        </c:url>
        	        
        	        <tr>
        	        	<td> ${tempCustomer.firstName} </td>
        	        	<td> ${tempCustomer.lastName} </td>
        	        	<td> ${tempCustomer.email} </td>
        	        	<td> 
        	        		<!--  display the update link -->
        	        		<a href="${updatelink}">Update</a>
        	        		|
        	        		<a href="${deletelink}"
        	        		   onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false">Delete</a>
        	        	</td>
        	        </tr>
        	        
        	    </c:forEach>

        	</table>
        
        </div>
    
    </div>
        

</body>

</html>