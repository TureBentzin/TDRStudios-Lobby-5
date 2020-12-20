package net.juligames.lobbyplugin;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class Chat {
    public static String Prefix = "null ";
    public static String getPrefix() {
        return Prefix;
    }
    public static void setPrefix(String prefix) {
        Prefix = prefix;
    }

    public static ChatColor chatColor = ChatColor.AQUA;
    public static ChatColor getChatColor() {
        return chatColor;
    }
    public static void setChatColor(ChatColor chatColor) {
        Chat.chatColor = chatColor;
    }

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
        for(Player player : getPlayers()) {
            if(player != null) {
                player.sendMessage(getPrefix() + message);
            }
        }
    }
}
