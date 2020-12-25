package net.juligames.lobbyplugin.msgs;

import net.juligames.lobbyplugin.Chat;
import org.bukkit.ChatColor;
import org.bukkit.permissions.Permission;

public class LackingPermissionMessage extends Message{

    public static String getNameFIX(Permission permission) {
      return  "tdrstudios.permission.lacking." + permission.getName();
    }
    private Permission permission;
    public void setPermission(Permission permission) {
        this.permission = permission;
    }

    public Permission getPermission() {
        return permission;
    }

    public LackingPermissionMessage(Permission permission) {
        super("tdrstudios.permission.lacking." + permission.getName(), Chat.getErrorColor() +"You are lacking the Permission :\"" + Chat.getAccentColor()+ permission.getName() + Chat.getErrorColor() + "\" !");
        setPermission(permission);
    }
    public LackingPermissionMessage(String permission) {
        super("tdrstudios.permission.lacking." + permission, Chat.getErrorColor() +"You are lacking the Permission :\"" + Chat.getAccentColor()+ permission + Chat.getErrorColor() + "\" !");
        setPermission(new Permission(permission));
    }
}
