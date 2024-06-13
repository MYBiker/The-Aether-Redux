package net.zepalesque.redux.item;

import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
import net.minecraft.world.item.PickaxeItem;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.zepalesque.redux.Redux;
import net.zepalesque.redux.block.ReduxBlocks;
import net.zepalesque.redux.item.combat.ReduxItemTiers;
import net.zepalesque.redux.item.tools.VeridiumPickaxeItem;

import java.util.function.UnaryOperator;

public class ReduxItems {


    public static final UnaryOperator<Component> TOOLTIP_SHIFT_FOR_INFO = (component -> Screen.hasShiftDown() ? component : Component.translatable("gui.aether_redux.shift_info", Minecraft.getInstance().options.keyShift.getKey().getDisplayName().getString()).withStyle(ChatFormatting.DARK_GRAY));


    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Redux.MODID);

    public static final DeferredItem<Item> WYND_OATS = ITEMS.register("wynd_oats", () -> new ItemNameBlockItem(ReduxBlocks.WYNDOATS.get(), new Item.Properties().food(ReduxFoods.WYND_OATS)));
    public static final DeferredItem<Item> WYND_OAT_PANICLE = ITEMS.register("wynd_oat_panicle", () -> new Item(new Item.Properties()));


    public static final DeferredItem<Item> RAW_VERIDIUM = ITEMS.register("raw_veridium", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VERIDIUM_INGOT = ITEMS.register("veridium_ingot", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> VERIDIUM_NUGGET = ITEMS.register("veridium_nugget", () -> new Item(new Item.Properties()));

    public static final DeferredItem<Item> REFINED_SENTRITE = ITEMS.register("refined_sentrite", () -> new Item(new Item.Properties()));
    public static final DeferredItem<Item> SENTRITE_CHUNK = ITEMS.register("sentrite_chunk", () -> new Item(new Item.Properties()));

    public static final DeferredItem<VeridiumPickaxeItem.Uninfused> VERIDIUM_PICKAXE = ITEMS.register("veridium_pickaxe", () -> new VeridiumPickaxeItem.Uninfused(ReduxItemTiers.VERIDIUM, 0, -2.9F, new Item.Properties()));
    public static final DeferredItem<VeridiumPickaxeItem> INFUSED_VERIDIUM_PICKAXE = ITEMS.register("infused_veridium_pickaxe", () -> new VeridiumPickaxeItem(ReduxItemTiers.INFUSED_VERIDIUM, 1, -2.6F, new Item.Properties()));
}
