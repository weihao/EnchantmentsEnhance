package org.pixeltime.enchantmentsenhance.commands.console;


import org.bukkit.command.CommandSender;

public abstract class SubConsoleCommand {

    /**
     * /<command> <subcommand> args[0] args[1]
     */
    public SubConsoleCommand() {
    }


    public abstract void onCommand(CommandSender sender, String[] args);


    public abstract String name();


    public abstract String info();


    public abstract String[] aliases();
}
