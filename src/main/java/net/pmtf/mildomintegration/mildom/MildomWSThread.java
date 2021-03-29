package net.pmtf.mildomintegration.mildom;

import jakarta.websocket.*;
import net.minecraftforge.common.MinecraftForge;
import net.pmtf.mildomintegration.Config;
import net.pmtf.mildomintegration.event.MildomEvent;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MildomWSThread extends Thread {
    public static final String USER_AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/88.0.4324.104 Safari/537.36";
    private static Session session;

    public static void init() {
        startThread();
    }

    public static void startThread() {
        try {
            if (session != null && session.isOpen()) {
                session.close();
                session = null;
            } else {
                MildomWSThread mt = new MildomWSThread();
                mt.start();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            WebSocketContainer container = ContainerProvider.getWebSocketContainer();
            URI uri = URI.create("wss://jp-room1.mildom.com/?roomId=" + Config.RoomID);
            session = container.connectToServer(new WebSocketClientMain(), uri);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @ClientEndpoint
    public static class WebSocketClientMain {
        @OnOpen
        public void onOpen(Session session) {
            String roomID = Config.RoomID;
            Date d = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:ss.sss'Z'");
            String time = sf.format(d);
            String nonopara = "fr=web`sfr=pc`devi=undefined`la=ja`gid=20193241516319`na=Japan`loc=Japan|Tokyo`clu=aws_japan`wh=3360*2100`rtm=" + time + "`ua=" + USER_AGENT + "`aid=" + roomID + "`live_type=2`live_subtype=2`isHomePage=false";
            String text = "{\"userId\":0,\"level\":1,\"userName\":\"guest114514\",\"guestId\":\"pc-gp-c5cf5a67-b30f-0cf8-f8ed-7e66399a49af\",\"" + nonopara + "\":\"fr=web`sfr=pc`devi=undefined`la=ja`gid=pc-gp-c5cf5a67-b30f-0cf8-f8ed-7e66399a49af`na=Japan`loc=Japan|Tokyo`clu=aws_japan`wh=3360*2100`rtm=2020-11-14T19:52:12.908Z`ua=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36`aid=" + roomID + "`live_type=2`live_subtype=2`isHomePage=false\",\"roomId\":" + roomID + ",\"cmd\":\"enterRoom\",\"reConnect\":1,\"nobleLevel\":0,\"avatarDecortaion\":0,\"enterroomEffect\":0,\"nobleClose\":0,\"nobleSeatClose\":0,\"reqId\":1}";
            try {
                session.getBasicRemote().sendText(text);
            } catch (IOException e) {
            }
        }

        @OnMessage
        public void onMessage(String message) {
            MildomEvent ev = new MildomEvent(message);
            MinecraftForge.EVENT_BUS.post(ev);
            MildomEvent me = Mildom.getEvent(ev.getCmd(), message);
            if (me != null) {
                MinecraftForge.EVENT_BUS.post(me);
            }
        }

        @OnError
        public void onError(Throwable th) {

        }

        @OnClose
        public void onClose(Session session) {
            MildomWSThread mt = new MildomWSThread();
            mt.start();
        }
    }

    public static boolean isMildomWsActive() {
        return session != null && session.isOpen();
    }

}
