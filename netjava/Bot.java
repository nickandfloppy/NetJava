package netjava;

import java.io.FileNotFoundException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;

import netjava.commands.main.*;
import netjava.commands.AOL.*;
import netjava.commands.fun.HackermanCommand;
import netjava.util.*;

public class Bot {
    public static Config config;
    public static String version = "DEV-0.0.1";
    public static DiscordApi api;
    public static Command[] mainCommands = { WhoisCommand.cmd, PingCommand.cmd, AboutCommand.cmd, HelpCommand.cmd };
    public static Command[] AOLCommands = { RollCommand.cmd };
    public static Command[] funCommands = { HackermanCommand.cmd };

    public static void main(String[] args) throws Exception {
        try {
            config = new Config();
        } catch (FileNotFoundException e) {
            System.out.println("config.json not found!");
            System.exit(0);
        }
        // Initialise API with token from config
        api = new DiscordApiBuilder().setToken(Bot.config.token).login().join();

        // Add a listener for each command
        // TODO: Find a better way to do this
        api.addMessageCreateListener(new WhoisCommand());
        api.addMessageCreateListener(new PingCommand());
        api.addMessageCreateListener(new HelpCommand());
        api.addMessageCreateListener(new AboutCommand());
        api.addMessageCreateListener(new RollCommand());
        api.addMessageCreateListener(new HackermanCommand());

        // Output the user the bot is logged in as to the console
        System.out.println("Connected as user " + api.getYourself().getDiscriminatedName());
    }
}
