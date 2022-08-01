package Listeners;

import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.inventory.ItemStack;

import com.spyxar.lockitem.LockItem;

public class DropEventListener implements Listener
{
    @EventHandler
    public void onItemDrop(PlayerDropItemEvent e)
    {
        ItemStack item = e.getItemDrop().getItemStack();
        if (LockItem.itemLocker.isLocked(item) == true)
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "This item is locked.");
        }
    }
}