package net.zepalesque.redux.event.listener;

import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.world.biome.modifier.AetherGrassColors;

@Mod.EventBusSubscriber(modid = Redux.MODID)
public class ServerListener {

    @SubscribeEvent
    public static void sendColors(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level().isClientSide() && event.getEntity() instanceof ServerPlayer player) {
            AetherGrassColors.sendToClient(player);
        }
    }
}
