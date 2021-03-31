package de.tdrstudios.lobbyplugin.utils.inventory;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Material;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.CustomItemTagContainer;
import org.bukkit.persistence.PersistentDataContainer;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.jar.Attributes;

public class InventoryContent {

    private boolean staticItem = false;

    public void setStaticItem(boolean staticItem) {
        this.staticItem = staticItem;
    }

    /**
     *
     * @return if the item is for multiple use!
     */
    public boolean isStaticItem() {
        return staticItem;
    }

    public InventoryContent(Material material, String displayName, int count, boolean isEnchant , int slot) {
        setCount(count);
        setMaterial(material);
        setItemStack(new ItemStack(getMaterial(), getCount()));
        setMeta(getItemStack().getItemMeta()); // Fixes #20

        if (meta != null) {

            if (isEnchant) {
                getMeta().addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            }
            getMeta().setDisplayName(displayName);

            getItemStack().setItemMeta(getMeta());
        }
        setSlot(slot);
    }

    public InventoryContent(String ConfigMaterialString, String displayName, int count, boolean isEnchant , int slot) {
        setCount(count);
        setMaterial(Material.getMaterial(ConfigUtils.getConfig().getString(ConfigMaterialString)));
        setItemStack(new ItemStack(getMaterial(), getCount()));
        setMeta(getItemStack().getItemMeta()); // Fixes #20

        if (meta != null) {


            if (isEnchant) {
                getMeta().addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, true);
            }
            getMeta().setDisplayName(displayName);

            getItemStack().setItemMeta(getMeta());
        }
        setSlot(slot);
    }

    public InventoryContent(String ConfigMaterialString, String ConfigdisplayNameString, int count , int slot) {
        setCount(count);
        setMaterial(Material.getMaterial(ConfigUtils.getString(ConfigMaterialString)));
        setItemStack(new ItemStack(getMaterial(), getCount()));
        setMeta(getItemStack().getItemMeta()); //Add in fix #20
        if (meta != null) {
            getMeta().setDisplayName(ConfigUtils.getString(ConfigdisplayNameString));
            getItemStack().setItemMeta(getMeta());
        }

        setSlot(slot);
    }

    public InventoryContent(String ConfigMaterialString, String ConfigdisplayNameString, int count , int slot , boolean staticItem) {
        setCount(count);
        setStaticItem(staticItem);

        setMaterial(Material.getMaterial(Objects.requireNonNull(ConfigUtils.getString(ConfigMaterialString))));

        setItemStack(new ItemStack(getMaterial(), getCount()));

        setMeta(getItemStack().getItemMeta()); //Add in fix #20



        getMeta().setDisplayName(ConfigUtils.getString(ConfigdisplayNameString));


        getItemStack().setItemMeta(getMeta());
        setSlot(slot);
    }

    public void setEnchant(boolean value) {
        if (meta == null) return;

        if(value) {
            if(getItemStack().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) < 1) {
                ItemMeta itemMeta = getMeta();
                itemMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
                getItemStack().addUnsafeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1);
                getItemStack().setItemMeta(itemMeta);
            }
        }else {
            if(getItemStack().getItemMeta().getEnchantLevel(Enchantment.PROTECTION_ENVIRONMENTAL) > 0) {
                ItemMeta itemMeta = getMeta();
                getItemStack().removeEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL);
                itemMeta.removeItemFlags(ItemFlag.HIDE_ENCHANTS);
                getItemStack().setItemMeta(itemMeta);
            }
        }


    }



    private int count;
    private ItemStack itemStack;
    private Material material;
    private ItemMeta meta;

    private int slot;

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    public void setMeta(ItemMeta meta) {
        this.meta = meta;
    }

    public List<String> getLore() {
        return meta == null ? Collections.EMPTY_LIST : meta.getLore();
    }

    public void setLore(List<String> lore) {
        if (meta == null) return;
        meta.setLore(lore);
    }


    @Nullable
    /**
     * @Nullable this can be null so use null checks
     */
    public ItemMeta getMeta() {
        return meta;
    }

    public int getCount() {
        return count;
    }

    private ItemStack getItemStack() {
        return itemStack;
    }

    public Material getMaterial() {
        return material;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setItemStack(ItemStack itemStack) {
        this.itemStack = itemStack;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    @Override
    public String toString() {
        return "InventoryContent{" +
                "count=" + count +
                ", itemStack=" + itemStack +
                ", material=" + material +
                ", meta=" + meta +
                '}';
    }

    public ItemStack toItemStack() {
        getItemStack().setItemMeta(getMeta());
        return getItemStack();
    }
}
