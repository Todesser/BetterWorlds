package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.Teleport;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.resource_bundle.LanguageLoader;
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
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "too_few_arguments_for_command"));
            return;
        }

        if(args.length > 2) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "too_many_arguments_for_command"));
            return;
        }

        if(args.length == 1) {

            if(!(sender instanceof Player)) {
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "must_be_player", "teleport"));
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
                player.sendMessage(LanguageLoader.get("plugin_identifier", "you_already_located_in_world", worldName));
                return;
            }

            player.sendMessage(LanguageLoader.get("plugin_identifier", "teleporting_to_world", worldName));
            if(!Teleport.teleport(player, world)) {
                player.sendMessage(LanguageLoader.get("plugin_identifier", "world_not_existing", worldName));
                return;
            }
            player.sendMessage(LanguageLoader.get("plugin_identifier", "teleported_to_world", worldName));

            return;
        }

        if(args.length == 2) {

            Player player = Bukkit.getPlayerExact(args[0]);

            if(player == null) {
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "player_is_offline", args[0]));
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
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "player_already_located_in_world", args[0], worldName));
                return;
            }

            sender.sendMessage(LanguageLoader.get("plugin_identifier", "teleporting_to_world", worldName));
            if(!Teleport.teleport(player, world)) {
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "world_not_existing", worldName));
                return;
            }
            player.sendMessage(LanguageLoader.get("plugin_identifier", "teleported_to_world", worldName));
            if(!player.getName().equals(sender.getName())) {
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "player_teleported_to_world", args[0], worldName));
            }

        }

    }
}
