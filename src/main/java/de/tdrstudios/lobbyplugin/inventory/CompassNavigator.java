package de.tdrstudios.lobbyplugin.inventory;

import de.tdrstudios.additional.debug.DebugConsole;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.utils.SoundUtils;
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
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class CompassNavigator implements Listener {
    private String GUI_NAME = "§e► Error!";

    public String getGUI_NAME() {
        return GUI_NAME;
    }

    public Inventory openGUI(Player player) {
        updateGUI_NAME();
        System.out.println("CompassNavigator.openGUI");
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
            if (event.getItem().getType().equals(Material.getMaterial(ConfigUtils.getConfig().getString("tdrstudios.hotbar.nav.material"))) && event.getItem().getItemMeta().getDisplayName().equals(ConfigUtils.getString("tdrstudios.hotbar.nav.displayName"))) {

                if (event.getHand().equals(EquipmentSlot.HAND)){

                    openGUI(event.getPlayer());
                    Chat.sendFast(event.getPlayer(), Chat.getAccentColor() + " -opened navigator: " + NavigatorUtils.getIndex());
                }
            }

        }
    }

    @EventHandler
    public void handleNavigatorGUIClick(InventoryClickEvent event) {
        event.getWhoClicked().sendMessage("handleNavigatorGUIClick!");
        if (!(event.getWhoClicked() instanceof Player))
            return;
        Player player = (Player) event.getWhoClicked();
        if (event.getView().getTitle().equals(getGUI_NAME())) {

            FileConfiguration configuration = ConfigUtils.getConfig();
            World world;
            double x, y, z;
            float yaw, pitch;
            Location location;

            TeleportType teleportType = null;
            if (event.getCurrentItem().getItemMeta().getDisplayName() == ConfigUtils.getString("tdrstudios.inventorys.nav.items.spawn.name")
                    && event.getCurrentItem().getType() == Material.getMaterial("tdrstudios.inventorys.nav.items.spawn.material"))
                teleportType = TeleportType.SPAWN;
            else
                teleportType = TeleportType.MINIGAME;
            event.setCancelled(true);


            switch (teleportType) {

                case SPAWN:
                    world = Bukkit.getWorld(configuration.getString("lobbySpawn.World"));
                    x = configuration.getInt("lobbySpawn.X");
                    y = configuration.getInt("lobbySpawn.Y");
                    z = configuration.getInt("lobbySpawn.Z");
                    yaw = (float) configuration.getDouble("lobbySpawn.Yaw");
                    pitch = (float) configuration.getDouble("lobbySpawn.Pitch");

                    location = new Location(world, x, y, z, yaw, pitch);

                    player.teleport(location);
                    player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 10.0F, 1.0F);
                    Message message = new Message("temp_handle_click", ConfigUtils.getString("tdrstudios.msg.teleport.navigator"));
                    message.replace("%MiniGame%", Chat.getAccentColor() + "Spawn" + Chat.getChatColor());
                    new Chat(player).sendMessage(message);
                    return;
                case MINIGAME:
                    boolean b = true;
                    int i = 1;
                    while (b) {
                        String path = "tdrstudios.inventorys.nav.items.MiniGame" + i;
                        if (ConfigUtils.isSet(path + ".name")) {
                            Material material = Material.getMaterial(ConfigUtils.getString(path + ".material"));
                            String name = ConfigUtils.getString(path + ".name");
                            ItemStack itemStack = event.getCurrentItem();
                            if (itemStack.getType().equals(material) && itemStack.getItemMeta().getDisplayName() == name) {
                                handleClick((Player) event.getWhoClicked(), i, configuration);
                            }
                        } else {
                            b = false;
                        }

                        i++;
                    }

            }


            player.sendMessage("§a Breakpoint 116 - CPN.j passed!");
        }else
        player.sendMessage("not this inventory!");

    }

    private void handleClick(Player player, int id, FileConfiguration configuration) {
        int x, y, z;
        float yaw, pitch;
        Location location;
        World world = Bukkit.getWorld(configuration.getString("lobbySpawn" + id + ".World"));
        x = configuration.getInt("lobbySpawn" + id + ".X");
        y = configuration.getInt("lobbySpawn" + id + ".Y");
        z = configuration.getInt("lobbySpawn" + id + ".Z");
        yaw = (float) configuration.getDouble("lobbySpawn" + id + ".Yaw");
        pitch = (float) configuration.getDouble("lobbySpawn" + id + ".Pitch");

        location = new Location(world, x, y, z, yaw, pitch);

        player.teleport(location);
        player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 10.0F, 1.0F);
        Message message = new Message("temp_handle_click", ConfigUtils.getString("tdrstudios.msg.teleport.navigator"));
        message.replace("%MiniGame%", Chat.getAccentColor() + ConfigUtils.getString("tdrstudios.inventorys.nav.items.MiniGame" + id + ".diplayname") + Chat.getChatColor());
        new Chat(player).sendMessage(message);
    }


    public static enum TeleportType {
        SPAWN,
        BACK,
        MINIGAME;
    }
}


