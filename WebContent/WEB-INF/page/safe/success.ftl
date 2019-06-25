<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html" charset="UTF-8">
	<META HTTP-EQUIV="Pragma" CONTENT="no-cache">
	<META HTTP-EQUIV="Cache-Control" CONTENT="no-cache,must-revalidate">
	<META HTTP-EQUIV="Expires" CONTENT="0">
	<script src="${base}/common/js/jquery.min.js" type="text/javascript"></script>
	<script type="text/javascript" src="${base}/common/artdialog/artDialog.js?skin=opera"></script>
	<script type="text/javascript" src="${base}/common/artdialog/plugins/iframeTools.js"></script>
</head>
<script type="text/javascript">
$(document).ready(function(){
	art.dialog({
				title: '提示信息',
				content: '操作成功！',
				icon: 'succeed',
				lock:true,
				time: 0.5,
				close:function(){
			     art.dialog.close();
						window.location = '${base}/main.action';
				},
				 success:function(){
				  art.dialog.close();
					window.location = '${base}/main.action';
     }
		});
	});
</script>
<body>

</body>
</html>
