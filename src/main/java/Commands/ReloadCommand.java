package Commands;

import com.spyxar.lockitem.Color;
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
                sender.sendMessage(Color.RED + "You have no permission to do this.");
                return true;
        }
        else if (sender.hasPermission("lockitem.admin"))
        {
            if (args.length == 1)
            {
                sender.sendMessage(Color.RED + "Invalid syntax, use /lockitem reload all/config");
                return true;
            }
            else
            {
                LockItem plugin = LockItem.getInstance();
                if (args[1].equalsIgnoreCase("all"))
                {
                    plugin.getPluginLoader().disablePlugin(plugin);
                    plugin.getPluginLoader().enablePlugin(plugin);
                    sender.sendMessage(Color.GREEN + "Reloaded the plugin!");

                }
                else if (args[1].equalsIgnoreCase("config"))
                {
                    plugin.createCustomConfig();
                    sender.sendMessage(Color.GREEN + "Reloaded the config!");
                }
                else
                {
                    sender.sendMessage(Color.RED + "Invalid syntax, use /lockitem reload all/config");
                    return true;
                }
            }
        }
        else
        {
            sender.sendMessage(Color.RED + "Something went wrong, if you're executing the command via a player or the console, please report this issue on GitHub at https://github.com/Spyxar/LockItem/issues");
        }
        return true;
    }
}