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
        <h3 class="custom-header-font">QUESTION PAGE</h3>
      </div>
    </div>
  


<div class="row">


<c:url var="addAction" value="/admin/course/${courseid}/exam/${examid}/ques/${quesid}/update" ></c:url>

<form:form action="${addAction}" commandName="question">

      <div class="col-lg-offset-1 col-lg-10">
        <h4 class="custom-header-font">Question Details</h4>
        <div class="text-right">
          <button class="btn">Save Question</button>
        </div>
        <br><br>
        
        <form:input path="id" type="hidden" readonly="true" disabled="true" />
		<form:hidden path="id" />
        
        <div class="row question-area">
          Question Title
        </div>
        <br>
       <div class="row answer-area">
			<form:input path="title" />
	   </div> 
        <br>
        <div class="row question-area">
          Enter The Question Description
        </div>
        <br>
        <div class="row answer-area">
        <form:textarea path="description" row="5" cols="80" placeholder="Enter Description"/>	        
        </div>
        <br>
		<div class="row question-area">
          Enter the Code Template Class Name
        </div>
        <br>
        <div class="row answer-area">
          <form:input path="submitCodeClassName" />	
        </div>
        <br>
        <div class="row question-area">
          Sample Template code goes here.
        </div>
        <br>
        <div class="row answer-area">
			<form:textarea row="50" cols="80" path="codetemplate" placeholder="Code Template"/>
	    </div>
        <br>
		<div class="row question-area">
          Enter the TestCase Code
        </div>
        <br>
        <div class="row answer-area">
         <form:textarea row="250" cols="80" path="testcase" placeholder="Test Case Code"/>
        </div>
        <br>
        <div class="row question-area">
          Enter the Total Score for the Question
        </div>
        <br>
        <div class="row answer-area">
         <form:input path="score" placeholder="Enter the score for this question"/>
        </div>
        <br>
        <div class="text-center">
        <input class="btn" type="submit" value="<spring:message text="Save Question"/>" />
        </div>
      </div>
      
     </form:form> 
    </div>
</div>

<jsp:include page="./script.jsp" />
</body>
</html>
