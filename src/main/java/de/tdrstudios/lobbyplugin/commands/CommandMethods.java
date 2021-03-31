package de.tdrstudios.lobbyplugin.commands;

import org.bukkit.permissions.Permission;

import java.util.List;

public  interface CommandMethods {

    public default void registerMessages() {}
    public default void generatePermission(String permission) {};
    public List<String>[] registerTabComplete();
}
