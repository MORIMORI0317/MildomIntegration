package net.morimori.mildomintegration.items;

import net.minecraft.block.Block;
import net.minecraft.block.BlockButton;
import net.minecraft.block.BlockGrass;
import net.minecraft.block.BlockPressurePlate;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityMinecart;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraft.entity.monster.EntitySkeleton;
import net.minecraft.entity.monster.EntityZombie;
import net.minecraft.entity.passive.EntityAnimal;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.*;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class GiftActions {
    private static List<IGiftAction> actions = new ArrayList<>();

    public static void init() {
        addAction((player, world, gift) -> {
            Random r = new Random();
            List<Potion> pos = ForgeRegistries.POTIONS.getValues();
            player.addPotionEffect(new PotionEffect(pos.get(r.nextInt(pos.size())), (int) (20 * 60 * 3 * (r.nextFloat() + 1f)), r.nextInt(2)));
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            List<Potion> pos = ForgeRegistries.POTIONS.getValues().stream().filter(Potion::isBeneficial).collect(Collectors.toList());
            player.addPotionEffect(new PotionEffect(pos.get(r.nextInt(pos.size())), (int) (20 * 60 * 3 * (r.nextFloat() + 1f)), r.nextInt(2)));
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            List<Potion> pos = ForgeRegistries.POTIONS.getValues().stream().filter(Potion::isBadEffect).collect(Collectors.toList());
            player.addPotionEffect(new PotionEffect(pos.get(r.nextInt(pos.size())), (int) (20 * 60 * 3 * (r.nextFloat() + 1f)), r.nextInt(2)));
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            world.createExplosion(player, player.posX, player.posY, player.posZ, (float) (4.0D + r.nextDouble() * 1.5D * r.nextInt(5)), true);
        });
        addAction((player, world, gift) -> {
            List<EntityEntry> entorrys = ForgeRegistries.ENTITIES.getValues();
            IntStream.range(0, 10).forEach(n -> {
                Random r = new Random();
                EntityEntry entorry = entorrys.get(r.nextInt(entorrys.size()));
                Entity entity = entorry.newInstance(world);
                if (entity.isNonBoss() && entity instanceof EntityAnimal) {
                    entity.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(entity);
                }
            });
        });
        addAction((player, world, gift) -> {
            List<EntityEntry> entorrys = ForgeRegistries.ENTITIES.getValues();
            IntStream.range(0, 10).forEach(n -> {
                Random r = new Random();
                EntityEntry entorry = entorrys.get(r.nextInt(entorrys.size()));
                Entity entity = entorry.newInstance(world);
                if (entity.isNonBoss() && entity instanceof EntityZombie) {
                    entity.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(entity);
                }
            });
        });
        addAction((player, world, gift) -> {
            List<EntityEntry> entorrys = ForgeRegistries.ENTITIES.getValues();
            IntStream.range(0, 10).forEach(n -> {
                Random r = new Random();
                EntityEntry entorry = entorrys.get(r.nextInt(entorrys.size()));
                Entity entity = entorry.newInstance(world);
                if (entity.isNonBoss() && entity instanceof EntitySkeleton) {
                    entity.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(entity);
                }
            });
        });
        addAction((player, world, gift) -> {
            List<EntityEntry> entorrys = ForgeRegistries.ENTITIES.getValues();
            IntStream.range(0, 10).forEach(n -> {
                Random r = new Random();
                EntityEntry entorry = entorrys.get(r.nextInt(entorrys.size()));
                Entity entity = entorry.newInstance(world);
                if (entity.isNonBoss() && entity instanceof EntityCreeper) {
                    entity.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(entity);
                }
            });
        });
        addAction((player, world, gift) -> {
            List<EntityEntry> entorrys = ForgeRegistries.ENTITIES.getValues();
            IntStream.range(0, 10).forEach(n -> {
                Random r = new Random();
                EntityEntry entorry = entorrys.get(r.nextInt(entorrys.size()));
                Entity entity = entorry.newInstance(world);
                if (entity.isNonBoss() && entity instanceof EntityVillager) {
                    entity.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(entity);
                }
            });
        });
        addAction((player, world, gift) -> {
            List<EntityEntry> entorrys = ForgeRegistries.ENTITIES.getValues();
            IntStream.range(0, 10).forEach(n -> {
                Random r = new Random();
                EntityEntry entorry = entorrys.get(r.nextInt(entorrys.size()));
                Entity entity = entorry.newInstance(world);
                if (entity.isNonBoss() && entity instanceof EntityMinecart) {
                    entity.setPosition(player.posX, player.posY, player.posZ);
                    world.spawnEntity(entity);
                }
            });
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            IntStream.range(0, r.nextInt(7) + 3).forEach(n -> {
                List<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(na -> na instanceof ItemFood).collect(Collectors.toList());
                ItemStack stack = new ItemStack(items.get(r.nextInt(items.size())));
                if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }
            });
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            IntStream.range(0, r.nextInt(2) + 1).forEach(n -> {
                List<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(na -> na instanceof ItemBed).collect(Collectors.toList());
                ItemStack stack = new ItemStack(items.get(r.nextInt(items.size())));
                if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }
            });
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            IntStream.range(0, 1).forEach(n -> {
                List<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(na -> na instanceof ItemRecord).collect(Collectors.toList());
                ItemStack stack = new ItemStack(items.get(r.nextInt(items.size())));
                if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }
            });
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            IntStream.range(0, 1 + r.nextInt(63)).forEach(n -> {
                List<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(na -> na instanceof ItemBlock && Block.getBlockFromItem(na) instanceof BlockGrass).collect(Collectors.toList());
                ItemStack stack = new ItemStack(items.get(r.nextInt(items.size())));
                if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }
            });
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            IntStream.range(0, 1 + r.nextInt(1 + r.nextInt(63))).forEach(n -> {
                List<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(na -> na instanceof ItemBlock && Block.getBlockFromItem(na) instanceof BlockButton).collect(Collectors.toList());
                ItemStack stack = new ItemStack(items.get(r.nextInt(items.size())));
                if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }
            });
        });
        addAction((player, world, gift) -> {
            Random r = new Random();
            IntStream.range(0, 1 + r.nextInt(1 + r.nextInt(63))).forEach(n -> {
                List<Item> items = ForgeRegistries.ITEMS.getValues().stream().filter(na -> na instanceof ItemBlock && Block.getBlockFromItem(na) instanceof BlockPressurePlate).collect(Collectors.toList());
                ItemStack stack = new ItemStack(items.get(r.nextInt(items.size())));
                if (!player.addItemStackToInventory(stack)) {
                    player.dropItem(stack, false);
                }
            });
        });

        addAction((player, world, gift) -> {
            Random r = new Random();

            IntStream.range(0, 30).forEach(n -> world.addWeatherEffect(new EntityLightningBolt(player.world, player.posX - 10 + r.nextInt(20), player.posY, player.posZ - 10 + r.nextInt(20), false)));
        });

    }

    public interface IGiftAction {
        void action(EntityPlayerMP player, World world, ItemStack gift);
    }

    public static void addAction(IGiftAction action) {
        actions.add(action);
    }

    public static List<IGiftAction> getActions() {
        return actions;
    }
}
