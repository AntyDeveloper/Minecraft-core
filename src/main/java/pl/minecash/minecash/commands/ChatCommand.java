package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.minecash.minecash.Main;

public class ChatCommand implements CommandExecutor {
    public static boolean muted = false;

        private final Main main;

    public ChatCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            Player player = (Player) sender;
        if (player.hasPermission("adm.cmd")) {
            if (args.length == 1) {
                if(args[0].equalsIgnoreCase("clear")) {
                    for(Player ps : Bukkit.getOnlinePlayers()) {
                        for(int i = 1; i < 101; i++) {
                            ps.sendMessage("  ");
                        }
                        ps.sendTitle("§7Chat", "§6zostal wyczyszczony przez §e" + sender.getName());
                    }
                } else if(args[0].equalsIgnoreCase("on")) {
                    muted = false;

                    for(Player ps : Bukkit.getOnlinePlayers()) {
                        for(int i = 1; i < 101; i++) {
                            ps.sendMessage("  ");
                        }

                        ps.sendTitle("§7Chat", "§azostal wlaczony przez §e" + sender.getName());
                    }
                } else if(args[0].equalsIgnoreCase("off")) {
                    muted = true;
                  for(Player ps : Bukkit.getOnlinePlayers()) {
                      for (int i = 1; i < 101; i++) {
                          ps.sendMessage("  ");
                      }
                      ps.sendTitle("§7Chat", "§czostal wylaczony przez §e" + sender.getName());
                  }
                }
            } else {
                player.sendMessage("§8» §7Uzycie: §f/chat clear/on/off");
            }
        } else {
            player.sendMessage("§8» §cNie masz permisji!");
        }

        return false;
    }

}



