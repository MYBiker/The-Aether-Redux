package net.zepalesque.redux.world.tree.foliage;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

import java.util.function.BiConsumer;

public class SmallGoldenOakFoliagePlacer extends FoliagePlacer {
    public static final Codec<SmallGoldenOakFoliagePlacer> CODEC = RecordCodecBuilder.create((instance) -> foliagePlacerParts(instance)
            .apply(instance, SmallGoldenOakFoliagePlacer::new));

    public SmallGoldenOakFoliagePlacer(IntProvider pRadius, IntProvider pOffset) {
        super(pRadius, pOffset);
    }

    @Override
    protected void createFoliage(LevelSimulatedReader level, BiConsumer<BlockPos, BlockState> setter, RandomSource rand, TreeConfiguration config, int maxHeight, FoliageAttachment attachment, int height, int radius, int offset) {
        BlockPos origin = attachment.pos();

        // Place main piece
        placeLeavesRow(level, setter, rand, config, origin, radius - 1, 0, false);
        placeLeavesRow(level, setter, rand, config, origin, radius, -1, false);
        placeLeavesRow(level, setter, rand, config, origin, radius, -2, false);
        placeLeavesRow(level, setter, rand, config, origin, radius, -3, false);
        placeLeavesRow(level, setter, rand, config, origin, radius - 1, -4, false);
    }

    @Override
    public int foliageHeight(RandomSource rand, int pHeight, TreeConfiguration config) {
        return 0;
    }
    
    @Override
    protected boolean shouldSkipLocation(RandomSource rand, int x, int y, int z, int radius, boolean large) {
        if (y == 0 || y == -4) {
            // If the y offset is 0 or -4, only skip the location if it is on the corners, but do so 75% of the time
            return x + z >= radius * 2 && rand.nextFloat() < 0.75F;
        } else if (y == -1 || y == -3) {
            // If the y offset is -1 or -3, skip the corners always, and skip stuff outside a diamond shape 75% of the time
            boolean diamond = x + z <= radius;
            boolean corners = x + z <= radius + 1;
            return !corners || (!diamond && rand.nextFloat() < 0.75F);
        } else {
            // If the y offset is -2, skip the location if it is on the corners and an unlikely boolean check succeeds
            return x + z >= radius * 2 && rand.nextFloat() < 0.25F;
        }
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ReduxFoliagePlacers.SMALL_GOLDEN_OAK_FOLIAGE.get();
    }
}
