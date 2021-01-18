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

        registerConfiguration("tdrstudios.join.msg" , "§8[§e+§8]§a %Player%");
        registerConfiguration("tdrstudios.leave.msg");
        registerConfiguration("tdrstudios.join.welcome" , "Welcome to MYServer.tdrstudios.de %Player%! %Date%" );
        registerConfiguration("tdrstudios.join.date.pattern" , "dd/MM/yyyy HH:mm:ss");

        registerConfiguration("tdrstudios.allowWeatherChange" , true);
        registerConfiguration("tdrstudios.hotbar.nav" , new ItemStack(Material.COMPASS).toString());
        registerConfiguration("tdrstudios.hotbar.nav.material" , Material.COMPASS.name());
        registerConfiguration("tdrstudios.hotbar.nav.displayName","§3§lTeleporter§f");
        registerConfiguration("tdrstudios.hotbar.nav.slot",4);

        registerConfiguration("tdrstudios.hotbar.info.material" , Material.GOLD_NUGGET.name());
        registerConfiguration("tdrstudios.hotbar.info.displayName" , "§3§lInfo§f");
        registerConfiguration("tdrstudios.hotbar.info.slot",1);

        registerConfiguration("tdrstudios.hotbar.settings.material" , Material.COMPARATOR.name());
        registerConfiguration("tdrstudios.hotbar.settings.displayName" , "§3§lEinstellungen§f");
        registerConfiguration("tdrstudios.hotbar.settings.slot",7);

        registerConfiguration("tdrstudios.hotbar.stick.material" , Material.BLAZE_ROD.name());
        registerConfiguration("tdrstudios.hotbar.stick.displayName" , "§3§lSpieler §a§lAnzeigen §f| §4§lVerstecken§f");
        registerConfiguration("tdrstudios.hotbar.stick.slot",8);

        registerConfiguration("tdrstudios.defaultGameMode" , GameMode.SURVIVAL.name());

        registerConfiguration("tdrstudios.hotbar.xp", 1.0); // 1.0F

        //GamemodeManipulation
        registerConfiguration("tdrstudios.manipulation.gamemode" , GameMode.CREATIVE.name());
        registerConfiguration("tdrstudios.manipulation.allow" , true);
        registerConfiguration("tdrstudios.manipulation.permission" , "tdrstudios.lobby.perms.manipulate");

        registerConfiguration("tdrstudios.inventory.nav.name" , ChatColor.DARK_RED + "If you see this -§l report it");

        registerConfiguration("tdrstudios.inventorys.nav.itmes.spawm.material" , Material.GOLD_NUGGET.name());
        registerConfiguration("tdrstudios.inventorys.nav.itmes.spawn.name");
        registerConfiguration("tdrstudios.inventorys.nav.itmes.spawn.count");

        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame1.material" , Material.AIR.name());
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame1.name" , "null");
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame1.count" , 1);

        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame2.material" , Material.AIR.name());
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame2.name" , "null");
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame2.count" ,1);

        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame3.material" , Material.DIAMOND_HOE.name());
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame3.name" , "§6|Example");
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame3.count" , 1);

        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame4.material" , Material.AIR.name());
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame4.name" , "null");
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame4.count");

        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame5.material" , Material.SAND.name());
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame5.name" , "§8| A Sand");
        registerConfiguration("tdrstudios.inventorys.nav.itmes.MiniGame5.count" , 1);


        registerConfiguration("tdrstudios.inventor"); //I don´t know for what this configuration is?

        //StaticItems
        registerConfiguration("tdrstudios.items.back.material" , Material.ARROW.name());
        registerConfiguration("tdrstudios.items.back.name" , "§4§l<X>");

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

    public static String getString(String path) throws InvalidConfigurationException {
        String r = getConfig().getString(path);
        if(r != null) {
            return r;
        }else {
            getConfig().set(path , "Enter here!");
            throw new InvalidConfigurationException("The String on path \"" + path + "\" isn´s set in the Configuration!");

        }
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
