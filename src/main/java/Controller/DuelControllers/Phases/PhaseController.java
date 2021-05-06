package Controller.DuelControllers.Phases;

import Controller.DuelControllers.GameData;

public class PhaseController extends AllPhases {
    private GameData gamedata;

    public PhaseController(GameData gamedata) {
        this.gamedata = gamedata;
    }

    public String run() {
        boolean gameIsOver = false;
        while (!gameIsOver) {
            if (gamerCanDraw()) {
                new DrawPhase(gamedata).run();
                new StandbyPhase(gamedata).run();
                new MainPhase(gamedata, 1).run();
                new BattlePhase(gamedata).run();
                new MainPhase(gamedata, 2).run();
                new EndPhase(gamedata).run();
                gameIsOver = gamedata.isGameOver();
            }
        }
        return "";
    }

    private boolean gamerCanDraw() {
        return gamedata.getFirstGamer().getGameBoard().deckZone.getSize() != 0;
    }

}
