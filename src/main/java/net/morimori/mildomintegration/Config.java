package net.morimori.mildomintegration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    public static Configuration config;
    public static String RoomID;
    public static String GiftPlayerUUID;

    public static void loadc(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        load();
        sync();

        MinecraftForge.EVENT_BUS.register(new Config());

    }

    public static void sync() {
        config.getCategory("general").setComment("The Ikisugi Config");

        RoomID = config.get("general", "RoomID", "", "Mildom Room ID").getString();
        GiftPlayerUUID = config.get("general", "GiftPlayerUUID", "", "Gift Player UUID").getString();


        if (config.hasChanged())
            save();
    }

    public static void save() {
        config.save();
    }

    public static void load() {
        config.load();
    }

}