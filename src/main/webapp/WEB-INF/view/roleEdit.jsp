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
<link rel="stylesheet" href="lib/zTree/v3/css/zTreeStyle/zTreeStyle.css"type="text/css">
</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" method="post" action="${role !=null ? 'role/update.do':'role/insert.do'}" id="roleForm">
	<!-- 隐藏域，用于修改时候提交 用户id -->
	<input type="hidden" name="roleId" value="${role.roleId}">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${role.rolename}" placeholder="" id="rolename" name="rolename">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色描述：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${role.remark}" placeholder="" id="remark" name="remark">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>角色权限：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<!-- 角色权限 -->
			<ul id="permissionTree" class="ztree"></ul>
		</div>
	</div>
	<div class="row cl">
		<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-3">
			<input class="btn btn-primary radius" type="submit" value="&nbsp;&nbsp;提交&nbsp;&nbsp;">
		</div>
	</div>
	<input type="hidden" name="permissionIds" id="permissionIds">
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
<script type="text/javascript" src="lib/zTree/v3/js/jquery.ztree.all-3.5.min.js"></script>
<script type="text/javascript">

/* ztree的设置，就是一个json对象，如果不写就是ztree的默认设置 */
var setting = {
	check : {enable : true},
	/* 是否支持简单数据 */
	data : {
		simpleData : {
			enable : true
		}
	},
	async : {
		enable : true,
		url : "permission/getAllPermission.do",
		//dataFilter : filter
	},
	/* 异步加载数据完毕以后回调的函数 */
	callback: {
		onAsyncSuccess: zTreeOnAsyncSuccess
	}
};

//异步加载数据成功以后回调的函数
//开发者在此函数中进行zTree数据的回显（已经存在的数据进行选中效果的处理）
function zTreeOnAsyncSuccess(event, treeId, treeNode, msg) {
 	
	var treeObj = $.fn.zTree.getZTreeObj("permissionTree");

	var permissionIds = "${role.permissionIds}";
	
	var permissionIdsArr = 	permissionIds.split(",");
	console.log(permissionIdsArr);
	
	for(var i = 0;i<permissionIdsArr.length;i++){
		
		var id = permissionIdsArr[i];
		
		var node = treeObj.getNodeByParam("id",id);
		console.log(node);
		
		if(node.children == null){
			treeObj.checkNode(node, true, true);
		}
	}
	
};

$(function(){
	
	/* 加载zTree */
	$.fn.zTree.init($("#permissionTree"), setting);
	
	$("#roleForm").validate({
		rules:{//校验规则
			/* 使用jstl标签库的 if标签 把rolename 的表单校验进行判断*/
			<c:if test="${role == null}">
			rolename:{//账号
				required:true,//不能为空
				remote: {//使用ajax接受返回的是boolean类型true可用，false不可用
				    url: "role/checkRolename.do",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        rolename: function() {
				            return $("#rolename").val();
				        }
				    }
				}
			},
			</c:if>
			remark:{//角色描述
				required:true//不能为空
			},
			
		},
		messages:{//校验失败的提示小消息
			<c:if test="${role == null}">
			rolename:{//账号
				required:"角色不能为空",//不能为空
				remote:"角色已经存在请换一个角色名称"
			},
			</c:if>
			remark:{//角色描述
				required:"角色备注"//不能为空
			}
			
		},
		//校验成功以后知悉的回调函数  form参数 表单
		submitHandler:function(form){
			
			//获取zTree选中数据
			//获取zTree树对象
			var treeObj = $.fn.zTree.getZTreeObj("permissionTree");
			//获取zTree选中的数据集合
			var nodes = treeObj.getCheckedNodes(true);
			
			//定义一个拼接 权限id的字符串
			var permissionIdsArr = [];
			
			for(var i = 0;i<nodes.length;i++){
				var node = nodes[i];
				var id = node.id;
				permissionIdsArr.push(id);
			}
			//调用js数组的toString方法，默认会将数组元素以逗号形式转换成字符串
			var permissionIds = permissionIdsArr.toString();
			$("#permissionIds").val(permissionIds);
			
			//1，先使用jquery将表单的数据序列化提取出来
			var formData = $(form).serialize();
			console.log(formData);
			
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