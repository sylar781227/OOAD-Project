<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
  <title>Question Pools Page</title>
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
        <h3 class="custom-header-font">QUESTION POOLS PAGE</h3>
      </div>
    </div>
    
   
   <div class="row" id="create-question-pool">
	<c:url var="addAction" value="/admin/pools/add" ></c:url>
	<form:form action="${addAction}" commandName="questionPool">

				<div class="col-lg-offset-6 col-lg-2">
					<form:input type="name" id="title" class="form-control"
						path="title" placeholder="Pool Name" />
				</div>
				<div class="col-lg-4">
					<input type="submit" class="btn create-btn"
						value="<spring:message text="Create Question Pool"/>" />
				</div>
				<div>${missingQuestionPoolTitle}</div>
			</form:form>
	</div>
	
  	<div class="row">
		<c:if test="${!empty questionPools}">
			<div class="col-lg-offset-1 col-lg-4">
        <h4 class="custom-header-font">List of Question Pools</h4>
        <br><br>
        	
        	<ul class="list-common">	
			<c:forEach items="${questionPools}" var="questionPool">
				   <div>${questionPool.title}
					<a href="<c:url value='/admin/pools/edit/${questionPool.id}' />" >Edit</a>
					<a href="<c:url value='/admin/pools/remove/${questionPool.id}' />" >Delete</a>
				   </div>
			</c:forEach>
			</ul>
		</div>	
		</c:if>
	</div> 
</div>

<jsp:include page="./script.jsp" />

</body>
</html>
