package de.tdrstudios.lobbyplugin.events;

import de.tdrstudios.additional.Work_In_Progress;
import de.tdrstudios.additional.debug.Point;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryUtils;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.Cancellable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.*;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.MainHand;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class GeneralEvents implements Listener {

    protected ArrayList<String> HideShow = new ArrayList<>();
    protected ArrayList<UUID> cooldown = new ArrayList<>();
    private final FileConfiguration c = ConfigUtils.getConfig();

    protected GeneralEvents() {

        LobbyPlugin.getMessageManager().registerMessage(new Message("de.tdrstudios.hideshow.hide", Chat.getChatColor() + ConfigUtils.getString("tdrstudios.hotbar.stick.chat.body") + Chat.getAccentColor() + " invisible" + Chat.getChatColor() + "!"));

        LobbyPlugin.getMessageManager().registerMessage(new Message("de.tdrstudios.hideshow.reveal", Chat.getChatColor() + ConfigUtils.getString("tdrstudios.hotbar.stick.chat.body") + Chat.getAccentColor() + " visible" + Chat.getChatColor() + "!"));
    }

    public static GeneralEvents getInstance() {
        return new GeneralEvents();
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
        if (e.getCurrentItem() != null)
            onManipulation((Player) e.getWhoClicked(), e);
    }

    @EventHandler

    public void onInventoryDrop(PlayerDropItemEvent e) {
        onManipulation(e.getPlayer(), e);
    }

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {

        if (!ConfigUtils.getConfig().getBoolean("tdrstudios.allowWeatherChange")) {
            e.setCancelled(true);
        }

    }

    @EventHandler
    public void blockbreak(BlockBreakEvent e) {
        onManipulation(e.getPlayer(), e);

    }

    private void onManipulation(Player player, Cancellable cancellable, boolean announce) {
        try {
            if (ConfigUtils.getBoolean("tdrstudios.manipulation.allow"))
                if (player.getGameMode() == GameMode.valueOf(ConfigUtils.getString("tdrstudios.manipulation.gamemode"))) {
                    if (player.hasPermission("tdrstudios.lobby.perms.manipulate")) {
                        cancellable.setCancelled(false);
                    } else {
                        Chat chat = new Chat();
                        chat.setPlayers(new Player[]{player});
                        if (announce)
                            chat.sendMessage(new LackingPermissionMessage("tdrstudios.lobby.perms.manipulate"));
                        cancellable.setCancelled(true);
                    }
                } else {
                    if (announce)
                        player.playSound(player.getLocation(), Sound.BLOCK_ANVIL_LAND, 30, 1);
                    //new Point(Thread.currentThread()).point();
                    cancellable.setCancelled(true);
                }
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }

    }

    private void onManipulation(Player player, Cancellable cancellable) {
        onManipulation(player, cancellable, true);
    }

    @EventHandler

    public void blockplace(BlockPlaceEvent e) {
        onManipulation(e.getPlayer(), e);
    }

    private void onManipulation(Cancellable cancellable, String path) {
        try {
            cancellable.setCancelled(!ConfigUtils.getBoolean(path));
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
    }

    @EventHandler
    public void onEnityExpolde(EntityExplodeEvent event) {
        onManipulation(event, "tdrstudios.manipulation.explosions.entity");
        if (!event.isCancelled())
            for (Player player : Bukkit.getOnlinePlayers()) {
                Location playerlocation = player.getLocation();
                if (playerlocation.distance(event.getLocation()) < 3)
                    new Chat(player).send(Chat.getErrorColor() + "BUMM!"); //This is a secret....
            }

    }

    @EventHandler
    public void onBlockExplode(BlockExplodeEvent event) {
        onManipulation(event, "tdrstudios.manipulation.explosions.block");
    }

    @EventHandler
    public void onFertilise(BlockFertilizeEvent event) {
        onManipulation(event, "tdrstudios.manipulation.manipulation.farmland.fertilize");
    }

    @EventHandler
    public void onGrow(BlockGrowEvent event) {
        onManipulation(event, "tdrstudios.manipulation.manipulation.farmland.grow");
    }

    @EventHandler
    public void onIgnite(BlockIgniteEvent event) {
        onManipulation(event, "tdrstudios.manipulation.fire.ignite");
    }

    @EventHandler
    public void onBurn(BlockBurnEvent event) {
        onManipulation(event, "tdrstudios.manipulation.fire.burn");
    }

    public void onBlockSpread(BlockSpreadEvent event) {
        Block block = event.getBlock();
        if (block.getType() == Material.FIRE) {
            try {
                if (!ConfigUtils.getBoolean("tdrstudios.manipulation.fire.spread")) {
                    event.setCancelled(true);

                }
            } catch (InvalidConfigurationException e) {
                e.printStackTrace();
            }
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onMuliPlace(BlockMultiPlaceEvent event) {
        if (event.getPlayer().hasPermission("tdrstudios.lobby.perms.manipulate") && event.getPlayer().getGameMode() == GameMode.valueOf(ConfigUtils.getString("tdrstudios.manipulation.gamemode"))) {
            event.setCancelled(false);
           // event.getPlayer().sendMessage("DEBUG: Multiplace allowed!");
        } else {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        List<String> stringList = new ArrayList<>();
        try {
            stringList = ConfigUtils.getList("tdrstudios.actions.whitelist");
        } catch (InvalidConfigurationException e) {
            e.printStackTrace();
        }
        Player player = event.getPlayer();
        Chat chat = new Chat(player);
        //chat.send(Chat.getAccentColor() + new Point(Thread.currentThread()).point());

        if (player.getItemInHand().getType() == Material.getMaterial(ConfigUtils.getString("tdrstudios.hotbar.stick.material"))) {


            if (event.getHand() == EquipmentSlot.HAND) {
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

            }
        }

        if (event.getClickedBlock() != null)
            if (stringList.contains(event.getClickedBlock().getType().toString())) {
                event.setCancelled(false);
            } else {
                onManipulation(player, event, false);
            }
    }



    public void onTEST(PlayerInteractEvent event) {
        event.getPlayer().sendMessage(event.getEventName() + " fired!");
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
        e.setQuitMessage(ConfigUtils.getConfig().getString("tdrstudios.leave.msg").replace("%Player%", player.getName()));
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
        manager.registerMessage(new Message("tdrstudios.hotbar.stick.show", "Every player is now " + Chat.getAccentColor() + "visible" + Chat.getChatColor() + "!"));

    }

}
