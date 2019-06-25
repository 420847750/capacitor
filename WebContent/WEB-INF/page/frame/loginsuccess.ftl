<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
<title>正在跳转</title>
</head>
<body>
</body>
<script language="javascript">
$(function(){
	var user = $("#user").val();
		if(user=='3'){
			var storage = window.localStorage;
			storage.removeItem("cc");
		  storage.setItem("cc","3000");
		 var a= storage.getItem("cc");
		 window.location.href="${base}/show.action?code=illegal_list_jc";
		}
		else if(user=='2'){
		var storage = window.localStorage;
		storage.removeItem("cc");
		  storage.setItem("cc","2100");
		 var a= storage.getItem("cc");	
		 
		window.location.href="${base}/${code?if_exists}";
		}
		else if(user=='1'){
		var storage = window.localStorage;
		storage.removeItem("cc");
		  storage.setItem("cc","0100");
		 var a= storage.getItem("cc");	
		window.location.href="${base}/${code?if_exists}";
		}
	})
</script>
	<input type="hidden" id="user" value="${user.roleCode?if_exists}">  
</html>