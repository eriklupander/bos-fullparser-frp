package se.lu.bosmp.model;

import javax.persistence.ElementCollection;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 22:36
 * To change this template use File | Settings | File Templates.
 */
public class MissionInstanceObjectGroup extends BaseEntity {

    private Integer leaderGameObjectId;
    private List<Integer> memberGameObjectIds = new ArrayList<>();

    public Integer getLeaderGameObjectId() {
        return leaderGameObjectId;
    }

    public void setLeaderGameObjectId(Integer leaderGameObjectId) {
        this.leaderGameObjectId = leaderGameObjectId;
    }

    @ElementCollection
    public List<Integer> getMemberGameObjectIds() {
        return memberGameObjectIds;
    }

    public void setMemberGameObjectIds(List<Integer> memberGameObjectIds) {
        this.memberGameObjectIds = memberGameObjectIds;
    }
}
