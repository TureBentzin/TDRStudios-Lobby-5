package net.juligames.lobbyplugin.listeners;

import net.juligames.lobbyplugin.LobbyPlugin;
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

public class JoinListener implements Listener {
  @EventHandler
  public void onPlayerJoin(PlayerJoinEvent event) {
    event.setJoinMessage("§8[§e+§8]§a " + event.getPlayer().getName());
    Player player = event.getPlayer();
    player.sendMessage("§8[§eInfo§8]§a Willkommen auf dem JuliGames Server.");
    ItemStack item1 = new ItemStack(Material.COMPASS);
    ItemMeta itemMeta = item1.getItemMeta();
    itemMeta.setDisplayName("§3§lTeleporter");
    item1.setItemMeta(itemMeta);
    ItemStack item2 = new ItemStack(Material.GOLD_NUGGET);
    ItemMeta itemMeta2 = item2.getItemMeta();
    itemMeta2.setDisplayName("§3§lInfo");
    item2.setItemMeta(itemMeta2);
    ItemStack item3 = new ItemStack(Material.COMPARATOR);
    ItemMeta itemMeta3 = item3.getItemMeta();
    itemMeta3.setDisplayName("§3§lEinstellungen");
    item3.setItemMeta(itemMeta3);
    ItemStack item4 = new ItemStack(Material.BLAZE_ROD);
    ItemMeta itemMeta4 = item4.getItemMeta();
    itemMeta4.setDisplayName("§3§lSpieler §a§lAnzeigen §f| §4§lVerstecken");
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
    player.setGameMode(GameMode.SURVIVAL);
    player.setExp(1.0F);
    player.setLevel(2020);
  }
}
