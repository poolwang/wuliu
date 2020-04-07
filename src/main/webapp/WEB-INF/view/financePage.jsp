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
		<form class="form form-horizontal" method="post" id="offerForm">
			<!-- 隐藏域 ，订单状态-->
			<input type="hidden" name="orderStatus" value="1">
			<h5>订单基本信息</h5>
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
					<label class="form-label col-xs-4 col-sm-4">入库人：</label> <input
						id="storeClerk" name="storeClerk" value="${principal.realname }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">入库选择：</label> <input
						id="warehouseId" name="warehouseId" value="${wareHouse.baseName }">
				</div>
			</div>
			<hr>
			<h5>费用明细</h5>

			<div class="row cl">

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">体积收费：</label> <input
						id="volumnExpense" name="volumnExpense"
						value="${finance.volumnExpense }">
				</div>

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">总体积：</label> <input
						id="totalVolumn" name="totalVolumn"
						value="${finance.totalVolumn }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">体积费率：</label> <input
						id="offerVolumnUnitPrice" name="offerVolumnUnitPrice"
						value="${offerVolumnUnitPrice }">
				</div>
			</div>

			<div class="row cl">

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">重量收费：</label> <input
						id="weightExpense" name="weightExpense"
						value="${finance.weightExpense }">
				</div>

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">总重量：</label> <input
						id="totalWeight" name="totalWeight"
						value="${finance.totalWeight }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">重量费率：</label> <input
						id="offerWeightUnitPrice" name="offerWeightUnitPrice"
						value="${offerWeightUnitPrice }">
				</div>

			</div>


			<div class="row cl">

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">过关税费：</label> <input
						id="rateExpense" name="rateExpense"
						value="${finance.rateExpense }">
				</div>

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">总价值：</label> <input
						id="totalValue" name="totalValue" value="${finance.totalValue }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">税率：</label> <input
						id="taxRate" name="taxRate"
						value="${finance.taxRate == 0?'0%':'finance.taxRate%' }">
				</div>

			</div>

			<div class="row cl">

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">取件费用：</label> <input
						id="offerTakeExpense" name="offerTakeExpense"
						value="${offerTakeExpense }">
				</div>

				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">其他费用：</label> <input
						id="otherExpense" name="otherExpense"
						value="${finance.otherExpense }">
				</div>
				<div class="col-xs-4 col-sm-4">
					<label class="form-label col-xs-4 col-sm-4">总费用：</label> <input
						id="totalExpense" name="totalExpense"
						value="${finance.totalExpense }">
				</div>

			</div>
			<hr>
			<h5>货物清单</h5>
			<div id="order_detail" class="row cl">

				<table border="1">
					<tr height="50">
						<td>序号</td>
						<td>货物名称</td>
						<td>数量</td>
						<td>单位</td>
						<td>长(dm)</td>
						<td>宽(dm)</td>
						<td>高(dm)</td>
						<td>核算体积(立方)</td>
						<td>核算重量(千克)</td>
						<td>总价值</td>
					</tr>
					<c:forEach items="${orderDetails }" var="o">
						<input type="hidden" name="orderDetails[][orderDetailId]"
							value="${o.orderDetailId }">
						<tr height="50">
							<td id="orderDetailId">${o.orderDetailId }</td>
							<td id="goodsName">${o.goodsName }</td>
							<td id="goodsNumber">${o.goodsNumber }</td>
							<td id="goodsUnit">${o.goodsUnitName }</td>
							<td id="cargoLong">${o.cargoLong }</td>
							<td id="cargoWeight">${o.cargoWeight }</td>
							<td id="cargoHeight">${o.cargoHeight }</td>
							<td id="valumn">${o.offerValumn }</td>
							<td id="weight"><c:if
									test="${freightMethod.baseName eq '空运'}">
								${o.offerWeight }
							</c:if> <c:if test="${freightMethod.baseName ne '空运'}">
								0
								</c:if></td>
							<td>${o.goodsTotal }</td>
						</tr>
					</c:forEach>
				</table>
			</div>

			<div class="row cl">
				<div class="col-xs-12 col-sm-12 col-xs-offset-4 col-sm-offset-3">
					<input class="btn btn-primary radius" type="button"
						style="float: left; margin: 10px" onclick="exportForm();"
						value="&nbsp;&nbsp;导出&nbsp;&nbsp;">
						
						
					<button class="btn btn-primary radius"
						style="float: left; margin: 10px">
						<a href="finance/financePage.do" style="color: white">&nbsp;&nbsp;返回&nbsp;&nbsp;</a>
					</button>
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
	<script type="text/javascript"
		src="lib/jquery/jquery.serializejson.min.js"></script>

	<script type="text/javascript" src="lib/jquery/FileSaver.js"></script>

	<script type="text/javascript" src="lib/jquery/jquery.wordexport.js"></script>

	<script type="text/javascript">
	 
		function exportForm(){
				$("#offerForm").wordExport("订单"+${order.orderId }+"的财务审核表");
		} 
	</script>
</body>
</html>