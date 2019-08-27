package de.todesser.BetterWorlds.main;

import de.todesser.BetterWorlds.command.Command;
import de.todesser.BetterWorlds.command.commands.*;
import de.todesser.BetterWorlds.core.world.World;
import de.todesser.BetterWorlds.io.Config;
import de.todesser.BetterWorlds.io.Message;
import de.todesser.BetterWorlds.resource_bundle.LanguageLoader;
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
        File[] files = Main.getPlugin().getServer().getWorldContainer().listFiles();
        if(files != null) {
            for(File f : files) {
                if(f.isDirectory()) {
                    if(f.getName().startsWith(World.PREFIX)) {
                        if(!list.contains(f.getName())) {
                            list.add(f.getName());
                        }
                    }
                }
            }
        }
        getConfig().set("worlds", list);
        saveConfig();

        Command.registerCommand(new DeleteCommand());
        Command.registerCommand(new CreateCommand());
        Command.registerCommand(new TeleportCommand());
        Command.registerCommand(new UnloadCommand());
        Command.registerCommand(new ListWorldsCommand());
        Command.enableMainCommand("world");

        Message.toConsole(LanguageLoader.get("console_plugin_identifier", "loaded_plugin"));
    }

    @Override
    public void onDisable() {
        //Bukkit.getWorlds().forEach(World::save);
        Message.toConsole(LanguageLoader.get("console_plugin_identifier", "unloaded_plugin"));
    }
}
