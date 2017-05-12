package pers.zb.ucenter.rpc.service.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.zb.entity.sys.SysUser;
import pers.zb.entity.sys.SysUserRole;
import pers.zb.ucenter.dao.sys.UserRoleMapper;
import pers.zb.ucenter.rpc.api.sys.UserRoleService;
import pers.zb.ucenter.rpc.service.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;


@Service("userRoleServiceImpl")
public class UserRoleServiceImpl extends BaseServiceImpl<SysUserRole> implements UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void deleteByUserId(Long userId) throws Exception {
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("userId", userId);
        userRoleMapper.deleteByExample(example);
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void updateUserRole(SysUser user, Long[] roleIds) throws Exception {
        //先删除用户角色
        deleteByUserId(user.getId());
        
        //设置用户新角色
        for (int i = 0; i < roleIds.length; i++) {
            SysUserRole userRole = new SysUserRole();
            userRole.setUserId(user.getId());
            userRole.setRoleId(roleIds[i]);
            userRoleMapper.insert(userRole);
        }
    }

    @Override
    public boolean checkRoleIsBindUser(Long roleId) {
        Example example = new Example(SysUserRole.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        List<SysUserRole> ls = userRoleMapper.selectByExample(example);
        return (ls == null || ls.size() <= 0) ? false : true;
    }
}
