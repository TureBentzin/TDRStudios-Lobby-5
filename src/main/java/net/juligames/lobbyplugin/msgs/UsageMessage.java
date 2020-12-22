package net.juligames.lobbyplugin.msgs;

import net.juligames.lobbyplugin.Chat;
import org.bukkit.command.Command;
import org.bukkit.permissions.Permission;

public class UsageMessage extends Message{
    public static String getNameFIX(Command command) {
        return  "tdrstudios.usage.invalid." + command.getName();
    }
    public UsageMessage(Command command) {
        super("tdrstudios.usage.invalid." + command.getName(), Chat.getErrorColor() + "Please care about the usage : " + Chat.getAccentColor() + command.getUsage() + Chat.getChatColor() + "!");


    }
}
