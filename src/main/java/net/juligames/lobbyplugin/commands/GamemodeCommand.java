package net.juligames.lobbyplugin.commands;

import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

public class GamemodeCommand implements CommandExecutor {
  private Permission[] permissions = new Permission[4];

  public void setPermissions(Permission[] permissions) {
    this.permissions = permissions;
  }
  public void setPermission(int i, Permission permission) {
    permissions[i] = permission;
  }

  public double getGamemodeid() {
    return gamemodeid;
  }

  public Permission[] getPermissions() {
    return permissions;
  }

  public GamemodeCommand(Permission permission0, Permission permission1, Permission permission2, Permission permission3) {
    setPermission(0, permission0);
    setPermission(1, permission1);
    setPermission(2, permission2);
    setPermission(3, permission3);;
  }

  /**
   *
   * @param perms shoud have a length of 4 and the Index number is the number of the gamemode!
   */
  public GamemodeCommand(Permission[] perms) {
    setPermissions(perms);
  }
  double gamemodeid = 0.0D;
  
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player p = (Player)sender;
      if (args.length == 1) {
        this.gamemodeid = Double.parseDouble(args[0]);
        if (this.gamemodeid == 0.0D)
          if (p.hasPermission("changegamemode")) {
            p.setGameMode(GameMode.SURVIVAL);
            p.sendMessage("§8[§eInfo§8]§a Dein neuer Gamemode ist nun überleben");
          } else {
            p.sendMessage("§8[§eInfo§8]§a Dafür reichen deine Rechte nicht aus");
          }  
        if (this.gamemodeid == 1.0D)
          if (p.hasPermission("changegamemode")) {
            p.setGameMode(GameMode.CREATIVE);
            p.sendMessage("§8[§eInfo§8]§a Dein neuer Gamemode ist nun Kreativ");
          } else {
            p.sendMessage("§8[§eInfo§8]§a Dafür reichen deine Rechte nicht aus");
          }  
        if (this.gamemodeid == 2.0D)
          if (p.hasPermission("changegamemode")) {
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage("§8[§eInfo§8]§a Dein neuer Gamemode ist nun Arbenteurer");
          } else {
            p.sendMessage("§8[§eInfo§8]§a Dafür reichen deine Rechte nicht aus");
          }  
        if (this.gamemodeid == 3.0D)
          if (p.hasPermission("changegamemode")) {
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage("§8[§eInfo§8]§a Dein neuer Gamemode ist nun Beobachter");
          } else {
            p.sendMessage("§8[§eInfo§8]§a Dafür reichen deine Rechte nicht aus");
          }  
        if (this.gamemodeid >= 3.0D)
          p.sendMessage("§8[§eInfo§8]§a Bitte benutze /gm [0] [1] [2] [3]"); 
      } else {
        p.sendMessage("§8[§eInfo§8]§a Bitte benutze /gm [0] [1] [2] [3]");
      } 
    } 
    return false;
  }
}
