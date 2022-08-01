package Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.spyxar.lockitem.LockItem;

public class ReloadCommand implements CommandExecutor
{
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player && !sender.hasPermission("lockitem.admin"))
        {
                sender.sendMessage(ChatColor.RED + "You have no permission to do this.");
                return true;
        }
        else if (sender.hasPermission("lockitem.admin"))
        {
            if (args.length == 1)
            {
                sender.sendMessage(ChatColor.RED + "Invalid syntax, use /lockitem reload all/config");
                return true;
            }
            else
            {
                //args[0] likely wont work because the new command structure would be /lock reload all
                LockItem plugin = LockItem.getInstance();
                if (args[1].equalsIgnoreCase("all"))
                {
                    plugin.getPluginLoader().disablePlugin(plugin);
                    plugin.getPluginLoader().enablePlugin(plugin);
                    sender.sendMessage(ChatColor.GREEN + "Reloaded the plugin!");

                }
                else if (args[1].equalsIgnoreCase("config"))
                {
                    plugin.createCustomConfig();
                    sender.sendMessage(ChatColor.GREEN + "Reloaded the config!");
                }
                else
                {
                    sender.sendMessage(ChatColor.RED + "Invalid syntax, use /lockitem reload all/config");
                    return true;
                }
            }
        }
        else
        {
            sender.sendMessage(ChatColor.RED + "Something went wrong, if you're executing the command via a player or the console, please report this issue on GitHub at https://github.com/Spyxar/LockItem/issues");
        }
        return true;
    }
}