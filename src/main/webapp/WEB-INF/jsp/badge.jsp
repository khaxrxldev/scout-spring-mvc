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
		        url: "/user/badge/" + window.location.pathname.split("/").pop()
		    }).then(function(data) {
		    	$('#badgeId').val(data.badgeId);
		    	$('#badgeName').val(data.badgeName);
		    	$('#badgeSerialNo').val(data.badgeSerialNo);
		 
		    });
		}
	</script>
</head>
<body>
	<h1><%="INDEX"%></h1>
	<form id="teacherForm" method="post">
		<div>
			<span>Badge Name</span>
			<input type="text" name="badgeId" id="badgeId"/>
			<input type="text" name="badgeName" id="badgeName"/>
		</div>
		<div>
			<span>Badge Serial No</span>
			<input type="text" name="badgeSerialNo" id="badgeSerialNo"/>
		</div>
		
		<button type="button" id="submit_btn">submit</button>
	</form>
	<script type="text/javascript">
		$('#submit_btn').click(function(event){
			event.preventDefault();
			
			let badgeForm = form2js("badgeForm", null, false);
			let badgeJSON = JSON.stringify(badgeForm);
			
			let formData = new FormData();
			formData.append("badgeRequest", new Blob([badgeJSON], { type : "application/json" }));
			
			$.ajax({
				type: "PUT",
		        url: "/user/badge",
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