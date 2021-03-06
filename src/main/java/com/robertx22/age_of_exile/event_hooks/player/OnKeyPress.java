package com.robertx22.age_of_exile.event_hooks.player;

import com.robertx22.age_of_exile.gui.screens.main_hub.MainHubScreen;
import com.robertx22.age_of_exile.mmorpg.registers.client.KeybindsRegister;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;

public class OnKeyPress implements ClientTickEvents.EndTick {

    public static int cooldown = 0;

    @Override

    public void onEndTick(MinecraftClient mc) {

        if (cooldown > 0) {
            cooldown--;
            return;
        }

        if (mc.player == null) {
            return;
        }

        if (KeybindsRegister.HUB_SCREEN_KEY.isPressed()) {
            mc.openScreen(new MainHubScreen());
            cooldown = 10;
        } else {

        }

    }
}
