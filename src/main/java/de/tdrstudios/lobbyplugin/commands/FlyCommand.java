package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.List;

public class FlyCommand extends SimpleCommand{
    //perm: de.tdrstudios.lobby.fly
    public FlyCommand(String permission) {
        super(LobbyPlugin.getPlugin().getCommand("fly"), new Permission(permission), getNullList());

    }

}
