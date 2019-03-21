package com.zmanuel.clickerdetector.configuration;

import java.util.Arrays;

public enum StorageType {

    YAML, MONGODB;

    public static StorageType getType(String name){
        return Arrays.stream(StorageType.values()).filter(storageType -> storageType.toString().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

}
