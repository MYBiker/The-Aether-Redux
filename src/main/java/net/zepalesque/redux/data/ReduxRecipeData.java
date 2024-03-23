package net.zepalesque.redux.data;

import com.aetherteam.aether.AetherTags;
import com.aetherteam.aether.block.AetherBlocks;
import com.aetherteam.aether.data.providers.AetherRecipeProvider;
import com.aetherteam.aether.item.AetherItems;
import com.aetherteam.aether_genesis.block.GenesisBlocks;
import com.aetherteam.aether_genesis.item.GenesisItems;
import com.aetherteam.nitrogen.recipe.BlockStateIngredient;
import com.aetherteam.nitrogen.recipe.builder.BlockStateRecipeBuilder;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.EntityPredicate;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.KilledTrigger;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalAdvancement;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;
import net.minecraftforge.fml.util.ObfuscationReflectionHelper;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.api.blockhandler.WoodHandler;
import net.zepalesque.redux.api.condition.AbstractCondition;
import net.zepalesque.redux.api.condition.Conditions;
import net.zepalesque.redux.block.ReduxBlocks;
import net.zepalesque.redux.item.ReduxItems;
import net.zepalesque.redux.misc.ReduxTags;
import net.zepalesque.redux.recipe.builder.StackingRecipeBuilder;
import net.zepalesque.redux.recipe.condition.DataRecipeCondition;
import net.zepalesque.redux.recipe.serializer.ReduxRecipeSerializers;
import org.jetbrains.annotations.NotNull;
import teamrazor.deepaether.init.DABlocks;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class ReduxRecipeData extends AetherRecipeProvider implements IConditionBuilder {
    public ReduxRecipeData(PackOutput output) {
        super(output, Redux.MODID);
    }

    @Override
    protected void buildRecipes(@NotNull Consumer<FinishedRecipe> consumer) {

        stairs(ReduxBlocks.DIVINITE_STAIRS, ReduxBlocks.DIVINITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.DIVINITE_SLAB.get(), ReduxBlocks.DIVINITE.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.DIVINITE_WALL.get(), ReduxBlocks.DIVINITE.get());
        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, Blocks.GLOWSTONE, ReduxBlocks.DIVINITE.get(), 0.0F, 200).save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_to_glowstone"));
        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.ENCHANTED_GRAVITITE.get(), ReduxItems.RAW_GRAVITITE.get(), 1.0F, 750).save(consumer, this.name("enchanted_gravitite_enchanting_from_raw_ore"));
        enchantingRecipe(RecipeCategory.DECORATIONS, ReduxBlocks.CRYSTAL_FRUIT_SAPLING.get(), ReduxTags.Items.BLUE_CRYSTAL_SAPLINGS, 0.0F, 150, "crystal_sapling").save(consumer, this.name("enchant_crystal_sapling"));

        ConditionalRecipe.builder().addCondition(
                dc(Conditions.GENESIS)
        ).addRecipe(enchantingRecipe(RecipeCategory.DECORATIONS, ReduxBlocks.PURPLE_CRYSTAL_FRUIT_SAPLING.get(), GenesisBlocks.PURPLE_CRYSTAL_TREE_SAPLING.get().asItem(), 0.0F, 150)::save).generateAdvancement().build(consumer, this.name("enchant_purple_crystal_sapling"));

        smeltingOreRecipe(ReduxItems.VERIDIUM_INGOT.get(), ReduxBlocks.VERIDIUM_ORE.get(), 0.8F).save(consumer, name("smelt_veridium"));
        blastingOreRecipe(ReduxItems.VERIDIUM_INGOT.get(), ReduxBlocks.VERIDIUM_ORE.get(), 0.8F).save(consumer, name("blast_veridium"));
        smeltingOreRecipe(ReduxItems.VERIDIUM_INGOT.get(), ReduxItems.RAW_VERIDIUM.get(), 0.8F).save(consumer, name("smelt_raw_veridium"));
        blastingOreRecipe(ReduxItems.VERIDIUM_INGOT.get(), ReduxItems.RAW_VERIDIUM.get(), 0.8F).save(consumer, name("blast_raw_veridium"));

        oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, ReduxItems.VERIDIUM_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.VERIDIUM_BLOCK.get(), "veridium_ingot_from_veridium_block", "veridium_ingot");

        oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, ReduxItems.RAW_VERIDIUM.get(), RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.RAW_VERIDIUM_BLOCK.get(), "raw_veridium_from_raw_veridium_block", "raw_veridium");
        oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, ReduxItems.RAW_VALKYRUM.get(), RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.RAW_VALKYRUM_BLOCK.get(), "raw_valkyrum_from_raw_valkyrum_block", "raw_valkyrum");

        oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, ReduxItems.GRAVITITE_INGOT.get(), RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.GRAVITITE_BLOCK.get(), "gravitite_ingot_from_gravitite_block", "gravitite_ingot");

        oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, ReduxItems.RAW_GRAVITITE.get(), RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.RAW_GRAVITITE_BLOCK.get(), "raw_gravitite_from_raw_gravitite_block", "raw_gravitite");


        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.DIVINITE_WALL.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.DIVINITE_SLAB.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.DIVINITE_STAIRS.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_stairs_stonecutting"));


        stonecut(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.ANGELIC_STONE.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_to_angelic_stone_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.ANGELIC_WALL.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_to_angelic_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.ANGELIC_SLAB.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_to_angelic_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.ANGELIC_STAIRS.get(), ReduxBlocks.DIVINITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.DIVINITE.getId().getPath() + "_to_angelic_stairs_stonecutting"));


        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.HELLFIRE_STONE.get(), 4)
                .define('D', ReduxBlocks.DIVINITE.get())
                .define('N', Blocks.NETHERRACK)
                .pattern("DN")
                .pattern("ND")
                .unlockedBy(getHasName(ReduxBlocks.DIVINITE.get()), has(ReduxBlocks.DIVINITE.get()))
                .save(consumer, Redux.locate("hellfire_stone"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.ANGELIC_STONE.get(), 4)
                .define('D', ReduxBlocks.DIVINITE.get())
                .pattern("DD")
                .pattern("DD")
                .unlockedBy(getHasName(ReduxBlocks.DIVINITE.get()), has(ReduxBlocks.DIVINITE.get()))
                .save(consumer, Redux.locate("angelic_stone"));

        stairs(ReduxBlocks.HOLEFIRE_STAIRS, ReduxBlocks.HOLEFIRE_STONE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.HOLEFIRE_SLAB.get(), ReduxBlocks.HOLEFIRE_STONE.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.HOLEFIRE_WALL.get(), ReduxBlocks.HOLEFIRE_STONE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_PILLAR.get(), 4)
                .define('#', ReduxBlocks.HOLEFIRE_STONE.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(ReduxBlocks.HOLEFIRE_STONE.get()), has(ReduxBlocks.HOLEFIRE_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_PILLAR_TOP.get(), 4)
                .define('#', ReduxBlocks.HOLEFIRE_PILLAR.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(ReduxBlocks.HOLEFIRE_STONE.get()), has(ReduxBlocks.HOLEFIRE_STONE.get()))
                .save(consumer);

        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_WALL.get(), ReduxBlocks.HOLEFIRE_STONE.get())
                .save(consumer, Redux.locate(ReduxBlocks.HOLEFIRE_STONE.getId().getPath() + "_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_SLAB.get(), ReduxBlocks.HOLEFIRE_STONE.get())
                .save(consumer, Redux.locate(ReduxBlocks.HOLEFIRE_STONE.getId().getPath() + "_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_STAIRS.get(), ReduxBlocks.HOLEFIRE_STONE.get())
                .save(consumer, Redux.locate(ReduxBlocks.HOLEFIRE_STONE.getId().getPath() + "_stairs_stonecutting"));

        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.GLOWY_HOLEFIRE_STONE.get(), ReduxBlocks.HOLEFIRE_STONE.get())
                .save(consumer, Redux.locate(ReduxBlocks.GLOWY_HOLEFIRE_STONE.getId().getPath() + "_stonecutting"));

        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_PILLAR.get(), ReduxBlocks.HOLEFIRE_STONE.get())
                .save(consumer, Redux.locate(ReduxBlocks.HOLEFIRE_PILLAR.getId().getPath() + "_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_PILLAR_TOP.get(), ReduxBlocks.HOLEFIRE_STONE.get())
                .save(consumer, Redux.locate(ReduxBlocks.HOLEFIRE_PILLAR_TOP.getId().getPath() + "_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_PILLAR_TOP.get(), ReduxBlocks.HOLEFIRE_PILLAR.get())
                .save(consumer, Redux.locate(ReduxBlocks.HOLEFIRE_PILLAR_TOP.getId().getPath() + "_stonecutting_from_pillar"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.HOLEFIRE_STONE.get(), 4)
                .define('D', Blocks.MAGMA_BLOCK)
                .pattern("DD")
                .pattern("DD")
                .unlockedBy(getHasName(Blocks.MAGMA_BLOCK), has(Blocks.MAGMA_BLOCK))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICKS.get(), 4)
                .define('S', ReduxBlocks.SENTRITE.get())
                .pattern("SS")
                .pattern("SS")
                .unlockedBy(getHasName(ReduxBlocks.SENTRITE.get()), has(ReduxBlocks.SENTRITE.get()))
                .save(consumer);

        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_WALL.get(), ReduxBlocks.SENTRITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_SLAB.get(), ReduxBlocks.SENTRITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_STAIRS.get(), ReduxBlocks.SENTRITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_stairs_stonecutting"));
        stairs(ReduxBlocks.SENTRITE_STAIRS, ReduxBlocks.SENTRITE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.SENTRITE_SLAB.get(), ReduxBlocks.SENTRITE.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.SENTRITE_WALL.get(), ReduxBlocks.SENTRITE.get());


        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICK_WALL.get(), ReduxBlocks.SENTRITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_brick_wall_from_base_block_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICK_SLAB.get(), ReduxBlocks.SENTRITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_brick_from_base_block_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICK_STAIRS.get(), ReduxBlocks.SENTRITE.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_brick_from_base_block_stairs_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICK_WALL.get(), ReduxBlocks.SENTRITE_BRICKS.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_brick_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICK_SLAB.get(), ReduxBlocks.SENTRITE_BRICKS.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_brick_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SENTRITE_BRICK_STAIRS.get(), ReduxBlocks.SENTRITE_BRICKS.get())
                .save(consumer, Redux.locate(ReduxBlocks.SENTRITE.getId().getPath() + "_brick_stairs_stonecutting"));
        stairs(ReduxBlocks.SENTRITE_BRICK_STAIRS, ReduxBlocks.SENTRITE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.SENTRITE_BRICK_SLAB.get(), ReduxBlocks.SENTRITE_BRICKS.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.SENTRITE_BRICK_WALL.get(), ReduxBlocks.SENTRITE_BRICKS.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, AetherBlocks.CARVED_STONE.get(), 4)
                .define('S', ReduxBlocks.SENTRITE.get())
                .define('H', AetherTags.Items.HOLYSTONE)
                .pattern("SH")
                .pattern("HS")
                .unlockedBy(getHasName(ReduxBlocks.SENTRITE.get()), has(ReduxBlocks.SENTRITE.get()))
                .save(consumer, Redux.locate("carved_stone"));

//        swetBall(AetherItems.SWET_BALL, ReduxTags.Items.BLUE_SWET_JELLY).save(consumer, Redux.locate("blue_swet_ball"));
//        swetBall(ReduxItems.VANILLA_SWET_BALL, ReduxItems.VANILLA_SWET_JELLY).save(consumer, Redux.locate("vanilla_swet_ball"));

        enchantingRecipe(RecipeCategory.MISC, ReduxBlocks.GILDED_HOLYSTONE.get(), AetherBlocks.MOSSY_HOLYSTONE.get(), 0.0F, 100).save(consumer, Redux.locate("enchant_mossy_holystone"));
        oneToOneConversionRecipe(consumer, Items.LIGHT_BLUE_DYE, ReduxBlocks.SPIROLYCTIL.get(), null);
        oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ReduxBlocks.BLIGHTSHADE.get(), null);

        oneToOneConversionRecipe(consumer, Items.YELLOW_DYE, ReduxBlocks.AURUM.get(), null);
        oneToOneConversionRecipe(consumer, Items.PURPLE_DYE, ReduxBlocks.ZYATRIX.get(), null);

        oneToOneConversionRecipe(consumer, Items.WHITE_DYE, ReduxBlocks.DAGGERBLOOM.get(), null);
        oneToOneConversionRecipe(consumer, Items.BLACK_DYE, ReduxBlocks.LUMINA.get(), null);

        oneToOneConversionRecipe(consumer, Items.PINK_DYE, ReduxBlocks.THERATIP.get(), null);

        oneToOneConversionRecipe(consumer, Items.BLAZE_POWDER, ReduxBlocks.INFERNIA.get(), null);

        oneToOneConversionRecipe(consumer, Items.WHITE_DYE, ReduxBlocks.FLAREBLOSSOM.get(), null);

        oneToOneConversionRecipe(consumer, Items.MAGENTA_DYE, ReduxBlocks.IRIDIA.get(), null);

        oneToOneConversionRecipe(consumer, Items.LIGHT_GRAY_DYE, ReduxBlocks.XAELIA_PATCH.get(), null);

        ambrosiumEnchanting(ReduxBlocks.GILDED_HOLYSTONE.get(), AetherBlocks.MOSSY_HOLYSTONE.get()).save(consumer, name("ambrosium_enchant_mossy_holystone_to_gilded_holystone"));
        sporeBlighting(ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get(), AetherBlocks.MOSSY_HOLYSTONE.get()).save(consumer, name("spore_blight_mossy_holystone_to_blightmoss_holystone"));
        sporeBlighting(ReduxBlocks.BLIGHTMOSS_BLOCK.get(), DABlocks.AETHER_MOSS_BLOCK.get()).save(consumer, name("spore_blight_deep_aether_aether_moss_block_to_blightmoss_block"));
        sporeBlighting(ReduxBlocks.BLIGHTMOSS_CARPET.get(), DABlocks.AETHER_MOSS_CARPET.get()).save(consumer, name("spore_blight_deep_aether_aether_moss_carpet_to_blightmoss_carpet"));

        infusionCharge(consumer, ReduxItems.VERIDIUM_PICKAXE, ReduxItems.INFUSED_VERIDIUM_PICKAXE);
        infusionCharge(consumer, ReduxItems.VERIDIUM_AXE, ReduxItems.INFUSED_VERIDIUM_AXE);
        infusionCharge(consumer, ReduxItems.VERIDIUM_SWORD, ReduxItems.INFUSED_VERIDIUM_SWORD);
        infusionCharge(consumer, ReduxItems.VERIDIUM_HOE, ReduxItems.INFUSED_VERIDIUM_HOE);
        infusionCharge(consumer, ReduxItems.VERIDIUM_SHOVEL, ReduxItems.INFUSED_VERIDIUM_SHOVEL);
        infusionCharge(consumer, ReduxItems.VERIDIUM_DART_SHOOTER, ReduxItems.INFUSED_VERIDIUM_DART_SHOOTER);

        twoByTwoPacker(consumer, RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SHELL_SHINGLES.get(), ReduxItems.MYKAPOD_SHELL_CHUNK.get());
        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLES.get(), ReduxBlocks.SHELL_SHINGLES.get(), 0F, 150);


        stairs(ReduxBlocks.SHELL_SHINGLE_STAIRS, ReduxBlocks.SHELL_SHINGLES).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.SHELL_SHINGLE_SLAB.get(), ReduxBlocks.SHELL_SHINGLES.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.SHELL_SHINGLE_WALL.get(), ReduxBlocks.SHELL_SHINGLES.get());
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SHELL_SHINGLE_WALL.get(), ReduxBlocks.SHELL_SHINGLES.get())
                .save(consumer, Redux.locate("shell_shingle_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SHELL_SHINGLE_SLAB.get(), ReduxBlocks.SHELL_SHINGLES.get())
                .save(consumer, Redux.locate("shell_shingle_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.SHELL_SHINGLE_STAIRS.get(), ReduxBlocks.SHELL_SHINGLES.get())
                .save(consumer, Redux.locate("shell_shingle_stairs_stonecutting"));

        stairs(ReduxBlocks.ENCHANTED_SHELL_SHINGLE_STAIRS, ReduxBlocks.ENCHANTED_SHELL_SHINGLES).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.ENCHANTED_SHELL_SHINGLE_SLAB.get(), ReduxBlocks.ENCHANTED_SHELL_SHINGLES.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.ENCHANTED_SHELL_SHINGLE_WALL.get(), ReduxBlocks.ENCHANTED_SHELL_SHINGLES.get());
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLE_WALL.get(), ReduxBlocks.ENCHANTED_SHELL_SHINGLES.get())
                .save(consumer, Redux.locate("enchanted_shell_shingle_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLE_SLAB.get(), ReduxBlocks.ENCHANTED_SHELL_SHINGLES.get())
                .save(consumer, Redux.locate("enchanted_shell_shingle_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLE_STAIRS.get(), ReduxBlocks.ENCHANTED_SHELL_SHINGLES.get())
                .save(consumer, Redux.locate("enchanted_shell_shingle_stairs_stonecutting"));

        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLE_STAIRS.get(), ReduxBlocks.SHELL_SHINGLE_STAIRS.get(), 0F, 150);
        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLE_WALL.get(), ReduxBlocks.SHELL_SHINGLE_WALL.get(), 0F, 150);
        enchantingRecipe(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.ENCHANTED_SHELL_SHINGLE_SLAB.get(), ReduxBlocks.SHELL_SHINGLE_SLAB.get(), 0F, 150);


        carpet(consumer, ReduxBlocks.BLIGHTMOSS_CARPET.get(), ReduxBlocks.BLIGHTMOSS_BLOCK.get());
        carpet(consumer, ReduxBlocks.FUNGAL_CARPET.get(), ReduxBlocks.FUNGAL_GROWTH.get());

        stairs(ReduxBlocks.GILDED_HOLYSTONE_STAIRS, ReduxBlocks.GILDED_HOLYSTONE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.GILDED_HOLYSTONE_SLAB.get(), ReduxBlocks.GILDED_HOLYSTONE.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.GILDED_HOLYSTONE_WALL.get(), ReduxBlocks.GILDED_HOLYSTONE.get());
        stairs(ReduxBlocks.BLIGHTMOSS_HOLYSTONE_STAIRS, ReduxBlocks.BLIGHTMOSS_HOLYSTONE).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.BLIGHTMOSS_HOLYSTONE_SLAB.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.BLIGHTMOSS_HOLYSTONE_WALL.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get());
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.GILDED_HOLYSTONE_WALL.get(), ReduxBlocks.GILDED_HOLYSTONE.get())
                .save(consumer, Redux.locate("gilded_holystone_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.GILDED_HOLYSTONE_SLAB.get(), ReduxBlocks.GILDED_HOLYSTONE.get())
                .save(consumer, Redux.locate("gilded_holystone_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.GILDED_HOLYSTONE_STAIRS.get(), ReduxBlocks.GILDED_HOLYSTONE.get())
                .save(consumer, Redux.locate("gilded_holystone_stairs_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.BLIGHTMOSS_HOLYSTONE_WALL.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get())
                .save(consumer, Redux.locate("blightmoss_holystone_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.BLIGHTMOSS_HOLYSTONE_SLAB.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get())
                        .save(consumer, Redux.locate("blightmoss_holystone_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.BLIGHTMOSS_HOLYSTONE_STAIRS.get(), ReduxBlocks.BLIGHTMOSS_HOLYSTONE.get())
                .save(consumer, Redux.locate("blightmoss_holystone_stairs_stonecutting"));

        // Gummy Swet recipes
        ConditionalRecipe.builder().addCondition(
                        dc(Conditions.GUMMY_NERF)
                )
                .addRecipe(gummySwet(GenesisItems.DARK_GUMMY_SWET, GenesisItems.DARK_SWET_BALL)::save)
                .generateAdvancement().build(consumer, Redux.locate("genesis_dark_gummy_swet"));

        ConditionalRecipe.builder().addCondition(
                        dc(Conditions.GUMMY_NERF)
                )
                .addRecipe(gummySwet(AetherItems.BLUE_GUMMY_SWET, AetherItems.SWET_BALL)::save)
                .generateAdvancement().build(consumer, Redux.locate("blue_gummy_swet"));

        ConditionalRecipe.builder().addCondition(
                        dc(Conditions.GUMMY_NERF)
                )
                .addRecipe(gummySwet(AetherItems.GOLDEN_GUMMY_SWET, ReduxTags.Items.GOLDEN_SWET_BALL, "has_golden_swet_ball")::save)
                .generateAdvancement().build(consumer, Redux.locate("golden_gummy_swet"));

        ConditionalRecipe.builder().addCondition(
                        dc(Conditions.GUMMY_NERF)
                )
                .addRecipe(gummySwet(ReduxItems.VANILLA_GUMMY_SWET, ReduxItems.VANILLA_SWET_BALL)::save)
                .generateAdvancement().build(consumer, Redux.locate("vanilla_gummy_swet"));


        // Swet jelly recipes

        swetJelly(ReduxItems.VANILLA_SWET_JELLY, ReduxItems.VANILLA_SWET_BALL).save(consumer, Redux.locate("vanilla_swet_jelly"));
        ConditionalRecipe.builder().addCondition(
                        not(dc(Conditions.GENESIS))
                )
                .addRecipe(swetJelly(ReduxItems.GOLDEN_SWET_JELLY, ReduxTags.Items.GOLDEN_SWET_BALL)::save)
                .generateAdvancement().build(consumer, Redux.locate("golden_swet_jelly"));
        ConditionalRecipe.builder().addCondition(
                        not(dc(Conditions.GENESIS))
                )
                .addRecipe(swetJelly(ReduxItems.BLUE_SWET_JELLY, AetherItems.SWET_BALL)::save)
                .generateAdvancement().build(consumer, Redux.locate("blue_swet_jelly"));



        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ReduxItems.BLUEBERRY_PIE.get()).requires(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get()).requires(AetherItems.BLUE_BERRY.get()).requires(ReduxTags.Items.BLUEBERRY_PIE_EGGS).requires(Items.SUGAR).unlockedBy("has_blue_berry", inventoryTrigger(ItemPredicate.Builder.item()
                .of(AetherItems.BLUE_BERRY.get()).build())).unlockedBy("has_oats", inventoryTrigger(ItemPredicate.Builder.item()
                .of(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get()).build())).save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.VERIDIUM_DART.get(), 4)
                .define('F', Tags.Items.FEATHERS)
                .define('/', AetherTags.Items.SKYROOT_STICKS)
                .define('V', ReduxItems.VERIDIUM_INGOT.get())
                .pattern("F")
                .pattern("/")
                .pattern("V")
                .unlockedBy("has_feather", has(Tags.Items.FEATHERS))
                .unlockedBy(getHasName(ReduxItems.VERIDIUM_INGOT.get()), has(ReduxItems.VERIDIUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.VERIDIUM_DART_SHOOTER.get(), 1)
                .define('H', AetherBlocks.HOLYSTONE.get())
                .define('V', ReduxItems.VERIDIUM_INGOT.get())
                .pattern("H")
                .pattern("H")
                .pattern("V")
                .unlockedBy(getHasName(ReduxItems.VERIDIUM_INGOT.get()), has(ReduxItems.VERIDIUM_INGOT.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.SPEAR_OF_THE_BLIGHT.get(), 1)
                .define('F', ReduxItems.BLIGHTBUNNY_FANG.get())
                .define('/', AetherTags.Items.SKYROOT_STICKS)
                .define('G', AetherItems.ZANITE_GEMSTONE.get())
                .pattern("F")
                .pattern("/")
                .pattern("G")
                .unlockedBy(getHasName(ReduxItems.BLIGHTBUNNY_FANG.get()), has(ReduxItems.BLIGHTBUNNY_FANG.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.SNAILSHELL_SHIELD.get(), 1)
                .define('S', ReduxItems.MYKAPOD_SHELL_CHUNK.get())
                .define('G', AetherBlocks.ENCHANTED_GRAVITITE.get())
                .pattern("GSG")
                .pattern("SSS")
                .pattern("GSG")
                .unlockedBy(getHasName(ReduxItems.MYKAPOD_SHELL_CHUNK.get()), has(ReduxItems.MYKAPOD_SHELL_CHUNK.get()))
                .save(consumer);

        enchantingRecipe(RecipeCategory.FOOD, ReduxItems.ENCHANTED_BLUEBERRY_PIE.get(), ReduxItems.BLUEBERRY_PIE.get(), 0.35F, 250).save(consumer, name("enchanted_blueberry_pie_enchanting"));

        stairs(ReduxBlocks.CARVED_STONE_BRICK_STAIRS, ReduxBlocks.CARVED_STONE_BRICKS).save(consumer);
        slab(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.CARVED_STONE_BRICK_SLAB.get(), ReduxBlocks.CARVED_STONE_BRICKS.get());
        wall(consumer, RecipeCategory.BUILDING_BLOCKS,ReduxBlocks.CARVED_STONE_BRICK_WALL.get(), ReduxBlocks.CARVED_STONE_BRICKS.get());

        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICK_WALL.get(), ReduxBlocks.CARVED_STONE_BRICKS.get())
                .save(consumer, Redux.locate("carved_stone_brick_wall_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICK_SLAB.get(), ReduxBlocks.CARVED_STONE_BRICKS.get())
                .save(consumer, Redux.locate("carved_stone_brick_slab_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICK_STAIRS.get(), ReduxBlocks.CARVED_STONE_BRICKS.get())
                .save(consumer, Redux.locate("carved_stone_brick_stairs_stonecutting"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_PILLAR.get(), AetherBlocks.CARVED_STONE.get())
                .save(consumer, Redux.locate("carved_stone_pillar_stonecutting"));

        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICK_WALL.get(), AetherBlocks.CARVED_STONE.get())
                .save(consumer, Redux.locate("carved_stone_brick_wall_stonecutting_from_carved_stone"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICK_SLAB.get(), AetherBlocks.CARVED_STONE.get())
                .save(consumer, Redux.locate("carved_stone_brick_slab_stonecutting_from_carved_stone"));
        stonecut(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICK_STAIRS.get(), AetherBlocks.CARVED_STONE.get())
                .save(consumer, Redux.locate("carved_stone_brick_stairs_stonecutting_from_carved_stone"));

        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_BRICKS.get(), 4)
                .define('#', AetherBlocks.CARVED_STONE.get())
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AetherBlocks.CARVED_STONE.get()), has(AetherBlocks.CARVED_STONE.get()))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, ReduxBlocks.CARVED_STONE_PILLAR.get(), 4)
                .define('#', AetherBlocks.CARVED_STONE.get())
                .pattern("##")
                .pattern("##")
                .pattern("##")
                .unlockedBy(getHasName(AetherBlocks.CARVED_STONE.get()), has(AetherBlocks.CARVED_STONE.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ReduxBlocks.VERIDIUM_CHAIN.get(), 3)
                .define('I', ReduxItems.VERIDIUM_INGOT.get())
                .define('N', ReduxItems.VERIDIUM_NUGGET.get())
                .pattern("N")
                .pattern("I")
                .pattern("N")
                .unlockedBy(getHasName(ReduxItems.VERIDIUM_NUGGET.get()), has(ReduxItems.VERIDIUM_NUGGET.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.ENCHANTED_RING.get(), 1)
                .define('P', AetherTags.Items.PLANKS_CRAFTING)
                .define('A', AetherItems.AMBROSIUM_SHARD.get())
                .pattern("PAP")
                .pattern("P P")
                .pattern("PPP")
                .unlockedBy(getHasName(AetherItems.AMBROSIUM_SHARD.get()), has(AetherItems.AMBROSIUM_SHARD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.RING_OF_WISDOM.get(), 1)
                .define('H', AetherTags.Items.HOLYSTONE)
                .define('E', Items.EMERALD)
                .define('R', ReduxItems.ENCHANTED_RING.get())
                .pattern("HEH")
                .pattern("HRH")
                .pattern("HHH")
                .unlockedBy(getHasName(ReduxItems.ENCHANTED_RING.get()), has(ReduxItems.ENCHANTED_RING.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.SENTRY_RING.get(), 1)
                .define('H', ReduxBlocks.SENTRITE.get())
                .define('E', ReduxItems.SENTRY_CHIP.get())
                .define('R', ReduxItems.ENCHANTED_RING.get())
                .pattern("HEH")
                .pattern("HRH")
                .pattern("HHH")
                .unlockedBy(getHasName(ReduxItems.SENTRY_CHIP.get()), has(ReduxItems.SENTRY_CHIP.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.SHROOM_RING.get(), 1)
                .define('L', ReduxItems.LIGHTROOT_CLUMP.get())
                .define('S', ReduxBlocks.SHIMMERSTOOL.get())
                .define('C', ReduxBlocks.CLOUDCAP_MUSHLING.get())
                .define('R', ReduxItems.ENCHANTED_RING.get())
                .pattern("LSL")
                .pattern("CRC")
                .pattern("LCL")
                .unlockedBy(getHasName(ReduxBlocks.SHIMMERSTOOL.get()), has(ReduxBlocks.SHIMMERSTOOL.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ReduxBlocks.VERIDIUM_LANTERN.get(), 1)
                .define('I', ReduxItems.VERIDIUM_INGOT.get())
                .define('N', ReduxItems.VERIDIUM_NUGGET.get())
                .define('A', AetherBlocks.AMBROSIUM_TORCH.get())
                .pattern("NIN")
                .pattern("NAN")
                .pattern("NIN")
                .unlockedBy(getHasName(ReduxItems.VERIDIUM_NUGGET.get()), has(ReduxItems.VERIDIUM_NUGGET.get()))
                .unlockedBy(getHasName(AetherBlocks.AMBROSIUM_TORCH.get()), has(AetherBlocks.AMBROSIUM_TORCH.get()))
                .unlockedBy(getHasName(AetherItems.AMBROSIUM_SHARD.get()), has(AetherItems.AMBROSIUM_SHARD.get()))
                .save(consumer);

        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ReduxItems.FEATHER_OF_WARDING.get(), 1)
                .define('V', ReduxBlocks.VERIDIUM_BLOCK.get())
                .define('A', AetherItems.AMBROSIUM_SHARD.get())
                .define('F', ReduxItems.COCKATRICE_FEATHER.get())
                .define('S', ReduxItems.BLIGHTED_SPORES.get())
                .pattern("VAV")
                .pattern("VFV")
                .pattern(" S ")
                .unlockedBy(getHasName(ReduxItems.COCKATRICE_FEATHER.get()), has(ReduxItems.COCKATRICE_FEATHER.get()))
                .save(consumer);

        ConditionalRecipe.builder().addCondition(dc(Conditions.GENESIS)).addRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ReduxItems.MOUSE_EAR_SOUP.get())
                .requires(Ingredient.of(Items.BOWL)).requires(ReduxTags.Items.MOUSE_EAR_CAPS)
                .unlockedBy("has_mouse_ear_cap", inventoryTrigger(ItemPredicate.Builder.item()
                .of(ReduxTags.Items.MOUSE_EAR_CAPS).build()))::save).build(consumer, Redux.locate("mouse_ear_soup"));


        ConditionalRecipe.builder().addCondition(
                dc(Conditions.DEEP)
        ).addRecipe(
                ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, DABlocks.AETHER_COARSE_DIRT.get(), 4)
                        .define('D', AetherBlocks.AETHER_DIRT.get())
                        .define('H', ReduxBlocks.HOLYSILT.get())
                        .pattern("DH")
                        .pattern("HD")
                        .unlockedBy(getHasName(ReduxBlocks.HOLYSILT.get()), has(ReduxBlocks.HOLYSILT.get()))
                        ::save).build(consumer, Redux.locate("deep_aether_coarse_dirt"));

        ConditionalRecipe.builder().addCondition(
                not(dc(Conditions.DEEP))
        ).addRecipe(
                ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ReduxBlocks.COARSE_AETHER_DIRT.get(), 4)
                        .define('D', AetherBlocks.AETHER_DIRT.get())
                        .define('H', ReduxBlocks.HOLYSILT.get())
                        .pattern("DH")
                        .pattern("HD")
                        .unlockedBy(getHasName(ReduxBlocks.HOLYSILT.get()), has(ReduxBlocks.HOLYSILT.get()))
                        ::save).build(consumer, Redux.locate("redux_coarse_dirt"));


        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ReduxItems.WYNDSPROUT_BAGEL.get(), 1)
                .requires(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get(), 4)
                .unlockedBy(getHasName(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get()), has(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ReduxItems.BLUEBERRY_BAGEL.get(), 1)
                .requires(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get(), 3)
                .requires(AetherItems.BLUE_BERRY.get(), 1)
                .unlockedBy(getHasName(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get()), has(ReduxItems.BUNDLE_OF_WYNDSPROUTS.get()))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, ReduxItems.OATMEAL.get(), 1)
                        .requires(Items.BOWL)
                        .requires(ReduxItems.WYNDSPROUT_SEEDS.get(), 3)
                .unlockedBy(getHasName(ReduxItems.WYNDSPROUT_SEEDS.get()), has(ReduxItems.WYNDSPROUT_SEEDS.get()))
                .save(consumer);

        makePickaxe(ReduxItems.VERIDIUM_PICKAXE, ReduxItems.VERIDIUM_INGOT).save(consumer);
        makeShovel(ReduxItems.VERIDIUM_SHOVEL, ReduxItems.VERIDIUM_INGOT).save(consumer);
        makeAxe(ReduxItems.VERIDIUM_AXE, ReduxItems.VERIDIUM_INGOT).save(consumer);
        makeHoe(ReduxItems.VERIDIUM_HOE, ReduxItems.VERIDIUM_INGOT).save(consumer);
        makeSword(ReduxItems.VERIDIUM_SWORD, ReduxItems.VERIDIUM_INGOT).save(consumer);

        oreBlockStorageRecipesRecipesWithCustomUnpacking(consumer, RecipeCategory.MISC, ReduxItems.VERIDIUM_NUGGET.get(), RecipeCategory.MISC, ReduxItems.VERIDIUM_INGOT.get(), "veridium_nugget", "veridium_nugget_to_veridium_ingot");

        for (WoodHandler woodHandler : Redux.WoodHandlers.WOOD_HANDLERS) {
            woodFromLogs(consumer, woodHandler.wood.get(), woodHandler.log.get());
            planksFromLog(consumer, woodHandler.planks.get(), woodHandler.logsTag, 4);
            fence(woodHandler.fence, woodHandler.planks).save(consumer);
            fenceGate(woodHandler.fenceGate, woodHandler.planks).save(consumer);
            doorBuilder(woodHandler.door.get(), Ingredient.of(woodHandler.planks.get())).unlockedBy(getHasName(woodHandler.planks.get()), has(woodHandler.planks.get())).group("wooden_door").save(consumer);
            trapdoorBuilder(woodHandler.trapdoor.get(), Ingredient.of(woodHandler.planks.get())).unlockedBy(getHasName(woodHandler.planks.get()), has(woodHandler.planks.get())).group("wooden_trapdoor").save(consumer);
            pressurePlate(consumer, woodHandler.pressurePlate.get(), woodHandler.planks.get());
            buttonBuilder(woodHandler.button.get(), Ingredient.of(woodHandler.planks.get())).unlockedBy(getHasName(woodHandler.planks.get()), has(woodHandler.planks.get())).group("wooden_button").save(consumer);
            skyrootSignBuilder(woodHandler.signItem.get(), Ingredient.of(woodHandler.planks.get())).unlockedBy(getHasName(woodHandler.planks.get()), has(woodHandler.planks.get())).group("wooden_sign").save(consumer);
            hangingAetherSign(consumer, woodHandler.hangingSignItem.get(), woodHandler.hasStrippedLogs && woodHandler.strippedLog.isPresent() ? woodHandler.strippedLog.get().get() : woodHandler.log.get());
            woodenBoat(consumer, woodHandler.boatItem.get(), woodHandler.planks.get());
            chestBoat(consumer, woodHandler.chestBoatItem.get(), woodHandler.boatItem.get());
            slab(consumer, RecipeCategory.BUILDING_BLOCKS, woodHandler.slab.get(), woodHandler.planks.get());
            stairs(woodHandler.stairs, woodHandler.planks).save(consumer);
            bookshelf(consumer, woodHandler.planks.get(), woodHandler.bookshelf.get());
            if (woodHandler.hasStrippedLogs && woodHandler.strippedLog.isPresent() && woodHandler.strippedWood.isPresent())
            {
                woodFromLogs(consumer, woodHandler.strippedWood.get().get(), woodHandler.strippedLog.get().get());
            }
            if (woodHandler.alwaysLogWalls)
            {
                wall(consumer, RecipeCategory.BUILDING_BLOCKS, woodHandler.logWall.get(), woodHandler.log.get());
                wall(consumer, RecipeCategory.BUILDING_BLOCKS, woodHandler.woodWall.get(), woodHandler.wood.get());
                if (woodHandler.hasStrippedLogs && woodHandler.strippedLogWall.isPresent() && woodHandler.strippedWoodWall.isPresent() && woodHandler.strippedLog.isPresent() && woodHandler.strippedWood.isPresent()) {
                    wall(consumer, RecipeCategory.BUILDING_BLOCKS, woodHandler.strippedLogWall.get().get(), woodHandler.strippedLog.get().get());
                    wall(consumer, RecipeCategory.BUILDING_BLOCKS, woodHandler.strippedWoodWall.get().get(), woodHandler.strippedWood.get().get());
                }
            } else {
                ConditionalRecipe.builder().addCondition(dc(Conditions.GENESIS)).addRecipe(wallNoBuild(RecipeCategory.BUILDING_BLOCKS, woodHandler.logWall.get(), woodHandler.log.get())::save).generateAdvancement().build(consumer, getDefaultRecipeId(woodHandler.logWall.get()));
                ConditionalRecipe.builder().addCondition(dc(Conditions.GENESIS)).addRecipe(wallNoBuild(RecipeCategory.BUILDING_BLOCKS, woodHandler.woodWall.get(), woodHandler.wood.get())::save).generateAdvancement().build(consumer, getDefaultRecipeId(woodHandler.woodWall.get()));
                if (woodHandler.hasStrippedLogs && woodHandler.strippedLogWall.isPresent() && woodHandler.strippedWoodWall.isPresent() && woodHandler.strippedLog.isPresent() && woodHandler.strippedWood.isPresent()) {
                    ConditionalRecipe.builder().addCondition(dc(Conditions.GENESIS)).addRecipe(wallNoBuild(RecipeCategory.BUILDING_BLOCKS, woodHandler.strippedLogWall.get().get(), woodHandler.strippedLog.get().get())::save).generateAdvancement().build(consumer, getDefaultRecipeId(woodHandler.strippedLogWall.get().get()));
                    ConditionalRecipe.builder().addCondition(dc(Conditions.GENESIS)).addRecipe(wallNoBuild(RecipeCategory.BUILDING_BLOCKS, woodHandler.strippedWoodWall.get().get(), woodHandler.strippedWood.get().get())::save).generateAdvancement().build(consumer, getDefaultRecipeId(woodHandler.strippedWoodWall.get().get()));
                }
            }
        }

    }

    public ResourceLocation name(String name) {
        return Redux.locate(name);
    }

  protected static BlockStateRecipeBuilder sporeBlighting(Block result, Block ingredient) {
      return BlockStateRecipeBuilder.recipe(BlockStateIngredient.of(ingredient), result, ReduxRecipeSerializers.SPORE_BLIGHTING.get());
  }

  protected static void infusionStacking(Consumer<FinishedRecipe> consumer, RegistryObject<? extends Item> result, RegistryObject<? extends Item> ingredient) {
      infusionStacking(result.get(), ingredient.get()).save(consumer, Redux.locate("infuse_" + ingredient.getId().getPath()));
  }

  protected static void infusionStacking(Consumer<FinishedRecipe> consumer, RegistryObject<? extends Item> result, RegistryObject<? extends Item> ingredient, int infusionAmount) {
      infusionStacking(result.get(), ingredient.get(), infusionAmount).save(consumer, Redux.locate("infuse_and_charge_" + ingredient.getId().getPath()));
  }

  protected static void infusionCharge(Consumer<FinishedRecipe> consumer, RegistryObject<? extends Item> uninfused, RegistryObject<? extends Item> infused) {
      infusionStacking(infused.get(), uninfused.get(), 8).save(consumer, Redux.locate("infuse_and_charge_" + uninfused.getId().getPath()));
      infusionStacking(infused.get(), infused.get(), 8).save(consumer, Redux.locate("infuse_and_charge_" + infused.getId().getPath()));
  }


  protected static StackingRecipeBuilder infusionStacking(Item result, ItemLike ingredient) {
      return infusionStacking(result, ingredient, 0);
  }

  protected static StackingRecipeBuilder infusionStacking(Item result, ItemLike ingredient, int infusionAmount) {
      return StackingRecipeBuilder.recipe(Ingredient.of(ingredient), result, infusionAmount, ReduxRecipeSerializers.INFUSION.get());
  }

  public ICondition dc(AbstractCondition<?> condition) {

      return new DataRecipeCondition(condition);
  }

    public ConditionalAdvancement.Builder dataConditionAdvancement(AbstractCondition<?> condition, ResourceLocation id, ItemLike... ingredients) {
        ConditionalAdvancement.Builder builder = ConditionalAdvancement.builder()
                .addCondition(dc(condition));
        Advancement.Builder advBuilder = Advancement.Builder.advancement();
        for (ItemLike item : ingredients) {
        advBuilder.addCriterion("has_" + ForgeRegistries.ITEMS.getKey(item.asItem()).getPath(), has(item.asItem()));
        }

        advBuilder.addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id)).requirements(RequirementsStrategy.OR);
        builder.addAdvancement(advBuilder);
        return builder;
    }

    protected static SingleItemRecipeBuilder stonecut(RecipeCategory category, ItemLike item, ItemLike ingredient, int count) {
        return SingleItemRecipeBuilder.stonecutting(Ingredient.of(ingredient), category, item, count).unlockedBy(getHasName(ingredient), has(ingredient));
    }

    protected static SingleItemRecipeBuilder stonecut(RecipeCategory category, ItemLike item, ItemLike ingredient)
    {
        return stonecut(category, item, ingredient, 1);
    }
    public ResourceLocation advLoc(ItemLike result, RecipeCategory category)
    {
        return advLoc(ForgeRegistries.ITEMS.getKey(result.asItem()), result, category);
    }
    public ResourceLocation advLoc(ResourceLocation loc, ItemLike result, RecipeCategory category)
    {
        return new ResourceLocation(loc.getNamespace(), "recipes/" + category.getFolderName() + "/" + loc.getPath());
    }


    protected static ShapelessRecipeBuilder gummySwet(Supplier<? extends ItemLike> gummy, Supplier<? extends ItemLike> ball) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, gummy.get())
                .group("gummy_swet")
                .requires(ball.get(), 3)
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ball.get()), has(ball.get()));
    }



    protected static ShapelessRecipeBuilder swetJelly(Supplier<? extends ItemLike> jelly, Supplier<? extends ItemLike> ball) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, jelly.get())
                .requires(ball.get())
                .requires(Items.SUGAR)
                .unlockedBy(getHasName(ball.get()), has(ball.get()));
    }

    protected static ShapelessRecipeBuilder swetJelly(Supplier<? extends ItemLike> jelly, TagKey<Item> ball) {
        ResourceKey registry = null;
        Object o = ObfuscationReflectionHelper.getPrivateValue(TagKey.class, ball, "registry");
        if (o instanceof ResourceKey registryObj)
        {
            registry = registryObj;
        }
        else throw new IllegalArgumentException("Reflection value did not return expected type!");
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, jelly.get())
                .requires(ball)
                .requires(Items.SUGAR)
                .unlockedBy("has_" + registry.location().getPath(), has(ball));
    }

    protected static ShapelessRecipeBuilder gummySwet(Supplier<? extends ItemLike> gummy, TagKey<Item> ball, String hasName) {
        return ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, gummy.get())
                .group("gummy_swet")
                .requires(Ingredient.of(ball), 3)
                .requires(Items.SUGAR)
                .unlockedBy(hasName, has(ball));
    }

    protected static RecipeBuilder wallNoBuild(RecipeCategory pCategory, ItemLike pWall, ItemLike pMaterial) {
        return wallBuilder(pCategory, pWall, Ingredient.of(pMaterial)).unlockedBy(getHasName(pMaterial), has(pMaterial));
    }

    protected static ShapedRecipeBuilder swetBall(Supplier<? extends ItemLike> ball, Supplier<? extends ItemLike> jelly) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ball.get())
                .define('J', jelly.get())
                .pattern("JJ")
                .pattern("JJ")
                .unlockedBy(getHasName(jelly.get()), has(jelly.get()));
    }
    protected static ShapedRecipeBuilder swetBall(Supplier<? extends ItemLike> ball, TagKey<Item> jelly) {
        ResourceKey registry = null;
        Object o = ObfuscationReflectionHelper.getPrivateValue(TagKey.class, jelly, "registry");
        if (o instanceof ResourceKey registryObj)
        {
            registry = registryObj;
        }
        else throw new IllegalArgumentException("Reflection value did not return expected type!");
        return ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ball.get())
                .define('J', jelly)
                .pattern("JJ")
                .pattern("JJ")
                .unlockedBy("has_" + registry.location().getPath(), has(jelly));
    }

    protected static RecipeBuilder skyrootSignBuilder(ItemLike pSign, Ingredient pMaterial) {
        return ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, pSign, 3).group("sign").define('P', pMaterial).define('/', Tags.Items.RODS_WOODEN).pattern("PPP").pattern("PPP").pattern(" / ");
    }

    protected static void hangingAetherSign(Consumer<FinishedRecipe> finishedRecipeConsumer, ItemLike sign, ItemLike material) {
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, sign, 6).group("hanging_sign").define('#', material).define('X', ReduxTags.Items.CHAINS).pattern("X X").pattern("###").pattern("###").unlockedBy("has_stripped_log", has(material)).save(finishedRecipeConsumer);
    }


    protected static void bookshelf(Consumer<FinishedRecipe> consumer, ItemLike plank, ItemLike bookshelf)
    {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, bookshelf, 1)
                .define('P', plank)
                .define('B', Items.BOOK)
                .pattern("PPP")
                .pattern("BBB")
                .pattern("PPP")
                .unlockedBy(getHasName(Items.BOOK), has(Items.BOOK))
                .save(consumer, getItemName(plank) + "_to_skyroot_bookshelf");
    }

    protected static KilledTrigger.TriggerInstance killed(EntityType entityType) {
        return KilledTrigger.TriggerInstance.playerKilledEntity(EntityPredicate.Builder.entity().of(entityType));
    }

    static ResourceLocation getDefaultRecipeId(ItemLike pItemLike) {
        return BuiltInRegistries.ITEM.getKey(pItemLike.asItem());
    }








}