package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

/**
 T:16845 AType:2 DMG:0.005 AID:-1 TID:163839 POS(132083.875,3975.702,196872.219)
 */
public class AType1RowData extends BaseHitRowData {


    private String ammunition;

    public AType1RowData(String row, Integer fileNameHash, long index) {
        super(row, fileNameHash, index, RowElementParser.parseNumber(row, "AID:"), RowElementParser.parseNumber(row, "TID:"));
        this.ammunition = RowElementParser.parseString(row, "AMMO:");
    }

    public String getAmmunition() {
        return ammunition;
    }

    public void setAmmunition(String ammunition) {
        this.ammunition = ammunition;
    }
}
