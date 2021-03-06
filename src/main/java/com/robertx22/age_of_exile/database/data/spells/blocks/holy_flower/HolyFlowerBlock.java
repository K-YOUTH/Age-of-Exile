package com.robertx22.age_of_exile.database.data.spells.blocks.holy_flower;

import com.robertx22.age_of_exile.database.data.spells.blocks.base.BaseSpellBlock;
import com.robertx22.age_of_exile.mmorpg.Ref;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class HolyFlowerBlock extends BaseSpellBlock {
    public static final String ID = Ref.MODID + ":holy_flower";

    public HolyFlowerBlock() {
        super();

    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {
        return new HolyFlowerTileEntity();
    }
}
