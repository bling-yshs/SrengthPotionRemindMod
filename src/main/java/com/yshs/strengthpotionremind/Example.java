package com.yshs.strengthpotionremind;

import com.yshs.strengthpotionremind.remind.Remind;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(modid = Example.MOD_ID, clientSideOnly = true,acceptableRemoteVersions = "*")
public class Example {
    public static final String MOD_ID = "strengthpotionremind";

    @Mod.EventHandler
    public void onPreInit(FMLPreInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new Remind());
    }


}
