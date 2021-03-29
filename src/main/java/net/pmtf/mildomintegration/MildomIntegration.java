package net.pmtf.mildomintegration;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.pmtf.mildomintegration.proxy.CommonProxy;

@Mod(modid = MildomIntegration.MODID, name = MildomIntegration.NAME, version = MildomIntegration.VERSION)
public class MildomIntegration {
    public static final String MODID = "mildomintegration";
    public static final String NAME = "Mildom Integration";
    public static final String VERSION = "1.2";

    @SidedProxy(clientSide = "net.pmtf.mildomintegration.proxy.ClientProxy", serverSide = "net.pmtf.mildomintegration.proxy.CommonProxy")
    public static CommonProxy proxy;


    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent e) {
        proxy.preInit(e);
    }

    @Mod.EventHandler
    public void init(FMLInitializationEvent e) {
        proxy.init(e);
    }

    @Mod.EventHandler
    public void postInit(FMLPostInitializationEvent e) {
        proxy.postInit(e);
    }
}
