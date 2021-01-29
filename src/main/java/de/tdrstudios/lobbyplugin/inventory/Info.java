package de.tdrstudios.lobbyplugin.inventory;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Info implements Listener {
  private final String GUI_NAME = "§a►  by Zzocker77";
  
  private LobbyPlugin plugin;
  
  private FileConfiguration config;
  
  private final String ROOT = "InfoNames";
  
  public void openGUI(Player player) {
    ItemStack diamond = new ItemStack(Material.DIAMOND);
    ItemMeta itemMetadiamond = diamond.getItemMeta();
    itemMetadiamond.setDisplayName("§2§lInfo");
    diamond.setItemMeta(itemMetadiamond);
    ItemStack iron = new ItemStack(Material.IRON_INGOT);
    ItemMeta itemMetairon = iron.getItemMeta();
    itemMetairon.setDisplayName("§a§lProgrammiert von:");
    iron.setItemMeta(itemMetairon);
    ItemStack emerald = new ItemStack(Material.EMERALD);
    ItemMeta itemMetaemerald = emerald.getItemMeta();
    itemMetaemerald.setDisplayName("§3§lDiscord");
    emerald.setItemMeta(itemMetaemerald);
    ItemStack back = new ItemStack(Material.ARROW);
    ItemMeta itemMetaBack = back.getItemMeta();
    itemMetaBack.setDisplayName("§3§lZurück");
    back.setItemMeta(itemMetaBack);
    Inventory inventory = Bukkit.createInventory(null, 9, "§a►  by Zzocker77");
    inventory.setItem(0, new ItemStack(diamond));
    inventory.setItem(4, new ItemStack(emerald));
    inventory.setItem(8, new ItemStack(back));
    player.openInventory(inventory);
  }
  
  @EventHandler
  public void handleNavigatorOpen(PlayerInteractEvent event) {
    if (event.getItem() != null) {
      if (event.getItem().getType() != Material.GOLD_NUGGET)
        return; 
      if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        openGUI(event.getPlayer()); 
    } 
  }
  
  @EventHandler
  public void handleNavigatorGUIClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player))
      return; 
    Player player = (Player)event.getWhoClicked();
    if (event.getView().getTitle().equals("§a►  by TDRStudios")) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
        case DIAMOND:
          player.sendMessage("§8[§eInfo§8]§a Benutze den Teleporter um zu den verschiedenen Modis herum zu wechseln.");
          return;
        case IRON_INGOT:
          player.sendMessage("§8[§eInfo§8]§a Programmiert von Zzocker77 & TDRStudios kommt gerne auf unseren Discord Server §8https://discord.gg/jZKFZZf ");
          return;
        case EMERALD:
          player.sendMessage("§8[§eInfo§8]§a Unseren Discord findest du über den Link: §8https://discord.gg/mEv3bnh");
          return;
        case ARROW:
          player.closeInventory();
          return;
      }
      player.sendMessage("§aDas ist ein leerer Slot.");
    } 
  }
}
