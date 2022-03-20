package netjava;

import java.io.FileNotFoundException;

import org.javacord.api.DiscordApi;
import org.javacord.api.DiscordApiBuilder;
import org.javacord.api.entity.activity.ActivityType;

import netjava.commands.Commands;
import netjava.commands.commandCategory;

public class Bot {
    public static Config config;
    public static String version = "DEV-0.0.1";
    public static DiscordApi api;

    public static commandCategory fun;

    public static void main(String[] args) throws Exception {
        try {
            config = new Config();
        } catch (FileNotFoundException e) {
            System.out.println("conf.json not found!");
            System.exit(0);
        }
        // Initialise API with token from config and set status
        api = new DiscordApiBuilder().setToken(Bot.config.token).login().join();
        api.updateActivity(ActivityType.PLAYING, Bot.config.status);
    
        // Load categories
        Commands.loadCategory(Commands.Main);
        Commands.loadCategory(Commands.Fun);
        Commands.loadCategory(Commands.AOL);

        // Output the user the bot is logged in as to the console
        System.out.println("Connected as user " + api.getYourself().getDiscriminatedName());
    }
}
