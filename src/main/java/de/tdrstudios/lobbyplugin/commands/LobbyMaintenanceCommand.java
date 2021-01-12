package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class LobbyMaintenanceCommand extends MyCommand {


    // STOPSHIP: 12.01.2021
    /**
     * @param command
     * @param permission
     * @param arguments
     * @STOPSHIP: 12.01.2021
     * @Autonom This is for maintenance not for other weird stuff! Thanks!
     */
    public LobbyMaintenanceCommand(Command command, Permission permission, List<Argument>[] arguments) {
        super(command, permission, arguments);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if(sender instanceof Player) {

        }else if (sender instanceof ConsoleCommandSender) {

        }else {

        }
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return null;
    }

    public void registerMessages() {

    }
}
