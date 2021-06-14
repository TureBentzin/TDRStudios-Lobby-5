package de.tdrstudios.lobbyplugin.enums;

import de.tdrstudios.lobbyplugin.Chat;
import org.bukkit.command.BlockCommandSender;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public enum  SenderType {

    PLAYER("Player"),
    CONSOLE("Console"),
    COMMAND_BLOCK("Commandblock"),
    UNKNOWN("Unknown"),
    ;

    private String text;
    public void setText(String text) {
        this.text = text;
    }
    @Deprecated
    /**
     * You can use .toString()
     */
    public String getText() {
        return text;
    }

    public static SenderType getFromCommandSender(CommandSender commandSender) {
        SenderType r = UNKNOWN;
        if(commandSender instanceof Player)
            r = SenderType.PLAYER;
        if(commandSender instanceof ConsoleCommandSender)
            r = SenderType.CONSOLE;
        if(commandSender instanceof BlockCommandSender)
            r = SenderType.CONSOLE;
        return r;
    }

    /**
     *
     * @return the message in error context!
     */
    public String format() {
        return " " + Chat.getAccentColor() + getText() + Chat.getErrorColor();
    }
    SenderType(String text) {
    setText(text);
    }

    @Override
    public String toString() {
        return text;
    }
}
