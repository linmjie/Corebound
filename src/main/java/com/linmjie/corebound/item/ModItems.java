package com.linmjie.corebound.item;

import com.linmjie.corebound.Corebound;
import com.linmjie.corebound.item.custom.LoggerItem;
import com.linmjie.corebound.item.custom.ModToolTiers;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Tiers;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Corebound.MODID);

    public static final DeferredItem<LoggerItem> LOGGER_AXE = ITEMS.register("logger_axe",
            () -> new LoggerItem(ModToolTiers.IRON, new Item.Properties()
                    .attributes(AxeItem.createAttributes(ModToolTiers.IRON,3F, 1F))));


}