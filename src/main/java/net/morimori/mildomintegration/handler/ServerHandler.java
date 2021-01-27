package net.morimori.mildomintegration.handler;

import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.morimori.mildomintegration.Config;
import net.morimori.mildomintegration.MildomIntegration;
import net.morimori.mildomintegration.ServerUtility;
import net.morimori.mildomintegration.event.MildomEvent;
import net.morimori.mildomintegration.mildom.Gift;
import net.morimori.mildomintegration.mildom.MildomUtilty;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Mod.EventBusSubscriber(modid = MildomIntegration.MODID)
public class ServerHandler {
    @SubscribeEvent
    public static void onMildom(MildomEvent e) {
        try {
            if (ServerUtility.getServer() != null) {
                int gm = MildomUtilty.getMessageGiftID(e.getMessage());
                if (gm != 0) {
                    ItemStack stack = Gift.getGiftByGiftID(gm).getGiftStack();
                    //64個以上は無理だけど多少はね？
                    stack.setCount(Math.min(MildomUtilty.getMessageGiftCount(e.getMessage()), 64));

                    List<EntityPlayerMP> players = new ArrayList<>();

                    if (Config.GiftPlayerUUID.isEmpty()) {
                        players.addAll(ServerUtility.getPlayers());
                    } else {
                        if (ServerUtility.isOnlinePlayer(UUID.fromString(Config.GiftPlayerUUID)))
                            players.add(ServerUtility.getServer().getPlayerList().getPlayerByUUID(UUID.fromString(Config.GiftPlayerUUID)));
                    }

                    players.forEach(n -> {
                        if (!n.addItemStackToInventory(stack)) {
                            n.dropItem(stack, false);
                        }
                    });
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
