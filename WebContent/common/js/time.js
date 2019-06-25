var TIME = 2000;


//全选
function chkall(input) {
	var objForm = document.getElementById("listForm");
	var objLen = objForm.length;
	for ( var i = 0; i < objLen; i++) {
		if (objForm.elements[i].type == "checkbox") {
			objForm.elements[i].checked = true;
		}
	}
}
function chkallno(input) {
	var objForm = document.getElementById("listForm");
	var objLen = objForm.length;
	for ( var i = 0; i < objLen; i++) {
		if (objForm.elements[i].type == "checkbox") {
			objForm.elements[i].checked = false;
		}
	}
}
function getIds() {
	var objForm = document.getElementById("listForm");
	if (!objForm) {
		return "";
	}
	var objLen = objForm.length;
	var ids = "";
	
	for ( var i = 0; i < objLen; i++) {
		if (objForm.elements[i].type == "checkbox"
				&& objForm.elements[i].checked == true) {
			
			ids = ids + objForm.elements[i].value + ",";
				
		}
	}
	return ids.substring(0,ids.length-1);
}
function doNothing(){  
    window.event.returnValue=false;  
    return false;
}  

 function doExport(){
	var  code=$("#code").val();
	code=code+"export";
	var url="/fancon/export.action?code="+code;
	 window.open(url);
	}    
 function doSearch(){
	 var form = document.getElementById("form");
	 form.submit();
	}
 
 
function doSearch(){
	var form = document.getElementById("form");
	form.submit();
}
function goPage(newPage, pageCount) {
	if (isIntNum(newPage)) {
		top.art.dialog({time: 2,icon: 'warning',lock:true,content: "数字格式不正确！"});
		return;
	}
	if (newPage > pageCount) {
		top.art.dialog({time: 2,icon: 'warning',lock:true,content: "页码太大，不能超过"+pageCount});
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
		top.art.dialog({time: 2,icon: 'warning',lock:true,content: "数字格式不正确！"});
		return;
	}
	if (newPage > pageCount) {
		top.art.dialog({time: 2,icon: 'warning',lock:true,content: "页码太大，不能超过"+pageCount});
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