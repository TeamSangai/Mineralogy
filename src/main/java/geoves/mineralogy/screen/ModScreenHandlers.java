package geoves.mineralogy.screen;

import geoves.mineralogy.Mineralogy;
import geoves.mineralogy.screen.custom.CoolerScreenHandler;
import geoves.mineralogy.screen.custom.FreezerScreenHandler;
import geoves.mineralogy.screen.custom.SlagFurnaceScreenHandler;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;

public class ModScreenHandlers {
    public static final ScreenHandlerType<FreezerScreenHandler> FREEZER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Mineralogy.MOD_ID, "freezer_screen_handler"),
                    new ExtendedScreenHandlerType<>(FreezerScreenHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<SlagFurnaceScreenHandler> SLAG_FURNACE_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Mineralogy.MOD_ID, "slag_furnace_screen_handler"),
                    new ExtendedScreenHandlerType<>(SlagFurnaceScreenHandler::new, BlockPos.PACKET_CODEC));
    public static final ScreenHandlerType<CoolerScreenHandler> COOLER_SCREEN_HANDLER =
            Registry.register(Registries.SCREEN_HANDLER, Identifier.of(Mineralogy.MOD_ID, "cooler_screen_handler"),
                    new ExtendedScreenHandlerType<>(CoolerScreenHandler::new, BlockPos.PACKET_CODEC));


    public static void registerScreenHandlers() {
        Mineralogy.LOGGER.info("Registering Screen Handlers for " + Mineralogy.MOD_ID);
    }
}