package netjava.commands.owner;

import java.nio.charset.Charset;
import java.util.Arrays;
import java.awt.Color;

import org.apache.commons.io.IOUtils;

import org.javacord.api.entity.message.MessageAuthor;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.util.logging.ExceptionLogger;

import org.javacord.api.exception.MissingPermissionsException;

import netjava.Bot;
import netjava.util.*;

public class ExecCommand implements MessageCreateListener {
	public static Command cmd = new Command("exec");

	@Override
	public void onMessageCreate(MessageCreateEvent event) {
		if (event.getMessageContent().startsWith(Bot.config.prefix + cmd.command)) {
			MessageAuthor author = event.getMessage().getAuthor();
			if (!Arrays.asList(Bot.config.ownerID).contains(author.getId())) return;
			if (event.getMessageContent().equalsIgnoreCase(Bot.config.prefix + cmd.command))
				return;
			String command = event.getMessageContent().replaceFirst(Bot.config.prefix + cmd.command + " ", "");
			try {
				Process verProc = new ProcessBuilder("/bin/bash", "-c", "echo \"${BASH_VERSION}\"").start();
				String ver = IOUtils.toString(verProc.getInputStream(), Charset.defaultCharset());
				Process proc = new ProcessBuilder("/bin/bash", "-c", command).start();
				String stderr = IOUtils.toString(proc.getErrorStream(), Charset.defaultCharset());
				String stdout = IOUtils.toString(proc.getInputStream(), Charset.defaultCharset());
				EmbedBuilder embed = new EmbedBuilder()
					.setTitle("Exec")
					.setFooter("Bash " + ver)
					.setColor(new Color(50, 106, 201));
				if (stderr.isBlank()) {
					embed.addField("Command", "```bash\n" + command + "\n```");
					embed.addField("Output", "```bash\n" + stdout + "\n```");
				}
				else
					embed.addField("Error", "```bash\n" + stderr + "\n```");
				event.getChannel().sendMessage(embed)
					.exceptionally(ExceptionLogger.get(MissingPermissionsException.class));
			} catch (Exception e) {
				//event.getChannel().sendMessage("**Exception:** ```\n" + ExceptionUtils.getStackTrace(e) + "```");
			}
		}
	}
}