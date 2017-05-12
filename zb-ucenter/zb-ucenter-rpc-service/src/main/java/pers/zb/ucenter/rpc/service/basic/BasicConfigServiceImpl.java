package pers.zb.ucenter.rpc.service.basic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.zb.entity.basic.BasicConfig;
import pers.zb.ucenter.dao.basic.BasicConfigMapper;
import pers.zb.ucenter.rpc.api.basic.BasicConfigService;
import pers.zb.ucenter.rpc.service.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;

@Service("basicConfigServiceImpl")
public class BasicConfigServiceImpl extends BaseServiceImpl<BasicConfig> implements BasicConfigService {

    @Autowired
    private BasicConfigMapper basicConfigMapper;

    @Override
    public BasicConfig getConfigByName(String configName) {
        Example example = new Example(BasicConfig.class);
        example.createCriteria().andEqualTo("configName", configName);
        List<BasicConfig>  list = basicConfigMapper.selectByExample(example);
        if(list != null && list.size() > 0){
            return list.get(0);
        }
        return null;
    }
}
