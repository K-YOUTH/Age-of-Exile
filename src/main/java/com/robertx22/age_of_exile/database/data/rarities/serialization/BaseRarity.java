package com.robertx22.age_of_exile.database.data.rarities.serialization;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.Rarity;
import com.robertx22.age_of_exile.uncommon.interfaces.data_items.IRarity;
import net.minecraft.util.Formatting;

public class BaseRarity implements Rarity {

    public BaseRarity(RarityType rarity_type) {
        this.rarity_type = rarity_type;
    }

    public enum RarityType {
        GEAR("gear"), MOB("mob"), SKILL_GEM("skill_gem");

        public String id;

        RarityType(String id) {
            this.id = id;
        }
    }

    public RarityType rarity_type;
    public int rank;
    public int weight;
    public String text_format;
    public String loc_name_id;
    public String loc_name;
    public String guid;

    @Override
    public String locNameLangFileGUID() {
        return loc_name_id;
    }

    @Override
    public String GUID() {
        return guid;
    }

    @Override
    public int Rank() {
        return rank;
    }

    @Override
    public int Weight() {
        return weight;
    }

    @Override
    public Formatting textFormatting() {
        try {
            return Formatting.valueOf(text_format);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        return Formatting.GRAY;
    }

    @Override
    public String locNameForLangFile() {
        return loc_name;
    }

    protected void setCommonFields() {
        this.guid = "common";
        this.loc_name = "Common";
        this.text_format = Formatting.GRAY.name();
        this.rank = IRarity.Common;
        onSetFields();
    }

    protected void setMagicalFields() {
        this.guid = "magical";
        this.loc_name = "Magical";
        this.text_format = Formatting.GREEN.name();

        this.rank = IRarity.Magical;
        onSetFields();
    }

    protected void setRareFields() {
        this.guid = "rare";
        this.loc_name = "Rare";
        this.text_format = Formatting.YELLOW.name();

        this.rank = IRarity.Rare;
        onSetFields();
    }

    protected void setEpicFields() {
        this.guid = "epic";
        this.loc_name = "Epic";
        this.text_format = Formatting.LIGHT_PURPLE.name();

        this.rank = IRarity.Epic;
        onSetFields();
    }

    protected void setLegendaryFields() {
        this.guid = "legendary";
        this.loc_name = "Legendary";
        this.text_format = Formatting.GOLD.name();

        this.rank = IRarity.Legendary;
        onSetFields();
    }

    protected void setRelicFields() {
        this.guid = "relic";
        this.loc_name = "Relic";
        this.text_format = Formatting.GOLD.name();

        this.rank = IRarity.Relic;
        onSetFields();
    }

    protected void setUniqueFields() {
        this.guid = "unique";
        this.loc_name = "Unique";
        this.text_format = Formatting.RED.name();

        this.rank = IRarity.Unique;
        onSetFields();
    }

    private void onSetFields() {
        this.loc_name_id = Ref.MODID + ".rarity." + rarity_type.id + "." + formattedGUID();
    }

}

