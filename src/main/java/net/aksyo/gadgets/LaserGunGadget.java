package net.aksyo.gadgets;

import net.aksyo.GadgetMain;
import org.apache.commons.lang3.tuple.Triple;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import java.util.*;

public class LaserGunGadget extends Gadget {

    /**
     *
     * @param player The player using the gadget
     * @return
     */
    @Override
    public boolean onGadgetUse(final Player player) {

        //Gets the first block that isn't air on the players eyes sight. (maximum of 50 blocks between the players location and the explosion location)
        final Block targetBlock = player.getTargetBlock(new HashSet<>(Arrays.asList(Material.AIR)), 50);

        if (targetBlock.getType() != Material.AIR) {
            //Draws the line to the targeted block, then plays the explosion animation
            drawParticleLine(player.getWorld(), player.getLocation(), targetBlock.getLocation(), 60);
            playExplosionAnimation(targetBlock.getLocation());
        }

        return false;
    }

    /**
     * Utility Method taken from the internet - adapted to the gadget
     * Draws a straight line of particles from a location to another
     * @author DarkBlade12
     *
     * @param world The world where to spawn the particles
     * @param from The players location {@link #onGadgetUse(Player)}
     * @param to The location of targeted block for the explosion
     * @param particles Amount of particles dispatched on the line
     */
    private void drawParticleLine(final World world, Location from, Location to, final int particles) {

        Location location = from.clone();
        Location target = to.clone();
        Vector link = target.toVector().subtract(location.toVector());
        float length = (float) link.length();
        link.normalize();

        float ratio = length / particles;
        Vector v = link.multiply(ratio);
        Location loc = location.clone().subtract(v);
        int step = 0;
        for (int i = 0; i < particles; i++) {
            if (step >= (double) particles) {
                step = 0;
            }
            step++;
            loc.add(v);
            world.spawnParticle(Particle.SNOWBALL, loc, 1);
        }
    }

    /**
     * Puts a barrier instead of the initial Material of the targeted block to simulate a hole
     * The initial Material is put back after 2 seconds (40 ticks)
     *
     * @param explosionLocation The location where the explosion happens
     */
    private void playExplosionAnimation(final Location explosionLocation) {


        Triple<Material, Location, Byte> triple = Triple.of(explosionLocation.getBlock().getType(), explosionLocation, explosionLocation.getBlock().getData());
        explosionLocation.getBlock().setType(Material.BARRIER);

        //BukkitRunnable to put back the initial material.
        new BukkitRunnable() {

            @Override
            public void run() {
                Block block = triple.getMiddle().getBlock();
                block.setType(triple.getLeft());
                //block.setData(triple.getRight()); //Deprecated Method
            }

        }.runTaskLater(GadgetMain.getInstance(), 40);

    }


}
