package pl.minecash.minecash.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.minecash.minecash.commands.ChatCommand;

public class ChatEvent implements Listener {
    @EventHandler(ignoreCancelled = true)
    public void onAsyncPlayerChat(AsyncPlayerChatEvent event) {

            Player player = event.getPlayer();
            if(!player.hasPermission("adm.cmd")) {
                if(ChatCommand.muted == true) {
                    event.setCancelled(true);

                    player.sendMessage("§8» §cChat jest wylaczony!");
                }
            }
    }
}
