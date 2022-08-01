package TabCompleters;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

public class MainTabCompleter implements TabCompleter
{
    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String label, String[] args)
    {
        if (command.getName().equalsIgnoreCase("lock") || command.getName().equalsIgnoreCase("lockitem") && args.length == 1)
        {
            String firstArg = args[0].toLowerCase();
            if (sender instanceof Player)
            {
                //instead of full list evry time, filter by perms
                String[] possibleHintsForPlayer = { "lock", "reload", "version", "help" };

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

}