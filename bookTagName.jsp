<!-- Header -->
 <jsp:include page="header.jsp" /> 
	
	<!-- JSTL includes -->
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	

	<header>
	 <div class="container">   
	  
	<c:choose>
	<c:when test="${not empty message }">
	  <p class="alert ${messageClass}">${message }</p>
	<%
	  session.setAttribute("message", null);
	  session.setAttribute("messageClass", null);
	%>
	</c:when>
	</c:choose>
	
	<h1>PUBHUB <small>Search by Tag</small></h1>
	 <hr class="book-primary"> 
	
	<form action="BookTagName" method ="get">
                  <input type="text" name="tag_name" required = "required" placeholder = "Enter Tag Name" >
                  <button type="submit" class="btn btn-primary">Search</button>
     </form>
     
    <table class="table table-striped table-hover table-responsive pubhub-datatabl">
             <thead>
				<tr>
					
					<td>ISBN-13:</td>
					<td>Author:</td>
					<td>Title:</td>
				  	<td>Publish Date:</td>
					<td>Price:</td>
				    <td></td>  
					
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
					  
					   <td><c:out value="${book.getIsbn13()}" /></td>
					   <td><c:out value="${book.getAuthor()}" /></td>
					   <td><c:out value="${book.getTitle()}" /></td>
					   <td><c:out value="${book.getPublishDate() }" /></td>
					   <td><fmt:formatNumber value="${book.getPrice()}" type="CURRENCY"/></td>
				       <td></td>
						 
					</tr>
				</c:forEach>
			</tbody>
		</table>

	 </div>  
	</header>
     
		

	<!--   Footer -->
	 <jsp:include page="footer.jsp" />  