package net.pmtf.mildomintegration.client.handler;

import com.google.common.base.Strings;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.gui.GuiOverlayDebug;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import net.pmtf.mildomintegration.mildom.Mildom;
import net.pmtf.mildomintegration.mildom.MildomLiveRanking;
import net.pmtf.mildomintegration.mildom.MildomWSThread;

import java.util.ArrayList;
import java.util.List;

public class RenderHandler {
    private static final Minecraft mc = Minecraft.getMinecraft();

    @SubscribeEvent
    public static void onRender(TickEvent.RenderTickEvent e) {

        if (mc.gameSettings.showDebugInfo || Mildom.viewers == 0 || !MildomWSThread.isMildomWsActive())
            return;

        FontRenderer fontRenderer = mc.fontRenderer;

        GlStateManager.pushMatrix();
        List<String> vslist = viewers();
        for (int i = 0; i < vslist.size(); ++i) {
            String s = vslist.get(i);

            if (!Strings.isNullOrEmpty(s)) {
                int j = fontRenderer.FONT_HEIGHT;
                int k = fontRenderer.getStringWidth(s);
                int i1 = 2 + j * i;
                GuiOverlayDebug.drawRect(1, i1 - 1, 2 + k + 1, i1 + j - 1, -1873784752);
                fontRenderer.drawString(s, 2, i1, 14737632);
            }
        }


        GlStateManager.popMatrix();
    }

    private static List<String> viewers() {
        List<String> vs = new ArrayList<>();

        if (ClientHandler.viewers)
            vs.add(I18n.format("message.viewers", Mildom.viewers));

        if (ClientHandler.ranking) {
            MildomLiveRanking.MildomLiveRankingData ranking = MildomLiveRanking.getRankData();
            if (ranking != null) {
                if (!MildomLiveRanking.serching) {
                    String fr = I18n.format("message.viewersrankingnone");
                    String pl = "";

                    if (0 < ranking.getFluctuation()) {
                        fr = I18n.format("message.viewersrankingup");
                        pl = "+";
                    } else if (0 > ranking.getFluctuation()) {
                        fr = I18n.format("message.viewersrankingdwon");
                    }

                    String fra = I18n.format("message.viewersrankingfluctuation", pl, ranking.getFluctuation(), fr);

                    vs.add(I18n.format("message.viewersranking", ranking.getSerchMax(), ranking.getRanking()) + (ranking.getFluctuation() == 0 ? "" : " " + fra));
                } else {
                    vs.add(I18n.format("message.viewersrankserching"));
                }
            }
        }

        return vs;
    }
}
