package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


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
        BaseComponent baseComponent = new TextComponent();
        baseComponent.addExtra("Check the process of development on GitHub!");
        ClickEvent clickEvent = baseComponent.getClickEvent();
        baseComponent.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL , "https://github.com/TDRMinecraft/ZZockers_Lobby_Edit"));
        //p.sendMessage("§3§lV1.2 Vom 18.09.2020 §4§l Programmiert von " +LobbyPlugin.getPlugin().getDescription().getAuthors());
        chat.sendMessage("Plugin: " + Chat.getAccentColor()+ LobbyPlugin.getPlugin().getDescription().getVersion());
        chat.sendMessage("Version: " + Chat.getAccentColor() + LobbyPlugin.getPlugin().getDescription().getVersion());
        chat.sendMessage("By: " + Chat.getAccentColor() + LobbyPlugin.getPlugin().getDescription().getAuthors());
        //p.spigot().sendMessage(baseComponent);

        return true;
      } else {
        // STOPSHIP: 12.01.21  
        chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
      } 
    }

    sender.sendMessage(Chat.buildSendS(ConfigUtils.getString("tdrstudios.msg.only.player") , true));
    return false;
  }

  public void registerMessages() {
    LobbyPlugin.getMessageManager().registerMessage(new UsageMessage(getCommand()));
  }
}
