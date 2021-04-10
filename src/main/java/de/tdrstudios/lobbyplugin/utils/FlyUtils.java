package de.tdrstudios.lobbyplugin.utils;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FlyUtils {

    public static void setFly(boolean fly, Player player, CommandSender sender) {
        if(sender == null) {
            player.setFlying(fly);
        }
        else {
            player.setFlying(fly);
        }
    }

    public static void toggleFly(Player player, CommandSender sender) {
        if (sender == null) {
            if(player.isFlying()) {
                player.setFlying(false);
            }
            else {
                player.setFlying(true);
            }
        }
        else {
            if(player.isFlying()) {
                player.setFlying(false);
            }
            else {
                player.setFlying(true);
            }
        }
    }

}
