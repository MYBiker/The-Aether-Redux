package net.zepalesque.redux.event.listener;

import com.aetherteam.aether.client.AetherSoundEvents;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.event.PlayLevelSoundEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.RegistryObject;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.client.audio.ReduxSoundEvents;
import net.zepalesque.redux.config.ReduxConfig;

@Mod.EventBusSubscriber(modid = Redux.MODID)
public class MobSoundListener {




    @SubscribeEvent
    public static void onPlaySound(PlayLevelSoundEvent event) {
        SoundEvent sound = event.getSound();
        RegistryObject<SoundEvent> newSound = null;

        // TODO: Move to built-in pack!!!
        if (ReduxConfig.COMMON.better_conversion_sounds.get()) {
            if (sound == AetherSoundEvents.ITEM_AMBROSIUM_SHARD.get()) {
                newSound = ReduxSoundEvents.CONVERT_AMBROSIUM;
            }
            if (sound == AetherSoundEvents.ITEM_SWET_BALL_USE.get()) {
                newSound = ReduxSoundEvents.CONVERT_SWET_BALL;
            }
        }
        if (newSound != null) {
            event.setSound(newSound.get());
        }
    }
}
