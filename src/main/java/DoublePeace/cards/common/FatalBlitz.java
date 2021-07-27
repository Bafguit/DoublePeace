package DoublePeace.cards.common;

import com.megacrit.cardcrawl.actions.AbstractGameAction.AttackEffect;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;
import DoublePeace.powers.BleedingPower;

import static DoublePeace.DPMod.makeCardPath;

public class FatalBlitz extends ChaserCard {

    public static final String ID = DPMod.makeID("Fatal Blitz");
    public static final String IMG = makeCardPath("FatalBlitz.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 2;
    private static final int DAMAGE = 15;
    private static final int UP_DMG = 2;
    private static final int BLEED = 5;
    private static final int UP_BLEED = 2;

    public FatalBlitz() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, DAMAGE, 0, BLEED);
        loadJokeCardImage(this, "FatalBlitz");
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, this.damage), AttackEffect.BLUNT_HEAVY));
        addToBot(new ApplyPowerAction(m, p, new BleedingPower(m, p, this.magicNumber), this.magicNumber));
    }

    @Override
    public void upgradeCard() {
        upgradeDamage(UP_DMG);
        upgradeMagicNumber(UP_BLEED);
    }

}
