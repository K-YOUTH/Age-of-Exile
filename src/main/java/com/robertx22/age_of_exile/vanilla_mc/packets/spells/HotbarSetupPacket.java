package com.robertx22.age_of_exile.vanilla_mc.packets.spells;

import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.age_of_exile.saveclasses.item_classes.SkillGemData;
import com.robertx22.age_of_exile.saveclasses.spells.SpellCastingData;
import com.robertx22.age_of_exile.uncommon.datasaving.Load;
import com.robertx22.age_of_exile.uncommon.datasaving.SkillGem;
import com.robertx22.age_of_exile.uncommon.utilityclasses.PlayerUtils;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.PlayerCaps;
import com.robertx22.age_of_exile.vanilla_mc.packets.sync_cap.SyncCapabilityToClient;
import com.robertx22.library_of_exile.main.MyPacket;
import com.robertx22.library_of_exile.main.Packets;
import net.fabricmc.fabric.api.network.PacketContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.util.Identifier;

public class HotbarSetupPacket extends MyPacket<HotbarSetupPacket> {

    public int number;
    public int invSlot = 0;

    public HotbarSetupPacket() {

    }

    public HotbarSetupPacket(int invSlot, int num) {
        this.number = num;
        this.invSlot = invSlot;
    }

    @Override
    public Identifier getIdentifier() {
        return new Identifier(Ref.MODID, "hotbarsetup");
    }

    @Override
    public void loadFromData(PacketByteBuf tag) {
        number = tag.readInt();
        invSlot = tag.readInt();

    }

    @Override
    public void saveToData(PacketByteBuf tag) {

        tag.writeInt(number);
        tag.writeInt(invSlot);

    }

    @Override
    public void onReceived(PacketContext ctx) {

        PlayerEntity player = ctx.getPlayer();

        SpellCastingData data = Load.spells(player)
            .getCastingData();

        if (invSlot < 0) {
            SkillGemData skillgem = data.getHotbar()
                .get(number);
            if (skillgem != null) {
                PlayerUtils.giveItem(skillgem.toItemStack(), player);
                data.getHotbar()
                    .remove(number);
            }
        } else {
            ItemStack stack = player.inventory.main.get(invSlot);

            SkillGemData skillgem = SkillGem.Load(stack);

            if (skillgem != null) {

                stack.decrement(1);

                data.setHotbar(number, skillgem);

                Packets.sendToClient(player, new SyncCapabilityToClient(player, PlayerCaps.SPELLS));
            }
        }
    }

    @Override
    public MyPacket<HotbarSetupPacket> newInstance() {
        return new HotbarSetupPacket();
    }
}
