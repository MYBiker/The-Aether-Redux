package net.zepalesque.redux.loot.modifer;


import com.mojang.serialization.Codec;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
import net.zepalesque.redux.Redux;

public class ReduxLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> GLOBAL_LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Redux.MODID);

    public static final DeferredHolder<Codec<? extends IGlobalLootModifier>, Codec<RawOreModifier>> RAW_ORES =
            GLOBAL_LOOT_MODIFIERS.register("raw_ores", () -> RawOreModifier.CODEC);
}