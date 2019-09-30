package fr.magrigri.buypowerboost;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;


import java.util.ArrayList;

public class Gui implements InventoryHolder {

    private Inventory inv;
    private Main main = Main.main;

    public Gui(){
        this.inv = Bukkit.createInventory(this, 9, "Acheter votre powerboost");
    }

    @Override
    public Inventory getInventory() {
        return inv;
    }

    public void initializeItems(Player p) {

        int lvl;
        int currentPowerBoost = 0;
        if(main.getConfig().getString("Storage."+p.getUniqueId().toString()) == null){
            lvl = 0;
        }else{
            lvl = main.getConfig().getInt("Storage."+p.getUniqueId().toString());
        }

        for(int i = 1; i < 9; i++){

            if(lvl == i-1){
                inv.setItem(i, createGuiItem(Material.END_CRYSTAL, main.getConfig().getInt("Boost.lvl" + i),
                        "PowerBoost lvl" + Integer.toString(i),
                        Integer.toString(main.getConfig().getInt("Cost.lvl" + i)) + "$",
                        "+ " + Integer.toString(main.getConfig().getInt("Boost.lvl" + i)) + " de power",
                        "§6Acheter"
                ));
            }
            else if(lvl > i-1){
                inv.setItem(i, createGuiItem(Material.END_CRYSTAL, main.getConfig().getInt("Boost.lvl" + i),
                        "PowerBoost lvl" + Integer.toString(i),
                        Integer.toString(main.getConfig().getInt("Cost.lvl" + i)) + "$",
                        "+ " + Integer.toString(main.getConfig().getInt("Boost.lvl" + i)) + " de power",
                        "§2Débloqué"
                ));
                currentPowerBoost = currentPowerBoost + main.getConfig().getInt("Boost.lvl" + i);
            }
            else{
                inv.setItem(i, createGuiItem(Material.END_CRYSTAL, main.getConfig().getInt("Boost.lvl" + i),
                        "PowerBoost lvl" + Integer.toString(i),
                        Integer.toString(main.getConfig().getInt("Cost.lvl" + i)) + "$",
                        "+ " + Integer.toString(main.getConfig().getInt("Boost.lvl" + i)) + " de power",
                        "§4Verrouillé "
                ));
            }

        }

        ItemStack playerhead = new ItemStack(Material.PLAYER_HEAD, 1);
        SkullMeta playerheadmeta = (SkullMeta) playerhead.getItemMeta();
        playerheadmeta.setOwner(p.getName());
        playerheadmeta.setDisplayName(p.getName());
        ArrayList<String> lores = new ArrayList<String>();
        lores.add("PowerBoost: " + Integer.toString(currentPowerBoost));
        playerheadmeta.setLore(lores);
        playerhead.setItemMeta(playerheadmeta);
        inv.setItem(0, playerhead);


    }

    private ItemStack createGuiItem(Material material, int amount, String name, String...lore) {
        ItemStack item = new ItemStack(material, amount);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(name);
        ArrayList<String> metalore = new ArrayList<String>();

        for(String lorecomments : lore) {

            metalore.add(lorecomments);

        }

        meta.setLore(metalore);
        item.setItemMeta(meta);
        return item;
    }

    // You can open the inventory with this
    public void openInventory(Player p) {
        p.openInventory(inv);
    }
}
