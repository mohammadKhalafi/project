package Model.Card.Spells;

import Model.Card.Spell;
import Model.Card.TrapAndSpellTypes.Undo;
import Model.Enums.MonsterEnums.MonsterType;

import java.util.ArrayList;

public class IncreaserType1 extends Spell implements Undo {
    public ArrayList<MonsterType> monsterTypes = new ArrayList<>();
    public int amountToIncreaseAttack;
    public int amountToIncreaseDefence;

    @Override
    public void activate() {

    }

    @Override
    public void undo() {

    }
}
