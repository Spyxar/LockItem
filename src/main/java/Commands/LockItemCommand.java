package Commands;

import com.spyxar.lockitem.Color;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.spyxar.lockitem.LockItem;

public class LockItemCommand implements CommandExecutor
{
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args)
    {
        if (sender instanceof Player)
        {
            Player player = (Player) sender;
            if (player.getInventory().getItemInMainHand() == null || player.getInventory().getItemInMainHand().getType().equals(Material.AIR))
            {
                player.sendMessage(Color.RED + "You aren't holding anything.");
                return true;
            }
            ItemStack itemToLock = player.getInventory().getItemInMainHand();
            LockItem.itemLocker.toggleLock(itemToLock, player);
        }
        return true;
    }
}