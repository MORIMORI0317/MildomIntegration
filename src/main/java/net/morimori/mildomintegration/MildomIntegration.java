package net.morimori.mildomintegration;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.morimori.mildomintegration.client.Client;
import net.morimori.mildomintegration.items.GiftActions;
import net.morimori.mildomintegration.mildom.MildomWSThread;
import org.apache.logging.log4j.Logger;

@Mod(modid = MildomIntegration.MODID, name = MildomIntegration.NAME, version = MildomIntegration.VERSION)
public class MildomIntegration {
    public static final String MODID = "mildomintegration";
    public static final String NAME = "Mildom Integration";
    public static final String VERSION = "1.0";

    private static Logger logger;

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        Config.loadc(event);
        logger = event.getModLog();
        if (event.getSide() == Side.CLIENT) {
            Client.preInit();
        }
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MildomWSThread.init();
        GiftActions.init();
    }
}
