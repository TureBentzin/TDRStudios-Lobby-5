package de.tdrstudios.lobbyplugin.inventory;

import com.google.gson.internal.bind.MapTypeAdapterFactory;
import de.tdrstudios.lobbyplugin.utils.inventory.InventoryContent;
import org.bukkit.Material;

import java.util.HashMap;
import java.util.Map;

public class MappedType {

    public MappedType(int id, Material material) {
        setId(id);
        setMaterial(material);
    }
    int id = 0;
    Material material;
    public Material getMaterial() {
        return material;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public void setMaterial(Material material) {
        this.material = material;
    }

    private static Map<InventoryContent, Integer> gameIDMap = new HashMap<>();

    public static Map<InventoryContent, Integer> getGameIDMap() {
        return gameIDMap;
    }

    public static void setGameIDMap(Map<InventoryContent, Integer> gameIDMap) {
        MappedType.gameIDMap = gameIDMap;
    }
    public static boolean mapGameID(InventoryContent inventoryContent , int MiniGameID) {
        if(!getGameIDMap().containsValue(gameIDMap)) {
            getGameIDMap().put(inventoryContent, MiniGameID);
            return true;
        }else
            return false;
    }
    public static boolean unmapGameID(InventoryContent inventoryContent , int MiniGameID) {
        if(getGameIDMap().containsValue(gameIDMap)) {
            getGameIDMap().remove(inventoryContent, MiniGameID)
            return true;
        }else
            return false;
    }
    public static int unmapGameID(InventoryContent inventoryContent) {
        if(getGameIDMap().containsValue(gameIDMap)) {
            return  getGameIDMap().remove(inventoryContent);

        }else
            return 0;
    }

}
