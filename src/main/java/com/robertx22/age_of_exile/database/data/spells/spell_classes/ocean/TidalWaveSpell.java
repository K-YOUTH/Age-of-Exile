package com.robertx22.age_of_exile.database.data.spells.spell_classes.ocean;

import com.robertx22.age_of_exile.database.data.gear_types.bases.BaseGearType;
import com.robertx22.age_of_exile.database.data.spells.entities.proj.TidalWaveEntity;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.BaseSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.cast_types.SpellCastType;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.configs.ImmutableSpellConfigs;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.configs.SC;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.localization.Words;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class TidalWaveSpell extends BaseSpell {

    private TidalWaveSpell() {
        super(
            new ImmutableSpellConfigs() {

                @Override
                public SpellCastType castType() {
                    return SpellCastType.PROJECTILE;
                }

                @Override
                public SoundEvent sound() {
                    return SoundEvents.BLOCK_BUBBLE_COLUMN_WHIRLPOOL_INSIDE;
                }

                @Override
                public Elements element() {
                    return Elements.Water;
                }
            }.cooldownIfCanceled(true)
                .summonsEntity(w -> new TidalWaveEntity(w))
                .setSwingArmOnCast());

    }

    @Override
    public BaseGearType.PlayStyle getPlayStyle() {
        return BaseGearType.PlayStyle.INT;
    }

    @Override
    public PreCalcSpellConfigs getPreCalcConfig() {
        PreCalcSpellConfigs c = new PreCalcSpellConfigs();

        c.set(SC.MANA_COST, 8, 13);
        c.set(SC.BASE_VALUE, 3, 6);
        c.set(SC.ATTACK_SCALE_VALUE, 0.1F, 0.3F);
        c.set(SC.SHOOT_SPEED, 0.6F, 0.9F);
        c.set(SC.PROJECTILE_COUNT, 3, 5);
        c.set(SC.CAST_TIME_TICKS, 0, 0);
        c.set(SC.COOLDOWN_TICKS, 20, 20);
        c.set(SC.TIMES_TO_CAST, 3, 4);
        c.set(SC.DURATION_TICKS, 60, 80);

        c.setMaxLevel(16);

        return c;
    }

    public static TidalWaveSpell getInstance() {
        return SingletonHolder.INSTANCE;
    }

    @Override
    public String GUID() {
        return "tidal_wave";
    }

    @Override
    public List<Text> GetDescription(TooltipInfo info, SpellCastContext ctx) {

        List<Text> list = new ArrayList<>();

        list.add(new LiteralText("Throw waves in a cone."));
        list.add(new LiteralText("Damaging all enemies hit:"));

        list.addAll(getCalculation(ctx).GetTooltipString(info, ctx));

        return list;

    }

    @Override
    public Words getName() {
        return Words.TidalWave;
    }

    private static class SingletonHolder {
        private static final TidalWaveSpell INSTANCE = new TidalWaveSpell();
    }
}
