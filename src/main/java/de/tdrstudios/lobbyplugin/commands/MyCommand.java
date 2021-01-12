package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public abstract class MyCommand extends TabComplete implements CommandExecutor {

    /**
     * @param arguments
     * @STOPSHIP: 10.01.2021
     * @Autonom This can manage your TabComplete fully self!
     */
    public MyCommand(List<Argument>[] arguments) {
        super(arguments);
    }
    private Command command;
    public Command getCommand() {
        return command;
    }
    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        return super.onTabComplete(commandSender, command, label, args);
    }
}
