package com.robertx22.age_of_exile.datapacks.generators;

import com.robertx22.age_of_exile.database.data.IGUID;
import com.robertx22.age_of_exile.datapacks.bases.ISerializable;
import com.robertx22.age_of_exile.mmorpg.Ref;
import net.minecraft.data.DataProvider;

import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public class RarityGenerator<T extends IGUID & ISerializable<T>> extends BaseDatapackGenerator<T> {

    public RarityGenerator(List<T> list, String category) {
        super(list, category);
    }

    @Override
    public void run() {
        generateAll();
    }

    public Path resolve(Path path, T object) {
        return path.resolve(
            "data/" + Ref.MODID + "/" + category + "/" + object.datapackFolder() + object.formattedGUID() +
                ".json");
    }

    protected void generateAll() {

        Path path = gameDirPath();

        for (T entry : list) {
            Path target = movePath(resolve(path, entry));

            try {
                DataProvider.writeToPath(GSON, cache, entry.toJson(), target);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
