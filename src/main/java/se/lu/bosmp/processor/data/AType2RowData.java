package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

/**
 T:16845 AType:2 DMG:0.005 AID:-1 TID:163839 POS(132083.875,3975.702,196872.219)
 */
public class AType2RowData extends BaseHitRowData {


    private Float damage;

    public AType2RowData(String row, Integer fileNameHash, long index) {
        super(row, fileNameHash, index, RowElementParser.parseNumber(row, "AID:"), RowElementParser.parseNumber(row, "TID:"));
        this.damage = RowElementParser.parseFloat(row, "DMG:");
    }

    public Float getDamage() {
        return damage;
    }

    public void setDamage(Float damage) {
        this.damage = damage;
    }
}
