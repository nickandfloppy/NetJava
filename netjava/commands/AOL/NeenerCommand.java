package netjava.commands.AOL;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import netjava.util.*;

public class NeenerCommand implements MessageCreateListener {
	public static Command cmd = new Command("neener", "neener");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith(("//" + cmd.command).toLowerCase())) {
			event.getChannel().sendMessage("http://floppydisk.aol-files.net/assets/neener.png");
		}
	}
}