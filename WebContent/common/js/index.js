$(document).ready(function(){
	var storage = window.localStorage;
	 var a= storage.getItem("cc");
	if(a!=null){ var b= a.substr(0, 2);
	 var last2= a.substr(2, 4);
	 if(last2=='00')
		 {
		 $('#'+a).addClass("now");
		 }
	 else{
		 var c=b+'00';
		 $('#'+c).addClass("now");
	 }
	}
	 
	$(".hite").mouseover(function(){
		$(".leftMenu_sonSon").show();
	});
	$(".hite").mouseout(function(){
		$(".leftMenu_sonSon").hide();
	});
	$(".hite2").mouseover(function(){
		$(".leftMenu_sonSon2").show();
	});
	$(".hite2").mouseout(function(){
		$(".leftMenu_sonSon2").hide();
	});

	var _height=document.body.scrollHeight;
	$('.leftMenu').css('height',_height -131+'px');
	
	
//	$("a.leftMenu_textSon").mouseover(function(){
//		$(this).css("color","#0099cb");
//	})
//	$("a.leftMenu_textSon").mouseout(function(){
//		$(this).css("color","");
//	})
//	$("a.leftMenu_textSon2").mouseover(function(){
//		$(this).css("color","#0099cb");
//	})
//	$("a.leftMenu_textSon2").mouseout(function(){
//		$(this).css("color","");
//	})
//	$(".leftMenu_son").click(function(){
//		$(this).css("background-color","#");
//	})
	
		
});

