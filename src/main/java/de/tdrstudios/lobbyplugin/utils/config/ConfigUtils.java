package de.tdrstudios.lobbyplugin.utils.config;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import org.bukkit.ChatColor;
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

        registerConfiguration("tdrstudios.sound.volume" , 20);

        registerConfiguration("tdrstudios.join.msg" , "§8[§e+§8]§a %Player%");
        registerConfiguration("tdrstudios.leave.msg");
        registerConfiguration("tdrstudios.join.welcome" , "Welcome to MYServer.tdrstudios.de %Player%! %Date%" );
        registerConfiguration("tdrstudios.join.date.pattern" , "dd/MM/yyyy HH:mm:ss");

        registerConfiguration("tdrstudios.allowWeatherChange" , true);
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


        //TODO: Fix this Problem with duplicate.
        //Pure Messages:
        registerConfiguration("tdrstudios.msg.only" , "This is only for %Sender%!");
        registerConfiguration("tdrstudios.spawn.success.me" , "Warped to the spawn!");
        registerConfiguration("tdrstudios.spawn.success.other" , "%Player% has warped you to the spawn!");
        registerConfiguration("tdrstudios.spawn.success.otherB" , "You warped %Player% to the spawn!");
        registerConfiguration("tdrstudios.msg.teleport.navigator" , "You have warped to %MiniGame%!");

        registerConfiguration("tdrstudios.inventorys.nav.items.spawm.material" , Material.GOLD_NUGGET.name());
        registerConfiguration("tdrstudios.inventorys.nav.items.spawn.name" , "§8 Spawn");
        registerConfiguration("tdrstudios.inventorys.nav.items.spawn.count", 1);
        

        registerConfiguration("tdrstudios.inventorys.nav.name" , "§a►  Navigator");


        registerConfiguration("tdrstudios.inventor"); //I don´t know for what this configuration is?

        //StaticItems
        registerConfiguration("tdrstudios.items.back.material" , Material.ARROW.name());
        registerConfiguration("tdrstudios.items.back.name" , "§4§l<X>");

        String prefix1 = "tdrstudios.inventorys.nav.items.";

        registerConfiguration(prefix1 + "MiniGame1.material" , Material.AIR.name());
        registerConfiguration(prefix1 + "MiniGame1.name");
        registerConfiguration(prefix1 + "MiniGame1.count" , 1);
        registerConfiguration(prefix1 + "MiniGame1.displayname" , "unset!");
        registerConfiguration(prefix1 + "MiniGame1.slot" , 10);

        registerConfiguration(prefix1 + "MiniGame2.material" , Material.AIR.name());
        registerConfiguration(prefix1 + "MiniGame2.name");
        registerConfiguration(prefix1 + "MiniGame2.displayname" , "unset!");
        registerConfiguration(prefix1 + "MiniGame2.count" , 1);
        registerConfiguration(prefix1 + "MiniGame2.slot" , 16);

        registerConfiguration(prefix1 + "MiniGame3.material" , Material.AIR.name());
        registerConfiguration(prefix1 + "MiniGame3.name");
        registerConfiguration(prefix1 + "MiniGame3.displayname" , "unset!");
        registerConfiguration(prefix1 + "MiniGame3.count" , 1);
        registerConfiguration(prefix1 + "MiniGame3.slot" , 28);

        registerConfiguration(prefix1 + "MiniGame4.material" , Material.AIR.name());
        registerConfiguration(prefix1 + "MiniGame4.name");
        registerConfiguration(prefix1 + "MiniGame4.displayname" , "unset!");
        registerConfiguration(prefix1 + "MiniGame4.count" , 1);
        registerConfiguration(prefix1 + "MiniGame4.slot" , 34);

        registerConfiguration(prefix1 + "MiniGame5.material" , Material.AIR.name());
        registerConfiguration(prefix1 + "MiniGame5.name");
        registerConfiguration(prefix1 + "MiniGame5.displayname" , "unset!");
        registerConfiguration(prefix1 + "MiniGame5.count" , 1);
        registerConfiguration(prefix1 + "MiniGame5.slot" , 40);

        registerConfiguration(prefix1 + "spawn.material" , Material.COMPASS.name());
        registerConfiguration(prefix1 + "spawn.name" , "§4Set in Config!");
        registerConfiguration(prefix1 + "spawn.count" , 1);
        registerConfiguration(prefix1 + "spawn.slot" , 40);


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

    public static boolean isSet(String path) {
        return getConfig().get(path) != null;
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

            getConfig().set(path , "Enter here!");
        }
        return null;
    }



    public static boolean getBoolean(String path) throws InvalidConfigurationException {
        Boolean r = getConfig().getBoolean(path);
        if(r != null) {
            return r;
        }else {
            getConfig().set(path , "Enter here!");
            throw new InvalidConfigurationException("The String on path \"" + path + "\" isn´s set in the Configuration!");
        }
    }



}
