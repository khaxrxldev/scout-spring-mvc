<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Memberships</h4>
				<ul class="list-group list-group-flush">
					<li id="addMembershipButton" class="d-none list-group-item p-1">
						<button type="button" class="btn btn-sm btn-primary" onclick="onOpenModal('')">Add Membership</button>
					</li>
					<li class="list-group-item p-1 overflow-auto">
						<table id="membershipTable" class="table table-striped compact" style="width:100%">
							<thead>
								<tr>
									<th>Type</th>
									<th>Unit</th>
									<th>Rank</th>
									<th>Membership Number</th>
									<th>Joined Year</th>
									<th>Form</th>
									<th>Status</th>
									<th>Date</th>
									<th>Receipt</th>
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
    							<input class="d-none" type="text" id="membershipId" name="membershipId" />
    							<input class="d-none" type="text" id="studentId" name="studentId" />
    							<label for="membershipType" class="form-label">Type</label>
    							<select class="form-select form-select-sm" id="membershipType" name="membershipType" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="AHLI BARU">AHLI BARU</option>
    								<option value="AHLI LAMA">AHLI LAMA</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide type.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="membershipUnit" class="form-label">Unit</label>
    							<select class="form-select form-select-sm" id="membershipUnit" name="membershipUnit" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="PENGAKAP MUDA">PENGAKAP MUDA</option>
    								<option value="PENGAKAP REMAJA">PENGAKAP REMAJA</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide unit.
						     	</div>
							</div>
						</div><div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="membershipRank" class="form-label">Rank</label>
    							<select class="form-select form-select-sm" id="membershipRank" name="membershipRank" required>
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="KETUA KUMPULAN">KETUA KUMPULAN</option>
    								<option value="KETUA PATROL">KETUA PATROL</option>
    								<option value="P.K. PATROL">P.K. PATROL</option>
    								<option value="KETUA PEK">KETUA PEK</option>
    								<option value="KETUA SEKAWAN">KETUA SEKAWAN</option>
    								<option value="P.K. SEKAWAN">P.K. SEKAWAN</option>
    								<option value="AHLI BIASA">AHLI BIASA</option>
    							</select>
    							<div class="d-none invalid-feedback">
						        	Please provide rank.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="membershipNo" class="form-label">Membership Number</label>
    							<input onkeyup="setInputUppercase(event)" type="text" class="form-control form-control-sm" id="membershipNo" name="membershipNo" required />
    							<div class="d-none invalid-feedback">
						        	Please provide membership number.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="membershipJoinedYear" class="form-label">Joined Year</label>
    							<input type="number" min="1900" max="2099" step="1" onkeyup="setInputUppercase(event)" class="form-control form-control-sm" id="membershipJoinedYear" name="membershipJoinedYear" required />
    							<div class="d-none invalid-feedback">
						        	Please provide joined year.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="membershipStudentForm" class="form-label">Standard / Form</label>
    							<input onkeyup="setInputUppercase(event)" type="number" class="form-control form-control-sm" id="membershipStudentForm" name="membershipStudentForm" required />
    							<div class="d-none invalid-feedback">
						        	Please provide standard / form.
						     	</div>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-12">
								<label for="membershipPayReceipt" class="form-label">Receipt</label>
  								<input class="form-control form-control-sm" id="membershipPayReceipt" name="membershipPayReceipt" type="file" accept="application/pdf" required>
    							<div class="d-none invalid-feedback">
						        	Please provide receipt.
						     	</div>
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
			if (sessionStorage.getItem('login_type') === 'STUDENT') {
				$('#addMembershipButton').removeClass('d-none');
	    	}
		}
		
		function onGetTableData() {
			$.ajax({
				type: "GET",
		        url: "/common/memberships"
		    }).then(function(dataList) {
		    	let filterDataList = [];
		    	if (sessionStorage.getItem('login_type') === 'STUDENT') {
		    		filterDataList = dataList.filter(function (data) {
						return data.studentId === sessionStorage.getItem('login_id');
					});
		    	} else {
		    		filterDataList = dataList;
		    	}
		    	
		    	for(let data of filterDataList) {
		    		let formattedDate = '';
		    		if (data.membershipPayDate) {
		    			let date = new Date(data.membershipPayDate);
		    			formattedDate = date.toLocaleDateString("en-GB");
		    		}
		    		
		    		if (sessionStorage.getItem('login_type') === 'STUDENT') {
		    			$('#membershipTable > tbody:last').append($('<tr>')
							.append($('<td>').append(data.membershipType))
							.append($('<td>').append(data.membershipUnit))
							.append($('<td>').append(data.membershipRank))
							.append($('<td>').append(data.membershipNo))
							.append($('<td>').append(data.membershipJoinedYear))
							.append($('<td>').append(data.membershipStudentForm))
							.append($('<td>').append(data.membershipPayStatus))
							.append($('<td>').append(formattedDate))
							.append($('<td>').append(data.membershipPayReceipt ? '<a href="javascript:void(0);" onclick="onGetReceipt(\'' + data.membershipId + '\')">' + data.membershipPayReceipt + '</a>' : ''))
							.append($('<td>').append('<button class="btn btn-sm btn-warning m-1" onclick="onOpenModal(\''+data.membershipId+'\')">Edit</button>' + '<button class="btn btn-sm btn-danger m-1" onclick="onPromptDelete(\''+data.membershipId+'\')">Delete</button>'))
						);
			    	} else {
			    		if (data.membershipPayStatus !== 'PENDING APPROVAL') {
			    			$('#membershipTable > tbody:last').append($('<tr>')
								.append($('<td>').append(data.membershipType))
								.append($('<td>').append(data.membershipUnit))
								.append($('<td>').append(data.membershipRank))
								.append($('<td>').append(data.membershipNo))
								.append($('<td>').append(data.membershipJoinedYear))
								.append($('<td>').append(data.membershipStudentForm))
								.append($('<td>').append(data.membershipPayStatus))
								.append($('<td>').append(formattedDate))
								.append($('<td>').append(data.membershipPayReceipt ? '<a href="javascript:void(0);" onclick="onGetReceipt(\'' + data.membershipId + '\')">' + data.membershipPayReceipt + '</a>' : ''))
								.append($('<td>').append(''))
							);
			    		} else {
			    			$('#membershipTable > tbody:last').append($('<tr>')
								.append($('<td>').append(data.membershipType))
								.append($('<td>').append(data.membershipUnit))
								.append($('<td>').append(data.membershipRank))
								.append($('<td>').append(data.membershipNo))
								.append($('<td>').append(data.membershipJoinedYear))
								.append($('<td>').append(data.membershipStudentForm))
								.append($('<td>').append(data.membershipPayStatus))
								.append($('<td>').append(formattedDate))
								.append($('<td>').append(data.membershipPayReceipt ? '<a href="javascript:void(0);" onclick="onGetReceipt(\'' + data.membershipId + '\')">' + data.membershipPayReceipt + '</a>' : ''))
								.append($('<td>').append('<button class="btn btn-sm btn-primary m-1" onclick="onApprove(\''+data.membershipId+'\', \'APPROVE\')">Approve</button>' + '<button class="btn btn-sm btn-danger m-1" onclick="onApprove(\''+data.membershipId+'\', \'REJECT\')">Reject</button>'))
							);
			    		}
			    	}
				}
		    	
				$('#membershipTable').dataTable({
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
				formData.append("membershipRequest", new Blob([insertJSON], { type : "application/json" }));
				formData.append("receipt", $("#membershipPayReceipt")[0].files[0]);
				
				$.ajax({
					type: ($('#membershipId').val()) ? 'PUT' : 'POST',
			        url: "/common/membership",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	$('#insertModal').modal('hide');
			    	$('#membershipTable').DataTable().clear().destroy();
			    	resetForm("insertForm");
			    	onGetTableData();
			    });
			}
		});
		
		function onOpenModal(updateId) {
			if (updateId) {
				// update - get form value
				$.ajax({
					type: "GET",
			        url: "/common/membership/" + updateId
			    }).then(function(data) {
				    $('#insertModal').modal('show');
				    setForm('insertForm', data);
			    });
			} else {
				$('#studentId').val(sessionStorage.getItem('login_id'));
				$('#membershipJoinedYear').val(new Date().getFullYear());
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
		        url: "/common/membership/" + $('#deleteId').val()
		    }).then(function(data) {
		    	if (data) {
					$('#membershipTable').DataTable().clear().destroy();
			    	onGetTableData();
					$('#deleteModal').modal('hide');
		    	}
		    });
		}
		
		function onApprove(membershipId, membershipPayStatus) {
			let membershipRequest = {
				"membershipId": membershipId,
				"membershipPayStatus": membershipPayStatus
			}
			let insertJSON = JSON.stringify(membershipRequest);
				
			let formData = new FormData();
			formData.append("membershipRequest", new Blob([insertJSON], { type : "application/json" }));
			
			$.ajax({
				type: 'PUT',
		        url: "/common/membership/approval",
		        processData: false,
		        contentType: false,
		        cache: false,
		        data: formData
		    }).then(function(data) {
		    	$('#insertModal').modal('hide');
		    	$('#membershipTable').DataTable().clear().destroy();
		    	resetForm("insertForm");
		    	onGetTableData();
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>