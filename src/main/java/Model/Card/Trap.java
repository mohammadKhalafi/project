package Model.Card;

import Model.Enums.LabelsForActivatingTraps;
import Model.Enums.SpellsAndTraps.TrapTypes;

public abstract class Trap extends Card {
    TrapTypes trapType;
    LabelsForActivatingTraps activationLabel;

    public boolean labelExists() {
        return false;
    }


    public void counter() {

    }

    public abstract void activate();

}
