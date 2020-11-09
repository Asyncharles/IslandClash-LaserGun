package net.aksyo.commands;

import net.aksyo.gadgets.LaserGunGadget;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LaserGunCommand implements CommandExecutor {

    /**
     * Used for debug to give the Laser Gun
     * Gives the player a Blaze Rod named Laser Gun, to shoot lasers {net.aksyo.gadgets.LaserGunGadget}
     *
     * @param sender The Player executing the command
     * @param command The command
     * @param s The label
     * @param strings The arguments
     * @return If the command was executed
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {

        if (sender instanceof Player) {

            Player player = (Player) sender;

            ItemStack laserGunItem = new ItemStack(Material.BLAZE_ROD);
            ItemMeta meta = laserGunItem.getItemMeta();
            meta.setDisplayName("§6Laser Gun");
            laserGunItem.setItemMeta(meta);

            player.getInventory().addItem(laserGunItem);
            new LaserGunGadget().onEnable(player);
            return true;

        }

        return false;
    }
}
