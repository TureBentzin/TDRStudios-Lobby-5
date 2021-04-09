package de.tdrstudios.lobbyplugin.commands;

import de.bentzin.tools.console.Console;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class SpawnSetter extends MyCommand{

    private Chat chat = LobbyPlugin.getChat();
    private String name;
    private Permission permission;
    private FileConfiguration config;
    private Console log = new Console(getName(), this.getClass().getSimpleName(), "!");
    private Command command;

    public Command getCommand() {
        return command;
    }

    public Console getConsole() {
        return log;
    }

    public void setConfig(FileConfiguration config) {
        this.config = config;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    public Permission getPermission() {
        return permission;
    }

    public FileConfiguration getConfig() {
        return config;
    }

    public String getName() {
        return name;
    }
    protected List<Argument> arguments() {
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(" "));
        return arguments;
    }

    public SpawnSetter(String cmdName, String pname , String permission, FileConfiguration configuration) {

        super(LobbyPlugin.getPlugin().getCommand(pname) , new Permission(permission) , TabComplete.getNullList());

        log.send("Init : " + pname);
        command = LobbyPlugin.getPlugin().getCommand(cmdName);
        setConfig(configuration);
        setName(pname);
        setPermission(new Permission(permission, "The Permission for : " + getName() + "!"));
        registerMessages(); // This have to be after the Init of the Name, the Permission and The Chat!
    }
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;
            Chat chat = new Chat();
            chat.setPlayers(new Player[]{player});

            if (player.hasPermission(getPermission())) {
                if (args.length == 0) {
                    FileConfiguration config = getConfig();
                    config.set(getName()+ ".World", player.getWorld().getName());
                    config.set(getName() +".X", Double.valueOf(player.getLocation().getX()));
                    config.set(getName() +".Y", Double.valueOf(player.getLocation().getY()));
                    config.set(getName() +".Z", Double.valueOf(player.getLocation().getZ()));
                    config.set(getName() +".Yaw", Float.valueOf(player.getLocation().getYaw()));
                    config.set(getName() +".Pitch", Float.valueOf(player.getLocation().getPitch()));
                    LobbyPlugin.getPlugin().saveConfig();

                    chat.sendMessage("spawn.setter." + getName() + ".success");
                } else {
                    // player.sendMessage("§8[§e!§8]§a Bitte benutze /setlobbyspawn");
                    chat.sendMessage(LobbyPlugin.getMessageManager().getMessageByName("spawn.setter." + getName() +".error.arguments"));
                    chat.sendMessage(UsageMessage.getNameFIX(getCommand()));

                }
            } else {
                chat.sendMessage(LackingPermissionMessage.getNameFIX(permission));
            }
        }
        return false;
    }


    @Override
    public void registerMessages() {
        MessageManager manager = LobbyPlugin.getMessageManager();
        //  manager.registerMessage(new Message("spawn.setter." + getName() +".success","You have set the position for " + Chat.getAccentColor() + getName() + Chat.getChatColor() + "!"));
        manager.registerMessage(new Message("spawn.setter." + getName() + ".success", ConfigUtils.getString("tdrstudios.spawn.set").replace("%target%", " " + Chat.getAccentColor() + getName() + Chat.getChatColor())));
        //manager.registerMessage(new Message("spawn.setter." + getName() + ".error.arguments" , Chat.getErrorColor() + "Please care about the usage : " + Chat.getAccentColor() + getCommand().getUsage() + Chat.getChatColor() + "!"));
        manager.registerMessage(new UsageMessage(getCommand()));
        manager.registerMessage(new LackingPermissionMessage(permission));
    }

    @Override
    public void generatePermission(String permission) {


    }

}
