package net.zepalesque.redux.data.loot;

import com.aetherteam.aether.data.generators.loot.AetherEntityLoot;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.LootingEnchantFunction;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.functions.SmeltItemFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemEntityPropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;
import net.minecraftforge.registries.RegistryObject;
import net.zepalesque.redux.entity.ReduxEntityTypes;
import net.zepalesque.redux.item.ReduxItems;
import net.zepalesque.redux.loot.functions.SwetSizeFunction;

import java.util.stream.Stream;

public class ReduxEntityLootData extends AetherEntityLoot {


    @Override
    public void generate() {
        this.add(ReduxEntityTypes.VANILLA_SWET.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ReduxItems.VANILLA_SWET_BALL.get())
                                .apply(SwetSizeFunction.instance())
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );
        this.add(ReduxEntityTypes.SHIMMERCOW.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F))
                        .add(LootItem.lootTableItem(Items.LEATHER).apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))))
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(Items.BEEF)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 5.0F)))
                        .apply(SmeltItemFunction.smelted().when(LootItemEntityPropertyCondition.hasProperties(LootContext.EntityTarget.THIS, ENTITY_ON_FIRE)))
                        .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F))))));

        this.add(ReduxEntityTypes.MYKAPOD.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(Items.SLIME_BALL)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                        )
                )
        );

        this.add(ReduxEntityTypes.BLIGHTBUNNY.get(), LootTable.lootTable()
                .withPool(LootPool.lootPool().setRolls(ConstantValue.exactly(1))
                        .add(LootItem.lootTableItem(ReduxItems.BLIGHTBUNNY_FANG.get())
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 1.0F)))
                                .apply(LootingEnchantFunction.lootingMultiplier(UniformGenerator.between(0.0F, 1.0F)))
                                .when(LootItemRandomChanceCondition.randomChance(0.25F))
                        )
                ));
    }

    @Override
    public Stream<EntityType<?>> getKnownEntityTypes() {
        return ReduxEntityTypes.ENTITY_TYPES.getEntries().stream().filter(ReduxEntityLootData::filterSpawners).flatMap(entityType -> Stream.of(entityType.get()));
    }

    private static boolean filterSpawners(RegistryObject<EntityType<?>> reg) {
        return !ReduxEntityTypes.SPAWNERS.contains(reg);
    }
}
