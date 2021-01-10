package de.tdrstudios.lobbyplugin.tabcomplete;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

/**
 * @STOPSHIP: 10.01.2021
 * @Autonom This can manage your TabComplete fully self!
 */
public class TabComplete implements TabCompleter {

    /**
     * @STOPSHIP: 10.01.2021
     * @Autonom This can manage your TabComplete fully self!
     */
    public TabComplete(List<List<Argument>> arguments) {
        setArguments((List<Argument>[]) arguments.toArray());
    }

    //example: List[0] == Hallo,Tschüss
    //         List[1] == Admins,Alle
    private List<Argument>[] arguments;

    public void setArguments(List<Argument>[] arguments) {
        this.arguments = arguments;
    }

    public List<Argument>[] getArguments() {
        return arguments;
    }

    @Override
    public @Nullable List<String> onTabComplete( CommandSender commandSender, Command command, String label, String[] args) {
        onTabComplete(commandSender, command, label, args);
        int length = args.length; // get length of player insert arguments
        List<String> r = null;
        if(getArguments().length >= length) { // Check if the TabArguments habe the same or a bigger length then the player insert!
           List<Argument> argumentList = arguments[length]; // extract a List of Arguments for the player insert length!
            for(Argument argument : argumentList) {
                if(argument.hasDepends()) {
                    //Get the Argument before
                    String arg0 = args[length -1];
                    if(argument.getDepends().contains(arg0))
                        r.add(argument.getArgument());
                }else {
                    r.add(argument.getArgument());
                }

            }
          // Add: Soon this checks for new written content (StartsWith)
        }else
            r.add(" ");
        return r;
    }

    /**
     *
     * @param sender
     * @param cmd
     * @param label
     * @param args insert by Player
     * @param arguments given by Plugin
     * @implNote This acts like a Event!
     */
    public void onTabComplete(CommandSender sender,  Command cmd,  String label, String[] args, List<Argument>[] arguments) {
    }
}
