package com.yshs.strengthpotionremind.remind;

import com.yshs.strengthpotionremind.message.GUIMessage;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.client.event.ClientChatEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.TickEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

public class Remind {

    private static final Logger LOGGER = LogManager.getLogger();
    public static HashMap<String, Integer> remindHashMap = new HashMap<>();
    private static HashMap<String, Integer> counter = new HashMap<>();

    @SubscribeEvent
    public void listPotions(ClientChatEvent event) {
        if (event.getMessage().contains("/re")) {
            event.setCanceled(true);
            if (remindHashMap.isEmpty()) {
                GUIMessage.printMessage("没有检测到药水效果");
                return;
            }
            for (String key : remindHashMap.keySet()) {
                GUIMessage.printMessage("检测到药水 " + remindHashMap.get(key) + "  " + key);
            }
            remindHashMap.clear();
        }
    }

    @SubscribeEvent
    public void method(TickEvent.PlayerTickEvent event) {

        if (event.phase != TickEvent.Phase.START) {
            return;
        }
        Minecraft mc = Minecraft.getMinecraft();
        EntityPlayer eventPlayerEntity = event.player;
        String playerName = eventPlayerEntity.getName();
        EntityPlayerSP selfEntity = mc.player;
        if (selfEntity == null) {
            return;
        }
        String selfName = selfEntity.getName();
        if (selfName.equals(playerName)) {
            return;
        }
        counter.putIfAbsent(playerName, 0);
        counter.put(playerName, counter.get(playerName) + 1);
        if (counter.get(playerName) < 40) {
            return;
        }
        counter.put(playerName, 0);
        float distance = selfEntity.getDistance(eventPlayerEntity);
        if (distance > 40) {
            return;
        }
        Integer i = remindHashMap.get(playerName);
        if (i == null) {
            return;
        }
        switch (i) {
            case 9718663:
            case 8874356:
            case 9643043:
            case 6044516:
            case 6658919:
            case 9404802:
            case 11550823:
            case 5935415:
                GUIMessage.printMessage(String.format("%s力量哥「%s」，距离你%d格", TextFormatting.RED, playerName, (int) distance));
            case 2445989:
            case 13458603:
            case 83588776:
            case 8171462:
            case 9787561:
            case 2293580:
            case 0:
                break;
            default:
                SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
                LOGGER.info(String.format("%s 未知的药水效果 %d「%s」，距离你%d格", format.format(new Date()), i, playerName, (int) distance));
        }
    }

}
