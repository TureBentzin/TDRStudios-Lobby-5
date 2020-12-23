package net.juligames.lobbyplugin.Navigator;

import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
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

public class Einstellungen implements Listener {
  private final String GUI_NAME = "§a►  Settings";
  
  private final String BEWERTEN_GUI_NAME = "§a►  Bewerten";
  
  private LobbyPlugin plugin;
  
  private FileConfiguration config;
  
  public void openGUI(Player player) {
    ItemStack diamond = new ItemStack(Material.LEGACY_REDSTONE_TORCH_ON);
    ItemMeta itemMetadiamond = diamond.getItemMeta();
    itemMetadiamond.setDisplayName("§lDaten Info");
    diamond.setItemMeta(itemMetadiamond);
    ItemStack blazerod = new ItemStack(Material.BLAZE_ROD);
    ItemMeta itemMetablazerod = blazerod.getItemMeta();
    itemMetablazerod.setDisplayName("§a§lUpdates");
    blazerod.setItemMeta(itemMetablazerod);
    ItemStack hook = new ItemStack(Material.TRIPWIRE_HOOK);
    ItemMeta itemMetahook = hook.getItemMeta();
    itemMetahook.setDisplayName("§3§lCommands");
    hook.setItemMeta(itemMetahook);
    ItemStack back = new ItemStack(Material.ARROW);
    ItemMeta itemMetaBack = back.getItemMeta();
    itemMetaBack.setDisplayName("§3§lZurück");
    back.setItemMeta(itemMetaBack);
    ItemStack bewerten = new ItemStack(Material.OAK_SIGN);
    ItemMeta itemMetabewerten = bewerten.getItemMeta();
    itemMetabewerten.setDisplayName("§lBewerten");
    bewerten.setItemMeta(itemMetabewerten);
    Inventory inventory = Bukkit.createInventory(null, 27, "§a►  Settings");
    inventory.setItem(13, new ItemStack(diamond));
    inventory.setItem(10, new ItemStack(blazerod));
    inventory.setItem(16, new ItemStack(hook));
    inventory.setItem(26, new ItemStack(back));
    inventory.setItem(0, new ItemStack(bewerten));
    player.openInventory(inventory);
  }
  
  public void openBewertenGUI(Player player) {
    ItemStack back = new ItemStack(Material.ARROW);
    ItemMeta itemMetaBack = back.getItemMeta();
    itemMetaBack.setDisplayName("§3§lZurück");
    back.setItemMeta(itemMetaBack);
    ItemStack redstone = new ItemStack(Material.REDSTONE);
    ItemMeta itemMetaredstone = redstone.getItemMeta();
    itemMetaredstone.setDisplayName("§lAuswertung");
    redstone.setItemMeta(itemMetaredstone);
    ItemStack limewool = new ItemStack(Material.GOLD_BLOCK);
    ItemMeta itemMetalimewool = limewool.getItemMeta();
    itemMetalimewool.setDisplayName("§lGut");
    limewool.setItemMeta(itemMetalimewool);
    ItemStack redwool = new ItemStack(Material.REDSTONE_BLOCK);
    ItemMeta itemMetaredwool = redwool.getItemMeta();
    itemMetaredwool.setDisplayName("§lSchlecht");
    redwool.setItemMeta(itemMetaredwool);
    Inventory inventory = Bukkit.createInventory(null, 9, "§a►  Bewerten");
    inventory.setItem(8, new ItemStack(back));
    inventory.setItem(3, new ItemStack(limewool));
    inventory.setItem(5, new ItemStack(redwool));
    inventory.setItem(0, new ItemStack(redstone));
    player.openInventory(inventory);
  }
  
  @EventHandler
  public void handleSettingsOpen(PlayerInteractEvent event) {
    if (event.getItem() != null) {
      if (event.getItem().getType() != Material.getMaterial(ConfigUtils.getConfig().getString("tdrstudios.hotbar.settings.material")))
        return; 
      if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        openGUI(event.getPlayer()); 
    } 
  }
  
  @EventHandler
  public void handleSettingsGUIClick(InventoryClickEvent event) {
    if (!(event.getWhoClicked() instanceof Player))
      return; 
    Player player = (Player) event.getWhoClicked();
    if (event.getView().getTitle().equals("§a►  Settings")) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
        case LEGACY_REDSTONE_TORCH_ON:
          if (player.hasPermission("DatenInfo")) {
            FileConfiguration setlobbyspawn1config = LobbyPlugin.getPlugin().getConfig();
            World setlobbyspawn1world = Bukkit.getWorld(setlobbyspawn1config.getString("lobbySpawn1.World"));
            double setlobbyspawn1x = setlobbyspawn1config.getDouble("lobbySpawn1.X");
            double setlobbyspawn1y = setlobbyspawn1config.getDouble("lobbySpawn1.Y");
            double setlobbyspawn1z = setlobbyspawn1config.getDouble("lobbySpawn1.Z");
            float setlobbyspawn1yaw = (float)setlobbyspawn1config.getDouble("lobbySpawn1.Yaw");
            float setlobbyspawn1pitch = (float)setlobbyspawn1config.getDouble("lobbySpawn1.Pitch");
            Location setlobbyspawn1location = new Location(setlobbyspawn1world, setlobbyspawn1x, setlobbyspawn1y, setlobbyspawn1z, setlobbyspawn1yaw, setlobbyspawn1pitch);
            FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
            World world = Bukkit.getWorld(config.getString("Spawn.World"));
            double x = config.getDouble("Spawn.X");
            double y = config.getDouble("Spawn.Y");
            double z = config.getDouble("Spawn.Z");
            float yaw = (float)config.getDouble("Spawn.Yaw");
            float pitch = (float)config.getDouble("Spawn.Pitch");
            Location location = new Location(world, x, y, z, yaw, pitch);
            FileConfiguration setlobbyspawn2config = LobbyPlugin.getPlugin().getConfig();
            World setlobbyspawn2world = Bukkit.getWorld(setlobbyspawn2config.getString("lobbySpawn2.World"));
            double setlobbyspawn2x = setlobbyspawn2config.getDouble("lobbySpawn2.X");
            double setlobbyspawn2y = setlobbyspawn2config.getDouble("lobbySpawn2.Y");
            double setlobbyspawn2z = setlobbyspawn2config.getDouble("lobbySpawn2.Z");
            float setlobbyspawn2yaw = (float)setlobbyspawn2config.getDouble("lobbySpawn2.Yaw");
            float setlobbyspawn2pitch = (float)setlobbyspawn2config.getDouble("lobbySpawn2.Pitch");
            Location setlobbyspawn2location = new Location(setlobbyspawn2world, setlobbyspawn2x, setlobbyspawn2y, setlobbyspawn2z, setlobbyspawn2yaw, setlobbyspawn2pitch);
            player.sendMessage("§3Spawn");
            player.sendMessage("" + location);
            player.sendMessage("§3Lobbyspawn1");
            player.sendMessage("" + setlobbyspawn1location);
            player.sendMessage("§3Lobbyspawn2");
            player.sendMessage("" + setlobbyspawn2location);
            break;
          } 
          player.sendMessage("§3Du hast leider zuwenig Recht um diese abzurufen");
          break;
        case TRIPWIRE_HOOK:
          player.sendMessage("§8[§eInfo§8]§a setup spawns");
          player.sendMessage("§3Spawnsetzen : /setlobbyspawn");
          player.sendMessage("§3Spawn1setzen : /setlobbyspawn1");
          player.sendMessage("§3Spawn2setzen : /setlobbyspawn2");
          player.sendMessage("§3Spawn3setzen : /setlobbyspawn3");
          player.sendMessage("§3Spawn4setzen : /setlobbyspawn4");
          player.sendMessage("§3Spawn5setzen : /setlobbyspawn5");
          break;
        case BLAZE_ROD:
          player.sendMessage("§8[§eInfo§8]§a Spieler Verstecken + Anzeigen drin seid dem 17.9.2020 ");
          player.sendMessage("§8[§eInfo§8]§a Großes Bug fix Update am 17.9.2020");
          break;
        case ARROW:
          player.closeInventory();
          break;
        case OAK_SIGN:
          openBewertenGUI(player);
          break;
        case REDSTONE:
          player.sendMessage("§8[§eInfo§8]§a Die Auswertung lautet: ");
          break;
        case GOLD_BLOCK:
          player.sendMessage("§8[§eInfo§8]§a Vielen Dank für deine Bewertung ");
          break;
        case REDSTONE_BLOCK:
          player.sendMessage("§8[§eInfo§8]§a Vielen Dank für deine Bewertung ");
          break;
        default:
          player.sendMessage("§aDas ist ein leerer Slot.");
          break;
      } 
    } 
    if (event.getView().getTitle().equals("§a►  Bewerten")) {
      event.setCancelled(true);
      switch (event.getCurrentItem().getType()) {
        case ARROW:
          player.closeInventory();
          return;
        case REDSTONE:
          player.sendMessage("§8[§eInfo§8]§a Diese Funktion kommt bald.");
          player.sendMessage("§8[§eInfo§8]§a Die Auswertung lautet: ");
          return;
        case GOLD_BLOCK:
          player.sendMessage("§8[§eInfo§8]§a Diese Funktion kommt bald.");
          player.sendMessage("§8[§eInfo§8]§a Vielen Dank für deine bewertung ");
          return;
        case REDSTONE_BLOCK:
          player.sendMessage("§8[§eInfo§8]§a Diese Funktion kommt bald.");
          player.sendMessage("§8[§eInfo§8]§a Vielen Dank für deine bewertung ");
          return;
      } 
      player.sendMessage("§aDas ist ein leerer Slot.");
    } 
  }
}
