<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Instructor Exams Page</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=no; target-densityDpi=device-dpi" />
</head>

<body>
<jsp:include page="./nav.jsp" />

<div class="container" id="first-section">
    <div class="row" id="login-header">
      <div class="col-lg-12 text-center">
        <h3 class="custom-header-font">COURSE 1 - EXAMS PAGE</h3>
      </div>
    </div>

    <div class="row text-center">
      <br><br>
      <h4 class="custom-header-font">Manage Students in the Course</h4>
      <br><br>
    </div>

		<div class="row">
			<div class="col-lg-offset-1 col-lg-4">
				<h4 class="custom-header-font">List of Students</h4>
				<br>
				<br>
				<ul class="list-common">
					<c:if test="${!empty course.students}">
						<c:forEach items="${course.students}" var="stu">
							<li>${stu.firstName}${stu.lastName}
								<a href="<c:url value='/admin/course/${course.id}/removeStudent/${stu.userName}' />" >
								<button class="btn delete-btn">Remove</button>
								</a>
							</li>
						</c:forEach>
					</c:if>
				</ul>
			</div>
	
	  <div class="col-lg-7">
	  <c:url var="addStudent" value="/admin/course/${course.id}/addStudent"></c:url>
				<form:form action="${addStudent}" commandName="student">
					<div class="row" id="add-student">
						<div class="col-lg-3">
							<form:input type="name" path="userName" class="form-control"
								placeholder="User Name" />
						</div>
					
						<div class="col-lg-3">
							<form:input type="name" path="firstName" class="form-control"
								placeholder="First Name" />
						</div>

						<div class="col-lg-3">
							<form:input type="name" path="lastName" class="form-control"
								placeholder="Last Name" />
						</div>
						<div class="col-lg-4">

							<input type="submit" class="btn create-btn"
								value="<spring:message text="Add Student"/>" />
						</div>
					</div>
				</form:form>

				<div class="row" id="bulk-upload">
				<c:url var="uploadAction" value="/admin/uploadFile/${course.id}" ></c:url>
					<form method="POST" action="${uploadAction}" enctype="multipart/form-data">
					<div class="col-lg-3">	 
						<input type="file" name="file">
					</div><br><br>				 
					<div class="col-lg-4">	
						<input class="btn create-btn" type="submit" value="Upload">
					</div>	
					</form>
				</div>
			</div>
</div>

 <div class="row" id="create-exam">
 	<c:url var="addExam" value="/admin/course/${course.id}/addExam" ></c:url>
	<form:form action="${addExam}" commandName="exam">
 		<form:input path="id" type="hidden" readonly="true" disabled="true" />
		<form:hidden path="id" />
 		
      <div class="col-lg-offset-6 col-lg-2">
      	<form:input path="title" type="name" id="examName" class="form-control" placeholder="New Exam Name"/>
      </div>
      <div class="col-lg-offset-6 col-lg-2">
      	<form:input path="timeLimit" type="name" id="timeLimit" class="form-control" placeholder="Enter Time Limit"/>
      </div>
      <div class="col-lg-4">
      	<input class="btn create-btn" type="submit" value="<spring:message text="Create Exam"/>" />
      </div>
      
     </form:form> 
    </div>

		<div class="row">
			<div class="col-lg-offset-1 col-lg-4">
				<h4 class="custom-header-font">List of Exam</h4>
				<br>
				<br>
				<c:if test="${!empty course.exams}">
					<ul class="list-common">
						<c:forEach items="${course.exams}" var="exam">
							<li>${exam.title}<a
								href="<c:url value='/admin/course/${course.id}/exam/${exam.id}' />">
									<button class="btn edit-btn">Edit</button>
							</a> 
							<a
								href="<c:url value='/admin/getreport/course/${course.id}/exam/${exam.id}' />">
									<button class="btn edit-btn">View Report</button>
							</a>
							
							<a
								href="<c:url value='/admin/course/${course.id}/remove/${exam.id}' />">
									<button class="btn delete-btn">Delete</button>
							</a>
							</li>
						</c:forEach>
					</ul>
				</c:if>
			</div>
		</div>
</div>

<jsp:include page="./script.jsp" />

</body>
</html>
