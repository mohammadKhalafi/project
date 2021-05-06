package Controller.DuelControllers;

import Model.Card.Card;
import Model.Card.Monster;
import Model.Card.State;
import Model.User;

import java.util.regex.Matcher;

public class CardsController {
    private Card card;
    private User user;
    private boolean canIActivateTrap = true;
    private boolean canEnemyAttack = true;
    private static CardsController instance = null;

    public static CardsController getInstance() {
        if (instance == null) {
            instance = new CardsController();
        }
        return instance;
    }

    public void manageCardController() {

    }

    private void destroyCard(Card card) {

    }


    private void summonMonster(Matcher matcher) {


    }

    private void setMonster(Matcher matcher) {

    }

    private void changeMode(Matcher matcher) {

        /*

         */


    }

    private void flipSummon(Matcher matcher) {

    }

    private void attackMonster(Matcher matcher) {

    }

    private void attackDirect(Matcher matcher) {

    }

    private void activateEffect(Matcher matcher) {

    }

    private void setSpell(Matcher matcher) {

    }

    private void setTrap(Matcher matcher) {

    }

    private void activateSpellOrTrap(Matcher matcher) {

    }

    private void ritualSummon(Matcher matcher) {

    }

    private void specialSummon(Matcher matcher) {

    }

    private void showGraveYard(Matcher matcher) {

    }

    private void showAllCards(Matcher matcher) {

    }

    private void handleScores(Matcher matcher) {

    }

    public void handleAttackIncrease() {

    }

    public void handleDefenceIncrease() {

    }

    public void killMonster() {

    }

    public void handleConstantScore(Card attackerCard) {

    }

    public void summonWithRitualCards(Card crabTurtleOrSkullGuardianCard) {

    }

    public Card swapWithRivalsCards(Card card) {

        return null;
    }

    public void deleteCardsOnRivalsControl() {

    }

    public void cancelAttack() {

    }

    private int getSumOfMonstersLevelsOfRival() {

        return 1;
    }

    public void setCanIActivateTrap(boolean canTrapBeActivated) {
        canIActivateTrap = canTrapBeActivated;
    }

    public void getFromGraveYard() {

    }

    public void summonInDefenseMod() {

    }

    public void changePosition(String source, String destination) {

    }

    public void changeState(State state) {

    }

    public void setCanEnemyAttack(boolean canEnemyAttack) {
        this.canEnemyAttack = canEnemyAttack;
    }

    public void setMonsterUnattackabel() {

    }

    public void endBattlePhase() {

    }

    public void setMonsterEffect() {

    }

    public void reduceEffect() {

    }

    public void activeOnAttack() {

    }

    public void drawCard() {

    }

    public String getMonsterUserAndPosition(Monster monster) {
        return "11";
    }

}
