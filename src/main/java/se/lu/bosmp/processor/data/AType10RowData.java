package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

/**
 T:5 AType:10 PLID:1865727 PID:1866751 BUL:1200 SH:0 BOMB:0 RCT:0 (95937.852,86.036,50399.012) IDS:a8a19327-93a5-492a-8066-24f32ae0e044 LOGIN:0551fc36-cc61-45ed-9be1-8b393c3abcc7 NAME:Lupson TYPE:Bf 109 G-2 COUNTRY:201 FORM:1 FIELD:608255 INAIR:1 PARENT:-1 PAYLOAD:0 FUEL:0.700 SKIN: WM:1

 */
public class AType10RowData extends BaseRowData {

    private Integer planeGameObjectId;
    private Integer pilotGameObjectId;

    private Integer bullets;
    private String pilotName;

    private String type;
    private Integer country;

    public AType10RowData(String row, Integer fileNameHash, long index) {
        super(row, fileNameHash, index);

        this.planeGameObjectId = RowElementParser.parseNumber(row, "PLID:");
        this.pilotGameObjectId = RowElementParser.parseNumber(row, "PID:");

        this.bullets = RowElementParser.parseNumber(row, "BUL:");

        this.type = RowElementParser.parseSpacedWord(row, "TYPE:");
        this.country = RowElementParser.parseCountry(row);
        this.pilotName = RowElementParser.parseName(row);
    }

    public Integer getPlaneGameObjectId() {
        return planeGameObjectId;
    }

    public void setPlaneGameObjectId(Integer planeGameObjectId) {
        this.planeGameObjectId = planeGameObjectId;
    }

    public Integer getPilotGameObjectId() {
        return pilotGameObjectId;
    }

    public void setPilotGameObjectId(Integer pilotGameObjectId) {
        this.pilotGameObjectId = pilotGameObjectId;
    }

    public Integer getBullets() {
        return bullets;
    }

    public void setBullets(Integer bullets) {
        this.bullets = bullets;
    }

    public String getPilotName() {
        return pilotName;
    }

    public void setPilotName(String pilotName) {
        this.pilotName = pilotName;
    }



    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCountry() {
        return country;
    }

    public void setCountry(Integer country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "AType10RowData{" +
                "planeGameObjectId=" + planeGameObjectId +
                ", pilotGameObjectId=" + pilotGameObjectId +
                ", bullets=" + bullets +
                ", pilotName='" + pilotName + '\'' +
                ", type='" + type + '\'' +
                ", country=" + country +
                '}';
    }
}
