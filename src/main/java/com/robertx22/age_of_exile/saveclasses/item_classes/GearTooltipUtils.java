package com.robertx22.age_of_exile.saveclasses.item_classes;

import com.robertx22.age_of_exile.capability.entity.EntityCap.UnitData;
import com.robertx22.age_of_exile.config.forge.ModConfig;
import com.robertx22.age_of_exile.database.data.rarities.IGearRarity;
import com.robertx22.age_of_exile.database.data.runewords.RuneWord;
import com.robertx22.age_of_exile.database.data.unique_items.IUnique;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.ExactStatData;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.FinalizedGearStatReq;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.IGearPartTooltip;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.TooltipInfo;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_parts.SocketData;
import com.robertx22.age_of_exile.saveclasses.item_classes.tooltips.MergedStats;
import com.robertx22.age_of_exile.uncommon.localization.Words;
import com.robertx22.age_of_exile.uncommon.utilityclasses.TooltipUtils;
import com.robertx22.age_of_exile.uncommon.wrappers.SText;
import com.robertx22.library_of_exile.utils.CLOC;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.item.ItemStack;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Formatting;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class GearTooltipUtils {

    public static void BuildTooltip(GearItemData gear, ItemStack stack, List<Text> tooltip, UnitData data) {

        List<Text> tip = tooltip;

        if (gear.GetBaseGearType() == null) {
            return;
        }

        TooltipInfo info = new TooltipInfo(data, gear.getRarity()
            .StatPercents());

        tip.clear();

        tip.add(gear.GetDisplayName(stack));

        if (!gear.isIdentified()) {

            tip.add(new SText(""));

            tip.add(
                Words.ItemIsUnidentified.locName()
                    .formatted(Formatting.GRAY));
            tip.add(
                Words.UseAnIdentifyScroll.locName()
                    .formatted(Formatting.GRAY));

            tip.add(new SText(""));

            return;
        }

        if (gear.baseStats != null) {
            tip.addAll(gear.baseStats.GetTooltipString(info, gear));
        }

        Formatting reqColor = Formatting.GRAY;
        Formatting reqNumberColor = Formatting.WHITE;

        if (!gear.meetsStatRequirements(data) || gear.level > data.getLevel()) {
            reqColor = Formatting.RED;
            reqNumberColor = Formatting.RED;
        }

        FinalizedGearStatReq req = gear.getStatRequirements();
        String reqtext = reqColor + "(Level " + reqNumberColor + gear.level;

        int dex = req.dexterity;
        int str = req.strength;
        int intr = req.intelligence;

        if (str > 0) {
            reqtext += reqColor + ", STR " + reqNumberColor + "" + str;
        }
        if (intr > 0) {
            reqtext += reqColor + ", INT " + reqNumberColor + "" + intr;
        }
        if (dex > 0) {
            reqtext += reqColor + ", DEX " + reqNumberColor + "" + dex;
        }
        reqtext += ")";

        tip.add(new SText(""));
        tip.add(new SText(reqtext));
        tip.add(new SText(""));

        if (gear.implicitStats != null) {
            tip.addAll(gear.implicitStats.GetTooltipString(info, gear));
        }

        tip.add(new LiteralText(""));

        List<IGearPartTooltip> list = new ArrayList<IGearPartTooltip>();

        if (gear.uniqueStats != null) {
            tip.addAll(gear.uniqueStats.GetTooltipString(info, gear));
        }

        tip.add(new LiteralText(""));

        List<ExactStatData> stats = new ArrayList<>();

        gear.affixes.getAllAffixesAndSockets()
            .forEach(x -> stats.addAll(x.GetAllStats(gear)));

        stats.addAll(gear.sockets.GetAllStats(gear));

        MergedStats merged = new MergedStats(stats, info);
        list.add(merged);

        int n = 0;
        for (IGearPartTooltip part : list) {
            if (part != null) {
                tip.addAll(part.GetTooltipString(info, gear));

                if (n == list.size() - 1) {
                    for (int i = 0; i < gear.sockets.getEmptySockets(); i++) {
                        tip.add(new SText(Formatting.YELLOW + "[Socket]"));
                    }
                }

                tip.add(new LiteralText(""));
            }
            n++;
        }

        if (Screen.hasShiftDown()) {
            if (!gear.isSalvagable) {
                tip.add(
                    Words.Unsalvagable.locName()
                        .formatted(Formatting.RED));
            }
        }

        if (gear.is_unique) {
            IUnique unique = gear.uniqueStats.getUnique();

            String uniqdesc = CLOC.translate(unique.locDesc());

            List<String> lores = TooltipUtils.cutIfTooLong(uniqdesc);
            tip.add(new LiteralText(""));

            int i = 0;
            for (String desc : lores) {

                MutableText comp = new LiteralText(gear.getRarity()
                    .textFormatting() + "");

                if (i == 0) {
                    comp.append("'");
                }
                comp.append(desc);

                if (i == lores.size() - 1) {
                    comp.append("'");
                }
                i++;
                tip.add(comp);

            }
        }

        tip.add(new SText(""));

        RuneWord word = gear.sockets.getRuneWord();

        if (word != null) {

            tip.add(new LiteralText(Formatting.GOLD + "'Runeword: ").append(word.locName())
                .append("'")
                .formatted(Formatting.GOLD));
        }
        tip.add(new LiteralText(""));

        IGearRarity rarity = gear.getRarity();

        int socketed = gear.sockets.sockets.size();

        if (socketed > 0) {

            Formatting BR = Formatting.GRAY;
            String runes = "";
            for (SocketData x : gear.sockets.sockets) {
                if (!runes.isEmpty()) {
                    runes += " ";
                }

                if (x.isRune()) {
                    runes += BR + "[" + Formatting.GOLD + x.rune_id.toUpperCase(Locale.ROOT) + BR + "]";

                }
                if (x.isGem()) {
                    runes += BR + "[" + x.getGem()
                        .getFormat() + x.getGem()
                        .getIcon() + BR + "]";
                }

            }

            tip.add(new LiteralText(Formatting.GOLD + runes).formatted(Formatting.GOLD));

        }

        tip.add(new LiteralText(""));

        tip.add(TooltipUtils.rarity(rarity));

        tip.add(new SText(Formatting.GRAY + "Durability: " + (stack.getMaxDamage() - stack.getDamage()) + "/" + stack.getMaxDamage()));

        if (Screen.hasShiftDown() == false) {
            tooltip
                .add(
                    new LiteralText(Formatting.BLUE + "").append(new TranslatableText(Ref.MODID + ".tooltip." + "press_shift_more_info")
                    )
                        .formatted(Formatting.BLUE));
        }

        List<Text> tool = TooltipUtils.removeDoubleBlankLines(tip,
            ModConfig.get().client.REMOVE_EMPTY_TOOLTIP_LINES_IF_MORE_THAN_X_LINES);

        tip.clear();
        tip.addAll(tool);

    }

}
