function exportxml(){
	$.ajax({
				url :"/fancon/export/exporttest.action",
				type : 'POST',
				success : function(data) {
					alert(data);

				}
			})
	}
					