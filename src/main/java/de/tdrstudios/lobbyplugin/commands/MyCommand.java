package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class MyCommand extends TabComplete implements CommandExecutor, CommandMethods{

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

    public MyCommand(Command command ,Permission permission) {

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

    /**
     *
     * @return a list with a empty String
     * @deprecated use TabComplete.getNullList
     * @see TabComplete
     */
    @Deprecated
    public static List<Argument>[] getNullList() {
        List<Argument>[] args = new List[1];
        List<Argument> list1 = new ArrayList<>();
        list1.add(new Argument(" "));
        args[0] = list1;
        return args;
    }

    /**
     *
     * @param parm The spaceholder
     * @return a list with a String filled with the parm
     */
    public static List<Argument>[] getNullList(String parm) {
        List<Argument>[] args = new List[1];
        List<Argument> list1 = new ArrayList<>();
        list1.add(new Argument(parm));
        args[0] = list1;
        return args;
    }

    @Override
    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args);

}
