package com.robertx22.age_of_exile.datapacks.bases;

import com.google.gson.JsonObject;
import com.google.gson.internal.Streams;
import com.robertx22.age_of_exile.database.data.IGUID;
import com.robertx22.age_of_exile.database.registry.ISlashRegistryEntry;
import com.robertx22.age_of_exile.datapacks.JsonUtils;
import com.robertx22.age_of_exile.uncommon.interfaces.IAutoLocDesc;
import com.robertx22.age_of_exile.uncommon.interfaces.IAutoLocName;
import com.robertx22.age_of_exile.uncommon.interfaces.IWeighted;
import com.robertx22.age_of_exile.uncommon.interfaces.data_items.IRarity;
import com.robertx22.age_of_exile.uncommon.interfaces.data_items.ITiered;

import java.io.IOException;

public interface ISerializable<T> {
    JsonObject toJson();

    T fromJson(JsonObject json);

    static String ID = "id";
    static String REGISTRY = "registry";
    static String LANG_NAME = "lang_file_string";
    static String LANG_DESC = "lang_file_desc_string";
    static String WEIGHT = "weight";
    static String RARITY = "rarity";
    static String TIER = "tier";

    default String datapackFolder() {
        return "";
    }

    default String getGUIDFromJson(JsonObject json) {
        return json.get(ID)
            .getAsString();
    }

    default int getTierFromJson(JsonObject json) {
        return json.get(TIER)
            .getAsInt();
    }

    default int getWeightFromJson(JsonObject json) {
        return json.get(WEIGHT)
            .getAsInt();
    }

    default int getRarityFromJson(JsonObject json) {
        return json.get(RARITY)
            .getAsInt();
    }

    default String getLangDescStringFromJson(JsonObject json) {
        return json.get(LANG_DESC)
            .getAsString();
    }

    default String getLangNameStringFromJson(JsonObject json) {
        return json.get(LANG_NAME)
            .getAsString();
    }

    default JsonObject getDefaultJson() {
        JsonObject json = new JsonObject();

        if (this instanceof IGUID) {
            IGUID claz = (IGUID) this;
            json.addProperty(ID, claz.GUID());
        }

        if (this instanceof IWeighted) {
            IWeighted claz = (IWeighted) this;
            json.addProperty(WEIGHT, claz.Weight());
        }

        if (this instanceof IRarity) {
            IRarity claz = (IRarity) this;
            json.addProperty(RARITY, claz.getRarityRank());
        }

        if (this instanceof ITiered) {
            ITiered claz = (ITiered) this;
            json.addProperty(TIER, claz.getTier());
        }

        if (this instanceof ISlashRegistryEntry) {
            ISlashRegistryEntry claz = (ISlashRegistryEntry) this;
            json.addProperty(REGISTRY, claz.getSlashRegistryType().id);
        }

        if (this instanceof IAutoLocName) {
            IAutoLocName loc = (IAutoLocName) this;
            json.addProperty(LANG_NAME, loc.formattedLocNameLangFileGUID());
        }
        if (this instanceof IAutoLocDesc) {
            IAutoLocDesc loc = (IAutoLocDesc) this;
            json.addProperty(LANG_DESC, loc.formattedLocDescLangFileGUID());
        }

        return json;
    }

    default boolean shouldGenerateJson() {
        return true;
    }

    default String toJsonNoSpaces() { // todo , unsure if this is much or any better. it removes 2 new object calls and thats it
        try {

            Streams.write(toJson(), JsonUtils.jsonWriter);
            return JsonUtils.stringWriter.toString();

        } catch (IOException e) {
            throw new AssertionError(e);
        }

    }

}
