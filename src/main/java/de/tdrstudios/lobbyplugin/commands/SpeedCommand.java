package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class SpeedCommand extends SimpleCommand{
    public SpeedCommand(Command command, Permission permission) {
        super(command, permission, null);

    }

    @Override
    public void onSimpleCommand(CommandSender sender, Command cmd, String label, String[] args) {

    }

    @Override
    public List<Argument>[] getTabComplete() {
        List<Argument> arguments1 = new ArrayList<>();

        return new List[]{arguments1};
    }

}
