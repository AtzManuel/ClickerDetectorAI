package com.zmanuel.clickerdetector.managers;

import com.google.common.collect.Lists;
import com.zmanuel.clickerdetector.player.PlayerData;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;

public class PlayerManager {

    @Getter private List<PlayerData> players = Lists.newArrayList();

    public PlayerData byPlayer(Player player){
        return players.stream().filter(playerData -> playerData.getPlayer().equals(player)).findFirst().orElse(null);
    }

}
