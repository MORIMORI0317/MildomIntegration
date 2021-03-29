package net.pmtf.mildomintegration.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.*;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.pmtf.mildomintegration.MildomIntegration;
import net.pmtf.mildomintegration.event.MildomEvent;
import net.pmtf.mildomintegration.mildom.MildomLiveRanking;
import net.pmtf.mildomintegration.mildom.OfficialEmotions;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class ClientHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onMildomMessage(MildomEvent.MildomChatEvent e) {
        if (mc.player != null && chat) {
            ITextComponent comp = e.getMildomUserNameAndLevel().appendSibling(new TextComponentString(": " + OfficialEmotions.convertString(e.getChat())).setStyle(new Style().setColor(TextFormatting.RESET)));
            mc.player.sendStatusMessage(comp, false);
        }
    }

    @SubscribeEvent
    public static void onMildomJoin(MildomEvent.MildomJoinEvent e) {
        if (mc.player != null && userJoin) {
            ITextComponent comp = new TextComponentTranslation("message.miuserjoin", e.getMildomUserNameAndLevel());
            mc.player.sendStatusMessage(comp, false);
        }
    }

    @SubscribeEvent
    public static void onMildomLiveStart(MildomEvent.MildomLiveStartEvent e) {
        if (mc.player != null) {
            mc.player.sendStatusMessage(new TextComponentTranslation("message.liveStart"), false);
        }
        if (ClientHandler.ranking)
            MildomLiveRanking.serchid();
    }

    @SubscribeEvent
    public static void onMildomLiveEnd(MildomEvent.MildomLiveEndEvent e) {
        if (mc.player != null) {
            mc.player.sendStatusMessage(new TextComponentTranslation("message.liveEnd"), false);
        }
        if (ClientHandler.ranking)
            MildomLiveRanking.serchid();
    }


    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent e) {
        if (ClientHandler.ranking)
            MildomLiveRanking.tick();
    }

    public static boolean chat = false;
    public static boolean userJoin = false;
    public static boolean gift = false;
    public static boolean viewers = false;
    public static boolean ranking = false;

    public static void loadConfig() {
        File cf = new File(MildomIntegration.MODID + ".txt");
        Map<String, String> datas = new HashMap<>();
        datas.put("chat", "true");
        datas.put("userJoin", "false");
        datas.put("gift", "true");
        datas.put("viewers", "false");
        datas.put("ranking", "false");
        try {
            if (!cf.exists()) {
                FileWriter fw = new FileWriter(cf, false);
                PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
                datas.forEach((key, value) -> pw.println(key + "=" + value));
                pw.close();
            } else {
                FileReader re = new FileReader(cf.toString());
                BufferedReader bre = new BufferedReader(re);
                String st;
                while ((st = bre.readLine()) != null) {
                    try {
                        String[] fruit = st.split("=", 0);
                        datas.put(fruit[0], fruit[1]);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
            }

            chat = "true".equalsIgnoreCase(datas.get("chat"));
            userJoin = "true".equalsIgnoreCase(datas.get("userJoin"));
            gift = "true".equalsIgnoreCase(datas.get("gift"));
            viewers = "true".equalsIgnoreCase(datas.get("viewers"));
            ranking = "true".equalsIgnoreCase(datas.get("ranking"));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SubscribeEvent
    public static void onInitGui(GuiScreenEvent.InitGuiEvent.Post e) {
        if (e.getGui() instanceof GuiChat) {
            int x = e.getGui().width - 80;
            e.getButtonList().add(new GuiButton(19190, x, 5, 70, 20, I18n.format("options.michat") + ":" + (chat ? I18n.format("options.show") : I18n.format("options.hide"))));
            e.getButtonList().add(new GuiButton(19191, x, 30, 70, 20, I18n.format("options.miuserJoin") + ":" + (userJoin ? I18n.format("options.show") : I18n.format("options.hide"))));
            e.getButtonList().add(new GuiButton(19192, x, 55, 70, 20, I18n.format("options.migift") + ":" + (gift ? I18n.format("options.show") : I18n.format("options.hide"))));
            e.getButtonList().add(new GuiButton(19193, x, 80, 70, 20, I18n.format("options.viewers") + ":" + (viewers ? I18n.format("options.show") : I18n.format("options.hide"))));
            e.getButtonList().add(new GuiButton(19194, x, 105, 70, 20, I18n.format("options.ranking") + ":" + (ranking ? I18n.format("options.show") : I18n.format("options.hide"))));
        }
    }

    @SubscribeEvent
    public static void onAction(GuiScreenEvent.ActionPerformedEvent.Post e) {
        if (e.getGui() instanceof GuiChat) {
            if (e.getButton().id == 19190) {
                chat = !chat;
                e.getButton().displayString = I18n.format("options.michat") + ":" + (chat ? I18n.format("options.show") : I18n.format("options.hide"));
            } else if (e.getButton().id == 19191) {
                userJoin = !userJoin;
                e.getButton().displayString = I18n.format("options.miuserJoin") + ":" + (userJoin ? I18n.format("options.show") : I18n.format("options.hide"));
            } else if (e.getButton().id == 19192) {
                gift = !gift;
                e.getButton().displayString = I18n.format("options.migift") + ":" + (gift ? I18n.format("options.show") : I18n.format("options.hide"));
            } else if (e.getButton().id == 19193) {
                viewers = !viewers;
                e.getButton().displayString = I18n.format("options.viewers") + ":" + (viewers ? I18n.format("options.show") : I18n.format("options.hide"));
            } else if (e.getButton().id == 19194) {
                ranking = !ranking;
                e.getButton().displayString = I18n.format("options.ranking") + ":" + (ranking ? I18n.format("options.show") : I18n.format("options.hide"));
                if (ranking)
                    MildomLiveRanking.serchid();
            }

        }
    }

    @SubscribeEvent
    public static void onSave(WorldEvent.Save e) {
        try {
            File cf = new File(MildomIntegration.MODID + ".txt");
            Map<String, String> datas = new HashMap<>();
            datas.put("chat", chat + "");
            datas.put("userJoin", userJoin + "");
            datas.put("gift", gift + "");
            datas.put("viewers", viewers + "");
            datas.put("ranking", ranking + "");
            FileWriter fw = new FileWriter(cf, false);
            PrintWriter pw = new PrintWriter(new BufferedWriter(fw));
            datas.forEach((key, value) -> pw.println(key + "=" + value));
            pw.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}