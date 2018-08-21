/**
 * Created by watts on 2017/08/15.
 */
//配置DataTables默认参数
$.extend(true, $.fn.dataTable.defaults, {
    "language": {
        //Global.contextPath+
        url:'/fonts/datatable-chinese.txt'
    },
    //使用bootstrap风格
    // "dom": "<'row' <'col-md-12'T>><'row'<'col-md-6 col-sm-12'l><'col-md-6 col-sm-12'f>r><'table-scrollable't><'row'<'col-md-5 col-sm-12'i><'col-md-7 col-sm-12'p>>"
    // "dom": "<'row'<'col-md-12 col-sm-12'f>r>t<'row'<'col-md-2 col-sm-12'l><'col-md-3 col-sm-12'i><'col-md-7 col-sm-12'p>>"

});
const CommonTable = function (id,url,columns,dataSrc,type) {
    this.tableId = id;
    this.url = url ;
    this.columns = columns;
    this.dataSrc = dataSrc ? dataSrc : '';
    this.serverSide = false;
    //table实例,返回的是API对象
    this.table = null;
    this.datas = {};
    this.pageSize = 5;
    this.ajaxType = type ? type : 'post';
    this.lengthMenu = [
        [5, 10, 20, 50, -1],
        [5, 10, 20, 50, "All"]
    ];
};


//加载表格
CommonTable.prototype.init = function () {
    const self = this;
    this.table = $('#'+this.tableId).DataTable({
        ajax: {
            url:this.url,
            data:function (d) {
                $.extend(d,self.datas);
                return self.serverSide ? JSON.stringify(d) : '';
            },
            dataSrc:this.dataSrc,
            type: this.ajaxType,
            contentType: "application/json;charset=utf-8",
            dataType:'json'
        },
        bServerSide:this.serverSide,
        //自定义属性列
        columns: this.columns,
        //每页数,有需要可作为参数传入
        lengthChange: false,
        lengthMenu: this.lengthMenu,
        //是否开启全表搜索
        filter:false,
        displayLength : this.pageSize,
        //排序
        ordering:false,
        destroy:true,
        processing : true
    });
    return this;
};
/**
 * 支持多选行记录
 */
CommonTable.prototype.supportSelect = function () {
    $('#'+this.tableId).find('tbody').on( 'click', 'tr', function () {
        $(this).toggleClass('selected');
    });
    return this;
};
/**
 * 支持单选行记录
 */
CommonTable.prototype.supportSingleSelect = function () {
    $('#'+this.tableId).find('tbody').on( 'click', 'tr', function () {
        if ($(this).hasClass('selected') ) {
            $(this).removeClass('selected');
        }else {
            //去掉其他行的选中样式
            $(this).siblings('tr').removeClass('selected');
            $(this).addClass('selected');
        }
    });
    return this;
};
/**
 * 获取当前表格选中记录数
 */
CommonTable.prototype.getSelectedRows = function () {
    return this.table.rows('.selected').data().length;
};
/**
 * 获取当前表格选中记录数据
 */
CommonTable.prototype.getSelectedRowsData = function () {
    return this.table.rows('.selected').data();
};
/**
 * 重新加载表格数据
 * @returns {CommonTable}
 */
CommonTable.prototype.reload = function () {
    this.table.ajax.reload();
    return this;
};
/**
 * 放入查询参数
 * @param data
 * @returns {CommonTable}
 */
CommonTable.prototype.setData = function (data) {
    this.datas = data;
    return this;
};
/**
 * 设置每页显示多少
 * @param size
 * @returns {CommonTable}
 */
CommonTable.prototype.setPageSize = function (size) {
    this.pageSize = size;
    return this;
};
/**
 * 设置请求类型
 * @param type
 * @returns {CommonTable}
 */
CommonTable.prototype.setAjaxType = function(type){
    this.type = type ;
    return this;
};
/**
 * 设置开启服务器分页
 * @param open
 * @returns {CommonTable}
 */
CommonTable.prototype.setServerSide = function(open){
    this.serverSide = open ;
    return this;
};