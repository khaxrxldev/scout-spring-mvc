<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Document</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	<script src="https://cdn.jsdelivr.net/npm/form2js@1.0.0/src/form2js.min.js"></script>
</head>
<body>
	<form id="teacherLoginForm" onsubmit="onLogin(event, 'TEACHER', '/user/teacher/login', '/user/teacher/view', 'teacherLoginForm')" method="post">
		<h3>TEACHER</h3>
		<div>
			<span>username</span>
			<input type="text" name="loginUsername" id="loginUsername" value=""/>
		</div>
		<div>
			<span>password</span>
			<input type="text" name="loginPassword" id="loginPassword" value=""/>
		</div>
		<button type="submit">submit</button>
	</form>
	<br>
	<form id="evaluatorLoginForm" onsubmit="onLogin(event, 'EVALUATOR', '/user/evaluator/login', '/core/student/assessment/evaluation', 'evaluatorLoginForm')" method="post">
		<h3>EVALUATOR</h3>
		<div>
			<span>username</span>
			<input type="text" name="loginUsername" id="loginUsername" value=""/>
		</div>
		<div>
			<span>password</span>
			<input type="text" name="loginPassword" id="loginPassword" value=""/>
		</div>
		<button type="submit">submit</button>
	</form>
	<br>
	<form id="studentLoginForm" onsubmit="onLogin(event, 'STUDENT', '/user/student/login', '/common/membership/view', 'studentLoginForm')" method="post">
		<h3>STUDENT</h3>
		<div>
			<span>username</span>
			<input type="text" name="loginUsername" id="loginUsername" value=""/>
		</div>
		<div>
			<span>password</span>
			<input type="text" name="loginPassword" id="loginPassword" value=""/>
		</div>
		<button type="submit">submit</button>
	</form>
	<script type="text/javascript">
		function onLogin(event, userType, loginURL, redirectURL, loginFormId) {
			event.preventDefault();
			
			let loginForm = form2js(loginFormId, null, false);
			let loginJSON = JSON.stringify(loginForm);
			
			let formData = new FormData();
			formData.append("loginRequest", new Blob([loginJSON], { type : "application/json" }));
			
			$.ajax({
				type: "POST",
		        url: loginURL,
		        processData: false,
		        contentType: false,
		        cache: false,
		        data: formData
		    }).then(function(data) {
				if (data.loginStatus) {
					sessionStorage.setItem("login_id", data.loginId);
					sessionStorage.setItem("login_type", userType);
					window.location.href = redirectURL;
				} else {
					alert(data.loginError);
				}
		    });
		}
	</script>
</body>
</html>
