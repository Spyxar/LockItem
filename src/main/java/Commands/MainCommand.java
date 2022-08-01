package Commands;

import org.bukkit.ChatColor;
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
            sender.sendMessage(ChatColor.RED + "You are missing the permission \"lockitem.lockitem\"");
            return true;
        }
        if (args.length == 0)
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage(ChatColor.RED + "Only players can use this command");
                return true;
            }
            new LockItemCommand().onCommand(sender, command, label, args);
            return true;
        }
        else if (args[0].equalsIgnoreCase("lock"))
        {
            if (!(sender instanceof Player))
            {
                sender.sendMessage(ChatColor.RED + "Only players can use this command");
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
            sender.sendMessage("§6LockItem v1.0\n§6Author: §eSpyxar\n§6Commands: §e/lockitem help");
        }
        else
        {
            sender.sendMessage("§cUnknown command: §7" + args[0] + "§c. Use /lockitem help for a list of commands");
            return true;
        }
        return true;
    }
}