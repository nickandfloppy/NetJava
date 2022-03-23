package netjava.commands;

import org.javacord.api.listener.message.MessageCreateListener;

import netjava.commands.main.*;
import netjava.commands.fun.*;
import netjava.commands.AOL.*;
import netjava.commands.owner.*;
import netjava.Bot;
import netjava.util.*;

public class Commands {
    // Define arrays of commands
    static MessageCreateListener[] mainCommands = { new WhoisCommand(), new PingCommand(), new AboutCommand(),  new HelpCommand() };
    static MessageCreateListener[] funCommands = { new HackermanCommand(), new EightBallCommand() };
    static MessageCreateListener[] AOLCommands = { new RollCommand() };
    static MessageCreateListener[] ownerCommands = { new ExecCommand() };

    // Define the categories
    public static commandCategory Main = new commandCategory("Main", mainCommands);
    public static commandCategory Fun = new commandCategory("Fun", funCommands);
    public static commandCategory AOL = new commandCategory("<a:ninacrazy:954394600494235718> AOL (// prefix)", AOLCommands);
    public static commandCategory Owner = new commandCategory("Owner", ownerCommands);

    public static void loadCategory(commandCategory category) {
        for (MessageCreateListener command : category.commands) {
            (Bot.api).addMessageCreateListener(command);
        }
    }

    public static Command[] mainInfo = { WhoisCommand.cmd, PingCommand.cmd, AboutCommand.cmd, HelpCommand.cmd };
    public static Command[] AOLInfo = { RollCommand.cmd };
    public static Command[] funInfo = { HackermanCommand.cmd, EightBallCommand.cmd };
    public static Command[] ownerInfo = { ExecCommand.cmd };
}
