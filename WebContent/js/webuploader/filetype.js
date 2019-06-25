function getFileTyle(type){
	switch(type){
		case 'images' : return {title: 'Images', extensions: 'gif,jpg,jpeg,bmp,png',mimeTypes: 'image/*'};
		case 'ico' : return {title: 'Ico', extensions: 'ico',mimeTypes: 'image/x-icon'};
		case 'attach' : return {title: 'attach', extensions: 'doc,docx,xls,xlsx,ppt,pptx,pdf,rar,zip,gif,jpg,png,tex,wps,et,dps,txt',mimeTypes: ''};
	}
	
}