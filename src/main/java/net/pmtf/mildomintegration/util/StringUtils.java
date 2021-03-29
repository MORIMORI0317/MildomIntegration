package net.pmtf.mildomintegration.util;

public class StringUtils {
    public static String jsonBuilder(String inJson) {
        return inJson;
        //  return new GsonBuilder().serializeNulls().setPrettyPrinting().create().toJson(JsonParser.parseString(inJson));
    }
}
