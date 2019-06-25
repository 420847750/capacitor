var Manufacture_date;//出厂日期
var test_cycle;//测试周期
var last_test;//最后一次测试
var next_test;//下一次测试
var error_message;//异常信息


//悬浮鼠标显示详细信息
window.onload=function() {
	var Omain=document.getElementById('main');
	var Ofj=document.getElementsByClassName('fengji');
	var Ofh=document.getElementsByClassName('msg');
	for (var i = 0; i < Ofj.length; i++) {
		Ofj[i].index=i;
		Ofj[i].onmouseover=function(){
			console.log(this.index)
			Ofh[this.index].style.display='block';
		}
		Ofj[i].onmouseout=function(){
			Ofh[this.index].style.display='none'
		}
	};
}

