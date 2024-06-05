package net.zepalesque.redux.data.gen;

import com.aetherteam.aether.block.AetherBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.block.ReduxBlocks;
import net.zepalesque.redux.data.prov.ReduxItemModelProvider;

public class ReduxItemModelGen extends ReduxItemModelProvider {

    public ReduxItemModelGen(PackOutput output, ExistingFileHelper helper) {
        super(output, Redux.MODID, helper);
    }

    @Override
    protected void registerModels() {
        Redux.WOOD_SETS.forEach(set -> set.itemData(this));
        Redux.STONE_SETS.forEach(set -> set.itemData(this));

        itemBlockFlatCustomTexture(ReduxBlocks.SHORT_AETHER_GRASS, "natural/aether_medium_grass");
        this.itemBlock(ReduxBlocks.CLOUDROOT_LEAVES.get());
        this.itemBlockFlat(ReduxBlocks.CLOUDROOT_SAPLING.get(), "natural/");

        this.itemBlock(ReduxBlocks.CARVED_STONE_BASE.get());
        this.itemBlock(ReduxBlocks.CARVED_STONE_PILLAR.get());
        this.itemBlock(ReduxBlocks.SENTRY_STONE_BASE.get());
        this.itemBlock(ReduxBlocks.SENTRY_STONE_PILLAR.get());

        this.itemOverlayColumn(ReduxBlocks.LOCKED_CARVED_STONE_BASE.get(), ReduxBlocks.CARVED_STONE_BASE.get(), "dungeon/lock", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.LOCKED_CARVED_STONE_PILLAR.get(), ReduxBlocks.CARVED_STONE_PILLAR.get(), "dungeon/lock", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.LOCKED_SENTRY_STONE_BASE.get(), ReduxBlocks.SENTRY_STONE_BASE.get(), "dungeon/lock", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.LOCKED_SENTRY_STONE_PILLAR.get(), ReduxBlocks.SENTRY_STONE_PILLAR.get(), "dungeon/lock", "dungeon/");

        this.itemOverlayColumn(ReduxBlocks.TRAPPED_CARVED_STONE_BASE.get(), ReduxBlocks.CARVED_STONE_BASE.get(), "dungeon/exclamation", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.TRAPPED_CARVED_STONE_PILLAR.get(), ReduxBlocks.CARVED_STONE_PILLAR.get(), "dungeon/exclamation", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.TRAPPED_SENTRY_STONE_BASE.get(), ReduxBlocks.SENTRY_STONE_BASE.get(), "dungeon/exclamation", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.TRAPPED_SENTRY_STONE_PILLAR.get(), ReduxBlocks.SENTRY_STONE_PILLAR.get(), "dungeon/exclamation", "dungeon/");

        this.itemOverlayColumn(ReduxBlocks.BOSS_DOORWAY_CARVED_STONE_BASE.get(), ReduxBlocks.CARVED_STONE_BASE.get(), "dungeon/door", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.BOSS_DOORWAY_CARVED_STONE_PILLAR.get(), ReduxBlocks.CARVED_STONE_PILLAR.get(), "dungeon/door", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.BOSS_DOORWAY_SENTRY_STONE_BASE.get(), ReduxBlocks.SENTRY_STONE_BASE.get(), "dungeon/door", "dungeon/");
        this.itemOverlayColumn(ReduxBlocks.BOSS_DOORWAY_SENTRY_STONE_PILLAR.get(), ReduxBlocks.SENTRY_STONE_PILLAR.get(), "dungeon/door", "dungeon/");

        this.itemBlock(ReduxBlocks.RUNELIGHT.get(), "_on");
        this.itemOverlayDungeonBlock(ReduxBlocks.LOCKED_RUNELIGHT.get(), ReduxBlocks.RUNELIGHT.get(), "_on");
    }

}