package de.tdrstudios.lobbyplugin;


import de.bentzin.tools.DevTools;

import de.bentzin.tools.console.Console;
import de.tdrstudios.lobbyplugin.Navigator.CompassNavigator;
import de.tdrstudios.lobbyplugin.Navigator.Cosmetics;
import de.tdrstudios.lobbyplugin.Navigator.Einstellungen;
import de.tdrstudios.lobbyplugin.Navigator.Info;

import de.tdrstudios.lobbyplugin.commands.*;

import de.tdrstudios.lobbyplugin.events.GeneralEvents;
import de.tdrstudios.lobbyplugin.listeners.JoinListener;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.FixInventoryTab;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryContent;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.defaults.BukkitCommand;
import org.bukkit.command.defaults.VersionCommand;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import de.tdrstudios.additional.BetaError;

import java.util.Collection;
import java.util.*;

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



    public void betaWarn() {
        setPlugin(this);
        System.out.println(" ");
        System.out.println("This is a beta software!");
        System.out.println("This plugin is currently in development, so the TDRStudios don't promise that your server " + Bukkit.getServer().getName() + " not getting any damage from using this software!");
        ConfigUtils.registerConfiguration("beta.acceptRisk", false);
        if (getConfig().getBoolean("beta.acceptRisk")) {
            getLogger().warning("You have accepted the risk of using this beta software!");
        } else {
            System.err.println("You cant start your server without accepting the risks of using this plugin! \n You can accept the beta risks if you set the flag \"beta.acceptRisk\" in the ConfigYML at /plugins/" + getPlugin().getName() + "/cnfig.yml to \"true\"! \n The server will stop with ExitCode 5!");
            throw new BetaError();
        }
        System.out.println("This is a beta software!");
        System.out.println(" ");
    }

         public LobbyPlugin() {
        DevTools.getDevToolsConsole().send("{" +this.getClass().getName() + "}" + " load!");
         }


    public void onEnable() {
        plugin = this;


        chat = new Chat();
        log = new Console(getPlugin().getName() , getPlugin().getName(), "!");
        messageManager = new MessageManager();
        getLog().send("JavaPlugin by tdrstudios.de load!"); //WaterMark
        initChat();

        Collection<? extends Player> players = Bukkit.getOnlinePlayers();
        Player[] players1 = players.toArray(new Player[players.size()]);
        chat.send(players1, "This Plugin is currently in Maintenance!");

        getConfig().addDefault("tdrstudios.commands.gamemode.allow.otherSelfSet" , "please enter");

        ConfigUtils.registerAllConfigurations();
        betaWarn();
        registerMessages();
        registerCommands();
        InventoryUtils.registerAllInventoryContents();

        getCommand("spawn").setExecutor((CommandExecutor)new SpawnCommand());


        Permission[] permissions = {new Permission("de.tdrstudios.lobby.gamemode.0"), new Permission("de.tdrstudios.lobby.gamemode.1"), new Permission("de.tdrstudios.lobby.gamemode.2"), new Permission("de.tdrstudios.lobby.gamemode.3")};

        getCommand("gm").setExecutor((CommandExecutor)new GamemodeCommand("gm", permissions));
        getCommand("gamemode").setExecutor((CommandExecutor)new GamemodeCommand("gamemode", permissions));
        getCommand("lv").setExecutor((CommandExecutor)new VersionCheckCommand(getCommand("lv")));
        getCommand("lobbyversion").setExecutor((CommandExecutor)new VersionCheckCommand(getCommand("lobbyversion")));
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents((Listener)new CompassNavigator(), (Plugin)this);
        pluginManager.registerEvents((Listener)new JoinListener(), (Plugin)this);
        pluginManager.registerEvents((Listener)new Info(), (Plugin)this);
        pluginManager.registerEvents((Listener)new Einstellungen(), (Plugin)this);
        pluginManager.registerEvents((Listener)new GeneralEvents(), (Plugin)this);
        pluginManager.registerEvents((Listener)new Cosmetics(), (Plugin)this);

        //register the "beta book"
        try {
            if(ConfigUtils.getBoolean("beta.enableBetaBook")) {
                InventoryContent betabook = new InventoryContent(Material.WRITTEN_BOOK, "§2The §5Beta §6Book", 1, true, 22);
                ItemMeta bookmeta =  betabook.getMeta();

                bookmeta.setDisplayName(ChatColor.BOLD + "§2The §5Beta §6Book");
                bookmeta.setLore(Collections.singletonList(Chat.getChatColor() + "This plugin still in development so please report any issue to TDRStudios!"));
                InventoryUtils.registerInventoryContent(betabook);
                betabook.setMeta(bookmeta);
            }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        getLogger().warning("[Preview] You are using a preview version of the plugin so please report any issues, that arent debugs to the developers via issue on github.");
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

    final String fixInventoryCommandName = "fixinventory";
    final String fixInventoryCommandName_Short = "fixinv";
    getCommand(fixInventoryCommandName).setExecutor(new FixInventoryCommand(fixInventoryCommandName , "tdrstudios.lobby.perms.fix.inventory"));
    getCommand(fixInventoryCommandName_Short).setExecutor(new FixInventoryCommand(fixInventoryCommandName_Short , "tdrstudios.lobby.perms.fix.inventory"));

    getCommand("config").setExecutor(new ConfigCommand("config"));

    //getCommand("config").setTabCompleter(new ConfigTab(new Permission("tdrstudios.debug")));

  }

  public static LobbyPlugin getPlugin() {
    return plugin;
  }
}

