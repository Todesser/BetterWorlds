package de.todesser.BetterWorlds.command;

import de.todesser.BetterWorlds.main.Main;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import java.util.HashSet;
import java.util.Set;

public class Command implements CommandExecutor {

    private static Set<ICommand> commands;

    static {
        commands = new HashSet<>();
    }

    public static void registerCommand(ICommand command) {
        commands.add(command);
    }

    public static void enableMainCommand(String commandName) {
        Main.getPlugin().getCommand(commandName).setExecutor(new Command());
    }

    @Override
    public boolean onCommand(CommandSender sender, org.bukkit.command.Command command, String label, String[] args) {

        if(!sender.hasPermission("BetterWorlds.permission")) {
            sender.sendMessage("You are not allowed to use this command!");
            return true;
        }

        if(args.length == 0) {
            //outputs usage
            return false;
        }

        for (ICommand cmd : commands) {
            if(args[0].equals(cmd.getName())) {
                String[] arr;
                if(args.length == 1) {
                    arr = null;
                } else {
                    arr = new String[args.length-1];
                    System.arraycopy(args, 1, arr, 0, arr.length);
                }
                cmd.execute(sender, command, arr);
                return true;
            }
        }

        //outputs usage
        return false;
    }
}
