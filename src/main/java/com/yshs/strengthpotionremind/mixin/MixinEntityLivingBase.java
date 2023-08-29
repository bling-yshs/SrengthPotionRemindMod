package com.yshs.strengthpotionremind.mixin;

import com.yshs.strengthpotionremind.remind.Remind;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@SideOnly(Side.CLIENT)
@Mixin(EntityLivingBase.class)
public abstract class MixinEntityLivingBase extends Entity {

    @Final
    @Shadow
    private static DataParameter<Integer> POTION_EFFECTS;

    public MixinEntityLivingBase(World worldIn) {
        super(worldIn);
    }

    @Inject(at = @At("RETURN"), method = "updatePotionEffects()V")
    public void onUpdatePotionEffects(CallbackInfo ci) {
        int i = this.dataManager.get(POTION_EFFECTS);
        Remind.remindHashMap.put(this.getName(), i);
    }
}
