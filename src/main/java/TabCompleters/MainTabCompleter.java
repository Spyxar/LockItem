package TabCompleters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class MainTabCompleter implements TabCompleter
{
    public ArrayList<String> allAvailableHintsForCommand = new ArrayList<>(Arrays.asList("lock", "reload", "version", "help"));

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("lock") || command.getName().equalsIgnoreCase("lockitem") && args.length == 1)
        {
            String firstArg = args[0].toLowerCase();
            if (sender instanceof Player)
            {
                String[] possibleHintsForPlayer = generateCommandHintsForSender(sender);

                List<String> list = new ArrayList<>();
                for (String hint : possibleHintsForPlayer)
                {
                    if (hint.startsWith(firstArg))
                    {
                        list.add(hint);
                    }
                }
                return list;
            }
        }
        return new ArrayList<>();
    }

    public String[] generateCommandHintsForSender(CommandSender sender)
    {
        ArrayList<String> possibleHints = allAvailableHintsForCommand;
        if (!sender.hasPermission("lockitem.lockitem"))
        {
            possibleHints.remove("lock");
        }
        if (!sender.hasPermission("lockitem.version"))
        {
            possibleHints.remove("version");
        }
        if (!sender.hasPermission("lockitem.admin"))
        {
            possibleHints.remove("reload");
            possibleHints.remove("help");
    }
        return possibleHints.toArray(new String[0]);
    }
}