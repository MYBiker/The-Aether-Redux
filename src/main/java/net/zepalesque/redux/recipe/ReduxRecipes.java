package net.zepalesque.redux.recipe;

import com.aetherteam.aether.Aether;
import com.aetherteam.aether.recipe.recipes.block.AmbrosiumRecipe;
import com.aetherteam.nitrogen.recipe.serializer.BlockStateRecipeSerializer;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.recipe.recipes.InfusionRecipe;
import net.zepalesque.zenith.recipe.serializer.StackingRecipeSerializer;

public class ReduxRecipes {
    public static final DeferredRegister<RecipeType<?>> TYPES = DeferredRegister.create(BuiltInRegistries.RECIPE_TYPE, Redux.MODID);
    public static final DeferredHolder<RecipeType<?>, RecipeType<InfusionRecipe>> INFUSION = TYPES.register("infusion", () -> RecipeType.simple(new ResourceLocation(Redux.MODID, "infusion")));



    public static class Serializers {
        public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS = DeferredRegister.create(BuiltInRegistries.RECIPE_SERIALIZER, Redux.MODID);
        public static final DeferredHolder<RecipeSerializer<?>, StackingRecipeSerializer<InfusionRecipe>> INFUSION = SERIALIZERS.register("infusion", InfusionRecipe.Serializer::new);

    }
}