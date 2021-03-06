package com.robertx22.age_of_exile.datapacks.loaders;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.robertx22.age_of_exile.database.data.rarities.BaseRaritiesContainer;
import com.robertx22.age_of_exile.datapacks.generators.RarityGenerator;
import com.robertx22.age_of_exile.mmorpg.MMORPG;
import com.robertx22.age_of_exile.saveclasses.gearitem.gear_bases.Rarity;
import net.minecraft.resource.JsonDataLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

public abstract class BaseRarityDatapackLoader<T extends Rarity> extends JsonDataLoader {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final Gson GSON = new GsonBuilder().create();

    String id;
    Function<JsonObject, T> serializer;
    BaseRaritiesContainer container;

    public BaseRarityDatapackLoader(BaseRaritiesContainer container, String id, Function<JsonObject, T> serializer) {
        super(GSON, id);
        this.id = id;
        this.serializer = serializer;
        this.container = container;
    }

    @Override
    protected Map<Identifier, JsonElement> prepare(ResourceManager resourceManager, Profiler profiler) {

        if (MMORPG.RUN_DEV_TOOLS) {
            this.getDatapackGenerator()
                .run(); // first generate, then load. so no errors in dev enviroment
        }
        return super.prepare(resourceManager, profiler);

    }

    public abstract RarityGenerator getDatapackGenerator();

    @Override
    protected void apply(Map<Identifier, JsonElement> mapToLoad, ResourceManager manager, Profiler profilerIn) {

        System.out.println("Starting to register rarity datapacks on the server from datapacks");

        List<T> list = new ArrayList<>();

        mapToLoad.forEach((loc, json) -> {
            try {
                T object = serializer.apply(json.getAsJsonObject());
                list.add(object);
            } catch (Exception exception) {
                LOGGER.error("Couldn't parse " + id + " {}", loc, exception);
            }

        });

        container.updateFromDatapack(list);

        if (container.getAllRarities()
            .isEmpty()) {
            throw new RuntimeException("Age of Exile rarities are EMPTY after datapack loading!");
        } else {
            System.out.println("Rarity loading succeeded.");
        }

    }

}