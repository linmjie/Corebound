package com.linmjie.corebound.datagen;

import com.linmjie.corebound.Corebound;
import com.linmjie.corebound.block.ModBlocks;
import com.linmjie.corebound.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput recipeOutput) {
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
        ShapelessRecipeBuilder.shapeless(RecipeCategory.BUILDING_BLOCKS, pPacked)
                .requires(pUnpacked, 9)
                .unlockedBy(getHasName(pUnpacked), has(pUnpacked))
                .save(pRecipeOutput, Corebound.MODID+":"+pPackedName+"_from_"+pUnpackedName);
        ShapelessRecipeBuilder.shapeless(recipeCategory, pUnpacked, 9)
                .requires(pPacked)
                .unlockedBy(getHasName(pPacked), has(pPacked))
                .save(pRecipeOutput, Corebound.MODID+":"+pUnpackedName+"_from_"+pPackedName);
    }
}
