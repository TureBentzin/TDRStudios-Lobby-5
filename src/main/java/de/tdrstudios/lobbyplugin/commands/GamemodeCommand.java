package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.LackingPermissionMessage;
import de.tdrstudios.lobbyplugin.msgs.Message;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import de.tdrstudios.additional.Work_In_Progress;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@SuppressWarnings("deprecation")
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

  /*
  public void setPermission(int i, Permission permission) {
    permissions[i] = permission;
  }
  */

  public Permission[] getPermissions() {
    return permissions;
  }

  private Permission getPermission(int i) {
    return permissions[i];
  }

  private Permission getPermissionOTHER(int i) {
    return  new Permission(permissions[i].getName() + ".other" , " The .other permission allows you to edit the gamemode of every other Person");
  }

  /*
  public GamemodeCommand(String cmdName ,Permission permission0, Permission permission1, Permission permission2, Permission permission3) {

    setPermission(0, permission0);
    setPermission(1, permission1);
    setPermission(2, permission2);
    setPermission(3, permission3);;
    setCommand(LobbyPlugin.getPlugin().getCommand(cmdName));
    registerMessages();
    registerTab();
  }
  */

  /**
   *
   * @param perms should have a length of 4 and the Index number is the number of the gamemode!
   */

  public GamemodeCommand(String cmdName, Permission[] perms) {
    setPermissions(perms);
    setCommand(LobbyPlugin.getPlugin().getCommand(cmdName));
    registerMessages();
    registerTab();
  }

  private void registerTab() {
    List<Argument> TabList0 = new ArrayList<>();
    List<Argument> TabList1 = new ArrayList<>();
    List<Argument>[] TabList = new List[2];

    TabList0.add(new Argument("0"));
    TabList0.add(new Argument("1"));
    TabList0.add(new Argument("2"));
    TabList0.add(new Argument("3"));
    TabList0.add(new Argument("survival"));
    TabList0.add(new Argument("creative"));
    TabList0.add(new Argument("adventure"));
    TabList0.add(new Argument("spectator"));

    TabList1.add(new Argument("*"));
    TabList1.add(new Argument("@a"));
    Argument argument = new Argument("%Players%");
    argument.setPlayermode(true);
    TabList1.add(argument);
    TabList[0] = TabList0;
    TabList[1] = TabList1;

    Objects.requireNonNull(LobbyPlugin.getPlugin().getCommand(getCommand().getName())).setTabCompleter(new TabComplete(TabList));
  }

  private GameMode getGamemode(String arg) {
    switch (arg) {
      case "0":
      case "survival": {
        return GameMode.SURVIVAL;
      }

      case "1":
      case "creative": {
        return GameMode.CREATIVE;
      }

      case "2":
      case "adventure": {
        return GameMode.ADVENTURE;
      }

      case "3":
      case "spectator": {
        return GameMode.SPECTATOR;
      }
    }
    return null;
  }

  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, String[] args) {
    setChat(new Chat());
    if (sender instanceof Player) {
      Player p = (Player) sender;

      getChat().setPlayers(new Player[]{p});

      if(args.length == 0) {
        getChat().sendMessage(UsageMessage.getNameFIX(getCommand()));
        return true;
      }

      if (args.length == 1) {
        GameMode gameMode = getGamemode(args[0]);

        if(gameMode == null) {
          getChat().sendMessage(UsageMessage.getNameFIX(getCommand()));
          return false;
        }

        int gameModeId = gameMode.getValue();

        if (p.hasPermission(getPermission(gameModeId))) {
          p.setGameMode(gameMode);
          chat.sendMessage("command.gamemode.success."+gameModeId);
        } else {
          chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermission(gameModeId)));
        }

        return true;
      } else {
       //Other
        if(args.length == 2) {

          if(Bukkit.getOfflinePlayer(args[1]).isOnline()) {
            Player target = Bukkit.getPlayer(args[1]);
            if(LobbyPlugin.getPlugin().getConfig().getBoolean("tdrstudios.commands.gamemode.allow.otherSelfSet")) {
              if(target == p) {
                chat.sendMessage(Chat.getErrorColor() + "For this action: use \"" + Chat.getAccentColor() + getCommand().getName() + " <gamemode> " + Chat.getChatColor() + "\" !");
                return true;
              }
            }

            GameMode gameMode = getGamemode(args[0]);

            if(gameMode == null) {
              getChat().sendMessage(UsageMessage.getNameFIX(getCommand()));
              return false;
            }

            int gameModeId = gameMode.getValue();

            if(target == null) {
              chat.sendMessage(Chat.getErrorColor() + "For this action: use \"" + Chat.getAccentColor() + getCommand().getName() + " <gamemode> <player>" + Chat.getChatColor() + "\" !");
              return true;
            }

            if(p.hasPermission(getPermissionOTHER(gameModeId))) {
              target.setGameMode(gameMode);

              chat.send(target, Chat.getAccentColor() + p.getName() + Chat.getChatColor() +  " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + gameMode.name() + Chat.getChatColor() + "!");
              chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + target.getName() + "'s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + gameMode.name() + Chat.getChatColor() + "!");
            }else {
              chat.sendMessage(LackingPermissionMessage.getNameFIX(getPermissionOTHER(0)));
            }

          } else {
            if(args[1].equals("*") | args[1].equals("@a")) {
              GameMode gameMode = getGamemode(args[0]);

              if(gameMode == null) {
                getChat().sendMessage(UsageMessage.getNameFIX(getCommand()));
                return false;
              }

              int gameModeId = gameMode.getValue();

              if (p.hasPermission(getPermissionOTHER(gameModeId) + ".all")) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                  player.setGameMode(gameMode);

                  chat.send(player, Chat.getAccentColor() + p.getName() + Chat.getChatColor() + " has switched " + Chat.getAccentColor() + "your" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + gameMode.name() + Chat.getChatColor() + "!");
                  chat.send(Chat.getAccentColor() + "You" + Chat.getChatColor() +  " have switched " + Chat.getAccentColor() + "everyone" + "'s" + Chat.getChatColor() + " gamemode to " + Chat.getAccentColor() + gameMode.name() + Chat.getChatColor() + "!");
                }
              } else {
                chat.sendMessage(new LackingPermissionMessage(getPermissionOTHER(gameModeId)+ ".all"));
              }
            } else {
              chat.send(p, Chat.getErrorColor() + "The Player " + Chat.getAccentColor() + args[1] + Chat.getErrorColor()+ " is not online!");
            }
          }
          return true;
        }
      }
    } else {
      /*
       This part of the class is written by @Bommels05
       */
      if(sender instanceof ConsoleCommandSender) {
        ConsoleCommandSender console = (ConsoleCommandSender) sender;
        Chat.sendFast(console , "Sorry! This Feature isn't available yet!");
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
