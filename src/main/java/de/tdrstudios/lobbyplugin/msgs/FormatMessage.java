package de.tdrstudios.lobbyplugin.msgs;

import de.tdrstudios.lobbyplugin.enums.FormatType;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class FormatMessage extends Message{


    private FormatType formatType;

    public FormatType getFormatType() {
        return formatType;
    }

    protected void setFormatType(FormatType formatType) {
        this.formatType = formatType;
    }

    public FormatMessage(String pname, String content , FormatType formatType) {
        super(pname, content);
        setFormatType(formatType);
    }

    public void send(CommandSender sender) {
        sender.sendMessage(getContent());
    }
}
