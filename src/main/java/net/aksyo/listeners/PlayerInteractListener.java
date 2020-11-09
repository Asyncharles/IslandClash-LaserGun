package net.aksyo.listeners;

import net.aksyo.gadgets.LaserGunGadget;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class PlayerInteractListener implements Listener {

    /**
     * Used for debug to test out the Laser Gun (right-click with blaze rod)
     */

    @EventHandler
    public void onInteract(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack itemStack = event.getItem();
        Action action = event.getAction();

        if (itemStack != null) {

            if (itemStack.getType() == Material.BLAZE_ROD && itemStack.hasItemMeta()) {

                if (itemStack.getItemMeta().hasDisplayName() && itemStack.getItemMeta().getDisplayName().equalsIgnoreCase("§6Laser Gun")) {

                    if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                        new LaserGunGadget().useGadget(player);
                        player.sendMessage("§cPiou");
                    }

                }
            }

        }

    }

}
