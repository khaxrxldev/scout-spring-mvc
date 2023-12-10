<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="row g-1">
				<div class="col-lg-6 col-12">
					<div id="studentDisplayId" class="d-none card w-100">
						<h4 class="card-header">Student</h4>
						<%@ include file="student_form.jspf" %>
					</div>
					<div id="teacherDisplayId" class="d-none card w-100">
						<h4 class="card-header">Teacher</h4>
						<%@ include file="teacher_form.jspf" %>
					</div>
					<div id="evaluatorDisplayId" class="d-none card w-100">
						<h4 class="card-header">Teacher</h4>
						<%@ include file="evaluator_form.jspf" %>
					</div>
				</div>
				<div class="col-lg-6 col-12">
					<div id="guardianDisplayId" class="d-none card w-100">
						<h4 class="card-header">Guardian</h4>
						<%@ include file="guardian_form.jspf" %>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onWindowLoad() {
			switch(sessionStorage.getItem('login_type')) {
				case 'TEACHER':
					$('#teacherDisplayId').removeClass('d-none');
					onGetTeacher();
				break;
				case 'EVALUATOR':
					$('#evaluatorDisplayId').removeClass('d-none');
					onGetEvaluator();
				break;
				case 'STUDENT':
					$('#studentDisplayId').removeClass('d-none');
					$('#guardianDisplayId').removeClass('d-none');
					onGetStudent();
					onGetGuardian();
				break;
			}
		}
		
		function onGetTeacher() {
			let teacherId = sessionStorage.getItem("login_id");
			
			$.ajax({
				type: "GET",
		        url: "/user/teacher/" + teacherId
		    }).then(function(data) {
			    setForm('teacherForm', data);
		    });
		}
		
		$('#teacherForm').submit(function(event){
			event.preventDefault();
			let formValidityStatus = checkFormValidity("teacherForm");
			
			if (formValidityStatus) {
				let teacherForm = form2js("teacherForm", null, false);
				let insertJSON = JSON.stringify(teacherForm);
				
				let formData = new FormData();
				formData.append("teacherRequest", new Blob([insertJSON], { type : "application/json" }));
				
				$.ajax({
					type: ($('#teacherId').val()) ? 'PUT' : 'POST',
			        url: "/user/teacher",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	if (data) {
			    		$('#teacherSuccess').removeClass('d-none').addClass('show');
			    	} else {
			    		$('#teacherError').removeClass('d-none').addClass('show');
			    	}
			    });
			}
		});
		
		function onGetEvaluator() {
			let evaluatorId = sessionStorage.getItem("login_id");
			
			$.ajax({
				type: "GET",
		        url: "/user/evaluator/" + evaluatorId
		    }).then(function(data) {
			    setForm('evaluatorForm', data);
		    });
		}
		
		$('#evaluatorForm').submit(function(event){
			event.preventDefault();
			let formValidityStatus = checkFormValidity("evaluatorForm");
			
			if (formValidityStatus) {
				let evaluatorForm = form2js("evaluatorForm", null, false);
				let insertJSON = JSON.stringify(evaluatorForm);
				
				let formData = new FormData();
				formData.append("evaluatorRequest", new Blob([insertJSON], { type : "application/json" }));
				
				$.ajax({
					type: ($('#evaluatorId').val()) ? 'PUT' : 'POST',
			        url: "/user/evaluator",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	if (data) {
			    		$('#evaluatorSuccess').removeClass('d-none').addClass('show');
			    	} else {
			    		$('#evaluatorError').removeClass('d-none').addClass('show');
			    	}
			    });
			}
		});
		
		function onGetStudent() {
			let studentId = sessionStorage.getItem("login_id");
			
			$.ajax({
				type: "GET",
		        url: "/user/student/" + studentId
		    }).then(function(data) {
			    setForm('studentForm', data);
		    });
		}
		
		$('#studentForm').submit(function(event){
			event.preventDefault();
			let formValidityStatus = checkFormValidity("studentForm");
			
			if (formValidityStatus) {
				let studentForm = form2js("studentForm", null, false);
				let insertJSON = JSON.stringify(studentForm);
				
				let formData = new FormData();
				formData.append("studentRequest", new Blob([insertJSON], { type : "application/json" }));
				
				$.ajax({
					type: ($('#studentId').val()) ? 'PUT' : 'POST',
			        url: "/user/student",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	if (data) {
			    		$('#studentSuccess').removeClass('d-none').addClass('show');
			    	} else {
			    		$('#studentError').removeClass('d-none').addClass('show');
			    	}
					onGetStudent($('#studentId').val());
			    });
			}
		});
		
		function onGetGuardian() {
			let studentId = sessionStorage.getItem("login_id");
			
			$.ajax({
				type: "GET",
		        url: "/common/guardian/student/" + studentId
		    }).then(function(data) {
		    	data.studentId = sessionStorage.getItem("login_id");
			    setForm('guardianForm', data);
		    });
		}
		
		$('#guardianForm').submit(function(event){
			event.preventDefault();
			let formValidityStatus = checkFormValidity("guardianForm");
			
			if (formValidityStatus) {
				let studentForm = form2js("guardianForm", null, false);
				let insertJSON = JSON.stringify(studentForm);
				
				let formData = new FormData();
				formData.append("guardianRequest", new Blob([insertJSON], { type : "application/json" }));
				
				$.ajax({
					type: ($('#guardianId').val()) ? 'PUT' : 'POST',
			        url: "/common/guardian",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	if (data) {
			    		$('#guardianSuccess').removeClass('d-none').addClass('show');
			    	} else {
			    		$('#guardianError').removeClass('d-none').addClass('show');
			    	}
			    	onGetGuardian($('#studentId').val());
			    });
			}
		});
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>