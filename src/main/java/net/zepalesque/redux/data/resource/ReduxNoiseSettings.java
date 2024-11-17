package net.zepalesque.redux.data.resource;

import com.aetherteam.aether.data.resources.registries.AetherNoiseSettings;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraft.world.level.levelgen.NoiseGeneratorSettings;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.zepalesque.redux.data.resource.builder.ReduxNoiseBuilders;

public class ReduxNoiseSettings {

    public static class NoisePack extends ReduxNoiseBuilders {
        public static void bootstrap(BootstapContext<NoiseGeneratorSettings> context) {
            HolderGetter<DensityFunction> densityFunctions = context.lookup(Registries.DENSITY_FUNCTION);
            HolderGetter<NormalNoise.NoiseParameters> noise = context.lookup(Registries.NOISE);
            context.register(AetherNoiseSettings.SKYLANDS, reduxSkylandsNoiseSettings(densityFunctions, noise));
        }
    }
}