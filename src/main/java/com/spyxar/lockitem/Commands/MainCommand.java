package com.spyxar.lockitem.Commands;

import com.spyxar.lockitem.Color;
import com.spyxar.lockitem.LockItem;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class MainCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (!sender.hasPermission("lockitem.lockitem"))
        {
            sender.sendMessage(Color.RED + "You are missing the permission \"lockitem.lockitem\"");
            return true;
        }
        if (args.length == 0)
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage(Color.RED + "Only players can use this command");
                return true;
            }
            new LockItemCommand().onCommand(sender, command, label, args);
            return true;
        }
        else if (args[0].equalsIgnoreCase("lock"))
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage(Color.RED + "Only players can use this command");
                return true;
            }
            new LockItemCommand().onCommand(sender, command, label, args);
            return true;
        }
        else if (args[0].equalsIgnoreCase("help"))
        {
            new HelpCommand().onCommand(sender, command, label, args);
            return true;
        }
        else if (args[0].equalsIgnoreCase("reload"))
        {
            new ReloadCommand().onCommand(sender, command, label, args);
        }
        else if (args[0].equalsIgnoreCase("version") || args[0].equals("v"))
        {
            sender.sendMessage("§6LockItem §7- §6" + LockItem.pluginVersion + "\n§6Author: §eSpyxar\n§6Commands: §e/lockitem help\n§6GitHub: §ehttps://github.com/Spyxar/LockItem");
        }
        else
        {
            sender.sendMessage("§cUnknown command: §7" + args[0] + "§c. Use /lockitem help for a list of commands");
            return true;
        }
        return true;
    }
}