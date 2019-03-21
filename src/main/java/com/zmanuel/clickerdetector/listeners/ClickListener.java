package com.zmanuel.clickerdetector.listeners;

import com.zmanuel.clickerdetector.Main;
import com.zmanuel.clickerdetector.player.PlayerData;
import com.zmanuel.clickerdetector.util.StringUtil;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.List;

public class ClickListener implements Listener {

    public ClickListener() {
        Bukkit.getPluginManager().registerEvents(this, Main.getInstance());
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e){

        Player player = e.getPlayer();

        if(e.getAction() == Action.LEFT_CLICK_AIR){

            PlayerData data = Main.getInstance().getPlayerManager().byPlayer(player);

            click(data);
        }

    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e){

        if(e.getDamager() instanceof Player){
            Player player = (Player) e.getDamager();
            PlayerData data = Main.getInstance().getPlayerManager().byPlayer(player);
            click(data);
        }


    }

    private void click(PlayerData data){
        if(data.getCurrentCps() == null || data.getCurrentCps() < System.currentTimeMillis()){
            if(data.getCurrentCps() != null) {
                if (data.getRegisteringProfile() != null) {
                    if (data.getRegisteringProfile().getValues().size() >= 10) {
                        data.getPlayer().sendMessage(StringUtil.translate("&bFinished registration for profile &f" + data.getRegisteringProfile().getName() + " &b."));
                        data.getRegisteringProfile().save();
                        data.setRegisteringProfile(null);
                        return;
                    }else{
                        data.getPlayer().sendMessage(StringUtil.translate("&bRegistration stage: &f" + data.getRegisteringProfile().getValues().size() + "/10"));
                    }
                    data.getRegisteringProfile().addValue(data.getCps());
                }else{
                    if(data.getLastCps().size() > 4){


                        int maxSavedFirstGroup = Math.max(data.getLastCps().get(0), data.getLastCps().get(1));
                        int minSavedFirstGroup = Math.min(data.getLastCps().get(0), data.getLastCps().get(1));
                        int differenceFirstGroup = maxSavedFirstGroup - minSavedFirstGroup;

                        int maxSavedSecondGroup = Math.max(data.getLastCps().get(2), data.getLastCps().get(3));
                        int minSavedSecondGroup = Math.min(data.getLastCps().get(2), data.getLastCps().get(3));
                        int differenceSecondGroup = maxSavedSecondGroup - minSavedSecondGroup;

                        Main.getInstance().getProfileManager().getProfiles().forEach(profile -> {

                            if(minSavedFirstGroup < profile.getMinCps() || maxSavedFirstGroup < profile.getMinCps() || maxSavedSecondGroup < profile.getMinCps() || minSavedSecondGroup < profile.getMinCps()){
                                return;
                            }

                            List<Integer> fromOneToFour = profile.getValues().subList(0, 4);


                            int maxFromOneToFourFirst = Math.max(fromOneToFour.get(0), fromOneToFour.get(1));
                            int minFromOneToFourFirst = Math.min(fromOneToFour.get(0), fromOneToFour.get(1));
                            int differenceFromOneToFourFirst = maxFromOneToFourFirst - minFromOneToFourFirst;

                            int maxFromOneToFourSecond = Math.max(fromOneToFour.get(2), fromOneToFour.get(3));
                            int minFromOneToFourSecond = Math.min(fromOneToFour.get(2), fromOneToFour.get(3));
                            int differenceFromOneToFourSecond = maxFromOneToFourSecond - minFromOneToFourSecond;

                            if(differenceFirstGroup == differenceFromOneToFourFirst && differenceSecondGroup == differenceFromOneToFourSecond){
                                Bukkit.broadcastMessage(StringUtil.translate("&fAutoClickerAI &bdetected &f" + data.getPlayer().getName() + " &busing &f" + profile.getName() + "&b."));
                            }

                            List<Integer> fromTwoToSix = profile.getValues().subList(2, 6);

                            int maxFromTwoToSixFirst = Math.max(fromTwoToSix.get(0), fromTwoToSix.get(1));
                            int minFromTwoToSixFirst = Math.min(fromTwoToSix.get(0), fromTwoToSix.get(1));
                            int differenceFromTwoToSixFirst = maxFromTwoToSixFirst - minFromTwoToSixFirst;

                            int maxFromTwoToSixSecond = Math.max(fromTwoToSix.get(2), fromTwoToSix.get(3));
                            int minFromTwoToSixSecond = Math.min(fromTwoToSix.get(2), fromTwoToSix.get(3));
                            int differenceFromTwoToSixSecond = maxFromTwoToSixSecond - minFromTwoToSixSecond;

                            if(differenceFirstGroup == differenceFromTwoToSixFirst && differenceSecondGroup == differenceFromTwoToSixSecond){
                                Bukkit.broadcastMessage(StringUtil.translate("&fAutoClickerAI &bdetected &f" + data.getPlayer().getName() + " &busing &f" + profile.getName() + "&b."));
                            }

                            List<Integer> fromFourToEight = profile.getValues().subList(4, 8);

                            int maxFromFourToEightFirst = Math.max(fromFourToEight.get(0), fromFourToEight.get(1));
                            int minFromFourToEightFirst = Math.min(fromFourToEight.get(0), fromFourToEight.get(1));
                            int differenceFromFourToEightFirst = maxFromFourToEightFirst - minFromFourToEightFirst;

                            int maxFromFourToEightSecond = Math.max(fromFourToEight.get(2), fromFourToEight.get(3));
                            int minFromFourToEightSecond = Math.min(fromFourToEight.get(2), fromFourToEight.get(3));
                            int differenceFromFourToEightSecond = maxFromFourToEightSecond - minFromFourToEightSecond;


                            if(differenceFirstGroup == differenceFromFourToEightFirst && differenceSecondGroup == differenceFromFourToEightSecond){
                                Bukkit.broadcastMessage(StringUtil.translate("&fAutoClickerAI &bdetected &f" + data.getPlayer().getName() + " &busing &f" + profile.getName() + "&b."));
                            }

                            List<Integer> fromSixToTen = profile.getValues().subList(6, 10);

                            int maxFromSixToTenFirst = Math.max(fromSixToTen.get(0), fromSixToTen.get(1));
                            int minFromSixToTenFirst = Math.min(fromSixToTen.get(0), fromSixToTen.get(1));
                            int differenceFromSixToTenFirst = maxFromSixToTenFirst - minFromSixToTenFirst;

                            int maxFromSixToTenSecond = Math.max(fromSixToTen.get(2), fromSixToTen.get(3));
                            int minFromSixToTenSecond = Math.min(fromSixToTen.get(2), fromSixToTen.get(3));
                            int differenceFromSixToTenSecond = maxFromSixToTenSecond - minFromSixToTenSecond;


                            if(differenceFirstGroup == differenceFromSixToTenFirst && differenceSecondGroup == differenceFromSixToTenSecond){
                                Bukkit.broadcastMessage(StringUtil.translate("&fAutoClickerAI &bdetected &f" + data.getPlayer().getName() + " &busing &f" + profile.getName() + "&b."));
                            }

                                /*
                                se(differenza tra i cps del secondo corrente sono uguali alla differenza dei primi 2 valori
                                quindi controlla se 12 - 8 (4) = 12 - 8  (4)
                                if(Math.max(data.getLastCps().get(0), data.getLastCps().get(1)) - Math.min(data.getLastCps().get(0), data.getLastCps().get(1)) ==
                                        (Math.max(profile.getValues().get(0), profile.getValues().get(1)) - Math.min(profile.getValues().get(0), profile.getValues().get(1))) &&
                                        Math.max(data.getLastCps().get(2), data.getLastCps().get(3)) - Math.min(data.getLastCps().get(2), data.getLastCps().get(3)) ==
                                                (Math.max(profile.getValues().get(2), profile.getValues().get(3)) - Math.min(profile.getValues().get(2), profile.getValues().get(3)))){
                                    Bukkit.broadcastMessage(StringUtil.translate("&fAutoClickerAI &bdetected &f" + player.getName() + " &busing &f" + profile.getName() + "&b."));
                                }*/
                        });
                        data.getLastCps().clear();
                    }
                    data.getLastCps().add(data.getCps());
                }
            }
            data.setCurrentCps(System.currentTimeMillis() + 1000);
            data.setCps(1);
        }else{
            data.incrementCps();
        }
    }

}
