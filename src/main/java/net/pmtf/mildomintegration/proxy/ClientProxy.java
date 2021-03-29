package net.pmtf.mildomintegration.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.pmtf.mildomintegration.client.handler.ClientHandler;
import net.pmtf.mildomintegration.client.handler.RenderHandler;
import net.pmtf.mildomintegration.mildom.OfficialEmotions;

public class ClientProxy extends CommonProxy {
    @Override
    public void preInit(FMLPreInitializationEvent e) {
        super.preInit(e);
        ClientHandler.loadConfig();
        OfficialEmotions.init();
        MinecraftForge.EVENT_BUS.register(ClientHandler.class);
        MinecraftForge.EVENT_BUS.register(RenderHandler.class);
    }

    @Override
    public void init(FMLInitializationEvent e) {
        super.init(e);
    }

    @Override
    public void postInit(FMLPostInitializationEvent e) {
        super.postInit(e);
    }
}
