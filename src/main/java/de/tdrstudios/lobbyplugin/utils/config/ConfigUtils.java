package de.tdrstudios.lobbyplugin.utils.config;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

public class ConfigUtils {
    public static FileConfiguration getConfig() {
        return LobbyPlugin.getPlugin().getConfig();
    }
    public static void saveConfig() {
        LobbyPlugin.getPlugin().saveConfig();
    }
    public static void registerAllConfigurations() {
        getConfig().options().header("#TDRStudios ConfigFile");


        registerConfiguration("beta.acceptRisk" , false);
        registerConfiguration("beta.enableBetaBook" , true);

        registerConfiguration("tdrstudios.join.msg" , "§8[§e+§8]§a %Player%");
        registerConfiguration("tdrstudios.leave.msg");
        registerConfiguration("tdrstudios.join.welcome" , "Welcome to MYServer.tdrstudios.de %Player%! %Date%" );
        registerConfiguration("tdrstudios.join.date.pattern" , "dd/MM/yyyy HH:mm:ss");

    /**
    * @deprecated
     *
     */
        registerConfiguration("tdrstudios.allowWeatherChange" , true);
        registerConfiguration("tdrstudios.hotbar.nav" , new ItemStack(Material.COMPASS).toString());
        registerConfiguration("tdrstudios.hotbar.nav.material" , Material.COMPASS.name());
        registerConfiguration("tdrstudios.hotbar.nav.displayName","§3§lTeleporter§f");
        registerConfiguration("tdrstudios.hotbar.nav.slot",4);

        registerConfiguration("tdrstudios.hotbar.info.material" , Material.GOLD_NUGGET.name());
        registerConfiguration("tdrstudios.hotbar.info.displayName" , "§3§lInfo§f");
        registerConfiguration("tdrstudios.hotbar.info.slot",1);

        registerConfiguration("tdrstudios.hotbar.settings.material" , Material.COMPARATOR.name());
        registerConfiguration("tdrstudios.hotbar.settings.displayName" , "§3§lSettings§f");
        registerConfiguration("tdrstudios.hotbar.settings.slot",7);

        registerConfiguration("tdrstudios.hotbar.stick.material" , Material.BLAZE_ROD.name());
        registerConfiguration("tdrstudios.hotbar.stick.displayName" , "§3§lPlayer §a§lHide §f| §4§lShow§f");
        registerConfiguration("tdrstudios.hotbar.stick.slot",8);

        registerConfiguration("tdrstudios.defaultGameMode" , GameMode.SURVIVAL.name());

        registerConfiguration("tdrstudios.hotbar.xp", 1.0); // 1.0F

        //This allows you to manipulate the world around you!
        registerConfiguration("tdrstudios.manipulation.gamemode" , GameMode.CREATIVE.name());
        registerConfiguration("tdrstudios.manipulation.allow" , true);
        registerConfiguration("tdrstudios.manipulation.permission" , "tdrstudios.lobby.perms.manipulate");

        //Maintenance
        registerConfiguration("tdrstudios.feature.maintenance");

        //Pure Messages:
        registerConfiguration("tdrstudios.msg.only" , "This is only for %Sender%!");
        registerConfiguration("tdrstudios.spawn.success.me" , "Warped to the spawn!");
        registerConfiguration("tdrstudios.spawn.success.other" , "%Player% has warped you to the spawn!");
        registerConfiguration("tdrstudios.spawn.success.otherB" , "You warped %Player% to the spawn!");


        registerConfiguration("tdrstudios.inventor"); //I don´t know for what this configuration is?

    }
    public static void registerConfiguration(String path) {
        //if(getConfig().isSet(path)) {
        if(getConfig().get(path) != null) {
            LobbyPlugin.getLog().send("ERROR: Path \"" + path + "\" is already set!");
        }else {
            getConfig().set(path , "null");
        }
        saveConfig();
    }
    public static void registerConfiguration(String path , Object object) {
       // if(getConfig().isSet(path)) {
        if(getConfig().get(path) != null) {
            LobbyPlugin.getLog().send("ERROR: Path \"" + path + "\" is alredy set!");
        }else {
            System.out.println("DEBUG: Vorher im Slot " + path + getConfig().get(path));
            getConfig().set(path , object);
            System.out.println("DEBUG: Nachher im Slot " + path + getConfig().get(path));
        }
        saveConfig();
    }

    public static String getString(String path){
        String r = getConfig().getString(path);
        if(r != null) {
            return r;
        }else {
            try {
                throw new InvalidConfigurationException("The String on path \"" + path + "\" isn´s set in the Configuration!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public static boolean getBoolean(String path) throws InvalidConfigurationException {
        Boolean r = getConfig().getBoolean(path);
        if(r != null) {
            return r;
        }else {
            throw new InvalidConfigurationException("The String on path \"" + path + "\" isn´s set in the Configuration!");
        }
    }



}
