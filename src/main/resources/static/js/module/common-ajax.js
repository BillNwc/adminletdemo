/**
 * Created by Amy·Duan on 2018/08/20.
 */
(function () {
   const $ajax = function (url, success, error) {
		this.url = url;
		this.type = "post";
		this.datas = {};
		this.dataType = "json";
		this.async = true;
		this.success = success;
		this.error = error;
	};

	$ajax.prototype = {
		start : function () {
			//因为ajax放在window对象中，所以需要获取this对象
      		const self = this;
			if (this.url.indexOf("?") === -1) {
				this.url = this.url + "?t=" + new Date().getTime();
			} else {
				this.url = this.url + "&t=" + new Date().getTime();
			}
			$.ajax({
				type: this.type,
				url: this.url,
				dataType: this.dataType,
				async: this.async,
				data: this.datas,
				beforeSend: function(data) {

				},
				success: function(data) {
					if($.isFunction(self.success)){
						self.success(data);
					}
				},
				error: function(data) {
					if($.isFunction(self.error)){
              			self.error(data);
					}
				},
				ajaxComplete:function (xhr,obj) {

				}
			});
		},
		//设置参数
		setData : function(data){
			this.datas = data;
			return this;
		},
        setAjaxType : function(type) {
            this.type = type;
            return this;
        },
		doGet : function () {
		  this.type = 'get';
		  return this;
		},
		doDelete : function () {
			this.type = 'delete';
			return this;
		}
	};
	window.$ajax = $ajax;
} ());