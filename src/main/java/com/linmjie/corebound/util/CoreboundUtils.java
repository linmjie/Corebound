package com.linmjie.corebound.util;

import com.linmjie.corebound.item.ModItems;
import net.minecraft.world.item.Item;

public class CoreboundUtils {
    public static int randomInt(int i){
        return Math.toIntExact((long) Math.ceil(Math.random() * i));
    }
    public static int randomInt(int min, int max){
        return randomInt(max-min) + min;
    }

    //temporary
    public static int getDropQuantity(Item item){
        if (item == ModItems.ROCK.get()){
            return randomInt(2, 5);
        }
        return 1;
    }
}
