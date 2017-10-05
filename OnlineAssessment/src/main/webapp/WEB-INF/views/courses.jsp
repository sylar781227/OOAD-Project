<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Instructor Courses Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=no; target-densityDpi=device-dpi" />
</head>


<body>
<jsp:include page="./nav.jsp" />

 <div class="container" id="first-section">
    <div>${missingCourseNameMessage}</div>
 	<div class="row" id="login-header">
      <div class="col-lg-12 text-center">
        <h3 class="custom-header-font">COURSES PAGE</h3>
      </div>
    </div>
    
    <%-- <p> <%= (String)request.getSession().getAttribute("username") %> </p> --%>
    
    <div class="row" id="create-course">
	<c:url var="addAction" value="/admin/course/add" ></c:url>
	<form:form action="${addAction}" commandName="course">
		
      <div class="col-lg-offset-6 col-lg-2">
		<c:if test="${!empty courses.courseName}">
               
				<form:input path="id" type="hidden" readonly="true" disabled="true" />
				<form:hidden path="id" />


		</c:if>
	
				<form:input type="name" id="courseName" class="form-control" path="courseName" placeholder="New Course Name"/>
				
		</div>
			<div class="col-lg-4">
				<c:if test="${!empty course.courseName}">
					<input type="submit"
						value="<spring:message text="Edit Course"/>" />
				</c:if>
				<c:if test="${empty course.courseName}">
				
				<!--  	<input type="submit"
						value="<spring:message text="Add Course"/>" /> 
				<button class="btn create-btn"> -->
				
				<input type="submit" class="btn create-btn" value="<spring:message text="Add Course"/>" />
					
				</c:if>
			 
		
	</div>
	</form:form>
	</div>
	
	<div class="row">
		<c:if test="${!empty listCourses}">
			<div class="col-lg-offset-1 col-lg-4">
        <h4 class="custom-header-font">List of Courses</h4>
        <br><br>
        	
        	<ul class="list-common">	
			<c:forEach items="${listCourses}" var="course">
				<!--  	<td>${course.id}</td>
					<td>${course.courseName}</td>
					<td><a href="<c:url value='/course/edit/${course.id}' />" >Edit</a></td>
					<td><a href="<c:url value='/course/remove/${course.id}' />" >Delete</a></td>
				-->
			<li>${course.courseName}
					<!--  	<button class="btn view-btn">View</button> -->
					<a href="<c:url value='/admin/course/edit/${course.id}' />" ><button class="btn edit-btn">Edit</button></a>
					<a href="<c:url value='/admin/course/remove/${course.id}' />" ><button class="btn delete-btn">Delete</button></a>
			</li> 
			</c:forEach>
			</ul>
		</div>	
		</c:if>
	</div>
</div>

<jsp:include page="./script.jsp" />
</body>
</html>
