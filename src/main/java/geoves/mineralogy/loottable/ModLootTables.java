package geoves.mineralogy.loottable;

import geoves.mineralogy.Mineralogy;
import net.minecraft.loot.LootTable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;

public class ModLootTables {
    public static RegistryKey<LootTable> PAY_DIRT_LOOT = RegistryKey.of(RegistryKeys.LOOT_TABLE, Identifier.of(Mineralogy.MOD_ID, "pay_dirt/loot"));
}
