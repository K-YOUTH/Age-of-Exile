package com.robertx22.age_of_exile.vanilla_mc.items.gearitems.bases;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.robertx22.age_of_exile.uncommon.interfaces.IAutoLocName;
import com.robertx22.age_of_exile.uncommon.interfaces.IGearItem;
import com.robertx22.age_of_exile.uncommon.utilityclasses.ItemUtils;
import com.robertx22.age_of_exile.vanilla_mc.items.gearitems.bases.itemtiers.RarityItemTier;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolItem;
import net.minecraft.util.registry.Registry;

public abstract class BaseWeaponItem extends ToolItem implements IAutoLocName, IGearItem {

    public BaseWeaponItem(String locname) {

        super(
            new RarityItemTier(0), ItemUtils.getDefaultGearProperties()
                .maxDamageIfAbsent(BaseArmorItem.GetMat(BaseArmorItem.Type.PLATE, false)
                    .getDurability(EquipmentSlot.MAINHAND)));
        this.locname = locname;

    }

    String locname;
    public float attackSpeed = -2.4F;

    @Override
    public final String locNameForLangFile() {
        return locname;
    }

    @Override
    public AutoLocGroup locNameGroup() {
        return AutoLocGroup.Gear_Items;
    }

    @Override
    public String locNameLangFileGUID() {
        return Registry.ITEM.getId(this)
            .toString();
    }

    @Override
    public String GUID() {
        return "";
    }

    @Override
    public boolean postHit(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        stack.damage(1, attacker, (entity) -> {
            entity.sendEquipmentBreakStatus(EquipmentSlot.MAINHAND);
        });
        return true;
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getAttributeModifiers(EquipmentSlot slot) {
        Multimap<EntityAttribute, EntityAttributeModifier> map = HashMultimap.create();
        if (slot == EquipmentSlot.MAINHAND) {
            map.put(
                EntityAttributes.GENERIC_ATTACK_DAMAGE,
                new EntityAttributeModifier(ATTACK_DAMAGE_MODIFIER_ID, "Weapon modifier", 6,
                    EntityAttributeModifier.Operation.ADDITION
                )
            );
            map.put(
                EntityAttributes.GENERIC_ATTACK_SPEED,
                new EntityAttributeModifier(ATTACK_SPEED_MODIFIER_ID, "Weapon modifier",
                    (double) this.attackSpeed, EntityAttributeModifier.Operation.ADDITION));

        }

        return map;
    }

}
