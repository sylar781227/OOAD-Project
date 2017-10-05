<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
<title>Upload File Request Page</title>
</head>
<body>

<c:url var="addAction" value="/uploadFile" ></c:url>

	<form method="POST" action="${addAction}" enctype="multipart/form-data">
		File to upload: <input type="file" name="file">
		
		<input type="submit" value="Upload"> Press here to upload the file!
	</form>	
</body>
</html>