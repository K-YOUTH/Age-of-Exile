package com.robertx22.age_of_exile.database.data.rarities;

import com.robertx22.age_of_exile.database.base.Rarities;
import com.robertx22.age_of_exile.database.data.rarities.containers.GearRarities;
import com.robertx22.age_of_exile.database.data.rarities.containers.SkillGemRarities;
import com.robertx22.age_of_exile.datapacks.bases.ISerializable;

public enum RarityTypeEnum {

    GEAR("gear", new GearRarities.CommonGear(), Rarities.Gears),
    MOB("mob", null, Rarities.Mobs),
    SKILL_GEM("skill_gem", new SkillGemRarities.CommonGem(), Rarities.SkillGems);;

    public String id;
    public ISerializable serializer;
    public BaseRaritiesContainer container;

    RarityTypeEnum(String id, ISerializable serializer, BaseRaritiesContainer container) {
        this.id = id;
        this.serializer = serializer;
        this.container = container;
    }

}
