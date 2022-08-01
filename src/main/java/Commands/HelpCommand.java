package Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

public class HelpCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        String commandString;
        if (args[1].equalsIgnoreCase("--permission") || args[1].equalsIgnoreCase("--permissions"))
        {

        }
        //if args contains --permission add permission to the thing
        //  §7Permission: lockitem.lockitem\n
        sender.sendMessage("§6LockItem v1.0 §e- §6Help\n/lock §7- §eLocks the item in your hand\n");
        //sender.sendMessage("§6LockItem v1.0 §e- §6Help\n/lock §7- §eLocks the item in your hand\n  &7Permission: lockitem.lockitem\n");
        return true;
    }
}