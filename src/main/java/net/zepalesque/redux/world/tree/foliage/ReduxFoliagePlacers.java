package net.zepalesque.redux.world.tree.foliage;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.redux.Redux;

public class ReduxFoliagePlacers {
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACERS = DeferredRegister.create(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Redux.MODID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<SkyrootFoliagePlacer>> SKYROOT_FOLIAGE = FOLIAGE_PLACERS.register("skyroot_foliage", () -> new FoliagePlacerType<>(SkyrootFoliagePlacer.CODEC));

}