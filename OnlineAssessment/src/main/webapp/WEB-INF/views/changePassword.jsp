<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>

<html>
<head>
  <title>Change Your Password</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/styles.css">
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.5.0/css/font-awesome.min.css" rel="stylesheet">
  <meta name="viewport" content="width=device-width; initial-scale=1.0; maximum-scale=1.0; minimum-scale=1.0; user-scalable=no; target-densityDpi=device-dpi" />
</head>
    <body>
	<nav class="navbar navbar-fixed-top">
		<div class="container navbarcontainer">
			<div class="navbar-header">
				<a class="navbar-brand custom-header-font" href="#introduction">TakeExam</a>
			</div>
		</div>
	</nav>
	
	  <div class="container" id="first-section">
    <div class="row" id="change-password-header">
      <div class="col-lg-12 text-center">
        <h3 class="custom-header-font">Change Your Password</h3>
      </div>
    </div>
    <div class="row">
      <div class="col-lg-offset-4 col-lg-4 text-center">
      
      <c:url var="changePassword" value="/changePassword"></c:url>
        <form:form class="form-signin" action="${changePassword}" commandName="changePassword"> 
     	  <form:input path="netID" type="hidden" readonly="true" disabled="true" />
		  <form:hidden path="netID" />
          <form:input path="oldPassword" type="password" id="oldPassword" class="form-control" placeholder="Old Password"/> <!--  required autofocus  required-->
          <form:input path="newPassword" type="password" id="newPassword" class="form-control" placeholder="New Password"/> <!--  required autofocus  required-->     
          <form:input path="newPasswordConfirm" type="password" id="newPasswordConfirm" class="form-control" placeholder="Confirm New Password"/> <!--  required autofocus  required-->
         
          <input type="submit" class="btn btn-lg btn-primary btn-block signinbutton"  value="Submit" />
        </form:form> 
      </div>
    </div>
  </div>  
        
<jsp:include page="./script.jsp" />       

</body>
</html>
