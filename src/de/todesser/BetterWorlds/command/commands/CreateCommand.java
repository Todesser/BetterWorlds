package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.core.world.WorldType;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.resource_bundle.LanguageLoader;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CreateCommand implements ICommand {

    private String commandName = "create";

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

        sender.sendMessage(LanguageLoader.get("plugin_identifier", "creating_world", args[0]));

        if(!World.create(name, WorldType.EMPTY)) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "world_already_existing", args[0]));
            return;
        }

        Config.set("worlds", name);

        sender.sendMessage(LanguageLoader.get("plugin_identifier", "created_world", args[0]));

        if(sender instanceof Player) {
            Player player = (Player) sender;
            org.bukkit.World w = Bukkit.getWorld(name);
            player.teleport(w.getSpawnLocation());
        }

    }

}
