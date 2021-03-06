package com.robertx22.age_of_exile.datapacks.generators;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.robertx22.age_of_exile.database.data.IGUID;
import com.robertx22.age_of_exile.datapacks.bases.ISerializable;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.data.DataCache;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public abstract class BaseDatapackGenerator<T extends IGUID & ISerializable<T>> {
    public static final Gson GSON = (new GsonBuilder()).setPrettyPrinting()
        .create();
    protected DataCache cache;
    public String category;
    public List<T> list;

    public abstract void run();

    public BaseDatapackGenerator(List<T> list, String category) {

        this.list = list;
        this.category = category;
        try {
            cache = new DataCache(FabricLoader.getInstance()
                .getGameDir(), "datagencache");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected Path gameDirPath() {
        return FabricLoader.getInstance()
            .getGameDir();
    }

    protected Path movePath(Path target) {
        String movedpath = target.toString();
        movedpath = movedpath.replace("run/", "src/generated/resources/");
        movedpath = movedpath.replace("run\\", "src/generated/resources\\");

        return Paths.get(movedpath);
    }
}
