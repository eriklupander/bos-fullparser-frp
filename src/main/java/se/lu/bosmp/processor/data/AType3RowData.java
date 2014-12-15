package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

/**
 T:51293 AType:3 AID:1644543 TID:1608703 POS(109652.055,46.206,145036.609)
 */
public class AType3RowData extends BaseRowData {

    private Integer attackerGameObjectId;
    private Integer targetGameObjectId;

    public AType3RowData(String row, Integer fileNameHash, long index) {
        super(row, fileNameHash, index);
        this.attackerGameObjectId = RowElementParser.parseNumber(row, "AID:");
        this.targetGameObjectId = RowElementParser.parseNumber(row, "TID:");
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

    @Override
    public String toString() {
        return "AType3RowData{" +
                "attackerGameObjectId=" + attackerGameObjectId +
                ", targetGameObjectId=" + targetGameObjectId +
                '}';
    }
}
