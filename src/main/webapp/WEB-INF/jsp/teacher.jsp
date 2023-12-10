<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/form2js@1.0.0/src/form2js.min.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			onGetTeacher();
		});
		
		function onGetTeacher() {
			$.ajax({
				type: "GET",
		        url: "/user/teacher/" + window.location.pathname.split("/").pop()
		    }).then(function(data) {
		    	$('#teacherId').val(data.teacherId);
		    	$('#teacherEmail').val(data.teacherEmail);
		    	$('#teacherPhoneNo').val(data.teacherPhoneNo);
		    	$('#teacherName').val(data.teacherName);
		    	$('#teacherAddress').val(data.teacherAddress);
		    	$('#teacherPassword').val(data.teacherPassword);
		    });
		}
	</script>
</head>
<body>
	<h1><%="INDEX"%></h1>
	<form id="teacherForm" method="post">
		<div>
			<span>teacherEmail</span>
			<input type="text" name="teacherId" id="teacherId"/>
			<input type="text" name="teacherEmail" id="teacherEmail"/>
		</div>
		<div>
			<span>teacherPhoneNo</span>
			<input type="text" name="teacherPhoneNo" id="teacherPhoneNo"/>
		</div>
		<div>
			<span>teacherName</span>
			<input type="text" name="teacherName" id="teacherName"/>
		</div>
		<div>
			<span>teacherAddress</span>
			<input type="text" name="teacherAddress" id="teacherAddress"/>
		</div>
		<div>
			<span>teacherPassword</span>
			<input type="text" name="teacherPassword" id="teacherPassword"/>
		</div>
		<button type="button" id="submit_btn">submit</button>
	</form>
	<script type="text/javascript">
		$('#submit_btn').click(function(event){
			event.preventDefault();
			
			let teacherForm = form2js("teacherForm", null, false);
			let teacherJSON = JSON.stringify(teacherForm);
			
			let formData = new FormData();
			formData.append("teacherRequest", new Blob([teacherJSON], { type : "application/json" }));
			
			$.ajax({
				type: "PUT",
		        url: "/user/teacher",
		        processData: false,
		        contentType: false,
		        cache: false,
		        data: formData
		    }).then(function(data) {
				console.log(data);
		    });
		});
	</script>
</body>
</html>