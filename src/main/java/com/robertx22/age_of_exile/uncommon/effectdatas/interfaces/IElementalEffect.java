package com.robertx22.age_of_exile.uncommon.effectdatas.interfaces;

import com.robertx22.age_of_exile.uncommon.enumclasses.Elements;

public interface IElementalEffect extends IEffect {

    public abstract Elements GetElement();

    void setElement(Elements ele);

}
