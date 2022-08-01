package com.spyxar.lockitem;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;

public class ItemLocker
{
    private LockItem plugin = LockItem.getInstance();

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
        List<String> lore = new ArrayList<String>();
        if(meta.hasLore())
        {
            lore = meta.getLore();
        }
        if (!meta.hasDisplayName() && plugin.getCustomConfig().getBoolean("only-lock-named-items"))
        {
            player.sendMessage(ChatColor.RED + "You can only lock named items.");
            return;
        }
        PersistentDataContainer container = meta.getPersistentDataContainer();
        if (container.has(lockKey, PersistentDataType.BYTE))
        {
            container.remove(lockKey);
            player.sendMessage(ChatColor.GREEN + "Unlocked item.");
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
                lore.add(ChatColor.RED.toString() + ChatColor.ITALIC.toString() + ChatColor.BOLD.toString() + "⋒" + ChatColor.RESET.toString() + ChatColor.RED.toString() + " Locked");
            }
            player.sendMessage(ChatColor.GREEN + "Locked item.");
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