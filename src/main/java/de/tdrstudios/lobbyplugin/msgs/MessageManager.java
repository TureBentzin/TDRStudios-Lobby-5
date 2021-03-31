package de.tdrstudios.lobbyplugin.msgs;

import de.tdrstudios.lobbyplugin.Chat;
import de.tdrstudios.lobbyplugin.enums.FormatType;

import java.util.ArrayList;


public class MessageManager {
    private int highID;
    private int getHighID() {
        highID = highID +1;
        return highID;
    }

    private ArrayList<Message> messages = new ArrayList<>();
    private ArrayList<Message> getMessages() {
        return messages;
    }
    public boolean registerMessage(Message message) {
        if(getMessages().contains(message)) {
            return false;
        }else {
            if(getMessageByName(message.getName()) == null) {
                message.setId(getHighID());
                getMessages().add(message);
                return true;
            }else {
                return false;
            }
        }
    }
    public boolean removeMessage(Message message) {
        if(!getMessages().contains(message)) {
            return false;
        }else {
            getMessages().remove(message);
            message.setId(0);
            return true;
        }
    }
    public Message getMessageByID(int i) {
        Message returnMessage = null;
        for(Message message : getMessages()) {
            if(message.getId() == i) {
                returnMessage = message;
            }
        }
        return returnMessage;
    }
    public Message getMessageByName(String MessageName) {
        Message returnMessage = null;
        for(Message message : getMessages()) {
            if(message.getName().equalsIgnoreCase(MessageName)) {
                returnMessage = message;
            }
        }
        return returnMessage;
    }
    public int getLength() {
        return getMessages().size();
    }
    public ArrayList<Message> getDebug() {
        return getMessages();
    }

    public FormatMessage formatMessage(Message message , FormatType formatType){
        FormatMessage r = new FormatMessage("fomat" + message.getName(), message.getContent() , formatType);
        if(formatType == FormatType.INFO){
            r.setContent(Chat.getPrefix() + Chat.getChatColor() + message.getContent());
        }else if(formatType == FormatType.ERROR)
            r.setContent(Chat.getPrefix() + Chat.getErrorColor() + message.getContent());
        else if (formatType == FormatType.RAW)
            r.setContent(message.getContent());
        else if (formatType == FormatType.CANVAS)
            r.setContent(Chat.getPrefix() + message.getContent());
        return r;
    }


    /**
     * @apiNote This removes every Message from Manager!
     */
    public void clear() {
        getMessages().clear();
    }


    /**
     * @return if the Message is already registered
     */
    public boolean isRegisterd(Message message) {
        return getMessages().contains(message);
    }
}
