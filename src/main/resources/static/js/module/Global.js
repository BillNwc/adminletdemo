/**
 * Created by watts on 2017/08/15.
 */
var Global = {
    contextPath : "",
    //前台设置上下文路径
    setContextPath: function (ctxPath) {
        if (this.contextPath === "") {
            this.contextPath = ctxPath;
        }
    },
    commonAjaxRemoteSelect2 : function(inputId,placeholder,url,itemsFormatResult,itemsFormatSelection){
		commonAjaxRemoteSelect2(inputId,placeholder,url,itemsFormatResult,itemsFormatSelection);
	},
	loadSelect2Info : function(inputId,url){
		loadSelect2Info(inputId,url);
	},
	loadDictionaryByJquery : function (selectObj, classid, attributeid){
		loadDictionaryByJquery(selectObj, classid, attributeid);
	},
 };

/**
 *加载select2控件数据 这个地方后台获取全部数据。拼装成<option></option>的形式
 */
function loadSelect2Info(inputId, url){
	$('#'+inputId).select2({
		placeholder : "",
		allowClear : true
	});
	$.getJSON(url,function(data){
		$("#"+inputId).empty();
		$("#"+inputId).html(data.assInfo);
		$("#"+inputId).select2("data","");
	});
};
function commonAjaxRemoteSelect2(inputId,placeholder,url,itemsFormatResult,itemsFormatSelection){
	// 拼装结果展示
	var defaultItemsFormatResult=function(repo) {
	    var markup = "<div class='clearfix'><div class='col-sm-20'>" + repo.name + "</div></div>";
	    return markup;
    }
    // 选择结果的回调处理函数
	var defaultItemsFormatSelection=function(repo) {
        repo.selected = true;
	    $("#"+inputId).val(repo.id);
	    return repo.name ;
    }
	//如果没有自定义函数，就使用默认的吧，但是返回的json格式要注意
	var $itemsFormatResult=$.isFunction(itemsFormatResult) ? itemsFormatResult : defaultItemsFormatResult ;
	var $itemsFormatSelection=$.isFunction(itemsFormatSelection) ? itemsFormatSelection : defaultItemsFormatSelection ;
	$("#"+inputId).select2({
		allowClear: true,
        placeholder: " ",
        minimumInputLength: 1,
        ajax: { // 每次输入都要请求后台
            url: url,
            dataType: 'json',
            quietMillis: 1000,
            data: function (term, page) {
                return {
                	q: term, // 查询关键字
                    page_limit: 10,
                    page: page
                };
            },
            results: function (data, page) {
            	page = page || 1;//当前页赋值
                return {
                    results: data.items,//查询结果集，可以自定义（不动最好）
                    pagination: {
			          more: (page * 10) < data.total_count//加载下一页，每页显示数
			        }
                };
            }
        },
        formatInputTooShort: function (input, min) {//未输入查询内容的提示
        	 var n = min - input.length;
        	  return "Please input value";
        },
        formatResult: $itemsFormatResult, // 拼装结果展示
        formatSelection: $itemsFormatSelection, // 选择结果的回调处理函数
        dropdownCssClass: "bigdrop", // apply css that makes the dropdown taller
        escapeMarkup: function (m) {
            return m;
        }
    });

}
function loadDictionaryByJquery(selectObj, classid, attributeid){
	selectObj.empty();
	$.ajax({
		type : "POST",
		url : Global.contextPath+"/dictionary/getDicList/",
		dataType : "json",
		data : {"classid": classid, "attributeid": attributeid},
		success : function(data) {
			var type = eval(data);
			for(var i=0; i<type.dic.length; i++){
				var att = type.dic[i];
				 selectObj.append("<option value='" + att[0] + "'>" + att[1] + "</option>");
		 }
		}
	});
}
