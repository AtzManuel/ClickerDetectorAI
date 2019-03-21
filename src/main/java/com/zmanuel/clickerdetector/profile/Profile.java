package com.zmanuel.clickerdetector.profile;

import com.google.common.collect.Lists;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.ReplaceOptions;
import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.configuration.StorageType;
import com.zmanuel.clickerdetector.configuration.YamlConfig;
import com.zmanuel.clickerdetector.mongo.Database;
import lombok.Data;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.List;

@Data
public class Profile {

    private String name;
    private int minCps;
    private List<Integer> values;

    public Profile(String name, int minCps) {
        this.name = name;
        this.minCps = minCps;
        this.values = Lists.newArrayList();
        Main.getInstance().getProfileManager().getProfiles().add(this);
    }

    public void addValue(int cps){
        if(values.size() > 10) return;
        if(cps < minCps) return;
        values.add(cps);
    }

    public void save(){
        switch (Main.getInstance().getStorageType()){
            case YAML:
                FileConfiguration config = Main.getInstance().getProfiles();
                config.set(name + ".minCps", minCps);
                config.set(name + ".values", values);
                YamlConfig.saveConfig(config, "profiles");
                break;
            case MONGODB:
                Document document = new Document();
                document.put("name", name);
                document.put("minCps", minCps);
                document.put("values", values);
                Database.getInstance().getProfiles().replaceOne(Filters.eq("name", name), document, new ReplaceOptions().upsert(true));
                break;
        }
    }

    public void delete(){
        Main.getInstance().getProfileManager().getProfiles().remove(this);
        if(Main.getInstance().getStorageType() == StorageType.YAML){
            FileConfiguration config = Main.getInstance().getProfiles();
            config.set(name, null);
            YamlConfig.saveConfig(config, "profiles");
        }
    }

}
