package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.LackingPermissionMessage;
import net.juligames.lobbyplugin.msgs.Message;
import net.juligames.lobbyplugin.msgs.UsageMessage;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import tdrstudios.Work_In_Progress;

import javax.persistence.Lob;

@Work_In_Progress
public class GamemodeCommand implements CommandExecutor {
  private Permission[] permissions = new Permission[4];
  private Command command;
  private Chat chat;

  public void setChat(Chat chat) {
    this.chat = chat;
  }

  public Chat getChat() {
    return chat;
  }

  public void setCommand(Command command) {
    this.command = command;
  }

  public Command getCommand() {
    return command;
  }

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
  private Permission getPermission(int i) {
    return permissions[i];
  }

  public GamemodeCommand(String cmdName ,Permission permission0, Permission permission1, Permission permission2, Permission permission3) {

    setPermission(0, permission0);
    setPermission(1, permission1);
    setPermission(2, permission2);
    setPermission(3, permission3);;
    setCommand(LobbyPlugin.getPlugin().getCommand(cmdName));
    registerMessages();
  }

  /**
   *
   * @param perms shoud have a length of 4 and the Index number is the number of the gamemode!
   */
  public GamemodeCommand(String cmdName,Permission[] perms) {
    setPermissions(perms);
    setCommand(LobbyPlugin.getPlugin().getCommand(cmdName));
    registerMessages();
  }
   private double gamemodeid = 0.0D;

  public void setGamemodeid(double gamemodeid) {
    this.gamemodeid = gamemodeid;
  }

  public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
    setChat(new Chat());
    if (sender instanceof Player) {
      Player p = (Player)sender;
      getChat().setPlayers(new Player[]{p});
      if (args.length == 1) {
        this.gamemodeid = Double.parseDouble(args[0]);
        if (this.gamemodeid == 0.0D)
          if (p.hasPermission(getPermission(0))) {
            p.setGameMode(GameMode.SURVIVAL);
            chat.sendMessage("command.gamemode.success.0");
          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(0)));
          }
        if (this.gamemodeid == 1.0D)
          if (p.hasPermission(getPermission(1))) {
            p.setGameMode(GameMode.CREATIVE);
            chat.sendMessage("command.gamemode.success.1");
          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(0)));
          }
        if (this.gamemodeid == 2.0D)
          if (p.hasPermission(getPermission(2))) {
            p.setGameMode(GameMode.ADVENTURE);
            p.sendMessage("§8[§eInfo§8]§a Dein neuer Gamemode ist nun Arbenteurer");
          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(0)));
          }
        if (this.gamemodeid == 3.0D)
          if (p.hasPermission(getPermission(3))) {
            p.setGameMode(GameMode.SPECTATOR);
            p.sendMessage("§8[§eInfo§8]§a Dein neuer Gamemode ist nun Beobachter");
          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(0)));
          }

        if (this.gamemodeid > 3.0D | getGamemodeid() < 0) {
          getChat().sendMessage(UsageMessage.getNameFIX(getCommand()));
          return false;
        }else {
          return true;
        }

      } else {
       //Other
          p.sendMessage("[TDR´s Debug]: This Feature isn't available in your build! Give the Devteam the time, witch is available!");
        System.out.println("Debug execute!");





      } 
    } 
    return true;
  }

  public void registerMessages() {
    System.out.println("Commands Register!");
    LobbyPlugin.getMessageManager().registerMessage(new UsageMessage(getCommand()));
    LobbyPlugin.getMessageManager().registerMessage(new Message("command.gamemode.success.0", "You have switched" + Chat.getAccentColor() +" your "  + Chat.getChatColor() + "gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!"));
    LobbyPlugin.getMessageManager().registerMessage(new Message("command.gamemode.success.1", "You have switched" + Chat.getAccentColor() +" your "  + Chat.getChatColor() + "gamemode to " + Chat.getAccentColor() + "Creative" + Chat.getChatColor() + "!"));





    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(0)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(1)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(2)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(3)));
    System.out.println("Commands im Manager -> " + LobbyPlugin.getMessageManager().getDebug());
  }
}
