package de.tdrstudios.lobbyplugin.tabcomplete;

import de.tdrstudios.additional.debug.DebugConsole;
import de.tdrstudios.lobbyplugin.commands.MyCommand;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * @STOPSHIP: 10.01.2021
 * @Autonom This can manage your TabComplete fully self!
 */
public class TabComplete implements TabCompleter {

    //example: List[0] == Hallo,Tschüss
    //         List[1] == Admins,Alle
    private List<Argument>[] arguments;

    /**
     * @STOPSHIP: 10.01.2021
     * @Autonom This can manage your TabComplete fully self!
     */
    public TabComplete(List<Argument>[] arguments) {
        setArguments(arguments);
    }

    public TabComplete() {
        List<Argument>[] argumentsList = new List[1];
        List<Argument> arguments = new ArrayList<>();
        arguments.add(new Argument(" "));
        argumentsList[0] = arguments;
        setArguments(argumentsList);
    }

    /**
     * @return a list with a empty String
     * @see MyCommand
     */
    public static List<Argument>[] getNullList() {
        List<Argument>[] args = new List[1];
        List<Argument> list1 = new ArrayList<>();
        list1.add(new Argument(" "));
        args[0] = list1;
        return args;
    }

    public List<Argument>[] getArguments() {
        return arguments;
    }

    public void setArguments(List<Argument>[] arguments) {
        this.arguments = arguments;
    }

    @Override
    public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
        if (getArguments() == null) {
            List<String> nullList = new ArrayList<>();
            nullList.add("");
            return nullList;
        }

        //DevTools.getDevToolsConsole().send("args: " + Arrays.deepToString(args) , "TabDebug" , ";");

        onTabCompleteEvent(commandSender, command, label, args, getArguments());
        int length = args.length; // get length of player insert arguments
        List<String> r = new ArrayList<>();
        DebugConsole.getDebugConsole().send("getArguments().length = " + getArguments().length);
        DebugConsole.getDebugConsole().send("length = " + length);
        if(getArguments().length >= length) { // Check if the TabArguments habe the same or a bigger length then the player insert!
           List<Argument> argumentList = arguments[length -1]; // extract a List of Arguments for the player insert length!

            for(Argument argument : argumentList) {
                if(argument.hasDepends()) {
                    // STOPSHIP: 09.04.2021
                    
                    //Get the Argument before
                    String arg0 = args[length - 2];
                    boolean playermode = Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arg0));
                    //commandSender.sendMessage("OnlinePlayers: " + Bukkit.getOnlinePlayers());
                    //commandSender.sendMessage("arg0: " + arg0);
                    //commandSender.sendMessage("playermode: " + playermode);
                    if(!playermode) {
                       /* commandSender.sendMessage("Depends: " + argument.getDependsAsString());
                        commandSender.sendMessage("Before: " + arg0);
                        commandSender.sendMessage("args: " + Arrays.toString(args));

                        */

                    if (argument.getDependsAsString().contains(arg0))
                        r.add(argument.getArgument());
                    else {
                        //commandSender.sendMessage("DEBUG: Depends for Argument not suc: " + argument.getArgument());
                    }
                }else {
                        if(Bukkit.getOnlinePlayers().contains(Bukkit.getPlayer(arg0))){ //Tripple check
                            r.add(argument.getArgument());
                        }else {
                          //  commandSender.sendMessage("DEBUG: Depends for Argument not suc: " + argument.getArgument());
                        }
                    }
                }else {
                    r.add(argument.getArgument());
                }

            }
            // Add: Soon this checks for new written content (StartsWith)
        } else
            r.add("");
        for (int i = 0; i < r.size(); i++) {
            if (r.get(i).equalsIgnoreCase("%Players%")) {
                r.remove("%Players%");
                if (Bukkit.getOnlinePlayers().size() > 0)
                    for (Player p : Bukkit.getOnlinePlayers()) {
                        if (!r.contains(p.getName()))
                            r.add(p.getName());
                    }
            }
        }
        return r;
    }

    /**
     * @param sender
     * @param cmd
     * @param label
     * @param args      insert by player
     * @param arguments given by plugin
     * @implNote This acts like a event!
     */
    public void onTabCompleteEvent(CommandSender sender, Command cmd, String label, String[] args, List<Argument>[] arguments) {
    }
}
