package net.morimori.mildomintegration.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.morimori.mildomintegration.mildom.gift.Gift;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;

public class ItemGift extends Item {
    public ItemGift() {
        this.setHasSubtypes(true);
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {
        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
            Arrays.stream(Gift.values()).filter(n -> n.getItemID() != 0).forEach(n -> items.add(new ItemStack(this, 1, n.getItemID())));
        }
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        tooltip.add(I18n.format("item.gift.old"));
    }
}
