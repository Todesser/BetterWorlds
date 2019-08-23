package de.todesser.BetterWorlds.command;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public interface ICommand {

    String getName();
    void execute(CommandSender sender, Command command, String[] args);

}
