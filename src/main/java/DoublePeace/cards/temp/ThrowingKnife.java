package DoublePeace.cards.temp;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.actions.RandomAttackAction;
import DoublePeace.abstracts.ChaserCard;

import static DoublePeace.DPMod.makeCardPath;

public class ThrowingKnife extends ChaserCard {

    public static final String ID = DPMod.makeID("Throwing Knife");
    public static final String IMG = makeCardPath("ThrowingKnife.png");

    private static final CardRarity RARITY = CardRarity.SPECIAL;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = CardColor.COLORLESS;

    private static final int COST = 0;
    private static final int DAMAGE = 3;
    private static final int UP_DMG = 2;

    public ThrowingKnife() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, DAMAGE, 0, 0);
        this.exhaust = true;
        this.isEthereal = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new RandomAttackAction(this, AttackEffect.BLUNT_LIGHT, true));
    }

    @Override
    public void upgradeCard() {
        upgradeDamage(UP_DMG);
    }

}
