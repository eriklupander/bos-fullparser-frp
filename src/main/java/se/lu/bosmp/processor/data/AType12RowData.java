package se.lu.bosmp.processor.data;

import se.lu.bosmp.processor.RowElementParser;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 23:06
 * To change this template use File | Settings | File Templates.
 */
public class AType12RowData extends BaseRowData {

    private Integer gameObjectId;
    private String type;
    private Integer country;
    private String name;
    private Integer parentId;

    public AType12RowData(String row, Integer fileNameHash, long index) {
        super(row, fileNameHash, index);

        this.gameObjectId = RowElementParser.parseNumber(row, "ID:");
        this.type = RowElementParser.parseSpacedWord(row, "TYPE:");
        this.country = RowElementParser.parseCountry(row);
        this.name = RowElementParser.parseName(row);
        this.parentId = RowElementParser.parseParentId(row);
    }

    public Integer getGameObjectId() {
        return gameObjectId;
    }

    public void setGameObjectId(Integer gameObjectId) {
        this.gameObjectId = gameObjectId;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Override
    public String toString() {
        return "AType12RowData{" +
                "gameObjectId=" + gameObjectId +
                ", type='" + type + '\'' +
                ", country=" + country +
                ", name='" + name + '\'' +
                ", parentId=" + parentId +
                '}';
    }
}
