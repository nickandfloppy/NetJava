package netjava.commands.fun;

import java.awt.Color;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import org.javacord.api.exception.MissingPermissionsException;

import netjava.Bot;
import netjava.util.*;

public class RateCommand implements MessageCreateListener {
	public static Command cmd = new Command("rate");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith((Bot.config.prefix + cmd.command).toLowerCase())) {
            String item = event.getMessageContent().toLowerCase().replaceFirst(Bot.config.prefix + cmd.command + " ", "");
            String score;
            if (item.toLowerCase().equals("saab")) { score = "GRIMP"; }
			else { score = Integer.toString((int)(Math.random() * 11)); }
			EmbedBuilder embed = new EmbedBuilder()
                .setTitle("🤔 I'd give " + item + " a solid " + score + "/10")
                .setColor(new Color(50, 106, 201));
			event.getChannel().sendMessage(embed)
				.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}
}