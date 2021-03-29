package net.pmtf.mildomintegration.mildom;

import com.google.gson.JsonObject;
import net.pmtf.mildomintegration.compat.EmojicordCompat;
import net.pmtf.mildomintegration.util.URLUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.StreamSupport;

public class OfficialEmotions {
    public static final String OFFICIALEMOJI_DATA_URL = "https://raw.githubusercontent.com/MORIMORI0317/MildomIntegration/main/officialemoji.json";
    public static final String OFFICIALEMOJI_LIST_URL = "https://cloudac.mildom.com/nonolive/gappserv/emotion/getListV1?__platform=web&room_id=114514";
    public static final Map<String, String> OFF_EMOZIS = new HashMap<>();

    public static void init() {
        if (!EmojicordCompat.isLoaded())
            return;
        try {
            Map<String, Long> ghOEs = new HashMap<>();
            JsonObject ghjO = URLUtils.getURLJsonResponse(OFFICIALEMOJI_DATA_URL);
            StreamSupport.stream(ghjO.getAsJsonArray("official_emotions").spliterator(), false).forEach(n -> {
                JsonObject jo = n.getAsJsonObject();
                ghOEs.put(jo.get("name").getAsString(), jo.get("emoji_id").getAsLong());
            });

            JsonObject jO = URLUtils.getURLJsonResponse(OFFICIALEMOJI_LIST_URL);
            StreamSupport.stream(jO.getAsJsonObject("body").getAsJsonArray("official_emotions").spliterator(), false).forEach(n -> {
                JsonObject jo = n.getAsJsonObject();
                String rped = "[/" + jo.get("id").getAsInt() + "]";
                if (ghOEs.containsKey(jo.get("pic").getAsString())) {
                    long ei = ghOEs.get(jo.get("pic").getAsString());
                    OFF_EMOZIS.put(rped, EmojicordCompat.getEmojiString(ei));
                } else {
                    OFF_EMOZIS.put(rped, EmojicordCompat.getNoImageEmoji());
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static String convertString(String mozi) {
        if (EmojicordCompat.isLoaded()) {
            for (Map.Entry<String, String> en : OFF_EMOZIS.entrySet()) {
                mozi = mozi.replace(en.getKey(), en.getValue());
            }
        }
        return mozi;
    }
}
