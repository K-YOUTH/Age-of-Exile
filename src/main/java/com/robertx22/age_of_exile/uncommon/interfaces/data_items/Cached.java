package com.robertx22.age_of_exile.uncommon.interfaces.data_items;

import com.robertx22.age_of_exile.database.data.MinMax;
import com.robertx22.age_of_exile.uncommon.utilityclasses.CompatibleItemUtils;
import net.minecraft.item.Item;

import java.util.HashMap;

public class Cached {

    public static Integer maxTier = null;

    public static HashMap<Item, CompatibleItemUtils.Data> COMPATIBLE_ITEMS = new HashMap<>();
    public static HashMap<String, MinMax> AREA_LEVEL_RANGE = new HashMap<>();

    public static void reset() {
        maxTier = null;

        COMPATIBLE_ITEMS.clear();
        AREA_LEVEL_RANGE.clear();
    }

}
