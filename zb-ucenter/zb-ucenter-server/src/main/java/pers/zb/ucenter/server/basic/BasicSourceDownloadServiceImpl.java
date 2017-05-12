package pers.zb.ucenter.server.basic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pers.zb.entity.basic.BasicSourceDownload;
import pers.zb.ucenter.dao.basic.BasicSourceDownloadMapper;
import pers.zb.ucenter.rpc.api.basic.BasicSourceDownloadService;
import pers.zb.ucenter.server.BaseServiceImpl;

@Service("basicSourceDownloadServiceImpl")
public class BasicSourceDownloadServiceImpl extends BaseServiceImpl<BasicSourceDownload> implements BasicSourceDownloadService {

    @Autowired
    private BasicSourceDownloadMapper basicSourceDownloadMapper;
}
