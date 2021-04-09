package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.permissions.Permission;

import java.util.List;

public  interface CommandMethods {

    public default void registerMessages() {}
    public default void generatePermission(String permission) {};
    public default List<Argument>[] getTabComplete(){return null;};
    public default void registerTabComplete() {};
}
