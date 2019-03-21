package com.zmanuel.clickerdetector;

import com.zmanuel.clickerdetector.command.GCommand;
import com.zmanuel.clickerdetector.command.commands.ProfileCommand;
import com.zmanuel.clickerdetector.configuration.StorageType;
import com.zmanuel.clickerdetector.configuration.YamlConfig;
import com.zmanuel.clickerdetector.listeners.ClickListener;
import com.zmanuel.clickerdetector.listeners.JoinListener;
import com.zmanuel.clickerdetector.managers.PlayerManager;
import com.zmanuel.clickerdetector.managers.ProfileManager;
import com.zmanuel.clickerdetector.mongo.Database;
import lombok.Getter;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Map;

public class Main extends JavaPlugin {

    @Getter private static Main instance;

    @Getter private PlayerManager playerManager;
    @Getter private ProfileManager profileManager;

    @Getter private FileConfiguration config;
    @Getter private FileConfiguration profiles;

    public Map<String, GCommand> commands;

    @Getter private StorageType storageType;

    @Override
    public void onEnable() {
        instance = this;
        YamlConfig.create("config", true);
        this.config = YamlConfig.getConfiguration("config");
        this.storageType = StorageType.getType(getConfig().getString("Storage"));
        switch (getStorageType()){
            case YAML:
                YamlConfig.create("profiles", true);
                this.profiles = YamlConfig.getConfiguration("profiles");
                break;
            case MONGODB:
                new Database();
                break;
        }
        this.commands = new HashMap<>();
        this.playerManager = new PlayerManager();
        this.profileManager = new ProfileManager();
        this.profileManager.load(getStorageType());

        new ClickListener();
        new JoinListener();

        new ProfileCommand();
    }
}
