package netjava.commands.fun;

import java.awt.Color;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import org.javacord.api.exception.MissingPermissionsException;

import netjava.Bot;
import netjava.util.*;

public class EightBallCommand implements MessageCreateListener {
	public static Command cmd = new Command("8ball");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith((Bot.config.prefix + cmd.command).toLowerCase())) {
			int idx = (int)(Math.random() * answers.length);
			EmbedBuilder embed = new EmbedBuilder().setTitle("🎱 " + answers[idx]);
			if (idx <= 9)
				embed.setColor(new Color(59, 165, 93));
			else if (idx <= 14)
				embed.setColor(new Color(250, 168, 26));
			else if (idx > 14)
				embed.setColor(new Color(237, 66, 69));
			event.getChannel().sendMessage(embed)
				.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}

	static String[] answers = {
		"It is certain.", "It is decidedly so.", "Without a doubt.", "Yes definitely.", "You may rely on it.", "As I see it, yes.", "Most likely.", "Outlook good.", "Yes.", "Signs point to yes.", 
		"Reply hazy, try again.", "Ask again later.", "Better not tell you now.", "Cannot predict now.", "Concentrate and ask again.",
		"Don't count on it.", "My reply is no.", "My sources say no.", "Outlook not so good.", "Very doubtful."
	};
}