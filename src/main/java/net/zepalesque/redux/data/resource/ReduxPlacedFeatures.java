package net.zepalesque.redux.data.resource;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.AetherConfig;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.resources.builders.AetherPlacedFeatureBuilders;
import com.aetherteam.aether.data.resources.registries.AetherConfiguredFeatures;
import com.aetherteam.aether.data.resources.registries.AetherPlacedFeatures;
import com.aetherteam.aether.world.placementmodifier.ConfigFilter;
import com.aetherteam.aether.world.placementmodifier.DungeonBlacklistFilter;
import com.aetherteam.aether.world.placementmodifier.ImprovedLayerPlacementModifier;
import com.aetherteam.nitrogen.data.resources.builders.NitrogenPlacedFeatureBuilders;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.*;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.heightproviders.UniformHeight;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.RegistryObject;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.api.condition.Conditions;
import net.zepalesque.redux.block.ReduxBlocks;
import net.zepalesque.redux.world.placement.ConditionFilter;

import java.util.List;

import static net.zepalesque.redux.data.resource.Folders.ORE;

public class ReduxPlacedFeatures {

    public static final DungeonBlacklistFilter DUNGEON_BLACKLIST = new DungeonBlacklistFilter();
    public static final NoiseThresholdCountPlacement NOISE_THRESHOLD = NoiseThresholdCountPlacement.of(-0.8D, 5, 10);

    public static final ResourceKey<PlacedFeature> AEVELIUM_GRASSES_PATCH = copyKey(ReduxConfiguredFeatures.AEVELIUM_GRASSES_PATCH);
    public static final ResourceKey<PlacedFeature> DEEP_GRASS_PATCH = copyKey(ReduxConfiguredFeatures.DEEP_GRASS_PATCH);
    public static final ResourceKey<PlacedFeature> SUGARGRASS_PATCH = copyKey(ReduxConfiguredFeatures.SUGARGRASS_PATCH);
    public static final ResourceKey<PlacedFeature> AURUM_PATCH = copyKey(ReduxConfiguredFeatures.AURUM_PATCH);
    public static final ResourceKey<PlacedFeature> EDELWEISS_PATCH = copyKey(ReduxConfiguredFeatures.EDELWEISS_PATCH);
    public static final ResourceKey<PlacedFeature> ZYATRIX_PATCH = copyKey(ReduxConfiguredFeatures.ZYATRIX_PATCH);
    public static final ResourceKey<PlacedFeature> VANILLA_PATCH = copyKey(ReduxConfiguredFeatures.VANILLA_PATCH);
    public static final ResourceKey<PlacedFeature> SHRUBLANDS_PURPLE_PATCH = createKey(Folders.PATCH + "shrublands_purple_patch");
    public static final ResourceKey<PlacedFeature> SHRUBLANDS_WHITE_PATCH = createKey(Folders.PATCH + "shrublands_white_patch");
    public static final ResourceKey<PlacedFeature> ZANBERRY_BUSH_PATCH = copyKey(ReduxConfiguredFeatures.ZANBERRY_BUSH_PATCH);
    public static final ResourceKey<PlacedFeature> BLIGHTMOSS_SPARSE_VEGETATION = createKey(Folders.CAVE + "blightmoss_sparse_vegetation");
    public static final ResourceKey<PlacedFeature> BLIGHTMOSS_VEGETATION = createKey(Folders.CAVE + "blightmoss_vegetation");
    public static final ResourceKey<PlacedFeature> FUNGAL_SPARSE_VEGETATION = createKey(Folders.CAVE + "fungal_sparse_vegetation");
    public static final ResourceKey<PlacedFeature> FUNGAL_VEGETATION = createKey(Folders.CAVE + "fungal_vegetation");
    public static final ResourceKey<PlacedFeature> BLIGHTSHADE_PATCH = copyKey(ReduxConfiguredFeatures.BLIGHTSHADE_PATCH);
    public static final ResourceKey<PlacedFeature> CORRUPTED_VINES_PATCH = copyKey(ReduxConfiguredFeatures.CORRUPTED_VINES_PATCH);
    public static final ResourceKey<PlacedFeature> BLIGHT_ROCK = copyKey(ReduxConfiguredFeatures.BLIGHT_ROCK);
    public static final ResourceKey<PlacedFeature> GLIMMERSTOOL_ROCK = copyKey(ReduxConfiguredFeatures.GLIMMERSTOOL_ROCK);
    public static final ResourceKey<PlacedFeature> DENSE_GRASS = createKey(Folders.PATCH + "dense_grass");
    public static final ResourceKey<PlacedFeature> GLIMMERSTOOL_PATCH = copyKey(ReduxConfiguredFeatures.GLIMMERSTOOL_PATCH);
    public static final ResourceKey<PlacedFeature> BLIGHT_TREES = copyKey(ReduxConfiguredFeatures.BLIGHT_TREES);
    public static final ResourceKey<PlacedFeature> DEEP_TREES = copyKey(ReduxConfiguredFeatures.DEEP_TREES);
    public static final ResourceKey<PlacedFeature> CANDY_TREES = copyKey(ReduxConfiguredFeatures.CANDY_TREES);
    public static final ResourceKey<PlacedFeature> CLOUDCAP_MUSHLING_PATCH = copyKey(ReduxConfiguredFeatures.CLOUDCAP_MUSHLING_PATCH);
    public static final ResourceKey<PlacedFeature> DAGGERBLOOM_PATCH = copyKey(ReduxConfiguredFeatures.DAGGERBLOOM_PATCH);
    public static final ResourceKey<PlacedFeature> PINK_COTTON_CANDY_PATCH = copyKey(ReduxConfiguredFeatures.PINK_COTTON_CANDY_PATCH);
    public static final ResourceKey<PlacedFeature> BLUE_COTTON_CANDY_PATCH = copyKey(ReduxConfiguredFeatures.BLUE_COTTON_CANDY_PATCH);
    public static final ResourceKey<PlacedFeature> PEPPERMINT_BARKLING_PATCH = copyKey(ReduxConfiguredFeatures.PEPPERMINT_BARKLING_PATCH);
    public static final ResourceKey<PlacedFeature> ROOTROSE_PATCH = copyKey(ReduxConfiguredFeatures.ROOTROSE_PATCH);
    public static final ResourceKey<PlacedFeature> THERATIP_PATCH = copyKey(ReduxConfiguredFeatures.THERATIP_PATCH);
    public static final ResourceKey<PlacedFeature> SPLITFERN_PATCH = copyKey(ReduxConfiguredFeatures.SPLITFERN_PATCH);
    public static final ResourceKey<PlacedFeature> AEROGEL_ORE = copyKey(ReduxConfiguredFeatures.AEROGEL_ORE);
    public static final ResourceKey<PlacedFeature> SPARSE_AEROGEL_ORE = createKey(Folders.ORE + "sparse_aerogel_ore");
    public static final ResourceKey<PlacedFeature> SPARSE_BLUE_AERCLOUD = createKey(Folders.MISC + "sparse_blue_aercloud");
    public static final ResourceKey<PlacedFeature> DENSE_BLUE_AERCLOUD = createKey(Folders.MISC + "dense_blue_aercloud");
    public static final ResourceKey<PlacedFeature> BLIGHTED_AERCLOUD = createKey(Folders.MISC + "blighted_aercloud");
    public static final ResourceKey<PlacedFeature> RAINBOW_AERCLOUD = createKey(Folders.MISC + "rainbow_aercloud");
    public static final ResourceKey<PlacedFeature> SPARSE_ZANITE_ORE = createKey(Folders.ORE + "sparse_zanite_ore");
    public static final ResourceKey<PlacedFeature> SPARSE_AMBROSIUM_ORE = createKey(Folders.ORE + "sparse_ambrosium_ore");
    public static final ResourceKey<PlacedFeature> DENSE_ZANITE_ORE = createKey(Folders.ORE + "dense_zanite_ore");
    public static final ResourceKey<PlacedFeature> DENSE_AMBROSIUM_ORE = createKey(Folders.ORE + "dense_ambrosium_ore");
    public static final ResourceKey<PlacedFeature> LARGE_ICESTONE_CHUNK = copyKey(ReduxConfiguredFeatures.LARGE_ICESTONE_CHUNK);
    public static final ResourceKey<PlacedFeature> FROSTED_PURPLE_FLOWER_PATCH = copyKey(ReduxConfiguredFeatures.FROSTED_PURPLE_FLOWER_PATCH);
    public static final ResourceKey<PlacedFeature> FROSTED_TREES = copyKey(ReduxConfiguredFeatures.FROSTED_TREES);
    public static final ResourceKey<PlacedFeature> GLACIAL_TREES = copyKey(ReduxConfiguredFeatures.GLACIAL_TREES);
    public static final ResourceKey<PlacedFeature> GILDED_HOLYSTONE_ORE = copyKey(ReduxConfiguredFeatures.GILDED_HOLYSTONE_ORE);
    public static final ResourceKey<PlacedFeature> CRYSTAL_GOLD_ORE = copyKey(ReduxConfiguredFeatures.CRYSTAL_GOLD_ORE);
    public static final ResourceKey<PlacedFeature> BLIGHTMOSS_HOLYSTONE_ORE = copyKey(ReduxConfiguredFeatures.BLIGHTMOSS_HOLYSTONE_ORE);
    public static final ResourceKey<PlacedFeature> GILDED_ROCK  = copyKey(ReduxConfiguredFeatures.GILDED_ROCK);
    public static final ResourceKey<PlacedFeature> GILDED_WHITE_FLOWER_PATCH = copyKey(ReduxConfiguredFeatures.GILDED_WHITE_FLOWER_PATCH);
    public static final ResourceKey<PlacedFeature> GLOWSPROUTS_PATCH = copyKey(ReduxConfiguredFeatures.GLOWSPROUTS_PATCH);
    public static final ResourceKey<PlacedFeature> GOLDEN_CLOVER_PATCH = copyKey(ReduxConfiguredFeatures.GOLDEN_CLOVER_PATCH);
    public static final ResourceKey<PlacedFeature> GOLDEN_HEIGHTS_GILDED_HOLYSTONE_ORE = createKey(ORE + "golden_heights_" + name(ReduxBlocks.GILDED_HOLYSTONE) + "_ore");
    public static final ResourceKey<PlacedFeature> GROVE_TREES = copyKey(ReduxConfiguredFeatures.GROVE_TREES);
    public static final ResourceKey<PlacedFeature> GRASSLAND_TREES = copyKey(ReduxConfiguredFeatures.GRASSLAND_TREES);
    public static final ResourceKey<PlacedFeature> GROVE_TREES_ALT = copyKey(ReduxConfiguredFeatures.GROVE_TREES_ALT);
    public static final ResourceKey<PlacedFeature> GRASSLAND_TREES_ALT = copyKey(ReduxConfiguredFeatures.GRASSLAND_TREES_ALT);
    public static final ResourceKey<PlacedFeature> SKYFIELDS_ROCK = copyKey(ReduxConfiguredFeatures.SKYFIELDS_ROCK);
    public static final ResourceKey<PlacedFeature> CANDY_ROCK = copyKey(ReduxConfiguredFeatures.CANDY_ROCK);
    public static final ResourceKey<PlacedFeature> SHRUBLANDS_ROCK  = copyKey(ReduxConfiguredFeatures.SHRUBLANDS_ROCK);
    public static final ResourceKey<PlacedFeature> SKYSPROUTS_PATCH = copyKey(ReduxConfiguredFeatures.SKYSPROUTS_PATCH);
    public static final ResourceKey<PlacedFeature> SKYFIELDS_TREES = copyKey(ReduxConfiguredFeatures.SKYFIELDS_TREES);
    public static final ResourceKey<PlacedFeature> CLASSIC_SKYFIELDS_TREES = copyKey(ReduxConfiguredFeatures.CLASSIC_SKYFIELDS_TREES);
    public static final ResourceKey<PlacedFeature> CRYSTALLIUM_TREES = copyKey(ReduxConfiguredFeatures.CRYSTALLIUM_TREES);
    public static final ResourceKey<PlacedFeature> SHRUBLANDS_TREES = copyKey(ReduxConfiguredFeatures.SHRUBLANDS_TREES);
    public static final ResourceKey<PlacedFeature> SHIMMERING_TREES = createKey(Folders.TREE + "shimmering_trees");
    public static final ResourceKey<PlacedFeature> IRIDIA_PATCH  = copyKey(ReduxConfiguredFeatures.IRIDIA_PATCH);
    public static final ResourceKey<PlacedFeature> XAELIA_PATCH  = copyKey(ReduxConfiguredFeatures.XAELIA_PATCH);
    public static final ResourceKey<PlacedFeature> LARGE_MUSHROOMS = copyKey(ReduxConfiguredFeatures.LARGE_MUSHROOMS);
    public static final ResourceKey<PlacedFeature> LUMINA_PATCH = copyKey(ReduxConfiguredFeatures.LUMINA_PATCH);
    public static final ResourceKey<PlacedFeature> FLAWLESS_BLOOM_PATCH = copyKey(ReduxConfiguredFeatures.FLAWLESS_BLOOM_PATCH);
    public static final ResourceKey<PlacedFeature> LIGHTROOTS = copyKey(ReduxConfiguredFeatures.LIGHTROOTS);
    public static final ResourceKey<PlacedFeature> MOSSY_HOLYSTONE_ORE  = createKey(ORE + name(AetherBlocks.MOSSY_HOLYSTONE) + "_ore");
    public static final ResourceKey<PlacedFeature> MOSSY_ROCK  = copyKey(ReduxConfiguredFeatures.MOSSY_ROCK);
    public static final ResourceKey<PlacedFeature> ICESTONE_ROCK  = copyKey(ReduxConfiguredFeatures.ICESTONE_ROCK);
    public static final ResourceKey<PlacedFeature> WYNDSPROUTS_PATCH = copyKey(ReduxConfiguredFeatures.WYNDSPROUTS_PATCH);
    public static final ResourceKey<PlacedFeature> GENESIS_WYNDSPROUTS_PATCH = copyKey(ReduxConfiguredFeatures.GENESIS_WYNDSPROUTS_PATCH);
    public static final ResourceKey<PlacedFeature> GENESIS_SKYSPROUTS_PATCH = copyKey(ReduxConfiguredFeatures.GENESIS_SKYSPROUTS_PATCH);
    public static final ResourceKey<PlacedFeature> SPIROLYCTIL_PATCH  = copyKey(ReduxConfiguredFeatures.SPIROLYCTIL_PATCH);
    public static final ResourceKey<PlacedFeature> JELLYSHROOM_PATCH = copyKey(ReduxConfiguredFeatures.JELLYSHROOM_PATCH);
    public static final ResourceKey<PlacedFeature> DECREASED_JELLYSHROOM_PATCH = createKey(Folders.PATCH + "decreased_jellyshroom_patch");
    public static final ResourceKey<PlacedFeature> SURFACE_RULE_WATER_LAKE = copyKey(ReduxConfiguredFeatures.SURFACE_RULE_WATER_LAKE);
    public static final ResourceKey<PlacedFeature> BLIGHT_LAKE = copyKey(ReduxConfiguredFeatures.BLIGHT_LAKE);
    public static final ResourceKey<PlacedFeature> BLIGHT_SPRING = copyKey(ReduxConfiguredFeatures.BLIGHT_SPRING);
    public static final ResourceKey<PlacedFeature> SYRUP_LAKE = copyKey(ReduxConfiguredFeatures.SYRUP_LAKE);
    public static final ResourceKey<PlacedFeature> SYRUP_SPRING = copyKey(ReduxConfiguredFeatures.SYRUP_SPRING);
    public static final ResourceKey<PlacedFeature> OASIS_LAKE = createKey(Folders.SURFACE + "oasis_lake");
    public static final ResourceKey<PlacedFeature> VERIDIUM_ORE = copyKey(ReduxConfiguredFeatures.VERIDIUM_ORE);
    public static final ResourceKey<PlacedFeature> DIVINITE_ORE = copyKey(ReduxConfiguredFeatures.DIVINITE_ORE);
    public static final ResourceKey<PlacedFeature> SENTRITE_ORE = copyKey(ReduxConfiguredFeatures.SENTRITE_ORE);
    public static final ResourceKey<PlacedFeature> DIVINITE_ORE_INCREASED = createKey(Folders.ORE + "divinite_ore_increased");
    public static final ResourceKey<PlacedFeature> HOLYSILT_DISK = copyKey(ReduxConfiguredFeatures.HOLYSILT_DISK);
    public static final ResourceKey<PlacedFeature> AEROGEL_DISK = copyKey(ReduxConfiguredFeatures.AEROGEL_DISK);
    public static final ResourceKey<PlacedFeature> CLOUD_LAYER = copyKey(ReduxConfiguredFeatures.CLOUD_LAYER);

    public static final ResourceKey<PlacedFeature> AETHER_SNOW_LAYER = copyKey(ReduxConfiguredFeatures.AETHER_SNOW_LAYER);

    public static final ResourceKey<PlacedFeature> ANCIENT_ENCHANTED_GRASS = copyKey(ReduxConfiguredFeatures.ANCIENT_ENCHANTED_GRASS);

    public static final ResourceKey<PlacedFeature> BONEMEAL_OVERRIDE = AetherPlacedFeatures.AETHER_GRASS_BONEMEAL;

    public static final ResourceKey<PlacedFeature> SUGARGRASS_BONEMEAL = createKey(Folders.MISC + "sugargrass_bonemeal");


    public static void bootstrap(BootstapContext<PlacedFeature> context) {

        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, AEVELIUM_GRASSES_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AEVELIUM_GRASSES_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                new ConfigFilter(AetherConfig.SERVER.generate_tall_grass),
                BiomeFilter.biome()
        );
        register(context, DEEP_GRASS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.DEEP_GRASS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                new ConfigFilter(AetherConfig.SERVER.generate_tall_grass),
                BiomeFilter.biome()
        );
        register(context, SUGARGRASS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SUGARGRASS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                new ConfigFilter(AetherConfig.SERVER.generate_tall_grass),
                BiomeFilter.biome()
        );
        register(context, ANCIENT_ENCHANTED_GRASS, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.ANCIENT_ENCHANTED_GRASS),
                NoiseThresholdCountPlacement.of(-0.8, 10, 20),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                new ConfigFilter(AetherConfig.SERVER.generate_tall_grass),
                ConditionFilter.whenTrue(Conditions.ANCIENT),
                BiomeFilter.biome()
        );
        register(context, CLOUD_LAYER, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CLOUD_LAYER));
        register(context, AETHER_SNOW_LAYER, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AETHER_SNOW_LAYER)
        );


        register(context, AURUM_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AURUM_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, BiasedToBottomInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(12),
                BiomeFilter.biome());

        register(context, EDELWEISS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.EDELWEISS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, BiasedToBottomInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(10),
                BiomeFilter.biome());

        register(context, SPARSE_BLUE_AERCLOUD,
                configuredFeatures.getOrThrow(AetherConfiguredFeatures.BLUE_AERCLOUD_CONFIGURATION),
                AetherPlacedFeatureBuilders.aercloudPlacement(32, 64, 48));

        register(context, DENSE_BLUE_AERCLOUD,
                configuredFeatures.getOrThrow(AetherConfiguredFeatures.BLUE_AERCLOUD_CONFIGURATION),
                AetherPlacedFeatureBuilders.aercloudPlacement(32, 64, 14));


        register(context, BLIGHTED_AERCLOUD,
                configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHTED_AERCLOUD),
                AetherPlacedFeatureBuilders.aercloudPlacement(32, 64, 24));

        register(context, RAINBOW_AERCLOUD,
                configuredFeatures.getOrThrow(ReduxConfiguredFeatures.RAINBOW_AERCLOUD),
                AetherPlacedFeatureBuilders.aercloudPlacement(32, 64, 64));


        register(context, SPARSE_AMBROSIUM_ORE, configuredFeatures.getOrThrow(AetherConfiguredFeatures.ORE_AMBROSIUM_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(10, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));

        register(context, SPARSE_ZANITE_ORE, configuredFeatures.getOrThrow(AetherConfiguredFeatures.ORE_ZANITE_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(7, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(75))));

        register(context, DENSE_AMBROSIUM_ORE, configuredFeatures.getOrThrow(AetherConfiguredFeatures.ORE_AMBROSIUM_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(30, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(128))));

        register(context, DENSE_ZANITE_ORE, configuredFeatures.getOrThrow(AetherConfiguredFeatures.ORE_ZANITE_CONFIGURATION),
                NitrogenPlacedFeatureBuilders.commonOrePlacement(21, HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(75))));


        register(context, ZYATRIX_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.ZYATRIX_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, BiasedToBottomInt.of(0, 3), 4),
                RarityFilter.onAverageOnceEvery(13),
                BiomeFilter.biome());


        register(context, VANILLA_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.VANILLA_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, BiasedToBottomInt.of(0, 3), 4),
                RarityFilter.onAverageOnceEvery(13),
                BiomeFilter.biome());


        register(context, SHRUBLANDS_WHITE_PATCH, configuredFeatures.getOrThrow(AetherConfiguredFeatures.WHITE_FLOWER_PATCH_CONFIGURATION),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(15),
                BiomeFilter.biome());

        register(context, SHRUBLANDS_PURPLE_PATCH, configuredFeatures.getOrThrow(AetherConfiguredFeatures.PURPLE_FLOWER_PATCH_CONFIGURATION),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(25),
                BiomeFilter.biome());


        register(context, ZANBERRY_BUSH_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.ZANBERRY_BUSH_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, BiasedToBottomInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(9),
                BiomeFilter.biome());

        register(context, GILDED_WHITE_FLOWER_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GILDED_WHITE_FLOWER_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(12),
                BiomeFilter.biome()
        );

        register(context, BLIGHTMOSS_SPARSE_VEGETATION, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHTMOSS_PATCH),
                CountPlacement.of(10),
                RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(120))),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.CAVE_AIR), BlockPredicate.solid(), 12),
                RandomOffsetPlacement.of(ConstantInt.ZERO, ConstantInt.of(1)),
                BiomeFilter.biome());

        register(context, BLIGHTMOSS_VEGETATION, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHTMOSS_PATCH),
                CountPlacement.of(250),
                RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(120))),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.CAVE_AIR), BlockPredicate.solid(), 12),
                RandomOffsetPlacement.of(ConstantInt.ZERO, ConstantInt.of(4)),
                BiomeFilter.biome());

        register(context, FUNGAL_SPARSE_VEGETATION, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.FUNGAL_PATCH),
                CountPlacement.of(10),
                RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(120))),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.CAVE_AIR), BlockPredicate.solid(), 14),
                RandomOffsetPlacement.of(ConstantInt.ZERO, ConstantInt.of(1)),
                BiomeFilter.biome());

        register(context, FUNGAL_VEGETATION, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.FUNGAL_PATCH),
                CountPlacement.of(225),
                RarityFilter.onAverageOnceEvery(5),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(120))),
                EnvironmentScanPlacement.scanningFor(Direction.DOWN, BlockPredicate.matchesBlocks(Blocks.CAVE_AIR), BlockPredicate.solid(), 14),
                RandomOffsetPlacement.of(ConstantInt.ZERO, ConstantInt.of(4)),
                BiomeFilter.biome());

        register(context, BLIGHT_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHT_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 5)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );
        register(context, GLIMMERSTOOL_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GLIMMERSTOOL_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 5)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        register(context, GLIMMERSTOOL_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GLIMMERSTOOL_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(1, 3), 4),
                RarityFilter.onAverageOnceEvery(28),
                BiomeFilter.biome()
        );

        register(context, DENSE_GRASS, configuredFeatures.getOrThrow(AetherConfiguredFeatures.GRASS_PATCH_CONFIGURATION),
                NoiseThresholdCountPlacement.of(-0.8, 5, 10), ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 7),
                BiomeFilter.biome(), new ConfigFilter(AetherConfig.SERVER.generate_tall_grass));



        register(context, BLIGHTSHADE_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHTSHADE_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(1, 3), 4),
                RarityFilter.onAverageOnceEvery(16),
                BiomeFilter.biome()
        );

        register(context, CORRUPTED_VINES_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CORRUPTED_VINES_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, ConstantInt.of(1), 4),
                RarityFilter.onAverageOnceEvery(21),
                BiomeFilter.biome()
        );



        register(context, LARGE_MUSHROOMS, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.LARGE_MUSHROOMS),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(10), 9)
                        .add(ConstantInt.of(14), 1)
                        .build())),
                SurfaceWaterDepthFilter.forMaxDepth(0),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST
        );

        register(context, CLOUDCAP_MUSHLING_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CLOUDCAP_MUSHLING_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(12),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.CLOUDCAP_MUSHLING.get()));



        register(context, DAGGERBLOOM_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.DAGGERBLOOM_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(10),
                BiomeFilter.biome()
        );
        register(context, PINK_COTTON_CANDY_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.PINK_COTTON_CANDY_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(10),
                BiomeFilter.biome()
        );
        register(context, BLUE_COTTON_CANDY_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLUE_COTTON_CANDY_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(10),
                BiomeFilter.biome()
        );
        register(context, PEPPERMINT_BARKLING_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.PEPPERMINT_BARKLING_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(14),
                BiomeFilter.biome()
        );

        register(context, ROOTROSE_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.ROOTROSE_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(10),
                BiomeFilter.biome()
        );
        register(context, THERATIP_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.THERATIP_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(5),
                BiomeFilter.biome()
        );

        register(context, SPLITFERN_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SPLITFERN_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 3), 4),
                RarityFilter.onAverageOnceEvery(7),
                BiomeFilter.biome());

        register(context, FROSTED_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.FROSTED_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(24), 9)
                        .add(ConstantInt.of(16), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GLACIA_SAPLING.get())
        );

        register(context, GLACIAL_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GLACIAL_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(8), 9)
                        .add(ConstantInt.of(6), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GLACIA_SAPLING.get())
        );


        register(context, AEROGEL_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AEROGEL_ORE),
                CountPlacement.of(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                BiomeFilter.biome()
        );
        register(context, SPARSE_AEROGEL_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AEROGEL_ORE),
                RarityFilter.onAverageOnceEvery(2),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                BiomeFilter.biome()
        );

        register(context, LARGE_ICESTONE_CHUNK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AEROGEL_ORE),
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                BiomeFilter.biome()
        );

        register(context, FROSTED_PURPLE_FLOWER_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.FROSTED_PURPLE_FLOWER_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(13),
                BiomeFilter.biome()
        );

        register(context, GILDED_HOLYSTONE_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GILDED_HOLYSTONE_ORE),
                CountPlacement.of(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                ConditionFilter.whenTrue(Conditions.MOSSY_ORE),
                BiomeFilter.biome()
        );
        register(context, BLIGHTMOSS_HOLYSTONE_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHTMOSS_HOLYSTONE_ORE),
                CountPlacement.of(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                ConditionFilter.whenTrue(Conditions.MOSSY_ORE),
                BiomeFilter.biome()
        );

        register(context, SHIMMERING_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CRYSTAL_LEAF_TREE),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(12), 9)
                        .add(ConstantInt.of(7), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GILDED_OAK_SAPLING.get())
        );
        register(context, GROVE_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GROVE_TREES),
                ConditionFilter.whenFalse(Conditions.ALT_GILDED),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(12), 9)
                        .add(ConstantInt.of(7), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GILDED_OAK_SAPLING.get())
        );

        register(context, GROVE_TREES_ALT, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GROVE_TREES_ALT),
                ConditionFilter.whenTrue(Conditions.ALT_GILDED),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(12), 9)
                        .add(ConstantInt.of(7), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GILDED_OAK_SAPLING.get())
        );

        register(context, GRASSLAND_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GRASSLAND_TREES),
                ConditionFilter.whenFalse(Conditions.ALT_GILDED),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(2), 9)
                        .add(ConstantInt.of(3), 1)
                        .add(ConstantInt.of(0), 4)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GILDED_OAK_SAPLING.get())
        );
        register(context, GRASSLAND_TREES_ALT, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GRASSLAND_TREES_ALT),
                ConditionFilter.whenTrue(Conditions.ALT_GILDED),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(2), 9)
                        .add(ConstantInt.of(3), 1)
                        .add(ConstantInt.of(0), 4)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST,
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GILDED_OAK_SAPLING.get())
        );

        register(context, GOLDEN_HEIGHTS_GILDED_HOLYSTONE_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GILDED_HOLYSTONE_ORE),
                CountPlacement.of(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                ConditionFilter.whenTrue(Conditions.MOSSY_ORE),
                BiomeFilter.biome()
        );


        register(context, GILDED_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GILDED_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 5)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );



        register(context, GLOWSPROUTS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GLOWSPROUTS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(1, 2), 4),
                RarityFilter.onAverageOnceEvery(4),
                BiomeFilter.biome()
        );

        register(context, GOLDEN_CLOVER_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GOLDEN_CLOVER_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(4),
                BiomeFilter.biome()
        );


        register(context, SKYFIELDS_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SKYFIELDS_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 2)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        register(context, CANDY_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CANDY_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 2)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );

        register(context, SHRUBLANDS_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SHRUBLANDS_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 6)
                                .add(UniformInt.of(1, 2), 3)
                                .add(UniformInt.of(1, 5), 1)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(12),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );



        register(context, SKYFIELDS_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SKYFIELDS_TREES),
                ConditionFilter.whenFalse(Conditions.CLASSIC_SKYFIELDS),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(1), 9)
                        .add(ConstantInt.of(2), 3)
                        .add(ConstantInt.of(0), 5)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, ConstantInt.of(2), 4),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(2),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.FIELDSPROOT_SAPLING.get()),
                DUNGEON_BLACKLIST
        );
        register(context, CLASSIC_SKYFIELDS_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CLASSIC_SKYFIELDS_TREES),
                ConditionFilter.whenTrue(Conditions.CLASSIC_SKYFIELDS),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(9), 9)
                        .add(ConstantInt.of(5), 3)
                        .add(ConstantInt.of(0), 5)
                        .add(ConstantInt.of(16), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, ConstantInt.of(2), 4),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.FIELDSPROOT_SAPLING.get()),
                DUNGEON_BLACKLIST
        );
        register(context, CRYSTALLIUM_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CRYSTALLIUM_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(9), 9)
                        .add(ConstantInt.of(7), 3)
                        .add(ConstantInt.of(3), 5)
                        .add(ConstantInt.of(16), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, ConstantInt.of(2), 4),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.GILDED_OAK_SAPLING.get()),
                DUNGEON_BLACKLIST
        );

        register(context, SHRUBLANDS_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SHRUBLANDS_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(ConstantInt.of(5), 9)
                        .add(ConstantInt.of(9), 3)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, ConstantInt.of(2), 4),
                BiomeFilter.biome(),
                RarityFilter.onAverageOnceEvery(2),
                PlacementUtils.filteredByBlockSurvival(AetherBlocks.SKYROOT_SAPLING.get()),
                DUNGEON_BLACKLIST
        );

        register(context, IRIDIA_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.IRIDIA_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 2), 4),
                RarityFilter.onAverageOnceEvery(15),
                BiomeFilter.biome());

        register(context, XAELIA_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.XAELIA_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 3), 4),
                RarityFilter.onAverageOnceEvery(7),
                BiomeFilter.biome());

        register(context, LUMINA_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.LUMINA_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(1, 2), 4),
                RarityFilter.onAverageOnceEvery(7),
                BiomeFilter.biome());

        register(context, FLAWLESS_BLOOM_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.FLAWLESS_BLOOM_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(1, 2), 4),
                RarityFilter.onAverageOnceEvery(7),
                BiomeFilter.biome());

        register(context, LIGHTROOTS, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.LIGHTROOTS),
                CountPlacement.of(UniformInt.of(104, 157)),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, new VerticalAnchor.Absolute(256))),
                InSquarePlacement.spread(),
                BiomeFilter.biome());

        register(context, MOSSY_HOLYSTONE_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.MOSSY_HOLYSTONE_ORE),
                CountPlacement.of(24),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                ConditionFilter.whenTrue(Conditions.MOSSY_ORE),
                BiomeFilter.biome()
        );

        register(context, MOSSY_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.MOSSY_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 5)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );
        register(context, ICESTONE_ROCK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.ICESTONE_ROCK),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING,
                        new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                                .add(ConstantInt.of(0), 7)
                                .add(UniformInt.of(1, 2), 5)
                                .add(UniformInt.of(1, 3), 3)
                                .build()), 4),
                RarityFilter.onAverageOnceEvery(16),
                InSquarePlacement.spread(),
                BiomeFilter.biome()
        );
        register(context, HOLYSILT_DISK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.HOLYSILT_DISK),
                RarityFilter.onAverageOnceEvery(5),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST
        );
        register(context, AEROGEL_DISK, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.AEROGEL_DISK),
                RarityFilter.onAverageOnceEvery(5),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome(),
                DUNGEON_BLACKLIST
        );

        register(context, SURFACE_RULE_WATER_LAKE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SURFACE_RULE_WATER_LAKE),
                RarityFilter.onAverageOnceEvery(15),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
        );

        register(context, BLIGHT_LAKE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHT_LAKE),
                RarityFilter.onAverageOnceEvery(15),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
        );

        register(context, BLIGHT_SPRING, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHT_SPRING),
                CountPlacement.of(30),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(8), VerticalAnchor.aboveBottom(128)),
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, SYRUP_LAKE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SYRUP_LAKE),
                RarityFilter.onAverageOnceEvery(8),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
        );

        register(context, SYRUP_SPRING, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SYRUP_SPRING),
                CountPlacement.of(50),
                InSquarePlacement.spread(),
                HeightRangePlacement.uniform(VerticalAnchor.aboveBottom(8), VerticalAnchor.aboveBottom(128)),
                BiomeFilter.biome(),
                new DungeonBlacklistFilter());

        register(context, OASIS_LAKE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SURFACE_RULE_WATER_LAKE),
                RarityFilter.onAverageOnceEvery(6),
                HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG),
                BiomeFilter.biome()
        );

        register(context, WYNDSPROUTS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.WYNDSPROUTS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(4),
                BiomeFilter.biome()
        );
        register(context, GENESIS_WYNDSPROUTS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GENESIS_WYNDSPROUTS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(8),
                BiomeFilter.biome()
        );

        register(context, GENESIS_SKYSPROUTS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GENESIS_SKYSPROUTS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(12),
                BiomeFilter.biome()
        );

        register(context, SKYSPROUTS_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SKYSPROUTS_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, BiasedToBottomInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(6),
                BiomeFilter.biome()
        );

        register(context, SPIROLYCTIL_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SPIROLYCTIL_PATCH),
                NOISE_THRESHOLD,
                RarityFilter.onAverageOnceEvery(12),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(1, 3), 4),
                BiomeFilter.biome());

        register(context, JELLYSHROOM_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.JELLYSHROOM_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(5),
                BiomeFilter.biome());

        register(context, DECREASED_JELLYSHROOM_PATCH, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.JELLYSHROOM_PATCH),
                NOISE_THRESHOLD,
                ImprovedLayerPlacementModifier.of(Heightmap.Types.MOTION_BLOCKING, UniformInt.of(0, 1), 4),
                RarityFilter.onAverageOnceEvery(16),
                BiomeFilter.biome());

        register(context, BLIGHT_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.BLIGHT_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(UniformInt.of(2, 6), 5)
                        .add(ConstantInt.of(4), 3)
                        .add(ConstantInt.of(6), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.BLIGHTWILLOW_SAPLING.get()),
                DUNGEON_BLACKLIST
        );

        register(context, DEEP_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.DEEP_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(UniformInt.of(2, 6), 5)
                        .add(ConstantInt.of(4), 3)
                        .add(ConstantInt.of(6), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.BLIGHTWILLOW_SAPLING.get()),
                DUNGEON_BLACKLIST
        );
        register(context, CANDY_TREES, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CANDY_TREES),
                CountPlacement.of(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder()
                        .add(UniformInt.of(2, 6), 5)
                        .add(ConstantInt.of(4), 3)
                        .add(ConstantInt.of(6), 1)
                        .build())),
                ImprovedLayerPlacementModifier.of(Heightmap.Types.OCEAN_FLOOR, UniformInt.of(0, 1), 4),
                BiomeFilter.biome(),
                PlacementUtils.filteredByBlockSurvival(ReduxBlocks.BLIGHTWILLOW_SAPLING.get()),
                DUNGEON_BLACKLIST
        );

        register(context, BONEMEAL_OVERRIDE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.GRASS_PATCH_BONEMEAL), PlacementUtils.isEmpty());
        register(context, SUGARGRASS_BONEMEAL, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SUGARGRASS_BONEMEAL), PlacementUtils.isEmpty());

        register(context, VERIDIUM_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.VERIDIUM_ORE),
                CountPlacement.of(11),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                BiomeFilter.biome()
        );
        register(context, CRYSTAL_GOLD_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.CRYSTAL_GOLD_ORE),
                CountPlacement.of(25),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(UniformHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.absolute(128))),
                BiomeFilter.biome()
        );

        register(context, SENTRITE_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.SENTRITE_ORE),
                InSquarePlacement.spread(),
//                RarityFilter.onAverageOnceEvery(1),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(128))),
                BiomeFilter.biome()
        );
        register(context, DIVINITE_ORE, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.DIVINITE_ORE),
                InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(6),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(192))),
                BiomeFilter.biome()
        );
        register(context, DIVINITE_ORE_INCREASED, configuredFeatures.getOrThrow(ReduxConfiguredFeatures.DIVINITE_ORE),
                InSquarePlacement.spread(),
                RarityFilter.onAverageOnceEvery(2),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.BOTTOM, VerticalAnchor.aboveBottom(192))),
                BiomeFilter.biome()
        );




    }





    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }

    private static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration, PlacementModifier... modifiers) {
        register(context, key, configuration, List.of(modifiers));
    }

    private static ResourceKey<PlacedFeature> createKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Redux.MODID, name));
    }
    private static ResourceKey<PlacedFeature> aetherKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(Aether.MODID, name));
    }
    private static String name(RegistryObject<?> reg)
    {
        return reg.getId().getPath();
    }

    private static ResourceKey<PlacedFeature> copyKey(ResourceKey<ConfiguredFeature<?, ?>> configFeat)
    {
        return createKey(configFeat.location().getPath());
    }

}
