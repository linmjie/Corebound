// Visit the wiki for more info - https://kubejs.com/
console.info('Hello, World! (Loaded startup example script)')

global.getTools = (namespace, material) => {
    return [
        namespace + ":" + material + "_sword",
        namespace + ":" + material + "_pickaxe",
        namespace + ":" + material + "_axe",
        namespace + ":" + material + "_shovel",
        namespace + ":" + material + "_hoe",
    ];
}

global.getArmor = (namespace, material) => {
    return [
        namespace + ":" + material + "_helmet",
        namespace + ":" + material + "_chestplate",
        namespace + ":" + material + "_leggings",
        namespace + ":" + material + "_boots",
    ];
}

global.getArmorAndTools = (namespace, material) => {
    // why does plus operator convert lists to strings js T_T
    // now i gotta do .concat :(
    return global.getTools(namespace, material).concat(global.getArmor(namespace, material));
}

global.deletedItems = [
    'tfmg:casting_basin'
];

Array.prototype.push.apply(global.deletedItems, global.getTools('tfmg', 'aluminum'));
Array.prototype.push.apply(global.deletedItems, global.getTools('tfmg', 'lead'));
Array.prototype.push.apply(global.deletedItems, global.getTools('tfmg', 'steel'));

global.deletedItems.forEach(item => {
    console.info('Removing item: ' + item);
});
