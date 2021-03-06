package com.robertx22.age_of_exile.mmorpg.registers.common;

import com.robertx22.age_of_exile.capability.entity.EntityCap;
import com.robertx22.age_of_exile.capability.player.PlayerSpellCap;
import com.robertx22.age_of_exile.capability.player.PlayerStatsCap;
import com.robertx22.age_of_exile.capability.world.WorldAreas;
import com.robertx22.age_of_exile.mmorpg.Ref;
import nerdhub.cardinal.components.api.ComponentRegistry;
import nerdhub.cardinal.components.api.ComponentType;
import nerdhub.cardinal.components.api.event.EntityComponentCallback;
import nerdhub.cardinal.components.api.event.WorldComponentCallback;
import nerdhub.cardinal.components.api.util.EntityComponents;
import nerdhub.cardinal.components.api.util.RespawnCopyStrategy;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;

public class ComponentRegisters {

    public ComponentType<EntityCap.UnitData> UNIT_DATA =
        ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier(Ref.MODID, "unit_data"),
            EntityCap.UnitData.class)
            .attach(EntityComponentCallback.event(LivingEntity.class), x -> new EntityCap.DefaultImpl(x));

    public ComponentType<PlayerSpellCap.ISpellsCap> PLAYER_SPELLS =
        ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier(Ref.MODID, "spells"),
            PlayerSpellCap.ISpellsCap.class)
            .attach(EntityComponentCallback.event(PlayerEntity.class), x -> new PlayerSpellCap.DefaultImpl());

    public ComponentType<PlayerStatsCap.IPlayerStatPointsData> PLAYER_STAT_POINTS =
        ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier(Ref.MODID, "stat_points"),
            PlayerStatsCap.IPlayerStatPointsData.class)
            .attach(EntityComponentCallback.event(PlayerEntity.class), x -> new PlayerStatsCap.DefaultImpl());

    public ComponentType<WorldAreas> WORLD_AREAS =
        ComponentRegistry.INSTANCE.registerIfAbsent(
            new Identifier(Ref.MODID, "world_areas"),
            WorldAreas.class);

    public ComponentRegisters() {
        EntityComponents.setRespawnCopyStrategy(UNIT_DATA, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(PLAYER_SPELLS, RespawnCopyStrategy.ALWAYS_COPY);
        EntityComponents.setRespawnCopyStrategy(PLAYER_STAT_POINTS, RespawnCopyStrategy.ALWAYS_COPY);

        WorldComponentCallback.EVENT.register(
            (world, components) -> components.put(
                WORLD_AREAS, new WorldAreas(world)));

    }

}
