package pl.minecash.minecash.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;
import pl.minecash.minecash.Main;

public class MotddEvent implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onServerListPing(ServerListPingEvent event) {
        double randDouble = Math.random();


            event.setMotd(Main.plugin.getConfig().getString("Motd").replace("&", "§"));
        if(randDouble <= 0.5D) {
            event.setMaxPlayers(1);
        } else if(randDouble <= 0.5D) {
            event.setMaxPlayers(150);
        }
    }
}
