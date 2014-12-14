package se.lu.bosmp.controller;

/**
 * JVM singleton that keeps Parser state. Example: Knows which is the current ongoing mission it is parsing.
 */
public class ParserContext {

    public static Long missionId;
    public static Long missionInstanceId;
}
