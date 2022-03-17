package netjava.commands.main;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.Color;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import org.javacord.api.exception.MissingPermissionsException;

import netjava.Bot;
import netjava.util.*;

public class HelpCommand implements MessageCreateListener {
	public static Command cmd = new Command("help", "Gets info on commands");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().equalsIgnoreCase(Bot.botConfig.prefix + cmd.command)) {
			String mainCommands = "";
			for (int i = 0; i < Bot.commands.length; i++) {
				mainCommands +=  Bot.commands[i].command;
				if (i != Bot.commands.length - 1) mainCommands += " | ";
			}

			EmbedBuilder embed = new EmbedBuilder()
				.setTitle(Bot.api.getYourself().getName() + " Commands")
				.addField("Main", mainCommands)
				.setFooter("Run " + Bot.botConfig.prefix + "help [command] for command specific help")
				.setColor(Color.blue);
			event.getChannel().sendMessage(embed)
				.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}
}