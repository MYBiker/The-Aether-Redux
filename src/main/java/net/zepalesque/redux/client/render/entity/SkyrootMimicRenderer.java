package net.zepalesque.redux.client.render.entity;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.client.renderer.AetherModelLayers;
import com.aetherteam.aether.client.renderer.entity.model.MimicModel;
import com.aetherteam.aether.entity.monster.dungeon.Mimic;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.fml.ModList;
import net.zepalesque.redux.Redux;

import java.util.Calendar;

public class SkyrootMimicRenderer extends MobRenderer<Mimic, MimicModel> {
	private static final ResourceLocation TEXTURE = new ResourceLocation("aether_redux", "textures/entity/mobs/skyroot_mimic/skyroot.png");
	private static final ResourceLocation XMAS_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/mimic/christmas.png");
	private static final ResourceLocation LOOTR_TEXTURE = new ResourceLocation(Aether.MODID, "textures/entity/mobs/mimic/lootr.png");

	private boolean isChristmas;
	
	public SkyrootMimicRenderer(EntityRendererProvider.Context renderer) {
		super(renderer, new MimicModel(renderer.bakeLayer(AetherModelLayers.MIMIC)), 1.0F);
		Calendar calendar = Calendar.getInstance();
		if (calendar.get(Calendar.MONTH) == Calendar.DECEMBER && calendar.get(Calendar.DAY_OF_MONTH) >= 24 && calendar.get(Calendar.DAY_OF_MONTH) <= 26) { // Time period when chests display as presents in Vanilla.
			this.isChristmas = true;
		}
	}

	/**
	 * If the Lootr mod is installed or if it is Christmas, Mimics will have a custom texture.
	 * @param Mimic The {@link Mimic} entity.
	 * @return The texture {@link ResourceLocation}.
	 */
	@Override
	public ResourceLocation getTextureLocation(Mimic Mimic) {
		return ModList.get().isLoaded("lootr") ? LOOTR_TEXTURE : this.isChristmas ? XMAS_TEXTURE : TEXTURE;
	}
}