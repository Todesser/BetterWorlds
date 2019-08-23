package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.Teleport;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class TeleportCommand implements ICommand {

    private String commandName = "teleport";

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        if(args == null) {
            return;
        }

        if(!(sender instanceof Player)) {
            sender.sendMessage("To perform the teleport command you must be a player!");
            return;
        }

        Player player = (Player) sender;

        Teleport.teleport(player, args[0]);

    }
}
