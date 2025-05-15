package geoves.mineralogy.block.entity;

import geoves.mineralogy.Mineralogy;
import geoves.mineralogy.block.ModBlocks;
import geoves.mineralogy.block.entity.custom.FreezerBlockEntity;
import geoves.mineralogy.block.entity.custom.SlagFurnaceBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlockEntities {
    public static final BlockEntityType<FreezerBlockEntity> FREEZER_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Mineralogy.MOD_ID, "freezer_be"),
                    FabricBlockEntityTypeBuilder.create(FreezerBlockEntity::new, ModBlocks.FREEZER_BLOCK).build());
    public static final BlockEntityType<SlagFurnaceBlockEntity> SLAG_FURNACE_BE =
            Registry.register(Registries.BLOCK_ENTITY_TYPE, Identifier.of(Mineralogy.MOD_ID, "slag_furnace_be"),
                    FabricBlockEntityTypeBuilder.create(SlagFurnaceBlockEntity::new, ModBlocks.SLAG_FURNACE_BLOCK).build());

    public static void registerBlockEntities() {
        Mineralogy.LOGGER.info("Registering Block Entitiyes for " + Mineralogy.MOD_ID);
    }
}
