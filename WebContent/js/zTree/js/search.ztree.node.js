var lastValue = "", nodeList = [], fontCss = {};

function focusKey(e) {
	if (key.hasClass("empty")) {
		key.removeClass("empty");
	}
}
function blurKey(e) {
	if (key.get(0).value === "") {
		key.addClass("empty");
	}
}

function searchNode(e) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	var value = $.trim(key.get(0).value);
	var keyType = "name";
	if (key.hasClass("empty")) {
		value = "";
	}
	if (lastValue === value) return;
	lastValue = value;
	if (value === "") return;
	updateNodes(false);
	nodeList = zTree.getNodesByParamFuzzy(keyType, value);
	updateNodes(true);
}

function updateNodes(highlight) {
	var zTree = $.fn.zTree.getZTreeObj("treeDemo");
	for( var i=0, l=nodeList.length; i<l; i++) {
		nodeList[i].highlight = highlight;
		zTree.updateNode(nodeList[i]);
		zTree.expandNode(nodeList[i], true, null, null);
	}
}

function getFontCss(treeId, treeNode) {
	return (!!treeNode.highlight) ? {color:"#A60000", "font-weight":"bold"} : {color:"#333", "font-weight":"normal"};
}

function openall(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.expandAll(true);
}
function closeall(){
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	treeObj.expandAll(false);
}

//获取选中的id
function getNodeIds() {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var ids = "";
	for(var i = 0; i < nodes.length; i++){
		ids = ids + nodes[i].id + ",";
	}
	return ids;
}

//获取选中的name
function getNodeNames() {
	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
	var nodes = treeObj.getCheckedNodes(true);
	var names = "";
	for(var i = 0; i < nodes.length; i++){
		names = names + nodes[i].name + ",";
	}
	return names;
}
