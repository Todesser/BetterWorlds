package de.todesser.BetterWorlds.io;

import de.todesser.BetterWorlds.main.Main;
import org.bukkit.command.ConsoleCommandSender;

public class Message {

    private static ConsoleCommandSender console;

    static {
        console = Main.getPlugin().getServer().getConsoleSender();
    }

    public static void toConsole(String msg) {
        console.sendMessage(msg);
    }

}
