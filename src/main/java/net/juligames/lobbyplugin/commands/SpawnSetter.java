package net.juligames.lobbyplugin.commands;

import de.bentzin.tools.console.Console;
import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
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
        setConfig(configuration);
        setName(pname);
        setPermission(new Permission(permission, "The Permission for : " + getName() + "!"));
    }
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player)sender;
                if (player.hasPermission(getPermission())) {
                    if (args.length == 0) {
                        FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
                        config.set( getName()+ ".World", player.getWorld().getName());
                        config.set(getName() +".X", Double.valueOf(player.getLocation().getX()));
                        config.set(getName() +".Y", Double.valueOf(player.getLocation().getY()));
                        config.set(getName() +".Z", Double.valueOf(player.getLocation().getZ()));
                        config.set(getName() +".Yaw", Float.valueOf(player.getLocation().getYaw()));
                        config.set(getName() +".Pitch", Float.valueOf(player.getLocation().getPitch()));
                        LobbyPlugin.getPlugin().saveConfig();
                        player.sendMessage("§8[§eInfo§8]§a Du hast den Lobbyspawnpunkt gesetzt!");
                    } else {
                        player.sendMessage("§8[§e!§8]§a Bitte benutze /setlobbyspawn");
                    }
                } else {
                    player.sendMessage("§8[§e!§8]§a Dafür reichen deine Recht nicht aus!");
                }
            }
            return false;
        }


}
