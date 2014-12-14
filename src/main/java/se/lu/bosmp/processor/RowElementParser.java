package se.lu.bosmp.processor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: Erik
 * Date: 2014-12-09
 * Time: 22:06
 * To change this template use File | Settings | File Templates.
 */
public class RowElementParser {

    private static final Pattern timePattern = Pattern.compile("(T:?)(\\d+)\\s");
    private static final Pattern aTypePattern = Pattern.compile("\\s(AType:?)(\\d+)\\s");

    private static final Pattern gameDatePattern = Pattern.compile("(GDate:?)(\\S+)");
    private static final Pattern gameTimePattern = Pattern.compile("(GTime:?)(\\S+)");
    private static final Pattern missionFilePattern = Pattern.compile("(MFile:?)(\\S+)");


    public static Integer parseTime(String row) {
        Matcher m = timePattern.matcher(row);
        if(m.find()) {
            return Integer.parseInt(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse time from row '" + row + "'");
    }

    public static Integer parseTypeCode(String row) {
        Matcher m = aTypePattern.matcher(row);
        if(m.find()) {
            return Integer.parseInt(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse AType from row '" + row + "'");
    }

    public static Integer parseCountry(String row) {
        return parseNumber(row, "COUNTRY:");
    }
    public static Integer parseParentId(String row) {
        return parseNumber(row, "PID:");
    }
    public static Integer parseId(String row) {
        Matcher m = timePattern.matcher(row);
        if(m.find()) {
            return Integer.parseInt(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse ID from row '" + row + "'");
    }

    public static String parseGameDate(String row) {
        Matcher m = gameDatePattern.matcher(row);
        if(m.find()) {
            return m.group(2);
        }
        throw new IllegalArgumentException("Could not parse gameDate from row '" + row + "'");
    }

    public static String parseGameTime(String row) {
        Matcher m = gameTimePattern.matcher(row);
        if(m.find()) {
            return m.group(2);
        }
        throw new IllegalArgumentException("Could not parse gameTime from row '" + row + "'");
    }


    public static String parseMissionFile(String row) {
        Matcher m = missionFilePattern.matcher(row);
        if(m.find()) {
            return m.group(2);
        }
        throw new IllegalArgumentException("Could not parse gameTime from row '" + row + "'");

    }



    public static String parseSpacedWord(String row, String key) {
        //return parseString(row, "TYPE:");
        int startingIndex = row.indexOf(key) + key.length();
        String tmp = row.substring(startingIndex);
        int nextColon = tmp.indexOf(":");
        if(nextColon > -1) {
            // From the next colon, back one char at a time until the first whitespace
           // char c = tmp.charAt(nextColon);

            do {
                char c  = tmp.charAt(nextColon);
                if(c == ' ') {
                    return tmp.substring(0, nextColon).trim();
                }
                nextColon--;
            } while(true);
        } else {
            return tmp.trim();
        }
    }



    public static String parseName(String row) {
        return parseString(row, "NAME:");
    }

    public static String parseString(String row, String key) {
        Pattern p = Pattern.compile("("+key+":?)(\\S+)");
        Matcher m = p.matcher(row);
        if(m.find()) {
            return m.group(2);
        }
        throw new IllegalArgumentException("Could not parse key '" + key + "' from row '" + row + "'");
    }

    public static Integer parseNumber(String row, String key) {
        Pattern p = Pattern.compile("("+key+":?)(\\S+)");
        Matcher m = p.matcher(row);
        if(m.find()) {
            return Integer.parseInt(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse key '" + key + "' from row '" + row + "'");
    }

    public static Float parseFloat(String row, String key) {
        Pattern p = Pattern.compile("("+key+":?)(\\S+)");
        Matcher m = p.matcher(row);
        if(m.find()) {
            return Float.parseFloat(m.group(2));
        }
        throw new IllegalArgumentException("Could not parse key '" + key + "' from row '" + row + "'");

    }
}
