package net.zepalesque.redux.mixin.common.block;

import com.aetherteam.aether.block.natural.QuicksoilBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.zepalesque.redux.config.ReduxConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(QuicksoilBlock.class)
public class QuicksoilBlockMixin extends BlockBehaviorMixin {

    @Override
    public void pathFindable(BlockState state, BlockGetter level, BlockPos pos, PathComputationType type, CallbackInfoReturnable<Boolean> cir) {
        if (ReduxConfig.COMMON.mobs_avoid_quicksoil.get() && type == PathComputationType.LAND) {
            cir.setReturnValue(false);
        }
    }
}
