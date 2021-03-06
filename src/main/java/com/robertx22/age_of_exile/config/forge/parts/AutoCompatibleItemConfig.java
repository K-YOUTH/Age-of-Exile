package com.robertx22.age_of_exile.config.forge.parts;

import com.robertx22.age_of_exile.uncommon.interfaces.data_items.IRarity;
import me.sargunvohra.mcmods.autoconfig1u.annotation.ConfigEntry;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.List;

public class AutoCompatibleItemConfig {

    public boolean ENABLE_AUTOMATIC_COMPATIBLE_ITEMS = false;

    public List<String> BLACKLISTED_MODS_FROM_AUTO_CONFIG = new ArrayList<>();
    public List<String> BLACKLISTED_ITEMS_FROM_AUTO_CONFIG = new ArrayList<>();

    public boolean isBlacklisted(Item item) {
        Identifier id = Registry.ITEM.getId(item);

        return BLACKLISTED_MODS_FROM_AUTO_CONFIG.contains(id.getNamespace())
            || BLACKLISTED_ITEMS_FROM_AUTO_CONFIG.contains(id.toString());
    }

    public int MAX_SINGLE_STAT_VALUE = 50;
    public int MAX_TOTAL_STATS = 200;

    @ConfigEntry.Gui.CollapsibleObject
    public AutoConfigItemType TIER_0 = new AutoConfigItemType(0, 0.15F, 10).maxRarity(IRarity.Common)
        .noSalvage();
    @ConfigEntry.Gui.CollapsibleObject
    public AutoConfigItemType TIER_1 = new AutoConfigItemType(0.15F, 0.25F, 10).noSalvage();
    @ConfigEntry.Gui.CollapsibleObject
    public AutoConfigItemType TIER_2 = new AutoConfigItemType(0.25F, 0.5F, 20);
    @ConfigEntry.Gui.CollapsibleObject
    public AutoConfigItemType TIER_3 = new AutoConfigItemType(0.5F, 0.8F, 30);
    @ConfigEntry.Gui.CollapsibleObject
    public AutoConfigItemType TIER_4 = new AutoConfigItemType(0.8F, 0.9F, 40);
    @ConfigEntry.Gui.CollapsibleObject
    public AutoConfigItemType TIER_5 = new AutoConfigItemType(0.9F, 1F, 50);

}
