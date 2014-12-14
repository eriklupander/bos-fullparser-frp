package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

/**
 T:16845 AType:2 DMG:0.005 AID:-1 TID:163839 POS(132083.875,3975.702,196872.219)
 */
public class AType2RowData extends BaseRowData {

    private Integer attackerGameObjectId;
    private Integer targetGameObjectId;
    private Float damage;

    public AType2RowData(String row, long index) {
        super(row, index);
        this.attackerGameObjectId = RowElementParser.parseNumber(row, "AID:");
        this.targetGameObjectId = RowElementParser.parseNumber(row, "TID:");
        this.damage = RowElementParser.parseFloat(row, "DMG:");
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

    public Float getDamage() {
        return damage;
    }

    public void setDamage(Float damage) {
        this.damage = damage;
    }

    @Override
    public String toString() {
        return "AType2RowData{" +
                "attackerGameObjectId=" + attackerGameObjectId +
                ", targetGameObjectId=" + targetGameObjectId +
                ", damage=" + damage +
                '}';
    }
}
