package net.juligames.lobbyplugin.msgs;

import net.juligames.lobbyplugin.Chat;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

public class LackingPermissionMessage extends Message{

    public LackingPermissionMessage(Permission permission) {
        super("tdrstudios.permission.lacking." + permission.getName() , Chat.getErrorColor() +"You are lacking the Permission : " + Chat.getAccentColor() + permission.getName() + Chat.getChatColor() + "!");
    }
}
