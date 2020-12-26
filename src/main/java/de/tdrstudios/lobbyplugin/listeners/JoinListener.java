package de.tdrstudios.lobbyplugin.listeners;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import de.tdrstudios.additional.Work_In_Progress;

import java.text.SimpleDateFormat;
import java.util.Date;

@Work_In_Progress
public class JoinListener implements Listener {
    @EventHandler
/**
 * @param PlayerJoinEvent event
 * @see PlayerJoinEvent
 */
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.setJoinMessage(ConfigUtils.getConfig().getString("tdrstudios.join.msg").replace("%Player%", event.getPlayer().getDisplayName()));

        Player player = event.getPlayer();
        Chat chat = new Chat();
        chat.setPlayers(new Player[]{player});
        SimpleDateFormat formatter = new SimpleDateFormat(ConfigUtils.getConfig().getString("tdrstudios.join.date.pattern"));
        SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
        Date date = new Date();
        System.out.println("Current Server Time: " + formatter.format(date));
        chat.send(ConfigUtils.getConfig().getString("tdrstudios.join.welcome").replace("%Player%", player.getName()).replace("%Date%", formatter.format(date)));

        InventoryUtils.setInventory(player);

        FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
        if(config.getString("Spawn.X") != null && config.getString("Spawn.Y") != null && config.getString("Spawn.Z") != null
                && config.getString("Spawn.Yaw") != null && config.getString("Spawn.Pitch") != null && config.getString("Spawn.World") != null) {

            World world = Bukkit.getWorld(config.getString("Spawn.World"));
            double x = config.getDouble("Spawn.X");
            double y = config.getDouble("Spawn.Y");
            double z = config.getDouble("Spawn.Z");
            float yaw = (float) config.getDouble("Spawn.Yaw");
            float pitch = (float) config.getDouble("Spawn.Pitch");
            Location location = new Location(world, x, y, z, yaw, pitch);
            player.teleport(location);

        }else {
            System.out.println("Error: I can´t find a serverspawn!");
        }

        player.setGameMode(GameMode.valueOf(ConfigUtils.getConfig().getString("tdrstudios.defaultGameMode"))); // DEBUG @WIP

        player.setExp(ConfigUtils.getConfig().getInt("tdrstudios.hotbar.xp"));
        player.setLevel(Integer.parseInt(formatter1.format(date)));
    }
}
