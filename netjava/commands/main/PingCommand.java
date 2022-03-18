package netjava.commands.main;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import netjava.Bot;
import netjava.util.*;

public class PingCommand implements MessageCreateListener {
    public static Command cmd = new Command("ping");
    @Override
    public void onMessageCreate(MessageCreateEvent event) {
        if (event.getMessageContent().equalsIgnoreCase(Bot.config.prefix + cmd.command)) {
            String latency = Long.toString(Bot.api.getLatestGatewayLatency().toMillis());
            event.getChannel().sendMessage(":ping_pong: Pong! **" + latency + "ms**");
        }
    }
}