<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ request.getContextPath() + "/";
%>
<!DOCTYPE HTML>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<style type="text/css">
.order_detail {
	background-color: durkgray;
	border: 1px solid black;
	border-right: 0;
	height: 40px;
	line-height: 40px;
}

.order_detail input {
	height: 30px;
	text-align: center;
}

.order_add {
	border: 1px solid black;
}

#order_detail {
	text-align: center;
}
</style>
<!-- 引入头部公共页面 -->
<link rel="stylesheet" type="text/css" href="lib/layer/2.4/skin/layer.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap-3.3.7-dist/css/bootstrap.min.css" />
<link rel="stylesheet" type="text/css" href="lib/bootstrap_table/bootstrap-table.css" />
<link rel="stylesheet" type="text/css" href="lib/layDate-v5.0.9/laydate/theme/default/laydate.css" />
<link rel="stylesheet" type="text/css" href="lib/zTree_v3/css/zTreeStyle/zTreeStyle.css" />
</head>
<body>
	<article class="page-container">
		<form class="form form-horizontal" id="offerForm">
			<!-- 隐藏域 ，订单状态-->
			<input type="hidden" name="orderStatus" value="1">
			<h4>订单基本信息</h4>
			<div class="row cl">
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">订单编号：</label><input
						id="orderId" name="orderId" value="${order.orderId }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">业务员：</label> <input
						id="userId" value="${user.realname }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">客户：</label> <input
						id=customerId value="${customer.customerName }">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">收货地址：</label> <input
						id="shippingAddress" value="${order.shippingAddress }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">收货人：</label> <input
						id=shippingName value="${order.shippingName }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">联系电话：</label> <input
						id=shippingPhone value="${order.shippingPhone }">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">到达国家：</label> <input
						id=intervalId value="${interval.baseName }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">付款方式：</label> <input
						id=paymentMethodId value="${paymentMethod.baseName }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">货运方式：</label> <input
						id=freightMethodId value="${freightMethod.baseName }">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">取件方式：</label> <input
						id=takeMethodId value="${takeMethod.baseName }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">取件费用：</label> <input
						id=takeExpense name="takeExpense" placeholder="取件实际费用">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">入库人：</label> <input
						id="storeClerk" name="storeClerk" value="${putinstorage.storeClerk }">
				</div>
			</div>
			<div class="row cl">
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">体积费率：</label> <input
						id=volumnUnitPrice name="volumnUnitPrice" placeholder="实际体积费率：${putinstorage.volumnUnitPrice }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">重量费率：</label> <input
						id=weightUnitPrice name="weightUnitPrice" placeholder="实际重量费率：${putinstorage.weightUnitPrice }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">入库选择：</label> <select
						id=warehouseId name="warehouseId">
						<c:forEach items="${wareHouses }" var="w">
							<option value="${w.baseId }"
							${putinstorage.warehouseId == w.baseId?'selected':'' }>${w.baseName }</option>
						</c:forEach>
					</select>

				</div>
			</div>


			<div id="order_detail" class="row cl">
			<h4>录入明细</h4>
				<table border="1">
					<tr height="50">
						<td >序号</td>
						<td>货物名称</td>
						<td>数量</td>
						<td>单位</td>
						<td>长(dm)</td>
						<td>宽(dm)</td>
						<td>高(dm)</td>
						<td>核算体积(立方)</td>
						<td>核算重量(千克)</td>
					</tr>
					<c:forEach items="${orderDetails }" var="o">
						<input type="hidden" name="orderDetails[][orderDetailId]"
							value="${o.orderDetailId }">
						<tr height="50">
							<td id="orderDetailId">${o.orderDetailId }</td>
							<td id="goodsName">${o.goodsName }</td>
							<td id="goodsNumber">${o.goodsNumber }</td>
							<td id="goodsUnit">${o.goodsUnitName }</td>
							<td id="cargoLong"><input name="orderDetails[][cargoLong]"
								value="${o.cargoLong }"></td>
							<td id="cargoWeight"><input
								name="orderDetails[][cargoWeight]" value="${o.cargoWeight }"></td>
							<td id="cargoHeight"><input
								name="orderDetails[][cargoHeight]" value="${o.cargoHeight }"></td>
							<td id="valumn"><input name="orderDetails[][offerValumn]"
								id="valumn" placeholder="实际体积是：${o.valumn }"></td>
							<td id="weight"><c:if
									test="${freightMethod.baseName eq '空运'}">
							<input name="orderDetails[][offerWeight]" id="weight" placeholder="实际重量是：${o.weight }">
							</c:if> <c:if test="${freightMethod.baseName ne '空运'}">
									0
								</c:if></td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="row cl">
				<div class="col-xs-12 col-sm-12 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="submit" 
						style="float: left;" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
				</div>
			</div>
		</form>
	</article>

<script type="text/javascript" src="lib/jquery/1.11.3/jquery.min.js"></script>
<script type="text/javascript" src="lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="static/h-ui/js/H-ui.min.js"></script>
<script type="text/javascript" src="static/h-ui.admin/js/H-ui.admin.js"></script>

<script type="text/javascript"src="lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript"src="lib/datatables/1.10.0/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript" src="lib/bootstrap_table/bootstrap-table.min.js"></script>
<script type="text/javascript"src="lib/bootstrap_table/locale/bootstrap-table-zh-CN.min.js"></script>

<script type="text/javascript" src="lib/layDate-v5.0.9/laydate/laydate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script>
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="lib/zTree_v3/js/jquery.ztree.all.min.js"></script>
<script type="text/javascript" src="lib/jquery/jquery.serializejson.min.js"></script>
	
	<script type="text/javascript">
	
	$(function() {
		$("#offerForm").validate({
			submitHandler : function(form) {
				//将表单使用Jquery序列化成JSON字符串
				var serializeJSON = $(form).serializeJSON();
				//将JSON字符串转换成JSON对象
				var jsonData = JSON.stringify(serializeJSON);
				console.log(jsonData);
				$.ajax({
					type : "POST",
					url : "offer/insert.do",
					contentType : "application/json; charset=UTF-8",
					data : jsonData,
					success : function(data) {
						if (data.code == 1) {
							layer.msg(data.msg, {
								icon : 6,
								time : 1000
							});
						}
					}
				});
			}
		});
	});
	

	</script>
</body>
</html>