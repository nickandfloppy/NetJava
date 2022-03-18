package netjava.commands.AOL;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import netjava.util.*;

public class RollCommand implements MessageCreateListener {
	public static Command cmd = new Command("roll");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith(("//" + cmd.command).toLowerCase())) {
			MessageAuthor author = event.getMessage().getAuthor();
			String cmd = event.getMessageContent().toLowerCase();
			int dice;
			int sides;
			if (cmd.equals("//roll")) {
				dice = 2;
				sides = 6;    
			} else {
				dice = parseDice(cmd);
				sides = parseSides(cmd);
			}
			if (dice > 15 || dice < 1) {
				event.getChannel().sendMessage("The amount of dice must be a whole number from one through fifteen.");
				return;
			}
			if (sides > 999 || sides < 1) {
				event.getChannel().sendMessage("The amount of sides must be a whole number from one through 999.");
				return;
			}
			String rolls = "";
			for (int i = 0; i < dice; i++ ) {
				rolls += (int)(Math.random()*sides+1);
				rolls += " ";
			}
			event.getChannel().sendMessage(author.getName() + " rolled " + Integer.toString(dice) + " " + Integer.toString(sides) + "-sided dice: " + rolls);
		}
	}

	public int parseDice(String command) {
		command = command.replaceAll("-dice", "?");
		command = command.replaceAll("-sides", "!");
		command = command.substring(command.indexOf("?") + 1);
		command = command.substring(0, command.indexOf("!"));
		return Integer.parseInt(command);
	}

	public int parseSides(String command) {
		command = command.replaceAll("-sides", "!");
		command = command.substring(command.indexOf("!") + 1);
		return Integer.parseInt(command);
	}
}