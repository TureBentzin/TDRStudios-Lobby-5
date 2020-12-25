package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.LackingPermissionMessage;
import net.juligames.lobbyplugin.msgs.MessageManager;
import net.juligames.lobbyplugin.msgs.UsageMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import tdrstudios.Work_In_Progress;

@Work_In_Progress
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

    public FixInventoryCommand(String command ,String permission) {
        setCommand(LobbyPlugin.getPlugin().getCommand(command));
        setPermission(new Permission(permission, "Permission for " + getClass().getName()));
        registerMessages(); // Change soon
    }
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

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
