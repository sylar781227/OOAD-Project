<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Edit Question Pool Page</title>
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
        <h3 class="custom-header-font">QUESTIONS POOL</h3>
      </div>
    </div>
    
    <div class="row" id="create-course">
 	<c:url var="addQuestion" value="/admin/pools/${pool.id}/addQuestion"></c:url>
	  <form:form action="${addQuestion}" commandName="question">
      	<div class="col-lg-4">
        	<button class="btn create-btn">Add Question</button>
      	</div>
      </form:form>
    </div>
    
    <div class="row">
      <div class="col-lg-offset-1 col-lg-10">
        <h4 class="custom-header-font">List of Question Pools</h4>
        <br><br>
        <div class="row">
          <div class="col-lg-2">Question ID</div>
          <div class="col-lg-2">Question Title</div>
          <div class="col-lg-2">Course</div>
          <div class="col-lg-2">Section</div>
          <div class="col-lg-2">Difficulty</div>
          <div class="col-lg-2">View</div>
        </div>
        <br>
        <c:if test="${!empty pool.questionsProp}">
         <c:forEach items="${pool.questionsProp}" var="questionprop">
	        <div class="row">
	          <div class="col-lg-2">${questionprop.ques.id}</div>
	          <div class="col-lg-2">${questionprop.ques.title}</div>
	          <div class="col-lg-2">${questionprop.course}</div>
	          <div class="col-lg-2">${questionprop.section}</div>
	          <div class="col-lg-2">${questionprop.difficulty}</div>
	          <div class="col-lg-2"><a href="<c:url value='/admin/pools/${pool.id}/edit/${questionprop.propID}' />">View</a></div>
	        </div>
	     </c:forEach>
        </c:if>
      </div>
    </div>
  </div>
  
<jsp:include page="./script.jsp" />
</body>
</html>
