package net.zepalesque.redux.world.density;

import com.mojang.serialization.Codec;
import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.DensityFunction;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zepalesque.redux.Redux;

public class ReduxDensityFunctionTypes {

    public static final DeferredRegister<Codec<? extends DensityFunction>> DENSITY_FUNCTIONS = DeferredRegister.create(Registry.DENSITY_FUNCTION_TYPE_REGISTRY, Redux.MODID);
    public static RegistryObject<Codec<? extends DensityFunction>> SQUARE_ROOT = DENSITY_FUNCTIONS.register("square_root", SquareRootFunction.SQRT_CODEC::codec);
    public static RegistryObject<Codec<? extends DensityFunction>> CUBE_ROOT = DENSITY_FUNCTIONS.register("cube_root", CubeRootFunction.CBRT_CODEC::codec);
    public static RegistryObject<Codec<? extends DensityFunction>> INTERPOLATE = DENSITY_FUNCTIONS.register("interpolate", InterpolateFunction.INTERPOLATE_CODEC::codec);
}
