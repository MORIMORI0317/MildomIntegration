package net.morimori.mildomintegration.mildom;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextFormatting;
import net.morimori.mildomintegration.compat.Emojicord;

public class MildomUtilty {
    private static final Gson gson = new Gson();

    public static JsonObject getMessageJson(String str) {
        return gson.fromJson(str, JsonObject.class);
    }

    public static CmdType getMessageCmd(String str) {
        return CmdType.getCmdTypeByName(getMessageJson(str).get("cmd").getAsString());
    }

    public static String getMessageChat(String str) {
        CmdType type = getMessageCmd(str);
        if (type == CmdType.CHAT) {
            return getMessageJson(str).get("msg").getAsString();
        }
        return "";
    }

    public static String getCovMessageChat(String message) {
        String str = getMessageChat(message);

        if (Emojicord.isLoaded()) {
            for (Nanika n : Nanika.values()) {
                str = str.replace(n.getStr(), n.getEmojiStr());
            }
        }

        return str;
    }

    public static String getMessageJoinUser(String str) {
        CmdType type = getMessageCmd(str);
        if (type == CmdType.ADD) {
            return getMessageJson(str).get("userName").getAsString();
        }
        return "";
    }

    public static int getMessageGiftID(String str) {
        CmdType type = getMessageCmd(str);
        if (type == CmdType.GIFT) {
            return getMessageJson(str).get("giftId").getAsInt();
        }
        return 0;
    }

    public static int getMessageGiftCount(String str) {
        CmdType type = getMessageCmd(str);
        if (type == CmdType.GIFT) {
            return getMessageJson(str).get("count").getAsInt();
        }
        return 0;
    }

    public static String getMessageUserName(String str) {
        return getMessageJson(str).get("userName").getAsString();
    }

    public static int getMessageUserLevel(String str) {
        return getMessageJson(str).get("level").getAsInt();
    }

    public static TextFormatting getTextFormatCloerByLevel(int lv) {

        if (lv >= 1 && lv <= 4)
            return TextFormatting.GRAY;
        else if (lv > 4 && lv <= 36)
            return TextFormatting.BLUE;
        else if (lv > 36 && lv <= 61)
            return TextFormatting.GREEN;
        else if (lv > 61 && lv <= 81)
            return TextFormatting.AQUA;
        else if (lv > 81 && lv <= 101)
            return TextFormatting.LIGHT_PURPLE;
        else if (lv > 101 && lv <= 116)
            return TextFormatting.RED;
        else if (lv > 116 && lv <= 131)
            return TextFormatting.YELLOW;
        else if (lv > 131 && lv <= 146)
            return TextFormatting.GOLD;
        else if (lv > 146 && lv <= 151)
            return TextFormatting.DARK_PURPLE;

        return TextFormatting.BLACK;
    }

    public static ITextComponent getMildomUserNameAndLevel(String message) {
        StringBuilder cmc = new StringBuilder();
        int lv = MildomUtilty.getMessageUserLevel(message);
        cmc.append("(").append("Lv").append(lv).append(") ").append(MildomUtilty.getMessageUserName(message));
        return new TextComponentString(cmc.toString()).setStyle(new Style().setColor(MildomUtilty.getTextFormatCloerByLevel(lv)));
    }

    public static enum CmdType {
        NONE("none"),
        ACTIVITY("onActivity"),
        CHAT("onChat"),
        GIFT("onGift"),
        ADD("onAdd");
        private final String name;

        private CmdType(String cmdStr) {
            this.name = cmdStr;
        }


        public String getNmae() {
            return name;
        }

        public static CmdType getCmdTypeByName(String name) {
            for (CmdType it : values()) {
                if (it.getNmae().equals(name))
                    return it;
            }
            return NONE;
        }
    }
}
