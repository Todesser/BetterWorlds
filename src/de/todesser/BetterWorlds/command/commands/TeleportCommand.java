package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.Teleport;
import de.todesser.BetterWorlds.core.world.World;
import org.bukkit.Bukkit;
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
            sender.sendMessage("You used too few arguments!");
            return;
        }

        if(args.length > 2) {
            sender.sendMessage("You used to many arguments!");
            return;
        }

        if(args.length == 1) {

            if(!(sender instanceof Player)) {
                sender.sendMessage("To perform the teleport command you must be a player!");
                return;
            }

            Player player = (Player) sender;

            String world;
            String worldName;
            if(args[0].startsWith(World.STD_PREFIX)) {
                world = args[0].substring(World.STD_PREFIX.length());
                worldName = world;
            } else {
                world = World.PREFIX + args[0];
                worldName = args[0];
            }

            if(player.getWorld().getName().equals(world)) {
                player.sendMessage("You are already in " + worldName + "!");
                return;
            }

            player.sendMessage("Teleporting...");
            if(!Teleport.teleport(player, world)) {
                player.sendMessage(worldName + " does not exist!");
                return;
            }
            player.sendMessage("You have been teleported to " + worldName + "!");

            return;
        }

        if(args.length == 2) {

            Player player = Bukkit.getPlayerExact(args[0]);

            if(player == null) {
                sender.sendMessage(args[0] + " is currently offline!");
                return;
            }

            String world;
            String worldName;
            if(args[1].startsWith(World.STD_PREFIX)) {
                world = args[1].substring(World.STD_PREFIX.length());
                worldName = world;
            } else {
                world = World.PREFIX + args[1];
                worldName = args[1];
            }

            if(player.getWorld().getName().equals(world)) {
                sender.sendMessage(args[0] + " is already in " + worldName + "!");
                return;
            }

            sender.sendMessage("Teleporting...");
            if(!Teleport.teleport(player, world)) {
                sender.sendMessage(worldName + " does not exist!");
                return;
            }
            player.sendMessage("You have been teleported to " + worldName + "!");
            if(!player.getName().equals(sender.getName())) {
                sender.sendMessage(args[0] + " has been teleported to " + worldName + "!");
            }

            return;
        }

    }
}
