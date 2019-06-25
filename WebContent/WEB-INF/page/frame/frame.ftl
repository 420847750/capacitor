<!doctype html>
<html>
<head>
<meta charset="utf-8">
<title>变桨后备电源全生命周期管理平台</title>
<link type="text/css" rel="stylesheet" href="./battery/css/dianchi.css">
<script>
	function gosafe(){
	  var storage = window.localStorage;
	  storage.removeItem("cc");
	  var a= storage.getItem("cc");
      window.location.href="${base}/show.action?code=safe";
   };
</script>
</head>
<body>
    <h1>变桨后备电源全生命周期管理平台</h1>
    <div class="head_right">
		<input type="hidden" id="id" value="${user.id?if_exists}">
		<div>
			<span class="g_headRightpic2"></span> 
			<a href="#" onclick="gosafe();"class="g_headRightloginout">修改密码</a> 
			<span class="xiaoline"></span>
			<span>${user.userName?if_exists}用户，欢迎您</span>
			<a href="${base}/logout.action" class="g_headRightloginout">退出</a>
		</div>
	</div>
    <nav>
        <a href="${base}/main.action" class="active">主&nbsp;&nbsp;&nbsp;&nbsp;页</a>
        <a href="${base}/test.action">操作界面</a>
        <a href="${base}/show.action?code=manage">管理界面</a>
        <a href="${base}/config.action">配置界面</a>
    </nav>
	<section id="main">
    	<#list datalist as list >
    	<div class="fengji">
	        <img src="./battery/img/yellow.png"/>
		    <div class="message">
		    	<span>机位号：${list.fanName?if_exists}</span><br>
		    	<span>状态：</span>需测试
		    </div>
		    <div class="msg" id="msg">
		    	出厂日期：<input type="text" name="" value="${list.createDate?if_exists}"><br>
		    	测试周期：<input type="text" name="" value="${list.testPeriod?if_exists}个月"><br>
		    	上次测试：<input type="text" name="" value="${list.lastDate?if_exists}"><br>
		    	下次测试：<input type="text" name="" value="${list.nextDate?if_exists}">
			</div>
        </div>
        </#list>
    </section>
    <script type="text/javascript" src="./battery/js/capacitor.js"></script>
</body>
</html>
