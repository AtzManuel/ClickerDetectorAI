package com.zmanuel.clickerdetector.player;

import com.google.common.collect.Lists;
import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.profile.Profile;
import lombok.Data;
import org.bukkit.entity.Player;

import java.util.List;

@Data
public class PlayerData {

    private Player player;
    private Long currentCps;
    private int cps;
    private Profile registeringProfile;
    private List<Integer> lastCps;

    public PlayerData(Player player) {
        this.player = player;
        this.currentCps = null;
        this.lastCps = Lists.newArrayList();
        Main.getInstance().getPlayerManager().getPlayers().add(this);
    }

    public void incrementCps(){
        cps++;
    }

}
