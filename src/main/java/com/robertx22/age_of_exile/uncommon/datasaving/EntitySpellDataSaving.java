package com.robertx22.age_of_exile.uncommon.datasaving;

import com.robertx22.library_of_exile.utils.LoadSave;
import com.robertx22.age_of_exile.saveclasses.spells.EntitySpellData;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;

public class EntitySpellDataSaving {
    private static final String LOC = "entitySpellData";

    public static boolean has(ItemStack stack) {
        return stack.hasTag() && stack.getTag()
            .contains(LOC);
    }

    public static EntitySpellData Load(CompoundTag nbt) {
        return LoadSave.Load(EntitySpellData.class, new EntitySpellData(), nbt, LOC);

    }

    public static void Save(CompoundTag nbt, EntitySpellData data) {
        if (data != null) {
            LoadSave.Save(data, nbt, LOC);
        }
    }
}
