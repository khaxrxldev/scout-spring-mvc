<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<h4 class="card-header">Students</h4>
				<ul class="list-group list-group-flush">
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
						.append($('<td>').append('<button onclick="window.location.href=\'/core/student/assessments/update/' + data.studentId + '\'" class="btn btn-sm btn-primary m-1">Evaluate</button>'))
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
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>