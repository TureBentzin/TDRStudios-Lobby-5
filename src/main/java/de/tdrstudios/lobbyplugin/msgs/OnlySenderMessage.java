package de.tdrstudios.lobbyplugin.msgs;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.enums.SenderType;
import de.tdrstudios.lobbyplugin.utils.config.ConfigUtils;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.InvalidConfigurationException;

public class OnlySenderMessage extends Message {



    public OnlySenderMessage(CommandSender sender) {
        super("sendermessage." + SenderType.getFromCommandSender(sender).name(), "If you see this - report it immediately!");
        init(SenderType.getFromCommandSender(sender));
    }

    public OnlySenderMessage(SenderType senderType) {
        super("sendermessage - > " + senderType, "If you see this - report it immediately!");
        init(senderType);
    }
    public void init(SenderType senderType) {
        setContent(Chat.getErrorColor() + ConfigUtils.getString("tdrstudios.msg.only").replace("%Sender%" , Chat.getAccentColor() + senderType.name() + Chat.getErrorColor()));
        ConfigUtils.getString("tdrstudios.msg.only");
    }
}
