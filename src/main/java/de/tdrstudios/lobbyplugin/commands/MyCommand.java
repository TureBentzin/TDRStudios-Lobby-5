package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class MyCommand extends TabComplete implements CommandExecutor {

    /**
     * @param arguments
     * @STOPSHIP: 10.01.2021
     * @Autonom This can manage your TabComplete and other stuff fully self!
     */
    public MyCommand(Command command ,Permission permission ,List<Argument>[] arguments) {
        super(arguments);
        setCommand(command);
        setPermission(permission);
        setPermission(permission);
    }
    private Command command;
    public Command getCommand() {
        return command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }

    private Permission permission;
    public void setPermission(Permission permission) {
        this.permission = permission;
    }
    public Permission getPermission() {
        return permission;
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

    @Override
    public abstract  @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args);
}
