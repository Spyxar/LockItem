package Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.spyxar.lockitem.LockItem;

import org.bukkit.ChatColor;

public class PlaceEventListener implements Listener
{
    private LockItem plugin = LockItem.getInstance();

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent e)
    {
        ItemStack item = e.getItemInHand();
        boolean stopPlacingLockedItems = plugin.getCustomConfig().getBoolean("stop-placing-locked-items");
        if (LockItem.itemLocker.isLocked(item) && stopPlacingLockedItems)
        {
            e.setCancelled(true);
            e.getPlayer().sendMessage(ChatColor.RED + "This item is locked.");
            return;
        }
    }
}