package de.tdrstudios.lobbyplugin.utils.config;

import org.bukkit.configuration.InvalidConfigurationException;

import java.io.PrintStream;
import java.io.PrintWriter;

public class InvalidConfigurationError extends InvalidConfigurationException {

    public InvalidConfigurationError(String cause) {
        setMessage(cause);
    }

    private String message = "The config is invalid!";

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public void printStackTrace() {
        System.out.println(getMessage());
    }

    @Override
    public void printStackTrace(PrintWriter s) {
        s.println(getMessage());
    }

    @Override
    public void printStackTrace(PrintStream s) {
        s.println(getMessage());
    }

    @Override
    public StackTraceElement[] getStackTrace() {
        StackTraceElement[] elements = new StackTraceElement[0];
        return elements;
    }

    @Override
    public synchronized Throwable fillInStackTrace() {
        return this;
    }

    @Override
    public void setStackTrace(StackTraceElement[] stackTrace) {

    }
}
