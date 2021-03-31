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



    public static boolean kickPlayer(Player player, String message, @Nullable KickType kickType ,@Nullable CommandSender kicker) {
        if(kickType == null)
            kickType = KickType.UNKNOWN;
            player.kickPlayer(Chat.getPrefix() + "\n" + kickType.getHeader() + message + getStringKicker(kicker));
            return !player.isOnline();
    }



    public static String getStringKicker(CommandSender kicker) {
        if(kicker !=null)
        return "\n" + Chat.getChatColor() + "kicked by " + Chat.getAccentColor() + kicker.getName() + Chat.getChatColor();
        else
            return "";
    }


    public static enum KickType {
        TIME_OUT("-Timed out-"),
        AFK("-You were idle for too long-"),
        SYSTEM(""),
        KICK_ALL("-All players removed from this lobby-"),
        UNKNOWN("-You got kicked from this server-");

        String getHeader(ChatColor headerColor, ChatColor afterColor){
            return "\n" + headerColor + header + "\n" + afterColor;
        }
        String getHeader() {
            return getHeader(Chat.getAccentColor(), Chat.getChatColor());
        }
        String header = "";
        KickType(String header) {
            this.header = header;
        }
    }


}
