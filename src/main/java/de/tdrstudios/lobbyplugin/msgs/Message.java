package de.tdrstudios.lobbyplugin.msgs;

public class Message {
    private String name;
    private int id;
    private String content;
    private boolean prefix = true;



    public void setPrefix(boolean prefix) {
        this.prefix = prefix;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setContent(String content) {
        this.content = content;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getContent() {
        return content;
    }

    public int getId() {
        return id;
    }

    public boolean havePrefix() {
        return prefix;
    }

    public Message(String pname,String content) {
        setName(pname);
        setContent(content);
    }

    @Override
    public String toString() {
        return "Message{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", content='" + content + '\'' +
                ", prefix=" + prefix +
                '}';
    }
}
