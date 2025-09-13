package com.linmjie.corebound.event;

import com.linmjie.corebound.Corebound;
import com.linmjie.corebound.item.custom.LoggerItem;
import com.linmjie.corebound.item.custom.SpearItem;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.ai.attributes.AttributeInstance;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.event.level.BlockEvent;
import net.neoforged.neoforge.event.tick.PlayerTickEvent;
import net.neoforged.neoforge.event.tick.ServerTickEvent;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EventBusSubscriber(modid = Corebound.MODID)
public class ModEvents {
    private static final Set<BlockPos> HARVESTED_BLOCKS = new HashSet<>();

    private static final AttributeModifier SPEAR_ENTITY_RANGE_MODIFIER =
            new AttributeModifier(ResourceLocation.withDefaultNamespace("spear_entity_range_modifier"),
                    1.5, AttributeModifier.Operation.ADD_VALUE);

    @SubscribeEvent
    public static void onLoggerUsage(BlockEvent.BreakEvent event) {
        Player player = event.getPlayer();
        ItemStack mainHandItem = player.getMainHandItem();

        if (mainHandItem.getItem() instanceof LoggerItem logger && player instanceof ServerPlayer serverPlayer) {
            BlockPos initialBlockPos = event.getPos();
            if (HARVESTED_BLOCKS.contains(initialBlockPos)) {
                return;
            }
            if (event.getLevel() instanceof Level level) {
                List<BlockPos> treeBlocks = LoggerItem.findTreeBlocks(level, initialBlockPos);
                if (treeBlocks.isEmpty()){
                    return;
                }
                else{
                    for (BlockPos pos : treeBlocks) {
                        if (pos == initialBlockPos || !logger.isCorrectToolForDrops(mainHandItem, event.getLevel().getBlockState(pos))) {
                            continue;
                        }

                        HARVESTED_BLOCKS.add(pos);
                        serverPlayer.gameMode.destroyBlock(pos);
                        HARVESTED_BLOCKS.remove(pos);
                    }
                }
            }
        }
    }

    //Event for giving player increased range for crits when holding a SpearItem
    @SubscribeEvent
    public static void onPlayerTickEvent(PlayerTickEvent.Pre event){
        Player player = event.getEntity();
        ItemStack mainHandItem = player.getMainHandItem();
        if (player instanceof ServerPlayer serverPlayer) {
            AttributeInstance attributeInstance = serverPlayer.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
            if (attributeInstance != null) {
                if (mainHandItem.getItem() instanceof SpearItem && !serverPlayer.onGround()) {
                    attributeInstance.addOrUpdateTransientModifier(SPEAR_ENTITY_RANGE_MODIFIER);
                } else {
                    attributeInstance.removeModifier(SPEAR_ENTITY_RANGE_MODIFIER);
                }
            }
        }
    }
}