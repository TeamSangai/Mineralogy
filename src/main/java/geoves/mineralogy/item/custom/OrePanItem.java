package geoves.mineralogy.item.custom;

import geoves.mineralogy.Mineralogy;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.component.type.AttributeModifiersComponent;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

public class OrePanItem extends Item {
    public OrePanItem(Settings settings) {
        super(settings);
    }
    public static AttributeModifiersComponent createAttributeModifiers() {
        return AttributeModifiersComponent.builder().add(EntityAttributes.ARMOR, new EntityAttributeModifier(Identifier.of(Mineralogy.MOD_ID, "copper_pan"), 2.0F,
                EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.HEAD).add(EntityAttributes.ATTACK_DAMAGE,
                new EntityAttributeModifier(BASE_ATTACK_DAMAGE_MODIFIER_ID, 6.0F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).
                add(EntityAttributes.ATTACK_SPEED, new EntityAttributeModifier(BASE_ATTACK_SPEED_MODIFIER_ID, -2.8F, EntityAttributeModifier.Operation.ADD_VALUE), AttributeModifierSlot.MAINHAND).build();
    }
}
