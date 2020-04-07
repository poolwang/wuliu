<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<%@ taglib uri="http://shiro.apache.org/tags"  prefix="shiro"%>
<!DOCTYPE HTML>
<html>
<head>

<base href="<%=basePath%>">

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap-table/bootstrap-table.min.css" />


<title>客户列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 客户管理 <span class="c-gray en">&gt;</span> 客户列表 
</nav>
<div class="page-container">

	<!-- bootstrap-table的工具条 -->
	 <span id="toolbar" class="l">
	 <shiro:hasPermission name="customer:delete">
	 	<a href="javascript:;" onclick="datadel()" class="btn btn-danger radius">
	 	<i class="Hui-iconfont">&#xe6e2;</i> 批量删除</a> 
	 	</shiro:hasPermission>
	 	<shiro:hasPermission name="customer:insert">
	 	<a href="javascript:;" onclick="customer_add()" class="btn btn-primary radius">
	 	<i class="Hui-iconfont">&#xe600;</i> 添加客户</a>
	 	</shiro:hasPermission>
	 </span> 
	
	<!-- 定义一个表格，以供bootstrap-table插件使用 -->
	<table id="customerTable" class="table table-border table-bordered table-bg">
	</table>
	
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>

<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>


<script type="text/javascript">

/* 
* 使用BootStrapTable插件 加载数据
*/
//在页面（文档）加载完毕以后才会执行内部代码
$(function(){
	$('#customerTable').bootstrapTable({
		url: 'customer/list.do',//ajax请求的url地址
		responseHandler: function(res) { 
			/*
				res: 后台分页对象PageInfo返回对应的json对象
				res.list : 结果集
				res.total : 总记录数
			*/
			var data =  {rows: res.list,total: res.total};
		
			return data;
		},
		sidePagination: "server",//是否是服务器分页，每次请求都是对应的10条数据，下一页发送ajax请求
		pageSize: 10,//默认的每页条数
		pagination: true,
		toolbar: "#toolbar",//顶部显示的工具条（添加和批量删除的）
		contentType: 'application/x-www-form-urlencoded',//条件搜索的时候ajax请求给后台数据的数据类型（条件搜索post提交必须设置）
		search: true,//是否显示搜索框
		pageNumber: 1,//默认的页面 第一页
		pageList:[10,25,50,100],//每页能显示的条数
		paginationHAlign: 'right', //底部分页条
		showToggle: true, //是否显示详细视图和列表视图的切换按钮
		cardView: false, //是否显示详细视图
		showColumns: true, //是否显示所有的列
		showRefresh: true, //是否显示刷新按钮
		columns: [ //表格显示数据对应的表头设置，
			{ checkbox: true},//是否显示前台的复选框（多选）
			/*
				每列数据的表头的设置
				filed:返回json数据对应数据的key
				title:表头要显示的名
			*/
			{field: 'customerId',title: '编号'}, 
			{field: 'customerName',title: '客户名称'}, 
			{field: 'phone',title: '电话'},
			{field: 'email',title: '邮箱'},
			{field: 'address',title: '地址'},
			{field: 'gender',title: '性别',formatter:genderFormatter},
			{field: 'idCard',title: '身份证'},
			{field: 'realname',title: '业务员'},
			{field: 'baseName',title: '客户区间'},
			{field: 'remark',title: '客户备注'},
			//操作列的设置（删除，修改）
			/*
			formatter: 格式化这一行，回调一个函数
			*/
			 {
				field:'customerId',
				title:'操作',
				align:'center',
				formatter:operationFormatter
			}],
		/*发送请求的参数，
			params: bootstrapTable的插件内部参数对象包含如下参数
			limit, offset, search, sort
			limit：每页条数
			offset：每页的结束位置
			search:搜索框对应的值
			sort：排序
		*/
		queryParams: function(params) { 
			console.log("params:",params);
			
			//此方法在客户分页或者搜索的时候回自动发送ajax请求调用，并把对应的参数传递给后台
			return {
				pageNum: params.offset / params.limit + 1,
				pageSize: params.limit, //页面大小
				keyword: params.search
			};
		},
	})
	
});

/*
	操作行格式化对应的函数 
	value: 当前列的值
	row：当前行的值(json对应的具体某一行)
	index：索引位置
*/
function operationFormatter(value,row,index){
	var html ="<shiro:hasPermission name='customer:update'><span onclick='customer_edit("+row.customerId+")'  style='cursor: pointer;' class='glyphicon glyphicon-pencil'></span></shiro:hasPermission>&nbsp;&nbsp";
		html+="<shiro:hasPermission name='customer:delete'><span onclick='customer_del("+row.customerId+")'  style='cursor: pointer;color:red' class='glyphicon glyphicon-trash'></span></shiro:hasPermission>";
	return html;			
}

function genderFormatter(value,row,index){
	if(row.gender==1){
		return "男";
	}
	return "女";
}


/*
 * 刷新bootstrap-table 表格插件
 */
function refreshTable(){
	$('#customerTable').bootstrapTable('refresh');
}



/*客户-增加*/
function customer_add(){
	layer_show("添加客户","customer/edit.do",700,500);
}
/*客户-编辑*/
function customer_edit(customerId){
	layer_show("修改客户","customer/edit.do?customerId="+customerId,700,500);
}
/* 批量删除 */
function datadel() {
	var rows = $("#customerTable").bootstrapTable('getSelections');
	if (rows.length == 0) {
		layer.msg('请选择您要删除的记录');
		return;
	}
	var ids = new Array();
	for (var i = 0; i < rows.length; i++) {
		ids.push(rows[i].customerId);
		
	}
	console.log(ids);
	deleteAll(ids);

}

/* 批量删除 */
function deleteAll(ids) {
	layer.confirm('确认要删除吗？', {
		icon : 3,
		title : '提示'
	}, function(index) {
		$.post("customer/deleteAll.do", {
			ids : ids
		}, function(data) {
			layer.close(index);
			layer.msg(data.msg, {
				time : 1000,
				icon : 6
			});
			if (data.code == 1) {
				refreshTable();
			}
		}, "json");
	});
}

/*客户-删除*/
function customer_del(customerId){

	layer.confirm('确认要删除吗？',{icon: 3, title:'提示'},function(index){
	
		//使用jquery发送ajax请求
		$.post("customer/delete.do",{customerId:customerId},function(resData){
			//关闭询问框
			layer.close(index);
			//弹出一个提示消息
			layer.msg(resData.msg, {time: 1000, icon:6});
			
			if(resData.code == 1){
				//刷新bootstrap-table
				refreshTable();
			}
		});
		
	});
}


</script>
</body>
</html>