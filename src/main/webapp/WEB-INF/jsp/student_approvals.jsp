<%@ include file="sidebar/sidebar_header.jspf" %>
	<div class="content">
		<%@ include file="top_nav.jspf" %>
		<div class="p-2" id="contentId"></div>
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
			onGetBadges();
		}
		
		function onGetBadges() {
			$.ajax({
				type: "GET",
		        url: "/core/badges"
		    }).then(function(dataList) {
		    	let sortedDataList = sortByKey(dataList, 'badgeCategoryOrder')
		    	let badgeCategories = getUniqueValueFromObjectArrayByKey(sortedDataList, 'badgeCategory');
		    	
		    	for(let badgeCategory of badgeCategories) {
		    		if (badgeCategory) {
		    			$('#contentId').append($('<div>')
		    				.addClass('card')
		    				.addClass('w-100')
		    				.append($('<h4>')
		    					.addClass('card-header')
		    					.html(badgeCategory)
		    				)
		    				.append($('<ul>')
		    					.addClass('list-group')
		    					.addClass('list-group-flush')
		    					.append($('<li>')
		    						.addClass('list-group-item')
		    						.addClass('p-2')
									.append($('<div>')
										.addClass('accordion')
										.attr('id', 'accordionFlushExample')
										.append($('<div>')
											.addClass('accordion-item')
											.append($('<h2>')
												.addClass('accordion-header')
												.append($('<button>')
													.addClass('accordion-button')
													.addClass('collapsed')
													.attr('type', 'button')
													.attr('data-bs-toggle', 'collapse')
													.attr('data-bs-target', '#flush-collapseOne')
													.html('badge')
												)
											)
											.append($('<div>')
												.attr('id', 'flush-collapseOne')
												.addClass('accordion-collapse')
												.addClass('collapse')
												.attr('data-bs-parent', '#accordionFlushExample')
												.append($('<div>')
													.addClass('accordion-body')
													.addClass('p-2')
													.append($('<table>')
														.attr('id', 'normalTable')
														.append($('<tr>')
															.append($('<td>')
																.addClass('w-25')
																.append($('<select>')
																	.addClass('form-select')
																	.addClass('form-select-sm')
																	.append($('<option>')
																		.attr('value', '')
																		.html('PLEASE CHOOSE')
																	)
																	.append($('<option>')
																		.attr('value', 'APPROVED')
																		.html('APPROVED')
																	)
																	.append($('<option>')
																		.attr('value', 'NOT APPROVED')
																		.html('NOT APPROVED')
																	)
																)
															)
															.append($('<td>')
																.addClass('w-75')
																.html('badge title')
															)
														)
													)
												)
											)
										)
									)
		    					)
		    				)
		    			);
		    		}
		    	}
			});
		}
	</script>
<%@ include file="sidebar/sidebar_footer.jspf" %>