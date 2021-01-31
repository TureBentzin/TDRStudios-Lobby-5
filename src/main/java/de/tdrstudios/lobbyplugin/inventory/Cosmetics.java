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
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

public class Cosmetics implements Listener {
  private final String GUI_NAME = "§a►  BETA TEST 1.0 | Cosmetics";
  
  private final String SHOP_NAME = "§a► Shop | Immer 24 Stunden";
  
  private LobbyPlugin plugin;
  
  private FileConfiguration config;
  
  public void openGUI(Player player) {
    ItemStack leatherhelmet = new ItemStack(Material.LEATHER_HELMET);
    ItemMeta itemMetaleatherhelmet = leatherhelmet.getItemMeta();
    itemMetaleatherhelmet.setDisplayName("§lMütze");
    leatherhelmet.setItemMeta(itemMetaleatherhelmet);
    ItemStack crown = new ItemStack(Material.GOLDEN_HELMET);
    ItemMeta itemMetacrown = crown.getItemMeta();
    itemMetacrown.setDisplayName("§lKrone");
    crown.setItemMeta(itemMetacrown);
    ItemStack back = new ItemStack(Material.ARROW);
    ItemMeta itemMetaBack = back.getItemMeta();
    itemMetaBack.setDisplayName("§3§lZurück");
    back.setItemMeta(itemMetaBack);
    ItemStack shop = new ItemStack(Material.OAK_SIGN);
    ItemMeta itemMetashop = shop.getItemMeta();
    itemMetashop.setDisplayName("§lShop");
    shop.setItemMeta(itemMetashop);
    Inventory inventory = Bukkit.createInventory(null, 27, "§a►  BETA TEST 1.0 | Cosmetics");
    inventory.setItem(5, new ItemStack(leatherhelmet));
    inventory.setItem(4, new ItemStack(crown));
    inventory.setItem(26, new ItemStack(back));
    inventory.setItem(18, new ItemStack(shop));
    player.openInventory(inventory);
  }
  
  public void shopGUI(Player player) {
    ItemStack item1 = new ItemStack(Material.DIAMOND_CHESTPLATE);
    ItemMeta itemMetaitem1 = item1.getItemMeta();
    itemMetaitem1.setDisplayName("§lGUCCI Pullover");
    item1.setItemMeta(itemMetaitem1);
    Inventory inventory = Bukkit.createInventory(null, 9, "§a► Shop | Immer 24 Stunden");
    inventory.setItem(4, new ItemStack(item1));
    player.openInventory(inventory);
  }
  
  @EventHandler
  public void handleNavigatorOpen(PlayerInteractEvent event) {
    if (event.getItem() != null) {
      if (event.getItem().getType() != Material.NETHER_STAR)
        return; 
      if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        openGUI(event.getPlayer()); 
    } 
  }
  
  @EventHandler
  public void handleNavigatorGUIClick(InventoryClickEvent event) {
    Player player = (Player)event.getWhoClicked();
    PlayerInventory pi = player.getInventory();
    ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
    ItemStack crown = new ItemStack(Material.GOLDEN_HELMET);
    ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
    ItemStack leggins = new ItemStack(Material.LEATHER_LEGGINGS);
    ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
    if (event.getView().getTitle().equals("§a►  BETA TEST 1.0 | Cosmetics")) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
        case LEATHER_HELMET:
          if (player.hasPermission("leathercap.cosmetic")) {
            pi.setArmorContents(null);
            pi.setHelmet(helmet);
          } else {
            player.sendMessage("§8[§eInfo§8]§a Dafür reichen deine Recht nicht aus.");
          } 
          return;
        case GOLDEN_HELMET:
          if (player.hasPermission("crown.cosmetic")) {
            pi.setArmorContents(null);
            pi.setHelmet(crown);
            player.sendMessage("§6 Ein König? Du?");
          } else {
            player.sendMessage("§8[§eInfo§8]§a Dafür reichen deine Recht nicht aus.");
          } 
          return;
        case ARROW:
          player.closeInventory();
          return;
        case OAK_SIGN:
          player.sendMessage("Der Shop");
          shopGUI(player);
          return;
      } 
      player.sendMessage("§aDas ist ein leerer Slot.");
    } 
  }
}
