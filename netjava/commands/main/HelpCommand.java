package netjava.commands.main;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import java.awt.Color;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;
import org.javacord.api.exception.MissingPermissionsException;

import netjava.Bot;
import netjava.commands.Commands;
import netjava.util.*;

public class HelpCommand implements MessageCreateListener {
	public static Command cmd = new Command("help", "Gets info on commands");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().equalsIgnoreCase(Bot.config.prefix + cmd.command)) {
			String mainCommands = "`";
			for (int i = 0; i < Commands.mainInfo.length; i++) {
				mainCommands +=  Commands.mainInfo[i].command;
				if (i != Commands.mainInfo.length - 1) mainCommands += "`, `";
				else mainCommands += "`";
			}

			String funCommands = "`";
			for (int i = 0; i < Commands.funInfo.length; i++) {
				funCommands +=  Commands.funInfo[i].command;
				if (i != Commands.funInfo.length - 1) funCommands += "`, `";
				else funCommands += "`";
			}

			String AOLCommands = "`";
			for (int i = 0; i < Commands.AOLInfo.length; i++) {
				AOLCommands +=  Commands.AOLInfo[i].command;
				if (i != Commands.AOLInfo.length - 1) AOLCommands += "`, `";
				else AOLCommands += "`";
			}

			EmbedBuilder embed = new EmbedBuilder()
				.setTitle(Bot.api.getYourself().getName() + " Commands")
				.addField(Commands.Main.name, mainCommands)
				.addField(Commands.Fun.name, funCommands)
				.addField(Commands.AOL.name, AOLCommands)
				.setFooter("Run " + Bot.config.prefix + "help [command] for command specific help")
				.setColor(new Color(50, 106, 201));
			event.getChannel().sendMessage(embed)
				.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
		}
	}
}