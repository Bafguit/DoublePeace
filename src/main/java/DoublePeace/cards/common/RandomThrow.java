package DoublePeace.cards.common;

import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.cards.temp.ThrowingKnife;
import DoublePeace.characters.TheChaser;

import static DoublePeace.DPMod.makeCardPath;

public class RandomThrow extends ChaserCard {

    public static final String ID = DPMod.makeID("Random Throw");
    public static final String IMG = makeCardPath("RandomThrow.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;
    private static final int TAG = 3;
    private static final int UP_TAG = 1;

    public RandomThrow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, TAG);
        this.cardsToPreview = new ThrowingKnife();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        this.addToBot(new MakeTempCardInHandAction(new ThrowingKnife(), this.magicNumber));
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_TAG);
    }

}
