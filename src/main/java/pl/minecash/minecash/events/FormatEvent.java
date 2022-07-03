package pl.minecash.minecash.events;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class FormatEvent implements Listener {
    @EventHandler
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        String formatt = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
        String NewMessage = event.getMessage().replace("&", "§");
         if(player.hasPermission("cmd.staff")) {

            event.setFormat("" + formatt + "§7%s §8» §c"+ NewMessage +"");
        } else if (player.isOp()) {
            event.setFormat("§7[§c§lRoot§7] §7%s §8» §c"+ NewMessage +" ");
        }
         else if(player.hasPermission("gracz.cmd")) {
            event.setFormat("" + formatt + "§7%s §8» §f%s");
        }

    }
}
