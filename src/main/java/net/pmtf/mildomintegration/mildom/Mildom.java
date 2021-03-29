package net.pmtf.mildomintegration.mildom;


import net.pmtf.mildomintegration.client.handler.ClientHandler;
import net.pmtf.mildomintegration.event.MildomEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Mildom {
    private static final Logger LOGGER = LogManager.getLogger(Mildom.class);
    private static boolean init;
    public static int viewers;

    public static void init() {
        if (init)
            return;
        init = true;
        LOGGER.info("MildomIntegration Initialized");
        MildomWSThread.init();
    }

    public static MildomEvent getEvent(String cmd, String rawMessage) {
        switch (cmd) {
            case "onChat":
                return new MildomEvent.MildomChatEvent(rawMessage);
            case "onGift":
                return new MildomEvent.MildomGiftEvent(rawMessage);
            case "onAdd":
                return new MildomEvent.MildomJoinEvent(rawMessage);
            case "enterRoom":
                return new MildomEvent.MildomEnterRoomEvent(rawMessage);
            case "onLiveStart":
                return new MildomEvent.MildomLiveStartEvent(rawMessage);
            case "onLiveEnd":
                return new MildomEvent.MildomLiveEndEvent(rawMessage);
            case "onUserCount":
                return new MildomEvent.MildomUserCountEvent(rawMessage);
        }
        return null;
    }

    public static boolean isShowGift() {
        return ClientHandler.gift;
    }
}
