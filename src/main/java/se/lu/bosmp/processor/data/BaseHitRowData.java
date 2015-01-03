package se.lu.bosmp.processor.data;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-21
 * Time: 00:07
 * To change this template use File | Settings | File Templates.
 */
public abstract class BaseHitRowData extends BaseRowData {

    private Integer attackerGameObjectId;
    private Integer targetGameObjectId;

    public BaseHitRowData(String row, Integer fileNameHash, long index, Integer attackerGameObjectId, Integer targetGameObjectId) {
         super(row, fileNameHash, index);

        this.attackerGameObjectId = attackerGameObjectId;
        this.targetGameObjectId = targetGameObjectId;
    }

    public Integer getAttackerGameObjectId() {
        return attackerGameObjectId;
    }

    public void setAttackerGameObjectId(Integer attackerGameObjectId) {
        this.attackerGameObjectId = attackerGameObjectId;
    }

    public Integer getTargetGameObjectId() {
        return targetGameObjectId;
    }

    public void setTargetGameObjectId(Integer targetGameObjectId) {
        this.targetGameObjectId = targetGameObjectId;
    }
}
