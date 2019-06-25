/*!
 * 
 *
 * jquery.fulongvalidate.js 1.0
 *
 *
 * jQuery JavaScript Library v1.10.1
 *
 * author：黄正元
 *
 * Date: 2014-06-08
 */

;(function($){
	var alertstr="";
	$.fn.alertSingle = function() { 
		return alertSingleStyle($(this));
	};
	$.fn.alertSingleAlert = function() { 
		return alertSingleAlertStyle($(this));
	};
	$.alertSingleAlert = function() { 
		return alertSingleAlertStyle('*');
	};
	$.alertSingle = function() { 
		return alertSingleStyle('*');
	};
	$.fn.alertMulti = function() { 
		return alertMultiStyle($(this));
	};
	//单个顺序校验，一旦失败则停止 (art.dialog)
	function alertSingleStyle(thisdom){
		var flag = true;
		$(thisdom).find("[fulongvalidate]").each(function(){
			var items = $(this).attr("fulongvalidate").split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$.trim($(this).val()),$(this).attr("title"),$(this).attr("name"));
				if(return_str != "1"){
					flag=false;
					art.dialog({left: '40%',content: return_str});
					return false;
				}
			}
		});
		return flag;
		
	}
	
	
	//单个顺序校验，一旦失败则停止 (alert)
	function alertSingleAlertStyle(thisdom){
		var flag = true;
		$(thisdom).find("[fulongvalidate]").each(function(){
			var items = $(this).attr("fulongvalidate").split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$.trim($(this).val()),$(this).attr("title"),$(this).attr("name"));
				if(return_str != "1"){
					flag=false;
					alert(return_str);
					return false;
				}
			}
		});
		return flag;
		
	}
	
	//多个顺序校验
	function alertMultiStyle(thisdom){
		var flag = true;
		$(thisdom).find("[fulongvalidate]").each(function(){
			var items = $(this).attr("fulongvalidate").split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$.trim($(this).val()),$(this).attr("title"),$(this).attr("name"));
				if(return_str != "1"){
					flag=false;
					alertstr = alertstr + "\n" +  return_str;
					break;
				}
			}
		});
		if(alertstr.length > 0){
			alert(alertstr);
		}
		alertstr = "";
		return flag;
	}

	function fulongvalidate_main(){
		$("[fulongvalidate]").each(function(){
			var items = $(this).attr("fulongvalidate").split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$(this).val(),$(this).attr("title"),$(this).attr("name"));
				if(return_str != "1"){
					flag=false;
					break;
				}
			}
		});
		return false;
	}
	
	
	function zyValidateCheck(item,value,title,name){
		var min;
		var max;
		var params =  new Array(3);
		;
		var multiOption = /^\w+\[(-?\d+(\.)?\d*)?,?(-?\d+(\.)?\d*)?(,-?\d+(\.)?\d*)?\]$/;
		var ajOption = /^aj\[[\w!\.'"/]+(,)?\]$/;
		var regexOption = /^\w+\[(,?\S*)+\]$/;
		if(multiOption.test(item) || regexOption.test(item)){
			//min = item.substring(item.indexOf('[')+1,item.indexOf(','));
			//max = item.substring(item.indexOf(',')+1,item.indexOf(']'));
			var par="";
			if(item.indexOf('[') > 0){
				par =  item.substring(item.indexOf('[')+1,item.lastIndexOf(']'));
				item = item.substring(0,item.indexOf('['));
			}
			if(par.length > 0){
				var sp = par.split(",");
				for(var i = 0;i < sp.length;i++){
					params[i] = sp[i];
				}
				min = params[0];
				max = params[1];
			}
		}else if(ajOption.test(item)){
			var its = item.replace(",","");
			min = its.substring(3,its.lastIndexOf("]"));
		    item = "aj";
		}
		switch(item){
			case "re" : //必填属性 re required
				if(!value || value.length == 0){
					return title + "：不能为空";
				}
				return "1";
				break;
			case "em" : //email em email
				var emailRule = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				if(value&&!emailRule.test(value)){
					return title + "：邮箱地址格式不正确";
				}
				return "1";
				break;
			case "po" : //邮政编码 po post
				var postRule = /^[1-9][0-9]{5}$/;
				if(value&&!postRule.test(value)){
					return title + "：邮政编码格式不正确";
				}
				return "1";
				break;
			case "mo" : //手机号码的验证（13开头18开头和158，159开头，共11位） mo mobilphone
				var mobilphoneRule = /^1[0-9]{10}$/; 
				if(value&&!mobilphoneRule.test(value)){
					return title + "：手机号码格式不正确";
				}
				return "1";
				break;
			case "te" : //电话号码格式
				var telephoneRule = /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
				if(value&&!telephoneRule.test(value)){
					return title + "：电话号码格式不正确";
				}
				return "1";
				break;
			case "fax" : //传真格式
				var telephoneRule = /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
				if(value&&!telephoneRule.test(value)){
					return title + "：传真格式不正确";
				}
				return "1";
				break;
				
			case "ph" : //电话和手机号校验
				var mobilphoneRule = /^1[0-9]{10}$/; 
				var telephoneRule = /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/; 
				if(value&&!(telephoneRule.test(value)||mobilphoneRule.test(value))){
					return title + "：格式不正确";
				}
				return "1";
				break;
			case "sl" : //字符串长度 sl stringlength
				if(value){
					if(min&&value.length < min){
						return title + "：不能小于" + min +"个字符";
					}
					if(max&&value.length > max){
						return title + "：不能大于" + max +"个字符";
					}
				}
				return "1";
				break;
			case "ni" : //整数 ni numi
				var intRule = /^-?\d+$/;
				if(value&&!intRule.test(value)){
					return title + "：必须为整数";
				}else{
					if(min&&parseFloat(value) < parseFloat(min)){
						return title + "：不能小于" + min;
					}
					if(max&&parseFloat(value) > parseFloat(max)){
						return title + "：不能大于" + max;
					}
				}
				
				return "1";
				break;
			case "nr" : //实数 nr numr
				var realRule = /^-?\d+(\.)?\d*$/;
				if(value&&!realRule.test(value)){
					return title + "：必须为实数";
				}else{
					if(min&&parseFloat(value) < parseFloat(min)){
						return title + "：不能小于" + min;
					}
					if(max&&parseFloat(value) > parseFloat(max)){
						return title + "：不能大于" + max;
					}
					if(params.length > 2 && value.indexOf(".") > 0){
						var decimals = params[2];
						var valuedecimals = value.substring(value.indexOf(".") + 1);
						if(valuedecimals.length > decimals){
							return title + "：小数位不能超过" + decimals;
						}
					}
				}
				
				return "1";
				break;
			
			case "cb" : //checkbox
				var checkboxname =name;
				var checkedlength = $("input[name='"+checkboxname+"']:checked").length;
				if(checkedlength < params[0]){
					return title + "：必须选择" + params[0] +"项";
				}
				return "1";
				break;
			case "rd" : //radio
				var checkboxname =name;
				var checkedlength = $("input[name='"+checkboxname+"']:checked").length;
				if(checkedlength < params[0]){
					return title + "：必须选择" + params[0] +"项";
				}
				return "1";
				break;
			case "reg" : //正则
				var regex =  eval(params[0]); 
				if(value&&!regex.test(value)){
					return title + "：格式不正确";
				}
				return "1";
				break;	
			case "aj" : 
				/*!
				 *ajax校验 空值需要在后台做判断
				 *接收的参数为提交的地址
				 *采用同步的方式校验
				 *传递到后台的参数为 validatedata 
				 *
				 *
				 */
				var ajretstr;
				$.ajax({
					type: "post",
					url: min,
					async:false,
					data: {validatedata:value},
					success:function(data){
						if(data){
							var dataArray = data.split(";");
							if(dataArray[0] != '0'){
								ajretstr = title + ": " + dataArray[1];
							}else{
								ajretstr = "1";
							}
						}else{
							ajretstr = title + "：校验失败";
						}
						
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						ajretstr = title + "：校验失败";
					}
				});
				return ajretstr;
				break;	
			default: 
				return "1";
				break;
		}
	}
	
})(jQuery)