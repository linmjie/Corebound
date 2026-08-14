const TabVisibility = Java.loadClass('net.minecraft.world.item.CreativeModeTab$TabVisibility');

NativeEvents.onEvent(Java.loadClass('net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent'), event => {
    global.deletedItems.forEach(id => {
        let stack = Item.of(id)
        event.remove(stack, TabVisibility.PARENT_AND_SEARCH_TABS);
    });
});

