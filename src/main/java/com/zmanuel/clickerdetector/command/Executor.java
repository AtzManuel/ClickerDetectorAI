package com.zmanuel.clickerdetector.command;

import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.util.StringUtil;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Executor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (Main.getInstance().commands.containsKey(label)) {
            GCommand command = Main.getInstance().commands.get(label);
            if (command.isConsole() && sender instanceof Player) {
                //TODO : SEND MESSAGE { EXECUTE FROM CONSOLE } 
            } else if (command.isConsole() && !(sender instanceof Player)) {
                command.execute(sender, args, label);
            }
            else if (!command.isUser() && !sender.hasPermission(command.getPerm())) {
                sender.sendMessage(StringUtil.translate(Main.getInstance().getConfig().getString("messages.no-permission")));
                return true;

            } else if (!command.isUser() && sender.hasPermission(command.getPerm())) {
                command.execute(sender, args, label);
            } else if (command.isUser()) {
                command.execute(sender, args, label);
            }

        }

        return true;
    }

}
