package de.tdrstudios.lobbyplugin.events;

import java.util.ArrayList;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.Sound;

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

import de.tdrstudios.additional.Work_In_Progress;

public class GeneralEvents implements Listener {

  private FileConfiguration c = ConfigUtils.getConfig();

  @EventHandler
  public void onDamage(EntityDamageEvent e) {
    if (e.getEntity() instanceof Player)
      e.setCancelled(true); 
  }
  
  @EventHandler

  public void onFood(FoodLevelChangeEvent e) {

    e.setCancelled(true);
    e.setFoodLevel(20);
  }
  
  @EventHandler
  public void onInventoryClickEvent(InventoryClickEvent e) {
    if (e.getWhoClicked().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler

  public void onInventoryDrop(PlayerDropItemEvent e) {

    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler
  public void onWeatherChange(WeatherChangeEvent e) {

      if(!ConfigUtils.getConfig().getBoolean("tdrstudios.allowWeatherChange")) {
          e.setCancelled(true);
      }

  }
  
  @EventHandler
  public void blockbreak(BlockBreakEvent e) {
    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }
  
  @EventHandler

  public void blockplace(BlockPlaceEvent e) {

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
    e.setQuitMessage(ConfigUtils.getConfig().getString("tdrstudios.leave.msg").replace("%Player%" , player.getName()));
  }
  
  @EventHandler
  public void canpickupitems(PlayerPickupItemEvent e) {
    if (e.getPlayer().getGameMode() != GameMode.CREATIVE)
      e.setCancelled(true); 
  }



  //This will be change in Merge with development!
  @EventHandler
  public void onGamemodechange(PlayerGameModeChangeEvent e) {
    InventoryUtils.setInventory(e.getPlayer());
    LobbyPlugin.getLog().send("The player " + e.getPlayer().getName() + " has switched gamemode to " + e.getNewGameMode().name() + "!");
    float v = 30;
    float v1 = 1;
    //Send CLICK
    e.getPlayer().playSound(e.getPlayer().getLocation(), Sound.UI_STONECUTTER_SELECT_RECIPE, v, v1);

  }

  /**
   * @apiNote This cant be used yet! This Class haven´t recodet yet so you have to wait!
   */
  @Work_In_Progress
  public void registerMessages() {
    MessageManager manager = LobbyPlugin.getMessageManager();
    manager.registerMessage(new Message("tdrstudios.hotbar.stick.show" , "Every player is now " + Chat.getAccentColor() + "visible" + Chat.getChatColor() + "!"));

  }
}
