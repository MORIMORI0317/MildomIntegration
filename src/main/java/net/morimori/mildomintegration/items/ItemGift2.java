package net.morimori.mildomintegration.items;

import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.NonNullList;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.morimori.mildomintegration.compat.Emojicord;
import net.morimori.mildomintegration.mildom.gift.Gift2;

import javax.annotation.Nullable;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class ItemGift2 extends ItemFood {

    public ItemGift2() {
        super(4, 1.2F, false);
        this.setHasSubtypes(true);
        this.setAlwaysEdible();
    }

    @Override
    public void getSubItems(CreativeTabs tab, NonNullList<ItemStack> items) {

        if (this.isInCreativeTab(tab)) {
            items.add(new ItemStack(this));
            Arrays.stream(Gift2.values()).filter(n -> n.getItemID() != 0).forEach(n -> items.add(new ItemStack(this, 1, n.getItemID())));
        }
    }

    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player) {
        if (!worldIn.isRemote) {
            Gift2 gift = Gift2.getGiftByItemID(stack.getItemDamage());
            Random r = new Random((gift.getGitfID() + "").hashCode());

            List<Potion> pos = ForgeRegistries.POTIONS.getValues();
            player.addPotionEffect(new PotionEffect(pos.get(r.nextInt(pos.size())), (int) (20 * 60 * 3 * (r.nextFloat() + 1f)), r.nextInt(2)));

            if (gift.getPrice() > 0) {
                try {
                    GiftActions.getActions().get(r.nextInt(GiftActions.getActions().size())).action((EntityPlayerMP) player, worldIn, stack);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            int price = Gift2.getGiftByItemID(stack.getItemDamage()).getPrice();
            player.addExperienceLevel(price);

        }
    }

    @Override
    public String getItemStackDisplayName(ItemStack stack) {

        Gift2 gift = Gift2.getGiftByItemID(stack.getItemDamage());

        if (gift == Gift2.NONE)
            return super.getItemStackDisplayName(stack);

        return I18n.format(this.getUnlocalizedNameInefficiently(stack) + "." + gift.getGitfID() + ".name");
    }

    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack stack, @Nullable World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
        if (Emojicord.isLoaded())
            tooltip.add(Emojicord.getPriceEmoji() + " " + Gift2.getGiftByItemID(stack.getItemDamage()).getPrice());
        else
            tooltip.add(I18n.format("item.gift.price", Gift2.getGiftByItemID(stack.getItemDamage()).getPrice()));
    }
}
