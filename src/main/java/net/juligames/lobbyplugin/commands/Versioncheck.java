package net.juligames.lobbyplugin.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Versioncheck implements CommandExecutor {
  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    if (sender instanceof Player) {
      Player p = (Player)sender;
      if (args.length == 0) {
        p.sendMessage("§3§lV1.2 Vom 18.09.2020 §4§l Programmiert von Zzocker77");
      } else {
        p.sendMessage("§3§lBitte benutze nur /version oder /v");
      } 
    } 
    return false;
  }
}
