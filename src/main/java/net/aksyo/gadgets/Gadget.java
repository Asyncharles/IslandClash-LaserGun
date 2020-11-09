package net.aksyo.gadgets;

import org.bukkit.entity.Player;

public abstract class Gadget {

    /**
     * Called whenever a player pulls out a gadget
     *
     * @param player The player who pulled out the gadget
     */
    public void onEnable(Player player) {}
    /**
     * Called whenever a player puts away a gadget
     *
     * @param player The player who put away the gadget
     */
    public void onDisable(Player player) {}
    /**
     * Called when a player is trying to use a gadget.
     * <p>
     *     This makes another call to {@link #onGadgetUse(Player)} if they can use the gadget.
     * </p>
     *
     * @param player - the player using the gadget.
     *
     * @return {@code true} if the can use the gadget, {@code false} otherwise.
     */
    public boolean useGadget(Player player){
        onGadgetUse(player);
        return true;
    }
    /**
     * Called whenever this gadget is in the players offhand and shift right clicking occurs
     *
     * @param player The player using the gadget
     * @return {@code true} if the {@link org.bukkit.event.player.PlayerInteractEvent} should be cancelled, {@code false} otherwise
     */
    public abstract boolean onGadgetUse(Player player);

}
