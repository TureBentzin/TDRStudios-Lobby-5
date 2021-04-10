package de.tdrstudios.lobbyplugin.utils.config;

import de.tdrstudios.additional.debug.Point;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;

public class ConfigUtils {
    private static ArrayList<String> paths = new ArrayList<>();
    public static ArrayList<String> getPaths() {
        return paths;
    }
    @Deprecated
    protected static void addToPaths(String path) {
        paths.add(path);
    }

    private static HashMap<String,Object> defaultsmap = new HashMap();

    public static HashMap getDefaultsmap() {
        return defaultsmap;
    }
    protected static void addToDefaults(String path,@Nullable Object object) {
        if(!getPaths().contains(object))
        addToPaths(path);
        defaultsmap.put(path, object);
    }

    public static Set<String> getKeys(boolean deep){
        return getConfig().getKeys(deep);
    }

    public static FileConfiguration getConfig() {
        return LobbyPlugin.getPlugin().getConfig();
    }
    public static void saveConfig() {
        LobbyPlugin.getPlugin().saveConfig();
    }

    private static List<String> ActionWhitelist = new ArrayList<>();
    public static List<String> getDefaultActionWhitelist() {
        return ActionWhitelist;
    }

    static {
        getDefaultActionWhitelist().add(Material.OAK_DOOR.name());
        getDefaultActionWhitelist().add(Material.OAK_BUTTON.name());
        getDefaultActionWhitelist().add(Material.AIR.name());
        new Point(Thread.currentThread(), 2).point();
        System.out.println("ActionWhitelist = " + ActionWhitelist);
    }

    public static void registerAllConfigurations() {
        getConfig().options().header("TDRStudios ConfigFile");


        registerConfiguration("beta.acceptRisk" , false);
        registerConfiguration("beta.enableBetaBook" , true);

        registerConfiguration("tdrstudios.sound.volume" , 20);

        registerConfiguration("tdrstudios.system.color", ChatColor.GREEN.name());
        registerConfiguration("tdrstudios.system.errorcolor", ChatColor.RED.name());
        registerConfiguration("tdrstudios.system.accentcolor", ChatColor.GRAY.name());

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
        registerConfiguration("tdrstudios.hotbar.stick.displayName" , "§3Player:§r §7Hide §r§7 -§l Show");
        registerConfiguration("tdrstudios.hotbar.stick.slot",8);
        registerConfiguration("tdrstudios.hotbar.stick.chat.body" , "All players are now");
        registerConfiguration("tdrstudios.hotbar.stick.naming.hide" , "§3Player:§r §7§lHide §r§7 - Show");
        registerConfiguration("tdrstudios.hotbar.stick.naming.show" , "§3Player:§r §7Hide §r§7 -§l Show");

        registerConfiguration("tdrstudios.defaultGameMode" , GameMode.SURVIVAL.name());
        registerConfiguration("tdrstudios.commands.gamemode.allow.otherSelfSet" , false);

        registerConfiguration("tdrstudios.hotbar.xp", 1.0); // 1.0F

        //This allows you to manipulate the world around you!
        registerConfiguration("tdrstudios.manipulation.gamemode" , GameMode.CREATIVE.name());
        registerConfiguration("tdrstudios.manipulation.allow" , true);
        registerConfiguration("tdrstudios.manipulation.permission" , "tdrstudios.lobby.perms.manipulate");
        registerConfiguration("tdrstudios.manipulation.explosions.entity" , false);
        registerConfiguration("tdrstudios.manipulation.explosions.block" , false);
        registerConfiguration("tdrstudios.manipulation.explosions.firework" , true);
        registerConfiguration("tdrstudios.manipulation.farmland.fertilize" , false);
        registerConfiguration("tdrstudios.manipulation.farmland.grow" , false);
        registerConfiguration("tdrstudios.manipulation.fire.burn" , false);
        registerConfiguration("tdrstudios.manipulation.fire.ignite" , true);
        registerConfiguration("tdrstudios.manipulation.fire.spread" , false);


        //TODO: Fix this Problem with duplicate.
        //Pure Messages:
        registerConfiguration("tdrstudios.msg.only" , "This is only for %Sender%!");
        registerConfiguration("tdrstudios.spawn.success.me" , "Warped to the spawn!");
        registerConfiguration("tdrstudios.spawn.success.other" , "%Player% has warped you to the spawn!");
        registerConfiguration("tdrstudios.spawn.success.otherB" , "You warped %Player% to the spawn!");
        registerConfiguration("tdrstudios.spawn.set", "You have set the position for %target%!");
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
        registerConfiguration(prefix1 + "spawn.displayname" , "Spawn");
        registerConfiguration(prefix1 + "spawn.count" , 1);
        registerConfiguration(prefix1 + "spawn.slot" , 40);


        registerConfiguration("tdrstudios.actions.whitelist" , getDefaultActionWhitelist()); // register allowed Material.MATERIAL.name()


    }
    public static void registerConfiguration(String path) {
        addToDefaults(path, null);
        //if(getConfig().isSet(path)) {
        if(getConfig().get(path) != null) {
            //LobbyPlugin.getLog().send("ERROR: Path \"" + path + "\" is already set!");
        }else {
            getConfig().set(path , "null");
        }
        saveConfig();
    }
    public static void registerConfiguration(String path , Object object) {
        addToDefaults(path, object);
       // if(getConfig().isSet(path)) {
        if(getConfig().get(path) != null) {
           // LobbyPlugin.getLog().send("ERROR: Path \"" + path + "\" is alredy set!");
        }else {
            getConfig().set(path , object);
        }
        saveConfig();
    }

    public static boolean isSet(String path) {
        return getConfig().get(path) != null;
    }

    public static String getString(@NotNull String path){
        String r = getConfig().getString(path);
        if(r != null) {
            return r;
        }else {
            try {
                throw new InvalidConfigurationError("The String on path \"" + path + "\" isn´s set in the Configuration!");
            } catch (Exception e) {
                e.printStackTrace();
            }

            getConfig().set(path , "Enter here!");
        }
        return null;
    }



    public static boolean getBoolean(@NotNull String path) throws InvalidConfigurationException {
        Boolean r = getConfig().getBoolean(path);
        if(r != null) {
            return r;
        }else {
            getConfig().set(path , "Enter here!");
            throw new InvalidConfigurationError("The String on path \"" + path + "\" isn´s set in the Configuration!");
        }
    }
    @Deprecated
    public static @Nullable String[] getArray(@NotNull String path) {
        List<String> stringList = getConfig().getStringList(path);
        String[] strings = new String[stringList.size()];
        for (int i = 0; i < stringList.size(); i++) {
            strings[i] = stringList.get(i);
        }
        return strings;
    }
    public static List<String> getList(@NotNull String path) throws InvalidConfigurationException {
        List<String> stringList = getConfig().getStringList(path);
        if(stringList != null) {
            return  stringList;
        }else {
            getConfig().set(path, "Enter here!");
            throw  new InvalidConfigurationError("The StringList on path \"" + path + "\" isn´s set in the Configuration!");
        }
    }
    public static Material getMaterial(@NotNull String path) throws InvalidConfigurationException {
        Material material = Material.getMaterial(getString(path));
        if(material != null) {
            return material;
        }else{
            getConfig().set(path, "Enter here!");
            throw  new InvalidConfigurationError("The Material on path \"" + path + "\" isn´s set in the Configuration!");
        }
    }



}
