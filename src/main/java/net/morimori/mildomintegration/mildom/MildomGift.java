package net.morimori.mildomintegration.mildom;

import net.morimori.mildomintegration.compat.Emojicord;

public class MildomGift {
    public static String getGiftNameByID(int id) {
        return Emojicord.isLoaded() ? Gift.getGiftByGiftID(id).getEmojiStr() : "§l§6" + Gift.getGiftByGiftID(id).getGiftStack().getDisplayName() + "§r";
    }
}
