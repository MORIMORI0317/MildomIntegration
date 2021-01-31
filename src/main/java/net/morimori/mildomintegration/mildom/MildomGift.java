package net.morimori.mildomintegration.mildom;

import net.morimori.mildomintegration.compat.Emojicord;
import net.morimori.mildomintegration.mildom.gift.Gift2;

public class MildomGift {
    public static String getGiftNameByID(int id) {
        return Emojicord.isLoaded() ? Gift2.getGiftByGiftID(id).getEmojiStr() : "§l§6" + Gift2.getGiftByGiftID(id).getGiftStack().getDisplayName() + "§r";
    }
}
