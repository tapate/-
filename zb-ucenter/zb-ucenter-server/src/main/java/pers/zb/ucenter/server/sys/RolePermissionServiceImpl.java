package pers.zb.ucenter.server.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import pers.zb.entity.sys.SysRole;
import pers.zb.entity.sys.SysRolePermission;
import pers.zb.ucenter.dao.sys.RolePermissionMapper;
import pers.zb.ucenter.rpc.api.sys.RolePermissionService;
import pers.zb.ucenter.server.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;


@Service("rolePermissionServiceImpl")
public class RolePermissionServiceImpl extends BaseServiceImpl<SysRolePermission> implements RolePermissionService {

    @Autowired
    private RolePermissionMapper rolePermissionMapper;

    @Override
    public List<SysRolePermission> getPermissionByRoleId(Long roleId) {
        Example example = new Example(SysRolePermission.class);
        example.createCriteria().andEqualTo("roleId", roleId);
        return rolePermissionMapper.selectByExample(example);
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void updateRolePermission(SysRole role, Long[] permissionIds) throws Exception {
        //删除角色权限
        deleteRolePermission(role);
        
        //设置角色权限
        for (int i = 0; i < permissionIds.length; i++) {
            SysRolePermission rolePermission = new SysRolePermission();
            rolePermission.setRoleId(role.getId());
            rolePermission.setPermissionId(permissionIds[i]);
            rolePermissionMapper.insert(rolePermission);
        }
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void deleteRolePermission(SysRole role) throws Exception {
        //删除角色权限
        Example example = new Example(SysRolePermission.class);
        example.createCriteria().andEqualTo("roleId",role.getId());
        rolePermissionMapper.deleteByExample(example);
    }

    @Override
    public boolean checkPermissionIsBindRole(Long pid) {
        Example example = new Example(SysRolePermission.class);
        example.createCriteria().andEqualTo("permissionId",pid);
        List<SysRolePermission> ls = rolePermissionMapper.selectByExample(example);
        return (ls ==null || ls.size() <= 0) ? false : true;
    }
}
