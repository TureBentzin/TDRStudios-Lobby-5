package test.testmaster.devtools;

import org.bukkit.entity.Player;
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
