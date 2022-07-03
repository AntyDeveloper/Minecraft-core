package pl.minecash.minecash.events;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class DropEvent implements Listener {
        @EventHandler
        public void onPlayerBlockBreak(BlockBreakEvent event) {
                Player player = event.getPlayer();
                Block b = event.getBlock();
                if (b.getType() == Material.STONE) {
                    int rand = Random.getRandom();
                    if(rand <= 10) {
                        player.giveExp(1);
                    }

                }

        }
    }
