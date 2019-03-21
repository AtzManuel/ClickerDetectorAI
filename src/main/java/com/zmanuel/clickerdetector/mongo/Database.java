package com.zmanuel.clickerdetector.mongo;

import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.zmanuel.clickerdetector.Main;
import lombok.Getter;
import org.bson.Document;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Collections;

@Getter
public class Database {

    @Getter private static Database instance;
    private MongoClient client;
    private MongoDatabase database;
    private MongoCollection<Document> profiles;

    public Database() {
        if(instance != null){
            throw new RuntimeException("The mongo database has already been instantiated.");
        }

        instance = this;

        FileConfiguration config = Main.getInstance().getConfig();
        if(config.getBoolean("MONGO.AUTH.ENABLED")){
            final String USERNAME = config.getString("MONGO.AUTH.USERNAME");
            final String PASSWORD = config.getString("MONGO.AUTH.PASSWORD");
            final String DATABASE = config.getString("MONGO.AUTH.DATABASE");
            MongoCredential credential = MongoCredential.createCredential(USERNAME, DATABASE, PASSWORD.toCharArray());
            this.client =  new MongoClient(new ServerAddress(config.getString("MONGO.HOST"), config.getInt("MONGO.PORT")), Collections.singletonList(credential));
        }else{
            this.client = new MongoClient(new ServerAddress(config.getString("MONGO.HOST"), config.getInt("MONGO.PORT")));
        }
        this.database = this.client.getDatabase(config.getString("MONGO.DATABASE"));
        this.profiles = this.database.getCollection("profiles");
    }

}