package de.tdrstudios.lobbyplugin.inventory;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.navigator.NavigatorUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import sun.security.krb5.Config;

public class CompassNavigator implements Listener {
  private String GUI_NAME = "§e► Error!" ;

    public String getGUI_NAME() {
        return GUI_NAME;
    }

    public Inventory openGUI(Player player) {
      player.openInventory(NavigatorUtils.getInventory());
      InventoryUtils.playOpeningSound(player);
      return NavigatorUtils.getInventory();
    }

    public void updateGUI_NAME() {
      GUI_NAME = ConfigUtils.getString("tdrstudios.inventorys.nav.name");
    }
  
  @EventHandler
  public void handleNavigatorOpen(PlayerInteractEvent event) {
    if (event.getItem() != null) {
      if (event.getItem().getType() != Material.getMaterial(ConfigUtils.getConfig().getString("tdrstudios.hotbar.nav.material")) && event.getItem().getItemMeta().getDisplayName() == ConfigUtils.getString("tdrstudios.hotbar.nav.name"))
        return; 
      if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        openGUI(event.getPlayer()); 
    } 
  }
  
  @EventHandler
  public void handleNavigatorGUIClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player))
      return; 
    Player player = (Player) event.getWhoClicked();
    if (event.getView().getTitle().equals(getGUI_NAME())) {

      FileConfiguration config;
      World world;
      double x, y, z;
      float yaw, pitch;
      Location location;

      TeleportType teleportType = null;

      event.setCancelled(true);
      config = LobbyPlugin.getPlugin().getConfig();

      switch (teleportType) {

        case SPAWN:

      }


      player.sendMessage("§aDas ist ein leerer Slot.");
    }


  }
  private void handleClick(Player player, int id, FileConfiguration configuration) {
      int x, y, z;
      float yaw, pitch;
      Location location;
    World world = Bukkit.getWorld(configuration.getString("lobbySpawn" + id + ".World"));
    x = configuration.getInt("lobbySpawn" + id + ".X");
    y = configuration.getInt("lobbySpawn" + id + ".Y");
    z = configuration.getInt("lobbySpawn" + id + ".Z");
    yaw = (float)configuration.getDouble("lobbySpawn" + id + ".Yaw");
    pitch = (float)configuration.getDouble("lobbySpawn" + id + ".Pitch");

    location = new Location(world, x, y, z,  yaw, pitch);

    player.teleport(location);
    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 10.0F, 1.0F);
    Message message = new Message("temp_handle_click" , ConfigUtils.getString("tdrstudios.msg.teleport.navigator"));
    message.replace("%MiniGame%" , Chat.getAccentColor() + ConfigUtils.getString("tdrstudios.inventorys.nav.itmes.MiniGame" + id + ".diplayname") + Chat.getChatColor());
     new Chat(player).sendMessage(message);
  }
}

enum TeleportType {
  SPAWN,
  MINIGAME;
}
