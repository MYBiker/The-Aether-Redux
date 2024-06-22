package net.zepalesque.redux.world.tree.decorator;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecorator;
import net.minecraft.world.level.levelgen.feature.treedecorators.TreeDecoratorType;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.data.ReduxTags;

import java.util.Collection;
import java.util.Optional;

public class GoldenVineDecorator extends TreeDecorator {

    public static final Codec<GoldenVineDecorator> CODEC = RecordCodecBuilder.create((vines) ->
            vines.group(Codec.floatRange(0.0F, 1.0F).fieldOf("probability").forGetter((config) -> config.probability),
                            BlockStateProvider.CODEC.fieldOf("plant_body_provider").forGetter((config) -> config.bodyBlock),
                            BlockStateProvider.CODEC.fieldOf("plant_head_provider").forGetter((config) -> config.headBlock),
                            IntProvider.codec(1,128).fieldOf("length").forGetter((config) -> config.length))
                                    .apply(vines, GoldenVineDecorator::new));
    private final float probability;
    private final BlockStateProvider bodyBlock;
    private final BlockStateProvider headBlock;
    private final IntProvider length;

    public GoldenVineDecorator(float vineProbability, BlockStateProvider bodyBlock, BlockStateProvider headBlock, IntProvider vineLength) {
        this.probability = vineProbability;
        this.bodyBlock = bodyBlock;
        this.headBlock = headBlock;
        this.length = vineLength;
    }

    public void place(TreeDecorator.Context pContext) {
        Table<Integer, Integer, Integer> xzyLowestMap = HashBasedTable.create();
        for (BlockPos leafPos : pContext.leaves()) {
            int x = leafPos.getX();
            int y = leafPos.getY();
            int z = leafPos.getZ();
            try {
                if (!xzyLowestMap.contains(x, z)) {
                    Redux.LOGGER.debug("Leaf table does not have value: x={}, y={}, z={}, adding", x, y, z);
                    xzyLowestMap.put(x, z, y);
                } else {
                    int old = xzyLowestMap.get(x, z);
                    if (y < old) {
                        Redux.LOGGER.debug("Found new lowest value in table: x={}, y={}, z={}, old value was y={}", x, y, z, old);
                        xzyLowestMap.put(x, z, y);
                    }
                }
            } catch (NullPointerException exception) {
                Redux.LOGGER.error("Caught error when trying to add leaf to table!", exception);
            }
        }
        RandomSource randomsource = pContext.random();
        for (Table.Cell<Integer, Integer, Integer> leafPos : xzyLowestMap.cellSet()) {
            Redux.LOGGER.debug("Converting Table.Cell {}, {} -> {} to BlockPos", leafPos.getRowKey(), leafPos.getColumnKey(), leafPos.getValue());
            BlockPos pos = new BlockPos(leafPos.getRowKey(), leafPos.getValue(), leafPos.getColumnKey());
            Redux.LOGGER.debug("Got BlockPos: x={}, y={}, z={}", pos.getX(), pos.getY(), pos.getZ());
            int length = this.length.sample(randomsource);
            Redux.LOGGER.debug("Sampled length with value of {}", length);
            if (randomsource.nextFloat() < probability) {
                BlockPos blockpos = pos.below();
                Redux.LOGGER.debug("Getting block under pos x={}, y={}, z={}", pos.getX(), pos.getY(), pos.getZ());
                if (pContext.isAir(blockpos)) {
                    Redux.LOGGER.debug("Block is air!");
                    this.addVine(blockpos, pContext, length);
                } else {
                    Redux.LOGGER.debug("Block is not air!");
                }
            }
        }
    }

    private void addVine(BlockPos pPos, TreeDecorator.Context pContext, int length) {
        for (int i = 1; i <= length; i++) {
            BlockPos placement = pPos.offset(0, 1 - i, 0);
            if (!pContext.isAir(placement)) {
                Redux.LOGGER.debug("Failed to place vine #{}", i);
                break;
            }

            boolean notAirBelow = !pContext.isAir(placement.below());
            boolean maxLength = i >= length;
            if (notAirBelow || maxLength) {
                if (notAirBelow && maxLength) {
                    Redux.LOGGER.debug("Placing vine block #{}, which should be the final one as there is no air below, and the max length has been reached", i);
                } else if (notAirBelow) {
                    Redux.LOGGER.debug("Placing vine block #{}, which should be the final one as there is no air below", i);
                } else {
                    Redux.LOGGER.debug("Placing vine block #{}, which should be the final one as the max length has been reached", i);
                }
                pContext.setBlock(placement, this.headBlock.getState(pContext.random(), pPos));
                break;
            } else {
                Redux.LOGGER.debug("Placing vine block #{}, which should be a body block", i);
                pContext.setBlock(placement, this.bodyBlock.getState(pContext.random(), pPos));
            }
        }
    }

    @Override
    protected TreeDecoratorType<?> type() {
        return ReduxTreeDecorators.GOLDEN_VINES.get();
    }
}
