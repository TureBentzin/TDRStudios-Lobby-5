package de.tdrstudios.additional.debug;

public class PointTrace {

    protected StackTraceElement[] stackTraceElements = new StackTraceElement[0];
    public void setStackTraceElements(StackTraceElement[] stackTraceElements) {
        this.stackTraceElements = stackTraceElements;
    }
    public StackTraceElement[] getStackTraceElements() {
        return stackTraceElements;
    }

    private String className = "";
    public void setClassName(String className) {
        this.className = className;
    }
    public String getClassName() {
        return className;
    }

    private String methodName = "";
    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }
    public String getMethodName() {
        return methodName;
    }

    private String fileName = "";
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
    public String getFileName() {
        return fileName;
    }

    private int lineNumber = 0;
    public void setLineNumber(int lineNumber) {
        this.lineNumber = lineNumber;
    }
    public int getLineNumber() {
        return lineNumber;
    }

    public PointTrace(Thread current) {
        setStackTraceElements(current.getStackTrace());
        String className = current.getStackTrace()[3].getClassName();
        String methodName = current.getStackTrace()[3].getMethodName();
        String fileName =   current.getStackTrace()[3].getFileName();
        int lineNumber = current.getStackTrace()[3].getLineNumber();

        setClassName(className);
        setMethodName(methodName);
        setFileName(fileName);
        setLineNumber(lineNumber);
    }
}
