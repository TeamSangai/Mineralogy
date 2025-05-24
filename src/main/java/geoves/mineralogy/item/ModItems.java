package geoves.mineralogy.item;

import geoves.mineralogy.Mineralogy;
import geoves.mineralogy.item.custom.OrePanItem;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.dispenser.DispenserBehavior;
import net.minecraft.client.realms.exception.upload.TooBigRealmsUploadException;
import net.minecraft.client.render.Camera;
import net.minecraft.component.ComponentType;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.component.type.EquippableComponent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.item.equipment.EquipmentAsset;
import net.minecraft.item.equipment.EquipmentAssetKeys;
import net.minecraft.item.equipment.EquipmentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.entry.RegistryEntryList;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;


public class ModItems {
    public static final Item COPPER_PAN = registerItem(
            "copper_pan",
            new Item.Settings().maxDamage(320).attributeModifiers(OrePanItem.createAttributeModifiers()).component(DataComponentTypes.EQUIPPABLE, EquippableComponent.builder(EquipmentSlot.HEAD).equipSound(SoundEvents.ITEM_ARMOR_EQUIP_GENERIC)
                    .damageOnHurt(true).swappable(false).equipOnInteract(false).dispensable(true).build()).repairable(Items.COPPER_INGOT));
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
