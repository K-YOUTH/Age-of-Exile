package com.robertx22.age_of_exile.uncommon.datasaving;

import com.robertx22.age_of_exile.capability.entity.EntityCap.UnitData;
import com.robertx22.age_of_exile.capability.player.PlayerSpellCap;
import com.robertx22.age_of_exile.capability.player.PlayerStatsCap;
import com.robertx22.age_of_exile.mmorpg.ModRegistry;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

public class Load {

    public static boolean hasUnit(Entity provider) {
        return true;
    }

    public static PlayerSpellCap.ISpellsCap spells(LivingEntity provider) {
        if (provider instanceof PlayerEntity) {
            return ModRegistry.COMPONENTS.PLAYER_SPELLS.get(provider);
        } else {
            return new PlayerSpellCap.DefaultImpl();
        }
    }

    public static UnitData Unit(Entity entity) {

        UnitData data = null;
        try {
            data = ModRegistry.COMPONENTS.UNIT_DATA.get(entity);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (data == null) {
            System.out.println("Unit data is null? " + entity.getEntityName());
        }

        return data;
    }

    public static PlayerStatsCap.IPlayerStatPointsData statPoints(PlayerEntity provider) {
        return ModRegistry.COMPONENTS.PLAYER_STAT_POINTS.get(provider);
    }

}
