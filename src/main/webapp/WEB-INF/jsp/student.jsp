<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="row g-1">
				<div class="col-lg-6 col-12">
					<div class="card w-100">
						<h4 class="card-header">Student</h4>
						<form id="studentForm">
							<ul class="list-group list-group-flush">
								<li class="list-group-item p-1">
									<div class="modal-body">
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="studentEmail" class="form-label">Email</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="studentEmail" id="studentEmail" />
											</div>
											<div class="col-lg-6 col-12">
				    							<label for="studentPhoneNo" class="form-label">Phone number</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="studentPhoneNo" id="studentPhoneNo" />
											</div>
										</div>
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="studentName" class="form-label">Full name</label>
				    							<input onkeyup="setInputUppercase(event)" type="text" class="form-control form-control-sm" disabled name="studentName" id="studentName" />
											</div>
											<div class="col-lg-6 col-12">
				    							<label for="studentPassword" class="form-label">Password</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="studentPassword" id="studentPassword" />
											</div>
										</div>
										<div class="row g-1 pb-2">
											<div class="col-12">
												<label for="studentAddress" class="form-label">Address</label>
												<textarea onkeyup="setInputUppercase(event)" class="form-control" rows="3" disabled name="studentAddress" id="studentAddress"></textarea>
											</div>
										</div>
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="studentRace" class="form-label">Race</label>
				    							<select class="form-select form-select-sm" disabled name="studentRace" id="studentRace">
				    								<option value="MALAY">MALAY</option>
				    								<option value="CHINESE">CHINESE</option>
				    								<option value="INDIAN">INDIAN</option>
				    							</select>
				    							<div class="d-none invalid-feedback">
										        	Please provide race.
										     	</div>
											</div>
											<div class="col-lg-6 col-12">
				    							<label for="studentReligion" class="form-label">Religion</label>
				    							<select class="form-select form-select-sm" disabled name="studentReligion" id="studentReligion">
				    								<option value="ISLAM">ISLAM</option>
				    								<option value="BUDDHIST">BUDDHIST</option>
				    								<option value="HINDU">HINDU</option>
				    								<option value="CHRISTIAN">CHRISTIAN</option>
				    							</select>
											</div>
										</div>
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="studentIdentificationNo" class="form-label">IC Number</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="studentIdentificationNo" id="studentIdentificationNo" />
											</div>
											<div class="col-lg-6 col-12">
				    							<label for="studentGender" class="form-label">Gender</label>
				    							<select class="form-select form-select-sm" disabled name="studentGender" id="studentGender">
				    								<option value="MALE">MALE</option>
				    								<option value="FEMALE">FEMALE</option>
				    							</select>
											</div>
										</div>
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="studentBirthDate" class="form-label">Birth Date</label>
				    							<input type="date" class="form-control form-control-sm" disabled name="studentBirthDate" id="studentBirthDate" />
											</div>
											<div class="col-lg-6 col-12">
				    							<label for="studentAge" class="form-label">Age</label>
				    							<input type="number" class="form-control form-control-sm" disabled name="studentAge" id="studentAge" />
											</div>
										</div>
									</div>
								</li>
							</ul>
						</form>
					</div>
				</div>
				<div class="col-lg-6 col-12">
					<div class="card w-100">
						<h4 class="card-header">Guardian</h4>
						<form id="guardianForm">
							<ul class="list-group list-group-flush">
								<li class="list-group-item p-1">
									<div class="modal-body">
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="guardianName" class="form-label">Full name</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="guardianName" id="guardianName" />
											</div>
											<div class="col-lg-6 col-12">
				    							<label for="guardianPhoneNo" class="form-label">Phone number</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="guardianPhoneNo" id="guardianPhoneNo" />
											</div>
										</div>
										<div class="row g-1 pb-2">
											<div class="col-lg-6 col-12">
				    							<label for="guardianConnection" class="form-label">Connection</label>
				    							<input type="text" class="form-control form-control-sm" disabled name="guardianConnection" id="guardianConnection" />
											</div>
											<div class="col-lg-6 col-12"></div>
										</div>
									</div>
								</li>
							</ul>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
		function onWindowLoad() {
			onGetStudent();
			onGetGuardian();
		}
		
		function onGetStudent() {
			$.ajax({
				type: "GET",
		        url: "/user/student/" + window.location.pathname.split("/").pop()
		    }).then(function(data) {
			    setForm('studentForm', data);
		    });
		}
		
		function onGetGuardian() {			
			$.ajax({
				type: "GET",
		        url: "/common/guardian/student/" + window.location.pathname.split("/").pop()
		    }).then(function(data) {
			    setForm('guardianForm', data);
		    });
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>