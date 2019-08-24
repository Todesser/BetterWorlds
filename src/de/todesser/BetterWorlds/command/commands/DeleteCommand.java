package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class DeleteCommand implements ICommand {

    private String commandName = "delete";

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        if(args == null) {
            sender.sendMessage("You used too few arguments!");
            return;
        }

        if(args.length != 1) {
            sender.sendMessage("You used too many arguments!");
            return;
        }

        String name = World.PREFIX + args[0];

        sender.sendMessage("Deleting " + args[0] + "...");

        if(Bukkit.getWorld(name) != null) {
            for (Player p : Bukkit.getWorld(name).getPlayers()) {
                p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
        }

        if(!World.delete(name)) {
            sender.sendMessage(args[0] + " does not exist!");
            return;
        }

        Config.remove("worlds", name);

        sender.sendMessage(args[0] + " has been deleted!");
    }
}
