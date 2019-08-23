package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class DeleteCommand implements ICommand {

    private String commandName = "delete";

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        if(args == null) {
            return;
        }

        String name = World.PREFIX + args[0];

        if(Bukkit.getWorld(name) == null) return;
        for (Player p : Bukkit.getWorld(name).getPlayers()) {
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }

        if(!World.delete(name)) {
            sender.sendMessage(args[0] + " does not exist!");
            return;
        }

        Config.remove("worlds", name);

    }
}
