package com.linmjie.corebound.item.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.*;

public class LoggerItem extends DiggerItem {
    public LoggerItem(Tier pTier, Properties pProperties) {
        super(pTier, BlockTags.MINEABLE_WITH_AXE, pProperties);
    }
    public static List<BlockPos> findTreeBlocks(Level level, BlockPos startPos) {
        List<BlockPos> treeBlocks = new ArrayList<>();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> toVisit = new LinkedList<>();
        boolean leafAdjacent = false;

        // Add starting position
        toVisit.add(startPos);

        // Directions for adjacency (6 directions for cubic blocks)
        BlockPos[] directions = new BlockPos[]{
                new BlockPos(-1, -1, -1), new BlockPos(-1, -1, 0), new BlockPos(-1, -1, 1),
                new BlockPos(0, -1, -1), new BlockPos(0, -1, 0), new BlockPos(0, -1, 1),
                new BlockPos(1, -1, -1), new BlockPos(1, -1, 0), new BlockPos(1, -1, 1),

                new BlockPos(-1, 0, -1), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1),
                new BlockPos(0, 0, -1),             /* Exclude (0, 0, 0) */ new BlockPos(0, 0, 1),
                new BlockPos(1, 0, -1), new BlockPos(1, 0, 0), new BlockPos(1, 0, 1),

                new BlockPos(-1, 1, -1), new BlockPos(-1, 1, 0), new BlockPos(-1, 1, 1),
                new BlockPos(0, 1, -1), new BlockPos(0, 1, 0), new BlockPos(0, 1, 1),
                new BlockPos(1, 1, -1), new BlockPos(1, 1, 0), new BlockPos(1, 1, 1)
        };

        // BFS loop
        while (!toVisit.isEmpty()) {
            BlockPos currentPos = toVisit.poll();
            if (!visited.contains(currentPos)) {
                visited.add(currentPos);

                // Check if the block at the current position is wood
                BlockState state = level.getBlockState(currentPos);
                if (state.getBlock().defaultBlockState().is(BlockTags.LOGS)) {
                    treeBlocks.add(currentPos);

                    // Add all adjacent blocks to the queue
                    for (BlockPos direction : directions) {
                        BlockPos neighbor = currentPos.offset(direction);
                        if (!visited.contains(neighbor)) {
                            toVisit.add(neighbor);
                        }
                    }
                }
                else if(state.getBlock().defaultBlockState().is(BlockTags.LEAVES)){
                    leafAdjacent = true;
                }
            }
        }
        if (leafAdjacent){
            return treeBlocks;
        }
        else{
            return new ArrayList<>();
        }


    }
}
