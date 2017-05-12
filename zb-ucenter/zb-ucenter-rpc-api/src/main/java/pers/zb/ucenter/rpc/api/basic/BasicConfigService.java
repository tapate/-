package pers.zb.ucenter.rpc.api.basic;

import pers.zb.entity.basic.BasicConfig;
import pers.zb.ucenter.rpc.api.BaseService;

public interface BasicConfigService extends BaseService<BasicConfig> {
    
    /**
     * 根据配置名称获取值
     * @description 
     * 
     * @author zhoubang 
     * @date 2017年3月20日 下午3:00:20 
     * 
     * @param configName
     * @return
     */
    public BasicConfig getConfigByName(String configName);
}
