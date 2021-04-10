package de.tdrstudios.lobbyplugin.commands;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.LobbyPlugin;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.tabcomplete.TabComplete;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import javafx.beans.property.adapter.ReadOnlyJavaBeanBooleanProperty;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.MemorySection;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

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
        List<Argument> configTabList1 = new ArrayList<>();
        List<Argument>[] configTabList = new List[2];

        Argument get = new Argument("get");

        List<Argument> depends = new ArrayList<>();
        depends.add(get);
        for (String path : ConfigUtils.getKeys(true)) {
            if(!configTabList1.contains(path)) {
                configTabList1.add(new Argument(path, new Permission("test"), depends));
            }
        }

        configTabList0.add(new Argument("save"));
        configTabList0.add(new Argument("read"));
        configTabList0.add(get);
        configTabList[0] = configTabList0;
        configTabList[1] = configTabList1;
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
                }else
                    chat.sendMessage(UsageMessage.getNameFIX(getCommand()));
                }else if(args.length == 2) {
                if(args[0].equalsIgnoreCase("get")){
                    Object output = ConfigUtils.getConfig().get(args[1]);
                    if(output != null) {
                        if(output instanceof MemorySection) {
                            MemorySection memorySection = (MemorySection) output;
                            Set<String> keys = memorySection.getKeys(true);
                            for (int i = 0; i < keys.size(); i++) {
                                chat.send(keys.toArray()[i] + " - " + Chat.getAccentColor() + ConfigUtils.getConfig().get(memorySection.getCurrentPath() + "." + keys.toArray()[i]));

                            }

                        }else
                        chat.send(Chat.getAccentColor() + "[DEBUG] " + Chat.getChatColor() + "Find " + Chat.getAccentColor() + output + Chat.getChatColor() + "@" + Chat.getAccentColor() + args[1] + Chat.getChatColor() + "!");
                    }else
                        chat.send(Chat.getAccentColor() + "[DEBUG] " + Chat.getErrorColor() + "There is no object on " + Chat.getAccentColor() + args[1] + Chat.getErrorColor() + "!");
                }else{
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
