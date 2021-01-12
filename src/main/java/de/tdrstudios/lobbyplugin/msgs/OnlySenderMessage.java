package de.tdrstudios.lobbyplugin.msgs;

import de.tdrstudios.lobbyplugin.enums.SenderType;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

public class OnlySenderMessage extends Message {

    private String content = null;
    public OnlySenderMessage(CommandSender sender) {
        super("SenderMessage - > " + SenderType.getFromCommandSender(sender).name(), "If you see this - report it immediately!");
        init(SenderType.getFromCommandSender(sender));
    }

    public OnlySenderMessage(SenderType senderType) {
        super("SenderMessage - > " + senderType.name(), "If you see this - report it immediately!");
        init(senderType);
    }
    public void init(SenderType senderType) {
        ConfigUtils.getString("tdrstudios.msg.only");
    }
}
