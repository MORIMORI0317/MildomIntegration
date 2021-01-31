package net.morimori.mildomintegration.items;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.morimori.mildomintegration.MildomIntegration;
import net.morimori.mildomintegration.mildom.gift.Gift2;

public class MICreativeTabs extends CreativeTabs {

    public static CreativeTabs MODTAB = new MICreativeTabs(MildomIntegration.MODID);

    public MICreativeTabs(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return Gift2.G1192.getGiftStack();
    }
}
