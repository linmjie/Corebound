package com.linmjie.corebound.util;

import com.linmjie.corebound.Corebound;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> DROPS_ROCKS = createTag("drops_rocks");

        private static net.minecraft.tags.TagKey<Block> createTag(String name){
            return BlockTags.create(ResourceLocation.fromNamespaceAndPath(Corebound.MODID, name));
        }
    }

    public static class Items {
        public static final TagKey<Item> ROCKS_REPLACE = createTag("rocks_replace");
        public static final TagKey<Item> NON_STRIPPED_LOGS_THAT_BURN = createTag("non_stripped_logs_that_burn");
        public static final TagKey<Item> STRIPPED_LOGS_THAT_BURN = createTag("stripped_logs_that_burn");
        public static final TagKey<Item> ROCK_ADJACENT = createTag("rock_adjacent");

        private static net.minecraft.tags.TagKey<Item> createTag(String name){
            return ItemTags.create(ResourceLocation.fromNamespaceAndPath(Corebound.MODID, name));
        }
    }
}
