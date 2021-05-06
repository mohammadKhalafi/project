package Model.Enums;

public enum CardNames {

    TRAP_HOLE("Model.Card.Monster.NormalMonster"),
    MIRROR_FORCE(""),
    MAGIC_CYLINDER(""),
    MIND_CRUSH(""),
    TORRENTIAL_TRIBUTE(""),
    TIME_SEAL(""),
    NEGATE_ATTACK(""),
    SOLEMN_WARNING(""),
    MAGIC_JAMMER(""),
    CALL_OF_THE_HAUNTED(""),
    VANITYS_EMPTINESS(""),
    WALL_OF_REVEALING_LIGHT(""),
    MONSTER_REBORN(""),
    TERRAFORMING(""),
    POT_OF_GREED(""),
    RAIGEKI(""),
    CHANGE_OF_HEART(""),
    SWORDS_OF_REVEALING_LIGHT(""),
    HARPIES_FEATHER_DUSTER(""),
    DARK_HOLE(""),
    SUPPLY_SQUAD(""),
    SPELL_ABSORPTION(""),
    MESSENGER_OF_PEACE(""),
    TWIN_TWISTERS(""),
    MYSTICAL_SPACE_TYPHOON(""),
    RING_OF_DEFENCE(""),
    YAMI(""),
    FOREST(""),
    CLOSED_FOREST(""),
    UMIIRUKA(""),
    SWORD_OF_DARK_DESTRUCTION(""),
    BLACK_PENDANT(""),
    UNITED_WE_STAND(""),
    MAGNUM_SHIELD(""),
    ADVANCED_RITUAL_ART(""),

    BATTEL_OX("Model.Card.Monster.NormalMonster"),
    AXE_RAIDER("Model.Card.Monster.NormalMonster"),
    YOMI_SHIP("Model.Card.EffectMonsters.YomiShip"),
    HORN_IMP("Model.Card.Monster.NormalMonster"),
    SILVER_FANG("Model.Card.Monster.NormalMonster"),
    SUIJIN("Model.Card.Monster.Suijin"),
    FIREYAROU("Model.Card.Monster.NormalMonster"),
    CURTAIN_OF_THE_DARD_ONES("Model.Card.Monster.NormalMonster"),
    FERAL_IMP("Model.Card.Monster.NormalMonster"),
    DARK_MAGICIAN("Model.Card.Monster.NormalMonster"),
    WATTKID("Model.Card.Monster.NormalMonster"),
    BABY_DRAGON("Model.Card.Monster.NormalMonster"),
    HERO_OF_THE_EAST("Model.Card.Monster.NormalMonster"),
    BATTLE_WARRIOR("Model.Card.Monster.NormalMonster"),
    CRAWLING_DRAGON("Model.Card.Monster.NormalMonster"),
    FLAME_MANIPULATOR("Model.Card.Monster.NormalMonster"),
    BLUE_EYES_WHITE_DRAGON("Model.Card.Monster.NormalMonster"),
    CRAB_TURTLE("Model.Card.Monster.CrabTurtle"),
    SKULL_GUARDIAN("Model.Card.Monster.SkullGuardian"),
    SLOT_MACHINE("Model.Card.Monster.NormalMonster"),
    HANIWA("Model.Card.Monster.NormalMonster"),
    MAN_EATER_BUG("Model.Card.Monster.ManEaterBug"),
    GATE_GUARDIAN("Model.Card.Monster.GateGuardian"),
    SCANNERMonster("Model.Card.Monster.ScannerMonster"),
    BITRON("Model.Card.Monster.NormalMonster"),
    MARSHMALLON("Model.Card.Monster.Marshmallon"),
    BEAST_KING_BARBAROS("Model.Card.Monster.BeastKingBarbaros"),
    TEXTCHANGER("Model.Card.Monster.Textchanger"),
    LEOTRON("Model.Card.Monster.NormalMonster"),
    THE_CALCULATOR("Model.Card.Monster.TheCalculator"),
    ALEXANDRITE_DRAGON("Model.Card.Monster.NormalMonster"),
    MIRAGE_DRAGON("Model.Card.Monster.MirageDragon"),
    HERALD_OF_CREATION("Model.Card.Monster.HeraldOfCreation"),
    EXPLODER_DRAGON("Model.Card.Monster.ExploderDragon"),
    WARRIOR_DAI_GREPHER("Model.Card.Monster.NormalMonster"),
    DARK_BLADE("Model.Card.Monster.NormalMonster"),
    WATTAILDRAGON("Model.Card.Monster.NormalMonster"),
    TERRATIGER__THE_EMPOWERED_WARRIOR("Model.Card.Monster.TerratigerTheEmpoweredWarrior"),
    THE_TRICKY("Model.Card.Monster.TheTricky"),
    SPIRAL_SERPENT("Model.Card.Monster.NormalMonster");

    String className;

    CardNames(String className) {
        this.className = className;
    }

    public String getClassName() {
        return this.className;
    }


}
