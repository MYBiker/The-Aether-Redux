package net.zepalesque.redux.event.listener;

import com.aetherteam.aether.entity.AetherEntityTypes;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.zepalesque.redux.config.ReduxConfig;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class ConfigListener {

    // TODO: make this actually work, also TODO 1.19.2: SKyroot Mimic
    @SubscribeEvent
    public static void configChanged(ModConfigEvent.Reloading event)
    {
       if (event.getConfig().getSpec() == ReduxConfig.COMMON_SPEC)
       {
           AetherEntityTypes.MIMIC.get().getDimensions().height = ReduxConfig.COMMON.smaller_mimic_hitbox.get() ? 1.25f : 2F;

       }
    }
}
