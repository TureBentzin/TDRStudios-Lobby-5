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

    public boolean hasPrefix() {
        return prefix;
    }

    public Message(String pname,String content) {
        setName(pname);
        setContent(content);
    }

    /**
     *
     * @param before
     * @param after
     * @return a String with the new Content! This isnt format Content only Raw text!
     */
    public String replace(String before, String after) {
        getContent().replace(before , after);
        return getContent();
    }

    public String replaceContent(String old, String s) {
        setContent(getContent().replace(old, s));
        return getContent();
    }

    public Message replaceMessage(String old, String s) {
        replaceContent(old, s);
        return this;

    }

    @Override
    public String toString() {
        return getContent();
    }
}
