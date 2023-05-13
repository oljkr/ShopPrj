/**
 * 
 */

function responseProc(){
    var queryString = $("form[name=loginfrm]").serialize() ;

		$.ajax({
			type : 'post',
			url : '/loginproc',
			data : queryString,
			dataType : 'text',
			error: function(xhr, status, error){
				alert(error);
			},
			success : function(json){
				alert(json)
			}
		});
}//click() end