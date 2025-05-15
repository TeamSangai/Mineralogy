package geoves.mineralogy.block;

import geoves.mineralogy.Mineralogy;
import geoves.mineralogy.block.custom.FreezerBlock;
import geoves.mineralogy.block.custom.PaydirtDry;
import geoves.mineralogy.block.custom.SlagBlockAbstract;
import geoves.mineralogy.block.custom.SlagFurnaceBlock;
import net.fabricmc.fabric.api.block.v1.FabricBlock;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;

public class ModBlocks {

    public static final Block COPPER_SLAG_COOLED_BLOCK = registerBlock( "copper_slag_cooled_block",
            new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "copper_slag_cooled_block"))).strength(2.0f, 2.5f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));
    public static final Block COPPER_SLAG_BLOCK = registerBlock( "copper_slag_block",
            new SlagBlockAbstract(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "copper_slag_block"))).strength(1.5f, 1.5f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));
    public static final Block IRON_SLAG_COOLED_BLOCK = registerBlock( "iron_slag_cooled_block",
            new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "iron_slag_cooled_block"))).strength(3.0f, 3.5f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));
    public static final Block IRON_SLAG_BLOCK = registerBlock( "iron_slag_block",
            new SlagBlockAbstract(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "iron_slag_block"))).strength(2.5f, 3.5f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));
    public static final Block GOLD_SLAG_COOLED_BLOCK = registerBlock( "gold_slag_cooled_block",
            new Block(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "gold_slag_cooled_block"))).strength(2.0f, 2.5f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));
    public static final Block GOLD_SLAG_BLOCK = registerBlock( "gold_slag_block",
            new SlagBlockAbstract(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "gold_slag_block"))).strength(1.5f, 2.5f)
                    .requiresTool().sounds(BlockSoundGroup.GILDED_BLACKSTONE)));
    public static final Block PAYDIRT_DRY = registerBlock( "paydirt_dry",
            new PaydirtDry(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "paydirt_dry"))).strength(0.7f, 1f)
                    .requiresTool().sounds(BlockSoundGroup.GRAVEL)));
    public static final Block FREEZER_BLOCK = registerBlock( "freezer_block",
            new FreezerBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "freezer_block"))).strength(1f, 1f)));
    public static final Block SLAG_FURNACE_BLOCK = registerBlock( "slag_furnace_block",
            new SlagFurnaceBlock(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, "slag_furnace_block"))).strength(2f, 2f)));

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(Mineralogy.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Mineralogy.MOD_ID, name),
        new BlockItem(block, new Item.Settings()
                .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Mineralogy.MOD_ID, name))).useBlockPrefixedTranslationKey()));
    }

    public static void registerModBlocks() {
        Mineralogy.LOGGER.info("Registering Mod Blocks for " + Mineralogy.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(ModBlocks.COPPER_SLAG_BLOCK);
            entries.add(ModBlocks.COPPER_SLAG_COOLED_BLOCK);
            entries.add(ModBlocks.PAYDIRT_DRY);
            entries.add(ModBlocks.IRON_SLAG_BLOCK);
            entries.add(ModBlocks.IRON_SLAG_COOLED_BLOCK);
            entries.add(ModBlocks.GOLD_SLAG_BLOCK);
            entries.add(ModBlocks.GOLD_SLAG_COOLED_BLOCK);
        });
    }
}
