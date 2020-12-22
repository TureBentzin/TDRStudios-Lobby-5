package net.juligames.lobbyplugin;

import net.juligames.lobbyplugin.msgs.Message;
import net.juligames.lobbyplugin.msgs.MessageManager;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {

    @Deprecated
    public static String buildSendS(String message, boolean withPrefix) {
        if(withPrefix) {
        return getPrefix() + message; }
        return message;
    }

    private static String Prefix = "null ";

    public static String getPrefix() {
        return Prefix;
    }
    public static void setPrefix(String prefix) {
        Prefix = prefix;
    }

    private static ChatColor chatColor = ChatColor.AQUA;
    public static ChatColor getChatColor() {
        return chatColor;
    }
    public static void setChatColor(ChatColor chatColor) {
        Chat.chatColor = chatColor;
    }
    private static ChatColor ErrorColor = ChatColor.RED;

    private static ChatColor AccentColor = ChatColor.GRAY;

    public static ChatColor getAccentColor() {
        return AccentColor;
    }

    public static ChatColor getErrorColor() {
        return ErrorColor;
    }
    // public static String















    //For Object
    public Player[] players;

    public void setPlayers(Player[] players) {
        this.players = players;
    }

    public Player[] getPlayers() {
        return players;
    }
    public void send(Player player, String message) {
        player.sendMessage(getPrefix() + message);
    }
    public void send(Player[] players, String message) {
       setPlayers(players);
       send(message);
    }
    public void send(String message) {
        if(getPlayers() !=null) {
            for (Player player : getPlayers()) {
                if (player != null) {
                    player.sendMessage(getPrefix() + message);
                }
            }
        }
    }
    public String buildSend(String message) {
        return getPrefix() + message;
   }

   public void sendMessage(Message message) {
        send(message.getContent());
   }
   public void sendMessage(int id) {
        sendMessage(LobbyPlugin.getMessageManager().getMessageByID(id));
   }
   public void sendMessage(String name) {
        sendMessage(LobbyPlugin.getMessageManager().getMessageByName(name));
   }
}
