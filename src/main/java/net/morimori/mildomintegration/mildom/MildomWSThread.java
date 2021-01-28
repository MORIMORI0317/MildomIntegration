package net.morimori.mildomintegration.mildom;

import jakarta.websocket.*;
import net.minecraftforge.common.MinecraftForge;
import net.morimori.mildomintegration.Config;
import net.morimori.mildomintegration.event.MildomEvent;

import java.io.IOException;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MildomWSThread extends Thread {
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
            String user_agent = "Mozilla/5.0 (windows nt 6.3; wow64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Safari/537.36";
            Date d = new Date();
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd'T'HH:ss.sss'Z'");
            String time = sf.format(d);
            String nonopara = "fr=web`sfr=pc`devi=undefined`la=ja`gid=20193241516319`na=Japan`loc=Japan|Tokyo`clu=aws_japan`wh=3360*2100`rtm=" + time + "`ua=" + user_agent + "`aid=" + Config.RoomID + "`live_type=2`live_subtype=2`isHomePage=false";
            String text = "{\"userId\":0,\"level\":1,\"userName\":\"guest128878\",\"guestId\":\"pc-gp-c5cf5a67-b30f-0cf8-f8ed-7e66399a49af\",\"" + nonopara + "\":\"fr=web`sfr=pc`devi=undefined`la=ja`gid=pc-gp-c5cf5a67-b30f-0cf8-f8ed-7e66399a49af`na=Japan`loc=Japan|Tokyo`clu=aws_japan`wh=3360*2100`rtm=2020-11-14T19:52:12.908Z`ua=Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/86.0.4240.193 Safari/537.36`aid=" + Config.RoomID + "`live_type=2`live_subtype=2`isHomePage=false\",\"roomId\":" + Config.RoomID + ",\"cmd\":\"enterRoom\",\"reConnect\":1,\"nobleLevel\":0,\"avatarDecortaion\":0,\"enterroomEffect\":0,\"nobleClose\":0,\"nobleSeatClose\":0,\"reqId\":1}";
            try {
                session.getBasicRemote().sendText(text);
            } catch (IOException e) {
            }
        }

        @OnMessage
        public void onMessage(String message) {
            String str = message;
            MinecraftForge.EVENT_BUS.post(new MildomEvent(message));
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
}
