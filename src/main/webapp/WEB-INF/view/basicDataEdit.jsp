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
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" method="post" action="${empty basicData ? 'basicData/insert.do' : 'basicData/update.do'}" id="basicDataForm">
	<!-- 隐藏域 -->
	<input type="hidden" name="baseId" value="${basicData.baseId}">
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>基础数据名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${basicData.baseName}"   placeholder="请输入基础数据名称" id="baseName" name="baseName">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>基础数据描述：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${basicData.baseDesc}" placeholder="请输入基础数据地址" id="baseDesc" name="baseDesc">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">父基础数据：</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="parentId" size="1">
				<option value="">=没有父基础数据=</option>
				<c:forEach items="${parents}" var="p">
					<!-- 
						如果当前 回显的 基础数据 parentId 和循环的 baseId相等，说明是父分类
					 -->
				
					<option value="${p.baseId}" ${basicData.parentId eq p.baseId ? 'selected':''}>${p.baseName}</option>
				</c:forEach>
			</select>
			</span> </div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
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
<script type="text/javascript" src="lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="lib/jquery.validation/1.14.0/messages_zh.js"></script> 

<script type="text/javascript">
$(function(){
	$("#basicDataForm").validate({
		rules:{
			<c:if test="${empty basicData } ">
			baseName:{
				required:true,
				remote: {//使用ajax接受返回的是boolean类型true可用，false不可用
				    url: "basicData/checkBaseName.do",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        username: function() {
				            return $("#baseName").val();
				        }
				    }
				}
			},
			</c:if>
		},
		messages:{
			baseName:{
				required:"基础数据名称不能为空",
				remote:"基础数据名不能重复"
			},
		},
		submitHandler:function(form){
			var jqForm = $(form);
			jqForm.ajaxSubmit(function(data){
				if(data.code == 1){
					layer.msg(data.msg,{icon:1,time:1000},function(){
						var index = parent.layer.getFrameIndex(window.name);
						window.parent.refreshTable();
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