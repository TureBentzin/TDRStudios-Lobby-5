package de.tdrstudios.lobbyplugin.utils;

import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

public class SoundUtils {

    private static Sound teleportSound = Sound.ENTITY_FIREWORK_ROCKET_TWINKLE;
    private static Sound selectSound = Sound.BLOCK_END_PORTAL_FRAME_FILL;
    private static Sound clickSound = Sound.UI_BUTTON_CLICK;

    /**
     * @implNote Play with a pitch of "2"
     */
    private static Sound toggleSound = Sound.UI_LOOM_SELECT_PATTERN;

    public static Sound getTeleportSound() {
        return teleportSound;
    }

    public static void setTeleportSound(Sound pTeleportSound) {
        teleportSound = pTeleportSound;
    }

    public static Sound getSelectSound() {
        return selectSound;
    }

    public static void setSelectSound(Sound selectSound) {
        SoundUtils.selectSound = selectSound;
    }

    public static void setClickSound(Sound clickSound) {
        SoundUtils.clickSound = clickSound;
    }

    public static Sound getClickSound() {
        return clickSound;
    }

    /**
     * @implNote Play with a pitch of "2"
     */
    public static Sound getToggleSound() {
        return toggleSound;
    }

    public static void setToggleSound(Sound toggleSound) {
        SoundUtils.toggleSound = toggleSound;
    }

    public static void playSound(Player player, Sound sound, int pitch) {
        player.playSound(player.getLocation(), sound, ConfigUtils.getConfig().getInt("tdrstudios.sound.volume"), pitch);
    }

    public static void playSound(Player player, Sound sound) {
        player.playSound(player.getLocation(), sound, ConfigUtils.getConfig().getInt("tdrstudios.sound.volume"), 0);
    }

    public static void playSound(Player[] player, Sound sound, int pitch) {
        for (int i = 0; i < player.length; i++) {
            player[i].playSound(player[i].getLocation(), sound, ConfigUtils.getConfig().getInt("tdrstudios.sound.volume"), pitch);
        }
    }

    public static void playSound(Player[] player, Sound sound) {
        for (int i = 0; i < player.length; i++) {
            player[i].playSound(player[i].getLocation(), sound, ConfigUtils.getConfig().getInt("tdrstudios.sound.volume"), 0);
        }
    }
}
