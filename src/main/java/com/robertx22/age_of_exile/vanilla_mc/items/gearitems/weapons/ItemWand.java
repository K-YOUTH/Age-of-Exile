package com.robertx22.age_of_exile.vanilla_mc.items.gearitems.weapons;

import com.robertx22.age_of_exile.vanilla_mc.items.gearitems.bases.BaseWeaponItem;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ItemWand extends BaseWeaponItem {

    public ItemWand(String locname) {
        super(locname);
        this.attackSpeed = -2.6F;
    }

    @Override
    public TypedActionResult<ItemStack> use(World worldIn, PlayerEntity player, Hand handIn) {
        return super.use(worldIn, player, handIn);
       /*
        ItemStack itemStack = player.getStackInHand(handIn);
        player.setCurrentHand(handIn);
        return TypedActionResult.success(itemStack);

        */

    }

}