package DoublePeace.cards.rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.DualWieldingPower;

import static DoublePeace.DPMod.makeCardPath;

public class DualWielding extends ChaserCard {

    public static final String ID = DPMod.makeID("Dual Wielding");
    public static final String IMG = makeCardPath("DualWielding.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;
    private static final int UP_COST = 0;

    public DualWielding() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new DualWieldingPower(p)));
    }


    @Override
    public void upgradeCard() {
        upgradeBaseCost(UP_COST);
    }
}
