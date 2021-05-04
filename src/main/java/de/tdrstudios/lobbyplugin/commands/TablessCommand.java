package de.tdrstudios.lobbyplugin.commands;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.command.CommandExecutor;
import org.jetbrains.annotations.NotNull;

import java.util.List;


/**
 * This Command does not support TabComplete
 */
public abstract interface TablessCommand extends CommandExecutor, CommandMethods {
    @Override
    default @NotNull List<Argument>[] getTabComplete() {
        return new List[0];
    }
}
