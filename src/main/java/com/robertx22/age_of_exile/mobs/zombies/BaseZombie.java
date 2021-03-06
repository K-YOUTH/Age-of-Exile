package com.robertx22.age_of_exile.mobs.zombies;

import com.robertx22.age_of_exile.mobs.OnTickRandomSpeedBoost;
import com.robertx22.age_of_exile.vanilla_mc.packets.EntityPacket;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.network.Packet;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;

public class BaseZombie extends ZombieEntity {

    public BaseZombie(EntityType<? extends ZombieEntity> entityType, World world) {
        super(entityType, world);
    }

    int angryTicks = 0;

    @Override
    public void tick() {
        if (OnTickRandomSpeedBoost.onTickTryAnger(this, angryTicks)) {
            angryTicks = 100;
        }
        angryTicks--;
        super.tick();
    }

    @Override
    public Packet<?> createSpawnPacket() {
        return EntityPacket.createPacket(this);
    }

    /*
    @Override
    public boolean canSpawn(WorldAccess world, SpawnReason spawnReason) {
        return true;
    }

     */

    @Override
    protected boolean burnsInDaylight() {
        return false;
    }

    @Override
    protected void initAttributes() {
        this.getAttributeInstance(EntityAttributes.ZOMBIE_SPAWN_REINFORCEMENTS)
            .setBaseValue(0);
    }

    @Override
    public boolean isConvertingInWater() {
        return false;
    }

    @Override
    protected void convertInWater() {
    }

    @Override
    protected Identifier getLootTableId() {
        return EntityType.ZOMBIE.getLootTableId(); // TODO, add loot tables later
    }

}



