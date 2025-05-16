package geoves.mineralogy;

import geoves.mineralogy.screen.ModScreenHandlers;
import geoves.mineralogy.screen.custom.FreezerScreen;
import geoves.mineralogy.screen.custom.SlagFurnaceScreen;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screen.ingame.HandledScreens;


import static geoves.mineralogy.screen.ModScreenHandlers.SLAG_FURNACE_SCREEN_HANDLER;

public class MineralogyClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        HandledScreens.register(ModScreenHandlers.FREEZER_SCREEN_HANDLER, FreezerScreen::new);
        HandledScreens.register(SLAG_FURNACE_SCREEN_HANDLER, SlagFurnaceScreen::new);
    }
}
