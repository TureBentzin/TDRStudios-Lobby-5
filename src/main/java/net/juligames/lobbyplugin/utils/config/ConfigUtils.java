package net.juligames.lobbyplugin.utils.config;

import net.juligames.lobbyplugin.LobbyPlugin;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.inventory.ItemStack;

import java.io.File;

public class ConfigUtils {
    public static FileConfiguration getConfig() {
        return LobbyPlugin.getPlugin().getConfig();
    }
    public static void saveConfig() {
        LobbyPlugin.getPlugin().saveConfig();
    }
    public static void registerAllConfigurations() {

    registerConfiguration("tdrstudios.join.msg" , "§8[§e+§8]§a %Player%");
    registerConfiguration("tdrstudios.leave.msg");
    registerConfiguration("tdrstudios.join.welcome" , "Welcome to JuliGames %Player%! %Date%" );
    registerConfiguration("tdrstudios.join.date.pattern" , "dd/MM/yyyy HH:mm:ss");

    /**
    * @deprecated
     */
    registerConfiguration("tdrstudios.hotbar.nav" , new ItemStack(Material.COMPASS).toString());
    registerConfiguration("tdrstudios.hotbar.nav.material" , Material.COMPASS.name());
    registerConfiguration("tdrstudios.hotbar.nav.displayName","§3§lTeleporter");


    }
    public static void registerConfiguration(String path) {
        //if(getConfig().isSet(path)) {
        if(getConfig().get(path) != null) {
            LobbyPlugin.getLog().send("ERROR: Path \"" + path + "\" is alredy set!");
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
}
