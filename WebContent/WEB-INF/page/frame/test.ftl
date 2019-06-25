<!doctype html>
<html>
<head>
<meta charset="utf-8">
<link type="text/css" rel="stylesheet" href="./battery/css/dianchi.css">
<script type="text/javascript" src="${base}/common/js/jquery.min.js"></script>
<title>操作界面</title>
<script>
	function gosafe(){
	  var storage = window.localStorage;
	  storage.removeItem("cc");
	  var a= storage.getItem("cc");
      window.location.href="${base}/show.action?code=safe";
   };
   function uuid() {
    var s = [];
    var hexDigits = "0123456789abcdef";
    for (var i = 0; i < 36; i++) {
        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
    }
    s[14] = "4"; // bits 12-15 of the time_hi_and_version field to 0010
    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1); // bits 6-7 of the clock_seq_hi_and_reserved to 01
    s[8] = s[13] = s[18] = s[23] = "-";

    var uuid = s.join("");
    return uuid;
}
</script>
</head>
<body>
    <h1>变桨后备电源全生命周期管理平台</h1>
<!-- 右上角退出登录等 -->
    <div class="head_right">
    	<input type="hidden" id="wsid" value="">
				
		<input type="hidden" id="id" value="${user.id?if_exists}">
		<div>
			<span class="g_headRightpic2"></span> 
			<a href="#" onclick="gosafe();"class="g_headRightloginout">修改密码</a> 
			<span class="xiaoline"></span>
			<span>${user.userName?if_exists}用户，欢迎您</span>
			<a href="${base}/logout.action" class="g_headRightloginout">退出</a>
		</div>
	</div>
<!-- 导航 -->
     <nav>
        <a href="${base}/main.action">主&nbsp;&nbsp;&nbsp;&nbsp;页</a>
        <a href="${base}/test.action"  class="active">操作界面</a>
        <a href="${base}/show.action?code=manage">管理界面</a>
        <a href="${base}/config.action">配置界面</a>
    </nav>
<!-- 测试界面 -->
    <section class="operation">
        <div class="control_test">
            <span>请选择机位号:</span>
	        <select name="deviceName"  id="deviceName" onchange="change();">
                <option>请选择</option>
                <#list snlist as snlist>
                <option <#if '${fanname}'=='${snlist.name}'> selected</#if> value="${snlist.name}">${snlist.name}</option>
                </#list>
            </select>
            </br>
            <span>通讯状态:</span><img id="Communication_status" alt="" src="./battery/img/gray.png" align="absmiddle" title="绿色代表通讯正常"><br>
            <span>待机</span><img id="MAIN_STANDSTILL" src="./battery/img/gray.png" align="absmiddle" title="绿色代表待机状态"><br>
            <span>停机</span><img id="MAIN_STOP" src="./battery/img/gray.png" align="absmiddle" title="红色代表停机状态"><br>
          <#--  <span>外部停机</span><img id="External_stop_demand" src="./battery/img/gray.png" align="absmiddle" title="红色代表停机外部停机命令"><br>
            <span>启动</span><img id="MAIN_STARTING" src="./battery/img/gray.png" align="absmiddle" title="红色代表启动状态"><br>
            --><span>机组故障</span><img id="ERROR_ERROR_GLOBAL" src="./battery/img/gray.png" align="absmiddle" title="红色代表故障状态"><br>
            <span>安全链故障</span><img id="SAFETY_ERROR_FROM_PITCH" src="./battery/img/gray.png"  align="absmiddle" title="红色代表故障状态"><br>
            <span>60秒平均风速：</span><input id="AVERAGE_WIND_SPEED_60S" type="text" name="" value="" disabled="disabled"><br>
            <form>
            	<input type="hidden" id="BLOCK_STARTING_INPUT" value="">
                <input type="hidden" id="status" value="">
                <button id="CONTROL_STOP" onclick="doaction('1');" type="button">停&nbsp;&nbsp;&nbsp;&nbsp;机</button>&nbsp;&nbsp;<br>
                <button id="BLOCK_STARTING_MODE" onclick="doaction('2');" type="button">禁止启动</button>&nbsp;&nbsp;<img id="BLOCK_STARTING_MODE_STATUS" src="./battery/img/gray.png" align="absmiddle" title="红色代表禁止启动命令激活"><br>
                <button id="B_ManualBatteryTest" onclick="doaction('3');" type="button">开始自检</button>&nbsp;&nbsp;<img id="B_ManualBatteryTest_STATUS" src="./battery/img/gray.png" align="absmiddle" title="红色代表测试模式开启"><br>
                <button id="BLOCK_STARTING_MODE" onclick="doaction('4');" type="button">取消禁止启动</button>&nbsp;&nbsp;<br>
                <button id="CONTROL_START" onclick="doaction('5');" type="button">启&nbsp;&nbsp;&nbsp;&nbsp;动</button>&nbsp;&nbsp;<br>
            </form>
        </div> 
        <div class="information">
            <div>
            	<p>实时数据</p>
            	<span>轴1桨距角：</span><input id="PITCH_POSITION_1" type="text" name="" value="" disabled="disabled">
	            <span>轴2桨距角：</span><input id="PITCH_POSITION_2" type="text" name="" value="" disabled="disabled">
	            <span>轴3桨距角：</span><input id="PITCH_POSITION_3" type="text" name="" value="" disabled="disabled"><br>
	
	            <span>轴1变桨速度：</span><input id="PITCH_SPEED_1" type="text" name="" value="" disabled="disabled">
	            <span>轴2变桨速度：</span><input id="PITCH_SPEED_2" type="text" name="" value="" disabled="disabled">
	            <span>轴3变桨速度：</span><input id="PITCH_SPEED_3" type="text" name="" value="" disabled="disabled"><br>
	
	            <span>轴1电容电压：</span><input id="CAPACITOR_VOLTAGE_1" type="text" name="" value="" disabled="disabled">
	            <span>轴2电容电压：</span><input id="CAPACITOR_VOLTAGE_2" type="text" name="" value="" disabled="disabled">
	            <span>轴3电容电压：</span><input id="CAPACITOR_VOLTAGE_3" type="text" name="" value="" disabled="disabled"><br>
	
	            <span>轴1电容温度：</span><input id="CAPACITOR_TEMP_1" type="text" name="" value="" disabled="disabled">
	            <span>轴1电容温度：</span><input id="CAPACITOR_TEMP_2" type="text" name="" value="" disabled="disabled">
	            <span>轴1电容温度：</span><input id="CAPACITOR_TEMP_3" type="text" name="" value="" disabled="disabled"><br>
            </div>
            <div id="last">
            	<p>上次测试数据</p> 
            	<span>测试时间：</span><input id="testtime" type="text" name="" value="" disabled="disabled" style=" width: 200px"><br>
	            <span>轴1压降：</span><input id="yajiang1" type="text" name="" value="" disabled="disabled">
	            <span>轴2压降：</span><input id="yajiang2" type="text" name="" value="" disabled="disabled">
	            <span>轴3压降：</span><input id="yajiang3" type="text" name="" value="" disabled="disabled"><br>
	            <span>轴1状态：</span><input id="status1" type="text" name="" value="" disabled="disabled">
	            <span>轴2状态：</span><input id="status2" type="text" name="" value="" disabled="disabled">
	            <span>轴3状态：</span><input id="status3" type="text" name="" value="" disabled="disabled">
	            
			</div>
		</div>
    </section>
</body>
<script type="text/javascript" >
var websocket = null;
//判断当前浏览器是否支持WebSocket
if ('WebSocket' in window) {

	var aaa=uuid();
	 $("#wsid").val(aaa);
	var domain = window.location.host;
    websocket = new WebSocket("ws://"+domain+"/capacitor/websocket/"+aaa);
}
else {
    alert('当前浏览器 Not support websocket')
}

//连接发生错误的回调方法
websocket.onerror = function () {
 	flag=0;
	setMessageInconsole("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
 	flag=1;
	setMessageInconsole("WebSocket连接成功");
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
	setMessageInnerHTML(event.data);
}

//连接关闭的回调方法
websocket.onclose = function () {
 	flag=0;
	setMessageInconsole("WebSocket连接关闭");
}

//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
window.onbeforeunload = function () {
    closeWebSocket();
}


function setMessageInconsole(innerHTML) {
	console.log(innerHTML+"aaa");
}

//关闭WebSocket连接

function closeWebSocket() {
	flag=0;
    websocket.close();
}
	
		function go(){
		var  fanname=$("#deviceName").val()+"|"+$("#wsid").val();
				websocket.send(fanname);
		}
function change(){
	var  fanname=$("#deviceName").val();
	$.ajax({
			url : "/capacitor/count/cleanstatus.action",
			type : 'POST',
			success : function(data) {
			}
		});
	if(fanname!="请选择"){
		window.location.href="${base}/test.action?fanname="+fanname;
		}else{
		window.location.href="${base}/test.action";
		}
	}
	var iv=setInterval("time()",1000);
		
			function time(){ 
			var  fanname=$("#deviceName").val();
			if(flag=1){
				if(fanname!="请选择"){
					go();
				}
				else{
				clearInterval(iv);
					$('#Communication_status').attr('src','./battery/img/gray.png');
				 	$('#MAIN_STANDSTILL').attr('src','./battery/img/gray.png');
				 	$('#MAIN_STOP').attr('src','./battery/img/gray.png');
				 	$('#External_stop_demand').attr('src','./battery/img/gray.png');
				 	$('#MAIN_STARTING').attr('src','./battery/img/gray.png');
				 	$('#ERROR_ERROR_GLOBAL').attr('src','./battery/img/gray.png');
				 	$('#SAFETY_ERROR_FROM_PITCH').attr('src','./battery/img/gray.png');
					document.getElementById('AVERAGE_WIND_SPEED_60S').value='';
				 	$('#BLOCK_STARTING_MODE_STATUS').attr('src','./battery/img/gray.png');
				 	$('#B_ManualBatteryTest_STATUS').attr('src','./battery/img/gray.png');
				 	
				/* 控制 */
				 	document.getElementById('CONTROL_STOP').value = '';
				 	document.getElementById('BLOCK_STARTING_MODE').value = '';
				 	document.getElementById('B_ManualBatteryTest').value = '';
				 	document.getElementById('CONTROL_START').value = '';
				/* 模拟量 */
				 	document.getElementById('PITCH_POSITION_1').value = '';
				 	document.getElementById('PITCH_POSITION_2').value = '';
				 	document.getElementById('PITCH_POSITION_3').value = '';
				 	document.getElementById('PITCH_SPEED_1').value = '';
				 	document.getElementById('PITCH_SPEED_2').value = '';
				 	document.getElementById('PITCH_SPEED_3').value = '';
				 	document.getElementById('CAPACITOR_VOLTAGE_1').value = '';
				 	document.getElementById('CAPACITOR_VOLTAGE_2').value = '';
				 	document.getElementById('CAPACITOR_VOLTAGE_3').value = '';
				 	document.getElementById('CAPACITOR_TEMP_1').value = '';
				 	document.getElementById('CAPACITOR_TEMP_2').value = '';
				 	document.getElementById('CAPACITOR_TEMP_3').value = '';
				}
				
			}
			else{
			}
		}
	
		
		
		function getdate(obj) {
            var now = new Date(obj),
                y = now.getFullYear(),
                m = now.getMonth() + 1,
                d = now.getDate();
            return y + "-" + (m < 10 ? "0" + m : m) + "-" + (d < 10 ? "0" + d : d) + " " + now.toTimeString().substr(0, 8);
        }  
//将消息显示在网页上
   function setMessageInnerHTML(innerHTML) {
   	var sss=JSON.parse(innerHTML);
	console.log(sss);
   	   	
	 	if (sss.data[0].status==true){
	 		if(sss.status1==1){
	 		$('#BLOCK_STARTING_MODE_STATUS').attr('src','./battery/img/red.png');
		
	 		}else {
	 		$('#BLOCK_STARTING_MODE_STATUS').attr('src','./battery/img/green.png');
		
	 		}
	 	}
	 	else{
	 		$('#BLOCK_STARTING_MODE_STATUS').attr('src','./battery/img/gray.png');
	 	}
	 	if (sss.data[0].status==true){
	 		if(sss.status2==1){
	 		$('#B_ManualBatteryTest_STATUS').attr('src','./battery/img/red.png');
		
	 		}else {
	 		$('#B_ManualBatteryTest_STATUS').attr('src','./battery/img/green.png');
		
	 		}
	 	}
	 	else{
	 		$('#B_ManualBatteryTest_STATUS').attr('src','./battery/img/gray.png');
	 	}
				 	
/* 上次测试结果 */  
	if(("data2" in sss)){

	   	document.getElementById('testtime').value = getdate(sss.data2.testTime);;
		document.getElementById('yajiang1').value = sss.data2.yajiang1;
	 	document.getElementById('yajiang2').value = sss.data2.yajiang2;
	 	document.getElementById('yajiang3').value = sss.data2.yajiang3;
	 	if(sss.data2.status1=="0"){
		document.getElementById('status1').value = "健康";
	 	}else if(sss.data2.status1=="1"){
	 	document.getElementById('status1').value = "不健康";
	 	}
	 	if(sss.data2.status2=="0"){
		document.getElementById('status2').value = "健康";
	 	}else if(sss.data2.status2=="1"){
	 	document.getElementById('status2').value = "不健康";
	 	}
	 	if(sss.data2.status3=="0"){
		document.getElementById('status3').value = "健康";
	 	}else if(sss.data2.status3=="1"){
	 	document.getElementById('status3').value = "不健康";
	 	}
 	
   	}else{

	   	document.getElementById('testtime').value = "";;
		document.getElementById('yajiang1').value = "";;
	 	document.getElementById('yajiang2').value = "";;
	 	document.getElementById('yajiang3').value = "";;
		document.getElementById('status1').value = "";
		document.getElementById('status2').value = "";
	 	document.getElementById('status3').value = "";
	 	}
/* 状态 */  
	if(sss.data[0].status==true){
	$('#Communication_status').attr('src','./battery/img/green.png');
	document.getElementById('status').value = '1';
 	}else if(sss.data[0].status==false){
 	$('#Communication_status').attr('src','./battery/img/red.png');
 	document.getElementById('status').value = '0';
 	}
 	else{
 	$('#Communication_status').attr('src','./battery/img/gray.png');
 	document.getElementById('status').value = '0';
 	}
 	
 	if(sss.data[0].MAIN_STANDSTILL=="true"){$('#MAIN_STANDSTILL').attr('src','./battery/img/green.png');
 	}else if(sss.data[0].MAIN_STANDSTILL=="false"){$('#MAIN_STANDSTILL').attr('src','./battery/img/red.png');}
 	else{$('#MAIN_STANDSTILL').attr('src','./battery/img/gray.png');}
 	
 	if(sss.data[0].VISU_DI_CONTROL_STOP_SIGNAL=="true"){$('#MAIN_STOP').attr('src','./battery/img/red.png');
 	}else if(sss.data[0].VISU_DI_CONTROL_STOP_SIGNAL=="false"){$('#MAIN_STOP').attr('src','./battery/img/green.png');}
 	else{$('#MAIN_STOP').attr('src','./battery/img/gray.png');}
 	
 	if(sss.data[0].control_signal_external_stop_demand=="true"){$('#External_stop_demand').attr('src','./battery/img/red.png');
 	}else if(sss.data[0].control_signal_external_stop_demand=="false"){$('#External_stop_demand').attr('src','./battery/img/green.png');}
 	else{$('#External_stop_demand').attr('src','./battery/img/gray.png');}
 	
 	if(sss.data[0].VISU_DI_CONTROL_START_SIGNAL=="true"){$('#MAIN_STARTING').attr('src','./battery/img/red.png');
 	}else if(sss.data[0].VISU_DI_CONTROL_START_SIGNAL=="false"){$('#MAIN_STARTING').attr('src','./battery/img/green.png');}
 	else{$('#MAIN_STARTING').attr('src','./battery/img/gray.png');}
 	
 	if(sss.data[0].ERROR_ERROR_GLOBAL=="true"){$('#ERROR_ERROR_GLOBAL').attr('src','./battery/img/red.png');
 	}else if(sss.data[0].ERROR_ERROR_GLOBAL=="false"){$('#ERROR_ERROR_GLOBAL').attr('src','./battery/img/green.png');}
 	else{$('#ERROR_ERROR_GLOBAL').attr('src','./battery/img/gray.png');}
 	
 	if(sss.data[0].SAFETY_SYSTEM_ERROR_ERROR_SAFETY_SYSTEM_PITCH_SYSTEM_EM_STOP_FROM_PITCH=="true"){$('#SAFETY_ERROR_FROM_PITCH').attr('src','./battery/img/red.png');
	}else if(sss.data[0].SAFETY_SYSTEM_ERROR_ERROR_SAFETY_SYSTEM_PITCH_SYSTEM_EM_STOP_FROM_PITCH=="false"){$('#SAFETY_ERROR_FROM_PITCH').attr('src','./battery/img/green.png');}
 	else{$('#SAFETY_ERROR_FROM_PITCH').attr('src','./battery/img/gray.png');}
	document.getElementById('AVERAGE_WIND_SPEED_60S').value=Math.round(sss.data[0].AVERAGE_WIND_SPEED_60S * 100) / 100	+' m/s';
	
 	
 	
 	
/* 控制 */
 	document.getElementById('CONTROL_STOP').value = sss.data[0].VISU_DI_CONTROL_STOP_SIGNAL;
 	document.getElementById('BLOCK_STARTING_MODE').value = sss.data[0].INIT_MAIN_LOOP_BLOCK_STARTING_MODE;
 	document.getElementById('B_ManualBatteryTest').value = sss.data[0].B_ManualBatteryTest;
 	document.getElementById('CONTROL_START').value = sss.data[0].VISU_DI_CONTROL_START_SIGNAL;
/* 模拟量 */
 	document.getElementById('PITCH_POSITION_1').value = sss.data[0].PITCH_POSITION_1+' °';
 	document.getElementById('PITCH_POSITION_2').value = sss.data[0].PITCH_POSITION_2+' °';
 	document.getElementById('PITCH_POSITION_3').value = sss.data[0].PITCH_POSITION_3+' °';
 	document.getElementById('PITCH_SPEED_1').value = sss.data[0].PITCH_SPEED_1+' °/s';
 	document.getElementById('PITCH_SPEED_2').value = sss.data[0].PITCH_SPEED_2+' °/s';
 	document.getElementById('PITCH_SPEED_3').value = sss.data[0].PITCH_SPEED_3+' °/s';
 	document.getElementById('CAPACITOR_VOLTAGE_1').value = sss.data[0].PITCH_ATECH_CAPACITOR_VOLTAGE_1+' V';
 	document.getElementById('CAPACITOR_VOLTAGE_2').value = sss.data[0].PITCH_ATECH_CAPACITOR_VOLTAGE_2+' V';
 	document.getElementById('CAPACITOR_VOLTAGE_3').value = sss.data[0].PITCH_ATECH_CAPACITOR_VOLTAGE_3+' V';
 	document.getElementById('CAPACITOR_TEMP_1').value = sss.data[0].PITCH_ATECH_CAPACITOR_TEMP_1+' ℃';
 	document.getElementById('CAPACITOR_TEMP_2').value = sss.data[0].PITCH_ATECH_CAPACITOR_TEMP_2+' ℃';
 	document.getElementById('CAPACITOR_TEMP_3').value = sss.data[0].PITCH_ATECH_CAPACITOR_TEMP_3+' ℃';
 	 /* 电容压降 */
 	/* document.getElementById('Pressure_drop_1').value = sss.data[0].+'V';
 	document.getElementById('Pressure_drop_2').value = sss.data[0].+'V';
 	document.getElementById('Pressure_drop_3').value = sss.data[0].+'V'; */
 	/* 判断电容健康状态 */
 	/* if(Pressure_drop_1>=15){
 		$('#Pressure_drop_1').attr('value','异常')
 	}else{
 		$('#Pressure_drop_1').attr('value','健康')
 	}
 	if(Pressure_drop_1>=15){
 		$('#Pressure_drop_2').attr('value','异常')
 	}else{
 		$('#Pressure_drop_2').attr('value','健康')
 	}
 	if(Pressure_drop_1>=15){
 		$('#Pressure_drop_3').attr('value','异常')
 	}else{
 		$('#Pressure_drop_3').attr('value','健康')
 	}  */
   }
	
	 function doaction(obj){
	 var  fanname=$("#deviceName").val();
	 var  status=$("#status").val();
	 var block_starting_input=$("#BLOCK_STARTING_INPUT").val();
				if(fanname=="请选择"){
					alert("请先选择需要操作的机位号");
				}else if(status!="1"){
					alert("该风机暂未通讯");
				}
				else{
					$.ajax({
						url : "/capacitor/count/checkpw.action?name="+fanname+"&type="+obj+"&status="+status,
						type : 'POST',
						success : function(data) {
						}
					});
					 if(obj=="1"){
						alert("停机信号已发出");
					}else if(obj=="2"){
						alert("禁止风机启动，开始记录数据");
					}else if(obj=="3"){
						alert("自检信号已发出");
						setTimeout(changestatus2,20000)
					}
					else if(obj=="4"){
						alert("取消禁止启动，停止记录数据");
					}
					else if(obj=="5"){
						alert("启动信号已发出");
					}
				}
	 }
	function changestatus2(){ 
	 $.ajax({
			url : "/capacitor/count/changestatus2.action",
			type : 'POST',
			success : function(data) {
			}
		});
	} 
</script>	
</html>
