<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page session="false"%>
<html>
<head>
  <title>Login Page</title>
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
    <div class="row">
   		<div id="exam-timer"></div>
  		<div class="col-lg-offset-1 col-lg-10">
  		
  		<div class="row">
          <div class="col-lg-2 col-lg-offset-5 text-center">
            <div id="exam-timer"></div>
          </div>
        </div>
  		<br><br>

  		
        <h4 class="custom-header-font">List of Questions</h4>
        
 <!--         <div class="text-right">
          <button class="btn"  onclick="countdown(${timeLimit})" >Submit Exam</button>
        </div>
        <br><br>
-->		
		<c:if test="${!started}">
			<c:if test="${!empty questions}">
			
			<c:forEach items="${questions}" var="ques">
			
				<form:form commandName="submission">
				<form:input type="hidden" path="id.questionID" value="${ques.id}" />
				
				<form:input type="hidden" path="id.userName" value="${userid}" />
				
				<form:input type="hidden" path="id.examID" value="${examid}" />
						
		        <div class="row question-area">
		         	Question Title
		        </div>
		        <br>
		        <div class="row answer-area">
		         	${ques.title}
		        </div>
		        <br>
		        <div class="row question-area">
		         	Question Description
		        </div>
		        <br>
		        <div class="row answer-area">
		         	${ques.description}
		        </div>
		        <br>
		         <div class="row question-area">
		         	Question Code Template
		        </div>
		        <br>
		        <div class="rows answer-area">
		          <textarea rows="5" cols="100">
		            ${ques.codetemplate}
		          </textarea>
		        </div>
		        <br>
		        <div class="row question-area">
		         	Question Answer Section
		        </div>
		        <br>
		        <div class="rows answer-area">
		        	<form:textarea rows="5" cols="100" path="submitedCode" />
		        </div>
		        
		        <div class="question-feedback"> </div>
		        <div class="text-center">
		          <button type="button" class="btn" id="${ques.id}">Submit</button>	          
		        </div>
		        
		       </form:form> 
	        </c:forEach>
	        </c:if>
        </c:if>
        
        <c:if test="${started}">
        	<c:if test="${!empty dresults}">
			
  			<div class="row">
          		<div class="col-lg-4">
           			 Total Score - ${total} 
          		</div>
        	</div>
			
			<c:forEach items="${dresults}" var="dr">
							
		        <div class="row question-area">
		         	Question Title
		        </div>
		        <br>
		        <div class="row answer-area">
		         	${dr.ques.title}
		        </div>
		        <br>
		        <div class="row question-area">
		         	Question Description
		        </div>
		        <br>
		        <div class="row answer-area">
		         	${dr.ques.description}
		        </div>
		        <br>
		         <div class="row question-area">
		         	Question Code Template
		        </div>
		        <br>
		        <div class="rows answer-area">
		          <textarea rows="5" cols="100">
		            ${dr.ques.codetemplate}
		          </textarea>
		        </div>
		        <br>
		        <div class="row question-area">
		         	Question Answer Section
		        </div>
		        <br>
		        <div class="rows answer-area">
		        	<textarea rows="5" cols="100">
		        	 ${dr.r.submitedCode}
		        	</textarea>
		        </div>
		        <br>
		         <div class="row question-area">
		         	Result Message
		        </div>
		        <br>
		        <div class="row answer-area">
		         	${dr.r.resultMessage} ${dr.r.score}
		        </div>
	        </c:forEach>
	        </c:if>
        	
        
        </c:if>
        
        
      </div>
  </div>
  </div>
  
<jsp:include page="./script.jsp" />
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/timer.js"></script>
<script type="text/javascript">
  
  $(document).ready(function(){
	   
	  
	  
	  $('.btn').click(function(){
	  		
		  var div = this;
		  var ques_id = this.id;
		  
		  var str = $(this).closest('form').serialize();
			
	  $.ajax({
	      type:"post",
	      data:str,
	      url:"${pageContext.request.contextPath}/course/${courseid}/exam/${examid}/quessubmit/"+ques_id,
	      async: false,
	      success: function(res){	     
	    	 $(div).parent().prev().text(res);
	      }
	  });
	}); 
	  		if(!("${started}" === "true")){
			 countdown('${timeLimit}'); 
	  		}
  });
  
  </script>
</body>