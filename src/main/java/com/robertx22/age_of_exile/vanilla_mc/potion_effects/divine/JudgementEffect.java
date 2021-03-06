package com.robertx22.age_of_exile.vanilla_mc.potion_effects.divine;

import com.robertx22.age_of_exile.database.data.spells.SpellUtils;
import com.robertx22.age_of_exile.database.data.stats.types.defense.Armor;
import com.robertx22.age_of_exile.database.data.stats.types.generated.ElementalResist;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.age_of_exile.saveclasses.spells.calc.SpellCalcData;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.uncommon.effectdatas.DamageEffect;
import com.robertx22.age_of_exile.uncommon.effectdatas.EffectData;
import com.robertx22.age_of_exile.uncommon.effectdatas.interfaces.WeaponTypes;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import com.robertx22.age_of_exile.uncommon.utilityclasses.ParticleUtils;
import com.robertx22.age_of_exile.vanilla_mc.potion_effects.bases.*;
import com.robertx22.age_of_exile.vanilla_mc.potion_effects.bases.data.ExtraPotionData;
import com.robertx22.age_of_exile.vanilla_mc.potion_effects.bases.data.PotionStat;
import com.robertx22.library_of_exile.utils.SoundUtils;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffectType;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.LiteralText;
import net.minecraft.text.Text;

import java.util.ArrayList;
import java.util.List;

public class JudgementEffect extends BasePotionEffect implements IApplyStatPotion, IOnBasicAttackedPotion {

    public static final JudgementEffect INSTANCE = new JudgementEffect();

    private JudgementEffect() {
        super(StatusEffectType.HARMFUL, 4393423);

        this.tickActions.add(new OnTickAction(ctx -> {
            ParticleUtils.spawnParticles(ParticleTypes.FALLING_HONEY, ctx.entity, 15);
            return ctx;
        }, null));

    }

    @Override
    public String GUID() {
        return "judgement";
    }

    @Override
    public String locNameForLangFile() {
        return "Judgement";
    }

    @Override
    public int getMaxStacks() {
        return 5;
    }

    @Override
    public List<PotionStat> getPotionStats() {
        List<PotionStat> list = new ArrayList<>();
        list.add(new PotionStat(-3, new ElementalResist(Elements.Elemental)));
        list.add(new PotionStat(-10, Armor.getInstance()));
        return list;
    }

    @Override
    public List<Text> getEffectTooltip(TooltipInfo info) {
        List<Text> list = new ArrayList<>();

        list.add(new LiteralText("Debuffs enemy, at max stacks, deal extra damage."));

        return list;

    }

    @Override
    public void onBasicAttacked(StatusEffectInstance instance, LivingEntity source, LivingEntity target) {

        ExtraPotionData data = PotionDataSaving.getData(target, instance);

        LivingEntity caster = data.getCaster(source.world);

        if (data.getStacks() < this.getMaxStacks()) {
            PotionEffectUtils.apply(this, caster, target);
        } else {

            SpellUtils.summonLightningStrike(target);

            SoundUtils.playSound(target, SoundEvents.ENTITY_LIGHTNING_BOLT_IMPACT, 1, 1);

            int num = getCalc(source).getCalculatedValue(caster);

            DamageEffect dmg = new DamageEffect(null, caster, target, num, Load.Unit(caster), Load.Unit(target),
                EffectData.EffectTypes.SPELL, WeaponTypes.None
            );
            dmg.element = Elements.Thunder;
            dmg.Activate();

            target.removeStatusEffect(this);

        }
    }

    @Override
    public SpellCalcData getCalc(LivingEntity caster) {
        return SpellCalcData.base(10);
    }

    @Override
    public int getDurationInSeconds(LivingEntity en) {
        return 10;
    }

    @Override
    public int getTickRate(LivingEntity en) {
        return 20;
    }
}
