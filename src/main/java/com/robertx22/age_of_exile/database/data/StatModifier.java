package com.robertx22.age_of_exile.database.data;

import com.google.gson.JsonObject;
import com.robertx22.age_of_exile.database.data.stats.Stat;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import com.robertx22.age_of_exile.datapacks.bases.ISerializable;
import com.robertx22.age_of_exile.saveclasses.ExactStatData;
import com.robertx22.age_of_exile.uncommon.enumclasses.ModType;
import info.loenwind.autosave.annotations.Storable;
import info.loenwind.autosave.annotations.Store;

@Storable
public class StatModifier implements ISerializable<StatModifier> {

    @Store
    public float firstMin = 0;
    @Store
    public float firstMax = 0;

    @Store
    public float secondMin = 0;
    @Store
    public float secondMax = 0;

    @Store
    public String stat;
    @Store
    public String type;

    public static StatModifier EMPTY = new StatModifier();

    private StatModifier() {

    }

    public StatModifier(float firstMin, float firstMax, Stat stat, ModType type) {
        this.firstMin = firstMin;
        this.firstMax = firstMax;
        this.stat = stat.GUID();
        this.type = type.name();
    }

    public StatModifier(float firstMin, float firstMax, Stat stat) {
        this.firstMin = firstMin;
        this.firstMax = firstMax;
        this.stat = stat.GUID();
        this.type = ModType.FLAT.name();
    }

    public StatModifier(float firstMin, float firstMax, String stat, ModType type) {
        this.firstMin = firstMin;
        this.firstMax = firstMax;
        this.stat = stat;
        this.type = type.name();
    }

    public StatModifier(float firstMin, float firstMax, float secondMin, float secondMax, Stat stat, ModType type) {

        if (!stat.UsesSecondValue()) {
            try {
                throw new Exception(stat.GUID() + " doesn't need 2nd value for modifiers!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        this.firstMin = firstMin;
        this.firstMax = firstMax;
        this.secondMin = secondMin;
        this.secondMax = secondMax;
        this.stat = stat.GUID();
        this.type = type.name();
    }

    public StatModifier(float firstMin, float firstMax, float secondMin, float secondMax, String stat, ModType type) {

        this.firstMin = firstMin;
        this.firstMax = firstMax;
        this.secondMin = secondMin;
        this.secondMax = secondMax;
        this.stat = stat;
        this.type = type.name();
    }

    public Stat GetStat() {
        return SlashRegistry.Stats()
            .get(stat);
    }

    public boolean usesNumberRanges() {
        return getModType()
            .equals(ModType.FLAT) && secondMax != 0;
    }

    public ModType getModType() {
        return ModType.fromString(type);
    }

    @Override
    public JsonObject toJson() {

        JsonObject json = new JsonObject();

        json.addProperty("firstMin", firstMin);
        json.addProperty("firstMax", firstMax);
        json.addProperty("secondMin", secondMin);
        json.addProperty("secondMax", secondMax);

        json.addProperty("stat", stat);
        json.addProperty("type", ModType.valueOf(type).id);

        return json;
    }

    @Override
    public StatModifier fromJson(JsonObject json) {

        float firstMin = json.get("firstMin")
            .getAsFloat();
        float firstMax = json.get("firstMax")
            .getAsFloat();

        float secondMin = json.get("secondMin")
            .getAsFloat();
        float secondMax = json.get("secondMax")
            .getAsFloat();

        String stat = json.get("stat")
            .getAsString();

        ModType type = ModType.fromString(json.get("type")
            .getAsString());

        return new StatModifier(firstMin, firstMax, secondMin, secondMax, stat, type);

    }

    public ExactStatData ToExactStat(int percent, int lvl) {
        return ExactStatData.fromStatModifier(this, percent, lvl);
    }

}
