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
			onGetEvaluator();
		});
		
		function onGetEvaluator() {
			$.ajax({
				type: "GET",
		        url: "/user/evaluator/" + window.location.pathname.split("/").pop()
		    }).then(function(data) {
		    	$('#evaluatorId').val(data.evaluatorId);
		    	$('#evaluatorEmail').val(data.evaluatorEmail);
		    	$('#evaluatorPassword').val(data.evaluatorPassword);
		    	$('#evaluatorName').val(data.evaluatorName);
		    	$('#evaluatorPosition').val(data.evaluatorPosition);
		    });
		}
	</script>
</head>
<body>
	<h1><%="INDEX"%></h1>
	<form id="evaluatorForm" method="post">
		<div>
			<span>evaluatorEmail</span>
			<input type="text" name="evaluatorId" id="evaluatorId"/>
			<input type="text" name="evaluatorEmail" id="evaluatorEmail"/>
		</div>
		<div>
			<span>evaluatorPassword</span>
			<input type="text" name="evaluatorPassword" id="evaluatorPassword"/>
		</div>
		<div>
			<span>evaluatorName</span>
			<input type="text" name="evaluatorName" id="evaluatorName"/>
		</div>
		<div>
			<span>evaluatorPosition</span>
			<input type="text" name="evaluatorPosition" id="evaluatorPosition"/>
		</div>
		<button type="button" id="submit_btn">submit</button>
	</form>
	<script type="text/javascript">
		$('#submit_btn').click(function(event){
			event.preventDefault();
			
			let evaluatorForm = form2js("evaluatorForm", null, false);
			let evaluatorJSON = JSON.stringify(evaluatorForm);
			
			let formData = new FormData();
			formData.append("evaluatorRequest", new Blob([evaluatorJSON], { type : "application/json" }));
			
			$.ajax({
				type: "PUT",
		        url: "/user/evaluator",
		        processData: false,
		        contentType: false,
		        cache: false,
		        data: formData
		    }).then(function(data) {
		    	onGetEvaluators();
		    });
		});
	</script>
</body>
</html>