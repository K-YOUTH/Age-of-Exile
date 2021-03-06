package com.robertx22.age_of_exile.database.data.spell_schools.parser;

import com.robertx22.age_of_exile.database.data.perks.Perk;
import com.robertx22.age_of_exile.database.registry.SlashRegistry;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.awt.*;

public class GridPoint {

    public int x;
    public int y;
    private String id;

    public String getId() {
        return id.toLowerCase();
    }

    public GridPoint(int x, int y, String str) {
        this.x = x;
        this.y = y;
        this.id = str;
    }

    public Point getPoint() {
        return new Point(x, y);
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this, false);
    }

    @Override
    public boolean equals(Object obj) {
        return EqualsBuilder.reflectionEquals(this, obj, false);
    }

    public Perk getPerk() {
        // handle both caps and lowercase
        String id = getId();

        if (!SlashRegistry.Perks()
            .isRegistered(id)) {
            id = id.toLowerCase();
            if (!SlashRegistry.Perks()
                .isRegistered(id)) {
                id = id.toUpperCase();
            }
        }

        return SlashRegistry.Perks()
            .get(id);
    }

    public static String CENTER_ID = "[CENTER]";

    public boolean isTalent() {
        return !id.isEmpty() && id.length() > 2 && !isCenter();
    }

    public boolean isCenter() {
        return id.equalsIgnoreCase(CENTER_ID);
    }

    public boolean isConnector() {
        return id.equals("O") || id.equals("o");
    }

}