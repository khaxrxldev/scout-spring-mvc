<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Application</h4>
				<ul class="list-group list-group-flush">
					<li class="list-group-item p-1 overflow-auto">
						<table id="assessmentTable" class="table table-striped compact" style="width:100%">
							<thead>
								<tr>
									<th>Assessment</th>
									<th>Evaluator</th>
									<th>Result</th>
									<th>Date</th>
									<th>Action</th>
							 	</tr>
							</thead>
							<tbody></tbody>
						</table>
					</li>
				</ul>
			</div>
		</div>
	</div>
	<div class="modal fade" id="insertModal" data-bs-backdrop="static" data-bs-keyboard="false">
		<div class="modal-dialog modal-lg">
			<div class="modal-content">
				<form id="insertForm" novalidate>
					<div class="modal-body">
						<div class="m-4 w-100 text-center">
							<h4 class="fw-bold">PERSEKUTUAN PENGAKAP MALAYSIA</h4>
							<h6>BORANG PERMOHONAN LENCANA <br><span id="name_label_id"></span></h6>
						</div>
						<div class="row g-1 pb-2">
							<div class="col-12">
								<input class="d-none" type="text" id="studentAssessmentId" name="studentAssessmentId" />
								<input class="d-none" type="text" id="studentId" name="studentId" />
								<input class="d-none" type="text" id="assessmentId" name="assessmentId" />
    							<label for="studentName" class="form-label">Full name</label>
    							<input type="text" class="form-control form-control-sm" id="studentName" disabled />
							</div>
						</div>
						<div class="row g-1 pb-2">
							<div class="col-lg-4 col-12">
    							<label for="studentIdentificationNo" class="form-label">IC Number</label>
    							<input type="text" class="form-control form-control-sm" id="studentIdentificationNo" disabled />
						     </div>
							<div class="col-lg-4 col-12">
    							<label for="studentBirthDate" class="form-label">Birth Date</label>
    							<input type="date" class="form-control form-control-sm" id="studentBirthDate" disabled />
							</div>
							<div class="col-lg-4 col-12">
    							<label for="studentAge" class="form-label">Age</label>
    							<input type="number" class="form-control form-control-sm" id="studentAge" disabled />
							</div>
						</div>
						<div class="row g-1 pb-2">
							<div class="col-12">
								<label for="studentAddress" class="form-label">Address</label>
								<textarea class="form-control" rows="3" id="studentAddress" disabled></textarea>
							</div>
						</div>
						<table id="normalTable">
							<tr>
								<th>LENCANA</th>
								<th>NO SIJIL</th>
								<th>TARIKH LULUS</th>
							</tr>
							<tbody id="badgeTable"></tbody>
						</table>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="resetForm('insertForm')" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">Close</button>
						<button id="apply_btn_id" type="submit" class="btn btn-sm btn-success">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function onWindowLoad() {
			onGetTableData();
		}
		
		function onGetTableData() {
			$.ajax({
				type: "GET",
		        url: "/core/student/assessments/applications/" + sessionStorage.getItem('login_id')
		    }).then(function(dataList) {
		    	for(let data of dataList) {
		    		let formattedDate = '';
		    		if (data.studentAssessmentResultDate) {
		    			let date = new Date(data.studentAssessmentResultDate);
		    			formattedDate = date.toLocaleDateString("en-GB");
		    		}
		    		
		    		$('#assessmentTable > tbody:last').append($('<tr>')
						.append($('<td>').append(data.assessmentResponse.assessmentName))
						.append($('<td>').append(data.evaluatorResponse ? data.evaluatorResponse.evaluatorName : ''))
						.append($('<td>').append(data.studentAssessmentResult ? data.studentAssessmentResult : ''))
						.append($('<td>').append(formattedDate))
						.append($('<td>').append('<button class="btn btn-sm btn-warning m-1" onclick="onOpenModal(\''+data.assessmentResponse.assessmentId+'\')">Apply</button>'))
					);
				}
		    	
				$('#assessmentTable').dataTable({
					"autoWidth": true,
					"info": false,
					"lengthChange": (window.innerWidth > 499) ? true : false,
					"pagingType": "simple_numbers",
					"bDestroy": true,
					"language": {
					    "decimal":        "",
					    "emptyTable":     "No data available in table",
					    "info":           "Showing _START_ to _END_ of _TOTAL_ entries",
					    "infoEmpty":      "Showing 0 to 0 of 0 entries",
					    "infoFiltered":   "(filtered from _MAX_ total entries)",
					    "infoPostFix":    "",
					    "thousands":      ",",
					    "lengthMenu":     "_MENU_",
					    "loadingRecords": "Loading...",
					    "processing":     "",
					    "search":         "",
					    "zeroRecords":    "No matching records found",
					    "paginate": {
					        "first":      "First",
					        "last":       "Last",
					        "next":       "&#11208;",
					        "previous":   "&#11207;"
					    },
					    "aria": {
					        "sortAscending":  ": activate to sort column ascending",
					        "sortDescending": ": activate to sort column descending"
					    }
					}
				});
		    });
		}
		
		$('#insertForm').submit(function(event){
			event.preventDefault();
			let formValidityStatus = checkFormValidity("insertForm");
			
			if (formValidityStatus) {
				let insertForm = form2js("insertForm", null, false);
				let insertJSON = JSON.stringify(insertForm);
				
				let formData = new FormData();
				formData.append("studentAssessmentRequest", new Blob([insertJSON], { type : "application/json" }));
				
				$.ajax({
					type: ($('#studentAssessmentId').val()) ? 'PUT' : 'POST',
			        url: "/core/studentAssessment",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	$('#insertModal').modal('hide');
			    	$('#assessmentTable').DataTable().clear().destroy();
			    	resetForm("insertForm");
			    	onGetTableData();
			    });
			}
		});
		
		function onOpenModal(assessmentId) {
			let studentId = sessionStorage.getItem('login_id');
			
			$.when(onGetStudent(studentId), onGetAssessment(assessmentId), onGetStudentAssessment(studentId, assessmentId), onGetStudentBadge(studentId)).done(function(a1, a2, a3, a4) {
			    $('#insertModal').modal('show');
				if (a3[0]) {
					$('#apply_btn_id').addClass('d-none');
			    	setForm('insertForm', a3[0]);
		    	} else {
					$('#apply_btn_id').removeClass('d-none');
		    		$('#studentId').val(studentId);
		    		$('#assessmentId').val(assessmentId);
		    	}
		    	
		    	if (a1[0]) {
		    		$('#studentName').val(a1[0].studentName);
		    		$('#studentIdentificationNo').val(a1[0].studentIdentificationNo);
		    		$('#studentAge').val(a1[0].studentAge);
		    		$('#studentAddress').val(a1[0].studentAddress);
		    		
		    		let jsDateTime = new Date(a1[0].studentBirthDate);
		    		let jsDateTimeOffset = new Date(jsDateTime.setMinutes(jsDateTime.getMinutes() - jsDateTime.getTimezoneOffset()));
		    		let birthDate = jsDateTimeOffset.toISOString().split('T')[0];
		    		$('#studentBirthDate').val(birthDate);
		    	}
		    	
		    	if (a2[0]) {
		    		$('#name_label_id').html(a2[0].assessmentName);
		    	}
		    	
		    	if (a4[0]) {
		    		$('#badgeTable').empty();
		    		
		    		for(let studentBadge of a4[0]) {
			    		let formattedDate = '';
			    		if (studentBadge.studentApprovalDate) {
			    			let date = new Date(studentBadge.studentApprovalDate);
			    			formattedDate = date.toLocaleDateString("en-GB");
			    		}
			    		
			    		if (studentBadge.studentBadgeStatus === 'APPROVE') {
			    			$('#normalTable > tbody:last').append($('<tr>')
								.append($('<td>').append(studentBadge.badge.badgeName))
								.append($('<td>').append(studentBadge.studentBadgeSerialNum))
								.append($('<td>').append(formattedDate))
							);
			    		}
		    		}
		    	}
			});
		}
		
		function onGetStudent(studentId) {
			return $.ajax({
				type: "GET",
		        url: "/user/student/" + studentId
		    });
		}
		
		function onGetAssessment(assessmentId) {
			return $.ajax({
				type: "GET",
		        url: "/core/assessment/" + assessmentId
		    });
		}
		
		function onGetStudentAssessment(studentId, assessmentId) {
			return $.ajax({
				type: "GET",
		        url: "/core/student/assessment/application/" + studentId + "/" + assessmentId
		    })
		}
		
		function onGetStudentBadge(studentId) {
			return $.ajax({
				type: "GET",
		        url: "/core/studentBadges/student/" + studentId
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>