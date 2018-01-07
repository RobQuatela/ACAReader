<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

<!-- jQuery library -->
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script>
	function uploadFile() {
		var x = document.getElementById("file");
		var txt = "";
		var path = "";

		if('files' in x) {
			if(x.files.length == 0) {
				txt = "Select a file";
			} else {
				for (var i = 0; i < x.files.length; i++) {
					var file = x.files[i];
					if('name' in file) {
						txt += "<br><strong>" + file.name + "</strong><br>";
						path += file.webkitRelativePath;
					}
				}
			}
		} else {
			if(x.value == "") {
				txt += "Select one or more files";
			} else {
				txt += "The files property is not supported by your browser";
				txt += "<br>The path of the selected file: " + x.value;
			}
		}

		document.getElementById("demo").innerHTML = txt;
		document.getElementById("filePath").value = path;
	}
</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body style="background-color: #f2f2f2">
	<div class="jumbotron" style="background-color: #66b3ff;">
		<h1 style="color: #f2f2f2;">ACA File Reader</h1>
		<p style="color: #f2f2f2;">The App to convert Paycom's ACA Spreadsheet into an importable
			template</p>
	</div>
	<div class="container well">
		<form name="frmUpload" method="post" action="Upload"
			enctype="multipart/form-data">
			<div class="page-header">
				<h2>Select the Paycom Spreadsheet to import</h2>
			</div>
			<input type="file" id="file" name="uploadFile" multiple="true"
				onchange="uploadFile()">
			<p id="demo"></p>
			<button type="submit" class="btn-default" name="btnSubmit">
				<span class="glyphicon glyphicon-cloud-upload"></span> Upload
			</button>
			<c:set var="processed" value="${processed }" />
			<c:if test="${processed != null }">
				<div class="page-header">
					<h2>Results</h2>
				</div>
				<div class="row">
					<div class="col-lg-2 text-right">
						<strong>Records Processed:</strong>
					</div>
					<div class="col-lg-10">${processed[1] }</div>
				</div>
				<div class="row">
					<div class="col-lg-2 text-right">
						<strong>Records Created:</strong>
					</div>
					<div class="col-lg-10">${processed[0] }</div>
				</div>
			</c:if>
		</form>
	</div>
</body>
</html>