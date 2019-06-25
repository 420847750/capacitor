<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="./battery/css/dianchi.css">
<script type="text/javascript" src="./battery/js/echarts.min.js"></script>
<script type="text/javascript" src="./js/jquery-1.9.1.js"></script>
<script type="text/javascript" src="./js/wdatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${base}/js/artdialog/artDialog.js?skin=opera"></script>
<script type="text/javascript" src="${base}/js/artdialog/plugins/iframeTools.js"></script>
	
<title>管理界面</title>
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
		<div class="top_right">
			<span class="g_headRightpic2"></span> 
			<a href="#" onclick="gosafe();" class="g_headRightloginout">修改密码</a> 
			<span class="xiaoline"></span>
			<span>${user.userName?if_exists}用户，欢迎您</span>
			<a href="${base}/logout.action" class="g_headRightloginout">退出</a>
		</div>
	</div>
     <nav>
        <a href="${base}/main.action">主&nbsp;&nbsp;&nbsp;&nbsp;页</a>
        <a href="${base}/test.action">操作界面</a>
        <a href="${base}/show.action?code=manage"  class="active">管理界面</a>
        <a href="${base}/config.action">配置界面</a>
    </nav>
    <section>
        <form id="form" method="post" action="${base}/show.action?code=manage" class="Query_log" >
		<input type="hidden" id="pageNo" name="pageNo" value="1" />
        	<span>请选择机位号：</span>
        	<select name="deviceName" id="deviceName" >
        	<option value="">请选择</option>
        	
        	<#list data.ds2 as snlist>
        		<option value="${snlist.name?if_exists}" <#if args.deviceName=='${snlist.name?if_exists}'>selected</#if>>${snlist.name?if_exists}</option>
        	</#list>
        	</select>&nbsp;&nbsp;&nbsp;&nbsp;
 			<span>开始时间:</span><input type="text" name="start" id="start" value="${args.start?if_exists}" autocomplete="off"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">&nbsp;&nbsp;&nbsp;&nbsp;
    		<span>结束时间:</span><input type="text" name="end" id="end" value="${args.end?if_exists}" autocomplete="off"
						onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">&nbsp;&nbsp;&nbsp;&nbsp;
  			<input type="button" onclick='doSearch();' value="查询">&nbsp;&nbsp;&nbsp;&nbsp;
		</form>
        <fieldset class="fenxi_left">
        	<table>
        		<thead>
        			<tr><th>序号</th><th>机位号</th><th>测试时间</th><th>轴1压降</th><th>轴2压降</th><th>轴3压降</th><th>测试结果</th></tr>
        		</thead>
        		<tbody>
        		<#list data.ds1 as resultList>
        		<tr><td>${resultList_index+1}</td>
        			<td>${resultList.fan_name?if_exists}</td>
        			<td><#if '${resultList.test_time?if_exists}'!=''>	
					${resultList.test_time?string("yyyy-MM-dd   HH:mm:ss")}</#if></td>
        			<td>${resultList.yajiang1?if_exists}</td>
        			<td>${resultList.yajiang2?if_exists}</td>
        			<td>${resultList.yajiang3?if_exists}</td>
        			<td><#if '${resultList.status1?if_exists}'=='0'&&'${resultList.status2?if_exists}'=='0'&&'${resultList.status3?if_exists}'=='0'>
        				健康
        				<#else> 
        				不健康
        			</#if></td></tr>
                    </#list>
        		</tbody>
        	</table>
        	<!-- 页码控制 -->
        	<#include "/frame/paging.ftl">
        </fieldset>
	        <!-- 曲线比较 
	        <fieldset class="wave">
	                <div id="sjsj"></div>
	                <div id="dryj"></div>
	        </fieldset>-->
    </section>
   <script>
			function doSearch() {
		
		var start= $("#start").val();
		var end= $("#end").val();
		var deviceName= $("#deviceName").val();
			 var form = document.getElementById("form");
				form.submit();

	}      
	function goPage(newPage, pageCount) {
	if (isIntNum(newPage)) {
		alert("数字格式不正确！");
		return;
	}
	if (newPage > pageCount) {
		alert("页码太大，不能超过"+pageCount);
		return;
	}
	var pageNum = document.getElementById("pageNo");
	pageNum.value = newPage;
	doSearch();
}
function skipPage(pageCount){
	var newPage = $("#pageint").val();
	goPage(newPage,pageCount);
}
function goPage2(newPage, pageCount) {
	if (isIntNum(newPage)) {
		alert("数字格式不正确！");
		return;
	}
	if (newPage > pageCount) {
		alert("页码太大，不能超过"+pageCount);
		return;
	}
	var pageNum = document.getElementById("pageNo");
	pageNum.value = newPage;
	doSearch2();
}
function skipPage2(pageCount){
	var newPage = $("#pageint").val();
	goPage2(newPage,pageCount);
}

function isIntNum(value) {
	if (/^[1-9]{1}[0-9]{0,8}$/.test(value)) {
		return false;
	} else {
		return true;
	}
}
		</script>
</body>
</html>
