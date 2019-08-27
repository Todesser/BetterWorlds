package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.resource_bundle.LanguageLoader;
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
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "no_worlds_unloaded"));
            } else {
                string.deleteCharAt(string.length()-1);
                sender.sendMessage(LanguageLoader.get("plugin_identifier", "unloaded_worlds", string));
            }

            return;
        }

        if(args.length != 1) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "too_many_arguments_for_command"));
            return;
        }


        String name = World.PREFIX + args[0];

        if(Bukkit.getWorld(name) == null) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "world_already_unloaded", args[0]));
            return;
        }

        sender.sendMessage(LanguageLoader.get("plugin_identifier", "unloading_world", args[0]));
        for (Player p : Bukkit.getWorld(name).getPlayers()) {
            p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
        }

        Bukkit.unloadWorld(Bukkit.getWorld(name), true);
        sender.sendMessage(LanguageLoader.get("plugin_identifier", "unloaded_world", args[0]));

    }
}
