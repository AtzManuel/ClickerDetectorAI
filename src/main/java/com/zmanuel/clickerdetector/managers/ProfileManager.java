package com.zmanuel.clickerdetector.managers;

import com.google.common.collect.Lists;
import com.mongodb.Block;
import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.configuration.StorageType;
import com.zmanuel.clickerdetector.mongo.Database;
import com.zmanuel.clickerdetector.profile.Profile;
import lombok.Getter;
import org.bson.Document;

import java.util.List;

public class ProfileManager {

    @Getter private List<Profile> profiles = Lists.newArrayList();

    public Profile byName(String name){
        return profiles.stream().filter(profile -> profile.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public boolean exists(String name){
        return profiles.stream().filter(profile -> profile.getName().equalsIgnoreCase(name)).findFirst().orElse(null) != null;
    }

    public void load(StorageType type){
        switch (type){
            case YAML:
                for (String key : Main.getInstance().getProfiles().getConfigurationSection("").getKeys(false)) {
                    Profile profile = new Profile(key, Main.getInstance().getProfiles().getInt(key + ".minCps"));
                    profile.setValues(Main.getInstance().getProfiles().getIntegerList(key + ".values"));
                }
                break;
            case MONGODB:
                Database.getInstance().getProfiles().find().forEach((Block<? super Document>) (Document document) ->{
                    Profile profile = new Profile(document.getString("name"), document.getInteger("minCps"));
                    profile.setValues((List<Integer>) document.get("values"));
                });
        }
    }

}
