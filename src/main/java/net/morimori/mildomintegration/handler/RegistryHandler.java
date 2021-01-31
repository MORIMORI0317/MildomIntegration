package net.morimori.mildomintegration.handler;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.morimori.mildomintegration.MildomIntegration;
import net.morimori.mildomintegration.items.MIItems;

@Mod.EventBusSubscriber(modid = MildomIntegration.MODID)
public class RegistryHandler {
    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        MIItems.MOD_ITEMS.forEach(n -> e.getRegistry().register(n));
    }
}
