package de.tdrstudios.lobbyplugin.utils;


import de.tdrstudios.lobbyplugin.Chat;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class KickUtils{

    final static String format = "%prefix% \n %cause& \n %footer%";

    public static boolean kickPlayer(Player player, String message, KickType kickType , CommandSender kicker) {
        if(kickType == KickType.SYSTEM) {
            player.kickPlayer(format.replaceAll("%prefix%", Chat.getPrefix()).replaceAll("%cause%", Chat.getChatColor() + message).replaceAll());
        }
    }


    public static enum KickType {
        TIME_OUT,
        AFK,
        SYSTEM,
        UNKNOWN;
    }


}
