package com.linmjie.corebound.datagen;

import com.linmjie.corebound.Corebound;
import com.linmjie.corebound.item.ModItems;
import com.linmjie.corebound.loot.AddItemModifier;
import com.linmjie.corebound.loot.LootItemBlockTagPropertyCondition;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.predicates.MatchTool;
import net.neoforged.neoforge.common.data.GlobalLootModifierProvider;

import java.util.concurrent.CompletableFuture;

public class ModGlobalLootModifierProvider extends GlobalLootModifierProvider {
    public ModGlobalLootModifierProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, Corebound.MODID);
    }

    @Override
    protected void start() {
        this.add("leaves_to_twigs",
                new AddItemModifier(new LootItemCondition[]{
                        LootItemBlockTagPropertyCondition.matchesBlockTag(BlockTags.LEAVES).build(),
                        MatchTool.toolMatches(ItemPredicate.Builder.item().of(ModItems.WOODEN_SHEARS)).build(),
                        LootItemRandomChanceCondition.randomChance(1f).build() }, ModItems.TWIG.get()));
                };

}
