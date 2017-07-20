package pers.zb.ucenter.rpc.service.sys;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pers.zb.common.util.AjaxResult;
import pers.zb.common.util.Pager;
import pers.zb.entity.sys.SysPermission;
import pers.zb.entity.sys.vo.PermissionVo;
import pers.zb.entity.vo.ZtreeVo;
import pers.zb.ucenter.dao.sys.PermissionMapper;
import pers.zb.ucenter.rpc.api.sys.PermissionService;
import pers.zb.ucenter.rpc.api.sys.RolePermissionService;
import pers.zb.ucenter.rpc.service.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;

@Service("permissionServiceImpl")
public class PermissionServiceImpl extends BaseServiceImpl<SysPermission> implements PermissionService {

    @Autowired
    private PermissionMapper permissionMapper;
    
    @Autowired
    private RolePermissionService rolePermissionService;

    @Override
    public List<SysPermission> getUserPermissions(String userName) {
        return permissionMapper.getUserPermissions(userName);
    }

    @Override
    public List<ZtreeVo> queryAllFormatWithZtree(boolean isShowTopParent) {
        List<ZtreeVo> results = new ArrayList<ZtreeVo>();
        if(isShowTopParent){
            ZtreeVo result = new ZtreeVo();
            result.setId("-1");
            result.setpId("0");
            result.setName("无上级权限");
            results.add(result);
        }
        List<SysPermission> permissions = permissionMapper.selectAll();
        if (CollectionUtils.isNotEmpty(permissions)) {
            for (SysPermission pms : permissions) {
                ZtreeVo foo = new ZtreeVo();
                foo.setId(String.valueOf(pms.getId()));
                foo.setpId(String.valueOf(pms.getParentId() == null ? "" : pms.getParentId()));
                foo.setName(pms.getName() + " - " + pms.getCode());
                results.add(foo);
            }
        }
        return results;
    }

    @Override
    public Pager<PermissionVo> getList(Pager<PermissionVo> pager, SysPermission permission) {
        if(pager.getUsePager()){
            PageHelper.offsetPage(pager.getOffset(), pager.getLimit());
        }
        List<PermissionVo> vos=permissionMapper.getList(permission,pager);
        pager.setRows(vos);
        PageInfo<PermissionVo> pageInfo=new PageInfo<PermissionVo>(vos);
        pager.setTotal(pageInfo.getTotal());
        return pager;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void updatePermission(SysPermission permission) throws Exception {
        permissionMapper.updateByPrimaryKeySelective(permission);
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public AjaxResult<String> deletePermission(Long permissionId) throws Exception {
        AjaxResult<String> result = new AjaxResult<String>();
        if(null == permissionId || "".equals(permissionId)){
            result.setCode(10001);
            result.setMsg("请选择一个权限删除");
            return result;
        }
        
        SysPermission permission = permissionMapper.selectByPrimaryKey(permissionId);
        if(permission == null){
            result.setCode(10002);
            result.setMsg("该权限不存在");
            return result;
        }
        
        //检查该权限是否被分配给角色
        boolean bool = rolePermissionService.checkPermissionIsBindRole(permission.getId());
        if(bool){
            result.setCode(10005);
            result.setMsg("该权限已被其他角色所绑定使用，不能删除");
            return result;
        }
        
        //删除权限
        permissionMapper.delete(permission);
        return result;
    }

    @Override
    public SysPermission selectByCode(String code) throws Exception {
        Example example = new Example(SysPermission.class);
        example.createCriteria().andEqualTo("code", code);
        List<SysPermission> pList = permissionMapper.selectByExample(example);
        return (pList == null || pList.size() <= 0) ? null : pList.get(0);
    }
}
