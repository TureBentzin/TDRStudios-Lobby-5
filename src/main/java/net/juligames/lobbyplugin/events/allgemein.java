package net.juligames.lobbyplugin.events;

import java.util.ArrayList;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerGameModeChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class allgemein implements Listener {
  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void OnFood(FoodLevelChangeEvent e) {
    e.setCancelled(true);
    e.setFoodLevel(20);
  }
  
  @EventHandler
  public void onInventoryClickEvent(InventoryClickEvent e) {
    if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void onInentoryDrop(PlayerDropItemEvent e) {
    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void onWeatherChange(WeatherChangeEvent e) {
    e.setCancelled(true);
  }
  
  @EventHandler
  public void blockbreak(BlockBreakEvent e) {
    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void blockplase(BlockPlaceEvent e) {
    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  ArrayList<String> HideShow = new ArrayList<>();
  
  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    if (player.getItemInHand().getType() == Material.BLAZE_ROD)
      if (this.HideShow.contains(player.getName())) {
        this.HideShow.remove(player.getName());
        for (Player players : Bukkit.getOnlinePlayers())
          player.showPlayer(players); 
        player.sendMessage("§8[§eInfo§8]§a Alle Spieler sind nun sichtbar");
        PlayerInventory playerInventory = player.getInventory();
        ItemStack item1 = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item1.getItemMeta();
        itemMeta.setDisplayName("§3§lVerstecken");
        item1.setItemMeta(itemMeta);
        playerInventory.setItem(8, item1);
      } else {
        this.HideShow.add(player.getName());
        for (Player players : Bukkit.getOnlinePlayers())
          player.hidePlayer(players); 
        player.sendMessage("§8[§eInfo§8]§a Alle Spieler sind nun unsichtbar");
        PlayerInventory playerInventory = player.getInventory();
        ItemStack item1 = new ItemStack(Material.BLAZE_ROD);
        ItemMeta itemMeta = item1.getItemMeta();
        itemMeta.setDisplayName("§3§lAnzeigen");
        item1.setItemMeta(itemMeta);
        playerInventory.setItem(8, item1);
      }  
  }
  
  @EventHandler
  public void onJoin(PlayerJoinEvent event) {
    Player player = event.getPlayer();
    for (Player players : Bukkit.getOnlinePlayers()) {
      if (this.HideShow.contains(players.getName()))
        players.hidePlayer(player); 
    } 
  }
  
  @EventHandler
  public void onLeave(PlayerQuitEvent e) {
    Player player = e.getPlayer();
    e.setQuitMessage("§8[§4-§8]§4 " + e.getPlayer().getName());
  }
  
  @EventHandler
  public void canpickupitems(PlayerPickupItemEvent e) {
    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void onGamemodechange(PlayerGameModeChangeEvent e) {
    Player player = e.getPlayer();
    ItemStack item1 = new ItemStack(Material.COMPASS);
    ItemMeta itemMeta = item1.getItemMeta();
    itemMeta.setDisplayName("§3§lTeleporter");
    item1.setItemMeta(itemMeta);
    ItemStack item2 = new ItemStack(Material.GOLD_NUGGET);
    ItemMeta itemMeta2 = item2.getItemMeta();
    itemMeta2.setDisplayName("§3§lInfo");
    item2.setItemMeta(itemMeta2);
    ItemStack item3 = new ItemStack(Material.REDSTONE_COMPARATOR);
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
  }
}
