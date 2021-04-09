package test.testmaster;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.commands.MyCommand;
import de.tdrstudios.lobbyplugin.msgs.UsageMessage;
import de.tdrstudios.lobbyplugin.tabcomplete.Argument;
import de.tdrstudios.lobbyplugin.utils.KickUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;

import java.util.ArrayList;
import java.util.List;

public class TestCommand extends MyCommand {

    public TestCommand(Command command) {
        super(command, new Permission("test"), getNullList());
        setArguments(getTabComplete());
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        sender.sendMessage(label);
        return true;
    }

    @Override
    public List<Argument>[] getTabComplete() {
        List<Argument> arguments0 = new ArrayList<>();
        Argument players = new Argument("%Players%");
        players.setPlayermode(true);
        arguments0.add(new Argument("1"));
        arguments0.add(new Argument("2", new Permission("test.2")));
        arguments0.add(players);
        List<Argument> arguments1 = new ArrayList<>();
        List<Argument> geheimDepends = new ArrayList<>();
        geheimDepends.add(players);
        arguments1.add(new Argument("geheim", new Permission("test.geheim"), geheimDepends));
        List<Argument>[] argumentsArray = new List[2];
        argumentsArray[0] = arguments0;
        argumentsArray[1] = arguments1;
        return argumentsArray;
    }
}
