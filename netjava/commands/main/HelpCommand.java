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
		if (event.getMessageContent().equalsIgnoreCase(Bot.config.prefix + cmd.command)) {
			String mainCommands = "";
			for (int i = 0; i < Bot.mainCommands.length; i++) {
				mainCommands +=  Bot.mainCommands[i].command;
				if (i != Bot.mainCommands.length - 1) mainCommands += " | ";
			}

			String AOLCommands = "";
			for (int i = 0; i < Bot.AOLCommands.length; i++) {
				AOLCommands +=  Bot.AOLCommands[i].command;
				if (i != Bot.AOLCommands.length - 1) AOLCommands += " | ";
			}

			EmbedBuilder embed = new EmbedBuilder()
				.setTitle(Bot.api.getYourself().getName() + " Commands")
				.addField("<a:ninacrazy:954394600494235718> AOL (// prefix)", AOLCommands)
				.addField("Main", mainCommands)
				.setFooter("Run " + Bot.config.prefix + "help [command] for command specific help")
				.setColor(new Color(50, 106, 201));
			event.getChannel().sendMessage(embed)
				.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}
}