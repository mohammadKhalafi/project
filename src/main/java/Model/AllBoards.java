package Model;

import Model.Board.*;

public class AllBoards {
    public DeckZone deckZone = new DeckZone();
    public FieldZone fieldZone = new FieldZone();
    public GraveYard graveYard = new GraveYard();
    public MonsterCardZone monsterCardZone = new MonsterCardZone();
    public SpellAndTrapCardZone spellAndTrapCardZone = new SpellAndTrapCardZone();
    public Hand hand = new Hand();

    void sendFromOneZoneToOther(Zones firstZone, Zones secondZone, int id) {
        secondZone.addCard(firstZone.removeCard(id));
    }


    public String selfToString(){
        return "\n" +
                fieldZone.toString() + "\t".repeat(6) + graveYard.getSize() + "\n" +
                monsterCardZone.toString() + "\n" +
                spellAndTrapCardZone.toString() + "\n" +
                "\t".repeat(6) + deckZone.getSize() + "\n" +
                hand.selfToString() + "\n";
    }

    public String rivalToString(){
        return hand.rivalToString()+"\n" +
                deckZone.getSize()+"    ".repeat(6)+"\n"+
                spellAndTrapCardZone.toString()+"\n"+
                monsterCardZone.toString()+"\n"+
                graveYard.getSize()+"\t".repeat(6)+fieldZone.toString()+"\n";
    }
}
