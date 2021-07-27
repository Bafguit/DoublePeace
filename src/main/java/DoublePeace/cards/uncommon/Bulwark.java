package DoublePeace.cards.uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import DoublePeace.DPMod;
import DoublePeace.abstracts.ChaserCard;
import DoublePeace.characters.TheChaser;

import static DoublePeace.DPMod.makeCardPath;

public class Bulwark extends ChaserCard {

    public static final String ID = DPMod.makeID("Bulwark");
    public static final String IMG = makeCardPath("Bulwark.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheChaser.Enums.COLOR_CHASER;

    private static final int COST = 1;
    private static final int DAMAGE = 1;
    private static final int UP_DAMAGE = 1;

    public Bulwark() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, 0, 0, DAMAGE);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber)));
        addToBot(new ApplyPowerAction(p, p, new DexterityPower(p, this.magicNumber)));
    }

    @Override
    public void upgradeCard() {
        upgradeMagicNumber(UP_DAMAGE);
    }

}