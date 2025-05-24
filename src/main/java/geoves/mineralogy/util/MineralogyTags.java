package geoves.mineralogy.util;

import geoves.mineralogy.Mineralogy;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

public class MineralogyTags {
    public static class Blocks {
        public static TagKey<Block> createTag(String name) {
            return TagKey.of(RegistryKeys.BLOCK, Identifier.of(Mineralogy.MOD_ID, name));
        }
    }
    public static class Items {

        public static final TagKey<Item> COPPER_PAN_REPAIR = createTag("copper_pan_repair");

        public static TagKey<Item> createTag(String name) {
            return TagKey.of(RegistryKeys.ITEM, Identifier.of(Mineralogy.MOD_ID, name));
        }
    }
}
