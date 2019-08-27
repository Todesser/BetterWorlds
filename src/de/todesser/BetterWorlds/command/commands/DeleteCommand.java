package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.resource_bundle.LanguageLoader;
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
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "too_few_arguments_for_command"));
            return;
        }

        if(args.length != 1) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "too_many_arguments_for_command"));
            return;
        }

        String name = World.PREFIX + args[0];

        sender.sendMessage(LanguageLoader.get("plugin_identifier", "deleting_world", args[0]));

        if(Bukkit.getWorld(name) != null) {
            for (Player p : Bukkit.getWorld(name).getPlayers()) {
                p.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            }
        }

        if(!World.delete(name)) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "world_not_existing", args[0]));
            return;
        }

        Config.remove("worlds", name);

        sender.sendMessage(LanguageLoader.get("plugin_identifier", "deleted_world", args[0]));
    }
}
