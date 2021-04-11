package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

public  interface CommandMethods {

    public default void registerMessages() {}
    public default void generatePermission(String permission) {};
    public default List<Argument>[] getTabComplete(){return null;};
    public default void registerTabComplete() {};
    public default Permission getOtherPermission() {return new Permission("other");};
    public default JavaPlugin getPlugin() {return JavaPlugin.getPlugin(JavaPlugin.class);};
    default StackTraceElement[] getStackTrace() {return new StackTraceElement[0];};
}
