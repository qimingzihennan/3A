package com.unitrust.timestamp3A.authorization.realm;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang3.builder.ReflectionToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;

import com.unitrust.timestamp3A.common.util.Common;
import com.unitrust.timestamp3A.model.user.ResourceRoles;
import com.unitrust.timestamp3A.model.user.User;
import com.unitrust.timestamp3A.service.user.ResourcesService;
import com.unitrust.timestamp3A.service.user.UserService;

public class MyRealm extends AuthorizingRealm {
    Log log = LogFactory.getLog(MyRealm.class);

    @Autowired
    private UserService userService;

    @Autowired
    private ResourcesService resourcesService;

    private List<ResourceRoles> list;

    private Long sessionTime;

    public Long getSessionTime() {
        return sessionTime;
    }

    public void setSessionTime(Long sessionTime) {
        this.sessionTime = sessionTime;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        // 角色名的集合
        Set<String> roles = new HashSet<String>();
        for (ResourceRoles rr : list) {
            String url = rr.getUrl();
            roles.add(url);
        }
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRoles(roles);
        setSession("resourceRoles", list);
        return authorizationInfo;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        String username = ((UsernamePasswordToken) token).getUsername();
        String password = new String((char[]) token.getCredentials()); // 得到密码
        User user = userService.findUserByName(username);
        if (user == null) {
            throw new UnknownAccountException();
        }
        String status = user.getStatus();
        if (status.equals("0")) {
            throw new DisabledAccountException();
        }
        String isSuper = user.getIsSuper();
        Integer roleId = user.getRoleId();
        list = new ArrayList<ResourceRoles>();
        if (isSuper != null) {
            list = Common.getAllResources(roleId);
        } else {
            list = resourcesService.getUserResources(user.getUserId().toString());
        }
        setSession("resourceRoles", list);
        setSession("userId", user.getUserId());
        setSession("user", user.getUserName());
        setSession("userModel", user);
        SecurityUtils.getSubject().getSession().setTimeout(sessionTime);
        return new SimpleAuthenticationInfo(username, user.getPassword(), getName());
    }

    /**
     * 将一些数据放到ShiroSession中,以便于其它地方使用
     * 比如Controller,使用时直接用HttpSession.getAttribute(key)就可以取到
     */
    private void setSession(Object key, Object value) {
        Subject currentUser = SecurityUtils.getSubject();
        if (null != currentUser) {
            Session session = currentUser.getSession();
            if (null != session) {
                session.setAttribute(key, value);
            }
        }
    }
}
