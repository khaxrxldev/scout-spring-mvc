<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2">
			<div class="card w-100">
				<ul class="list-group list-group-flush">
					<li class="list-group-item p-1">
						<button type="button" class="btn btn-sm btn-primary" onclick="onGetTableData('REPORT')" id="printBtnId" disabled>Print</button>
					</li>
					<li class="list-group-item p-2 overflow-auto" id="printSection">
						<div class="w-100 text-center">
							<img class="mt-4" style="width: 10%;" alt="School Logo" id="logoId" src="/image/LOGO.png">
							<canvas width="100" height="100" class="d-none" id="canvasId"></canvas>
						</div>
						<div class="w-100 text-center my-4 fw-bold">YEARLY REPORT</div>
						<div class="row g-0 mb-4">
							<div class="col-lg-1 col-12"></div>
							<div class="col-lg-10 col-12">
								<div class="row pb-2">
									<div class="col-lg-3 col-12">
		    							<label class="form-label">Year</label>
										<div class="input-group input-group-sm">
											<input id="reportYearId" type="number" class="form-control" />
											<button onclick="onGetTableData('DISPLAY')" class="input-group-text">Generate</button>
										</div>
									</div>
									<div class="col-lg-3 col-12">
		    							<label class="form-label">Group</label>
		    							<input type="text" class="form-control form-control-sm" value="71" disabled />
									</div>
									<div class="col-lg-3 col-12">
		    							<label class="form-label">Registration Num.</label>
		    							<input type="text" class="form-control form-control-sm" value="KEA 5025" disabled />
									</div>
									<div class="col-lg-3 col-12">
		    							<label class="form-label">PPM District</label>
		    							<input type="text" class="form-control form-control-sm" value="KULIM" disabled />
									</div>
								</div>
								<div class="row pb-2">
									<div class="col-lg-8 col-12">
		    							<label class="form-label">School Name</label>
		    							<input type="text" class="form-control form-control-sm" value="SMK DATO LELA PAHLAWAN" disabled />
									</div>
									<div class="col-lg-4 col-12">
		    							<label class="form-label">State</label>
		    							<input type="text" class="form-control form-control-sm" value="KEDAH" disabled />
									</div>
								</div>
								<div class="row pb-2">
									<div class="col-lg-4 col-12">
		    							<label class="form-label">Phone Num.</label>
		    							<input type="text" class="form-control form-control-sm" value="+604-485 7140" disabled />
									</div>
									<div class="col-lg-4 col-12">
		    							<label class="form-label">Postcode</label>
		    							<input type="text" class="form-control form-control-sm" id="studentPhoneNo" value="09400" disabled />
									</div>
									<div class="col-lg-4 col-12"></div>
								</div>
							</div>
							<div class="col-lg-1 col-12"></div>
						</div>
						<div class="w-100 border border-black mb-4" style="padding: 2px;">
							<table class="normalTable" id="totalTable">
								<thead>
									<tr class="bg-secondary-subtle">
										<th colspan="2"></th>
										<th colspan="17">MEMBERSHIP ACCORDING TO RACE</th>
									</tr>
									<tr class="bg-secondary-subtle">
										<th rowspan="2">NUM</th>
										<th rowspan="2">MEMBERSHIP UNIT</th>
										<th colspan="2">MALAY</th>
										<th colspan="2">CHINESE</th>
										<th colspan="2">INDIAN</th>
										<th colspan="2">KADAZAN</th>
										<th colspan="2">DAYAK</th>
										<th colspan="2">OTHER</th>
										<th colspan="2">TOTAL MEMBER</th>
										<th rowspan="2">OVERALL TOTAL</th>
										<th rowspan="2">UNIT FEE</th>
										<th rowspan="2">TOTAL FEE COLLECTED</th>
									</tr>
									<tr class="bg-secondary-subtle">
										<th>L</th>
										<th>P</th>
										<th>L</th>
										<th>P</th>
										<th>L</th>
										<th>P</th>
										<th>L</th>
										<th>P</th>
										<th>L</th>
										<th>P</th>
										<th>L</th>
										<th>P</th>
										<th>L</th>
										<th>P</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
						<div class="w-100 border border-black" style="padding: 2px;">
							<table class="normalTable" id="studentTable">
								<thead>
									<tr class="bg-secondary-subtle">
										<th>NUM</th>
										<th>FULL NAME</th>
										<th>BIRTH DATE</th>
										<th>IC NUM</th>
										<th>RACE</th>
										<th>GENDER</th>
										<th>RELIGION</th>
										<th>ADDRESS</th>
										<th>PAYMENT</th>
										<th>GUARDIAN NAME</th>
										<th>RANK</th>
									</tr>
								</thead>
								<tbody></tbody>
							</table>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</div>
	
	<script type="text/javascript">
		function onWindowLoad() {
			const currentDate = new Date;
			$('#reportYearId').val(currentDate.getFullYear());
			onGetTableData('DISPLAY');
		}
		
		function onGetTableData(action) {
			$('#totalTable > tbody').empty();
			$('#studentTable > tbody').empty();
			
			$.ajax({
				type: "GET",
		        url: "/core/reports"
		    }).then(function(dataList) {
		    	$('#printBtnId').prop("disabled", false);
	    		let index = 1;
		    	for (let data of dataList) {
		    		let formattedDate = '';
		    		if (data.studentResponse.studentName) {
		    			let date = new Date(data.studentResponse.studentBirthDate);
		    			formattedDate = date.toLocaleDateString("en-GB");
		    		}
		    		
		    		if (data.membershipResponse.membershipJoinedYear === $('#reportYearId').val()) {
			    		if (data.membershipResponse.membershipPayStatus === 'APPROVE') {
			    			
			    			$('#studentTable > tbody:last').append($('<tr>')
								.append($('<td>').append(index))
								.append($('<td>').append(data.studentResponse.studentName))
								.append($('<td>').append(formattedDate))
								.append($('<td>').append(data.studentResponse.studentIdentificationNo))
								.append($('<td>').append(data.studentResponse.studentRace))
								.append($('<td>').append(data.studentResponse.studentGender))
								.append($('<td>').append(data.studentResponse.studentReligion))
								.append($('<td>').append(data.studentResponse.studentAddress))
								.append($('<td>').append('7.00'))
								.append($('<td>').append( (data.guardianResponse) ? data.guardianResponse.guardianName : '' ))
								.append($('<td>').append(data.membershipResponse.membershipRank))
							);
			    			index++;
			    		}
		    		}
		    	}
		    	
		    	let totalUnits = []
		    	let units = [{ 'unit_name': 'PENGAKAP MUDA', 'unit_price': 7 }, { 'unit_name': 'PENGAKAP REMAJA', 'unit_price': 7 }];

	    		let overall_total_M_M = 0;
	    		let overall_total_M_F = 0;
	    		let overall_total_C_M = 0;
	    		let overall_total_C_F = 0;
	    		let overall_total_I_M = 0;
	    		let overall_total_I_F = 0;
	    		let overall_total_K_M = 0;
	    		let overall_total_K_F = 0;
	    		let overall_total_D_M = 0;
	    		let overall_total_D_F = 0;
	    		let overall_total_O_M = 0;
	    		let overall_total_O_F = 0;
	    		let overall_total_M = 0;
	    		let overall_total_F = 0;
	    		let overall_total = 0;
	    		let overall_total_fee = 0;
	    		
		    	for(let unit of units) {
		    		let total_M_M = 0;
		    		let total_M_F = 0;
		    		let total_C_M = 0;
		    		let total_C_F = 0;
		    		let total_I_M = 0;
		    		let total_I_F = 0;
		    		let total_K_M = 0;
		    		let total_K_F = 0;
		    		let total_D_M = 0;
		    		let total_D_F = 0;
		    		let total_O_M = 0;
		    		let total_O_F = 0;
		    		let total_M = 0;
		    		let total_F = 0;
		    		let total = 0;
		    		
		    		for(let data of dataList) {			    		
				    	if (data.membershipResponse.membershipJoinedYear === $('#reportYearId').val()) {
				    		if (data.membershipResponse.membershipPayStatus === 'APPROVE') {				    			
				    			if (data.membershipResponse.membershipUnit === unit.unit_name) {
				    				switch(data.studentResponse.studentRace) {
					    				case 'MALAY':
					    					if (data.studentResponse.studentGender === 'MALE') {
					    						total_M_M++;
					    					} else if (data.studentResponse.studentGender === 'FEMALE') {
					    						total_M_F++;
					    					}
					    				break;
					    				case 'CHINESE':
					    					if (data.studentResponse.studentGender === 'MALE') {
					    						total_C_M++;
					    					} else if (data.studentResponse.studentGender === 'FEMALE') {
					    						total_C_F++;
					    					}
					    				break;
					    				case 'INDIAN':
					    					if (data.studentResponse.studentGender === 'MALE') {
					    						total_I_M++;
					    					} else if (data.studentResponse.studentGender === 'FEMALE') {
					    						total_I_F++;
					    					}
					    				break;
					    				case 'KADAZAN':
					    					if (data.studentResponse.studentGender === 'MALE') {
					    						total_K_M++;
					    					} else if (data.studentResponse.studentGender === 'FEMALE') {
					    						total_K_F++;
					    					}
					    				break;
					    				case 'DAYAK':
					    					if (data.studentResponse.studentGender === 'MALE') {
					    						total_D_M++;
					    					} else if (data.studentResponse.studentGender === 'FEMALE') {
					    						total_D_F++;
					    					}
					    				break;
					    				break;
					    				default:
					    					if (data.studentResponse.studentGender === 'MALE') {
					    						total_O_M++;
					    					} else if (data.studentResponse.studentGender === 'FEMALE') {
					    						total_O_F++;
					    					}
				    				}
			    					if (data.studentResponse.studentGender === 'MALE') {
			    						total_M++;
			    					} else if (data.studentResponse.studentGender === 'FEMALE') {
			    						total_F++;
			    					}
					    			total++;
				    			}
				    		}
				    	}
					}
		    		totalUnits.push({
		    			'unit_name': unit.unit_name,
		    			'unit_price': unit.unit_price,
		    			'unit_total_M_M': total_M_M,
		    			'unit_total_M_F': total_M_F,
		    			'unit_total_C_M': total_C_M,
		    			'unit_total_C_F': total_C_F,
		    			'unit_total_I_M': total_I_M,
		    			'unit_total_I_F': total_I_F,
		    			'unit_total_K_M': total_K_M,
		    			'unit_total_K_F': total_K_F,
		    			'unit_total_D_M': total_D_M,
		    			'unit_total_D_F': total_D_F,
		    			'unit_total_O_M': total_O_M,
		    			'unit_total_O_F': total_O_F,
		    			'unit_total_M': total_M,
		    			'unit_total_F': total_F,
		    			'unit_total': total,
		    			'unit_total_fee': total * unit.unit_price
		    		});
		    		
		    		overall_total_M_M = overall_total_M_M + total_M_M;
		    		overall_total_M_F = overall_total_M_F + total_M_F;
		    		overall_total_C_M = overall_total_C_M + total_C_M;
		    		overall_total_C_F = overall_total_C_F + total_C_F;
		    		overall_total_I_M = overall_total_I_M + total_I_M;
		    		overall_total_I_F = overall_total_I_F + total_I_F;
		    		overall_total_K_M = overall_total_K_M + total_K_M;
		    		overall_total_K_F = overall_total_K_F + total_K_F;
		    		overall_total_D_M = overall_total_D_M + total_D_M;
		    		overall_total_D_F = overall_total_D_F + total_D_F;
		    		overall_total_O_M = overall_total_O_M + total_O_M;
		    		overall_total_O_F = overall_total_O_F + total_O_F;
		    		overall_total_M = overall_total_M + total_M;
		    		overall_total_F = overall_total_F + total_F;
		    		overall_total = overall_total + total;
		    		overall_total_fee = overall_total_fee + (total * unit.unit_price);
		    	}

		    	$('#totalTable > tbody:last').append($('<tr>')
					.append($('<td>').attr('colspan', 18).append('KUMPULAN'))
					.append($('<td>').addClass('text-end').append('10.00'))
				);
		    	let num = 1;
		    	for(let totalUnit of totalUnits) {
		    		$('#totalTable > tbody:last').append($('<tr>')
						.append($('<td>').addClass('text-center').append(num))
						.append($('<td>').append(totalUnit.unit_name))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_M_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_M_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_C_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_C_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_I_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_I_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_K_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_K_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_D_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_D_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_O_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_O_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_M))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total_F))
						.append($('<td>').addClass('text-center').append(totalUnit.unit_total))
						.append($('<td>').addClass('text-end').append(totalUnit.unit_price.toFixed(2)))
						.append($('<td>').addClass('text-end').append(totalUnit.unit_total_fee.toFixed(2)))
					);
		    		num++;
		    	}
		    	
		    	$('#totalTable > tbody:last').append($('<tr>').css('border', '2px solid black')
					.append($('<td>').attr('colspan', 2).addClass('text-center').append('JUMLAH'))
					.append($('<td>').addClass('text-center').append(overall_total_M_M))
					.append($('<td>').addClass('text-center').append(overall_total_M_F))
					.append($('<td>').addClass('text-center').append(overall_total_C_M))
					.append($('<td>').addClass('text-center').append(overall_total_C_F))
					.append($('<td>').addClass('text-center').append(overall_total_I_M))
					.append($('<td>').addClass('text-center').append(overall_total_I_F))
					.append($('<td>').addClass('text-center').append(overall_total_K_M))
					.append($('<td>').addClass('text-center').append(overall_total_K_F))
					.append($('<td>').addClass('text-center').append(overall_total_D_M))
					.append($('<td>').addClass('text-center').append(overall_total_D_F))
					.append($('<td>').addClass('text-center').append(overall_total_O_M))
					.append($('<td>').addClass('text-center').append(overall_total_O_F))
					.append($('<td>').addClass('text-center').append(overall_total_M))
					.append($('<td>').addClass('text-center').append(overall_total_F))
					.append($('<td>').addClass('text-center').append(overall_total))
					.append($('<td>').addClass('text-center').append(''))
					.append($('<td>').addClass('text-end').append((overall_total_fee + 10).toFixed(2)))
				);
		    	
		    	totalUnits.push({
	    			'unit_name': '',
	    			'unit_price': 0,
	    			'unit_total_M_M': overall_total_M_M,
	    			'unit_total_M_F': overall_total_M_F,
	    			'unit_total_C_M': overall_total_C_M,
	    			'unit_total_C_F': overall_total_C_F,
	    			'unit_total_I_M': overall_total_I_M,
	    			'unit_total_I_F': overall_total_I_F,
	    			'unit_total_K_M': overall_total_K_M,
	    			'unit_total_K_F': overall_total_K_F,
	    			'unit_total_D_M': overall_total_D_M,
	    			'unit_total_D_F': overall_total_D_F,
	    			'unit_total_O_M': overall_total_O_M,
	    			'unit_total_O_F': overall_total_O_F,
	    			'unit_total_M': overall_total_M,
	    			'unit_total_F': overall_total_F,
	    			'unit_total': overall_total,
	    			'unit_total_fee': overall_total_fee + 10
	    		});
		    	
		    	if (action === 'REPORT') {
			    	onPrintTable(totalUnits, dataList);
		    	}
		    });
		}
		
		function onPrintTable(totalUnits, dataList) {
            let docDefinition = {
                pageSize: 'A4',
                pageOrientation: 'landscape',
                pageMargins: [ 20, 20, 20, 20 ],
                content: [
                	{image: imageToDataURL(document.getElementById("canvasId")), alignment: 'center'},
                	{text: 'YEARLY REPORT', bold: true, style: 'header', alignment: 'center', margin: [20, 20, 20, 20]},
                    {
                        table: {
			                widths: [80, '*', 80, 70, 80, 70, 80, 70],
                            body:[
                                [
                                    {text: 'YEAR', fontSize: 8, color: '#595959'},
                                    {text: $('#reportYearId').val(), fontSize: 8, bold: true},
                                    {text: 'GROUP', fontSize: 8, color: '#595959'},
                                    {text: '71', fontSize: 8, bold: true},
                                    {text: 'REGISTRATION NUM', fontSize: 8, color: '#595959'},
                                    {text: 'KEA 5025', fontSize: 8, bold: true},
                                    {text: 'PPM DISTRICT', fontSize: 8, color: '#595959'},
                                    {text: 'KULIM', fontSize: 8, bold: true}
                                ],
                                [
                                    {text: 'SCHOOL', fontSize: 8, color: '#595959'},
                                    {text: 'SMK DATO LELA PAHLAWAN', fontSize: 8, bold: true, colSpan: 5},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {text: 'STATE', fontSize: 8, color: '#595959'},
                                    {text: 'KEDAH', fontSize: 8, bold: true}
                                ],
                                [
                                    {text: 'PHONE NUM.', fontSize: 8, color: '#595959'},
                                    {text: '+604-485 7140', fontSize: 8, bold: true},
                                    {text: 'POSTCODE', fontSize: 8, color: '#595959'},
                                    {text: '09400', fontSize: 8, bold: true},
                                    {text: '', fontSize: 8, colSpan: 4},
                                    {},
                                    {},
                                    {}
                                ]
                            ]
                        },
                        layout: {
                            hLineColor: function (i, node) {
                                return '#CED4DA';
                            },
                            vLineColor: function (i, node) {
                                return '#CED4DA';
                            },
                            paddingLeft: function(i, node) { return 10; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 5; },
                            paddingBottom: function(i, node) { return 5; }
                        }
                    },
                    {text: '', margin: [10, 10, 10, 10]},
                    {
                        table: {
			                widths: [20, '*', 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 10, 40, 50, 30, 50],
                            headerRows: 3,
                            body: [
                                [
                                    {text: '', fontSize: 8, colSpan: 2}, {}, {text: 'MEMBERSHIP ACCORDING TO RACE', fontSize: 8, colSpan: 17, alignment: 'center', bold: true}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}
                                ],
                                [
                                    {text: 'NUM', fontSize: 8, rowSpan: 2, alignment: 'center', bold: true}, {text: 'MEMBERSHIP UNIT', fontSize: 8, rowSpan: 2, alignment: 'center', bold: true}, {text: 'MALAY', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'CHINESE', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'INDIAN', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'KADAZAN', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'DAYAK', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'OTHER', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'TOTAL MEMBER', fontSize: 8, colSpan: 2, alignment: 'center', bold: true}, {}, {text: 'OVERALL TOTAL', fontSize: 8, rowSpan: 2, alignment: 'center', bold: true}, {text: 'UNIT FEE', fontSize: 8, rowSpan: 2, alignment: 'center', bold: true}, {text: 'TOTAL FEE COLLECTED', fontSize: 8, rowSpan: 2, alignment: 'center', bold: true}
                                ],
                                [
                                    {}, {}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {text: 'L', fontSize: 8, alignment: 'center', bold: true}, {text: 'P', fontSize: 8, alignment: 'center', bold: true}, {}, {}, {}
                                ],
                                [
                                    {text: 'KUMPULAN', fontSize: 8, colSpan: 18}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {}, {text: '10.00', fontSize: 8, alignment: 'right'}
                                ]
                            ]
                        },
                        layout: {
                            fillColor: function (rowIndex, node, columnIndex) {
                                return (rowIndex === 0 || rowIndex === 1 || rowIndex === 2) ? '#E2E3E5' : null;
                            },
                            hLineWidth: function (i, node) {
                                return (i === 0 || i === (node.table.body.length - 1)|| i === node.table.body.length) ? 1.5 : 1;
                            },
                            vLineWidth: function (i, node) {
                                return (i === 0 || i === node.table.widths.length) ? 1.5 : 1;
                            },
                            hLineColor: function (i, node) {
                                return '#CED4DA';
                            },
                            vLineColor: function (i, node) {
                                return '#CED4DA';
                            },
                            paddingLeft: function(i, node) { return 10; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 5; },
                            paddingBottom: function(i, node) { return 5; }
                        }
                    },
                    { text: '', pageBreak: 'before'},
                    {
                        table: {
			                widths: [20, 80, 50, 40, 40, 40, 50, '*', 40, 80, 50],
                            headerRows: 2,
                            body: [
                                [
                                    {text: 'REGISTRATION MEMBER FOR ' + $('#reportYearId').val(), fontSize: 8, colSpan: 11, alignment: 'center', bold: true},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {},
                                    {},
                                ],
                                [
                                    {text: 'NUM', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'FULL NAME', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'BIRTH DATE', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'IC NUM', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'RACE', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'GENDER', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'RELIGION', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'ADDRESS', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'PAYMENT', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'GUARDIAN NAME', fontSize: 8, alignment: 'center', bold: true},
                                    {text: 'RANK', fontSize: 8, alignment: 'center', bold: true},
                                ]
                            ]
                        },
                        layout: {
                            fillColor: function (rowIndex, node, columnIndex) {
                                return (rowIndex === 0 || rowIndex === 1) ? '#E2E3E5' : null;
                            },
                            hLineWidth: function (i, node) {
                                return (i === 0 || i === node.table.body.length) ? 1.5 : 1;
                            },
                            vLineWidth: function (i, node) {
                                return (i === 0 || i === node.table.widths.length) ? 1.5 : 1;
                            },
                            hLineColor: function (i, node) {
                                return '#CED4DA';
                            },
                            vLineColor: function (i, node) {
                                return '#CED4DA';
                            },
                            paddingLeft: function(i, node) { return 10; },
                            paddingRight: function(i, node) { return 10; },
                            paddingTop: function(i, node) { return 5; },
                            paddingBottom: function(i, node) { return 5; }
                        }
                    }
                ]
            };
            
            let count = 1;
            for(let totalUnit of totalUnits) {
            	if ((count -1) === totalUnits.length - 1) {
            		docDefinition.content[4].table.body.push(
                   		[{text: 'JUMLAH', fontSize: 8, alignment: 'center', colSpan: 2}, {}, {text: totalUnit.unit_total_M_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_M_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_C_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_C_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_I_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_I_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_K_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_K_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_D_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_D_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_O_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_O_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total, fontSize: 8, alignment: 'center'}, {text: (totalUnit.unit_price) ? totalUnit.unit_price.toFixed(2) : '', fontSize: 8, alignment: 'right'}, {text: (totalUnit.unit_total_fee) ? totalUnit.unit_total_fee.toFixed(2): '', fontSize: 8, alignment: 'right'}]
                   	);
            	} else {
            		docDefinition.content[4].table.body.push(
                   		[{text: count, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_name, fontSize: 8}, {text: totalUnit.unit_total_M_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_M_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_C_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_C_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_I_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_I_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_K_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_K_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_D_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_D_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_O_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_O_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_M, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total_F, fontSize: 8, alignment: 'center'}, {text: totalUnit.unit_total, fontSize: 8, alignment: 'center'}, {text: (totalUnit.unit_price) ? totalUnit.unit_price.toFixed(2) : '', fontSize: 8, alignment: 'right'}, {text: (totalUnit.unit_total_fee) ? totalUnit.unit_total_fee.toFixed(2): '', fontSize: 8, alignment: 'right'}]
                   	);
                	count++;
            	}
            }
            
            count = 1;
            for(let data of dataList) {
            	let formattedDate = '';
	    		if (data.studentResponse.studentName) {
	    			let date = new Date(data.studentResponse.studentBirthDate);
	    			formattedDate = date.toLocaleDateString("en-GB");
	    		}
	    		
	    		if (data.membershipResponse.membershipJoinedYear === $('#reportYearId').val()) {
		    		if (data.membershipResponse.membershipPayStatus === 'APPROVE') {
		    			docDefinition.content[6].table.body.push(
		           			[
		   		                {text: count, fontSize: 8},
		   		                {text: data.studentResponse.studentName, fontSize: 8},
		   		                {text: formattedDate, fontSize: 8},
		   		                {text: data.studentResponse.studentIdentificationNo, fontSize: 8},
		   		                {text: data.studentResponse.studentRace, fontSize: 8},
		   		                {text: data.studentResponse.studentGender, fontSize: 8},
		   		                {text: data.studentResponse.studentReligion, fontSize: 8},
		   		                {text: data.studentResponse.studentAddress, fontSize: 8},
		   		                {text: '7.00', fontSize: 8},
		   		                {text: (data.guardianResponse) ? data.guardianResponse.guardianName : '', fontSize: 8},
		   		                {text: data.membershipResponse.membershipRank, fontSize: 8},
		   		            ]
		            	);
		            	count++;
		    		}
	    		}
            }

            pdfMake.createPdf(docDefinition).open();
        }
		
		function imageToDataURL(canvas) {
			let context = canvas.getContext("2d");
			let image = document.getElementById("logoId");
			context.drawImage(image, 0, 0, 100, 100);
			return canvas.toDataURL();
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                         