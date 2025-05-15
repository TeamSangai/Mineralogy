package geoves.mineralogy.item;

import geoves.mineralogy.Mineralogy;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModItems {
    public static final Item COPPER_PAN = registerItem("copper_pan", new Item.Settings());
    public static final Item COPPER_NUGGET = registerItem("copper_nugget", new Item.Settings());


    private static Item registerItem(String name, Item.Settings itemSettings) {
        RegistryKey<Item> key = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mineralogy.MOD_ID, name));
        Item item = new Item(itemSettings.registryKey(key));
        return Registry.register(Registries.ITEM, key, item);
    }
    public static void registerModItems() {
        Mineralogy.LOGGER.info("Registering Mod Items for " + Mineralogy.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->{
            entries.add(COPPER_NUGGET);
                });
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries ->{
            entries.add(COPPER_PAN);
        });
    }
}
