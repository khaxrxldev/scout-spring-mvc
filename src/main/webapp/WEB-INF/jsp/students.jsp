<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Students</h4>
				<ul class="list-group list-group-flush">
					<li class="list-group-item p-1">
						<button type="button" class="btn btn-sm btn-primary" onclick="onOpenModal('', 'INSERT')">Add Student</button>
					</li>
					<li class="list-group-item p-1 overflow-auto">
						<table id="studentTable" class="table table-striped compact" style="width:100%">
							<thead>
								<tr>
									<th>Name</th>
									<th>IC Number</th>
									<th>Email</th>
									<th>Phone Number</th>
									<th>Gender</th>
									<th>Race</th>
									<th>Religion</th>
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
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<input class="d-none" type="text" id="studentId" name="studentId" />
    							<label for="studentEmail" class="form-label">Email</label>
    							<input type="text" class="form-control form-control-sm" id="studentEmail" name="studentEmail" required />
    							<div class="d-none invalid-feedback">
						        	Please provide email address.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentPhoneNo" class="form-label">Phone number</label>
    							<input type="text" class="form-control form-control-sm" id="studentPhoneNo" name="studentPhoneNo" required />
    							<div class="d-none invalid-feedback">
						        	Please provide phone number.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="studentName" class="form-label">Full name</label>
    							<input onkeyup="setInputUppercase(event)" type="text" class="form-control form-control-sm" id="studentName" name="studentName" required />
    							<div class="d-none invalid-feedback">
						        	Please provide full name.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentPassword" class="form-label">Password</label>
    							<input type="text" class="form-control form-control-sm" id="studentPassword" name="studentPassword" required />
    							<div class="d-none invalid-feedback">
						        	Please provide password.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-12">
								<label for="studentAddress" class="form-label">Address</label>
								<textarea onkeyup="setInputUppercase(event)" class="form-control" rows="3" id="studentAddress" name="studentAddress" required></textarea>
    							<div class="d-none invalid-feedback">
						        	Please provide address.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="studentRace" class="form-label">Race</label>
    							<select class="form-select form-select-sm" id="studentReligion" name="studentReligion" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="ISLAM">ISLAM</option>
    								<option value="BUDDHIST">BUDDHIST</option>
    								<option value="HINDU">HINDU</option>
    								<option value="CHRISTIAN">CHRISTIAN</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide race.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentReligion" class="form-label">Religion</label>
    							<select class="form-select form-select-sm" id="studentRace" name="studentRace" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="MALAY">MALAY</option>
    								<option value="CHINESE">CHINESE</option>
    								<option value="INDIAN">INDIAN</option>
    								<option value="KADAZAN">KADAZAN</option>
    								<option value="DAYAK">DAYAK</option>
    								<option value="OTHER">OTHER</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide religion.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="studentIdentificationNo" class="form-label">IC Number</label>
    							<input type="text" class="form-control form-control-sm" id="studentIdentificationNo" name="studentIdentificationNo" required />
    							<div class="d-none invalid-feedback">
						        	Please provide IC Number.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentGender" class="form-label">Gender</label>
    							<select class="form-select form-select-sm" id="studentGender" name="studentGender" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="MALE">MALE</option>
    								<option value="FEMALE">FEMALE</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide gender.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="studentBirthDate" class="form-label">Birth Date</label>
    							<input type="date" class="form-control form-control-sm" id="studentBirthDate" name="studentBirthDate" required />
    							<div class="d-none invalid-feedback">
						        	Please provide birth date.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentAge" class="form-label">Age</label>
    							<input type="number" class="form-control form-control-sm" id="studentAge" name="studentAge" required />
    							<div class="d-none invalid-feedback">
						        	Please provide age.
						     	</div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="resetForm('insertForm'); enableForm('insertForm')" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">Cancel</button>
						<button type="submit" class="btn btn-sm btn-success">Submit</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="modal fade" id="deleteModal">
	  	<div class="modal-dialog">
	    	<div class="modal-content">
	      		<div class="modal-header">
	        		<h1 class="modal-title fs-5" id="exampleModalLabel">Confirmation</h1>
	        		<button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
	      		</div>
	      		<input type="hidden" id="deleteId"/>
	      		<div class="modal-body">Are you sure to delete the information?</div>
	      		<div class="modal-footer">
	        		<button type="button" class="btn btn-sm btn-success" data-bs-dismiss="modal">Cancel</button>
	        		<button type="button" onclick="onDelete()" class="btn btn-sm btn-danger">Delete</button>
	      		</div>
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
		        url: "/user/students"
		    }).then(function(dataList) {
		    	for(let data of dataList) {
		    		$('#studentTable > tbody:last').append($('<tr>')
						.append($('<td>').append(data.studentName))
						.append($('<td>').append(data.studentIdentificationNo))
						.append($('<td>').append(data.studentEmail))
						.append($('<td>').append(data.studentPhoneNo))
						.append($('<td>').append(data.studentGender))
						.append($('<td>').append(data.studentRace))
						.append($('<td>').append(data.studentReligion))
						.append($('<td>').append('<button class="btn btn-sm btn-secondary m-1" onclick="onOpenModal(\''+data.studentId+'\', \'DISPLAY\')">View</button>' + '<button class="btn btn-sm btn-warning m-1" onclick="onOpenModal(\''+data.studentId+'\', \'UPDATE\')">Edit</button>' + '<button class="btn btn-sm btn-danger m-1" onclick="onPromptDelete(\''+data.studentId+'\')">Delete</button>'))
					);
				}
		    	
				$('#studentTable').dataTable({
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
		
		$('#insertForm').submit(function(event) {
			event.preventDefault();
			let formValidityStatus = checkFormValidity("insertForm");
			
			if (formValidityStatus) {
				let insertForm = form2js("insertForm", null, false);
				let insertJSON = JSON.stringify(insertForm);
				
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
			    	$('#insertModal').modal('hide');
			    	$('#studentTable').DataTable().clear().destroy();
			    	resetForm("insertForm");
			    	onGetTableData();
			    });
			}
		});
		
		function onOpenModal(updateId, action) {
			switch(action) {
  				case 'UPDATE':
					$.ajax({
						type: "GET",
				        url: "/user/student/" + updateId
				    }).then(function(data) {
					    $('#insertModal').modal('show');
					    setForm('insertForm', data);
				    });
				break;
				case 'INSERT':
			    	$('#insertModal').modal('show');
				break;
				case 'DISPLAY':
					window.location.href='/user/student/update/' + updateId;
				break;
			}
		}
		
		function onPromptDelete(deleteId) {
			$('#deleteModal').modal('show');
			$('#deleteId').val(deleteId);
		}
		
		function onDelete() {
			$.ajax({
				type: "DELETE",
		        url: "/user/student/" + $('#deleteId').val()
		    }).then(function(data) {
		    	if (data) {
					$('#studentTable').DataTable().clear().destroy();
			    	onGetTableData();
					$('#deleteModal').modal('hide');
		    	}
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>