package pl.minecash.minecash.events;

import org.bukkit.Material;
import org.bukkit.block.EnchantingTable;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class ItemEnchantDisable implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = (ItemStack) event.getClickedBlock();
        Player player = event.getPlayer();
        Material mat = event.getClickedBlock().getType();
        EnchantingTable enchant = (EnchantingTable) event.getClickedBlock().getState();
        if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(event.getClickedBlock().getType().equals(Material.ENCHANTING_TABLE)) {
                    event.setCancelled(true);
                    event.setUseInteractedBlock(Event.Result.DENY);
                }
            }

    }
}
