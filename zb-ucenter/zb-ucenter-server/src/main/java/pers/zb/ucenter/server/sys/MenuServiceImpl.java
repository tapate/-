package pers.zb.ucenter.server.sys;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.zb.entity.sys.SysMenu;
import pers.zb.ucenter.dao.sys.MenuMapper;
import pers.zb.ucenter.rpc.api.sys.MenuService;
import pers.zb.ucenter.server.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;

@Service("menuServiceImpl")
public class MenuServiceImpl extends BaseServiceImpl<SysMenu> implements MenuService {

	@Autowired
	private MenuMapper menuMapper;

	@Override
	public List<SysMenu> getAllParentList() {
		Example example = new Example(SysMenu.class);
		example.createCriteria().andEqualTo("parentId", 0);
		return menuMapper.selectByExample(example);
	}

	@Override
	public List<SysMenu> getSubMenuByParentId(String id) {
		Example example = new Example(SysMenu.class);
		example.createCriteria().andEqualTo("parentId", id);
		example.setOrderByClause("sort ASC");//排序值升序排序
		return menuMapper.selectByExample(example);
	}
}
