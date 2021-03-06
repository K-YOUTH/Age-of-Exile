package com.robertx22.age_of_exile.vanilla_mc.items.gearitems.bases.armor_materials;

import net.minecraft.entity.EquipmentSlot;

public class PlateMat extends BaseMat {

    static int[] damageReductions = new int[]{
        2,
        4,
        6,
        3
    };

    @Override
    public int getProtectionAmount(EquipmentSlot slotIn) {
        return damageReductions[slotIn.getEntitySlotId()];
    }

    @Override
    public int getEnchantability() {
        return 10;
    }

    @Override
    public String getName() {
        return "plate";
    }

    @Override
    public float getToughness() {
        return 2;
    }

}
