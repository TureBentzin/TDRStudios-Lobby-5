package de.tdrstudios.additional.debug;

public class Point extends PointTrace{
    private int id = 0;
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }

    /**
     *
     * @param thread The current Thread
     * @implNote Set a easy Point
     * @note Don´t forget to use .point();
     */
    public Point(Thread thread) {
        super(thread);
    }

    public Point() {
        super(Thread.currentThread());
    }

    /**
     *
     * @param thread The current Thread
     * @implNote Set a easy Point
     * @note Don´t forget to use .point();
     */
    public Point(Thread thread, int id) {
        super(thread);
        setId(id);
    }

    public String point() {
        System.out.println("Code:" + getId() + " " + getMethodName() + "@" + getClassName() + "@" + getFileName() + " on line: " + getLineNumber());
        return "Code:" + getId() + " " + getMethodName() + "@" + getClassName() + "@" + getFileName() + " on line: " + getLineNumber();
    }
}
