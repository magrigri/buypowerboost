package fr.magrigri.buypowerboost;


import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandBuypowerboost implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, org.bukkit.command.Command command, String s, String[] strings) {

        if(commandSender instanceof Player){
            Gui gui = new Gui();
            gui.initializeItems((Player) commandSender);
            gui.openInventory((Player) commandSender);

        }else{
            commandSender.sendMessage("Vous devez être un joueur.");
        }

        return false;
    }
}
