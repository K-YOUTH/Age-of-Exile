package com.robertx22.age_of_exile.saveclasses.spells;

import com.robertx22.age_of_exile.database.data.IGUID;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.BaseSpell;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.SpellCastContext;
import com.robertx22.age_of_exile.database.data.spells.spell_classes.bases.configs.PreCalcSpellConfigs;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;

public interface IAbility extends IGUID {
    public enum Type {
        SPELL, SYNERGY, EFFECT
    }

    public static List<IAbility> getAll() {

        List<IAbility> list = new ArrayList<>();

        list.addAll(SlashRegistry.Spells()
            .getList());

        return list;

    }

    public Elements getElement();

    public static IAbility fromId(String id) {
        IAbility ability = null;

        if (SlashRegistry.Spells()
            .isRegistered(id)) {
            ability = SlashRegistry.Spells()
                .get(id);
        }

        return ability;
    }

    MutableText getLocName();

    PreCalcSpellConfigs getPreCalcConfig();

    Identifier getIconLoc();

    public Type getAbilityType();

    public BaseSpell getSpell();

    public default void finishTooltip(List<Text> list, SpellCastContext ctx, TooltipInfo info) {
        try {
            /* No, it's not accurate right now
            TooltipUtils.addEmpty(list);

            list.add(new SText(Formatting.LIGHT_PURPLE + "" + Formatting.BOLD).append("Ability Stats:"));
            list.addAll(ctx.getConfigFor(this)
                .GetTooltipString(info, ctx));

             */

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}


