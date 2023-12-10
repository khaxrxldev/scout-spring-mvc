<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Badges Submission</h4>
				<ul class="list-group list-group-flush">
					<li class="list-group-item p-1">
						<button type="button" class="btn btn-sm btn-primary" onclick="onOpenModal('')">Badge Submission</button>
					</li>
					<li class="list-group-item p-1 overflow-auto">
						<table id="studentBadgeTable" class="table table-striped compact" style="width:100%">
							<thead>
								<tr>
									<th>Badge Name</th>
									<th>Serial Number</th>
									<th>Status</th>
									<th>Approval Date</th>
									<th>Logbook</th>
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
    							<input class="d-none" type="text" id="studentBadgeId" name="studentBadgeId" />
    							<input class="d-none" type="text" id="studentId" name="studentId" />
    							<input class="d-none" type="text" id="teacherId" name="teacherId" />
    							<label for="badgeId" class="form-label">Badge</label>
    							<select class="form-select form-select-sm" id="badgeId" name="badgeId" required></select>
								<div class="d-none invalid-feedback">
						        	Please select badge.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentBadgeSerialNum" class="form-label">Serial Number</label>
    							<input onkeyup="setInputUppercase(event)" type="text" class="form-control form-control-sm" id="studentBadgeSerialNum" name="studentBadgeSerialNum" required />
    							<div class="d-none invalid-feedback">
						        	Please provide serial number.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-12">
								<label for="studentBadgeLogbook" class="form-label">Logbook</label>
  								<input class="form-control form-control-sm" id="studentBadgeLogbook" name="studentBadgeLogbook" type="file" accept="application/pdf">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" onclick="resetForm('insertForm')" class="btn btn-sm btn-secondary" data-bs-dismiss="modal">Cancel</button>
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
			onGetBadges();
			$('#studentId').val(sessionStorage.getItem('login_id'));
		}
		
		function onGetBadges() {
			$.ajax({
				type: "GET",
		        url: "/core/badges"
		    }).then(function(dataList) {
		    	$('#badgeId').empty().append($('<option>').val('').html('PLEASE CHOOSE').prop('selected', true).prop('disabled', true).prop('hidden', true));
		    	for(let data of dataList) {
		    		if (!data.badgeIdFk) {
		    			$('#badgeId').append($('<option>').val(data.badgeId).html(data.badgeName));
		    		}
		    	}
		    });
		}
		
		function onGetTableData() {
			$.ajax({
				type: "GET",
		        url: "/core/studentBadges/student/" + sessionStorage.getItem('login_id')
		    }).then(function(dataList) {
		    	for(let data of dataList) {
		    		let formattedDate = '';
		    		if (data.studentApprovalDate) {
		    			let date = new Date(data.studentApprovalDate);
		    			formattedDate = date.toLocaleDateString("en-GB");
		    		}
		    		
		    		$('#studentBadgeTable > tbody:last').append($('<tr>')
						.append($('<td>').append(data.badge.badgeName))
						.append($('<td>').append(data.studentBadgeSerialNum))
						.append($('<td>').append(data.studentBadgeStatus))
						.append($('<td>').append(formattedDate))
						.append($('<td>').append(data.studentBadgeLogbook ? '<a href="javascript:void(0);" onclick="onGetLogbook(\'' + data.studentBadgeId + '\')">' + data.studentBadgeLogbook + '</a>' : ''))
						.append($('<td>').append('<button class="btn btn-sm btn-warning m-1" onclick="onOpenModal(\''+data.studentBadgeId+'\')">Edit</button>' + '<button class="btn btn-sm btn-danger m-1" onclick="onPromptDelete(\''+data.studentBadgeId+'\')">Delete</button>'))
					);
				}
		    	
				$('#studentBadgeTable').dataTable({
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
				formData.append("studentBadgeRequest", new Blob([insertJSON], { type : "application/json" }));
				formData.append("logbook", $("#studentBadgeLogbook")[0].files[0]);
				
				$.ajax({
					type: ($('#studentBadgeId').val()) ? 'PUT' : 'POST',
			        url: "/core/studentBadge",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	$('#insertModal').modal('hide');
			    	$('#studentBadgeTable').DataTable().clear().destroy();
			    	resetForm("insertForm");
			    	onGetTableData();
					onGetBadges();
			    });
			}
		});
		
		function onOpenModal(updateId) {
			if (updateId) {
				$.ajax({
					type: "GET",
			        url: "/core/studentBadge/" + updateId
			    }).then(function(data) {
				    $('#insertModal').modal('show');
				    setForm('insertForm', data);
			    });
			} else {
			    $('#insertModal').modal('show');
			}
		}
		
		function onPromptDelete(deleteId) {
			$('#deleteModal').modal('show');
			$('#deleteId').val(deleteId);
		}
		
		function onDelete() {
			$.ajax({
				type: "DELETE",
		        url: "/core/studentBadge/" + $('#deleteId').val()
		    }).then(function(data) {
		    	if (data) {
					$('#studentBadgeTable').DataTable().clear().destroy();
			    	onGetTableData();
					$('#deleteModal').modal('hide');
		    	}
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>