package com.robertx22.age_of_exile.uncommon.stat_calculation;

import com.robertx22.age_of_exile.capability.entity.EntityCap.UnitData;
import com.robertx22.age_of_exile.database.data.stats.types.core_stats.base.ICoreStat;
import com.robertx22.age_of_exile.database.registrators.Stats;
import com.robertx22.age_of_exile.saveclasses.unit.StatData;
import com.robertx22.age_of_exile.saveclasses.unit.Unit;
import com.robertx22.age_of_exile.uncommon.interfaces.IAffectsStats;
import com.robertx22.age_of_exile.vanilla_mc.potion_effects.bases.IApplyStatPotion;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;

public class CommonStatUtils {

    public static void addExactCustomStats(UnitData data) {
        data.getCustomExactStats()
            .applyStats(data);
    }

    public static void addPotionStats(LivingEntity entity) {

        for (StatusEffectInstance instance : entity.getStatusEffects()) {
            if (instance.getEffectType() instanceof IApplyStatPotion) {
                IApplyStatPotion stat = (IApplyStatPotion) instance.getEffectType();
                try {
                    stat.applyStats(entity.world, instance, entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void CalcTraitsAndCoreStats(UnitData unit) {

        Unit theunit = unit.getUnit();

        for (ICoreStat core : Stats.allPreGenMapStatLists.get(ICoreStat.class)) {

            StatData statdata = theunit.peekAtStat(core.GUID());
            if (statdata.isMoreThanZero()) {
                core.addToOtherStats(unit, statdata);
            }

        }

        for (IAffectsStats trait : Stats.allPreGenMapStatLists.get(IAffectsStats.class)) {

            StatData statdata = theunit.peekAtStat(trait.GUID());
            if (statdata.isMoreThanZero()) {
                trait.affectStats(unit, statdata);
            }

        }
    }

}
