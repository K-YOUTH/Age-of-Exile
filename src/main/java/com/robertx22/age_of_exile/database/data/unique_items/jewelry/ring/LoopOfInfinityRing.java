package com.robertx22.age_of_exile.database.data.unique_items.jewelry.ring;

import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Intelligence;
import com.robertx22.age_of_exile.database.data.stats.types.reduced_req.FlatIncreasedReq;
import com.robertx22.age_of_exile.database.data.stats.types.resources.Mana;
import com.robertx22.age_of_exile.database.data.stats.types.resources.RegeneratePercentStat;
import com.robertx22.age_of_exile.database.data.unique_items.IUnique;
import com.robertx22.age_of_exile.database.registrators.BaseGearTypes;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

import java.util.Arrays;
import java.util.List;

public class LoopOfInfinityRing implements IUnique {

    @Override
    public List<StatModifier> uniqueStats() {
        return Arrays.asList(
            new StatModifier(5, 10, Mana.getInstance(), ModType.FLAT),
            new StatModifier(1, 4, RegeneratePercentStat.MANA, ModType.FLAT),
            new StatModifier(0.5F, 0.75F, new FlatIncreasedReq(Intelligence.INSTANCE), ModType.FLAT)
        );
    }

    @Override
    public String locDescForLangFile() {
        return "Is it truly an end if everything just starts all over again? Maybe it really is just a loop.";
    }

    @Override
    public String locNameForLangFile() {
        return "Loop of Infinity";
    }

    @Override
    public String GUID() {
        return "loop_of_infinity";
    }

    @Override
    public BaseGearType getBaseGearType() {
        return BaseGearTypes.MID_TO_END_HP_RING_MANA_REG;
    }
}

