package pers.zb.ucenter.web.shiro;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.subject.support.DefaultSubjectContext;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.zb.common.util.enums.UserStatus;
import pers.zb.entity.sys.SysPermission;
import pers.zb.entity.sys.SysRole;
import pers.zb.entity.sys.SysUser;
import pers.zb.ucenter.rpc.api.sys.PermissionService;
import pers.zb.ucenter.rpc.api.sys.RoleService;
import pers.zb.ucenter.rpc.api.user.UserService;

@Service("globalAuthorizingRealm")
public class GlobalAuthorizingRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(GlobalAuthorizingRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    /**
     * 登陆认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String userName = upToken.getUsername();

        if (StringUtils.isEmpty(userName)) {
            LOGGER.warn("login username is blank.");
            throw new AccountException("Empty usernames are not allowed by this realm.");
        }
        
        //清除之前登录所存在的session，同一账户同一地点，只能在一个地方登录
        DefaultWebSecurityManager securityManager = (DefaultWebSecurityManager) SecurityUtils.getSecurityManager();
        DefaultWebSessionManager sessionManager = (DefaultWebSessionManager)securityManager.getSessionManager();
        Collection<Session> sessions = sessionManager.getSessionDAO().getActiveSessions();//获取当前已登录的用户session列表
        for(Session session:sessions){
            //清除该用户以前登录时保存的session
            if(userName.equals(String.valueOf(session.getAttribute(DefaultSubjectContext.PRINCIPALS_SESSION_KEY)))) {
                sessionManager.getSessionDAO().delete(session);
            }
        }
        
        // 根据用户名加载记录
        SysUser user;
        try {
            user = userService.getUserByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new AuthenticationException("cannot query user[" + userName + "]", e);
        }

        if (user == null) {
            final String errmsg = "No account found for user [" + userName + "]";
            LOGGER.warn(errmsg);
            throw new UnknownAccountException(errmsg);
        }else if (user.getStatus() == UserStatus.LOCK) {//锁定状态 
            throw new LockedAccountException("用户 [" + userName + "] 被锁定.");
        }else if(user.getStatus() == UserStatus.DISABLE){
            throw new DisabledAccountException("用户 [" + userName + "] 被禁用");
        }
         
        return new SimpleAuthenticationInfo(user.getUserName(), user.getPassword(), getName());
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String) principals.fromRealm(getName()).iterator().next();
        SysUser user = null;
        try {
            // 根据用户名加载记录
            user = userService.getUserByName(userName);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        if (user == null) {
            return null;
        }

        // 添加session
        Subject currentUser = SecurityUtils.getSubject();
        Session session = currentUser.getSession();
        session.setAttribute("userInfo", user);

        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        try {
            // roles
            Set<String> roles = new HashSet<String>();
            // 查找用户角色
            List<SysRole> roleLi = roleService.getUserRoles(userName);
            if (CollectionUtils.isNotEmpty(roleLi)) {
                for (SysRole foo : roleLi) {
                    roles.add(foo.getName());
                }
            }
            info.addRoles(roles);

            // 根据用户名获取权限
            // permissions
            Set<String> permissions = new HashSet<String>();
            List<SysPermission> permissionsLi = permissionService.getUserPermissions(userName);
            if (CollectionUtils.isNotEmpty(permissionsLi)) {
                for (SysPermission foo : permissionsLi) {
                    if (StringUtils.isNotEmpty(foo.getCode())) {
                        permissions.add(foo.getCode());
                    }
                }
            }
            info.addStringPermissions(permissions);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Fetch roles and permissions for user[" + userName + "] failed: ", e);
        }

        return info;
    }

    /**
     * 设定密码校验的Hash算法与迭代次数
     */
    /*
     * @PostConstruct public void initCredentialsMatcher() {
     * HashedCredentialsMatcher matcher = new
     * HashedCredentialsMatcher(Md5Hash.ALGORITHM_NAME);
     * setCredentialsMatcher(matcher); }
     */

}
