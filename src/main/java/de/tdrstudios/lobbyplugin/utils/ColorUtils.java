package de.tdrstudios.lobbyplugin.utils;

import de.bentzin.tools.DevTools;
import de.bentzin.tools.integer.IntegerUtils;
import de.bentzin.tools.integer.RandomIntegerUtils;
import org.bukkit.ChatColor;

public class ColorUtils {

    private static final ChatColor[] chatColors = new ChatColor[]{
            ChatColor.AQUA,
            ChatColor.BLUE,
            ChatColor.LIGHT_PURPLE,
            ChatColor.GOLD,
            ChatColor.GREEN,
            ChatColor.RED,
            ChatColor.YELLOW,
            ChatColor.WHITE
    };

    public static ChatColor getRandomChatColor() {
        int i = RandomIntegerUtils.getRandomI(0, chatColors.length -1);
        if(i < 0)
           i = -i;
        return chatColors[i];
    }
}
