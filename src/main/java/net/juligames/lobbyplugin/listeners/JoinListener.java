package net.juligames.lobbyplugin.listeners;

import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import sun.security.krb5.Config;
import tdrstudios.Work_In_Progress;

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
    event.setJoinMessage( ConfigUtils.getConfig().getString("tdrstudios.join.msg").replace("%Player%" , event.getPlayer().getDisplayName()));

    Player player = event.getPlayer();
    Chat chat = new Chat();
    chat.setPlayers(new Player[]{player});
    SimpleDateFormat formatter = new SimpleDateFormat(ConfigUtils.getConfig().getString("tdrstudios.join.date.pattern"));
    SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy");
    Date date = new Date();
    System.out.println("Current Server Time: " + formatter.format(date));
    chat.send(ConfigUtils.getConfig().getString("tdrstudios.join.welcome").replace("%Player%" , player.getName()).replace("%Date%" , formatter.format(date)));


    ItemStack item1 = new ItemStack(Material.getMaterial(ConfigUtils.getConfig().getString("tdrstudios.hotbar.nav.material")));
    ItemMeta itemMeta = item1.getItemMeta();
    itemMeta.setDisplayName(ConfigUtils.getConfig().getString("tdrstudios.hotbar.nav.displayName"));
    item1.setItemMeta(itemMeta);

    FileConfiguration c = ConfigUtils.getConfig();
    ItemStack item2 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.info.material")));
    ItemMeta itemMeta2 = item2.getItemMeta();
    itemMeta2.setDisplayName(c.getString("tdrstudios.hotbar.info.displayName"));
    item2.setItemMeta(itemMeta2);

    ItemStack item3 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.settings.material")));
    ItemMeta itemMeta3 = item3.getItemMeta();
    itemMeta3.setDisplayName(c.getString("tdrstudios.hotbar.settings.displayName"));
    item3.setItemMeta(itemMeta3);

    ItemStack item4 = new ItemStack(Material.getMaterial(c.getString("tdrstudios.hotbar.stick.material")));
    ItemMeta itemMeta4 = item4.getItemMeta();
    itemMeta4.setDisplayName(c.getString("tdrstudios.hotbar.stick.displayName"));
    item4.setItemMeta(itemMeta4);

    PlayerInventory playerInventory = player.getInventory();
    player.getInventory().clear();
    playerInventory.setItem(7, item3);
    playerInventory.setItem(1, item2);
    playerInventory.setItem(4, item1);
    playerInventory.setItem(8, item4);
    FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
    World world = Bukkit.getWorld(config.getString("Spawn.World"));
    double x = config.getDouble("Spawn.X");
    double y = config.getDouble("Spawn.Y");
    double z = config.getDouble("Spawn.Z");
    float yaw = (float)config.getDouble("Spawn.Yaw");
    float pitch = (float)config.getDouble("Spawn.Pitch");
    Location location = new Location(world, x, y, z, yaw, pitch);
    player.teleport(location);
    player.setGameMode(GameMode.valueOf(ConfigUtils.getConfig().getString("tdrstudios.defaultGameMode"))); // DEBUG @WIP

    player.setExp(1.0F);
    player.setLevel(Integer.parseInt(formatter1.format(date)));
  }
}
