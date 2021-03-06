package com.robertx22.age_of_exile.database.data.rarities.containers;

import com.robertx22.age_of_exile.database.data.rarities.BaseRaritiesContainer;
import com.robertx22.age_of_exile.database.data.rarities.MobRarity;
import com.robertx22.age_of_exile.database.data.rarities.RarityTypeEnum;
import com.robertx22.age_of_exile.database.data.rarities.mobs.*;

import java.util.Arrays;
import java.util.List;

public class MobRarities extends BaseRaritiesContainer<MobRarity> {

    public MobRarities() {
        super();
        add(CommonMob.getInstance());
        add(UncommonMob.getInstance());
        add(RareMob.getInstance());
        add(EpicMob.getInstance());
        add(LegendaryMob.getInstance());
        add(BossMob.getInstance());
        this.onInit();
    }

    @Override
    public RarityTypeEnum getType() {
        return RarityTypeEnum.MOB;
    }

    public List<MobRarity> getElite() {
        return Arrays.asList(LegendaryMob.getInstance(), EpicMob.getInstance(), RareMob.getInstance());
    }

}


