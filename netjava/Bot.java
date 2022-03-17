package netjava;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import netjava.commands.main.*;
import netjava.util.*;

public class Bot {
    public static Config botConfig;
    public static String version = "DEV-0.0.1";
    public static DiscordApi api;
    public static Command[] commands = { WhoisCommand.cmd, PingCommand.cmd, AboutCommand.cmd, HelpCommand.cmd };

    public static void main(String[] args) throws Exception {
        botConfig = new Config();
        api = new DiscordApiBuilder().setToken(Bot.botConfig.token).login().join();

        // Add a listener which answers with "Pong!" if someone writes "!ping"        
        api.addMessageCreateListener(new WhoisCommand());
        api.addMessageCreateListener(new PingCommand());
        api.addMessageCreateListener(new HelpCommand());
        api.addMessageCreateListener(new AboutCommand());

        System.out.println("Connected as user " + api.getYourself().getDiscriminatedName());
    }
}
