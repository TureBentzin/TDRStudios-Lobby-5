package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.additional.Work_In_Progress;
import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.enums.SenderType;
import de.tdrstudios.lobbyplugin.msgs.*;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
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
   * @STOPSHIP: 10.01.2021
   * @Autonom This can manage your TabComplete and other stuff fully self!
   */
  public SpawnCommand(Command command, Permission permission) {
    super(command, permission, null);
    registerMessages();
    registerTabComplete();
  }

  @Work_In_Progress
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

    //Basics
    FileConfiguration config = LobbyPlugin.getPlugin().getConfig();
    World world = Bukkit.getWorld(config.getString("Spawn.World"));
    double x = config.getDouble("Spawn.X");
    double y = config.getDouble("Spawn.Y");
    double z = config.getDouble("Spawn.Z");
    float yaw = (float) config.getDouble("Spawn.Yaw");
    float pitch = (float) config.getDouble("Spawn.Pitch");
    Location location = new Location(world, x, y, z, yaw, pitch);
    //End of Basics
    if (sender instanceof Player) {
      Player player = (Player) sender;
      Chat chat = new Chat();
      chat.setPlayers(new Player[]{player});

      if (args.length == 0) {
        if (player.hasPermission(getPermission())) {
          player.teleport(location);
          chat.sendMessage("tdrstudios.spawn.success");
        } else
          chat.sendMessage(new LackingPermissionMessage(getPermission()));
      } else if (args.length == 1) {
        if (Bukkit.getOfflinePlayer(args[0]).isOnline()) {
            //TODO: Bommels schreib den Code!
        } else if (args[0].equalsIgnoreCase("all") | args[0].equalsIgnoreCase("@a") | args[0].equalsIgnoreCase("*")) {
          if (player.hasPermission(getPermission().getName() + ".other.all")) {
            //TODO: Bommels schreib den Code!
          } else {
            chat.send(LackingPermissionMessage.getNameFIX(getPermission() + ".other.all"));
          }
        } else {
          chat.send(UsageMessage.getNameFIX(getCommand()));
        }

      } else {
        chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
      }


    }else {
      if(sender instanceof ConsoleCommandSender){
        //TODO: Bommels schreib den Code!
      }else {
        Chat.sendFast(sender , new OnlySenderMessage(SenderType.PLAYER));
      }
    }
    return true;
  }

  @Override

  public void registerMessages() {

    MessageManager messageManager = LobbyPlugin.getMessageManager();
    messageManager.registerMessage(new Message("tdrstudios.spawn.success", ConfigUtils.getString("tdrstudios.spawn.success.me")));
    messageManager.registerMessage(new Message("tdrstudios.spawn.success.other", ConfigUtils.getString("tdrstudios.spawn.success.other")));
    messageManager.registerMessage(new Message("tdrstudios.spawn.success.otherB", ConfigUtils.getString("tdrstudios.spawn.success.otherB")));
    messageManager.registerMessage(new UsageMessage(getCommand()));
    messageManager.registerMessage(new LackingPermissionMessage(getPermission()));
    messageManager.registerMessage(new LackingPermissionMessage(getPermission().getName() + ".other"));
    messageManager.registerMessage(new LackingPermissionMessage(getPermission().getName() + ".other.all"));


  }

  @Override
  public void generatePermission(String permission) {

  }


  @Override
  public void registerTabComplete() {
    List<Argument>[] r = new List[1];
    List<Argument> list1 = new ArrayList<>();
    Argument playerArgument = new Argument("%Players%");
    playerArgument.setPermission(new Permission(getPermission().getName() + ".other"));
    list1.add(new Argument("*"));
    list1.add(new Argument("@a"));
    list1.add(new Argument("all"));
    list1.add(playerArgument);
  }
}
