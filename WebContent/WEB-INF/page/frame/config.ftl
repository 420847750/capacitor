<!DOCTYPE html>
<html>
<head>
	<title>config</title>
	<link type="text/css" rel="stylesheet" href="./battery/css/dianchi.css">
	<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
	<script type="text/javascript" src="${base}/common/wdatePicker/WdatePicker.js"></script>
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
        <a href="${base}/main.action" >主&nbsp;&nbsp;&nbsp;&nbsp;页</a>
        <a href="${base}/test.action">操作界面</a>
        <a href="${base}/show.action?code=manage">管理界面</a>
        <a href="${base}/config.action" class="active">配置界面</a>
    </nav>
    <section class="config_1">
        <fieldset class="program_config">
            <legend>程序配置</legend>
            <input type="button" name="" value="导入风场配置表" onclick="exportxml();"> 
           <!-- <input type="button" name="" value="导入数据配置表">-->
        </fieldset>
    	<fieldset class="date_cc">
            <legend>出厂日期配置</legend>
            <table>
                <thead>
                    <tr><th>机位号</th><th>出厂日期</th><th>上次测试日期</th><th></th></tr>
                </thead>
                <tbody>
                <#list datalist  as list>
	                <tr>
		                <td>${list.fanName?if_exists}</td>
		                <td>${list.createDate?if_exists}</td>
		                <td>${list.createDate?if_exists}</td>
		                <td><input type="button" value="确认修改" onclick="sure('${list.fanName?if_exists}','date${list_index}');"></td>
                    </tr>
               	</#list>
                </tbody>
            </table>
        </fieldset>
    </section>
</body>
<script type="text/javascript" >
function exportxml(){
		$.ajax({
			url :"/capacitor/export/export2.action",
			type : 'POST',
			success : function(data) {
				alert("导入配置成功");
				window.location.reload();
			}
		})
	}
function sure(obj1,obj2){
	var date=$("#"+obj2).val();
	  if(typeof date == "undefined" || date == null || date == ""){
        alert("请先选择日期");
    }else{
       $.ajax({
			url :"/capacitor/saveDate.action?fanName="+obj1+"&date="+date,
			type : 'POST',
			success : function(data) {
				alert("保存成功");
				window.location.reload();
			}
		})
    }
		
	}
	</script>
			
</html>