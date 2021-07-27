package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.AccelerationPower;

import static DoublePeace.DPMod.makeCardPath;

public class Acceleration extends ChaserCard {

    public static final String ID = DPMod.makeID("Acceleration");
    public static final String IMG = makeCardPath("Acceleration.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;
    private static final int STR = 1;

    public Acceleration() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, STR);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new AccelerationPower(p, this.magicNumber)));
    }


    @Override
    public void upgradeCard() {
        upgradeBaseCost(0);
    }
}
