package net.zepalesque.redux.loot.condition;

import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import net.zepalesque.redux.Redux;

public class ReduxLootConditions {
    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES = DeferredRegister.create(Registry.LOOT_CONDITION_TYPE.key(), Redux.MODID);
    public static final RegistryObject<LootItemConditionType> DATA_CONDITION = LOOT_CONDITION_TYPES.register("data_loot_condition", () -> new LootItemConditionType(new DataLootCondition.Serializer()));

}
