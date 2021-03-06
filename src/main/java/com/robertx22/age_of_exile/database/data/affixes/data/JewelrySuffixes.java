package com.robertx22.age_of_exile.database.data.affixes.data;

import com.robertx22.age_of_exile.database.data.StatModifier;
import com.robertx22.age_of_exile.database.data.affixes.AffixBuilder;
import com.robertx22.age_of_exile.database.data.affixes.ElementalAffixBuilder;
import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType.SlotTag;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.AllAttributes;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Dexterity;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Intelligence;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.Strength;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalDamageBonus;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryInit;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;

import java.util.Arrays;

public class JewelrySuffixes implements ISlashRegistryInit {

    @Override
    public void registerAll() {

        ElementalAffixBuilder.start()
            .guid(x -> x.guidName + "_ele_dmg_jewelry")
            .add(Elements.Fire, "Of Embers")
            .add(Elements.Water, "Of Ice")
            .add(Elements.Thunder, "Of Electricity")
            .add(Elements.Nature, "Of Venom")
            .tier(1, x -> Arrays.asList(new StatModifier(15, 20, new ElementalDamageBonus(x), ModType.FLAT)))
            .tier(2, x -> Arrays.asList(new StatModifier(11, 15, new ElementalDamageBonus(x), ModType.FLAT)))
            .tier(3, x -> Arrays.asList(new StatModifier(7, 11, new ElementalDamageBonus(x), ModType.FLAT)))
            .tier(4, x -> Arrays.asList(new StatModifier(5, 7, new ElementalDamageBonus(x), ModType.FLAT)))
            .tier(5, x -> Arrays.asList(new StatModifier(2, 5, new ElementalDamageBonus(x), ModType.FLAT)))
            .includesTags(SlotTag.jewelry_family)
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_the_philosopher")
            .Named("Of the Philosopher")
            .tier(1, new StatModifier(0.2F, 0.3F, Intelligence.INSTANCE, ModType.FLAT))
            .tier(2, new StatModifier(0.15F, 0.2F, Intelligence.INSTANCE, ModType.FLAT))
            .tier(3, new StatModifier(0.1F, 0.15F, Intelligence.INSTANCE, ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.intelligence)
            .excludesTags(SlotTag.weapon_family)
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_the_titan")
            .Named("Of the Titan")
            .tier(1, new StatModifier(0.2F, 0.3F, Strength.INSTANCE, ModType.FLAT))
            .tier(2, new StatModifier(0.15F, 0.2F, Strength.INSTANCE, ModType.FLAT))
            .tier(3, new StatModifier(0.1F, 0.15F, Strength.INSTANCE, ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.strength)
            .excludesTags(SlotTag.weapon_family)
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_the_wind")
            .Named("Of the Wind")
            .tier(1, new StatModifier(0.2F, 0.3F, Dexterity.INSTANCE, ModType.FLAT))
            .tier(2, new StatModifier(0.15F, 0.2F, Dexterity.INSTANCE, ModType.FLAT))
            .tier(3, new StatModifier(0.1F, 0.15F, Dexterity.INSTANCE, ModType.FLAT))
            .includesTags(SlotTag.jewelry_family, SlotTag.dexterity)
            .excludesTags(SlotTag.weapon_family)
            .Suffix()
            .Build();

        AffixBuilder.Normal("of_the_sky")
            .Named("Of the Sky")
            .tier(1, new StatModifier(0.15F, 0.25F, AllAttributes.getInstance(), ModType.FLAT))
            .tier(2, new StatModifier(0.1F, 0.15F, AllAttributes.getInstance(), ModType.FLAT))
            .tier(3, new StatModifier(0.05F, 0.1F, AllAttributes.getInstance(), ModType.FLAT))
            .includesTags(SlotTag.jewelry_family)
            .Weight(50)
            .Suffix()
            .Build();

    }
}
