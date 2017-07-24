package pers.zb.ucenter.rpc.api.record;

import pers.zb.entity.record.VisitRecord;
import pers.zb.ucenter.rpc.api.BaseService;

public interface VisitRecordService extends BaseService<VisitRecord> {

    /**
     * 根据sessionId获取用户登录信息
     * @param sessionId
     * @return
     * @throws Exception
     */
    VisitRecord selectBySessionId(String sessionId) throws Exception;

    /**
     * 获取上次登录信息
     * @param valueOf
     * @return
     * @throws Exception
     */
    VisitRecord getLastLogin(String userName) throws Exception;

    /**
     * 获取用户某日登录次数
     * @param userName
     * @return
     * @throws Exception
     */
    Integer getCurUserDayLoginNum(String userName,String day) throws Exception;

}
