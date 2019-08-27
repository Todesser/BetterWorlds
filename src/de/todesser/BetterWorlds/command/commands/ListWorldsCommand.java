package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.resource_bundle.LanguageLoader;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class ListWorldsCommand implements ICommand {

    private String commandName = "list";

    @Override
    public String getName() {
        return commandName;
    }

    @Override
    public void execute(CommandSender sender, Command command, String[] args) {

        if(args != null) {
            sender.sendMessage(LanguageLoader.get("plugin_identifier", "too_many_arguments_for_command"));
            return;
        }

        StringBuilder string = new StringBuilder(LanguageLoader.get("plugin_identifier", "world_list"));
        for(String s : Config.get("worlds")) {
            string.append(s.substring(World.PREFIX.length())).append(", ");
        }
        string.replace(string.length()-2,string.length()-1, "");
        sender.sendMessage(string.toString());

    }

}
