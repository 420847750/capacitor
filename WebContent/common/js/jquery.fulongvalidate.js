/*!
 * 
 *
 * jquery.validate.js 1.2
 *
 *
 * jQuery JavaScript Library v1.10.1
 *
 * author：zy
 *
 * Date: 2015-09-01
 */
;(function($){
	var define_label = "fulongvalidate";
	var feedbackid_label ="fulongvalifeedbackfor";

	//默认配置
	var defaultsConfig = {  
        anchor: true 
    } 


	//单个顺序校验，一旦失败则停止 (alert)
	$.fn.alertSingle = function() { 
		return alertSingleAlertStyle($(this));
	};
	//多个顺序校验
	$.fn.alertMulti = function() { 
		return alertMultiStyle($(this));
	};
	//带反馈的校验 需在表单后放置一个 <font id="xxxx"></font> 标签
	$.fn.multiAndFeedback = function(config) { 
		var options = $.extend(defaultsConfig, config);
		return multiAndFeedback($(this),options);
	};
	function alertSingleAlertStyle(thisdom){
		var flag = true;
		$(thisdom).find("["+define_label+"]").each(function(){
			var items = $(this).attr(define_label).split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$.trim($(this).val()),$(this).attr("title") + "：",$(this).attr("name"),thisdom);
				if(return_str != "1"){
					flag=false;
					alert(return_str);
					return false;
				}
			}
		});
		return flag;	
	}
	
	
	function alertMultiStyle(thisdom){
		var alertstr="";
		var flag = true;
		$(thisdom).find("["+define_label+"]").each(function(){
			var items = $(this).attr(define_label).split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$.trim($(this).val()),$(this).attr("title") + "：",$(this).attr("name"),thisdom);
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
		return flag;
	}
	
	function multiAndFeedback(thisdom,options){
		var anchorPosition;
		var anchorFlag = false;
		var alertstr="";
		var flag = true;
		$(thisdom).find("["+define_label+"]").each(function(){
			var items = $(this).attr(define_label).split(";");
			for(var i = 0; i < items.length; i++){
				var return_str = zyValidateCheck(items[i],$.trim($(this).val()),'',$(this).attr("name"),thisdom);
				var feedbackid = $(this).attr(feedbackid_label);
				$(thisdom).find("#" + feedbackid).removeClass("vali_correct");
				$(thisdom).find("#" + feedbackid).removeClass("vali_error");
				if(return_str != "1"){
					if(feedbackid && feedbackid != ''){
						$(thisdom).find("#" + feedbackid).html("" + return_str).addClass("vali_error");
						flag = false;
						if(!anchorFlag){
							anchorFlag = true;
							anchorPosition = $(this);
						}
					}
					break;		
				}else{
					$(thisdom).find("#" + feedbackid).html("").addClass("vali_correct");
				}
			}
		});
		if(options.anchor && anchorFlag && anchorPosition){
			$("html,body").animate({scrollTop:anchorPosition.offset().top - 50},1000);//1000是ms,也可以用slow代替
		}
		return flag;
		
	}
	
	
	function zyValidateCheck(item,value,title,name,formdom){
		var min;
		var max;
		var params =  new Array(3);
		var multiOption = /^\w+\[(-?\d+(\.)?\d*)?,?(-?\d+(\.)?\d*)?(,-?\d+(\.)?\d*)?\]$/;
		var ajOption = /^aj\[[\w!\.'"/]+(,)?\]$/;
		var regexOption = /^\w+\[(,?\S*)+\]$/;
		if(multiOption.test(item) || regexOption.test(item)){
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
					return title + "不能为空";
				}else if(name && ($(formdom).find("input[name='"+name+"']").attr("type") == "radio" || $(formdom).find("input[name='"+name+"']").attr("type") == "checkbox")){
					var checkedlength = $(formdom).find("input[name='"+name+"']:checked").length;
					if(checkedlength == 0){
						return title + "必须选择";
					}
				}
				return "1";
				break;
			case "em" : //email em email
				var emailRule = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+((\.[a-zA-Z0-9_-]{2,3}){1,2})$/;
				if(value&&!emailRule.test(value)){
					return title + "邮箱地址格式不正确";
				}
				return "1";
				break;
			case "po" : //邮政编码 po post
				var postRule = /^[1-9][0-9]{5}$/;
				if(value&&!postRule.test(value)){
					return title + "邮政编码格式不正确";
				}
				return "1";
				break;
			case "mo" : //手机号码的验证（13开头18开头和158，159开头，共11位） mo mobilphone
				var mobilphoneRule = /^([0-9]){11}$/;
				if(value&&!mobilphoneRule.test(value)){
					return title + "手机号码格式不正确";
				}
				return "1";
				break;
			case "te" : //电话号码格式
				var telephoneRule = /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
				if(value&&!telephoneRule.test(value)){
					return title + "电话号码格式不正确";
				}
				return "1";
				break;
			case "fax" : //传真格式
				var telephoneRule = /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/;
				if(value&&!telephoneRule.test(value)){
					return title + "传真格式不正确";
				}
				return "1";
				break;
			
			case "ic" : //身份证号
				if(value&&!identity_card_validate(value)){
					return title + "身份证格式不正确";
				}
				return "1";
				break;
			
			case "ph" : //电话和手机号校验
				var mobilphoneRule = /^1[0-9]{10}$/; 
				var telephoneRule = /^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$/; 
				if(value&&!(telephoneRule.test(value)||mobilphoneRule.test(value))){
					return title + "格式不正确";
				}
				return "1";
				break;
			case "sl" : //字符串长度 sl stringlength
				if(value){
					if(min&&value.length < min){
						return title + "不能小于" + min +"个字";
					}
					if(max&&value.length > max){
						return title + "不能大于" + max +"个字";
					}
				}
				return "1";
				break;
			case "ni" : //整数 ni numi
				var intRule = /^-?\d+$/;
				if(value&&!intRule.test(value)){
					return title + "必须为整数";
				}else{
					if(min&&parseFloat(value) < parseFloat(min)){
						return title + "不能小于" + min;
					}
					if(max&&parseFloat(value) > parseFloat(max)){
						return title + "不能大于" + max;
					}
				}
				
				return "1";
				break;
			case "nr" : //实数 nr numr
				var realRule = /^-?\d+(\.)?\d*$/;
				if(value&&!realRule.test(value)){
					return title + "必须为实数";
				}else{
					if(min&&parseFloat(value) < parseFloat(min)){
						return title + "不能小于" + min;
					}
					if(max&&parseFloat(value) > parseFloat(max)){
						return title + "不能大于" + max;
					}
					if(params.length > 2 && value.indexOf(".") > 0){
						var decimals = params[2];
						var valuedecimals = value.substring(value.indexOf(".") + 1);
						if(valuedecimals.length > decimals){
							return title + "小数位不能超过" + decimals;
						}
					}
				}
				
				return "1";
				break;
			
			case "cb" : //checkbox
				var checkboxname =name;
				var checkedlength = $(formdom).find("input[name='"+checkboxname+"']:checked").length;
				if(checkedlength < params[0]){
					return title + "必须选择" + params[0] +"项";
				}
				return "1";
				break;
			case "rd" : //radio
				var checkboxname =name;
				var checkedlength = $(formdom).find("input[name='"+checkboxname+"']:checked").length;
				if(checkedlength < params[0]){
					return title + "必须选择" + params[0] +"项";
				}
				return "1";
				break;
			case "reg" : //正则
				var regex =  eval(params[0]); 
				if(value&&!regex.test(value)){
					return title + "格式不正确";
				}
				return "1";
				break;
			case "yyzz" : //
				var telephoneRule = /^([0-9]){13}$/
				if(value&&!telephoneRule.test(value)){
					return title + "营业执照注册号格式不正确，必须为13位数字";
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
							ajretstr = title + "校验失败";
						}
						
					},
					error:function(XMLHttpRequest, textStatus, errorThrown){
						ajretstr = title + "校验失败";
					}
				});
				return ajretstr;
				break;	
			default: 
				return "1";
				break;
		}
	}
	function identity_card_validate(idCard){
		var Wi = [ 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2, 1 ];    // 加权因子   
		var ValideCode = [ 1, 0, 10, 9, 8, 7, 6, 5, 4, 3, 2 ];            // 身份证验证位值.10代表X  
		idCard = $.trim(idCard.replace(/ /g, ""));               //去掉字符串头尾空格      
		if (idCard.length == 15) {   
			return isValidityBrithBy15IdCard(idCard);       //进行15位身份证的验证    
		} else if (idCard.length == 18) {   
			var a_idCard = idCard.split("");                // 得到身份证数组   
			if(isValidityBrithBy18IdCard(idCard)&&isTrueValidateCodeBy18IdCard(a_idCard)){   //进行18位身份证的基本验证和第18位的验证
				return true;   
			}else {   
				return false;   
			}   
		} else {   
			return false;   
		}   

		/**  
		 * 判断身份证号码为18位时最后的验证位是否正确  
		 * @param a_idCard 身份证号码数组  
		 * @return  
		 */  
		function isTrueValidateCodeBy18IdCard(a_idCard) {   
			var sum = 0;                             // 声明加权求和变量   
			if (a_idCard[17].toLowerCase() == 'x') {   
				a_idCard[17] = 10;                    // 将最后位为x的验证码替换为10方便后续操作   
			}   
			for ( var i = 0; i < 17; i++) {   
				sum += Wi[i] * a_idCard[i];            // 加权求和   
			}   
			valCodePosition = sum % 11;                // 得到验证码所位置   
			if (a_idCard[17] == ValideCode[valCodePosition]) {   
				return true;   
			} else {   
				return false;   
			}   
		}   
		/**  
		  * 验证18位数身份证号码中的生日是否是有效生日  
		  * @param idCard 18位书身份证字符串  
		  * @return  
		  */  
		function isValidityBrithBy18IdCard(idCard18){   
			var year =  idCard18.substring(6,10);   
			var month = idCard18.substring(10,12);   
			var day = idCard18.substring(12,14);   
			var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
			// 这里用getFullYear()获取年份，避免千年虫问题   
			if(temp_date.getFullYear()!=parseFloat(year)   
				  ||temp_date.getMonth()!=parseFloat(month)-1   
				  ||temp_date.getDate()!=parseFloat(day)){   
					return false;   
			}else{   
				return true;   
			}   
		}   
		  /**  
		   * 验证15位数身份证号码中的生日是否是有效生日  
		   * @param idCard15 15位书身份证字符串  
		   * @return  
		   */  
		  function isValidityBrithBy15IdCard(idCard15){   
			  var year =  idCard15.substring(6,8);   
			  var month = idCard15.substring(8,10);   
			  var day = idCard15.substring(10,12);   
			  var temp_date = new Date(year,parseFloat(month)-1,parseFloat(day));   
			  // 对于老身份证中的你年龄则不需考虑千年虫问题而使用getYear()方法   
			  if(temp_date.getYear()!=parseFloat(year)   
					  ||temp_date.getMonth()!=parseFloat(month)-1   
					  ||temp_date.getDate()!=parseFloat(day)){   
						return false;   
				}else{   
					return true;   
				}   
		  }  
		
	}
})(jQuery)