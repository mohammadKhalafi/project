package Model.Card.Spells;

import Model.Card.Monster;
import Model.Card.Spell;
import Model.Card.TrapAndSpellTypes.ContinuousEffect;
import Model.Card.TrapAndSpellTypes.Undo;

public class ChangeOfHeart extends Spell implements ContinuousEffect, Undo {
    Monster monster;

    @Override
    public void undo() {

    }

    @Override
    public void activate() {

    }

    @Override
    public void checkActivation() {

    }
}
