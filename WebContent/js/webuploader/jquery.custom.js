/*!
 * 用于上传
 * 整合webupload
 * jquery.custom.js 1.0
 *
 *	
 * jQuery JavaScript Library v1.10.1
 *
 * author：黄正元
 *
 * Date: 2014-10-08
 *
 * 文件必须放置在相关html元素下方
 *
 */

;
(function($) {
	$.getUploadData = function() { 
		return unescape(getCookie('fileupload_file_data'));
	};
	$.clearUploadData = function() { 
		return deleteCookie('fileupload_file_data');
	};
	var chunkSize = 5 * 1024 * 1024; // 分块大小 5M
	var FILEUPLOAD_FILE_DATA = "";
	var FILE_SINGLE_SIZE_LIMIT = 10;
	function getServer(type) { // 测试用，根据不同类型的后端返回对应的请求地址
		switch (type) {
		case "md5Check":
			return window.FILEUPLOAD_BASE_PATH + "/uploadercheckfile.do";
		case "chunkCheck":
			return window.FILEUPLOAD_BASE_PATH + "/uploaderchunkCheck.do";
		case "uploadserver":
			return window.FILEUPLOAD_BASE_PATH + "/uploaderchunkfile.do";
		case "chunksMerge":
			return window.FILEUPLOAD_BASE_PATH + "/uploaderchunksMerge.do";
		case "deleteChunk":
			return window.FILEUPLOAD_BASE_PATH + "/uploaderdeleteChunk.do";
		}
	}
	WebUploader.Uploader.register({
		"before-send-file" : "beforeSendFile",
		"before-send" : "beforeSend",
		"after-send-file" : "afterSendFile"
	}, {
		beforeSendFile : function(file) {
			// 秒传验证
			var task = new $.Deferred();

			(new WebUploader.Uploader()).md5File(file, 0, 10 * 1024 * 1024)
					.progress(function(percentage) {

					}).then(function(val) {

						$("#" + file.id).find(".filemd5").val(val);

						$.ajax({
							type : "POST",
							url : getServer('md5Check'),
							data : {
								md5 : val
							},
							cache : false,
							timeout : 1000 // todo 超时的话，只能认为该文件不曾上传过
							,
							dataType : "json"
						}).then(function(data, textStatus, jqXHR) {
							if (data.ifExist) { // 若存在，这返回失败给WebUploader，表明该文件不需要上传
								task.reject();
								uploader.skipFile(file);
								file.path = data.path;
								UploadComlate(file);
							} else {
								task.resolve();
							}
						}, function(jqXHR, textStatus, errorThrown) { // 任何形式的验证失败，都触发重新上传
							task.resolve();
						});
					});
			return $.when(task);
		},
		beforeSend : function(block) {
			// 分片验证是否已传过，用于断点续传
			var task = new $.Deferred();

			var $statedom = $("#" + block.file.id).find(".itemState");
			$statedom.removeClass("text-danger");
			$statedom.removeClass("text-success");
			$statedom.addClass("text-warning");
			$statedom.text("正在上传");

			$.ajax({
				type : "POST",
				url : getServer('chunkCheck'),
				data : {
					chunkIndex : block.chunk,
					size : block.end - block.start,
					md5 : $("#" + block.file.id).find(".filemd5").val()
				},
				cache : false,
				timeout : 1000 // todo 超时的话，只能认为该分片未上传过
				,
				dataType : "json"
			}).then(function(data, textStatus, jqXHR) {
				if (data.ifExist) { // 若存在，返回失败给WebUploader，表明该分块不需要上传
					task.reject();
				} else {
					task.resolve();
				}
			}, function(jqXHR, textStatus, errorThrown) { // 任何形式的验证失败，都触发重新上传
				task.resolve();
			});

			return $.when(task);
		},
		afterSendFile : function(file) {
			var chunksTotal = 0;
			if ((chunksTotal = Math.ceil(file.size / chunkSize)) >= 1) {
				// 合并请求
				var task = new $.Deferred();
				$.ajax({
					type : "POST",
					url : getServer('chunksMerge'),
					data : {
						chunks : chunksTotal,
						ext : file.ext,
						md5 : $("#" + file.id).find(".filemd5").val(),
						relativeFolder:window.FILEUPLOAD_RELATIVE_FOLDER
					},
					cache : false,
					dataType : "json"
				}).then(function(data, textStatus, jqXHR) {

					// todo 检查响应是否正常
					task.resolve();
					file.path = data.filepath;
					if (data.flag) {
						UploadComlate(file);
					} else {
						task.resolve();
					}

				}, function(jqXHR, textStatus, errorThrown) {
					task.reject();
				});

				return $.when(task);
			} else {
				UploadComlate(file);
			}
		}
	});
	var urlsearch = window.document.location.search;
	var strs = urlsearch.substring(1).split("&");
	var limit = undefined;
	for (var i = 0; i < strs.length; i++) {
		if (strs[i].split("=")[0] == 'limit') {// 站点首页
			limit = strs[i].split("=")[1];
		}
	}

	var uploader = WebUploader.create({
		swf : window.FILEUPLOAD_BASE_PATH + "/webuploader/Uploader.swf",
		server : getServer('uploadserver'),
		pick : "#picker",
		resize : false,
		dnd : "#theList",
		paste : document.body,
		disableGlobalDnd : true,
		accept:getFileTyle(window.FILEUPLOAD_FILE_TYPE),
		thumb : {
			width : 100,
			height : 100,
			quality : 70,
			allowMagnify : true,
			//type: "image/jpeg",
			crop : true
			
		}
		// , compress: {
		// quality: 90
		// , allowMagnify: false
		// , crop: false
		// , preserveHeaders: true
		// , noCompressIfLarger: true
		// ,compressSize: 100000
		// }
		,
		compress : false,
		prepareNextFile : true,
		chunked : true,
		chunkSize : chunkSize,
		chunkRetry : 10,
		threads : true,
		fileNumLimit : limit,
		fileSingleSizeLimit : FILE_SINGLE_SIZE_LIMIT * 1024 * 1024 // 10M
		,
		duplicate : false
	});

	uploader.on("uploadBeforeSend", function(block, data) {
		data.chunk = block.chunk;
		data.md5 = $("#" + block.file.id).find(".filemd5").val();
	});

	uploader
			.on(
					"fileQueued",
					function(file) {
						$("#theList")
								.append(
										'<tr id="'
												+ file.id
												+ '">'
												+ '<input type="hidden" value="" class="filemd5"/>'
												+ '<td >'
												+ file.name
												+ '</td>'
												+ '<td>'
												// + ' <button type="button"
												// class="btn itemUpload
												// btn-info btn-sm">上传</button>'
												// + ' <button type="button"
												// class="btn itemStop btn-info
												// btn-sm">暂停</button>'
												+ '	<button type="button" class="btn itemDel btn-info btn-sm">删除</button></td>'
												+ '<td class="progressTd"></td>'
												+ '<td><span class="itemState text-warning">请点击【上传】按钮</span></td>'
												+ '</tr>');

					});

	$("#theList").on("click", ".itemUpload", function() {
		// var file = uploader.getFile($(this).closest("tr").attr("id"));
		// uploader.upload(file);
		uploader.upload($(this).closest("tr").attr("id"));
		// "上传"-->"暂停"
		$(this).hide();
		$(this).next().show();
		var $statedom = $(this).closest("tr").find(".itemState");
		$statedom.removeClass("text-danger");
		$statedom.addClass("text-success");
		$statedom.text("上传中");
	});
	$("#ctlBtn").click(function() {
		// 整体处理
		$("#theList .itemDel").not(":hidden").each(function() {
			var $statedom = $(this).closest("tr").find(".itemState");
			$statedom.removeClass("text-danger");
			$statedom.removeClass("text-success");
			$statedom.addClass("text-warning");
			$statedom.text("等待上传");
		});

		if (1 == uploader.getStats().uploadFailNum) {
			alert(uploader.getStats().uploadFailNum);
			uploader.retry();
		} else {
			uploader.upload();
		}

		// $("#theList .itemUpload").not(":hidden").trigger("click");

	});
	$("#cleanBtn").click(function() {
		if (confirm("是否清空列表")) {
			$("#theList .itemDel").trigger("click");
		}

	});

	$("#stopBtn").click(function() {
		// 整体处理
		uploader.stop(true);
		$("#theList .itemDel").not(":hidden").each(function() {
			var $statedom = $(this).closest("tr").find(".itemState");
			$statedom.removeClass("text-danger");
			$statedom.removeClass("text-success");
			$statedom.addClass("text-warning");
			$statedom.text("已暂停");
		});
		// $("#theList .itemStop").not(":hidden").trigger("click");

	});

	$("#theList").on("click", ".itemStop", function() {

		// var file = uploader.getFile($(this).closest("tr").attr("id"));
		// uploader.stop(file);
		uploader.stop(true);
		// "暂停"-->"上传"
		$(this).hide();
		$(this).prev().show();
		var $statedom = $(this).closest("tr").find(".itemState");
		$statedom.removeClass("text-danger");
		$statedom.removeClass("text-warning");
		$statedom.addClass("text-success");
		$statedom.text("已暂停");
	});

	// todo 如果要删除的文件正在上传（包括暂停），则需要发送给后端一个请求用来清除服务器端的缓存文件
	$("#theList").on("click", ".itemDel", function() {
		uploader.removeFile($(this).closest("tr").attr("id")); // 从上传文件列表中删除
		$(this).closest("tr").remove(); // 从上传列表dom中删除

		$.ajax({
			type : "post",
			url : getServer("deleteChunk"),
			data : {
				md5 : $(this).closest("tr").find(".filemd5").val()
			},
			success : function(data) {

			}
		});

	});

	uploader
			.on(
					"uploadProgress",
					function(file, percentage) {
						var $li = $('#' + file.id), $percent = $li
								.find('.progress .progress-bar');

						// 避免重复创建
						if (!$percent.length) {

							$percentdiv = $('<div class="percentage"><div class="progress">'
									+ '<div class="progress-bar  progress-bar-info progress-bar-striped active" role="progressbar" style="width: 0%">'
									+ '</div>' + '</div></div>');
							$percent = $li.find('.progress .progress-bar');
							$li.find('.progressTd').append($percentdiv);
						}
						$percent.css('width', percentage * 99 + '%');
						$percent.text(parseInt(percentage * 99) + '%');
					});

	uploader.on("uploadError", function(file, reason) {
		$("#" + file.id + " .itemStop").hide();
		$("#" + file.id + " .itemUpload").show();
		var $errordom = $("#" + file.id + " .itemState");
		$errordom.removeClass("text-success");
		$errordom.removeClass("text-warning");
		$errordom.addClass("text-danger");
		if (reason == 'abort') {
			$errordom.text("网络中断");
		} else {
			$errordom.text("未知原因中断");
		}

	});

	uploader.onError = function(code, file) {
		if (code == 'F_DUPLICATE') {
			alert('文件  ' + file.name + '  已在上传列表中');
		} else if (code == 'Q_EXCEED_SIZE_LIMIT') {
			alert('文件  ' + file.name + '  类型不正确');
		} else if (code == 'Q_EXCEED_NUM_LIMIT') {
			alert("超出文件上传数量。限制数为" + limit);
		} else if (code == 'F_EXCEED_SIZE') {
			alert("文件大小超出限制,限制数为" +FILE_SINGLE_SIZE_LIMIT + "M");
		}

	};

	function UploadComlate(file) {
		FILEUPLOAD_FILE_DATA += file.path+"," + file.name + ";";
		addCookie("fileupload_file_data",FILEUPLOAD_FILE_DATA);
		$("#" + file.id + " .percentage").hide();
		var $statedom = $("#" + file.id + " .itemState");
		$statedom.removeClass("text-danger");
		$statedom.removeClass("text-warning");
		$statedom.addClass("text-success");
		$statedom.text("上传完毕");
		$("#" + file.id + " .itemStop").hide();
		$("#" + file.id + " .itemUpload").hide();
		$("#" + file.id + " .itemDel").hide();
	}
	function addCookie(name,value){ 
		var cookieString=name+"="+escape(value); 
		document.cookie=cookieString; 
	} 
	function getCookie(name){ 
		var strCookie=document.cookie; 
		var arrCookie=strCookie.split("; "); 
		for(var i=0;i<arrCookie.length;i++){ 
		var arr=arrCookie[i].split("="); 
		if(arr[0]==name)return arr[1]; 
		} 
		return ""; 
	} 
	function deleteCookie(name){ 
		var date=new Date(); 
		date.setTime(date.getTime()-10000); 
		document.cookie=name+"=v; expires="+date.toGMTString(); 
	} 
})(jQuery);
