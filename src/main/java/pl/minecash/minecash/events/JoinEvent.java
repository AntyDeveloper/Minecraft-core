package pl.minecash.minecash.events;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.ChatColor;
public class JoinEvent implements Listener {

    @EventHandler
    public static void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.sendTitle( "§8» §c§lWitamy!", "", 10, 50, 10 );
        if(player.hasPermission("staff.cmd") || player.isOp()) {
            player.getWorld().strikeLightningEffect(player.getLocation());
        }
        for(Player target : Bukkit.getServer().getOnlinePlayers()) {
            event.setJoinMessage("§7[§a+§7] "+ player.getName() +" ");
        }
    }
}
