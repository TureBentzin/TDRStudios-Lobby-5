package test.testmaster.devtools;

import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.craftbukkit.v1_15_R1.inventory.CraftMerchantCustom;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import net.minecraft.server.v1_15_R1.*;
import org.spigotmc.event.player.PlayerSpawnLocationEvent;

public class Sc {
   public String eventInfo(Player player) {
       player.setDisplayName("String");
       org.spigotmc.event.player.PlayerSpawnLocationEvent playerSpawnLocationEvent = new PlayerSpawnLocationEvent(player , player.getLocation());
       StringBuilder stringBuilder = new StringBuilder();
       stringBuilder.append(playerSpawnLocationEvent.getEventName() + ";");
       return stringBuilder.toString();
   }
}
