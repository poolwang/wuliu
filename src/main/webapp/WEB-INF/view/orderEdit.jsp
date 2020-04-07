<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>    
    
<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + 	request.getServerPort() + request.getContextPath() + "/";
%>
<!DOCTYPE HTML>
<html>
<head>

<base href="<%=basePath%>">

<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style type="text/css">
	.order_detail{
		background-color: durkgray;
		border: 1px solid black;
		border-right: 0; 
		height: 40px;
		line-height: 40px;
	}
	.order_detail input{
		height: 30px;
		text-align: center;
	}
	.order_add{
		border: 1px solid black;
	}
	#order_detail{
		text-align: center;
	}

</style>
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.7-dist/css/bootstrap.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap-table/bootstrap-table.min.css" />
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" method="post"  id="orderForm">
	<!-- 隐藏域 ，订单状态-->
	<input type="hidden" name="orderStatus" value="1">
	
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">业务员：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="userId">
					<c:forEach items="${users}" var="obj">
						<option value="${obj.userId}" ${order.userId == obj.userId?'selected':'' }>${obj.realname}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">客户：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="customerId" id="customer">
					<c:forEach items="${customers}" var="obj">
						<option data-base_id="${obj.baseId}" value="${obj.customerId}"
						${order.customerId == obj.customerId ? 'selected':''}>${obj.customerName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">到达国家：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select id="interval" name="intervalId">
					<c:forEach items="${intervals}" var="obj">
						<option value="${obj.baseId}" ${order.intervalId == obj.baseId?'selected':'' }>${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">收货地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.shippingAddress }" required="required"  placeholder="" id="shippingAddress" name="shippingAddress">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">收货人：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.shippingName }" required="required"   placeholder="" id="shippingName" name="shippingName">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.shippingPhone }" required="required"  placeholder="" id="shippingPhone" name="shippingPhone">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">付款方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="paymentMethodId">
					<c:forEach items="${payments}" var="obj">
						<option value="${obj.baseId}" 
						${order.paymentMethodId == obj.baseId?'selected':'' }>${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">货运方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="freightMethodId">
					<c:forEach items="${freights}" var="obj">
						<option value="${obj.baseId}" ${order.freightMethodId == obj.baseId?'selected':'' }>${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件方式：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<select name="takeMethodId">
					<c:forEach items="${fetchTypes}" var="obj">
						<option value="${obj.baseId}"
						${order.takeMethodId == obj.baseId?'selected':'' }>${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">物流公司：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="xx物流公司"  disabled="disabled"  placeholder="" id="customerName" name="customerName">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">物流单号：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="243242343" disabled="disabled"   placeholder="" id="customerName" name="customerName">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">收件人：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="张三" disabled="disabled"   placeholder="" id="customerName" name="customerName">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">收货地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="广州市天河区xxx" disabled="disabled"   placeholder="" id="customerName" name="customerName">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="135xxxxx"  disabled="disabled"  placeholder="" id="customerName" name="customerName">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件地址：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeAddress }"   placeholder="" id="customerName" name="takeAddress">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">联系电话：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takePhone }"   placeholder="" id="takePhone" name="takePhone">
			</div>
		</div>
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">取件联系人：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<input type="text" class="input-text" value="${order.takeName }"   placeholder="" id="takeName" name="takeName">
			</div>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-4 col-sm-4">
			<label class="form-label col-xs-4 col-sm-4">订单备注：</label>
			<div class="formControls col-xs-8 col-sm-8">
				<textarea name="orderRemark" cols="" rows="" class="textarea"  placeholder="" >
				${order.orderRemark }</textarea>
			</div>
		</div>
	</div>
	<!-- 隐藏域传递id -->
			<input type="hidden" name="orderId" value="${order.orderId }">
	
	
	<div  id="order_detail" class="row cl">
		<div>
			<div  class="col-xs-2 col-sm-2 order_detail">货物名称</div>
			<div  class="col-xs-2 col-sm-1 order_detail">数量</div>
			<div  class="col-xs-2 col-sm-1 order_detail">单位</div>
			<div  class="col-xs-2 col-sm-2 order_detail">单价</div>
			<div  class="col-xs-2 col-sm-2 order_detail">总价值</div>
			<div  class="col-xs-2 col-sm-3 order_detail">备注</div>
			<div  class="col-xs-2 col-sm-1 order_detail order_add">
				<span style="font: 30px;cursor: pointer;color: green" 
					class="glyphicon glyphicon-plus"
					onclick="add_goods_detail();"
					></span>
				</div>
		</div>
		
		<c:forEach items="${orderDetails}" var="o">
			<input type="hidden" name="orderDetails[][orderDetailId]"
				value="${o.orderDetailId }">
		<div id="goods_detail">
		
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input type="text" name="orderDetails[][goodsName]" value="${o.goodsName}">
			</div>
			
			<div  class="col-xs-2 col-sm-1 order_detail">
				<input value="${o.goodsNumber}" onkeyup="countTotalPrice(this)" type="text" size="3" name="orderDetails[][goodsNumber]">
			</div>
			
			<div  class="col-xs-2 col-sm-1 order_detail">
				<select name="orderDetails[][goodsUnit]">
					<c:forEach items="${units}" var="obj">
						<option value="${obj.baseId}"
						${obj.baseId == o.goodsUnit?'selected':'' }>${obj.baseName}</option>
					</c:forEach>
				</select>
			</div>
			
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input value="${o.goodsUnitPrice}" onkeyup="countTotalPrice(this)" type="text" name="orderDetails[][goodsUnitPrice]">
			
			</div>
			
			<div  class="col-xs-2 col-sm-2 order_detail">
				<input value="${o.goodsTotal }" type="text" name="orderDetails[][goodsTotal]">
			</div>
			
			<div  class="col-xs-2 col-sm-3 order_detail">
				<input value="${o.goodsRemark}" type="text" name="orderDetails[][goodsRemark]">
			</div>
			
			<div  class="col-xs-2 col-sm-1 order_detail order_add">
				<span style="font: 30px;cursor: pointer;color: red" 
				class="glyphicon glyphicon-remove"
				onclick="remove_goods_detail(this);"
				></span>
			</div>
		</div>
		</c:forEach>
	</div>
	
	
	<div class="row cl">
		<div class="col-xs-12 col-sm-12 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	</form>
</article>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script> 
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table.min.js"></script>
<script type="text/javascript" src="lib/bootstrap-table/bootstrap-table-zh-CN.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 
<script type="text/javascript" src="lib/jquery/jquery.serializejson.min.js"></script>

<script type="text/javascript">
//自动计算商品详情的总价
function countTotalPrice(obj){
	var parents= $(obj).parent().parent();
	var inputs=parents.find("input");
	inputs.eq(3).val(inputs.eq(1).val()*inputs.eq(2).val());
}

//添加商品详情
function add_goods_detail(){
	//克隆商品详情
	var goodsDetail = $("#goods_detail").clone();
	
	//清除表单的数据
	goodsDetail.find("input").val("");
	
	var order_detail = $("#order_detail");
	
	
	order_detail.append(goodsDetail);
}
//删除商品详情
function remove_goods_detail(obj){
	
	$(obj).parent().parent().remove();
}

//更改客户区间的方法
function  changeCustomerInterval(){
	//获取当前选中的option
	var selectedOption = $("#customer option:selected")
	
	//获取当前选中客户的区间id
	var customer_baseId =  selectedOption.data("base_id");
	
	//获取区间下拉框
	var intervalOptions = $("#interval option");
	
	//var interval = $("#interval");
	//interval.html("");
	
	//循环区间数据
	for(var i = 0 ;i<intervalOptions.length ; i ++){
		//获取单个区间option
		var option = intervalOptions[i];
		//清除选中selected
		$(option).attr("selected",false);
		
		//获取option的value
		var baseId = $(option).val();
		
		//如果customer_baseId客户的区间id和 baseId相等，就让对应的option选中
		if(customer_baseId == baseId){
			//让option选中
			$(option).attr("selected","selected");
		}
	}
	
}



$(function(){
	//更改客户区间的方法
	changeCustomerInterval();
	
	//绑定客户chanage事件
	$("#customer").change(function(){
		changeCustomerInterval();
	});
	
	
	
	
	$("#orderForm").validate({
		submitHandler:function(form){
			//将表单使用Jquery序列化成JSON字符串
			var serializeJSON = $(form).serializeJSON();
			
			//将JSON字符串转换成JSON对象
			var jsonData = JSON.stringify(serializeJSON);
			
			 $.ajax({
			   type: "POST",
			   url: "order/update.do", 
			   contentType: "application/json; charset=utf-8",
			   data: jsonData,
			   success: function(data) {
					layer.msg(data.msg, {
						icon : 6,
						time : 1000
					},function() {
						//获取当前弹出层的索引
						var index = parent.layer
								.getFrameIndex(window.name);
						//让父层页面重新刷新一下
						window.parent.refreshTable();
						//关闭当前弹出层
						parent.layer.close(index);
					});
				}
			}); 
			
			
		}
	});
});
</script> 
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>