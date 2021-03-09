package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class ConfigCommand implements CommandExecutor {
    private Command command;

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) {
        this.command = command;
    }
    public ConfigCommand(String command) {
        setCommand(LobbyPlugin.getPlugin().getCommand(command));
        registerMessages();

        List<Argument> configTabList0 = new ArrayList<>();
        List<Argument>[] configTabList = new List[1];
        configTabList0.add(new Argument("save"));
        configTabList0.add(new Argument("read"));
        configTabList[0] = configTabList0;
        LobbyPlugin.getPlugin().getCommand("config").setTabCompleter(new TabComplete(configTabList));
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] args) {

        Chat chat = new Chat();
        if(sender instanceof Player) {
            Player player = (Player) sender;
            chat.setPlayers(new Player[]{player});
            chat.send(Chat.getAccentColor() + "[DEBUG] "+ Chat.getChatColor()+"This command is only for debug purposes!");
            if(args.length == 1) {
                if(args[0].equalsIgnoreCase("save")) {
                    LobbyPlugin.getPlugin().saveConfig();
                    chat.send(Chat.getAccentColor() + "[DEBUG] "+ Chat.getChatColor()+"Config was saved successfully!");
                } else
                if(args[0].equalsIgnoreCase("read")) {
                    String string = LobbyPlugin.getPlugin().getConfig().saveToString();
                    chat.send(Chat.getAccentColor() + "[DEBUG] "+ Chat.getChatColor()+"Config read was successfully!");
                    chat.send(" ");
                    chat.send(string);
                    chat.send(" ");
                }else {
                    chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
                }
            }else {
                chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
            }
        }
        return true;
    }
    private void registerMessages() {
        LobbyPlugin.getMessageManager().registerMessage(new UsageMessage(getCommand()));
    }
}
