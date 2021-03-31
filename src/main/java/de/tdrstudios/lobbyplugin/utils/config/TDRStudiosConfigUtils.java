package de.tdrstudios.lobbyplugin.utils.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class TDRStudiosConfigUtils {

    public static YamlConfiguration ymlFileConfig = new YamlConfiguration();

    public static YamlConfiguration getYmlFileConfig() {
        return ymlFileConfig;
    }

    public static void init() {
        getYmlFileConfig().options().header("Plugin Infos");
    }




}
