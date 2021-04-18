package de.tdrstudios.lobbyplugin.utils;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.Nullable;

public class FlyUtils {

    public static void setFly(boolean fly, Player player, @Nullable CommandSender sender) {
        Chat chat = new Chat(player);
        if(sender == null) {
            if (player.isFlying() && fly) {
                String message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyA").getContent();
                chat.send(Chat.getErrorColor() + message.replaceAll("%Status%", Chat.getAccentColor() + "flying" + Chat.getErrorColor()));
                return;
            }
            if (!player.isFlying() && !fly) {
                String message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyA").getContent();
                chat.send(Chat.getErrorColor() + message.replaceAll("%Status%", Chat.getAccentColor() + "standing" + Chat.getErrorColor()));
                return;
            }
            player.setAllowFlight(fly);
            player.setFlying(fly);
            if (fly) {
                String message = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA").getContent();
                chat.send(message.replaceAll("%Mode%", Chat.getAccentColor() + "flying" + Chat.getChatColor()));
            }
            else {
                String message = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA").getContent();
                chat.send(message.replaceAll("%Mode%", Chat.getAccentColor() + "standing" + Chat.getChatColor()));
            }
        }
        else {
            Chat chat2 = new Chat(sender);
            if (player.isFlying() && fly) {
                String message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyB").getContent();
                chat2.send(Chat.getErrorColor() + message.replaceAll("%Status%", Chat.getAccentColor() + "flying" + Chat.getErrorColor()).replaceAll("%Target%", Chat.getAccentColor() + player.getName() + Chat.getErrorColor()));
                return;
            }
            if (!player.isFlying() && !fly) {
                String message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyB").getContent();
                chat2.send(Chat.getErrorColor() + message.replaceAll("%Status%", Chat.getAccentColor() + "standing" + Chat.getErrorColor()).replaceAll("%Target%", Chat.getAccentColor() + player.getName() + Chat.getErrorColor()));
                return;
            }
            player.setAllowFlight(fly);
            player.setFlying(fly);
            if (fly) {
                String message1 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesB").getContent();
                String message2 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA").getContent();
                chat.send(message2.replaceAll("%Mode%", Chat.getAccentColor() + "flying" + Chat.getChatColor()));
                chat2.send(message1.replaceAll("%Mode%", Chat.getAccentColor() + "flying" + Chat.getChatColor()).replaceAll("%Target%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()));
            }
            else {
                String message1 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesB").getContent();
                String message2 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA").getContent();
                chat.send(message2.replaceAll("%Mode%", Chat.getAccentColor() + "standing" + Chat.getChatColor()));
                chat2.send(message1.replaceAll("%Mode%", Chat.getAccentColor() + "standing" + Chat.getChatColor()).replaceAll("%Target%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()));
            }
        }
    }

    public static void toggleFly(Player player, @Nullable CommandSender sender) {
        if(player.isFlying()) {
            setFly(false, player, sender);
        }
        else {
            setFly(true, player, sender);
        }
    }

    public boolean isflying(Player player) {
        if(player.isFlying()) {
            if (player.getAllowFlight()) {
                return true;
            }
            else {
                return false;
            }
        }
        else {
            return false;
        }
    }

    public static void registerMessages() {
        Message message = new Message("fly.succsesA", ConfigUtils.getString("tdrstudios.commands.fly.msgs.succsesA"));
        LobbyPlugin.getMessageManager().registerMessage(message);
        Message message2 = new Message("fly.succsesB", ConfigUtils.getString("tdrstudios.commands.fly.msgs.succsesB"));
        LobbyPlugin.getMessageManager().registerMessage(message2);
        Message message3 = new Message("fly.alreadyA", ConfigUtils.getString("tdrstudios.commands.fly.msgs.alreadyA"));
        LobbyPlugin.getMessageManager().registerMessage(message3);
        Message message4 = new Message("fly.alreadyB", ConfigUtils.getString("tdrstudios.commands.fly.msgs.alreadyB"));
        LobbyPlugin.getMessageManager().registerMessage(message4);
    }

}