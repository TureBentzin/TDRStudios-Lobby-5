package de.tdrstudios.lobbyplugin.commands;

import org.bukkit.permissions.Permission;

import java.util.List;

public  interface CommandMethods {

    public void registerMessages();
    public void generatePermission(String permission);
    public List<String>[] registerTabComplete();
}
