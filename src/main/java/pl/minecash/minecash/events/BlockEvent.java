package pl.minecash.minecash.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

import java.util.*;

public class BlockEvent implements Listener {
    static String[] blockedEquals = {
            "/pl",
            "/plugins",
            "/?",
            "/bukkit:?",
            "/help",
            "/lp",
            "/luckperms",
            "/papi help",
            "/placeholderapi",
            "/placeholderapi:papi",
            "/papi ecloud",
            "/papi",
            "bukkit:var",
            "/bukkit:version",
            "/bukkit:about",
            "/bukkit:pl",
            "/bukkit:plugins",
            "/ver",
            "/version",
            "gildia Helf"
    };
   static String[] blockedStart = {
        "bukkit",
        "minecraft:"
};
    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        String msg = event.getMessage();
        String[] args = msg.split(" ");
        Player player = event.getPlayer();
        boolean blocked = false;

            if (Arrays.asList(blockedEquals).contains(args[0].toLowerCase()))
                blocked = true;

            for(String st : blockedStart)
                if(args[0].toLowerCase().startsWith(st))
                event.setCancelled(true);

            if(blocked && !player.hasPermission("staff.cmd")) {
                event.setCancelled(true);
                player.sendTitle("§l§4Mine§l§cCore", "§l§7Author §8» §cAntyDev", 30, 50, 30);

            }
        }

    }

