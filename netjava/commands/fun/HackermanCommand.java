package netjava.commands.fun;

import java.util.Random;
import java.awt.Color;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.exception.MissingPermissionsException;
import org.javacord.api.util.logging.ExceptionLogger;

import netjava.Bot;
import netjava.util.*;

public class HackermanCommand implements MessageCreateListener {
	public static Command cmd = new Command("hackerman");
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith((Bot.config.prefix + cmd.command).toLowerCase())) {
			int length = -1;
			try {
				length = Integer.valueOf(event.getMessageContent().replaceAll(Bot.config.prefix + cmd.command + " ", ""));
			} catch (Exception e) {
				event.getChannel().sendMessage("Error parsing length!")
					.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
			} finally {
				Random r = new Random();
				if (length < 6) {
					event.getChannel().sendMessage("Length must be greater than or equal to 6");
					return;
				}
				if (length > 100) {
					event.getChannel().sendMessage("Length must be less than 100");
					return;
				}

				String jargon = actions[r.nextInt(actions.length)];
				for (int i = 0; i < length; i++ ) {
					if (i == 1) {
						jargon += "with ";
						continue;
					}
					jargon += things[r.nextInt(things.length)] + " ";
				}
				EmbedBuilder embed = new EmbedBuilder()
					.setDescription("```cpp\n" + Util.Truncate(jargon, 2000) + "```")
					.setFooter("Length: " + String.valueOf(length))
					.setColor(new Color(50, 106, 201));
				// Send the embed. It logs every exception, besides missing permissions (you are not allowed to send message in the channel)
				event.getChannel().sendMessage(embed)
						.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
			}
		}
	}

	public String[] things = {
		"non-rotatable disk", "side fumbling CPU", "processor", "with multidimension network security access vulnerabilities",
		"oc6 level optical line", "microprocessor architecture", "server", "minecraft server",
		"webserver running Linux 0.01", "Linux system", "shell access terminals", "vulnerable networking firewall",
		"multiphase process memorizer", "x86 IBM level architecture", "network firewall daemon", "network routing device",
		"insecure Windows server", "Windows server 1985", "transdimensional phasing device"
	};

	public String[] actions = {
		"I've hacked into your ", "I'm breaking into the ", "I've hacked the ", "I've gained effective root access to your ",
		"I'm gaining root access to ", "I've hacked the ", "I broke into the "
	};
}