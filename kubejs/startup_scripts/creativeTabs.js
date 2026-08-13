const allTabIds = [];

//i hope this event runs for all tabs before startup events lol
NativeEvents.onEvent(Java.loadClass('net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent'), event => {
    const tabId = event.tabKey.location();
    allTabIds.push(tabId);
});

allTabIds.forEach(tabId => {
    StartupEvents.modifyCreativeTab(tabId, event => {
        global.deletedItems.forEach(id => {
            event.remove(id);
        });
    })
});
