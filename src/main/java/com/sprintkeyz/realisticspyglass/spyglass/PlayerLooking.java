package com.sprintkeyz.realisticspyglass.spyglass;

import com.sprintkeyz.realisticspyglass.RealisticSpyglass;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

public class PlayerLooking {

    public void startChecking() {
        new BukkitRunnable() {
            @Override
            public void run() {
                boolean enabled = RealisticSpyglass.getInstance().getConfig().getBoolean("enabled");
                if (enabled) {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        long timeOfDay = player.getWorld().getTime();
                        int listIndex = Sun.getClosestTimeIndex(timeOfDay);
                        checkPlayerDirection(player, listIndex);
                    }
                }
            }
        }.runTaskTimer(RealisticSpyglass.getInstance(), 0L, 2L);
    }

    public void checkPlayerDirection(Player player, int listIndex) {
        boolean debug = RealisticSpyglass.getInstance().getConfig().getBoolean("debug-messages");

        Location playerLoc = player.getLocation();
        float playerYaw = playerLoc.getYaw();
        float playerPitch = playerLoc.getPitch();

        int rangedIndex;

        if (Sun.sunLocations.get(listIndex+1) != null)
            rangedIndex = listIndex + 1;

        else rangedIndex = listIndex;

        // start at index 0! yaw1, yaw2, pitch1, pitch2!
        float yaw1 = Sun.sunLocations.get(listIndex).getValue2();
        float yaw2 = Sun.sunLocations.get(rangedIndex).getValue3();
        float pitch1 = Sun.sunLocations.get(listIndex).getValue0();
        float pitch2 = Sun.sunLocations.get(rangedIndex).getValue1();

        //player.sendMessage("[DEBUG] INDEX " + listIndex + ": Yaw1, yaw2, pitch1, pitch2: " + yaw1 + yaw2 + pitch1 + pitch2);

        boolean yawCheck = false;
        boolean pitchCheck = false;
        //if (yaw1 >= playerYaw && yaw2 <= playerYaw) yawCheck = true;
        if (player.getWorld().getTime() < 5460) {
            if (yaw1 <= playerYaw && yaw2 >= playerYaw) yawCheck = true;
            if (pitch1 >= playerPitch && pitch2 <= playerPitch) pitchCheck = true;
        }

        else {
            if (Math.abs(yaw1) >= playerYaw && Math.abs(yaw2) <= playerYaw) yawCheck = true;
            if (pitch1 >= playerPitch && pitch2 >= playerPitch) pitchCheck = true;
        }
        if (debug) {
            float roundYaw = Math.round(playerYaw * 100)/100f;
            float roundPitch = Math.round(playerPitch * 100)/100f;

            player.sendMessage("[DEBUG] Vals: Yaw " + yawCheck + ", Pitch: " + pitchCheck);

            if (player.getWorld().getTime() < 5460) {
                player.sendMessage("[DEBUG] Yaw: " + Math.round(yaw1 * 100) / 100 + "<=" + roundYaw + "&" + Math.round(yaw2 * 100) / 100 + ">=" + roundYaw);
                player.sendMessage("[DEBUG] Pitch: " + Math.round(pitch1 * 100) / 100 + ">=" + roundPitch + "&" + Math.round(pitch2 * 100) / 100 + "<=" + roundPitch);
            }
            else{
                player.sendMessage("[DEBUG (t>5460)] Yaw: " + Math.abs(Math.round(yaw1 * 100) / 100) + ">=" + roundYaw + "&" + Math.abs(Math.round(yaw2 * 100) / 100) + "<=" + roundYaw);
                player.sendMessage("[DEBUG (t>5460)] Pitch: " + Math.round(pitch1*100)/100 + ">=" + roundPitch + "&" + Math.round(pitch2*100)/100 + ">=" + roundPitch);
            }

        }

        if (yawCheck && pitchCheck) checkUsingSpyglass(player);
    }

    private void checkUsingSpyglass(Player player) {
        boolean debug = RealisticSpyglass.getInstance().getConfig().getBoolean("debug-messages");
        // now, check if they're using a spyglass!
        ItemStack heldItem = player.getItemInUse(); // wait... this MAY work!
        if (heldItem != null) {
            if (heldItem.getType() == Material.SPYGLASS && !player.getWorld().hasStorm()) {
                if (debug) player.sendMessage("[DEBUG] Used spyglass, isnt raining.");
                if (player.getTargetBlockExact(120) == null) blindPlayer(player);
            }
        }
    }

    public void blindPlayer(Player player) {
        boolean debug = RealisticSpyglass.getInstance().getConfig().getBoolean("debug-messages");
        int blindTime = RealisticSpyglass.getInstance().getConfig().getInt("blind-time");
        if (blindTime != Integer.MAX_VALUE) blindTime = blindTime*20;
        player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, blindTime, 0, false, false));
        if (debug) player.sendMessage("[DEBUG] Blinded for " + blindTime + "s!");
    }
}
