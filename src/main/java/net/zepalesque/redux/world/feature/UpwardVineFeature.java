package net.zepalesque.redux.world.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;

public class UpwardVineFeature extends Feature<UpwardVineFeature.Config> {
   public UpwardVineFeature(Codec<Config> codec) {
      super(codec);
   }

   /**
    * Places the given feature at the given location.
    * During world generation, features are provided with a 3x3 region of chunks, centered on the chunk being generated,
    * that they can safely generate into.
    * @param context A context object with a reference to the level and the position the feature is being placed at
    */
   public boolean place(FeaturePlaceContext<Config> context) {
      WorldGenLevel worldgenlevel = context.level();
      BlockPos blockpos = context.origin();
      if (!canPlaceHere(worldgenlevel, blockpos)) {
         return false;
      } else {
         RandomSource rand = context.random();
         Config config = context.config();
         int height = config.length.sample(rand);
         BlockPos.MutableBlockPos mutable = new BlockPos.MutableBlockPos();

         for (int i = 0; i < height; i++) {
            mutable.setWithOffset(blockpos, 0, i, 0);
            boolean isLast = !validBlockPos(worldgenlevel, mutable.above()) || i == height - 1;
            if (isLast) {
               worldgenlevel.setBlock(mutable, config.head.getState(rand, mutable), 3);
               break;
            } else {
               worldgenlevel.setBlock(mutable, config.vine.getState(rand, mutable), 3);
            }
         }
         return true;
      }
   }

   private static boolean canPlaceHere(LevelAccessor level, BlockPos pos) {
      if (!validBlockPos(level, pos)) {
         return false;
      }
      BlockPos below = pos.below();
      return !level.isStateAtPosition(below, state -> !state.is(BlockTags.DIRT));
   }

   private static boolean validBlockPos(LevelAccessor level, BlockPos pos) {
      return level.isStateAtPosition(pos, state -> state.isAir() || state.canBeReplaced());
   }

   public record Config(BlockStateProvider vine, BlockStateProvider head, IntProvider length) implements FeatureConfiguration {
      public static final Codec<Config> CODEC = RecordCodecBuilder.create((p_191375_) -> p_191375_.group(
              BlockStateProvider.CODEC.fieldOf("vine_plant").forGetter(Config::vine),
              BlockStateProvider.CODEC.fieldOf("vine_head").forGetter(Config::head),
              IntProvider.POSITIVE_CODEC.fieldOf("length").forGetter(Config::length))
              .apply(p_191375_, Config::new));
   }
}