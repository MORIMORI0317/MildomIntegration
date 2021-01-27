package net.morimori.mildomintegration.items;

import net.minecraft.item.Item;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.morimori.mildomintegration.MICreativeTabs;
import net.morimori.mildomintegration.MildomIntegration;

@Mod.EventBusSubscriber(modid = MildomIntegration.MODID)
public class MIItems {
    public static final Item GIFT = new ItemGift().setCreativeTab(MICreativeTabs.MODTAB).setRegistryName(MildomIntegration.MODID, "gift").setUnlocalizedName("gift");

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> e) {
        e.getRegistry().register(GIFT);
    }
}
