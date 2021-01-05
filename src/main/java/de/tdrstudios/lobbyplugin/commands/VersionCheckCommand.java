package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import sun.awt.image.PixelConverter;

public class VersionCheckCommand implements CommandExecutor {

  private Command command;

  public Command getCommand() {
    return command;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  public VersionCheckCommand(Command command) {
    setCommand(command);
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    Chat chat = new Chat();

    if (sender instanceof Player) {

      Player p = (Player) sender;
      chat.setPlayers(new Player[]{p});
      if (args.length == 0) {
        //p.sendMessage("§3§lV1.2 Vom 18.09.2020 §4§l Programmiert von " +LobbyPlugin.getPlugin().getDescription().getAuthors());
        chat.sendMessage("Plugin: " + LobbyPlugin.getPlugin().getDescription().getVersion());
        chat.sendMessage("Version: " + LobbyPlugin.getPlugin().getDescription().getVersion());
        chat.sendMessage("By: " + LobbyPlugin.getPlugin().getDescription().getAuthors());

      } else {
        chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
      } 
    } 
    return false;
  }

  public void registerMessages() {
    LobbyPlugin.getMessageManager().registerMessage(new UsageMessage(getCommand()));
  }
}
