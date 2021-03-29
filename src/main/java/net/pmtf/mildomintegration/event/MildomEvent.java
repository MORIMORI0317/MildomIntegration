package net.pmtf.mildomintegration.event;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.pmtf.mildomintegration.util.MildomUtil;

public class MildomEvent extends Event {
    private static final Gson gson = new Gson();

    private final String message;

    public MildomEvent(String rawMessage) {
        this.message = rawMessage;
    }

    public String getMessage() {
        return message;
    }

    public JsonObject getMessageJson() {
        return gson.fromJson(message, JsonObject.class);
    }

    public String getCmd() {
        return getMessageJson().get("cmd").getAsString();
    }

    public int getMessageUserLevel() {
        return getMessageJson().get("level").getAsInt();
    }

    public String getUser() {
        return getMessageJson().get("userName").getAsString();
    }

    public ITextComponent getMildomUserNameAndLevel() {
        StringBuilder cmc = new StringBuilder();
        int lv = getMessageUserLevel();
        cmc.append("(").append("Lv").append(lv).append(") ").append(getUser());
        return new TextComponentString(cmc.toString()).setStyle(new Style().setColor(MildomUtil.getTextFormatCloerByLevel(lv)));
    }

    public static class MildomChatEvent extends MildomEvent {
        public MildomChatEvent(String rawMessage) {
            super(rawMessage);
        }

        public String getChat() {
            return getMessageJson().get("msg").getAsString();
        }

        public boolean isAdmin() {
            return getMessageJson().get("roomAdmin").getAsInt() == 1;
        }

        @Override
        public ITextComponent getMildomUserNameAndLevel() {
            StringBuilder cmc = new StringBuilder();
            int lv = getMessageUserLevel();
            if (isAdmin()) {
                cmc.append("[" + I18n.format("message.admin") + "] ");
            }
            cmc.append("(").append("Lv").append(lv).append(") ").append(getUser());
            return new TextComponentString(cmc.toString()).setStyle(new Style().setColor(MildomUtil.getTextFormatCloerByLevel(lv)));
        }
    }

    public static class MildomGiftEvent extends MildomEvent {

        public MildomGiftEvent(String rawMessage) {
            super(rawMessage);
        }

        public int getGift() {
            return getMessageJson().get("giftId").getAsInt();
        }

        public int getCont() {
            return getMessageJson().get("count").getAsInt();
        }
    }

    public static class MildomJoinEvent extends MildomEvent {
        public MildomJoinEvent(String rawMessage) {
            super(rawMessage);

        }

        public int getViewers() {
            return getMessageJson().get("userCount").getAsInt();
        }
    }

    public static class MildomEnterRoomEvent extends MildomEvent {

        public MildomEnterRoomEvent(String rawMessage) {
            super(rawMessage);
        }

        public int getViewers() {
            return getMessageJson().get("userCount").getAsInt();
        }
    }

    public static class MildomLiveStartEvent extends MildomEvent {
        public MildomLiveStartEvent(String rawMessage) {
            super(rawMessage);
        }
    }

    public static class MildomLiveEndEvent extends MildomEvent {
        public MildomLiveEndEvent(String rawMessage) {
            super(rawMessage);
        }
    }

    public static class MildomUserCountEvent extends MildomEvent {
        public MildomUserCountEvent(String rawMessage) {
            super(rawMessage);
        }

        public int getViewers() {
            return getMessageJson().get("userCount").getAsInt();
        }
    }
}