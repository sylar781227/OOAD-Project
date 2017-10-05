<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Courses Page</title>
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
        <h3 class="custom-header-font">EXAM 1 - QUESTIONS PAGE</h3>
      </div>
    </div>  
	
	<div class="row" id="create-course">
	<c:url var="addQuestion" value="/admin/course/${courseid}/exam/${examid}/addQuestion" ></c:url>
	<form:form action="${addQuestion}" commandName="question">
		<form:input path="id" type="hidden" readonly="true" disabled="true" />
		<form:hidden path="id" />
	
      <div class="col-lg-6 col-lg-offset-1">
      <form:input path="title"  type="name" id="newQuestion" class="form-control" placeholder="New Question"/>
      </div>
      <div class="col-lg-4">
      	<input class="btn create-btn" type="submit" value="<spring:message text="Create Question"/>" />
      </div>
      </form:form>
    </div>
    
    <div class="row" id="create-course">
	<c:url var="addQuestionById" value="/admin/course/${courseid}/exam/${examid}/submitQuestionById" ></c:url>
	<form:form action="${addQuestionById}" commandName="question">
		
	  <div class="col-lg-6 col-lg-offset-1">
      <form:input path="id"  type="name" id="newQuestion" class="form-control" placeholder="Enter Question ID"/>
      </div>
		
      <div class="col-lg-4">
      	<input class="btn create-btn" type="submit" value="<spring:message text="Add Question"/>" />
      </div>
      </form:form>
    </div>
    
		<div class="row">
			<div class="col-lg-offset-1 col-lg-10">
				<h4 class="custom-header-font">List of Questions</h4>
				<br> <br>
				<c:if test="${!empty questions}">
					<ul class="list-common">
						<c:forEach items="${questions}" var="ques">
							<li>${ques.title}<a
								href="<c:url value='/admin/course/${courseid}/exam/${examid}/ques/${ques.id}' />">
									<button class="btn edit-btn">Edit</button>
							</a> <a
								href="<c:url value='/admin/course/${courseid}/exam/${examid}/deleteques/${ques.id}' />">
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