package netjava.commands;

import org.javacord.api.listener.message.MessageCreateListener;

public class commandCategory {
    public MessageCreateListener[] commands;
    public String name;

    public commandCategory(String name, MessageCreateListener[] commands) {
        this.commands = commands;
        this.name = name;
    }
}
