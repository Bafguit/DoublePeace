package DoublePeace.cards.temp;

import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.actions.TargetAttackAction;
import DoublePeace.abstracts.ChaserCard;

import static DoublePeace.DPMod.makeCardPath;

public class KnifeThrow extends ChaserCard {

    public static final String ID = DPMod.makeID("Knife Throw");
    public static final String IMG = makeCardPath("KnifeThrow.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 1;
    private static final int AMOUNT = 3;
    private static final int UP_AMOUNT = 1;

    public KnifeThrow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, AMOUNT);
    }

    public void onChoseThisOption() {
        for(int i = 0; i < this.magicNumber; i++) {
            this.addToBot(new TargetAttackAction());
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_AMOUNT);
    }

}
