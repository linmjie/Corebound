ServerEvents.tags('item', event => {
    const hideFromEMI = [];
    Array.prototype.push.apply(hideFromEMI, global.deletedItems);

    hideFromEMI.forEach(id => {
        event.add('c:hidden_from_recipe_viewers', id);
    });
});
