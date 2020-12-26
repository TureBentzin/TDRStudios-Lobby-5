package net.juligames.lobbyplugin.commands;

import net.juligames.lobbyplugin.Chat;
import net.juligames.lobbyplugin.LobbyPlugin;
import net.juligames.lobbyplugin.msgs.LackingPermissionMessage;
import net.juligames.lobbyplugin.msgs.Message;
import net.juligames.lobbyplugin.msgs.UsageMessage;
import net.juligames.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import sun.security.krb5.Config;
import tdrstudios.Work_In_Progress;

import java.util.Arrays;


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
  private Permission getPermissionOTHER(int i) {
    return  new Permission(permissions[i].getName() + ".other" , " The .other permission allows you to edit the gamemode of every other Person");
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
        if (args[0].equals("0"))
          if (p.hasPermission(getPermission(0))) {
            p.setGameMode(GameMode.SURVIVAL);
            chat.sendMessage("command.gamemode.success.0");


          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(0)));
          }
        if (args[0].equals("1"))
          if (p.hasPermission(getPermission(1))) {
            p.setGameMode(GameMode.CREATIVE);
            chat.sendMessage("command.gamemode.success.1");

          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(1)));
          }
        if (args[0].equals("2"))
          if (p.hasPermission(getPermission(2))) {
            p.setGameMode(GameMode.ADVENTURE);
            chat.sendMessage("command.gamemode.success.2");

          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(2)));
          }
        if (args[0].equals("3"))
          if (p.hasPermission(getPermission(3))) {
            p.setGameMode(GameMode.SPECTATOR);
            chat.sendMessage("command.gamemode.success.3");

          } else {
            chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(3)));
          }

        if (args[0].equals("0") | args[0].equals("1") | args[0].equals("2") |args[0].equals("3")) {

          return true;
        }else {

          getChat().sendMessage(UsageMessage.getNameFIX(getCommand()));
          return false;
        }

      } else {
       //Other
        if(args.length == 2) {

          if(Bukkit.getOfflinePlayer(args[1]).isOnline()) {
            Player target = Bukkit.getPlayer(args[1]);
            if(LobbyPlugin.getPlugin().getConfig().getBoolean("tdrstudios.commands.gamemode.allow.otherSelfSet")) {

              if(target == p) {
                chat.sendMessage(Chat.getErrorColor() + "For this action: use \"" + Chat.getAccentColor() + getCommand().getName() + " [0] [1] [2] [3] " + Chat.getChatColor() + "\" !");
                return true;
              }
            }
            if(args[0].equals("0")) {
              if(p.hasPermission(getPermissionOTHER(0))) {
                target.setGameMode(GameMode.SURVIVAL);

                chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() +  " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
                chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + target.getName() + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
              }else {
                chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(0)));
              }
            }
            if(args[0].equals("1")) {
              if(p.hasPermission(getPermissionOTHER(0))) {
                target.setGameMode(GameMode.CREATIVE);

                chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() +  " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
                chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + target.getName() + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
              }else {
                chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(1)));
              }
            }
            if(args[0].equals("2")) {
              if(p.hasPermission(getPermissionOTHER(0))) {
                target.setGameMode(GameMode.ADVENTURE);
                chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() +  " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
                chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + target.getName() + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
              }else {
                chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(2)));
              }
            }
            if(args[0].equals("3")) {
              if(p.hasPermission(getPermissionOTHER(0))) {
                target.setGameMode(GameMode.SPECTATOR);
                chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() +  " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
                chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + target.getName() + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
              }else {
                chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(3)));
              }
            }
          }else {
            if(args[1].equals("*") | args[1].equals("@a")) {

              if (args[0].equals("0") |args[0].equals("1")  | args[0].equals("2")  | args[0].equals("3") ) {
                GameMode gameMode = GameMode.getByValue(Integer.parseInt(args[0]));

                if (gameMode == GameMode.SURVIVAL) {
                  if (p.hasPermission(getPermissionOTHER(0) + ".all")) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                      Player target = all;
                      target.setGameMode(gameMode);;

                      chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() + " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!");
                      chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + "everyone" + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() +
                              "Survival" + Chat.getChatColor() + "!");
                    }


                  }else {
                    chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(0)) + ".all");
                  }
                }
                if (gameMode == GameMode.CREATIVE) {
                  if (p.hasPermission(getPermissionOTHER(1) + ".all")) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                      Player target = all;
                      target.setGameMode(gameMode);
                      chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() + " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor()
                              + "Creative" + Chat.getChatColor() + "!");
                      chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + "everyone" + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() +
                              "Creative" + Chat.getChatColor() + "!");
                    }

                  }else {
                    chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(1)) + ".all");
                  }
                }
                if (gameMode == GameMode.ADVENTURE) {
                  if (p.hasPermission(getPermissionOTHER(2) + ".all")) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                      Player target = all;
                      target.setGameMode(gameMode);
                      chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() + " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor()
                              + "Adventure" + Chat.getChatColor() + "!");
                      chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + "everyone" + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() +
                              "Adventure" + Chat.getChatColor() + "!");
                    }
                  }else {
                    chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(2)) + ".all");
                  }
                }
                if (gameMode == GameMode.SPECTATOR) {
                  if (p.hasPermission(getPermissionOTHER(3) + ".all")) {
                    for (Player all : Bukkit.getOnlinePlayers()) {
                      Player target = all;
                      target.setGameMode(gameMode);
                      chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() + " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor()
                              + "Spectator" + Chat.getChatColor() + "!");
                      chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + "everyone" + "´s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() +
                              "Spectator" + Chat.getChatColor() + "!");

                    }
                  }else {
                    chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(3)) + ".all");
                  }
                }


              } else {
                chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
              }


            }else {
              chat.send(p, Chat.getErrorColor() + "The Player " + Chat.getAccentColor() + args[1] + Chat.getErrorColor()+ " is not online!");
            }

          }
        }

      }
    }else {
      /**
       * This part of the class is written by @Bommels05
       */
      if(sender instanceof ConsoleCommandSender) {
        ConsoleCommandSender console = (ConsoleCommandSender) sender;
        chat.sendFast(console , "Sorry! This Feature isn't available yet!");

      }
    }
    return true;
  }

  public void registerMessages() {
    System.out.println("Commands Register!");
    LobbyPlugin.getMessageManager().registerMessage(new UsageMessage(getCommand()));
    LobbyPlugin.getMessageManager().registerMessage(new Message("command.gamemode.success.0", Chat.getAccentColor() + "You" + Chat.getChatColor() + " have switched" + Chat.getAccentColor() +" your "  + Chat.getChatColor() + "gamemode to " + Chat.getAccentColor() + "Survival" + Chat.getChatColor() + "!"));
    LobbyPlugin.getMessageManager().registerMessage(new Message("command.gamemode.success.1", Chat.getAccentColor() + "You" + Chat.getChatColor() + " have switched" + Chat.getAccentColor() +" your "  + Chat.getChatColor() + "gamemode to " + Chat.getAccentColor() + "Creative" + Chat.getChatColor() + "!"));
    LobbyPlugin.getMessageManager().registerMessage(new Message("command.gamemode.success.2", Chat.getAccentColor() + "You" + Chat.getChatColor() + " have switched" + Chat.getAccentColor() +" your "  + Chat.getChatColor() + "gamemode to " + Chat.getAccentColor() + "Adventure" + Chat.getChatColor() + "!"));
    LobbyPlugin.getMessageManager().registerMessage(new Message("command.gamemode.success.3", Chat.getAccentColor() + "You" + Chat.getChatColor() + " have switched" + Chat.getAccentColor() +" your "  + Chat.getChatColor() + "gamemode to " + Chat.getAccentColor() + "Spectator" + Chat.getChatColor() + "!"));



    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(0)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(1)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(2)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermission(3)));

    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(0)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(1)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(2)));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(3)));

    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(0) +  ".all"));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(1) +  ".all"));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(2) +  ".all"));
    LobbyPlugin.getMessageManager().registerMessage(new LackingPermissionMessage(getPermissionOTHER(3) +  ".all"));



  }
}
