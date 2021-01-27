package net.morimori.mildomintegration.compat;

import net.minecraftforge.fml.common.Loader;

public class Emojicord {
    public static boolean isLoaded() {
        return Loader.isModLoaded("emojicord");
    }

    public static String getPriceEmoji() {
        return "<:price:Cxnj82SKABQ=>";
    }
}
