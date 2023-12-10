<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Badges</h4>
				<ul class="list-group list-group-flush">
					<li class="list-group-item p-1">
						<button type="button" class="btn btn-sm btn-primary" onclick="onOpenModal('')">Add Badge</button>
					</li>
					<li class="list-group-item p-1 overflow-auto">
						<table id="badgeTable" class="table table-striped compact" style="width:100%">
							<thead>
								<tr>
									<th>Name</th>
									<th>Category</th>
									<th>Form</th>
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
    							<input class="d-none" type="text" id="badgeId" name="badgeId" />
    							<label for="badgeName" class="form-label">Name</label>
    							<input onkeyup="setInputUppercase(event)" type="text" class="form-control form-control-sm" id="badgeName" name="badgeName" required />
    							<div class="d-none invalid-feedback">
						        	Please provide name.
						     	</div>
							</div>
							<div class="col-lg-6 col-12">
    							<label for="badgeCategory" class="form-label">Category</label>
    							<select class="form-select form-select-sm" id="badgeCategory" name="badgeCategory">
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="LENCANA TERTINGGI">LENCANA TERTINGGI</option>
    								<option value="LENCANA PENGAKAP MUDA">LENCANA PENGAKAP MUDA</option>
    							</select>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-lg-6 col-12">
    							<label for="badgeOrder" class="form-label">Order</label>
    							<input type="number" class="form-control form-control-sm" id="badgeOrder" name="badgeOrder" />
							</div>
							<div class="col-lg-6 col-12">
    							<label for="badgeForm" class="form-label">Form</label>
    							<select class="form-select form-select-sm" id="badgeForm" name="badgeForm">
    								<option selected disabled hidden value="">PLEASE CHOOSE</option>
    								<option value="1">1</option>
    								<option value="2">2</option>
    								<option value="3">3</option>
    								<option value="4">4</option>
    								<option value="5">5</option>
    							</select>
							</div>
						</div>
						<div class="row pb-2">
							<div class="col-12">
    							<label for="badgeIdFk" class="form-label">Main Badge</label>
    							<select class="form-select form-select-sm" id="badgeIdFk" name="badgeIdFk"></select>
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
		}
		
		function onGetBadges() {
			$.ajax({
				type: "GET",
		        url: "/core/badges"
		    }).then(function(dataList) {
		    	$('#badgeIdFk').empty().append($('<option>').val('').html('PLEASE CHOOSE').prop('selected', true).prop('disabled', true).prop('hidden', true));
		    	for(let data of dataList) {
		    		if (!data.badgeIdFk) {
		    			$('#badgeIdFk').append($('<option>').val(data.badgeId).html(data.badgeName));
		    		}
		    	}
		    });
		}
		
		function onGetTableData() {
			$.ajax({
				type: "GET",
		        url: "/core/badges"
		    }).then(function(dataList) {
		    	for(let data of dataList) {
		    		$('#badgeTable > tbody:last').append($('<tr>')
						.append($('<td>').append(data.badgeName))
						.append($('<td>').append(data.badgeCategory))
						.append($('<td>').append(data.badgeForm))
						.append($('<td>').append('<button class="btn btn-sm btn-warning m-1" onclick="onOpenModal(\''+data.badgeId+'\')">Edit</button>' + '<button class="btn btn-sm btn-danger m-1" onclick="onPromptDelete(\''+data.badgeId+'\')">Delete</button>'))
					);
				}
		    	
				$('#badgeTable').dataTable({
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
				formData.append("badgeRequest", new Blob([insertJSON], { type : "application/json" }));
				
				$.ajax({
					type: ($('#badgeId').val()) ? 'PUT' : 'POST',
			        url: "/core/badge",
			        processData: false,
			        contentType: false,
			        cache: false,
			        data: formData
			    }).then(function(data) {
			    	$('#insertModal').modal('hide');
			    	$('#badgeTable').DataTable().clear().destroy();
			    	resetForm("insertForm");
			    	onGetTableData();
					onGetBadges();
			    });
			}
		});
		
		function onOpenModal(updateId) {
			if (updateId) {
				// update - get form value
				$.ajax({
					type: "GET",
			        url: "/core/badge/" + updateId
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
		        url: "/core/badge/" + $('#deleteId').val()
		    }).then(function(data) {
		    	if (data) {
					$('#badgeTable').DataTable().clear().destroy();
			    	onGetTableData();
					$('#deleteModal').modal('hide');
		    	}
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>