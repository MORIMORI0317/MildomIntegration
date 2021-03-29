package net.pmtf.mildomintegration.proxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.pmtf.mildomintegration.Config;
import net.pmtf.mildomintegration.handler.ServerHandler;
import net.pmtf.mildomintegration.mildom.Mildom;

public class CommonProxy {
    public void preInit(FMLPreInitializationEvent e) {
        Mildom.init();
        Config.loadc(e);
        MinecraftForge.EVENT_BUS.register(ServerHandler.class);
    }

    public void init(FMLInitializationEvent e) {

    }

    public void postInit(FMLPostInitializationEvent e) {

    }
}
