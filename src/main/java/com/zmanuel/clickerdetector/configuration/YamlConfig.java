package com.zmanuel.clickerdetector.configuration;

import com.zmanuel.clickerdetector.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class YamlConfig {

    private static FileConfiguration configuration;
    private static File file;

    public static void create(String filename, boolean saveResource){
        init(filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            Main.getInstance().saveResource(filename + ".yml", saveResource);
        }
    }

    public static void createNewFile(String filename){
        init(filename);
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            try  {
                file.createNewFile();
            } catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public static FileConfiguration getConfiguration(String filename){
        init(filename);
        if (file.exists()) {
            loadConfiguration();
            return configuration;
        } else{
            return null;
        }
    }

    public static void saveConfig(FileConfiguration configuration, String filename){
        init(filename);
        if (file.exists()) {
            saveConfig(configuration);
        }
    }

    private static void init(String filename){
        file = new File(Main.getInstance().getDataFolder(), filename + ".yml");
        configuration = new YamlConfiguration();
    }

    public static void fastModify(String filename, String path, Object object){
        FileConfiguration fileConfiguration = getConfiguration(filename);
        fileConfiguration.set(path,object);
        saveConfig(fileConfiguration,filename);
    }

    /**
     * You have to init configuration && file too
     */
    private static void loadConfiguration(FileConfiguration fileConfiguration){
        try{
            fileConfiguration.load(file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void loadConfiguration(){
        loadConfiguration(configuration);
    }

    /**
     * You have to init and load configuration && file too
     */
    private static void saveConfig(FileConfiguration fileConfiguration){
        try{
            fileConfiguration.save(file);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private static void saveConfig(){
        saveConfig(configuration);
    }
}