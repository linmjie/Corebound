ServerEvents.recipes(event => {
    // Remove by recipe input
    const removedRecipeInputs = [];
    Array.prototype.push.apply(removedRecipeInputs, global.deletedItems);

    const removedRecipeOutputs = [];
    Array.prototype.push.apply(removedRecipeOutputs, global.deletedItems);

    // Remove by recipe id
    // Cannot be done by {id: ['id1', 'id2', ...]}
    // Only by individual id, {id: 'id'}
    const removedRecipeIds = [
        'shyomusic:music_disc_fragment',
        'minecraft:campfire',
        'minecraft:crafting_table',
        'minecraft:wooden_sword',
        'minecraft:wooden_shovel',
        'minecraft:wooden_pickaxe',
        'minecraft:wooden_axe',
        'minecraft:wooden_hoe',
        'minecraft:stone_sword',
        'minecraft:stone_shovel',
        'minecraft:stone_pickaxe',
        'minecraft:stone_axe',
        'minecraft:stone_hoe',
    ];

    removedRecipeInputs.forEach(removedId => {
        event.remove({input: removedId});
    });

    removedRecipeOutputs.forEach(removedId => {
        event.remove({output: removedId});
    });

    removedRecipeIds.forEach(removedId => {
        event.remove({id: removedId});
    });
});
