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
        <h3 class="custom-header-font">COURSE EXAMS PAGE</h3>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-offset-1 col-lg-4">
        <h4 class="custom-header-font">List of Exam</h4>
        <br><br>
        <c:if test="${!empty course.exams}">
        <ul class="list-common">
        <c:forEach items="${course.exams}" var="exam">
          <li>${exam.title}
          <a href="<c:url value='/course/${course.id}/openexam/${exam.id}/uid/${userid}' />" >
          	<button class="btn view-btn">View</button>
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
