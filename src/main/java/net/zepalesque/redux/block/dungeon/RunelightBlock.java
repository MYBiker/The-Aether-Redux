package net.zepalesque.redux.block.dungeon;

import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.zepalesque.redux.block.construction.BaseLitBlock;

public class RunelightBlock extends BaseLitBlock {

    protected final boolean creativeInteractOnly;
    public RunelightBlock(Properties properties, boolean creativeInteractOnly) {
        super(properties);
        this.creativeInteractOnly = creativeInteractOnly;
    }

    @Override
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hit) {
        if (this.isValidForInteraction(player)) {
            if (!level.isClientSide()) {
                state = state.cycle(LIT);
                level.setBlock(pos, state, 3);
                // TODO: sfx
                level.gameEvent(player, state.getValue(LIT) ? GameEvent.BLOCK_ACTIVATE : GameEvent.BLOCK_DEACTIVATE, pos);
                return InteractionResult.CONSUME;
            } else {
                return InteractionResult.SUCCESS;
            }
        }
        return super.use(state, level, pos, player, hand, hit);
    }

    public boolean isValidForInteraction(Player player) {
        return !this.creativeInteractOnly || player.getAbilities().instabuild;
    }
}