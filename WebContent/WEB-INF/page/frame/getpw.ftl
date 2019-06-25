<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8">
		<title>监控界面</title>
		<link type="text/css" rel="stylesheet" href="${base}/common/css/main.css">
		<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
		<script type="text/javascript" src="${base}/common/artdialog/artDialog.js?skin=opera"></script>
		<script type="text/javascript" src="${base}/common/artdialog/plugins/iframeTools.js"></script>	
	</head>
	<body>
		<input  type="hidden" id="type"  value="${type?if_exists}">
		<input  type="hidden" id="ids"  value="${ids?if_exists}">
		请确认是否执行该操作，如${type?if_exists}确定，请输入密码后点击确定。
		<br><hr>
		<input name="password" type="password" id="password" placeholder="密码" class="pwd">
		<br><hr>
		<a href="javascript:void(0);" onclick="submit();" class="password">确&nbsp;&nbsp;认</a>&nbsp;&nbsp;&nbsp;&nbsp;
		<a href="javascript:void(0);" onclick="closez();" class="password">取&nbsp;&nbsp;消</a>
	</body>
	<script type="text/javascript">
		function submit() {
			var password=$("#password").val();
			var ids=$("#ids").val();	
			var type=$("#type").val();
			$.ajax({
				url : "/fancon/count/checkpw.action?password="+password+"&ids="+ids+"&type="+type,
				type : 'POST',
				success : function(data) {
					if(data=="0"){
					alert("密码错误，请重新输入");
					}
					else{
					alert("操作成功");
					art.dialog.close();
					}
				}
			});
		}
 		function closez(){
		art.dialog.close();
		}
	</script>
<!--
$(document).ready(function(){
	var dialog = art.dialog({
				title: '提示信息',
				content: '操作成功',
				icon: 'succeed',
				lock:true,
				time: 1,
				close:function(){
				art.dialog.close();
				 var str = "${base}/show.action?code=emp&u=${u?if_exists}&m=${m?if_exists}";
					window.open(str,'mainFrame');
				}
		});
	});
	-->
</html>
