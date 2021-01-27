package net.morimori.mildomintegration;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.morimori.mildomintegration.mildom.Gift;

public class MICreativeTabs extends CreativeTabs {

    public static CreativeTabs MODTAB = new MICreativeTabs(MildomIntegration.MODID);

    public MICreativeTabs(String label) {
        super(label);
    }

    @Override
    public ItemStack getTabIconItem() {
        return Gift.G1192.getGiftStack();
    }
}
