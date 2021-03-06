package com.robertx22.age_of_exile.mixin_methods;

import com.robertx22.age_of_exile.gui.screens.spell_hotbar_setup.SpellHotbatSetupScreen;
import com.robertx22.age_of_exile.mmorpg.registers.client.KeybindsRegister;
import com.robertx22.age_of_exile.saveclasses.spells.SpellCastingData;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.options.GameOptions;
import net.minecraft.client.options.KeyBinding;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Arrays;

public class OnKeyMethod {

    public static boolean isSelectingSpells() {
        return KeybindsRegister.CHOOSE_SPELL_KEY.isPressed() || MinecraftClient.getInstance().currentScreen instanceof SpellHotbatSetupScreen;
    }

    public static void onKey(long window, int key, int scancode, CallbackInfo ci) {

        GameOptions options = MinecraftClient.getInstance().options;

        if (KeybindsRegister.CHOOSE_SPELL_KEY.isPressed()) {

            if (Arrays.stream(options.keysHotbar)
                .anyMatch(x -> x.matchesKey(key, scancode))) {

                KeyBinding[] bar = MinecraftClient.getInstance().options.keysHotbar;

                for (int i = 0; i < bar.length; i++) {
                    if (bar[i].matchesKey(key, scancode)) {
                        SpellCastingData.selectedSpell = i;
                        ci.cancel();
                        return;
                    }
                }
                ci.cancel();
            }
        }

    }
}
