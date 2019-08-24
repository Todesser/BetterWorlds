package de.todesser.BetterWorlds.command.commands;

import de.todesser.BetterWorlds.command.ICommand;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
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
            sender.sendMessage("You used too many arguments!");
            return;
        }

        StringBuilder string = new StringBuilder("Better Worlds: ");
        for(String s : Config.get("worlds")) {
            string.append(s.substring(World.PREFIX.length())).append(", ");
        }
        string.replace(string.length()-2,string.length()-1, "");
        sender.sendMessage(string.toString());

    }

}
