package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.LackingPermissionMessage;
import net.juligames.lobbyplugin.msgs.MessageManager;
import net.juligames.lobbyplugin.msgs.UsageMessage;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class FixInventoryCommand implements CommandExecutor {
    private Permission permission;
    private Command command;

    protected Command getCommand() {
        return command;
    }

    public Permission getPermission() {
        return permission;
    }

    protected void setCommand(Command command) {
        this.command = command;
    }
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public FixInventoryCommand(Permission permission, Command command) {
        setCommand(command);
        setPermission(permission);
        registerMessages();
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if(sender instanceof Player) {

        }else {
            if(sender instanceof ConsoleCommandSender) {
                if(args.length == 1) {
                    if(Bukkit.getPlayer(args[0]) != null) {

                    }else {
                     if(args[0] == "all" | args[0] == "*" | args[0] == "@a") {

                     }
                    }
                }else {
                    sender.sendMessage("Please care about the usage: /fixinventory [Player/'*']");
                }
            }else {
                sender.sendMessage("Error: You cant execute this! -- this is a Problem of your sendertype! Report this error to the Devs if you aren´t using an CommandBlock!");
            }
        }
        return true;
    }

    public void registerMessages() {
        MessageManager messageManager = LobbyPlugin.getMessageManager();
        messageManager.registerMessage(new LackingPermissionMessage(getPermission()));
        messageManager.registerMessage(new LackingPermissionMessage(getPermission() + ".other"));
        messageManager.registerMessage(new LackingPermissionMessage(getPermission() + ".other.all"));

        messageManager.registerMessage(new UsageMessage(getCommand()));
    }
}
