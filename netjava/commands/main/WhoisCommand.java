package netjava.commands.main;

import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.Color;

//import java.io.ObjectInputFilter.Config;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.util.logging.ExceptionLogger;

import netjava.Bot;
import netjava.util.*;

public class WhoisCommand implements MessageCreateListener {

	public static Command cmd = new Command("whois", "Gets info on your user");
	/*
	 * This command can be used to display information about the user who used the command.
	 * It's a good example for the MessageAuthor, MessageBuilder and ExceptionLogger class.
	 */
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		// Check if the message content equals "!userInfo"
		if (event.getMessageContent().equalsIgnoreCase(Bot.botConfig.prefix + cmd.command)) {
			MessageAuthor author = event.getMessage().getAuthor();
			Date creationDate = Date.from(author.getCreationTimestamp());
			SimpleDateFormat fmt = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
			EmbedBuilder embed = new EmbedBuilder()
					.addField("ID", author.getIdAsString(), false)
					.addField("Created On", fmt.format(creationDate), true)
					.addField("Name + Discriminator", author.getDiscriminatedName(), true)
					.setThumbnail(author.getAvatar())
					.setColor(new Color(50, 106, 201))
					.setAuthor(author);
			// Send the embed. It logs every exception, besides missing permissions (you are not allowed to send message in the channel)
			event.getChannel().sendMessage(embed)
					.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}

}
