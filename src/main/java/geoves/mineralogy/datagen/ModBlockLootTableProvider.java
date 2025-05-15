package geoves.mineralogy.datagen;

import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.loot.LootTable;


import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.entry.LeafEntry;
import net.minecraft.loot.function.ApplyBonusLootFunction;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;


import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public ModBlockLootTableProvider(FabricDataOutput dataOutput, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }


    @Override
    public void generate() {
        addDrop(ModBlocks.FREEZER_BLOCK);
        addDrop(ModBlocks.SLAG_FURNACE_BLOCK);
        addDrop(ModBlocks.COPPER_SLAG_BLOCK);
        addDrop(ModBlocks.PAYDIRT_DRY, Blocks.DIRT);
        addDrop(ModBlocks.COPPER_SLAG_COOLED_BLOCK, multipleOreDrops(ModBlocks.COPPER_SLAG_COOLED_BLOCK, ModItems.COPPER_NUGGET, 3,8));
        addDrop(ModBlocks.GOLD_SLAG_BLOCK);
        addDrop(ModBlocks.GOLD_SLAG_COOLED_BLOCK, multipleOreDrops(ModBlocks.GOLD_SLAG_COOLED_BLOCK, Items.GOLD_NUGGET, 4,12));
        addDrop(ModBlocks.IRON_SLAG_BLOCK);
        addDrop(ModBlocks.IRON_SLAG_COOLED_BLOCK, multipleOreDrops(ModBlocks.IRON_SLAG_COOLED_BLOCK, Items.IRON_NUGGET, 2,7));
    }
    public LootTable.Builder multipleOreDrops(Block drop, Item item, float minDrops, float maxDrops) {
        RegistryWrapper.Impl<Enchantment> impl = this.registries.getOrThrow(RegistryKeys.ENCHANTMENT);
        return this.dropsWithSilkTouch(drop, this.applyExplosionDecay(drop, ((LeafEntry.Builder<?>)
                        ItemEntry.builder(item).apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(minDrops, maxDrops)))))
                .apply(ApplyBonusLootFunction.oreDrops(impl.getOrThrow(Enchantments.FORTUNE))));
    }

}
