package net.zepalesque.redux.client.render.entity;

import com.aetherteam.aether.client.renderer.entity.SwetRenderer;
import com.aetherteam.aether.entity.monster.Swet;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import net.zepalesque.redux.Redux;

import javax.annotation.Nonnull;

public class DarkSwetRenderer extends SwetRenderer {
    private static final ResourceLocation DARK_TEXTURE = new ResourceLocation(Redux.MODID, "textures/entity/mobs/swet/swet_dark.png");

    public DarkSwetRenderer(EntityRendererProvider.Context context) {
        super(context);
    }

    @Nonnull
    @Override
    public ResourceLocation getTextureLocation(@Nonnull Swet swet) {
        return DARK_TEXTURE;
    }
}