package com.linmjie.corebound.loot;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemConditionType;

public record LootItemBlockTagPropertyCondition(TagKey<Block> blockTag) implements LootItemCondition {
    public static final MapCodec<LootItemBlockTagPropertyCondition> CODEC = RecordCodecBuilder.
            mapCodec((instance) -> instance.group(
                    TagKey.codec(Registries.BLOCK)
                    .fieldOf("block_tag").forGetter(LootItemBlockTagPropertyCondition::blockTag)).
                    apply(instance, LootItemBlockTagPropertyCondition::new));

    @Override
    public LootItemConditionType getType() {
        return ModLootRegistries.BLOCK_TAG.get();
    }

    @Override
    public boolean test(LootContext lootContext) {
        BlockState blockstate = lootContext.getParamOrNull(LootContextParams.BLOCK_STATE);
        return blockstate != null && blockstate.is(this.blockTag);
    }
}
