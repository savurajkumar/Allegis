$(document).on('click','.side-nav li a',function(){
	var link = $(this).attr("data-href");
	console.log("HI");
	$.get(link,function(response){
		$("#page-wrapper").html(response);
		$('.side-nav li').removeClass('active');
	});
	$(this).parent().addClass('active');
});