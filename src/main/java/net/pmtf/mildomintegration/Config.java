package net.pmtf.mildomintegration;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

public class Config {
    public static Configuration config;
    public static String RoomID;
    public static long searchCoolDown;

    public static void loadc(FMLPreInitializationEvent event) {
        config = new Configuration(event.getSuggestedConfigurationFile());
        load();
        sync();

        MinecraftForge.EVENT_BUS.register(new Config());

    }

    public static void sync() {
        config.getCategory("general").setComment("The Ikisugi Config");

        RoomID = config.get("general", "RoomID", "", "Mildom Room ID").getString();
        searchCoolDown = config.get("general", "searchCoolDown", 1000 * 60 * 3, "Mildom Viewers Ranking Search Cool Down").getLong();

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
