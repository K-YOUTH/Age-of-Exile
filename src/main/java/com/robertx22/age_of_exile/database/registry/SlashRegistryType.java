package com.robertx22.age_of_exile.database.registry;

import com.robertx22.age_of_exile.database.data.DimensionConfig;
import com.robertx22.age_of_exile.database.data.EntityConfig;
import com.robertx22.age_of_exile.database.data.compatible_item.CompatibleItem;
import com.robertx22.age_of_exile.database.data.gems.Gem;
import com.robertx22.age_of_exile.database.data.perks.Perk;
import com.robertx22.age_of_exile.database.data.runes.Rune;
import com.robertx22.age_of_exile.database.data.runewords.RuneWord;
import com.robertx22.age_of_exile.database.data.spell_schools.SpellSchool;
import com.robertx22.age_of_exile.database.data.tiers.base.Tier;
import com.robertx22.age_of_exile.database.registrators.MobAffixes;
import com.robertx22.age_of_exile.database.registry.empty_entries.EmptyAffix;
import com.robertx22.age_of_exile.database.registry.empty_entries.EmptyUniqueGear;
import com.robertx22.age_of_exile.datapacks.bases.ISerializable;
import com.robertx22.age_of_exile.datapacks.seriazables.SerializableBaseGearType;

public enum SlashRegistryType {

    NONE("none"),
    EFFECT("effect"),
    STAT("stat"),
    SPELL_SCHOOL("spell_school") {
        @Override
        public ISerializable getSerializer() {
            return SpellSchool.SERIALIZER;
        }
    },
    PERK("perk") {
        @Override
        public ISerializable getSerializer() {
            return Perk.SERIALIZER;
        }
    },
    RUNEWORD("runeword") {
        @Override
        public ISerializable getSerializer() {
            return RuneWord.SERIALIZER;
        }
    },
    GEM("gem") {
        @Override
        public ISerializable getSerializer() {
            return Gem.SERIALIZER;
        }
    },
    RUNE("rune") {
        @Override
        public ISerializable getSerializer() {
            return Rune.SERIALIZER;
        }
    },
    GEAR_SLOT("gear_slot"),
    TIER("tier") {
        @Override
        public ISerializable getSerializer() {
            return Tier.SERIALIZER;
        }
    },
    MOB_AFFIX("mob_affix") {
        @Override
        public ISerializable getSerializer() {
            return MobAffixes.EMPTY;
        }
    },
    STATMOD("stat_mod"),
    UNIQUE_GEAR("unique_gear") {
        @Override
        public ISerializable getSerializer() {
            return new EmptyUniqueGear();
        }
    },
    GEAR_TYPE("gear_type") {
        @Override
        public ISerializable getSerializer() {
            return SerializableBaseGearType.EMPTY;
        }
    },
    SPELL("spell"),
    AFFIX("affix") {
        @Override
        public ISerializable getSerializer() {
            return EmptyAffix.getInstance();
        }
    },
    EMPTY("empty"),
    DIMENSION_CONFIGS("dimension_config") {
        @Override
        public ISerializable getSerializer() {
            return DimensionConfig.EMPTY;
        }
    },
    ENTITY_CONFIGS("entity_config") {
        @Override
        public ISerializable getSerializer() {
            return EntityConfig.EMPTY;
        }
    },
    CURRENCY_ITEMS("currency_item"),
    COMPATIBLE_ITEM("compatible_item") {
        @Override
        public ISerializable getSerializer() {
            return CompatibleItem.EMPTY;
        }
    },
    LOOT_CRATE("loot_crate");

    public String id;

    SlashRegistryType(String id) {
        this.id = id;
    }

    public ISerializable getSerializer() { // TODO this could be better
        return null;
    }

    public static SlashRegistryType getFromString(String str) {
        for (SlashRegistryType type : values()) {
            if (str.equals(type.id)) {
                return type;
            }
        }
        return null;
    }

}
