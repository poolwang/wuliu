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
	<form class="form form-horizontal" method="post" action="${user !=null ? 'admin/update.do':'admin/insert.do'}" id="userForm">
	<!-- 隐藏域，用于修改时候提交 用户id -->
	<input type="hidden" name="userId" value="${user.userId}">
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员账号：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${user.username}" ${user !=null ?'disabled':''}  placeholder="" id="username" name="username">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>管理员真实名称：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="text" class="input-text" value="${user.realname}" placeholder="" id="realname" name="realname">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>初始密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" autocomplete="off" value="${user.password}" placeholder="密码" id="password" name="password">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3"><span class="c-red">*</span>确认密码：</label>
		<div class="formControls col-xs-8 col-sm-9">
			<input type="password" class="input-text" autocomplete="off" value="${user.password}"  placeholder="确认新密码" id="password2" name="password2">
		</div>
	</div>
	<div class="row cl">
		<label class="form-label col-xs-4 col-sm-3">角色：</label>
		<div class="formControls col-xs-8 col-sm-9"> <span class="select-box" style="width:150px;">
			<select class="select" name="roleId" size="1">
				<option value="-1">=请选择=</option>
				<c:forEach items="${roles}" var="role">
					<!-- 判断循环的roleId和当前要修改的管理员的 roleId 如果相等
						就让 <option selected> 选中
					 -->
					<option value="${role.roleId}" ${user.roleId eq role.roleId ?'selected':''}>${role.rolename}</option>
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
	
	$("#userForm").validate({
		rules:{//校验规则
			/* 使用jstl标签库的 if标签 把username 的表单校验进行判断*/
			<c:if test="${user == null}">
			username:{//账号
				required:true,//不能为空
				minlength:4,//最小4位数
				maxlength:12,//最大12位数
				remote: {//使用ajax接受返回的是boolean类型true可用，false不可用
				    url: "admin/checkUsername.do",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        username: function() {
				            return $("#username").val();
				        }
				    }
				}
			},
			</c:if>
			realname:{//真实名称
				required:true//不能为空
			},
			password:{//密码
				required:true,//不能为空
			},
			password2:{//确认密码
				required:true,//不能为空
				equalTo: "#password"//确认必须和密码相等
			},
			roleId:{//角色
				min:1,//最小必须为1
			},
		},
		messages:{//校验失败的提示小消息
			<c:if test="${user == null}">
			username:{//账号
				required:"账号不能为空",//不能为空
				minlength:"账号最少4位数",//最小4位数
				maxlength:"账号最大12位数",//最大12位数
				remote:"账号已经存在请换一个账号"
			},
			</c:if>
			realname:{//真实名称
				required:"真实姓名不能为空"//不能为空
			},
			password:{//密码
				required:"密码不能为空",//不能为空
			},
			password2:{//确认密码
				required:"确认密码不能为空",//不能为空
				equalTo: "确认密码必须和密码相同"//确认必须和密码相等
			},
			roleId:{//角色
				min:"请选择一个角色"
			},
		},
		//校验成功以后知悉的回调函数  form参数 表单
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