package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.core.world.WorldType;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class CreateCommand implements ICommand {

    private String commandName = "create";

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

        if(!World.create(name, WorldType.EMPTY)) {
            sender.sendMessage("World already exists!");
            return;
        }

        Config.set("worlds", name);

        if(sender instanceof Player) {
            Player player = (Player) sender;
            org.bukkit.World w = Bukkit.getWorld(name);
            player.teleport(w.getSpawnLocation());
        }

    }

}
