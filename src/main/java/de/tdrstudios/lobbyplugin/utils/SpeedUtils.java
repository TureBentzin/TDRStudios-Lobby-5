package de.tdrstudios.lobbyplugin.utils;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class SpeedUtils {

    public static final PotionEffect SPEED_EFFECT = new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, 2, false, false, false);
    public static Sound TOGGLE_SOUND = SoundUtils.getToggleSound();

    public static boolean hasSpeed(Player player) {
        return player.hasPotionEffect(SPEED_EFFECT.getType());
    }

    public static String getToggledMessage(boolean status) {
        String s = "on";
        if (!status)
            s = "off";
        return Chat.getChatColor() + ConfigUtils.getString("tdrstudios.utils.speed.msgs.toggled").replace(
                "%Status%", Chat.getAccentColor() + s + Chat.getChatColor());
    }

    public static String getToggledMessage(boolean status, CommandSender initiator) {
        String s = "on";
        if (!status)
            s = "off";
        return Chat.getChatColor() + ConfigUtils.getString("tdrstudios.commands.speed.msgs.toggled-byPlayer").replace(
                "%Status%", Chat.getAccentColor() + s + Chat.getChatColor()).replace(
                "%Player%", Chat.getAccentColor() + initiator.getName() + Chat.getChatColor()
        );
    }

    public static void toggleSpeed(Player player) {
        if (hasSpeed(player)) {
            player.removePotionEffect(SPEED_EFFECT.getType());
            Chat.sendFast(player, getToggledMessage(false));
        } else {
            SPEED_EFFECT.apply(player);
            Chat.sendFast(player, getToggledMessage(true));
        }
        SoundUtils.playSound(player, TOGGLE_SOUND, 2);
    }

    private void toggleSpeed(Player player, CommandSender initiator) {
        if (hasSpeed(player)) {
            player.removePotionEffect(SPEED_EFFECT.getType());
            Chat.sendFast(player, getToggledMessage(false, initiator));
            Chat.sendFast(initiator, ConfigUtils.getString("tdrstudios.commands.speed.msgs.toggled-byMe").replace("%Player%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()).replace("%Status%", Chat.getAccentColor() + "off" + Chat.getChatColor()));

        } else {
            SPEED_EFFECT.apply(player);
            Chat.sendFast(player, getToggledMessage(true, initiator));
            Chat.sendFast(initiator, ConfigUtils.getString("tdrstudios.commands.speed.msgs.toggled-byMe").replace("%Player%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()).replace("%Status%", Chat.getAccentColor() + "on" + Chat.getChatColor()));

        }
        SoundUtils.playSound(player, TOGGLE_SOUND, 2);
    }
    private void setSpeed(Player player, boolean b, CommandSender initiator) {
        if (!b) {
            player.removePotionEffect(SPEED_EFFECT.getType());
            Chat.sendFast(player, getToggledMessage(false, initiator));
            Chat.sendFast(initiator, ConfigUtils.getString("tdrstudios.commands.speed.msgs.toggled-byMe").replace("%Player%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()).replace("%Status%", Chat.getAccentColor() + "on" + Chat.getChatColor()));

        } else {
            SPEED_EFFECT.apply(player);
            Chat.sendFast(player, getToggledMessage(true, initiator));
            Chat.sendFast(initiator, ConfigUtils.getString("tdrstudios.commands.speed.msgs.toggled-byMe").replace("%Player%", Chat.getAccentColor() + player.getName() + Chat.getChatColor()).replace("%Status%", Chat.getAccentColor() + "off" + Chat.getChatColor()));

        }
        SoundUtils.playSound(player, TOGGLE_SOUND, 2);
    }
    private void setSpeed(Player player, boolean b) {
        if (!b) {
            player.removePotionEffect(SPEED_EFFECT.getType());
            Chat.sendFast(player, getToggledMessage(false));
        } else {
            SPEED_EFFECT.apply(player);
            Chat.sendFast(player, getToggledMessage(true));
        }
        SoundUtils.playSound(player, TOGGLE_SOUND, 2);
    }
}
