package pers.zb.ucenter.dao.sys;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import pers.zb.common.util.Pager;
import pers.zb.entity.sys.SysPermission;
import pers.zb.entity.sys.vo.PermissionVo;
import tk.mybatis.mapper.common.Mapper;

public interface PermissionMapper extends Mapper<SysPermission> {

    /**
     * 获取用户权限列表
     * 
     * 日期：2016年8月14日 下午7:44:32
     * 用户：zhoubang
     * 
     * @param userName
     * @return
     */
    List<SysPermission> getUserPermissions(@Param("userName") String userName);

    /**
     * 分页获取权限列表
     * 
     * 日期：2016年8月14日 下午7:44:39
     * 用户：zhoubang
     * 
     * @param permission
     * @param pager
     * @return
     */
    List<PermissionVo> getList(@Param("permission") SysPermission permission, @Param("pager") Pager<PermissionVo> pager);

}
