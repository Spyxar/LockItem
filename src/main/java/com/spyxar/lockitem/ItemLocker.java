package com.spyxar.lockitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemLocker
{
    private final LockItem plugin = LockItem.getInstance();

    private final NamespacedKey lockKey;

    public ItemLocker(JavaPlugin plugin)
    {
        this.lockKey = new NamespacedKey(plugin, "item-lock");
    }

    public void toggleLock(ItemStack itemStack, Player player)
    {
        ItemMeta meta = itemStack.getItemMeta();
        if (meta == null)
        {
            return;
        }
        List<String> lore = new ArrayList<>();
        if(meta.hasLore())
        {
            lore = meta.getLore();
        }
        if (!meta.hasDisplayName() && plugin.getCustomConfig().getBoolean("only-lock-named-items"))
        {
            player.sendMessage(Color.RED + "You can only lock named items.");
            return;
        }
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(lockKey, PersistentDataType.BYTE))
        {
            container.remove(lockKey);
            player.sendMessage(Color.GREEN + "Unlocked item.");
            for (String l : lore)
            {
                if (l.contains("⋒"))
                {
                    lore.remove(l);
                    break;
                }
            }
        }
        else
        {
            container.set(lockKey, PersistentDataType.BYTE, (byte) 1);
            if (plugin.getCustomConfig().getBoolean("add-lore-to-item"))
            {
                lore.add(Color.RED + Color.ITALIC + Color.BOLD + "⋒" + Color.RESET + Color.RED + " Locked");
            }
            player.sendMessage(Color.GREEN + "Locked item.");
        }
        meta.setLore(lore);
        itemStack.setItemMeta(meta);
    }

    public boolean isLocked(ItemStack itemStack)
    {
        ItemMeta meta = itemStack.getItemMeta();
        if(meta == null)
        {
            return false;
        }
        PersistentDataContainer container = meta.getPersistentDataContainer();
        return container.has(lockKey, PersistentDataType.BYTE);
    }
}
