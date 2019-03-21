package com.zmanuel.clickerdetector.util;

import org.bukkit.ChatColor;

public class StringUtil {

    public static String translate(String s){
        return ChatColor.translateAlternateColorCodes('&', s);
    }

}
