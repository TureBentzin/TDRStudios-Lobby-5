package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class SpawnCommand extends MyCommand {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player player = (Player)sender;
      if (args.length == 0) {
        FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
        World world = Bukkit.getWorld(config.getString("Spawn.World"));
        double x = config.getDouble("Spawn.X");
        double y = config.getDouble("Spawn.Y");
        double z = config.getDouble("Spawn.Z");
        float yaw = (float)config.getDouble("Spawn.Yaw");
        float pitch = (float)config.getDouble("Spawn.Pitch");
        Location location = new Location(world, x, y, z, yaw, pitch);
        player.teleport(location);
        player.sendMessage("§8[§eInfo§8]§a Du wurdest zum Spawn teleportiert!");
      } else {
        player.sendMessage("§8[§e!§8]§a Bitte benutze /spawn");
      } 
    } 
    return false;
  }


}
