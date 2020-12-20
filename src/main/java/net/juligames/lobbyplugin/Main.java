package net.juligames.lobbyplugin;

import net.juligames.lobbyplugin.Navigator.CompassNavigator;
import net.juligames.lobbyplugin.Navigator.Cosmetics;
import net.juligames.lobbyplugin.Navigator.Einstellungen;
import net.juligames.lobbyplugin.Navigator.Info;
import net.juligames.lobbyplugin.commands.SpawnCommand;
import net.juligames.lobbyplugin.commands.Versioncheck;
import net.juligames.lobbyplugin.commands.gamemode0;
import net.juligames.lobbyplugin.commands.setlobbyspawn1;
import net.juligames.lobbyplugin.commands.setlobbyspawn2;
import net.juligames.lobbyplugin.commands.setlobbyspawn3;
import net.juligames.lobbyplugin.commands.setlobbyspawn4;
import net.juligames.lobbyplugin.commands.setlobbyspawn5;
import net.juligames.lobbyplugin.commands.setspawn;
import net.juligames.lobbyplugin.events.allgemein;
import net.juligames.lobbyplugin.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
  private static Main plugin;
  
  public void onEnable() {
    plugin = this;
    getCommand("setspawn").setExecutor((CommandExecutor)new setspawn());
    getCommand("spawn").setExecutor((CommandExecutor)new SpawnCommand());
    getCommand("setlobbyspawn1").setExecutor((CommandExecutor)new setlobbyspawn1());
    getCommand("setlobbyspawn2").setExecutor((CommandExecutor)new setlobbyspawn2());
    getCommand("setlobbyspawn3").setExecutor((CommandExecutor)new setlobbyspawn3());
    getCommand("setlobbyspawn4").setExecutor((CommandExecutor)new setlobbyspawn4());
    getCommand("setlobbyspawn5").setExecutor((CommandExecutor)new setlobbyspawn5());
    getCommand("gm").setExecutor((CommandExecutor)new gamemode0());
    getCommand("lv").setExecutor((CommandExecutor)new Versioncheck());
    getCommand("lobbyversion").setExecutor((CommandExecutor)new Versioncheck());
    PluginManager pluginManager = Bukkit.getPluginManager();
    pluginManager.registerEvents((Listener)new CompassNavigator(), (Plugin)this);
    pluginManager.registerEvents((Listener)new JoinListener(), (Plugin)this);
    pluginManager.registerEvents((Listener)new Info(), (Plugin)this);
    pluginManager.registerEvents((Listener)new Einstellungen(), (Plugin)this);
    pluginManager.registerEvents((Listener)new allgemein(), (Plugin)this);
    pluginManager.registerEvents((Listener)new Cosmetics(), (Plugin)this);
  }
  
  public static Main getPlugin() {
    return plugin;
  }
}
