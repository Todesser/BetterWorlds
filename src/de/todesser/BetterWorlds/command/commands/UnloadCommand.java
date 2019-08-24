package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class UnloadCommand implements ICommand {

    private String commandName = "unload";

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        if(args == null) {
            StringBuilder string = new StringBuilder();
            for (String world : Config.get("worlds")) {
                if(Bukkit.unloadWorld(world, true)) {
                    string.append(world.substring(World.PREFIX.length())).append(",");
                }
            }
            if(string.toString().isEmpty()) {
                sender.sendMessage("No worlds have been unloaded!");
            } else {
                string.deleteCharAt(string.length()-1);
                sender.sendMessage(string + " have been unloaded!");
            }

            return;
        }

        if(args.length != 1) {
            sender.sendMessage("You used too many arguments!");
            return;
        }


        String name = World.PREFIX + args[0];

        if(Bukkit.getWorld(name) == null) {
            sender.sendMessage(args[0] + " is already unloaded or does not exist!");
            return;
        }

        sender.sendMessage("Unloading " + args[0] + "...");
        for (Player p : Bukkit.getWorld(name).getPlayers()) {
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }

        Bukkit.unloadWorld(Bukkit.getWorld(name), true);
        sender.sendMessage(args[0] + " has been unloaded!");

    }
}
