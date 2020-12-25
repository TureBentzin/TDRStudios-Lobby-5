package net.juligames.lobbyplugin;

import de.bentzin.tools.console.Console;
import net.juligames.lobbyplugin.Navigator.CompassNavigator;
import net.juligames.lobbyplugin.Navigator.Cosmetics;
import net.juligames.lobbyplugin.Navigator.Einstellungen;
import net.juligames.lobbyplugin.Navigator.Info;
import net.juligames.lobbyplugin.commands.*;
import net.juligames.lobbyplugin.events.allgemein;
import net.juligames.lobbyplugin.listeners.JoinListener;
import net.juligames.lobbyplugin.msgs.MessageManager;
import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tdrstudios.BetaError;

import java.util.Collection;

public class LobbyPlugin extends JavaPlugin {
    private static LobbyPlugin plugin;

    public static void setPlugin(LobbyPlugin plugin) {
        LobbyPlugin.plugin = plugin;
    }

    private static Chat chat;
    private static MessageManager messageManager;
    private static Console log;

    public static Console getLog() {
        return log;
    }

    public static MessageManager getMessageManager() {
        return messageManager;
    }


    public static Chat getChat() {
        return chat;
    }

    public LobbyPlugin() {
        //This is a Temp Fix for the Issue-InvalidPluginExeption #3
    }

    @Override
    public void onLoad() {
        System.out.println(" ");
        System.out.println("This is a beta software!");
        System.out.println("This plugin is currently in development, so the TDRStudios don't promise that your server " + Bukkit.getServer().getName() + " not getting any damage from using this software!");
        if(getConfig().getBoolean("beta.acceptRisk")) {
            getLogger().warning("You have accepted the risk of using this beta software!");
        }else {
            System.err.println("You cant start your server without accepting the risks of using this plugin! \n You can accept the beta risks if you set the flag \"beta.acceptRisk\" in the ConfigYML at /plugins/" + getPlugin().getName() +"/cnfig.yml to \"true\"! \n The server will stop with ExitCode 5!");

            throw new BetaError();
        }
        System.out.println("This is a beta software!");
        System.out.println(" ");
    }

    public void onEnable() {

        setPlugin(this);
        chat = new Chat();
        log = new Console(getPlugin().getName() , getPlugin().getName(), "!");
        messageManager = new MessageManager();
        getLog().send("JavaPlugin by tdrstudios.de load!"); //WaterMark
        initChat();
        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Player[] players1 = players.toArray(new Player[players.size()]);
        chat.send(players1, "This Plugin is currently in Maintenance!");
        plugin = this;
        getConfig().addDefault("tdrstudios.commands.gamemode.allow.otherSelfSet" , "please enter");
        ConfigUtils.registerAllConfigurations();
        registerMessages();
        registerCommands();

        getCommand("spawn").setExecutor((CommandExecutor)new SpawnCommand());


        Permission[] permissions = {new Permission("de.tdrstudios.lobby.gamemode.0"), new Permission("de.tdrstudios.lobby.gamemode.1"), new Permission("de.tdrstudios.lobby.gamemode.2"), new Permission("de.tdrstudios.lobby.gamemode.3")};

        getCommand("gm").setExecutor((CommandExecutor)new GamemodeCommand("gm", permissions));
        getCommand("gamemode").setExecutor((CommandExecutor)new GamemodeCommand("gamemode", permissions));
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
    public void fixConfig() {
        if(getConfig().getLocation("tdrstudios.spawn") == null) {
            getConfig().set("tdrstudios.spawn", new Location(Bukkit.getWorld("world"), 0 , Bukkit.getWorld("world").getSeaLevel() ,0));
        }
    }


  public void initChat() {
    Chat.setChatColor(ChatColor.GREEN);
    Chat.setPrefix(ChatColor.GRAY + "[" + ChatColor.YELLOW + this.getConfig().getName() + ChatColor.GRAY + "] " + Chat.getChatColor());
    Chat.setPrefix(ChatColor.GRAY + "[" + ChatColor.YELLOW + "Lobby" + ChatColor.GRAY + "] " + Chat.getChatColor());
  }
  private void registerMessages() {

  }
  private void registerCommands() {
    final String setSpawnCommandName = "setspawn";
    final String setLobbyWarp1Name = "setLobbyWarp1";
    final String setLobbyWarp2Name = "setLobbyWarp2";
    final String setLobbyWarp3Name = "setLobbyWarp3";
    final String setLobbyWarp4Name = "setLobbyWarp4";
    final String setLobbyWarp5Name = "setLobbyWarp5";
    getCommand(setSpawnCommandName).setExecutor(new SpawnSetter(setSpawnCommandName, "Spawn", "tdrstudios.lobby.perms.setSpawn", getConfig()));
    getCommand(setLobbyWarp1Name).setExecutor(new SpawnSetter(setLobbyWarp1Name, "setWarp1", "tdrstudios.lobby.perms.setWarp1", getConfig()));
    getCommand(setLobbyWarp2Name).setExecutor(new SpawnSetter(setLobbyWarp2Name, "setWarp2", "tdrstudios.lobby.perms.setWarp2", getConfig()));
    getCommand(setLobbyWarp3Name).setExecutor(new SpawnSetter(setLobbyWarp3Name, "setWarp3", "tdrstudios.lobby.perms.setWarp3", getConfig()));
    getCommand(setLobbyWarp4Name).setExecutor(new SpawnSetter(setLobbyWarp4Name, "setWarp4", "tdrstudios.lobby.perms.setWarp4", getConfig()));
    getCommand(setLobbyWarp5Name).setExecutor(new SpawnSetter(setLobbyWarp5Name, "setWarp5", "tdrstudios.lobby.perms.setWarp5", getConfig()));

    //FixCommand for Inventory

    final String fixInventoryCommandName = "fixinvenory";
    final String fixInventoryCommandName_Short = "fixinv";
    getCommand(fixInventoryCommandName).setExecutor(new FixInventoryCommand(fixInventoryCommandName , "tdrstudios.lobby.perms.fix.inventory"));
    getCommand(fixInventoryCommandName_Short).setExecutor(new FixInventoryCommand(fixInventoryCommandName_Short , "tdrstudios.lobby.perms.fix.inventory"));


    getCommand("config").setExecutor(new ConfigCommand("config"));
  }

  public static LobbyPlugin getPlugin() {
    return plugin;
  }
}
