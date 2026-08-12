ServerEvents.tags('item', event => {
    const hideFromEMI = [];
    Array.prototype.push.apply(hideFromEMI, global.deletedItems);
    event.add('c:hidden_from_recipe_viewers', "tfmg:casting_basin")
    event.add('c:hidden_from_recipe_viewers', "tfmg:steel_pickaxe")

    hideFromEMI.forEach(id => {
        event.add('c:hidden_from_recipe_viewers', id);
    });
});