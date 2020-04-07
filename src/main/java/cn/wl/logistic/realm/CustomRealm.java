package cn.wl.logistic.realm;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import cn.wl.logistic.pojo.Permission;
import cn.wl.logistic.pojo.PermissionExample;
import cn.wl.logistic.pojo.Role;
import cn.wl.logistic.pojo.User;
import cn.wl.logistic.pojo.UserExample;
import cn.wl.logistic.pojo.UserExample.Criteria;
import cn.wl.logistic.service.PermissionService;
import cn.wl.logistic.service.RoleService;
import cn.wl.logistic.service.UserService;

public class CustomRealm extends AuthorizingRealm{
	 @Autowired
	 private UserService userService;
	 @Autowired
	 private RoleService roleService;
	 @Autowired
	 private PermissionService permissionService;
//认证
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		
	    String username = (String) token.getPrincipal();
	      UserExample example =new UserExample();
	      Criteria criteria = example.createCriteria();
	      criteria.andUsernameEqualTo(username);
		List<User> users = userService.selectByExample(example );
		 User user=users.size() ==1 ? users.get(0) :null;
		if (user !=null) {
			user.setRolename(user.getRole().getRolename());//共享rolename 用于jsp页面取值
			 Object hashedCredentials = user.getPassword();
			 ByteSource credentialsSalt = ByteSource.Util.bytes(user.getSalt());
		 SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(user, hashedCredentials, credentialsSalt, this.getName());
		return authenticationInfo;
		}
		return null;
	}
	
	//授权
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//获取已经登陆的身份信息
		User user = (User) principals.getPrimaryPrincipal();
		//通过该身份里的角色id查出对应的角色
		Role role = roleService.selectByPrimaryKey(user.getRoleId());
		//角色对应的权限id并将其装进集合
		String permissionIds = role.getPermissionIds();
		String[] permissionIdsArr = permissionIds.split(",");
		List<Long> permissionIdList =new ArrayList<>();
		for (String permissionId : permissionIdsArr) {
			permissionIdList.add(Long.valueOf(permissionId));
		}
		//通过权限id查出所有的权限
		PermissionExample example=new PermissionExample();
		cn.wl.logistic.pojo.PermissionExample.Criteria criteria = example.createCriteria();
		criteria.andPermissionIdIn(permissionIdList);
		List<Permission> permissions = permissionService.selectByExample(example);
		//通过循环查询出每个权限对应的权限表达式
		SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
		for (Permission permission : permissions) {
			String expression = permission.getExpression();
			if (StringUtils.isNotBlank(expression)) {
				authorizationInfo.addStringPermission(expression);
			}
		}
		return authorizationInfo;
	}


}
