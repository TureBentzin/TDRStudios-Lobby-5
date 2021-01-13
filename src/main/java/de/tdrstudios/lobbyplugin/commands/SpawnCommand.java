package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.MessageManager;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SpawnCommand extends MyCommand {
  /**
   * @param command
   * @param permission
   * @param arguments
   * @STOPSHIP: 10.01.2021
   * @Autonom This can manage your TabComplete and other stuff fully self!
   */
  public SpawnCommand(Command command, Permission permission) {
    super(command, permission, null);
    registerMessages();
    registerTabComplete();
  }

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

  @Override
  public @Nullable List<String> onTabComplete(CommandSender commandSender, Command command, String label, String[] args) {
    return null;
  }


  @Override

  public void registerMessages() {

    MessageManager messageManager = LobbyPlugin.getMessageManager();
    messageManager.registerMessage(new Message("tdrstudios.spawn.success", ConfigUtils.getString("tdrstudios.spawn.success")));
    messageManager.registerMessage(new Message("tdrstudios.spawn.success.other", ConfigUtils.getString("tdrstudios.spawn.success.other")));
    messageManager.registerMessage(new Message("tdrstudios.spawn.success.otherB", ConfigUtils.getString("tdrstudios.spawn.success.otherB")));

  }

  @Override
  public void generatePermission(String permission) {

  }


  @Override
  public List<String>[] registerTabComplete() {
    List<Argument> list1 = new ArrayList<>();
    return new List[0];
  }
}
