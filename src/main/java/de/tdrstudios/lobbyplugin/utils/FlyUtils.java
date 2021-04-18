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
                Message message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyA");
                chat.send(Chat.getErrorColor() + message.replaceContent("%Status%", Chat.getAccentColor() + "flying" + Chat.getErrorColor()));
                return;
            }
            if (!player.isFlying() && !fly) {
                Message message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyA");
                chat.send(Chat.getErrorColor() + message.replaceContent("%Status%", Chat.getAccentColor() + "standing" + Chat.getErrorColor()));
                return;
            }
            player.setAllowFlight(fly);
            player.setFlying(fly);
            if (fly) {
                Message message = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA");
                player.sendMessage(message.getContent());
                chat.send(message.replaceContent("%Mode%", Chat.getAccentColor() + "flying" + Chat.getChatColor()));
            }
            else {
                Message message = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA");
                player.sendMessage(message.getContent());
                chat.send(message.replaceContent("%Mode%", Chat.getAccentColor() + "standing" + Chat.getChatColor()));
            }
        }
        else {
            chat = new Chat(sender);
            if (player.isFlying() && fly) {
                Message message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyB");
                chat.send(Chat.getErrorColor() + message.replaceMessage("%Status%", Chat.getAccentColor() + "flying" + Chat.getErrorColor()).replaceContent("%Target%", Chat.getAccentColor() + player.getName() + Chat.getErrorColor()));
                return;
            }
            if (!player.isFlying() && !fly) {
                Message message = LobbyPlugin.getMessageManager().getMessageByName("fly.alreadyB");
                chat.send(Chat.getErrorColor() + message.replaceMessage("%Status%", Chat.getAccentColor() + "standing" + Chat.getErrorColor()).replaceContent("%Target%", Chat.getAccentColor() + player.getName() + Chat.getErrorColor()));
                return;
            }
            player.setAllowFlight(fly);
            player.setFlying(fly);
            if (fly) {
                Message message1 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesB");
                Message message2 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA");
                Chat.sendFast(player, message2.replaceContent("%Mode%", Chat.getAccentColor() + "flying" + Chat.getChatColor()));
                chat.send(message1.replaceMessage("%Mode%", Chat.getAccentColor() + "flying" + Chat.getChatColor()).replaceContent("%Target%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()));
            }
            else {
                Message message1 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesB");
                Message message2 = LobbyPlugin.getMessageManager().getMessageByName("fly.succsesA");
                Chat.sendFast(player, message2.replaceContent("%Mode%", Chat.getAccentColor() + "standing" + Chat.getChatColor()));
                chat.send(message1.replaceMessage("%Mode%", Chat.getAccentColor() + "standing" + Chat.getChatColor()).replaceContent("%Target%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()));
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
        Message message4 = new Message("fly.succsesB", ConfigUtils.getString("tdrstudios.commands.fly.msgs.succsesB"));
        LobbyPlugin.getMessageManager().registerMessage(message4);
    }

}