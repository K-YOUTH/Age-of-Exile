package com.robertx22.mine_and_slash.uncommon.testing.tests;

import com.robertx22.mine_and_slash.uncommon.utilityclasses.EntityTypeUtils;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.registry.Registry;

public class MobTypesTest {

    public static void run(ServerWorld world) {

        for (EntityType<?> type : Registry.ENTITY_TYPE) {

            Entity en = type.create(world);

            if (en instanceof LivingEntity) {
                EntityTypeUtils.EntityType ent = EntityTypeUtils.getType((LivingEntity) en);

                System.out.println(Registry.ENTITY_TYPE.getId(type)
                    .toString() + ": " + ent.id);
            }

        }

    }
}
