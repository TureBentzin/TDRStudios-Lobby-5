package de.tdrstudios.lobbyplugin.events;

import java.util.ArrayList;

import de.tdrstudios.additional.debug.Point;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.Sound;

import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.Event;
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

  public GeneralEvents() {

    LobbyPlugin.getMessageManager().registerMessage(new Message("de.tdrstudios.hideshow.hide" ,  Chat.getChatColor() + ConfigUtils.getString("tdrstudios.hotbar.stick.chat.body") + Chat.getAccentColor() + " invisible" + Chat.getChatColor() + "!"));

    LobbyPlugin.getMessageManager().registerMessage(new Message("de.tdrstudios.hideshow.reveal" ,  Chat.getChatColor() + ConfigUtils.getString("tdrstudios.hotbar.stick.chat.body") + Chat.getAccentColor() + " visible" + Chat.getChatColor() + "!"));
  }

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
    if(e.getCurrentItem() != null)
    onManipulation((Player) e.getWhoClicked(), e);
  }
  
  @EventHandler

  public void onInventoryDrop(PlayerDropItemEvent e) {
    onManipulation(e.getPlayer() ,e);
  }
  
  @EventHandler
  public void onWeatherChange(WeatherChangeEvent e) {

      if(!ConfigUtils.getConfig().getBoolean("tdrstudios.allowWeatherChange")) {
          e.setCancelled(true);
      }

  }
  
  @EventHandler
  public void blockbreak(BlockBreakEvent e) {
  onManipulation(e.getPlayer() , e);

  }

  private void onManipulation(Player player , Cancellable cancellable, boolean playSound){
    try {
      if(ConfigUtils.getBoolean("tdrstudios.manipulation.allow"))
        if(player.getGameMode() == GameMode.valueOf(ConfigUtils.getString("tdrstudios.manipulation.gamemode"))) {
          if (player.hasPermission(ConfigUtils.getString("tdrstudios.manipulation.permission"))) {
            cancellable.setCancelled(false);
          }else{
            Chat chat = new Chat();
            chat.setPlayers(new Player[]{player});
            if(playSound)
            chat.sendMessage(new LackingPermissionMessage(ConfigUtils.getString("tdrstudios.manipulation.permission")));
            cancellable.setCancelled(true);
          }
        }else {
          if(playSound)
          player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 30, 1);
          new Point(Thread.currentThread()).point();
          cancellable.setCancelled(true);
        }
    } catch (InvalidConfigurationException e) {
      e.printStackTrace();
    }

  }

  private void onManipulation(Player player, Cancellable cancellable) {
    onManipulation(player, cancellable , true);
  }
  
  @EventHandler

  public void blockplace(BlockPlaceEvent e) {
    onManipulation(e.getPlayer() , e);
  }
  
  ArrayList<String> HideShow = new ArrayList<>();

  @EventHandler
  public void onInteract(PlayerInteractEvent event) {
    Player player = event.getPlayer();
    Chat chat = new Chat(player);
    if(event.getClickedBlock() != null)
    chat.send(Chat.getAccentColor() + "You have interact: " + event.getAction() + "@" + event.getClickedBlock().getType().name());
    else
      chat.send(Chat.getAccentColor() + "You have interact: " + event.getAction() + "@" + "nothing");
    if(event.getItem() != null && event.getItem().getType() == Material.getMaterial(ConfigUtils.getString("tdrstudios.hotbar.stick.material"))) {

      if (player.getItemInHand().getType() == Material.getMaterial(ConfigUtils.getString("tdrstudios.hotbar.stick.material")))
        if (this.HideShow.contains(player.getName())) {
          this.HideShow.remove(player.getName());
          for (Player p : Bukkit.getOnlinePlayers()) {
            player.showPlayer(p);
          }

          chat.sendMessage(LobbyPlugin.getMessageManager().getMessageByName("de.tdrstudios.hideshow.reveal"));
          PlayerInventory playerInventory = player.getInventory();
          ItemStack item1 = new ItemStack(Material.getMaterial(ConfigUtils.getString("tdrstudios.hotbar.stick.material")));
          ItemMeta itemMeta = item1.getItemMeta();
          itemMeta.setDisplayName(ConfigUtils.getString("tdrstudios.hotbar.stick.naming.show"));
          item1.setItemMeta(itemMeta);
          playerInventory.setItem(ConfigUtils.getConfig().getInt("tdrstudios.hotbar.stick.slot"), item1);
        } else {

          this.HideShow.add(player.getName());
          for (Player players : Bukkit.getOnlinePlayers())
            player.hidePlayer(players);
          chat.sendMessage(LobbyPlugin.getMessageManager().getMessageByName("de.tdrstudios.hideshow.hide"));
          PlayerInventory playerInventory = player.getInventory();
          ItemStack item1 = new ItemStack(Material.getMaterial(ConfigUtils.getString("tdrstudios.hotbar.stick.material")));
          ItemMeta itemMeta = item1.getItemMeta();
          itemMeta.setDisplayName(ConfigUtils.getString("tdrstudios.hotbar.stick.naming.hide"));
          item1.setItemMeta(itemMeta);
          playerInventory.setItem(ConfigUtils.getConfig().getInt("tdrstudios.hotbar.stick.slot"), item1);
        }
    }else {
      if (player.hasPermission(ConfigUtils.getString("tdrstudios.manipulation.permission"))) {
        event.setCancelled(false);
      }else {
        chat.send(Chat.getErrorColor() + "This is" + Chat.getAccentColor() + " currently" + Chat.getErrorColor() + " not allowed!");
        event.setCancelled(true);
      }
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
    onManipulation(e.getPlayer(), e, false);
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
