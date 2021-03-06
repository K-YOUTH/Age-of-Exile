package com.robertx22.age_of_exile.database.data.stats.types.misc;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.data.stats.StatScaling;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;

public class ExtraMobDropsStat extends Stat {
    public static String GUID = "extra_mob_drops";

    public static ExtraMobDropsStat getInstance() {
        return ExtraMobDropsStat.SingletonHolder.INSTANCE;
    }

    @Override
    public StatScaling getScaling() {
        return StatScaling.NONE;
    }

    @Override
    public StatGroup statGroup() {
        return StatGroup.Misc;
    }

    private ExtraMobDropsStat() {
        this.minimumValue = -100;
    }

    @Override
    public String GUID() {
        return GUID;
    }

    @Override
    public Elements getElement() {
        return null;
    }

    @Override
    public boolean IsPercent() {
        return true;
    }

    @Override
    public String locNameForLangFile() {
        return "Extra Mob Drops";
    }

    @Override
    public String locDescForLangFile() {
        return "Mobs with this stat drop extra loot when slain.";
    }

    private static class SingletonHolder {
        private static final ExtraMobDropsStat INSTANCE = new ExtraMobDropsStat();
    }
}

