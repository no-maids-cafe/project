package team.nomaidscafe.project.realm;

import team.nomaidscafe.project.entity.User;
import team.nomaidscafe.project.service.PermissionService;
import team.nomaidscafe.project.service.RoleService;
import team.nomaidscafe.project.service.UserService;

import java.util.Set;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;

public class Realm extends AuthorizingRealm{
    @Autowired
    private UserService userService;
    @Autowired
    private PermissionService PermissionService;
    @Autowired
    private RoleService RoleService;

    // 重写获取授权信息方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // 获取当前用户的所有权限
        String name = principalCollection.getPrimaryPrincipal().toString();
        Set<String> permissions = PermissionService.listPermissionURLsByUser(name);

        // 将权限放入授权信息中
        SimpleAuthorizationInfo s = new SimpleAuthorizationInfo();
        s.setStringPermissions(permissions);
        return s;
    }

    // 获取认证信息，即根据 token 中的用户名从数据库中获取密码、盐等并返回
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String name = token.getPrincipal().toString();
        User user = userService.findByName(name);
        if (ObjectUtils.isEmpty(user)) {
            throw new UnknownAccountException();
        }
        String passwordInDB = user.getPassword();
        String salt = user.getSalt();
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(name, passwordInDB, ByteSource.Util.bytes(salt), user.getName());
        return authenticationInfo;
    }
}