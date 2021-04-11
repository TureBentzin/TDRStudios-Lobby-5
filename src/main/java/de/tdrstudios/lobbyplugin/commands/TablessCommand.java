package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * This Command does not support TabComplete
 */
public abstract class TablessCommand extends MyCommand{

    public TablessCommand(Command command, Permission permission) {
        super(command, permission);
    }
    public TablessCommand(Command command) {
        super(command, new Permission(command.getPermission()));
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return new ArrayList<String>();
    }

    public final boolean isCompleteble = false;

    @Override
    public List<Argument>[] getArguments() {
        return getNullList();
    }
}
