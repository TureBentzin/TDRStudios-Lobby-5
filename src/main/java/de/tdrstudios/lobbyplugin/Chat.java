package de.tdrstudios.lobbyplugin;

import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import java.io.PrintStream;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 * The type Chat.
 */
public class Chat {

    public Chat(Player player) {
        setPlayers(new Player[]{player});
    }

    /**
    Default constructor!
     */
    public Chat() {

    }

    /**
     * Build send s string.
     *
     * @param message    the message
     * @param withPrefix the with prefix
     * @return the string
     */
    @Deprecated
    public static String buildSendS(String message, boolean withPrefix) {
        if(withPrefix) {
        return getPrefix() + Chat.getChatColor() + message; }
        return Chat.getChatColor() + message;
    }

    private static String Prefix = "null ";

    /**
     * Gets prefix.
     *
     * @return the prefix
     */
    public static String getPrefix() {
        return Prefix;
    }

    /**
     * Sets prefix.
     *
     * @param prefix the prefix
     */
    public static void setPrefix(String prefix) {
        Prefix = prefix;
    }

    private static ChatColor chatColor = ChatColor.AQUA;

    /**
     * Gets chat color.
     *
     * @return the chat color
     */
    public static ChatColor getChatColor() {
        return chatColor;
    }


    /**
     * Sets chat color.
     *
     * @param chatColor the chat color
     */
    public static void setChatColor(ChatColor chatColor) {
        Chat.chatColor = chatColor;
    }

    private static void setAccentColor(ChatColor accentColor) {
        AccentColor = accentColor;
    }

    private static void setErrorColor(ChatColor errorColor) {
        ErrorColor = errorColor;
    }

    private static ChatColor ErrorColor = ChatColor.RED;

    private static ChatColor AccentColor = ChatColor.GRAY;

    /**
     * Gets accent color.
     *
     * @return the accent color
     */
    public static ChatColor getAccentColor() {
        return AccentColor;
    }

    /**
     * Gets error color.
     *
     * @return the error color
     */
    public static ChatColor getErrorColor() {
        return ErrorColor;
    }
    // public static String

    public static void loadColors() {
        setChatColor(ChatColor.valueOf(ConfigUtils.getString("tdrstudios.system.color")));
        setAccentColor(ChatColor.valueOf(ConfigUtils.getString("tdrstudios.system.accentcolor")));
        setErrorColor(ChatColor.valueOf(ConfigUtils.getString("tdrstudios.system.errorcolor")));
    }

    public static final String logo = "\n" +
            " \n"+
            "   ████████╗██████╗░██████╗░░██████╗████████╗██╗░░░██╗██████╗░██╗░█████╗░░██████╗\n" +
            "   ╚══██╔══╝██╔══██╗██╔══██╗██╔════╝╚══██╔══╝██║░░░██║██╔══██╗██║██╔══██╗██╔════╝\n" +
            "   ░░░██║░░░██║░░██║██████╔╝╚█████╗░░░░██║░░░██║░░░██║██║░░██║██║██║░░██║╚█████╗░\n" +
            "   ░░░██║░░░██║░░██║██╔══██╗░╚═══██╗░░░██║░░░██║░░░██║██║░░██║██║██║░░██║░╚═══██╗\n" +
            "   ░░░██║░░░██████╔╝██║░░██║██████╔╝░░░██║░░░╚██████╔╝██████╔╝██║╚█████╔╝██████╔╝\n" +
            "   ░░░╚═╝░░░╚═════╝░╚═╝░░╚═╝╚═════╝░░░░╚═╝░░░░╚═════╝░╚═════╝░╚═╝░╚════╝░╚═════╝░\n" +
            " ";
    public static void printLogo(ConsoleCommandSender console, ChatColor chatColor) {
        console.sendMessage(logo.replaceAll("█" , chatColor + "█" + ChatColor.RESET));
    }

    public static void printLogo() {
        printLogo(Bukkit.getConsoleSender(), ChatColor.WHITE);
    }
    public static void printLogo(ChatColor chatColor) {
        printLogo(Bukkit.getConsoleSender(), chatColor);
    }

    /**
     * The Players.
     */
//For Object
    public Player[] players;

    /**
     * Sets players.
     *
     * @param players the players
     */
    public void setPlayers(Player[] players) {
        this.players = players;
    }

    /**
     * Get players player [ ].
     *
     * @return the player [ ]
     */
    public Player[] getPlayers() {
        return players;
    }

    /**
     * Send.
     *
     * @param player  the player
     * @param message the message
     */
    public void send(Player player, String message) {
        player.sendMessage(getPrefix() + message);
    }


    @Deprecated
    public static void sendFast(Player player, String message) {
        player.sendMessage(getPrefix() + message);
    }
    @Deprecated
    public static void sendFast(CommandSender sender, String message) {
        sender.sendMessage(getPrefix() + message);
    }
    @Deprecated
    public static void sendFast(CommandSender sender, Message message) {
        sender.sendMessage(getPrefix() + message.getContent());
    }
    /**
     * Send.
     *
     * @param players the players
     * @param message the message
     */
    public void send(Player[] players, String message) {
       setPlayers(players);
       send(message);
    }

    /**
     * Send.
     *
     * @param message the message
     */
    public void send(String message) {
        if(getPlayers() !=null) {
            for (Player player : getPlayers()) {
                if (player != null) {
                    player.sendMessage(getPrefix() + message);
                }
            }
        }
    }

    /**
     * Build send string.
     *
     * @param message the message
     * @return the string
     */
    public String buildSend(String message) {
        return getPrefix() + message;
   }

    /**
     * Send message.
     *
     * @param message the message
     */
    public void sendMessage(Message message) {
        send(message.getContent());
   }

    /**
     * Send message.
     *
     * @param id the id
     */
    public void sendMessage(int id) {
        sendMessage(LobbyPlugin.getMessageManager().getMessageByID(id));
   }

    /**
     * Send message.
     *
     * @param name the name
     */
    public void sendMessage(String name) {
        Message send = LobbyPlugin.getMessageManager().getMessageByName(name);
        if(send != null) {
            sendMessage(send);
        }else {
            send(name);
        }
   }

    @Override
    public String toString() {
        return "Chat{" +
                "players=" + Arrays.toString(players) +
                '}';
    }

    public void send(boolean b) {
        send(""+ b);
    }

    public void lineSeperator() {
        for (Player player : getPlayers()) {
            player.sendMessage("    ");
        }
    }
}
