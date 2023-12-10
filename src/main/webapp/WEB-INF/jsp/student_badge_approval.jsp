<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Badges</h4>
				<ul class="list-group list-group-flush">
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
    							<select class="form-select form-select-sm" id="badgeId" name="badgeId" disabled></select>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="studentBadgeSerialNum" class="form-label">Serial Number</label>
    							<input type="text" class="form-control form-control-sm" id="studentBadgeSerialNum" name="studentBadgeSerialNum" disabled />
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="studentBadgeStatus" class="form-label">Status</label>
    							<select class="form-select form-select-sm" id="studentBadgeStatus" name="studentBadgeStatus" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="APPROVE">APPROVE</option>
    								<option value="NOT APPROVE">NOT APPROVE</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide status.
						     	</div>
							</div>
							<div class="col-lg-6 col-12"></div>
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
	
	<script type="text/javascript">
		function onWindowLoad() {
			onGetTableData();
			onGetBadges();
		}
		
		function onGetBadges() {
			$.ajax({
				type: "GET",
		        url: "/core/badges"
		    }).then(function(dataList) {
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
		        url: "/core/studentBadges/student/" + window.location.pathname.split("/").pop()
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
						.append($('<td>').append('<button class="btn btn-sm btn-warning m-1" onclick="onOpenModal(\'' + data.studentBadgeId + '\')">Edit</button>'))
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
				
				$.ajax({
					type: 'PUT',
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
			$.ajax({
				type: "GET",
		        url: "/core/studentBadge/" + updateId
		    }).then(function(data) {
			    $('#insertModal').modal('show');
			    data.teacherId = sessionStorage.getItem('login_id');
			    setForm('insertForm', data);
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>