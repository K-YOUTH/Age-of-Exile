package com.robertx22.age_of_exile.database.data.stats.effects.offense;

import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.data.stats.effects.base.BaseStatEffect;
import com.robertx22.age_of_exile.saveclasses.unit.StatData;
import com.robertx22.age_of_exile.uncommon.effectdatas.EffectData;
import com.robertx22.age_of_exile.uncommon.effectdatas.SpellDamageEffect;

public class AllSpellDamageEffect extends BaseStatEffect<SpellDamageEffect> {
    public static final AllSpellDamageEffect INSTANCE = new AllSpellDamageEffect();

    private AllSpellDamageEffect() {
        super(SpellDamageEffect.class);
    }

    @Override
    public int GetPriority() {
        return Priority.Second.priority;
    }

    @Override
    public EffectSides Side() {
        return EffectSides.Source;
    }

    @Override
    public SpellDamageEffect activate(SpellDamageEffect effect, StatData data, Stat stat) {
        effect.percentIncrease += data.getAverageValue();

        return effect;
    }

    @Override
    public boolean canActivate(SpellDamageEffect effect, StatData data, Stat stat) {
        return effect.getEffectType()
            .equals(EffectData.EffectTypes.SPELL);
    }

}