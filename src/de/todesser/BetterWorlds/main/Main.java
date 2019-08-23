package de.todesser.BetterWorlds.main;

import de.todesser.BetterWorlds.command.Command;
import de.todesser.BetterWorlds.command.commands.CreateCommand;
import de.todesser.BetterWorlds.command.commands.DeleteCommand;
import de.todesser.BetterWorlds.command.commands.ListWorldsCommand;
import de.todesser.BetterWorlds.command.commands.TeleportCommand;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.io.Message;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.List;

public class Main extends JavaPlugin {

    private static Main plugin;

    public static Main getPlugin() {
        return plugin;
    }

    {
        plugin = this;
    }


    @Override
    public void onEnable() {
        List<String> list = Config.get("worlds");
        for (String s : Config.get("worlds")) {
            if(!new File(getServer().getWorldContainer().getPath() + "\\" + s).exists()) {
                list.remove(s);
            }
        }
        getConfig().set("worlds", list);
        saveConfig();

        Command.registerCommand(new DeleteCommand());
        Command.registerCommand(new CreateCommand());
        Command.registerCommand(new TeleportCommand());
        Command.registerCommand(new ListWorldsCommand());
        Command.enableMainCommand("world");

        Message.toConsole("Started!");
    }

    @Override
    public void onDisable() {
        //Bukkit.getWorlds().forEach(World::save);
        Message.toConsole("Stopped!");
    }
}
