package Listeners;

import com.spyxar.lockitem.Color;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import com.spyxar.lockitem.LockItem;

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
            e.getPlayer().sendMessage(Color.RED + "This item is locked.");
        }
    }
}