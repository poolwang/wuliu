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
<!--[if lt IE 9]>
<script type="text/javascript" src="lib/html5shiv.js"></script>
<script type="text/javascript" src="lib/respond.min.js"></script>
<![endif]-->
<link rel="stylesheet" type="text/css" href="static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="static/h-ui.admin/css/style.css" />
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" method="post" action="${empty permission ? 'permission/insert.do' : 'permission/update.do'}" id="permissionForm">
	<!-- 隐藏域 -->
	<input type="hidden" name="permissionId" value="${permission.permissionId}">
	
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${permission.name}"   placeholder="请输入权限名称" id="name" name="name">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限地址：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${permission.url}" placeholder="请输入权限地址" id="url" name="url">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限表达式：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" autocomplete="off" value="${permission.expression}" placeholder="请输入权限表达式" id="expression" name="expression">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>权限类型：</label>
		<div class="formControls col-xs-8 col-sm-9">
			普通权限：<input type="radio" class="input-radio" autocomplete="off" name="type"  ${permission.type eq 'permission' ?'checked':''} value="permission">
			菜单权限：<input type="radio" class="input-radio" autocomplete="off" name="type"  ${permission.type eq 'menu' ?'checked':''} value="menu" >
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">父权限：</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="parentId" size="1">
				<option value="-1">=顶级权限=</option>
				<c:forEach items="${permissions}" var="p">
					<option value="${p.permissionId}" ${p.permissionId eq permission.parentId ? 'selected':''}>${p.name}</option>
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
	
	$("#permissionForm").validate({
		rules:{
			<c:if test="${empty permission } ">
			name:{
				required:true,
				remote:{
					url:"permission/checkPermissionname.do",
					type:"post",
					dataType:"json",
					data:{
						name:function(){
							return  $("#name").val();
						}
					}
				}
			},
			</c:if>
			//url:"required",
			expression:"required",
			type:"required"
		},
		messages:{
			name:{
				required:"权限名称不能为空",
				remote:"权限名不能重复"
			},
			//url:"地址不能为空",
			expression:"权限表达式不能为空",
			type:"权限类型不能为空"
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