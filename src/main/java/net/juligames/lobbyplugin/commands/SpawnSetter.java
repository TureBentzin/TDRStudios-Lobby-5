package net.juligames.lobbyplugin.commands;

import de.bentzin.tools.console.Console;
import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.LackingPermissionMessage;
import net.juligames.lobbyplugin.msgs.Message;
import net.juligames.lobbyplugin.msgs.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class SpawnSetter implements CommandExecutor{

    private Chat chat = LobbyPlugin.getChat();
    private String name;
    private Permission permission;
    private FileConfiguration config;
    private Console log = new Console(getName(), this.getClass().getSimpleName(), "!");
    private Command command;

    public void setCommand(Command command) {
        this.command = command;
    }

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

    public SpawnSetter(String pname , String permission, FileConfiguration configuration) {
        log.send("Init : " + pname);
        registerMSGs();
        setConfig(configuration);
        setName(pname);
        setPermission(new Permission(permission, "The Permission for : " + getName() + "!"));
    }
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        setCommand(command);
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
                       chat.sendMessage("spawn.setter." + getName() + "error.arguments");

                    }
                } else {
                    chat.sendMessage(LackingPermissionMessage.getNameFIX(permission));
                }
            }
            return false;
        }
        public void  registerMSGs() {
            MessageManager manager = LobbyPlugin.getMessageManager();
            manager.registerMessage(new Message("spawn.setter." + getName() +".success","You have set the position for " + Chat.getAccentColor() + getName() + Chat.getChatColor() + "!"));
            manager.registerMessage(new Message("spawn.setter." + getName() + ".error.arguments" , Chat.getErrorColor() + "Please care about the usage : " + Chat.getAccentColor() + getCommand().getUsage() + Chat.getChatColor() + "!"));
            manager.registerMessage(new LackingPermissionMessage(permission));

        }


}
