package net.zepalesque.redux.mixin.client.render;

import com.aetherteam.aether.client.renderer.entity.MimicRenderer;
import com.aetherteam.aether.client.renderer.entity.model.MimicModel;
import com.aetherteam.aether.entity.monster.dungeon.Mimic;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.renderer.MultiBufferSource;
import net.zepalesque.redux.config.ReduxConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin({
        MimicRenderer.class,
//        SkyrootMimicRenderer.class
})
public class MimicRendererMixin extends MobRendererMixin<Mimic, MimicModel> {
    @Override
    public void renderMob(Mimic mimic, float entityYaw, float partialTicks, PoseStack poseStack, MultiBufferSource buffer, int packedLight, CallbackInfo ci) {
        this.model.knob.skipDraw = ReduxConfig.CLIENT.mimic_model_upgrade.get().shouldUseModern();
        this.model.leftLeg.skipDraw = ReduxConfig.CLIENT.mimic_model_upgrade.get().shouldUseModern();
        this.model.rightLeg.skipDraw = ReduxConfig.CLIENT.mimic_model_upgrade.get().shouldUseModern();
        this.model.lowerBody.skipDraw = ReduxConfig.CLIENT.mimic_model_upgrade.get().shouldUseModern();
        this.model.upperBody.skipDraw = ReduxConfig.CLIENT.mimic_model_upgrade.get().shouldUseModern();
        super.renderMob(mimic, entityYaw, partialTicks, poseStack, buffer, packedLight, ci);
    }

}
