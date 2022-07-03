package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;


public class SpawnCommand implements CommandExecutor {

    private int TaskID;
    private int count = 5;


    private final Main main;

    public SpawnCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player = (Player) sender;

        String world = Main.plugin.getConfig().getString("Spawn.World");
        Location ab = player.getLocation();
        int x = ab.getBlockX();
        int y = ab.getBlockY();
        int z = ab.getBlockZ();



        Location a = new Location(Bukkit.getWorld(world), x, y, z);
        double b, c, h, m, q;

        b = Main.plugin.getConfig().getDouble("Spawn.X");
        c = Main.plugin.getConfig().getDouble("Spawn.Y");
        h = Main.plugin.getConfig().getDouble("Spawn.Z");
        m = Main.plugin.getConfig().getDouble("Spawn.Yaw");
        q = Main.plugin.getConfig().getDouble("Spawn.Pitch");
        Location loc = new Location(Bukkit.getWorld(world), b, c, h, (float) m, (float) q);
        if(args.length > 0) {
            if (player.hasPermission("adm.cmd") || player.hasPermission("staff.cmd")) {
                Player target = Bukkit.getServer().getPlayer(args[0]);
                target.teleport(loc);
                target.sendMessage("§8» §7Zostales przeteleportowany przez §c" + player.getName() + " §7na spawna.");
            }
        } else if(args.length == 0) {
            if (!player.hasPermission("adm.cmd") || !player.hasPermission("staff.cmd")) {
                player.sendTitle("§7Spawn", "§7Zostaniesz teleportowany za §a" + count + "§7 sekund", 10, 20, 10);
                player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 130, 5));
                player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 130, 5));
                new BukkitRunnable() {

                    @Override
                    public void run() {
                        Location abb = player.getLocation();
                        int xx = abb.getBlockX();
                        int yy = abb.getBlockY();
                        int zz = abb.getBlockZ();
                        Location b = new Location(Bukkit.getWorld(world), xx, yy, zz);
                        if (!a.equals(b)) {
                            cancel();
                            player.removePotionEffect(PotionEffectType.SLOW);
                            player.removePotionEffect(PotionEffectType.BLINDNESS);
                            player.sendMessage("§8» §cAnulowano teleportacje!");
                        } else if (a.equals(b)) {
                            player.teleport(loc);
                            player.sendMessage("§8» §7Zostales przeteleportowany!");
                        }
                    }
                }.runTaskLater(Main.getInstance(), 100);

            } else {
                player.teleport(loc);
                player.sendMessage("§8» §aZostales przeteleportowany!");
            }
        }
        return true;
    }
}
