package de.tdrstudios.lobbyplugin.msgs;

import de.tdrstudios.lobbyplugin.Chat;
import org.bukkit.command.Command;

public class UsageMessage extends Message{
    public static String getNameFIX(Command command) {
        return  "tdrstudios.usage.invalid." + command.getName();
    }
    public UsageMessage(Command command) {
        super("tdrstudios.usage.invalid." + command.getName(), Chat.getErrorColor() + "Please care about the usage :\"" + Chat.getAccentColor() + command.getUsage() + Chat.getErrorColor() + "\"!");

    }
}
