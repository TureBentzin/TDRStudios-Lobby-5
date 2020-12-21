package net.juligames.lobbyplugin;

import net.juligames.lobbyplugin.Navigator.CompassNavigator;
import net.juligames.lobbyplugin.Navigator.Cosmetics;
import net.juligames.lobbyplugin.Navigator.Einstellungen;
import net.juligames.lobbyplugin.Navigator.Info;
import net.juligames.lobbyplugin.commands.*;
import net.juligames.lobbyplugin.commands.setspawn;
import net.juligames.lobbyplugin.events.allgemein;
import net.juligames.lobbyplugin.listeners.JoinListener;
import net.juligames.lobbyplugin.msgs.MessageManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class LobbyPlugin extends JavaPlugin {
  private static LobbyPlugin plugin;
  private static Chat chat;
  private static MessageManager messageManager;

  public static MessageManager getMessageManager() {
    return messageManager;
  }

  public static Chat getChat() {
    return chat;
  }

  public void onEnable() {
    initChat();
    chat.send((Player) Bukkit.getOnlinePlayers(), "This Plugin is currently in Maintenance!");
    plugin = this;
   // getCommand("setspawn").setExecutor((CommandExecutor)new setspawn());
    getCommand("setLobbySpawn").setExecutor(new SpawnSetter("setspawn", "tdrstudios.lobby.perms.setSpawn", getConfig()));
    getCommand("setLobbyWarp1").setExecutor(new SpawnSetter("setWarp1", "tdrstudios.lobby.perms.setWarp1", getConfig()));
    getCommand("setLobbyWarp2").setExecutor(new SpawnSetter("setWarp2", "tdrstudios.lobby.perms.setWarp2", getConfig()));
    getCommand("setLobbyWarp3").setExecutor(new SpawnSetter("setWarp3", "tdrstudios.lobby.perms.setWarp3", getConfig()));
    getCommand("setLobbyWarp4").setExecutor(new SpawnSetter("setWarp4", "tdrstudios.lobby.perms.setWarp4", getConfig()));
    getCommand("setLobbyWarp5").setExecutor(new SpawnSetter("setWarp5", "tdrstudios.lobby.perms.setWarp5", getConfig()));
    getCommand("spawn").setExecutor((CommandExecutor)new SpawnCommand());


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

  public void initChat() {
    Chat.setChatColor(ChatColor.GOLD);
    Chat.setPrefix(ChatColor.BLACK + "[" + ChatColor.YELLOW + this.getConfig().getName() + ChatColor.BLACK + "] " + Chat.getChatColor());
  }
  private void registerMessages() {

  }
  
  public static LobbyPlugin getPlugin() {
    return plugin;
  }
}
