function playSound()
{
  var domain = window.location.host+'/fancon/common/sound/stop.wav';
  var borswer = window.navigator.userAgent.toLowerCase();
  if ( borswer.indexOf( "ie" ) >= 0 )
  {
    //IE内核浏览器
    var strEmbed = '<embed name="embedPlay" src="./fancon/common/sound/stop.wav" autostart="true" hidden="true" loop="false"></embed>';
    if ( $( "body" ).find( "embed" ).length <= 0 )
      $( "body" ).append( strEmbed );
    var embed = document.embedPlay;

    //浏览器不支持 audion，则使用 embed 播放
    embed.volume = 100;
    //embed.play();这个不需要
  } else
  {
    //非IE内核浏览器
    var strAudio ='<audio id="audioPlay" src="/fancon/common/sound/stop.wav" >';
    if ( $( "body" ).find( "audio" ).length <= 0 )
      $( "body" ).append( strAudio );
    var audio = document.getElementById( "audioPlay" );
    //浏览器支持 audion
    audio.play();
  }
}
var iv3=setInterval("ShowAlertDiv()",TIME);
function ShowAlertDiv() {
	$.ajax({
		url : "/fancon/cole/checknotice.action",
		type : 'POST',
		success : function(data) {
			if(data!="0"&&data!=null){
				console.log(data);
				Showmsg(data);
				playSound();	
			}
		}
	});
	
	
	
}
function Showmsg(obj) {
	art.dialog({
	    id: 'msg',
	    title: '警告',
	    content: obj,
	    width: 220, 
	    height: 80,
	    left: '100%',
	    top: '100%',
	    fixed: true,
	    drag: false,
	    resize: false,
	})
}
var flag2=0;
var flag=0;
var heartflag2 = false;
	function exportxml(){    //导入功能将websocket全部关闭
		if(1==flag2){
			flag2=0;
			closeWebSocket2();
		}
		if( 1==flag){
			flag=0;
			closeWebSocket();
		}
  
		var wait = document.getElementById('wait');
		 wait.style.display = "block";
		 $.ajax({
				url :"/fancon/export/export2.action",
				type : 'POST',
				success : function(data) {
					alert("操作成功！");
					var wait = document.getElementById('wait');
					 wait.style.display = "none";
					 location.reload();
				}
			})
	}


	
	
			var websocket2 = null;
		    //判断当前浏览器是否支持WebSocket
		    if ('WebSocket' in window) {
		        var domain = window.location.host;
		        flag2=1;
		        websocket2 = new WebSocket("ws://"+domain+"/fancon/websocket2");
		    }
		    else {
		        alert('当前浏览器 Not support websocket')
		    }

		    //连接发生错误的回调方法
		    websocket2.onerror = function () {
		    	 heartflag2 = false;
		    	flag2=0;
		    	setMessageInconsole("WebSocket连接发生错误");
		    };

		    //连接成功建立的回调方法
		    websocket2.onopen = function () {
		    	heartflag2 = true;
		    	setMessageInconsole("WebSocket连接成功");
		    }

		    //接收到消息的回调方法
		    websocket2.onmessage = function (event) {
		    	setMessageInnerHTML2(event.data);
		    }

		    //连接关闭的回调方法
		    websocket2.onclose = function () {
		    	flag2=0;
		    	setMessageInconsole("WebSocket2连接关闭");
		    	
		    }

		    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
		    window.onbeforeunload = function () {
		        closeWebSocket2();
		    }

		    
		    function setMessageInconsole(innerHTML) {
		    	console.log(innerHTML);
		    }

		    //关闭WebSocket连接
		    function closeWebSocket2() {
		    	flag2=0;
		        websocket2.close();
		    }

		    $(function(){ 
		    	$.ajax({
				url : "/fancon/check.action",
				type : 'POST',
				success : function(data) {
					if(data==1){
							$.ajax({
								url : "/fancon/show.action?code=common_top",
								type : 'POST',
								success : function(data) {
									$("#top").html(data);
								}
							});
							$.ajax({
								url : "/fancon/show.action?code=show_menu",
								type : 'POST',
								success : function(data) {
									$("#menu").html(data);
								}
							});
							$.ajax({
								url : "/fancon/show.action?code=wait",
								type : 'POST',
								success : function(data) {
									$("#tcc").html(data);
								}
							});
							$.ajax({
								url : "/fancon/show.action?code=common_foot",
								type : 'POST',
								success : function(data) {
									$("#foot").html(data);
								}
							});
							$.ajax({
								url : "/fancon/show.action?code=show_topmenu",
								type : 'POST',
								success : function(data) {
									$("#topmenu").html(data);
								}
							});
							$.ajax({
								url : "/fancon/show.action?code=header",
								type : 'POST',
								success : function(data) {
									$("#header").html(data);
								}
							});
							
							
						}
					else{
						 var a= storage.getItem("cc");
						 window.location.href="/fancon/login.action";
					}
				}
			});
		})
		var iv2=setInterval("time2()",TIME);

		function time2(){ 
			if(flag2=1){
				 if (heartflag2){
				websocket2.send("common");
				 }
			}
			else{
				clearInterval(iv2);
			}
		}
		
		//将消息显示在网页上
	   function setMessageInnerHTML2(innerHTML) {
		   
	   	var sss=JSON.parse(innerHTML);
	   	console.log(sss);
	   	var htmenu="";
	    for (var a=0;a<sss.data.length;a++){
	    	var zt="";
	    	if(sss.data[a].status=="未通讯")	{
	    		zt="status_weitongxun";
	    	}else if(sss.data[a].status=="并网")	{
	    		zt="status_bingwang";
	    	}else if(sss.data[a].status=="待机")	{
	    		zt="status_daiji";
	    	}else if(sss.data[a].status=="维护")	{
	    		zt="status_weihu";
	    	}else if(sss.data[a].status=="故障")	{
	    		zt="status_guzhang";
	    	}else if(sss.data[a].status=="运行")	{
	    		zt="status_yunxing";
	    	}else if(sss.data[a].status=="停机")	{
	    		zt="status_tingji";
	    	}else if(sss.data[a].status=="启动")	{
	    		zt="status_qidong";
	    	}
	    	htmenu=htmenu+"<a href='/fancon/show.action?code=main&id="+sss.data[a].deviceId+"'  class='"+zt+"'>"+sss.data[a].deviceName+"</a></br>"
	    	
	      	 		
	   	}$("#memenu").html(htmenu);
		document.getElementById('weitongxun').value = sss.data2.weitongxun;
	    document.getElementById('bingwang').value = sss.data2.bingwang;
	    document.getElementById('weihu').value = sss.data2.weihu;
	    document.getElementById('guzhang').value = sss.data2.guzhang;
	    document.getElementById('qidong').value = sss.data2.qidong;
	    document.getElementById('daiji').value = sss.data2.daiji;
	    document.getElementById('tingji').value = sss.data2.tingji;
	    document.getElementById('yunxing').value =sss.data2.yunxing;
	    
	    
	    document.getElementById('totalGen').value = tf(sss.data2.totalGen);
	    document.getElementById('windspeed').value = tf(sss.data2.windspeed);
	    document.getElementById('fullRate').value = tf(sss.data2.fullRate);
	    document.getElementById('totalPower').value = sss.data2.totalPower;
	    document.getElementById('yearPower').value = sss.data2.yearPower;
	    document.getElementById('monthPower').value =sss.data2.monthPower;
	    document.getElementById('dayPower').value = sss.data2.dayPower;
	    
//	    var code=$("#code").val();
//	    console.log(code);
	  }
	   //小数点控制
	   function tf(num){
			var result =parseFloat(num);
			if(isNaN(result)){
			return ;
			}else{
			result=Math.round(num*100)/100;
			var sx=result.toString();
			var pos=sx.indexOf(".");
			if(pos<0){
				pos=sx.length;
				sx+='.';}
				
				while(sx.length<=pos+2){
				sx+='0';
				}
			return sx;
			}
			
			
			num=num.toFixed(2);
			return num;
			}
