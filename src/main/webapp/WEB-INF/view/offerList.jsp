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


<title>订单列表</title>
</head>
<body>
<nav class="breadcrumb"><i class="Hui-iconfont">&#xe67f;</i> 首页 <span class="c-gray en">&gt;</span> 订单管理 <span class="c-gray en">&gt;</span> 订单列表 
</nav>
<div class="page-container">

	<h4 align="center">未进行业务报价的订单列表</h4>
	
	<!-- 定义一个表格，以供bootstrap-table插件使用 -->
	<table id="orderTable" class="table table-border table-bordered table-bg">
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
	$('#orderTable').bootstrapTable({
		url: 'offer/list.do',//ajax请求的url地址
		responseHandler: function(res) { 
			
			var data =  {rows: res.list,total: res.total};
		
			return data;
		},
		detailView: true,//父子表
		sidePagination: "server",//是否是服务器分页，每次请求都是对应的10条数据，下一页发送ajax请求
		pageSize: 10,//默认的每页条数
		pagination: true,
		toolbar: "#toolbar",//顶部显示的工具条（添加和批量删除的）
		contentType: 'application/x-www-form-urlencoded',//条件搜索的时候ajax请求给后台数据的数据类型（条件搜索post提交必须设置）
		pageNumber: 1,//默认的页面 第一页
		pageList:[10,25,50,100],//每页能显示的条数
		paginationHAlign: 'right', //底部分页条
		showToggle: true, //是否显示详细视图和列表视图的切换按钮
		cardView: false, //是否显示详细视图
		showColumns: true, //是否显示所有的列
		showRefresh: true, //是否显示刷新按钮
		columns: [ //表格显示数据对应的表头设置，
		 //	{ checkbox: true},//是否显示前台的复选框（多选）
			/*
				每列数据的表头的设置
				filed:返回json数据对应数据的key
				title:表头要显示的名
			*/
			{field: 'orderId',title: '编号'}, 
			{field: 'shippingAddress',title: '发货地址'}, 
			{field: 'shippingName',title: '发货人'}, 
			{field: 'shippingPhone',title: '发货电话'}, 
			{field: 'takeName',title: '取件人'},
			{field: 'takeAddress',title: '取件地址'},
			{field: 'takePhone',title: '取件电话'},
			{field: 'userId',title: '业务员'},
			{field: 'customerId',title: '订单所属客户'},
			{field: 'orderStatus',title: '订单状态',formatter : orderStatusFormatter},
			//操作列的设置（删除，修改）
			/*
			formatter: 格式化这一行，回调一个函数
			*/
			 {
				field:'orderId',
				title:'操作',
				align:'center',
				formatter:operationFormatter
			}],
		
		queryParams: function(params) { 
			console.log("params:",params);
			
			//此方法在订单分页或者搜索的时候回自动发送ajax请求调用，并把对应的参数传递给后台
			return {
				pageNum: params.offset / params.limit + 1,
				pageSize: params.limit, //页面大小
				keyword: params.search
			};
		},
		onExpandRow: function (index, row, $detail) {
			
			
			 //获取当前展开行对应的 订单id	
			 var orderId = row.orderId;
			 
			 //创建一个表格，用户点击+号时候马上创建一个表格（子表），用于添加详细数据
		     var cur_table = $detail.html('<table></table>').find('table');
		     
			 //把子表变成bootstra-table
		     $(cur_table).bootstrapTable({
		            url: 'order/detail.do',
		            method: 'get',
		            contentType: 'application/json;charset=UTF-8',//这里我就加了个utf-8
		            dataType: 'json',
		            queryParams: { orderId: orderId },
		            ajaxOptions: { orderId: orderId },
		            clickToSelect: true,
		            columns: [{
		                field: 'orderDetailId',
		                title: '订单明细编号'
		            },{
		                field: 'goodsName',
		                title: '货品名称'
		            },{
		                field: 'goodsNumber',
		                title: '获取数量'
		            },{
		                field: 'goodsTotal',
		                title: '总价'
		            },{
		                field: 'goodsRemark',
		                title: '货品描述'
		            }]
		        });
       }
	});
	
});


function operationFormatter(value,row,index){
	var html = "<a href='offer/returnMsg.do?orderId="+row.orderId+"'><span class='glyphicon glyphicon-circle-arrow-right' style='cursor: pointer;'title='业务报价'></span></a>";
		return html;			
}

function orderStatusFormatter(value, row, index) {
	switch (row.orderStatus) {
	case 1:
		return "取件完成";
	case 2:
		return "核对入库完成";
	case 3:
		return "业务报价完成";
	case 4:
		return "货物运送中";
	case 5:
		return "完成";
	}
}
/*
 * 刷新bootstrap-table 表格插件
 */
function refreshTable(){
	$('#orderTable').bootstrapTable('refresh');
}
</script>
</body>
</html>