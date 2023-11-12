package qiumu.deathinfo;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

public class DeathInfo extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        Bukkit.getServer().getPluginManager().registerEvents(this, this);
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        World world = player.getWorld();
        String playerName = player.getName();
        String location = player.getLocation().getBlockX() + ", " + player.getLocation().getBlockY() + ", " + player.getLocation().getBlockZ();
        String timeOfDeath = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

        ItemStack deathInfo = new ItemStack(Material.PAPER);
        ItemMeta deathInfoMeta = deathInfo.getItemMeta();
        deathInfoMeta.setDisplayName(ChatColor.GOLD + "死亡信息");
        deathInfoMeta.setLore(Arrays.asList(
                ChatColor.GRAY + "玩家名称：" + playerName,
                ChatColor.GRAY + "死亡时间：" + timeOfDeath,
                ChatColor.GRAY + "死亡坐标：" + location));
        deathInfo.setItemMeta(deathInfoMeta);

        Bukkit.getServer().getScheduler().runTaskLater(this, new Runnable() {
            @Override
            public void run() {
                player.getInventory().addItem(deathInfo);
            }
        }, 1L);
    }
}