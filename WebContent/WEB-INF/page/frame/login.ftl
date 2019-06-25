<!DOCTYPE html>
<html>
  <head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=8" />
	<meta name="renderer" content="webkit">
	<link rel="stylesheet" type="text/css" href="${base}/battery/css/login.css">
	<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
    <title>变桨电容监测系统</title>
	<script type="text/javascript">
	
		/* $(document).ready(function(){
			var _height2=$(window).height();
			$(".wnLogin").css("height", _height2 + "px");

		}); */
		
	 document.onkeydown=function(e){//网页内按下回车触发
		switch ((window.event)?event.keyCode:e.which){
			case 13:
				submit();
				return false;
				break;
			default:
				break;
		}
	}
	  function submit(){
	

	  	var f = document.getElementById("fm");
	  	f.submit();
 
	  }
  </script>
</head>
  <body>
  	<div class="wnLogin">
		<form id="fm" action="${base}/tologin.action" method="post">
			<div class="loginBox">
				<div class="boxRight">
				<h2>xx风电场风机变桨电容监测系统</h2><hr>	
					<div class="boxMain">
						<div class="wnUser">
							<p>系统登录<span>UserLogin</span></p><br>
						</div>
						<div class="wnText">
							<label for="" class="user"></label>
							<input name="username" type="text" placeholder="用户名" class="user">
							
						</div>
						<div class="wnText">
							<label for="" class="pwd"></label>
							<input name="password" type="password" placeholder="密码" class="pwd">
							<p style="">${error?if_exists}</p>
						</div>
						
						<div class="wnText btn">
							<a href="javascript:void(0);" class="login" onclick="submit();">登录</a>
							<span>建设单位：北京国电思达科技有限公司-技术部</span>
						</div>
						
					</div>
				</div>
			</div>
			</form>
	</div>
</body>
</html>