package fr.magrigri.buypowerboost;

import net.milkbowl.vault.economy.Economy;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    private Economy econ;
    public static Main main;

    @Override
    public void onEnable(){

        Main.main = this;

        saveDefaultConfig();

        if (!setupEconomy()) {
            this.getLogger().severe("[jqgames] Disabled due to no Vault dependency found!");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }

        System.out.println("[buypowerboost] Enabling buypowerboost");

        getCommand("buypowerboost").setExecutor(new CommandBuypowerboost());

        getServer().getPluginManager().registerEvents(new ListenerInventory(), this);

    }

    @Override
    public void onDisable(){
        System.out.println("[buypowerboost] disabling buypowerboost");
    }


    private boolean setupEconomy() {
        if (Bukkit.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }

        RegisteredServiceProvider<Economy> rsp = getServer().getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    public Economy getEconomy() {
        return econ;
    }

    public static void buyPowerBoost(Player p, int desiredLvl){

        int lvl;
        if(main.getConfig().getString("Storage."+p.getUniqueId().toString()) == null){
            lvl = 0;
        }else{
            lvl = main.getConfig().getInt("Storage."+p.getUniqueId().toString());
        }

        if(lvl+1 != desiredLvl){
            p.sendMessage(Main.main.getConfig().getString("Messages.locked"));
            return;
        }

        Double balance = Main.main.getEconomy().getBalance(p);

        if(balance < main.getConfig().getInt("Cost.lvl" + Integer.toString(desiredLvl))){
            p.sendMessage(Main.main.getConfig().getString("Messages.noMoney"));
            return;
        }

        Main.main.getEconomy().withdrawPlayer(p, Double.valueOf(desiredLvl));
        Main.main.getConfig().set("Storage."+p.getUniqueId().toString(), desiredLvl);
        Main.main.saveConfig();
        p.closeInventory();
        Gui gui = new Gui();
        gui.initializeItems(p);
        gui.openInventory(p);
        p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1f, 1f);
        p.sendMessage(Main.main.getConfig().getString("Messages.done") + Integer.toString(desiredLvl));
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(),
                "f powerboost player " + p.getName() + " " + Integer.toString(Main.main.getConfig().getInt("Boost.lvl" + Integer.toString(desiredLvl))));
    }

}
