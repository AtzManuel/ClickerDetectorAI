package com.zmanuel.clickerdetector.command.commands;

import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.command.GCommand;
import com.zmanuel.clickerdetector.player.PlayerData;
import com.zmanuel.clickerdetector.profile.Profile;
import com.zmanuel.clickerdetector.util.StringUtil;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;

public class ProfileCommand extends GCommand {

    public ProfileCommand() {
        super("profile", false, "ac.admin");
    }

    @Override
    public void execute(CommandSender sender, String[] args, String label) {

        Player player = (Player) sender;

        PlayerData data = Main.getInstance().getPlayerManager().byPlayer(player);

        if(args.length != 3 && args.length != 1 && args.length != 2) {
            player.sendMessage(StringUtil.translate("&bClickerDetectorAI Help:"));
            player.sendMessage("");
            player.sendMessage(StringUtil.translate("&b/profile new <Name> <minCps> &f - Create new profile to detect a new clicker"));
            player.sendMessage(StringUtil.translate("&b/profile delete <Name> &f- Delete a profile"));
            player.sendMessage(StringUtil.translate("&b/profile list &f- Show all profiles"));
            return;
        }
        if(args.length == 1){
            if(args[0].equalsIgnoreCase("list")){
                StringBuilder sb = new StringBuilder();
                List<Profile> profiles = Main.getInstance().getProfileManager().getProfiles();

                if(profiles.size() == 0){
                    player.sendMessage(StringUtil.translate("&bNo profiles found."));
                    return;
                }

                for (int i = 0; i < profiles.size(); i++) {

                    Profile profile = profiles.get(i);
                    sb.append(StringUtil.translate("&b" + profile.getName()));
                    if(i != profiles.size()-1){
                       sb.append(StringUtil.translate("&f, "));
                    }
                }
                player.sendMessage(sb.toString());
            }
            return;
        }

        if(args.length == 3) {
            if (args[0].equalsIgnoreCase("new")) {
                if (Main.getInstance().getProfileManager().exists(args[1])) {
                    player.sendMessage(StringUtil.translate("&bThis profile already exists!"));
                    return;
                }
                Profile profile = new Profile(args[1], Integer.parseInt(args[2]));
                player.sendMessage(StringUtil.translate("&bRegistration for profile &f" + profile.getName() + "&b started."));
                data.setRegisteringProfile(profile);
            }
        }
        if(args[0].equalsIgnoreCase("delete")){
            if(!Main.getInstance().getProfileManager().exists(args[1])){
                player.sendMessage(StringUtil.translate("&bProfile not found!"));
                return;
            }
            Profile profile = Main.getInstance().getProfileManager().byName(args[1]);
            profile.delete();
            player.sendMessage(StringUtil.translate("&bProfile deleted!"));
        }
    }
}
