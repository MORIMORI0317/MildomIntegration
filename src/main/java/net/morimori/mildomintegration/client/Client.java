package net.morimori.mildomintegration.client;

import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ModelLoader;
import net.morimori.mildomintegration.MildomIntegration;
import net.morimori.mildomintegration.items.MIItems;
import net.morimori.mildomintegration.mildom.Gift;

import java.util.Arrays;

public class Client {

    public static void preInit() {
        rgItemModel();
    }

    public static void rgItemModel() {
        ModelLoader.setCustomModelResourceLocation(MIItems.GIFT, 0, new ModelResourceLocation(new ResourceLocation(MildomIntegration.MODID, "gift"), "inventory"));

        Arrays.stream(Gift.values()).filter(n -> n.getItemID() != 0).forEach(n -> {
            ModelLoader.setCustomModelResourceLocation(MIItems.GIFT, n.getItemID(), new ModelResourceLocation(new ResourceLocation(MildomIntegration.MODID, "gift/" + n.getGitfID()), "inventory"));
        });
    }
}
