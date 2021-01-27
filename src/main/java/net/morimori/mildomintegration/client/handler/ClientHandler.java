package net.morimori.mildomintegration.client.handler;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.text.Style;
import net.minecraft.util.text.TextComponentString;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.morimori.mildomintegration.MildomIntegration;
import net.morimori.mildomintegration.event.MildomEvent;
import net.morimori.mildomintegration.mildom.MildomGift;
import net.morimori.mildomintegration.mildom.MildomUtilty;

@Mod.EventBusSubscriber(modid = MildomIntegration.MODID, value = Side.CLIENT)
public class ClientHandler {
    public static boolean chat = false;
    public static boolean userJoin = false;
    public static boolean gift = true;
    public static boolean filter = false;

    @SubscribeEvent
    public static void onMildom(MildomEvent e) {
        if (Minecraft.getMinecraft().player != null) {

            if (chat) {
                String cm = MildomUtilty.getCovMessageChat(e.getMessage());
                if (!cm.isEmpty()) {
                    cm = filter ? filter(cm) : cm;
                    if (!cm.isEmpty()) {
                        Minecraft.getMinecraft().player.sendMessage(MildomUtilty.getMildomUserNameAndLevel(e.getMessage()).appendSibling(new TextComponentString(": " + cm).setStyle(new Style().setColor(TextFormatting.RESET))));
                    }
                }
            }
            if (userJoin) {
                String jm = MildomUtilty.getMessageJoinUser(e.getMessage());
                if (!jm.isEmpty()) {
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("message.miuserjoin", MildomUtilty.getMildomUserNameAndLevel(e.getMessage())));
                }
            }

            if (gift) {
                int gm = MildomUtilty.getMessageGiftID(e.getMessage());
                if (gm != 0) {
                    int cont = MildomUtilty.getMessageGiftCount(e.getMessage());
                    Minecraft.getMinecraft().player.sendMessage(new TextComponentTranslation("message.migift", MildomUtilty.getMildomUserNameAndLevel(e.getMessage()), MildomGift.getGiftNameByID(MildomUtilty.getMessageGiftID(e.getMessage())) + (cont > 1 ? ("×" + cont) : "")));
                }
            }
        }
    }

    public static String filter(String str) {

        return "FIL:" + str;
    }

    @SubscribeEvent
    public static void onInitGui(GuiScreenEvent.InitGuiEvent.Post e) {
        if (e.getGui() instanceof GuiChat) {
            int x = e.getGui().width - 80;
            e.getButtonList().add(new GuiButton(19190, x, 5, 70, 20, I18n.format("options.michat") + ":" + (chat ? I18n.format("options.show") : I18n.format("options.hide"))));
            e.getButtonList().add(new GuiButton(19191, x, 30, 70, 20, I18n.format("options.miuserJoin") + ":" + (userJoin ? I18n.format("options.show") : I18n.format("options.hide"))));
            e.getButtonList().add(new GuiButton(19192, x, 55, 70, 20, I18n.format("options.migift") + ":" + (gift ? I18n.format("options.show") : I18n.format("options.hide"))));
            //    e.getButtonList().add(new GuiButton(19193, x, 80, 70, 20, I18n.format("options.mifilter") + ":" + (filter ? I18n.format("options.on") : I18n.format("options.off"))));
        }
    }

    @SubscribeEvent
    public static void action(GuiScreenEvent.ActionPerformedEvent.Post e) {
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
                filter = !filter;
                e.getButton().displayString = I18n.format("options.mifilter") + ":" + (filter ? I18n.format("options.on") : I18n.format("options.off"));
            }
        }
    }

}
