package top.itsglobally.circlenetwork.lifesteal.lifeSteal.commands;

import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.data.Items;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.interfaces.Global;
import top.itsglobally.circlenetwork.lifesteal.lifeSteal.utils.MessageUtil;
import top.nontage.nontagelib.command.NontageCommand;

public class withdrawhp implements Global, NontageCommand {
    @Override
    public void execute(CommandSender commandSender, String s, String[] strings) {
        if (!(commandSender instanceof Player p)) return;

        int withdrawHPs = 1;
        if (strings.length >= 1) {
            try {
                withdrawHPs = Integer.parseInt(strings[0]);
            } catch (NumberFormatException e) {
                MessageUtil.sendMessage(p, "&cPlease enter a valid number!");
                return;
            }
        }

        withdrawHPs = withdrawHPs * 2;

        if (p.getMaxHealth() - withdrawHPs <= cfg.lowestWithdrawHP) {
            MessageUtil.sendMessage(p, "&cYou don't have enough hp to withdraw!");
            return;
        }

        Inventory inv = p.getInventory();

        if (inv.firstEmpty() == -1) {
            MessageUtil.sendMessage(p, "&cYour inventory is full!");
            return;
        }

        p.setMaxHealth(p.getMaxHealth() - withdrawHPs);
        ItemStack is = Items.HEART.clone();
        is.setAmount(withdrawHPs / 2);
        inv.addItem(is);

        MessageUtil.sendMessage(p, "&dWithdrew successfully!");
    }

}
