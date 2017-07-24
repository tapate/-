package pers.zb.ucenter.rpc.service.record;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.zb.entity.record.VisitRecord;
import pers.zb.ucenter.dao.record.VisitRecordMapper;
import pers.zb.ucenter.rpc.api.record.VisitRecordService;
import pers.zb.ucenter.rpc.service.BaseServiceImpl;
import tk.mybatis.mapper.entity.Example;

@Service("visitRecordServiceImpl")
public class VisitRecordServiceImpl extends BaseServiceImpl<VisitRecord> implements VisitRecordService {

    @Autowired
    private VisitRecordMapper visitRecordMapper;

    @Override
    public VisitRecord selectBySessionId(String sessionId) throws Exception {
        Example example = new Example(VisitRecord.class);
        example.createCriteria().andEqualTo("reqSessionId", sessionId);
        List<VisitRecord> list = visitRecordMapper.selectByExample(example);
        return (list == null || list.size() <= 0) ? null : list.get(0);
    }

    @Override
    public VisitRecord getLastLogin(String userName) throws Exception {
        return visitRecordMapper.getLastLogin(userName);
    }

    @Override
    public Integer getCurUserDayLoginNum(String userName,String day) throws Exception {
        Integer curUserTodayLoginNum = visitRecordMapper.getCurUserDayLoginNum(userName,day);
        return curUserTodayLoginNum;
    }

    
}
