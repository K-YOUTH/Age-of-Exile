package com.robertx22.age_of_exile.database.data.spells.blocks.magma_flower;

import com.robertx22.age_of_exile.database.data.spells.blocks.base.BaseSpellBlock;
import com.robertx22.age_of_exile.mmorpg.Ref;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class MagmaFlowerBlock extends BaseSpellBlock {
    public static final String ID = Ref.MODID + ":magma_flower";

    public MagmaFlowerBlock() {
        super();

    }

    @Override
    public BlockEntity createBlockEntity(BlockView world) {

        return new MagmaFlowerTileEntity();

    }

}
