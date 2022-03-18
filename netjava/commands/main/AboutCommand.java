package netjava.commands.main;

import java.awt.Color;
import java.net.InetAddress;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

import netjava.util.*;
import netjava.Bot;

public class AboutCommand implements MessageCreateListener{
	public static Command cmd = new Command("about", "Gets info about the bot");

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		// Check if the message content equals "!userInfo"
		if (event.getMessageContent().equalsIgnoreCase(Bot.config.prefix + cmd.command)) {
			String osName = System.getProperty("os.name") + " " + System.getProperty("os.version") + " " + System.getProperty("os.arch");
			EmbedBuilder embed = new EmbedBuilder()
				.setTitle("About " + Bot.api.getYourself().getName())
				.addField("Maintainer", "floppydisk", true)
				.addField("Contributors", "nick99nack", true)
				.addField("Library", "Javacord", true)
				.addField("Host OS", osName, true)
				.setThumbnail(Bot.api.getYourself().getAvatar())
				.setFooter(Bot.api.getYourself().getName() + " · version " + Bot.version)
				.setColor(new Color(50, 106, 201));
			try {
				embed.addField("Hostname", InetAddress.getLocalHost().getHostName(), true);
			} catch (Exception e) {}
			// Send the embed. It logs every exception, besides missing permissions (you are not allowed to send message in the channel)
			event.getChannel().sendMessage(embed)
				.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}
}
