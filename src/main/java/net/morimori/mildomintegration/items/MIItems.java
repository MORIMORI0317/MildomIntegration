package net.morimori.mildomintegration.items;

import net.minecraft.item.Item;
import net.morimori.mildomintegration.MildomIntegration;

import java.util.ArrayList;
import java.util.List;

public class MIItems {
    public static List<Item> MOD_ITEMS = new ArrayList<Item>();

    public static final Item GIFT = register("gift", new ItemGift(), true);
    public static final Item GIFT2 = register("gift2", new ItemGift2());

    private static Item register(String name, Item item) {
        return register(name, item, false);
    }

    private static Item register(String name, Item item, boolean hide) {
        Item itm = item.setRegistryName(MildomIntegration.MODID, name).setUnlocalizedName(name);
        if (!hide)
            itm.setCreativeTab(MICreativeTabs.MODTAB);
        MOD_ITEMS.add(itm);
        return itm;
    }
}
