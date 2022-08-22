package com.spyxar.lockitem.Commands;

import com.spyxar.lockitem.Color;
import com.spyxar.lockitem.LockItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!sender.hasPermission("lockitem.admin"))
        {
            sender.sendMessage(Color.RED + "You are missing the permission \"lockitem.admin\"");
            return true;
        }
        String commandString = "§6LockItem " + LockItem.pluginVersion + " §e- §6Help\n";
        if (args.length >= 2 && (args[1].equalsIgnoreCase("--permissions") || args[1].equalsIgnoreCase("--permission")))
        {
            commandString += getCommandHelpString("lockitem", "Locks the item in your hand", "lockitem.lockitem");
            commandString += getCommandHelpString("lockitem help", "View the help menu", "lockitem.admin");
            commandString += getCommandHelpString("lockitem reload", "Reload the plugin", "lockitem.admin");
            commandString += getCommandHelpString("lockitem version", "Shows some information about the plugin", "lockitem.version");
        }
        else
        {
            commandString += getCommandHelpString("lockitem", "Locks the item in your hand");
            commandString += getCommandHelpString("lockitem help", "View the help menu");
            commandString += getCommandHelpString("lockitem reload", "Reload the plugin");
            commandString += getCommandHelpString("lockitem version", "Shows some information about the plugin");
        }
        sender.sendMessage(commandString);
        return true;
    }

    private static String getCommandHelpString(String command, String description)
    {
        return getCommandHelpString(command, description, null);
    }

    private static String getCommandHelpString(String command, String description, String permission)
    {
        String returnValue = Color.GOLD + "/" + command + " " + Color.GRAY + "- " + Color.YELLOW + description;
        if (permission != null)
        {
            returnValue += "\n  " + Color.GRAY + "Permission: " + permission;
        }
        return returnValue + "\n";
    }
}