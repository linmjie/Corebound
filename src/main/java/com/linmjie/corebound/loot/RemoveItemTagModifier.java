package com.linmjie.corebound.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import it.unimi.dsi.fastutil.objects.ObjectArrayList;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.common.loot.LootModifier;

public class RemoveItemTagModifier extends LootModifier {
    public static final MapCodec<RemoveItemTagModifier> CODEC = RecordCodecBuilder.mapCodec(inst ->
            LootModifier.codecStart(inst).and(
                    TagKey.codec(Registries.ITEM).fieldOf("item_tag").forGetter(e -> e.itemTag))
                    .apply(inst, RemoveItemTagModifier::new));
    private final TagKey<Item> itemTag;

    public RemoveItemTagModifier(LootItemCondition[] conditionsIn, TagKey<Item> itemTag) {
        super(conditionsIn);
        this.itemTag = itemTag;
    }

    @Override
    protected ObjectArrayList<ItemStack> doApply(ObjectArrayList<ItemStack> generatedLoot, LootContext lootContext) {
        for (LootItemCondition condition : this.conditions) {
            if(!condition.test(lootContext)) {
                return generatedLoot;
            }
        }
        generatedLoot.removeIf(e -> e.is(itemTag));
        return generatedLoot;
    }

    @Override
    public MapCodec<? extends IGlobalLootModifier> codec() {
        return CODEC;
    }
}
