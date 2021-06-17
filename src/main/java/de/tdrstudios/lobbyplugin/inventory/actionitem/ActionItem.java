package de.tdrstudios.lobbyplugin.inventory.actionitem;

import de.tdrstudios.lobbyplugin.commands.LobbyMaintenanceCommand;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryAction;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;


public abstract class ActionItem extends ItemStack{

    public static final String DEFAULT_PATH = "tdrstudios.items";
    private ItemDefaults defaults;
    public ItemDefaults getDefaults() {
        return defaults;
    }

    private String name;
    private String configPath;

    private int slot;
    private Material material;
    private final int count = 1; //<- HardCode!
    private boolean active = false;
    private String displayName;

    public String getConfigPath() {
        return configPath;
    }
    public String getName() {
        return name;
    }
    public String getDisplayName() {
        return displayName;
    }
    public void setName(String name) {
        this.name = name;
    }
    private void setConfigPath(String configPath) {
        this.configPath = configPath;
    }
    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }
    public void setSlot(int slot) {
        this.slot = slot;
    }

    public int getCount() {
        return count;
    }
    public int getSlot() {
        return slot;
    }
    public Material getMaterial() {
        return material;
    }

    public void registerConfigurations() {
        ConfigUtils.registerConfiguration(configPath + "." + name + ".material", getDefaults().getMaterial());
        ConfigUtils.registerConfiguration(configPath + "." + name + ".displayname", getDefaults().getDisplayName());
        ConfigUtils.registerConfiguration(configPath + "." + name + ".active", getDefaults().isActive());
        ConfigUtils.registerConfiguration(configPath + "." + name + ".slot", getDefaults().getSlot());
    }
    public void loadConfigurations() throws InvalidConfigurationException {
        String pattern = configPath + "." + name + ".";
                setMaterial(ConfigUtils.getMaterial(pattern + "material"));
                setDisplayName(ConfigUtils.getString(pattern + "displayname"));
                setActive(ConfigUtils.getBoolean(pattern + "active"));
                setSlot(ConfigUtils.getConfig().getInt(pattern + "slot"));
    }

    public ActionItem(String name, String configPath, ItemDefaults defaults) throws InvalidConfigurationException {

        setConfigPath(configPath);
        setName(name);
        setMaterial(ConfigUtils.getMaterial(configPath + ""));
        this.defaults = defaults;

        registerConfigurations();
        loadConfigurations();
    }

    public ActionItem(String name, ItemDefaults defaults) throws InvalidConfigurationException {
        setConfigPath(ActionItem.DEFAULT_PATH);
        setName(name);
        setMaterial(ConfigUtils.getMaterial(configPath + ""));
        this.defaults = defaults;

        registerConfigurations();
        loadConfigurations();
    }



    public void setActive(boolean active) {
        this.active = active;
        if(active){
            ItemMeta meta = getItemMeta();
            meta.addEnchant(Enchantment.LOYALTY, 1, true);
            setItemMeta(meta);
        }else {
            ItemMeta meta = getItemMeta();
            meta.removeEnchant(Enchantment.LOYALTY);
            setItemMeta(meta);
        }
    }

    public boolean isActive() {
        return active;
    }

    public abstract void onClick(ActionParameter actionParameter);

    public static class ActionParameterClass implements de.tdrstudios.lobbyplugin.inventory.actionitem.ActionParameter {

        public ActionParameterClass (InventoryClickEvent event) {
            this.event = event;
            this.clicker = (Player) event.getWhoClicked();
            this.humanClicker = event.getWhoClicked();
            this.clickType = event.getClick();
            this.inventoryAction = event.getAction();
        }

        private Player clicker;
        @Override
        public Player getClicker() {
            return clicker;
        }

        private InventoryAction inventoryAction;
        @Override
        public InventoryAction getInventoryAction() {
            return inventoryAction;
        }

        private HumanEntity humanClicker;
        @Override
        public HumanEntity getHumanClicker() {
            return humanClicker;
        }
        private ClickType clickType;
        @Override
        public ClickType getClickType() {
            return clickType;
        }

        private InventoryClickEvent event;
        @Override
        public InventoryClickEvent getEvent() {
            return null;
        }
    }

    public static class ItemDefaultsClass implements de.tdrstudios.lobbyplugin.inventory.actionitem.ItemDefaults {

        public ItemDefaultsClass(boolean active, int slo, String displayName, Material material) {
            this.active = active;
            this.slot = slot;
            this.displayName = displayName;
            this.material = material;
        }
        private boolean active;
        @Override
        public boolean isActive() {
            return active;
        }

        private int slot;
        @Override
        public int getSlot() {
            return slot;
        }

        private String displayName;
         @Override
        public String getDisplayName() {
            return displayName;
        }

        private Material material;
         @Override
        public Material getMaterial() {
            return material;
        }
    }

}
