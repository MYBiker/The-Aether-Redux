package net.zepalesque.redux.mixin.common.block;

import com.aetherteam.aether.block.natural.EnchantedAetherGrassBlock;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.zepalesque.redux.block.ReduxBlocks;
import org.spongepowered.asm.mixin.Mixin;

import java.util.Optional;

@Mixin(EnchantedAetherGrassBlock.class)
public class EnchantedAetherGrassBlockMixin extends GrassBlockMixin {

    @Override
    protected boolean placeGrass(PlacedFeature instance, WorldGenLevel pLevel, ChunkGenerator pGenerator, RandomSource pRandom, BlockPos pPos, Operation<Boolean> original) {

        Holder<PlacedFeature> optional = VegetationPlacements.GRASS_BONEMEAL;
        if (instance.equals(optional.get())) {
            if (pLevel.ensureCanWrite(pPos)) {
                pLevel.setBlock(pPos, ReduxBlocks.SHORT_AETHER_GRASS.get().defaultBlockState(), 3);
                return true;
            } else {
                return false;
            }
        }
        return super.placeGrass(instance, pLevel, pGenerator, pRandom, pPos, original);
    }
}
