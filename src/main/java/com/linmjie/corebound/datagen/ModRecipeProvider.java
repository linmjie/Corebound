package com.linmjie.corebound.datagen;

import com.linmjie.corebound.Corebound;
import com.linmjie.corebound.block.ModBlocks;
import com.linmjie.corebound.item.ModItems;
import com.linmjie.corebound.util.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ModItems.WOODEN_SHEARS.get())
                .pattern(" P ")
                .pattern("LIP")
                .pattern("IL ")
                .define('P', ItemTags.PLANKS)
                .define('L', ModTags.Items.NON_STRIPPED_LOGS_THAT_BURN)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ModItems.SHARP_STICK.get())
                .pattern(" TR")
                .pattern(" IT")
                .pattern("I  ")
                .define('T', ModItems.TWIG.get())
                .define('R', ModTags.Items.ROCK_ADJACENT)
                .define('I', Items.STICK)
                .unlockedBy(getHasName(ModItems.ROCK.get()), has(ModItems.ROCK.get()))
                .save(recipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, Items.COBBLESTONE)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', ModItems.ROCK)
                .unlockedBy(getHasName(ModItems.ROCK), has(ModItems.ROCK))
                .save(recipeOutput);

        ninePacker(recipeOutput, ModItems.RAW_TIN.get(), ModBlocks.RAW_TIN_BLOCk.get(), "tin");
    }

    //HELPER METHODS FOR STANDARDIZED RECIPE TYPES

    //ninePacker with default RecipeCategory.MISC and default "item_block" name for packed block
    protected static void ninePacker(RecipeOutput pRecipeOutput,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName) {
        ninePacker(pRecipeOutput, RecipeCategory.MISC, pUnpacked, pPacked,
                pUnpackedName, pUnpackedName+"_block");
    }
    //ninePacker with specified RecipeCategory and default "item_block" name for packed block
    protected static void ninePacker(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName) {
        ninePacker(pRecipeOutput, recipeCategory, pUnpacked, pPacked,
                pUnpackedName, pUnpackedName+"_block");
    }
    //ninePacker with default RecipeCategory.MISC & specified ids for unpacked and packed items
    protected static void ninePacker(RecipeOutput pRecipeOutput,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName, String pPackedName){
        ninePacker( pRecipeOutput, RecipeCategory.MISC, pUnpacked, pPacked,
                pUnpackedName, pPackedName);
    }
    protected static void ninePacker(RecipeOutput pRecipeOutput, RecipeCategory recipeCategory,
                                     ItemLike pUnpacked, ItemLike pPacked,
                                     String pUnpackedName, String pPackedName){
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, pPacked)
                .pattern("###")
                .pattern("###")
                .pattern("###")
                .define('#', pUnpacked)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pRecipeOutput, Corebound.MODID+":"+pPackedName+"_from_"+pUnpackedName);
        ShapelessRecipeBuilder.shapeless(recipeCategory, pUnpacked, 9)
                .requires(pPacked)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pRecipeOutput, Corebound.MODID+":"+pUnpackedName+"_from_"+pPackedName);
    }
}
