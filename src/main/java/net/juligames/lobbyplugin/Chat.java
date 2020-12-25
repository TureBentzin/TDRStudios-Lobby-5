package net.juligames.lobbyplugin;

import net.juligames.lobbyplugin.msgs.Message;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.Arrays;

/**
 * The type Chat.
 */
public class Chat {

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
        return getPrefix() + message; }
        return message;
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
}
