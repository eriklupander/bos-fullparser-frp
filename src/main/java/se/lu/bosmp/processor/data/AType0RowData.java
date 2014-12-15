package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

import java.util.ArrayList;
import java.util.List;

/**
 T:0 AType:0 GDate:1942.12.11 GTime:11:45:0 MFile:missions/veteranenmissions/chir_front/Chir_Front_2.msnbin MID: GType:0 CNTRS:0:0,50:0,101:1,201:2 SETTS:1111000101101001000000011 MODS:0 PRESET:0 AQMID:0
 */
public class AType0RowData extends BaseRowData {

    private String gameDate;
    private String gameTime;
    private String missionFile;

    private List<String> countries = new ArrayList<>();

    public AType0RowData(String row, Integer fileNameHash, long index) {
        super(row, fileNameHash, index);
        this.gameDate = RowElementParser.parseGameDate(row);
        this.gameTime = RowElementParser.parseGameTime(row);
        this.missionFile = RowElementParser.parseMissionFile(row);
    }

    public String getGameDate() {
        return gameDate;
    }

    public void setGameDate(String gameDate) {
        this.gameDate = gameDate;
    }

    public String getGameTime() {
        return gameTime;
    }

    public void setGameTime(String gameTime) {
        this.gameTime = gameTime;
    }

    public String getMissionFile() {
        return missionFile;
    }

    public void setMissionFile(String missionFile) {
        this.missionFile = missionFile;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    @Override
    public String toString() {
        return "AType0RowData{" +
                "gameDate='" + gameDate + '\'' +
                ", gameTime='" + gameTime + '\'' +
                ", missionFile='" + missionFile + '\'' +
                ", countries=" + countries +
                '}';
    }
}
