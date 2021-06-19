package de.tdrstudios.lobbyplugin.inventory.actionitem;

import de.bentzin.tools.console.Console;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import de.tdrstudios.lobbyplugin.utils.inventory.ActionItemContent;
import org.bukkit.Bukkit;
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
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Objects;


public abstract class ActionItem extends ItemStack{

    @Override
    public boolean isSimilar(@Nullable ItemStack stack) {
        if (stack == null) {
            return false;
        } else if (stack == this) {
            return true;
        } else {
            if(stack.hasItemMeta() && hasItemMeta()) {
                return ((stack.getAmount() == getAmount()) && (stack.getDurability() == getDurability()) &&
                (stack.getItemMeta().getDisplayName().equals(getItemMeta().getDisplayName())) && (stack.getItemMeta().getLore() == getItemMeta().getLore()));


            }else {
                return false;
            }
        }
    }

    public static final String DEFAULT_PATH = "tdrstudios.items";

    private ArrayList<ActionItemContent> actionItemContents = new ArrayList<ActionItemContent>();

    public ArrayList<ActionItemContent> getActionItemContents() {
        return actionItemContents;
    }

    public void setActionItemContents(ArrayList<ActionItemContent> actionItemContents) {
        this.actionItemContents = actionItemContents;
    }

    public void addActionItemContent(ActionItemContent actionItemContent) {
        if(!getActionItemContents().contains(actionItemContent))
        getActionItemContents().add(actionItemContent);
    }

    private ItemDefaults defaults;
    public ItemDefaults getDefaults() {
        return defaults;
    }

    private String name;
    private String configPath;

    private Console console;

    private int slot;
    private Material material;
    private final int count = 1; //<- HardCode!
    private boolean active = false;
    private String displayName;
    private final Enchantment activeEnchantment = Enchantment.LOYALTY;

    public Enchantment getActiveEnchantment() {
        return activeEnchantment;
    }
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
        ConfigUtils.registerConfiguration(configPath + "." + name + ".material", getDefaults().getMaterial().name());
        ConfigUtils.registerConfiguration(configPath + "." + name + ".displayname", getDefaults().getDisplayName());
        ConfigUtils.registerConfiguration(configPath + "." + name + ".active", getDefaults().isActive());
        ConfigUtils.registerConfiguration(configPath + "." + name + ".slot", getDefaults().getSlot());
    }
    public void loadConfigurations() throws InvalidConfigurationException {

        String pattern = configPath + "." + name + ".";
                setMaterial(ConfigUtils.getMaterial(pattern + "material"));
                setType(getMaterial());
                setDisplayName(ConfigUtils.getString(pattern + "displayname"));
                setActive(ConfigUtils.getBoolean(pattern + "active"));
                setSlot(ConfigUtils.getConfig().getInt(pattern + "slot"));

                setupItemMeta();
    }

    public void setupItemMeta() { //TODO: Refactor to "updateItemMeta"?
        ItemMeta meta = getItemMeta();
        meta.setDisplayName(getDisplayName());
        setItemMeta(meta);
        setAmount(count);

    }

    public ActionItem(String name, String configPath, ItemDefaults defaults) throws InvalidConfigurationException {

        setConfigPath(configPath);
        setName(name);
        setMaterial(ConfigUtils.getMaterial(configPath + ""));
        this.defaults = defaults;

        console = new Console(getName(), getName(), "");

        registerConfigurations();
        loadConfigurations();
    }

    public ActionItem(String name, ItemDefaults defaults) throws InvalidConfigurationException {
        setConfigPath(ActionItem.DEFAULT_PATH);
        setName(name);
        this.defaults = defaults;

        console = new Console(getName(), getName(), "");

        registerConfigurations();
        loadConfigurations();
    }

    public void lay(@NotNull Player... players) {
        for (Player player : players) {
            player.getInventory().setItem(getSlot(), this);
        }
    }

    public boolean buildMeta() {
        console.send("building meta...");
       if( hasItemMeta()) {
           console.send("has already a meta!");
           setItemMeta(Bukkit.getItemFactory().getItemMeta(getMaterial()));
           console.send("new meta: " + getItemMeta());
       }
       else {
           console.send("doesn't has a meta!");
           if(getMaterial().equals(Material.AIR)) {
               console.send("cant have a meta!");
               return false;
           }
           setItemMeta(Bukkit.getItemFactory().getItemMeta(getMaterial()));
           console.send("new meta: " + getItemMeta());
           return true;
       }
       return true;
    }

    public void setActive(boolean active) {
        this.active = active;
        if(active){
            ItemMeta meta = getItemMeta();
            meta.addEnchant(getActiveEnchantment(), 1, true);
            setItemMeta(meta);
        }else {
            ItemMeta meta = getItemMeta();
            meta.removeEnchant(getActiveEnchantment());
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        ActionItem that = (ActionItem) o;
        return slot == that.slot && count == that.count && active == that.active && Objects.equals(actionItemContents, that.actionItemContents) && Objects.equals(defaults, that.defaults) && Objects.equals(name, that.name) && Objects.equals(configPath, that.configPath) && Objects.equals(console, that.console) && material == that.material && Objects.equals(displayName, that.displayName) && Objects.equals(activeEnchantment, that.activeEnchantment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), actionItemContents, defaults, name, configPath, console, slot, material, count, active, displayName, activeEnchantment);
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
