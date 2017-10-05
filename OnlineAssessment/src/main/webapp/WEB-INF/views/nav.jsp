<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<nav class="navbar navbar-fixed-top">
    <div class="container">
      <div class="navbar-header">
        <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
          <span class="sr-only">Toggle navigation</span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
          <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand custom-header-font" href="#introduction">TAKE EXAM</a>
      </div>
      <div id="navbar" class="collapse navbar-collapse">
        <ul class="nav navbar-nav custom-header-font" id="topnavbar">
          
          <% String user = (String)request.getSession().getAttribute("username");
          
          	if(user!=null && user.equals("admin")){
          %>
          	<c:url var="courses" value="/admin/courses" ></c:url>
          	<c:url var="pools" value="/admin/pools" ></c:url>
         
          <li><a href="${courses}">COURSES</a></li>
          <li><a href="${pools}">POOLS</a></li>
          <%
          	} else {          		
          %>
          <c:url var="mycourses" value="/mycourses" ></c:url>	
          <li><a href="${mycourses}">COURSES</a></li>
           
          <%
          	}
          %> 
                    
          <li><a href="<c:url value='/logout' />">LOGOUT</a></li>
        </ul>
      </div>
    </div>
  </nav>