package com.linmjie.corebound.loot;

import com.linmjie.corebound.Corebound;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootRegistries {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIER_SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, Corebound.MODID);

    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> ADD_ITEM =
            LOOT_MODIFIER_SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);
    public static final Supplier<MapCodec<? extends IGlobalLootModifier>> REMOVE_ITEM_FROM_TAG =
            LOOT_MODIFIER_SERIALIZERS.register("remove_item_from_tag", () -> RemoveItemTagModifier.CODEC);



    public static final DeferredRegister<LootItemConditionType> LOOT_CONDITION_TYPES =
            DeferredRegister.create(Registries.LOOT_CONDITION_TYPE, Corebound.MODID);

    public static final Supplier<LootItemConditionType> BLOCK_TAG =
            LOOT_CONDITION_TYPES.register("block_tag_property",
                    () -> new LootItemConditionType(LootItemBlockTagPropertyCondition.CODEC));

}
