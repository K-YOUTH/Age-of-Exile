package com.robertx22.age_of_exile.vanilla_mc.items.gearitems.bases.itemtiers;

import com.robertx22.age_of_exile.database.base.Rarities;
import com.robertx22.age_of_exile.database.data.rarities.IGearRarity;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import static net.minecraft.item.ToolMaterials.DIAMOND;
import static net.minecraft.item.ToolMaterials.IRON;

public class RarityItemTier implements ToolMaterial {

    public RarityItemTier(int rar) {
        this.rar = Rarities.Gears.get(rar);
    }

    IGearRarity rar;

    @Override
    public int getDurability() {
        return DIAMOND.getDurability();
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return IRON.getMiningSpeedMultiplier();
    }

    @Override
    public float getAttackDamage() {
        return IRON.getAttackDamage();
    }

    @Override
    public int getMiningLevel() {
        return IRON.getMiningLevel();
    }

    @Override
    public int getEnchantability() {
        return IRON.getEnchantability();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return Ingredient.ofItems(Items.STRUCTURE_BLOCK);
    }
}
