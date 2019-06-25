
function time(){ 
	var type=$("#type").val();
	var nodename=$("#nodename").val();
	var sendtype=type+"~"+nodename;
	websocket.send(sendtype);
	}setInterval("time()",2000);
//将消息显示在网页上
//  function aa(){
//		var type=$("#type").val();
//		var nodename=$("#nodename").val();
//		var sendtype=type+"~"+nodename;
//websocket.send(sendtype);
//}
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

//将消息显示在网页上
   function setMessageInnerHTML(innerHTML) {
	   
   	var sss=JSON.parse(innerHTML);
   	console.log(sss);
   	var ht="";
   	var htmenu="";
   	var type=$("#type").val();
//基本信息界面
   	if(type==1){
   		document.getElementById('GRID_POWER').value = tf(sss.data.GRID_POWER);
   		document.getElementById('jindutiao_value').style.width=(tf(sss.data.GRID_POWER)/15)+"%";
		document.getElementById('WIND_SPEED').value = tf(sss.data.WIND_SPEED);
   		document.getElementById('CONVERTER_MOTOR_SPEED').value = tf(sss.data.CONVERTER_MOTOR_SPEED);
   		document.getElementById('ROTOR_SPEED').value = tf(sss.data.ROTOR_SPEED);
   		document.getElementById('YAW_POSITION').value = tf(sss.data.YAW_POSITION);
   		document.getElementById('GRID_UL1').value = tf(sss.data.GRID_UL1);
   			document.getElementById('GRID_UL11').style.width=(tf(sss.data.GRID_UL1)/5)+"%";
   		document.getElementById('GRID_I1').value = tf(sss.data.GRID_I1);
   			document.getElementById('GRID_I11').style.width=(tf(sss.data.GRID_I1)/16)+"%";
   		document.getElementById('GRID_UL2').value = tf(sss.data.GRID_UL2);
   			document.getElementById('GRID_UL21').style.width=(tf(sss.data.GRID_UL2)/5)+"%";
   		document.getElementById('GRID_I2').value = tf(sss.data.GRID_I2);
   			document.getElementById('GRID_I21').style.width=(tf(sss.data.GRID_I2)/16)+"%";
   		document.getElementById('GRID_UL3').value = tf(sss.data.GRID_UL3);
   			document.getElementById('GRID_UL31').style.width=(tf(sss.data.GRID_UL3)/5)+"%";
   		document.getElementById('GRID_I3').value = tf(sss.data.GRID_I3);
   			document.getElementById('GRID_I31').style.width=(tf(sss.data.GRID_I3)/16)+"%";
   		document.getElementById('GRID_COSPI').value = tf(sss.data.GRID_COSPI);
   		document.getElementById('GRID_FREQUENCY').value = tf(sss.data.GRID_FREQUENCY);
   		document.getElementById('GEARBOX_TEMPERATURE_INPUT_SHAFT1').value = tf(sss.data.GEARBOX_TEMPERATURE_INPUT_SHAFT1);
   		document.getElementById('NACELLE_TEMPERATURE').value = tf(sss.data.NACELLE_TEMPERATURE);
   		document.getElementById('GEARBOX_TEMPERATURE_INPUT_SHAFT2').value = tf(sss.data.GEARBOX_TEMPERATURE_INPUT_SHAFT2);
   		document.getElementById('OPERATION_DATA_ENERGY_YIELD').value = tf(sss.data.OPERATION_DATA_ENERGY_YIELD);
   		document.getElementById('GEARBOX_OIL_TEMPERATURE_GEARBOX').value = tf(sss.data.GEARBOX_OIL_TEMPERATURE_GEARBOX);
   		document.getElementById('GENERATOR_COOLING_AIR_TEMPERATURE').value = tf(sss.data.GENERATOR_COOLING_AIR_TEMPERATURE);
   		document.getElementById('GENERATOR_BEARING_TEMPERATURE_A').value = tf(sss.data.GENERATOR_BEARING_TEMPERATURE_A);
   		document.getElementById('NACELLE_OUTDOOR_TEMPERATURE').value = tf(sss.data.NACELLE_OUTDOOR_TEMPERATURE);
   		document.getElementById('GENERATOR_BEARING_TEMPERATURE_B').value = tf(sss.data.GENERATOR_BEARING_TEMPERATURE_B);
   		document.getElementById('PITCH_POSITION_1').value = tf(sss.data.PITCH_POSITION_1);
   		document.getElementById('PITCH_POSITION_2').value = tf(sss.data.PITCH_POSITION_2);
   		document.getElementById('PITCH_POSITION_3').value = tf(sss.data.PITCH_POSITION_3);
   	//基本信息页面的叶片表盘
   		var myChart1 = echarts.init(document.getElementById('yepian'));
		option1 = {
				/* backgroundColor: 'rgba(0, 0, 0, 0.5)', */
                tooltip : {
                    formatter: "{a} <br/>{c} {b}"
                },
                toolbox: {
                    show : false,
                    feature : {
                        mark : {show: true},
                        restore : {show: true},
                        saveAsImage : {show: true}
                    }
                },
                series : [
                    {
                        name:'叶片1角度',
                        type:'gauge',
                        center : ['80', '80'],
                        min:0,
                        max:90,
                        endAngle:45,
                        splitNumber:3,
                        radius: 60,
                        pointer:{               //指针样式
                            width:3,
                            length:'100%'
                             },
                        axisLine: {            // 坐标轴线
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: [[0.33, 'red'],[0.66, '#1e90ff'],[1, 'lime']],
                                width: 8,
                            }
                        },
                        axisLabel: {            // 坐标轴小标记数字文本
                            textStyle: {       // 属性lineStyle控制线条样式
                                fontSize:10,
                                color: '#fff',
                            }
                        },
                        axisTick: {            // 坐标轴小标记
                            length :12,        // 属性length控制线长
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: 'auto',
                            }
                        },
                        splitLine: {           // 分隔线
                            length :15,         // 属性length控制线长
                            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                width:2,
                                length :5,
                                color: 'auto',
                            }
                        },
                        title : {
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder',
                                fontSize: 35,
                                fontStyle: 'italic',
                                color: '#fff',
                            }
                        },
                        detail : {//仪表盘详情
                            offsetCenter: [0, '100%'],       // x, y，单位px
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder',
                                fontSize: 14,
                                color: 'white'
                            },
                            formatter:'叶片1：{value}°',
                            width:20,
                        },
                        data:[{value: 0, }]
                    },
                    {
                        name:'叶片2角度',
                        type:'gauge',
                        center : ['200', '80'],
                        min:0,
                        max:90,
                        endAngle:45,
                        splitNumber:3,
                        radius: 60,
                        pointer:{               //指针样式
                            width:3,
                            length:'100%'
                             },
                        axisLine: {            // 坐标轴线
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: [[0.33, 'red'],[0.66, '#1e90ff'],[1, 'lime']],
                                width: 8,
                            }
                        },
                        axisLabel: {            // 坐标轴小标记数字文本
                            textStyle: {       // 属性lineStyle控制线条样式
                                fontSize:10,
                                color: '#fff',
                            }
                        },
                        axisTick: {            // 坐标轴小标记
                            length :12,        // 属性length控制线长
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: 'auto',
                            }
                        },
                        splitLine: {           // 分隔线
                            length :15,         // 属性length控制线长
                            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                width:2,
                                color: 'auto',
                            }
                        },
                        title : {
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder',
                                fontSize: 35,
                                fontStyle: 'italic',
                                color: '#fff',
                            }
                        },
                        detail : {//仪表盘详情
                            offsetCenter: [0, '100%'],       // x, y，单位px
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder',
                                fontSize: 14,
                                color: 'white'
                            },
                            formatter:'叶片2：{value}°',
                            width:20,
                        },
                        data:[{value: 0, }]
                    },
                    {
                        name:'叶片3角度',
                        type:'gauge',
                        center : ['320', '80'],
                        min:0,
                        max:90,
                        endAngle:45,
                        splitNumber:3,
                        radius: 60,
                        pointer:{               //指针样式
                            width:3,
                            length:'100%'
                             },
                        axisLine: {            // 坐标轴线
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: [[0.33, 'red'],[0.66, '#1e90ff'],[1, 'lime']],
                                width: 8,
                            }
                        },
                        axisLabel: {            // 坐标轴小标记数字文本
                            textStyle: {       // 属性lineStyle控制线条样式
                                fontSize:10,
                                color: '#fff',
                            }
                        },
                        axisTick: {            // 坐标轴小标记
                            length :12,        // 属性length控制线长
                            lineStyle: {       // 属性lineStyle控制线条样式
                                color: 'auto',
                            }
                        },
                        splitLine: {           // 分隔线
                            length :15,         // 属性length控制线长
                            lineStyle: {       // 属性lineStyle（详见lineStyle）控制线条样式
                                width:2,
                                color: 'auto',
                            }
                        },
                        title : {
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder',
                                fontSize: 35,
                                fontStyle: 'italic',
                                color: '#fff',
                            }
                        },
                        detail : {//仪表盘详情
                            offsetCenter: [0, '100%'],       // x, y，单位px
                            textStyle: {       // 其余属性默认使用全局文本样式，详见TEXTSTYLE
                                fontWeight: 'bolder',
                                fontSize: 14,
                                color: 'white'
                            },
                            formatter:'叶片3：{value}°',
                            width:20,
                        },
                        data:[{value: 0, }]
                    }
                ]
            };
			 option1.series[0].data[0].value =sss.data.PITCH_POSITION_1;
			 option1.series[1].data[0].value =sss.data.PITCH_POSITION_2;
			 option1.series[2].data[0].value =sss.data.PITCH_POSITION_3;
			 myChart1.setOption(option1);
   	}
   	/*功率圆环*/
   	var myChart2 = echarts.init(document.getElementById('power1'));
   	var labelTop = {
   		    normal : {
   		        label : {
   		            show : true,
   		            position : 'center',
   		            formatter : '{b}',
   		            textStyle: {
   		                baseline : 'bottom'
   		            }
   		        },
   		        labelLine : {
   		            show : true
   		        }
   		    }
   		};
   		var labelFromatter = {
   		    normal : {
   		        label : {
   		        	show:true,
   		            formatter : function (params){
   		                return params.value*16+'kw'
   		            },
   		            textStyle: {
   		                baseline : 'top',
   		                color:'#00ff00',
   		                fontSize:16,
   		                fontWeight:'bold'
   		            }
   		        }
   		    },
   		}
   		var labelBottom = {
   		    normal : {
   		        color: '#ccc',
   		        label : {
   		            show : false,
   		            position : 'center'
   		        }
   		    }
   		};
   		var radius = [40, 55];
   		option2 = {
   				series : [
   		        {
   		            type : 'pie',
   		            center : ['50%', '30%'],
   		            radius : radius,
   		            x: '0%', // for funnel
   		            itemStyle : labelFromatter,
   		            data : [
   		                 {name:'other', value:100-tf(sss.data.GRID_POWER)/16, itemStyle : labelBottom},
   		                {name:'功率', value:tf(sss.data.GRID_POWER)/16,itemStyle : labelTop}
   		            ]
   		        },
   		       
   		    ]
   		};
   	myChart2.setOption(option2);
   	
   	
//发电机界面
   	if(type==2){
   		if(sss.data.PROFI_DI_GENERATOR_HEATER_PROTECTION_OK=="false"){
   			$("#PROFI_DI_GENERATOR_HEATER_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GENERATOR_HEATER_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_GENERATOR_HEATER_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GENERATOR_HEATER=="false"){
   			$("#PROFI_DO_GENERATOR_HEATER").attr("src","common/images/status/green.png");
   	   		}else if(sss.data.PROFI_DO_GENERATOR_HEATER=="true"){
   			$("#PROFI_DO_GENERATOR_HEATER").attr("src","common/images/status/red.png");
   	   		}
   		if(sss.data.PROFI_DI_GENERATOR_BRUSH_OK=="false"){
   			$("#PROFI_DI_GENERATOR_BRUSH_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GENERATOR_BRUSH_OK=="true"){
	   			$("#PROFI_DI_GENERATOR_BRUSH_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GENERATOR_COOLING_AIR_FAN1=="false"){
   			$("#PROFI_DO_GENERATOR_COOLING_AIR_FAN1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_GENERATOR_COOLING_AIR_FAN1=="true"){
	   			$("#PROFI_DO_GENERATOR_COOLING_AIR_FAN1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GENERATOR_COOLING_AIR_FAN2=="false"){
   			$("#PROFI_DO_GENERATOR_COOLING_AIR_FAN2").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_GENERATOR_COOLING_AIR_FAN2=="true"){
	   			$("#PROFI_DO_GENERATOR_COOLING_AIR_FAN2").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GENERATOR_COOLING_AIR_FAN1_PROTECTION_OK=="false"){
   			$("#PROFI_DI_GENERATOR_COOLING_AIR_FAN1_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GENERATOR_COOLING_AIR_FAN1_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_GENERATOR_COOLING_AIR_FAN1_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GENERATOR_COOLING_AIR_FAN2_PROTECTION_OK=="false"){
   			$("#PROFI_DI_GENERATOR_COOLING_AIR_FAN2_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GENERATOR_COOLING_AIR_FAN2_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_GENERATOR_COOLING_AIR_FAN2_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		document.getElementById('GENERATOR_WINDING_TEMPERATURE_U1').value = tf(sss.data.GENERATOR_WINDING_TEMPERATURE_U1);
   		document.getElementById('GENERATOR_WINDING_TEMPERATURE_U2').value = tf(sss.data.GENERATOR_WINDING_TEMPERATURE_U2);
   		document.getElementById('GENERATOR_WINDING_TEMPERATURE_V1').value = tf(sss.data.GENERATOR_WINDING_TEMPERATURE_V1);
   		document.getElementById('GENERATOR_WINDING_TEMPERATURE_V2').value = tf(sss.data.GENERATOR_WINDING_TEMPERATURE_V2);
   		document.getElementById('GENERATOR_WINDING_TEMPERATURE_W1').value = tf(sss.data.GENERATOR_WINDING_TEMPERATURE_W1);
   		document.getElementById('GENERATOR_WINDING_TEMPERATURE_W2').value = tf(sss.data.GENERATOR_WINDING_TEMPERATURE_W2); 
   		document.getElementById('GENERATOR_BEARING_TEMPERATURE_A2').value = tf(sss.data.GENERATOR_BEARING_TEMPERATURE_A);
   		document.getElementById('GENERATOR_BEARING_TEMPERATURE_B2').value = tf(sss.data.GENERATOR_BEARING_TEMPERATURE_B);
   		document.getElementById('GENERATOR_COOLING_AIR_TEMPERATURE2').value = tf(sss.data.GENERATOR_COOLING_AIR_TEMPERATURE); 
   		document.getElementById('GENERATOR_SLIP_RING_TEMPERATURE').value = tf(sss.data.GENERATOR_SLIP_RING_TEMPERATURE); 
   	}
//齿轮箱界面
   	if(type==3){
   		if(sss.data.PROFI_DI_GEARBOX_OIL_LEVEL_OK=="false"){
   			$("#PROFI_DI_GEARBOX_OIL_LEVEL_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_OIL_LEVEL_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_OIL_LEVEL_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_HEATER_PROTECTION_OK=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_HEATER_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_HEATER_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_HEATER_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_FILTER_PRESSURE_OK=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_FILTER_PRESSURE_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_FILTER_PRESSURE_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_FILTER_PRESSURE_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_HOT=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_HOT").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_HOT=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_HOT").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_STANDSTILL_HEATER_PROTECTION_OK=="false"){
   			$("#PROFI_DI_GEARBOX_STANDSTILL_HEATER_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_STANDSTILL_HEATER_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_STANDSTILL_HEATER_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GEARBOX_LUBRICATION_OIL_HEATER=="false"){
   			$("#PROFI_DO_GEARBOX_LUBRICATION_OIL_HEATER").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_GEARBOX_LUBRICATION_OIL_HEATER=="true"){
	   			$("#PROFI_DO_GEARBOX_LUBRICATION_OIL_HEATER").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_A=="false"){
   			$("#PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_A").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_A=="true"){
	   			$("#PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_A").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_B=="false"){
   			$("#PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_B").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_B=="true"){
	   			$("#PROFI_DO_GEARBOX_LUBRICATION_OIL_PUMP_HIGH_B").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_GEARBOX_OIL_PUMP_HEATER=="false"){
   			$("#PROFI_DO_GEARBOX_OIL_PUMP_HEATER").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_GEARBOX_OIL_PUMP_HEATER=="true"){
	   			$("#PROFI_DO_GEARBOX_OIL_PUMP_HEATER").attr("src","common/images/status/red.png");
	   	   	}
   		document.getElementById('GEARBOX_LUBRICATION_FILTER_INLET_PRESSURE').value = tf(sss.data.GEARBOX_LUBRICATION_FILTER_INLET_PRESSURE); 
   		document.getElementById('GEARBOX_LUBRICATION_FILTER_OUTLET_PRESSURE').value = tf(sss.data.GEARBOX_LUBRICATION_FILTER_OUTLET_PRESSURE); 
   		document.getElementById('GEARBOX_OIL_TEMPERATURE_GEARBOX2').value = tf(sss.data.GEARBOX_OIL_TEMPERATURE_GEARBOX); 
   		document.getElementById('GEARBOX_OIL_TEMPERATURE_OIL_INLET').value = tf(sss.data.GEARBOX_OIL_TEMPERATURE_OIL_INLET); 
   		document.getElementById('GEARBOX_TEMPERATURE_INPUT_SHAFT11').value = tf(sss.data.GEARBOX_TEMPERATURE_INPUT_SHAFT1); 
   		document.getElementById('GEARBOX_TEMPERATURE_OUTPUT_SHAFT22').value = tf(sss.data.GEARBOX_TEMPERATURE_OUTPUT_SHAFT2); 
   	}
//变桨界面
   	if(type==4){
   		if(sss.data.PITCH_STATUS_LIMIT_SWITCH91_OK_1=="false"){
   			$("#PITCH_STATUS_LIMIT_SWITCH91_OK_1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PITCH_STATUS_LIMIT_SWITCH91_OK_1=="true"){
	   			$("#PITCH_STATUS_LIMIT_SWITCH91_OK_1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PITCH_STATUS_LIMIT_SWITCH91_OK_2=="false"){
   			$("#PITCH_STATUS_LIMIT_SWITCH91_OK_2").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PITCH_STATUS_LIMIT_SWITCH91_OK_2=="true"){
	   			$("#PITCH_STATUS_LIMIT_SWITCH91_OK_2").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PITCH_STATUS_LIMIT_SWITCH91_OK_3=="false"){
   			$("#PITCH_STATUS_LIMIT_SWITCH91_OK_3").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PITCH_STATUS_LIMIT_SWITCH91_OK_3=="true"){
	   			$("#PITCH_STATUS_LIMIT_SWITCH91_OK_3").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PITCH_STATUS_LIMIT_SWITCH96_OK_1=="false"){
   			$("#PITCH_STATUS_LIMIT_SWITCH96_OK_1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PITCH_STATUS_LIMIT_SWITCH96_OK_1=="true"){
	   			$("#PITCH_STATUS_LIMIT_SWITCH96_OK_1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PITCH_STATUS_LIMIT_SWITCH96_OK_2=="false"){
   			$("#PITCH_STATUS_LIMIT_SWITCH96_OK_2").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PITCH_STATUS_LIMIT_SWITCH96_OK_2=="true"){
	   			$("#PITCH_STATUS_LIMIT_SWITCH96_OK_2").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PITCH_STATUS_LIMIT_SWITCH96_OK_3=="false"){
   			$("#PITCH_STATUS_LIMIT_SWITCH96_OK_3").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PITCH_STATUS_LIMIT_SWITCH96_OK_3=="true"){
	   			$("#PITCH_STATUS_LIMIT_SWITCH96_OK_3").attr("src","common/images/status/red.png");
	   	   	}
   		document.getElementById('PITCH_POSITION_12').value = tf(sss.data.PITCH_POSITION_1);
   		document.getElementById('PITCH_POSITION_22').value = tf(sss.data.PITCH_POSITION_2);
   		document.getElementById('PITCH_POSITION_32').value = tf(sss.data.PITCH_POSITION_3);
   		document.getElementById('PITCH_MCCV_POSITION_REDUNDANT_1').value=tf(sss.data.PITCH_MCCV_POSITION_REDUNDANT_1);
   	   	document.getElementById('PITCH_MCCV_POSITION_REDUNDANT_2').value=tf(sss.data.PITCH_MCCV_POSITION_REDUNDANT_2);
   	   	document.getElementById('PITCH_MCCV_POSITION_REDUNDANT_3').value=tf(sss.data.PITCH_MCCV_POSITION_REDUNDANT_3);
   	   	document.getElementById('PITCH_MCCV_SPEED_1').value=tf(sss.data.PITCH_MCCV_SPEED_1);
   	   	document.getElementById('PITCH_MCCV_SPEED_2').value=tf(sss.data.PITCH_MCCV_SPEED_2);
   	   	document.getElementById('PITCH_MCCV_SPEED_3').value=tf(sss.data.PITCH_MCCV_SPEED_3);
   	   	document.getElementById('PITCH_MCCV_DRIVE_CURRENT_1').value=tf(sss.data.PITCH_MCCV_DRIVE_CURRENT_1);
   	   	document.getElementById('PITCH_MCCV_DRIVE_CURRENT_2').value=tf(sss.data.PITCH_MCCV_DRIVE_CURRENT_2);
   	   	document.getElementById('PITCH_MCCV_DRIVE_CURRENT_3').value=tf(sss.data.PITCH_MCCV_DRIVE_CURRENT_3);   	
   	   	/*document.getElementById('PITCH_MCCV_BATTERY_VOLTAGE_1').value = tf(sss.data.PITCH_MCCV_BATTERY_VOLTAGE_1);
   	   	document.getElementById('PITCH_MCCV_BATTERY_VOLTAGE_2').value = tf(sss.data.PITCH_MCCV_BATTERY_VOLTAGE_2);
   	   	document.getElementById('PITCH_MCCV_BATTERY_VOLTAGE_3').value = tf(sss.data.PITCH_MCCV_BATTERY_VOLTAGE_3);*/
   	   	document.getElementById('PITCH_MCCV_MOTOR_TEMPERATURE_1').value = tf(sss.data.PITCH_MCCV_MOTOR_TEMPERATURE_1);
   	   	document.getElementById('PITCH_MCCV_MOTOR_TEMPERATURE_2').value = tf(sss.data.PITCH_MCCV_MOTOR_TEMPERATURE_2);
   	   	document.getElementById('PITCH_MCCV_MOTOR_TEMPERATURE_3').value = tf(sss.data.PITCH_MCCV_MOTOR_TEMPERATURE_3);
   	   	document.getElementById('PITCH_MCCV_BATTBOX_TEMPERATURE_1').value = tf(sss.data.PITCH_MCCV_BATTBOX_TEMPERATURE_1);
   	   	document.getElementById('PITCH_MCCV_BATTBOX_TEMPERATURE_2').value = tf(sss.data.PITCH_MCCV_BATTBOX_TEMPERATURE_2);
   	   	document.getElementById('PITCH_MCCV_BATTBOX_TEMPERATURE_3').value = tf(sss.data.PITCH_MCCV_BATTBOX_TEMPERATURE_3);
   	   	document.getElementById('PITCH_AXISBOX_TEMPERATURE_1').value = tf(sss.data.PITCH_AXISBOX_TEMPERATURE_1);
   	   	document.getElementById('PITCH_AXISBOX_TEMPERATURE_2').value = tf(sss.data.PITCH_AXISBOX_TEMPERATURE_2);
   	   	document.getElementById('PITCH_AXISBOX_TEMPERATURE_3').value = tf(sss.data.PITCH_AXISBOX_TEMPERATURE_3);
   	}
//偏航界面
   	if(type==5){
   		if(sss.data.PROFI_DI_YAW_STOP_POSITION_LEFT=="false"){
   			$("#PROFI_DI_YAW_STOP_POSITION_LEFT").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_STOP_POSITION_LEFT=="true"){
	   			$("#PROFI_DI_YAW_STOP_POSITION_LEFT").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_STOP_POSITION_RIGHT=="false"){
   			$("#PROFI_DI_YAW_STOP_POSITION_RIGHT").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_STOP_POSITION_RIGHT=="true"){
	   			$("#PROFI_DI_YAW_STOP_POSITION_RIGHT").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_YAW_LEFT=="false"){
   			$("#PROFI_DO_YAW_LEFT").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_YAW_LEFT=="true"){
	   			$("#PROFI_DO_YAW_LEFT").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_YAW_RIGHT=="false"){
   			$("#PROFI_DO_YAW_RIGHT").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_YAW_RIGHT=="true"){
	   			$("#PROFI_DO_YAW_RIGHT").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_SOFTSTARTER_OK=="false"){
   			$("#PROFI_DI_YAW_SOFTSTARTER_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_SOFTSTARTER_OK=="true"){
	   			$("#PROFI_DI_YAW_SOFTSTARTER_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_YAW_MOTOR_BRAKE_ELECTRICAL_OPEN=="false"){
   			$("#PROFI_DO_YAW_MOTOR_BRAKE_ELECTRICAL_OPEN").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_YAW_MOTOR_BRAKE_ELECTRICAL_OPEN=="true"){
	   			$("#PROFI_DO_YAW_MOTOR_BRAKE_ELECTRICAL_OPEN").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_YAW_SOFTSTART_A=="false"){
   			$("#PROFI_DO_YAW_SOFTSTART_A").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_YAW_SOFTSTART_A=="true"){
	   			$("#PROFI_DO_YAW_SOFTSTART_A").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_YAW_SOFTSTART_EN=="false"){
   			$("#PROFI_DO_YAW_SOFTSTART_EN").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_YAW_SOFTSTART_EN=="true"){
	   			$("#PROFI_DO_YAW_SOFTSTART_EN").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_SSI_YAW_POSITION_SENSOR_SETPOINT_ZERO=="false"){
   			$("#PROFI_DO_SSI_YAW_POSITION_SENSOR_SETPOINT_ZERO").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_SSI_YAW_POSITION_SENSOR_SETPOINT_ZERO=="true"){
	   			$("#PROFI_DO_SSI_YAW_POSITION_SENSOR_SETPOINT_ZERO").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_SSI_YAW_POSITION_SENSOR_DATAVALID_MT=="false"){
   			$("#PROFI_DI_SSI_YAW_POSITION_SENSOR_DATAVALID_MT").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_SSI_YAW_POSITION_SENSOR_DATAVALID_MT=="true"){
	   			$("#PROFI_DI_SSI_YAW_POSITION_SENSOR_DATAVALID_MT").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_LUBRICATION_PUMP_BEARING_PROTECTION_OK=="false"){
   			$("#PROFI_DI_YAW_LUBRICATION_PUMP_BEARING_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_LUBRICATION_PUMP_BEARING_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_YAW_LUBRICATION_PUMP_BEARING_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_LUBRICATION_BEARING_FILL_LEVEL_OK=="false"){
   			$("#PROFI_DI_YAW_LUBRICATION_BEARING_FILL_LEVEL_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_LUBRICATION_BEARING_FILL_LEVEL_OK=="true"){
	   			$("#PROFI_DI_YAW_LUBRICATION_BEARING_FILL_LEVEL_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_MOTOR1_PROTECTION_OK=="false"){
   			$("#PROFI_DI_YAW_MOTOR1_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_MOTOR1_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_YAW_MOTOR1_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_MOTOR2_PROTECTION_OK=="false"){
   			$("#PROFI_DI_YAW_MOTOR2_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_MOTOR2_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_YAW_MOTOR2_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_MOTOR3_PROTECTION_OK=="false"){
   			$("#PROFI_DI_YAW_MOTOR3_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_MOTOR3_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_YAW_MOTOR3_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_YAW_MOTOR4_PROTECTION_OK=="false"){
   			$("#PROFI_DI_YAW_MOTOR4_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_YAW_MOTOR4_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_YAW_MOTOR4_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		document.getElementById('YAW_DEVIATION_WIND_NACELLE_POSITION').value = tf(sss.data.YAW_DEVIATION_WIND_NACELLE_POSITION);
   		document.getElementById('YAW_YAWING_SPEED').value = tf(sss.data.YAW_YAWING_SPEED);
   		document.getElementById('YAW_POSITION2').value = tf(sss.data.YAW_POSITION);
   	}
//液压界面
   	if(type==6){
   		if(sss.data.PROFI_DO_HYDRAULIC_PUMP_MOTOR_ON=="false"){
   			$("#PROFI_DO_HYDRAULIC_PUMP_MOTOR_ON").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_HYDRAULIC_PUMP_MOTOR_ON=="true"){
	   			$("#PROFI_DO_HYDRAULIC_PUMP_MOTOR_ON").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_HYDRAULIC_OIL_HEATER=="false"){
   			$("#PROFI_DO_HYDRAULIC_OIL_HEATER").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_HYDRAULIC_OIL_HEATER=="true"){
	   			$("#PROFI_DO_HYDRAULIC_OIL_HEATER").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_OPEN_BRAKE=="false"){
   			$("#PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_OPEN_BRAKE").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_OPEN_BRAKE=="true"){
	   			$("#PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_OPEN_BRAKE").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_CLOSE_BRAKE=="false"){
   			$("#PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_CLOSE_BRAKE").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_CLOSE_BRAKE=="true"){
	   			$("#PROFI_DO_HYDRAULIC_ROTOR_BRAKE_VALVE_CLOSE_BRAKE").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_PARTLY=="false"){
   			$("#PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_PARTLY").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_PARTLY=="true"){
	   			$("#PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_PARTLY").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_TOTAL=="false"){
   			$("#PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_TOTAL").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_TOTAL=="true"){
	   			$("#PROFI_DO_HYDRAULIC_YAW_BRAKE_VALVE_OPEN_TOTAL").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX=="false"){
   			$("#PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX=="true"){
	   			$("#PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_OIL_LEVEL_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_OIL_LEVEL_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_OIL_LEVEL_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_OIL_LEVEL_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_PUMP_MOTOR_FEEDBACK=="false"){
   			$("#PROFI_DI_HYDRAULIC_PUMP_MOTOR_FEEDBACK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_PUMP_MOTOR_FEEDBACK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_PUMP_MOTOR_FEEDBACK").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_OIL_HEATER_PROTECTION_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_OIL_HEATER_PROTECTION_OK").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_OIL_HEATER_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_OIL_HEATER_PROTECTION_OK").attr("src","common/images/status/red.png");
	   	   	}
   		document.getElementById('HYDRAULIC_MAIN_SYS_PRESSURE').value = tf(sss.data.HYDRAULIC_MAIN_SYS_PRESSURE);
   		document.getElementById('HYDRAULIC_ROTOR_BRAKE_SYS_PRESSURE').value = tf(sss.data.HYDRAULIC_ROTOR_BRAKE_SYS_PRESSURE);
   	}
  //错误信息
   	if(type==7){
   		var errorbody="";
		
	    for (var a=0;a<sss.data.length;a++){
	    	var fjname=$("#fjname").val();
				if(sss.data[a].deviceName=fjname){
					errorbody=errorbody+"<tr><td>"+sss.data[a].ownname+"</td><td>"+sss.data[a].errorNo+"</td><td>"+sss.data[a].errorEnglishName+"</td><td>"+sss.data[a].errorName+"</td></tr>"
			      		
				}
	    	 		
	   	}$("#errbaby").html(errorbody);
   		console.log(errorbody);
   	}

   	
   	
//机舱信息
   	if(type==8){
   		if(sss.data.PROFI_DI_HYDRAULIC_OIL_LEVEL_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_OIL_LEVEL_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_OIL_LEVEL_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_OIL_LEVEL_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX=="false"){
   			$("#PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX=="true"){
	   			$("#PROFI_DI_HYDRAULIC_OIL_TEMPERATURE_LIMIT_MAX1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_ROTOR_BRAKE_PRESSURE_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK=="false"){
   			$("#PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_HYDRAULIC_PUMP_MOTOR_PROTECTION_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_OIL_LEVEL_OK=="false"){
   			$("#PROFI_DI_GEARBOX_OIL_LEVEL_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_OIL_LEVEL_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_OIL_LEVEL_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_OIL_PRESSURE_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK=="false"){
   			$("#PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_LUBRICATION_PUMP_MOTORPROT_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GEARBOX_COOLING_WATER_PRESSURE_OK=="false"){
   			$("#PROFI_DI_GEARBOX_COOLING_WATER_PRESSURE_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GEARBOX_COOLING_WATER_PRESSURE_OK=="true"){
	   			$("#PROFI_DI_GEARBOX_COOLING_WATER_PRESSURE_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		if(sss.data.PROFI_DI_GENERATOR_HEATER_PROTECTION_OK=="false"){
   			$("#PROFI_DI_GENERATOR_HEATER_PROTECTION_OK1").attr("src","common/images/status/green.png");
	   	   	}else if(sss.data.PROFI_DI_GENERATOR_HEATER_PROTECTION_OK=="true"){
	   			$("#PROFI_DI_GENERATOR_HEATER_PROTECTION_OK1").attr("src","common/images/status/red.png");
	   	   	}
   		document.getElementById('PITCH_POSITION_11').value = tf(sss.data.PITCH_POSITION_1);
   		document.getElementById('PITCH_POSITION_21').value = tf(sss.data.PITCH_POSITION_2);
   		document.getElementById('PITCH_POSITION_31').value = tf(sss.data.PITCH_POSITION_3);
   		document.getElementById('WIND_SPEED1').value = tf(sss.data.WIND_SPEED);
   		document.getElementById('GRID_POWER1').value = tf(sss.data.GRID_POWER);
   		document.getElementById('ROTOR_SPEED1').value = tf(sss.data.ROTOR_SPEED);
   		document.getElementById('CONVERTER_MOTOR_SPEED2').value = tf(sss.data.CONVERTER_MOTOR_SPEED2);
   		document.getElementById('YAW_DEVIATION_WIND_NACELLE_POSITION1').value = tf(sss.data.YAW_DEVIATION_WIND_NACELLE_POSITION);   		
   	}

   	
 
   	document.getElementById('fjname').value = sss.data2;
   	document.getElementById('mfhl').value = sss.time;
   	
  };
	
    var websocket = null;
    //判断当前浏览器是否支持WebSocket
    if ('WebSocket' in window) {
    	var domain = window.location.host;
        websocket = new WebSocket("ws://"+domain+"/fancon/websocket");
    }
    else {
        alert('当前浏览器 Not support websocket')
    }

    //连接发生错误的回调方法
    websocket.onerror = function () {
    	setMessageInconsole("WebSocket连接发生错误");
    };

    //连接成功建立的回调方法
    websocket.onopen = function () {
    	setMessageInconsole("WebSocket连接成功");
    }

    //接收到消息的回调方法
    websocket.onmessage = function (event) {
    	setMessageInnerHTML(event.data);
    }

    //连接关闭的回调方法
    websocket.onclose = function () {
    	setMessageInconsole("WebSocket连接关闭");
    }

    //监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。
    window.onbeforeunload = function () {
        closeWebSocket();
    }

    
    function setMessageInconsole(innerHTML) {
    	console.log(innerHTML);
    }

    //关闭WebSocket连接
    function closeWebSocket() {
        websocket.close();
    }
