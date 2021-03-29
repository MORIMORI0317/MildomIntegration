package net.pmtf.mildomintegration.handler;

import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.pmtf.mildomintegration.event.MildomEvent;
import net.pmtf.mildomintegration.mildom.Mildom;

public class ServerHandler {
    @SubscribeEvent
    public static void onMildomJoin(MildomEvent.MildomJoinEvent e) {
        Mildom.viewers = e.getViewers();
    }

    @SubscribeEvent
    public static void onEnterRoom(MildomEvent.MildomEnterRoomEvent e) {
        Mildom.viewers = e.getViewers();
    }

    @SubscribeEvent
    public static void onUserCount(MildomEvent.MildomUserCountEvent e) {
        Mildom.viewers = e.getViewers();
    }
}
