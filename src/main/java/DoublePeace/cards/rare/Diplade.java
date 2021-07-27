package DoublePeace.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.cards.temp.ThrowingKnife;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.DipladePower;

import static DoublePeace.DPMod.makeCardPath;

public class Diplade extends ChaserCard {

    public static final String ID = DPMod.makeID("Diplade");
    public static final String IMG = makeCardPath("Diplade.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 2;
    private static final int UP_COST = 1;

    public Diplade() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        this.cardsToPreview = new ThrowingKnife();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DipladePower(p, 1)));
    }

    @Override
    public void upgradeCard() {
        upgradeBaseCost(UP_COST);
    }

}
