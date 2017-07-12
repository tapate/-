package pers.zb.ucenter.rpc.service.user;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import pers.zb.common.util.Pager;
import pers.zb.common.util.enums.FromSystemEnum;
import pers.zb.common.util.util.CryptoUtils;
import pers.zb.entity.sys.SysUser;
import pers.zb.entity.sys.qo.UserQo;
import pers.zb.entity.sys.vo.UserVo;
import pers.zb.ucenter.dao.sys.UserMapper;
import pers.zb.ucenter.rpc.api.sys.UserRoleService;
import pers.zb.ucenter.rpc.api.user.UserService;
import pers.zb.ucenter.rpc.service.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;

@Service("userServiceImpl")
public class UserServiceImpl extends BaseServiceImpl<SysUser> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleService userRoleService;
    
    @Override
    public SysUser getUserByName(String username) {
        List<SysUser> list = userMapper.getUserByName(username);
        return (list == null || 0 == list.size()) ? null : list.get(0);
    }

    @Override
    public Pager<UserVo> getList(Pager<UserVo> pager, UserQo userQo) {
        if (pager.getUsePager()) {
            PageHelper.offsetPage(pager.getOffset(), pager.getLimit());
        }
        
        List<UserVo> vos = userMapper.getUserList(userQo, pager);
        pager.setRows(vos);
        PageInfo<UserVo> pageInfo = new PageInfo<UserVo>(vos);
        pager.setTotal(pageInfo.getTotal());
        return pager;
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void deleteUser(SysUser user) throws Exception{
        //删除用户
        userMapper.delete(user);
        
        //删除用户对于的角色
        userRoleService.deleteByUserId(user.getId());
    }

    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void updateUser(SysUser user, UserQo qo) throws Exception{
        //更新用户
        user.setRealName(qo.getRealName());
        user.setStatus(qo.getStatus());
        user.setUpdateTime(new Date());
        user.setUserName(qo.getUserName());
        userMapper.updateByPrimaryKey(user);
        
        //更新用户角色列表
        userRoleService.updateUserRole(user,qo.getRoleIds());
    }

    
    @Transactional(propagation=Propagation.REQUIRED,rollbackFor=Exception.class)
    @Override
    public void saveUser(UserQo qo) throws Exception {
        //新增用户
        SysUser user = new SysUser();
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());
        user.setRealName(qo.getRealName());
        user.setUserName(qo.getUserName());
        user.setStatus(qo.getStatus());
        user.setPassword(CryptoUtils.encodeMD5(qo.getPassword()));
        user.setFromSystem(FromSystemEnum.DEFAULT_CURRENT_USER_CENTER);
        userMapper.insert(user);
        
        //保存角色
        userRoleService.updateUserRole(user, qo.getRoleIds());
    }

    @Override
    public void deleteUsers(Long[] userIdArr) throws Exception {
        if(userIdArr != null && userIdArr.length > 0){
            for (int i = 0; i < userIdArr.length; i++) {
                //删除用户
                Example example = new Example(SysUser.class);
                example.createCriteria().andIn("id", Arrays.asList(userIdArr));
                userMapper.deleteByExample(example);
                
                //删除用户对应的角色信息
                userRoleService.deleteByUserId(userIdArr[i]);
            }
        }
    }
}
