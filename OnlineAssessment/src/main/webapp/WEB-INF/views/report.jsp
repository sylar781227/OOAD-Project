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
        <h3 class="custom-header-font">STUDENTS' REPORT</h3>
      </div>
    </div>
    
    <c:if test="${!empty dresults}">
    <div class="row">
      <div class="col-lg-offset-1 col-lg-10">
        <h4 class="custom-header-font">List of Student Questions</h4>
        <br><br>
        <div class="row">
          <div class="col-lg-4">Student Name</div>
          <div class="col-lg-2">Title</div>
          <div class="col-lg-2">Result Msg</div>
          <div class="col-lg-2">Score</div>
          <div class="col-lg-2">Code(click to expand)</div>
        </div>
        <br>
        <c:forEach items="${dresults}" var="dr">
	        <div class="row">
	          <div class="col-lg-4">${dr.r.id.userName}</div>
	          <div class="col-lg-2">${dr.ques.title}</div>
	          <div class="col-lg-2">${dr.r.resultMessage}</div>
	          <div class="col-lg-2"> ${dr.r.score}</div>
	          <div class="col-lg-2"><button onclick='toggleDisplay("${dr.ques.id}${dr.r.id.userName}")'>View Code</button></div>
	        </div>
	        <br><br>
	        <div class="row student-code-display" id="student-code-${dr.ques.id}${dr.r.id.userName}">	       
	          <textarea name="name" rows="8" cols="100">${dr.r.submitedCode}</textarea>
	        </div>
      	</c:forEach>
      </div>
    </div>
    </c:if>
  </div>
  
<jsp:include page="./script.jsp" />
</body>
</html>