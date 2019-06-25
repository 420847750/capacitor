<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>修改密码</title>
	<link rel="stylesheet" href="${base}/common/css/index.css">
	<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
	<script type="text/javascript" src="${base}/common/js/frame.js"></script>
	<script type="text/javascript" src="${base}/common/js/index.js"></script>
</head>
<body>
		<div class="g_body">
		<div class="g_head" id="top" >
		</div>
		<div class="middleCon">
			<div class="leftMenu" id="menu">
			</div>
			<div class="middleCenter">
				<div class="centerHead">
					<span class="qianImg"></span>
					<span class="qianText">密码修改</span>
				</div>
				<div class="new">
				<form id="form" action="${base}/safe/save.action" method="post">
					<div class="newDetail">
						<div class="enterprise">
							<div class="enterpriseText"><span class="">原密码：</span></div>
							<div class="enterpriseInput"><input name="oldpassword" id="oldpassword" type="password" >
							<span style="color:red; margin-right: 10px;">*</span><span  id="old" class="errorspan"></span></div>
						</div>
						<div class="enterprise">
							<div class="enterpriseText"><span class="">新密码：</span></div>
							<div class="enterpriseInput"><input name="password" id="password" type="password">
							<span style="color:red; margin-right: 10px;">*</span><span id="new" class="errorspan"></span></div>
						</div>
						<div class="enterprise">
							<div class="enterpriseText"><span class="">确认密码：</span></div>
							<div class="enterpriseInput"><input name="reword" id="reword" type="password" >
							<span style="color:red; margin-right: 10px;">*</span><span id="re" class="errorspan"></span></div>
						</div>
						
						<div class="enterprise2">
							<div class="enterpriseText"><span class=""></span></div>
							<div class="enterpriseCheck">
								<a href="javascript:void(0);" onclick="save();" class="auditPass">确认</a>
								<a href="javascript:history.go(-1)" class="return">返回</a>
							</div>
						</div>
						
					</div>
					</form>
				</div>
				
			</div>
		</div>
		
	</div>
</body>
<script>
 	function save(){
 		var oldpassword=$("#oldpassword").val();
 		var pwd = /^([a-zA-Z0-9]){4,16}$/;
			if (oldpassword==''){
	   			$("#old").html("原密码不能为空");
			}else{
				$.ajax({
      	 		url: '${base}/safe/checkpassword.action?password='+oldpassword,
	      		type:'post',
	      	 	async:false,
	       		success: function (data) { 
	       			$("#old").html("");
	       			if(data && data=="1"){
	       				 var password=$("#password").val();
						 var reword=$("#reword").val();
						 if (password==''){
							 $("#new").html("新密码不能为空");
						 }
						  else if (reword==''){
							$("#new").html("");
							$("#re").html("确认密码不能为空");
						 }
						  else if(!pwd.test(password)){
						 	$("#new").html("新密码必须为4-16位数字或者字母");}
						  else if(password!=reword){
							$("#new").html("");
						 	$("#re").html("两次新密码输入不一致请重新填写");
						 	}
						  else{
							$("#form").submit();
							}
		   				}
		   			else{
		   			$("#old").html("原密码不正确");
		   			}
	           	  },
	         	});
			}
 }
 	</script>
</html>