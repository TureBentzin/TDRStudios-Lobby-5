package net.juligames.lobbyplugin.utils;

import de.bentzin.tools.silkworm.Silkworm;
import org.bukkit.Bukkit;

public class StopServerOperation extends Silkworm {

    @Override
    public void run() {
        Bukkit.getServer().shutdown();
    }
}
