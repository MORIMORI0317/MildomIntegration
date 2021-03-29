package net.pmtf.mildomintegration.compat;

import net.minecraftforge.fml.common.Loader;

public class EmojicordCompat {
    public static boolean isLoaded() {
        return Loader.isModLoaded("emojicord");
    }

    public static String getPriceEmoji() {
        return "<:price:Cxnj82SKABQ=>";
    }

    public static String getEmojiString(long id) {
        return "<:_:" + id + ">";
    }

    public static String getNoImageEmoji() {
        return EmojicordCompat.getEmojiString(826070960087498853L);
    }
}
