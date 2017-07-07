package pers.zb.entity.sys.qo;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import pers.zb.common.util.enums.UserStatus;

@ApiModel(value = "UserQo", description = "用户信息请求类")
public class UserQo implements Serializable{
    
    private static final long serialVersionUID = -4815652578119718069L;
    
    @ApiModelProperty(value = "角色id")
    private Long roleId;// 角色id
    
    @ApiModelProperty(value = "姓名")
    private String realName;// 姓名
    
    @ApiModelProperty(value = "账户名")
    private String userName;// 账户名
    
    @ApiModelProperty(value = "状态")
    private UserStatus status;// 状态
    
    @ApiModelProperty(value = "用户id")
    private Long userId;
    
    @ApiModelProperty(value = "密码")
    private String password;// 密码
    
    @ApiModelProperty(value = "用户拥有的角色id集合")
    private Long[] roleIds;// 用户拥有的角色id集合

    @ApiModelProperty(value = "是否拥有查看用户列表权限")
    private boolean haveUserListPermission;// 是否拥有查看用户列表权限

    
    public boolean getHaveUserListPermission() {
        return haveUserListPermission;
    }

    public void setHaveUserListPermission(boolean haveUserListPermission) {
        this.haveUserListPermission = haveUserListPermission;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Long[] getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(Long[] roleIds) {
        this.roleIds = roleIds;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

}
