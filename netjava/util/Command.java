package netjava.util;

public class Command {
    public String command;
    public String description;
    public String usage;

    public Command(String command) {
        this.command = command;
        this.description = null;
        this.usage = "";
    }

    public Command(String command, String description) {
        this.command = command;
        this.description = description;
        this.usage = "";
    }

    public Command(String command, String usage, String description) {
        this.command = command;
        this.usage = usage;
        this.description = description;
    }
}
