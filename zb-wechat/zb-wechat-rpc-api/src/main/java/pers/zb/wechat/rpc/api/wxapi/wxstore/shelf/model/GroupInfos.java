package pers.zb.wechat.rpc.api.wxapi.wxstore.shelf.model;

import java.util.List;

public class GroupInfos {
    // 分组ID
    private List<GroupsInfo> groups;

    public List<GroupsInfo> getGroups() {
        return groups;
    }

    public void setGroups(List<GroupsInfo> groups) {
        this.groups = groups;
    }
}
