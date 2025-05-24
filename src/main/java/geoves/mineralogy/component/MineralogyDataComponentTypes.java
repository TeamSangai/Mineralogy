package geoves.mineralogy.component;

import geoves.mineralogy.Mineralogy;
import net.minecraft.component.ComponentType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

import java.util.function.UnaryOperator;

public class MineralogyDataComponentTypes {

    public static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Mineralogy.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void  registerMineralogyDataComponentTypes() {

    }
}
