package fr.magrigri.buypowerboost;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;


public class ListenerInventory implements Listener {

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){

        InventoryView inv = e.getView();
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();

        if (inv.getTitle().equalsIgnoreCase("Acheter votre powerboost")){
            e.setCancelled(true);
            if(clickedItem.getType() != Material.END_CRYSTAL){
                return;
            }

            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl1")){
                Main.buyPowerBoost(p, 1);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl2")){
                Main.buyPowerBoost(p, 2);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl3")){
                Main.buyPowerBoost(p, 3);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl4")){
                Main.buyPowerBoost(p, 4);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl5")){
                Main.buyPowerBoost(p, 5);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl6")){
                Main.buyPowerBoost(p, 6);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl7")){
                Main.buyPowerBoost(p, 7);
            }
            if(clickedItem.getItemMeta().getDisplayName().equalsIgnoreCase("PowerBoost lvl8")){
                Main.buyPowerBoost(p, 8);
            }
        }

        if(clickedItem == null || clickedItem.getType() == Material.AIR){
            return;
        }

    }

}
