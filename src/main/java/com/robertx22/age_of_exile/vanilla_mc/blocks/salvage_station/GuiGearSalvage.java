package com.robertx22.age_of_exile.vanilla_mc.blocks.salvage_station;

import com.mojang.blaze3d.systems.RenderSystem;
import com.robertx22.age_of_exile.mmorpg.Ref;
import com.robertx22.library_of_exile.utils.CLOC;
import com.robertx22.age_of_exile.uncommon.localization.Words;
import com.robertx22.library_of_exile.gui.HelpButton;
import com.robertx22.library_of_exile.tile_bases.TileGui;
import com.robertx22.library_of_exile.utils.GuiUtils;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.text.LiteralText;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Environment(EnvType.CLIENT)
public class GuiGearSalvage extends TileGui<ContainerGearSalvage, TileGearSalvage> {

    // This is the resource location for the background image
    private static final Identifier texture = new Identifier(Ref.MODID, "textures/gui/salvage_station.png");

    public GuiGearSalvage(ContainerGearSalvage cont, PlayerInventory invPlayer,
                          MutableText comp) {
        super(cont, invPlayer, new LiteralText("Salvage"), TileGearSalvage.class);

        backgroundWidth = 176;
        backgroundHeight = 207;
    }

    // some [x,y] coordinates of graphical elements
    final int COOK_BAR_XPOS = 49;
    final int COOK_BAR_YPOS = 60;
    final int COOK_BAR_ICON_U = 0; // texture position of white arrow icon
    final int COOK_BAR_ICON_V = 207;
    final int COOK_BAR_WIDTH = 80;
    final int COOK_BAR_HEIGHT = 17;

    @Override
    protected void init() {
        super.init();

        List<Text> list = new ArrayList<>();

        list.add(new LiteralText("Here you can salvage Age of Exile items:"));
        list.add(new LiteralText("* Gear with Exile stats"));
        list.add(new LiteralText("* jewels"));
        list.add(new LiteralText("* currencies"));
        list.add(new LiteralText(""));
        list.add(new LiteralText("In return you get valuable materials,"));
        list.add(new LiteralText("usually used to repair gear."));
        list.add(new LiteralText(""));
        list.add(new LiteralText("Sometimes, currencies can drop too."));

        this.addButton(new HelpButton(list, this.x + this.backgroundWidth, this.y));

    }

    @Override
    protected void drawBackground(MatrixStack matrix, float partialTicks, int x, int y) {

        // Bind the image texture
        MinecraftClient.getInstance()
            .getTextureManager()
            .bindTexture(texture);
        // Draw the image
        RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        drawTexture(matrix, this.x, this.y, 0, 0, backgroundWidth, backgroundHeight);

        // get cook progress as a double between 0 and 1
        double cookProgress = tile.fractionOfCookTimeComplete();
        // draw the cook progress bar
        drawTexture(matrix, this.x + COOK_BAR_XPOS, this.y + COOK_BAR_YPOS, COOK_BAR_ICON_U, COOK_BAR_ICON_V, (int) (cookProgress * COOK_BAR_WIDTH), COOK_BAR_HEIGHT);

        this.buttons.forEach(b -> b.renderToolTip(matrix, x, y));

    }

    @Override
    protected void drawForeground(MatrixStack matrix, int mouseX, int mouseY) {
        super.drawForeground(matrix, mouseX, mouseY);

        final int LABEL_XPOS = 5;
        final int LABEL_YPOS = 5;

        font.draw(matrix, CLOC.translate(tile.getDisplayName()), LABEL_XPOS, LABEL_YPOS, Color.darkGray
            .getRGB());

        List<String> hoveringText = new ArrayList<String>();

        // If the mouse is over the progress bar add the progress bar hovering text
        if (GuiUtils.isInRect(x + COOK_BAR_XPOS, y + COOK_BAR_YPOS, COOK_BAR_WIDTH, COOK_BAR_HEIGHT, mouseX, mouseY)) {
            hoveringText.add(Words.Progress.translate() + ": ");
            int cookPercentage = (int) (tile.fractionOfCookTimeComplete() * 100);
            hoveringText.add(cookPercentage + "%");
        }

        // If hoveringText is not empty draw the hovering text
        if (!hoveringText.isEmpty()) {

            // renderTooltip(hoveringText, mouseX - x, mouseY - y, font); todo
        }
    }

}