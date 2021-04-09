package de.tdrstudios.lobbyplugin.utils;


import de.tdrstudios.lobbyplugin.Chat;
import jdk.nashorn.internal.runtime.Timing;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerEvent;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionAttachment;
import org.bukkit.permissions.PermissionAttachmentInfo;
import org.bukkit.plugin.Plugin;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

public class KickUtils{



    public static boolean kickPlayer(Player player, String message, @Nullable KickType kickType ,@Nullable CommandSender kicker) {
        if(kickType == null)
            kickType = KickType.UNKNOWN;
            player.kickPlayer(Chat.getPrefix() + kickType.getHeader() +"\n" + message  + "\n "+ getStringKicker(kicker));
            return !player.isOnline();
    }



    public static String getStringKicker(CommandSender kicker) {
        if(kicker !=null)
        return "\n" + Chat.getChatColor() + "kicked by " + Chat.getAccentColor() + kicker.getName() + Chat.getChatColor();
        else
            return "";
    }

    public static boolean kickAll(Player[] players , String message, boolean isAll) {
        for (Player player : players) {
            if(isAll)
                kickPlayer(player, message,  KickType.KICK_ALL, null);
            else
                kickPlayer(player, message,  KickType.SYSTEM, null);
        }
        for(Player player : players) {
            if(player != null)
                return false;
        }
        return true;
    }


    public enum KickType {
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
